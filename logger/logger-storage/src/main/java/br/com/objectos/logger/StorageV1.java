/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.objectos.logger;

import br.com.objectos.concurrent.IoWorker;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import br.com.objectos.fs.SimplePathNameVisitor;
import br.com.objectos.fs.watch.Watch;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

final class StorageV1 extends Storage {

  static final Charset CHARSET = Charsets.utf8();

  // header + type + level + timestamp
  static final int HEADER_LENGTH = 4 + 1 + 1 + 8;

  static final byte[] LOG = "O7LG".getBytes(CHARSET);

  static final String LOG_NAME = "v1";

  static final int MAX_STRING_SIZE = 1 << 16;

  static final byte NOT_THROWABLE = 127;

  static final byte POP_THROWABLE = 125;

  static final byte THROWABLE = 124;

  static final byte THROWABLE_CAUSE = 123;

  static final byte THROWABLE_SUPPRESSED = 122;

  static final byte TYPE0 = 0;

  static final byte TYPE1 = 1;

  static final byte TYPE2 = 2;

  static final byte TYPE3 = 3;

  private static final Opener OPENER = new Opener();

  private static final byte[] SIGNATURE = "UNCO".getBytes(CHARSET);

  private static final int V1 = 1;

  private final RegularFile logFile;

  StorageV1(RegularFile logFile) {
    this.logFile = logFile;
  }

  public static StorageV1 create(Directory directory) throws IOException {
    Directory dataDirectory;
    dataDirectory = directory.createDirectory("data");

    Directory versionDirectory;
    versionDirectory = directory.createDirectory("version");

    RegularFile file;
    file = versionDirectory.createRegularFile("v001");

    long timestamp;
    timestamp = System.currentTimeMillis();

    byte[] bytes;
    bytes = new byte[] {
                        (byte) (timestamp >>> 56),
                        (byte) (timestamp >>> 48),
                        (byte) (timestamp >>> 40),
                        (byte) (timestamp >>> 32),
                        (byte) (timestamp >>> 24),
                        (byte) (timestamp >>> 16),
                        (byte) (timestamp >>> 8),
                        (byte) timestamp,
    };

    try (OutputStream out = file.openOutputStream()) {
      out.write(bytes);
    }

    RegularFile logFile;
    logFile = dataDirectory.createRegularFile(LOG_NAME);

    try (FileChannel channel = logFile.openWriteChannel()) {
      ByteBuffer buffer;
      buffer = ByteBuffer.allocate(8);

      buffer.order(ByteOrder.BIG_ENDIAN);

      buffer.put(SIGNATURE);

      buffer.putInt(V1);

      buffer.flip();

      channel.write(buffer);
    }

    return new StorageV1(logFile);
  }

  public static Storage openIfPossible(
      Directory directory, Directory versionDirectory) throws IOException {
    ResolvedPath maybeV1;
    maybeV1 = versionDirectory.resolve("v001");

    return maybeV1.acceptPathNameVisitor(OPENER, directory);
  }

  @Override
  public final ReadJob createReadJob(
      IoWorker ioWorker, ImmutableList<LogListener> listeners) throws IOException {
    FileChannel channel;
    channel = logFile.openReadChannel();

    // skip signature
    channel.position(8);

    return new StorageV1ReadJob(channel, ioWorker, listeners);
  }

  @Override
  public final WriteJob createWriteJob(
      IoWorker ioWorker, ImmutableList<LogListener> listeners) throws IOException {
    FileChannel channel;
    channel = logFile.openWriteChannel();

    channel.position(channel.size());

    return new StorageV1WriteJob(channel, ioWorker, listeners);
  }

  @Override
  public final void watchDirectory(Watch.ServiceBuilder builder, Watch.Listener listener)
      throws IOException {
    Directory directory;
    directory = logFile.getParent();

    builder.watchDirectory(directory, listener, Watch.MODIFIED);
  }

  final ByteBuffer createByteBuffer() {
    ByteBuffer buffer;
    buffer = ByteBuffer.allocate(8192);

    buffer.order(ByteOrder.BIG_ENDIAN);

    return buffer;
  }

  private static class Opener extends SimplePathNameVisitor<Storage, Directory> {
    @Override
    public final Storage visitRegularFile(RegularFile file, Directory p) throws IOException {
      RegularFile logFile;

      try {
        Directory dataDirectory;
        dataDirectory = p.getDirectory("data");

        logFile = dataDirectory.getRegularFile(LOG_NAME);
      } catch (IOException e) {
        throw new IOException("Corrupt storage: log file not found", e);
      }

      try (FileChannel channel = logFile.openReadChannel()) {
        ByteBuffer buffer;
        buffer = ByteBuffer.allocate(8);

        buffer.order(ByteOrder.BIG_ENDIAN);

        channel.position(0);

        channel.read(buffer);

        buffer.flip();

        byte[] byteArray;
        byteArray = new byte[4];

        buffer.get(byteArray, 0, 4);

        boolean valid;
        valid = true
            && byteArray[0] == SIGNATURE[0]
            && byteArray[1] == SIGNATURE[1]
            && byteArray[2] == SIGNATURE[2]
            && byteArray[3] == SIGNATURE[3];

        if (!valid) {
          throw new IOException("Invalid file format: SIGNATURE was not found.");
        }

        int version;
        version = buffer.getInt();

        if (version != V1) {
          throw new IOException("Invalid file format: unsupported version " + version);
        }
      }

      return new StorageV1(logFile);
    }
  }

}