/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.smtp.mail;

import br.com.objectos.core.io.Write;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.smtp.ConfigurationException;
import br.com.objectos.smtp.MailBox;
import br.com.objectos.smtp.MailObject;
import br.com.objectos.smtp.MailStore;
import br.com.objectos.smtp.ServerBuilder;
import br.com.objectos.smtp.UUIDs;
import br.com.objectos.smtp.server.ProcessingResult;
import br.com.objectos.smtp.server.Transaction;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;
import objectos.lang.Checks;

public final class FsMailStore implements MailStore {

  private static final byte CR = 13;
  private static final byte DOT = 46;
  private static final byte LF = 10;

  private final Directory mailboxDirectory;
  private final Directory objectsDirectory;

  private FsMailStore(Directory mailboxDirectory, Directory objectsDirectory) {
    this.mailboxDirectory = mailboxDirectory;
    this.objectsDirectory = objectsDirectory;
  }

  public static FsMailStore create(Directory directory) throws ConfigurationException {
    Checks.checkNotNull(directory, "directory == null");

    Directory mailbox;

    try {
      mailbox = directory.resolve("mailbox").toDirectoryCreateIfNotFound();
    } catch (IOException e) {
      throw new ConfigurationException("Could not access or create mailbox directory", e);
    }

    Directory objects;

    try {
      objects = directory.resolve("objects").toDirectoryCreateIfNotFound();
    } catch (IOException e) {
      throw new ConfigurationException("Could not access or create objects directory", e);
    }

    return new FsMailStore(mailbox, objects);
  }

  @Override
  public final void acceptServerBuilder(ServerBuilder builder) {
    builder.setMailStore(this);
  }

  @Override
  public final MailBox getMailBox(String localPart) throws IOException {
    RegularFile messages;
    messages = mailboxDirectory.getOrCreateRegularFile(localPart);

    FileChannel messagesChannel;
    messagesChannel = messages.openReadChannel();

    return new FsMailBox(this, messagesChannel);
  }

  @Override
  public final Transaction startTransaction() throws IOException {
    UUID uuid;
    uuid = UUID.randomUUID();

    String hex;
    hex = UUIDs.toHexString(uuid);

    Directory hexDirectory;
    hexDirectory = getHexDirectory(hex);

    String fileNameHex;
    fileNameHex = hex.substring(2);

    RegularFile metaPath;
    metaPath = hexDirectory.createRegularFile(fileNameHex + ".meta");

    FileChannel metaChannel;
    metaChannel = metaPath.openReadAndWriteChannel();

    RegularFile dataPath;
    dataPath = hexDirectory.createRegularFile(fileNameHex + ".data");

    FileChannel dataChannel;
    dataChannel = dataPath.openReadAndWriteChannel();

    return new ThisTransaction(uuid, metaChannel, dataChannel);
  }

  final MailObject getMailObject(String hex) throws IOException {
    Directory hexDirectory;
    hexDirectory = getHexDirectory(hex);

    String fileNameHex;
    fileNameHex = hex.substring(2);

    RegularFile metaFile;
    metaFile = hexDirectory.getRegularFile(fileNameHex + ".meta");

    RegularFile dataFile;
    dataFile = hexDirectory.getRegularFile(fileNameHex + ".data");

    return new FsMailObject(metaFile, dataFile);
  }

  private Directory getHexDirectory(String hex) throws IOException {
    String directoryName;
    directoryName = hex.substring(0, 2);

    return objectsDirectory.resolve(directoryName).toDirectoryCreateIfNotFound();
  }

  private class ThisTransaction implements Transaction {

    private final FileChannel dataChannel;
    private final ByteBuffer metaBuffer = ByteBuffer.allocateDirect(256);
    private final FileChannel metaChannel;

    private ProcessingResult processingResult = ProcessingResult.EMPTY;

    private final UUID uuid;

    public ThisTransaction(UUID uuid,
                           FileChannel metaChannel,
                           FileChannel dataChannel) {
      this.uuid = uuid;
      this.metaChannel = metaChannel;
      this.dataChannel = dataChannel;
    }

    @Override
    public final void addForwardPath(ForwardPath forwardPath) throws IOException {
      String localPart;
      localPart = forwardPath.getLocalPart();

      RegularFile messages;
      messages = mailboxDirectory.getOrCreateRegularFile(localPart);

      byte[] bytes;
      bytes = UUIDs.toByteArray(uuid);

      synchronized (forwardPath) {
        Write.byteArray(messages, bytes);
      }
    }

    @Override
    public final void commit() {
      writeMeta();

      close(metaChannel);
      close(dataChannel);
    }

    @Override
    public final ProcessingResult processData() throws IOException {
      switch (processingResult) {
        case EMPTY:
        case KEEP_RECEIVING:
          ProcessingResult result;
          result = processData0();

          return processingResult = result;
        default:
          throw new UnsupportedOperationException("Implement me");
      }
    }

    @Override
    public final void rollback() {
      throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public final void setClientName(ClientName clientName) {
      metaWrite(Meta.CLIENT_NAME, clientName);
      // write receive date
      // write cliente ip
    }

    @Override
    public final void setReversePath(ReversePath reversePath) {
      metaWrite(Meta.REVERSE_PATH, reversePath);
    }

    @Override
    public final void writeData(ByteBuffer byteBuffer) throws IOException {
      dataChannel.write(byteBuffer);
    }

    private void close(FileChannel c) {
      try {
        c.close();
      } catch (IOException e) {
      }
    }

    private void metaWrite(Meta meta, Object o) {
      metaBuffer.putInt(meta.ordinal());

      String string;
      string = o.toString();

      byte[] bytes;
      bytes = string.getBytes(Charsets.UTF8);

      metaBuffer.putInt(bytes.length);

      metaBuffer.put(bytes);
    }

    private boolean peekTail() throws IOException {
      long currentPosition;
      currentPosition = dataChannel.position();

      dataChannel.position(currentPosition - 5);

      ByteBuffer tailBuffer;
      tailBuffer = ByteBuffer.allocate(5);

      int read;
      read = dataChannel.read(tailBuffer);

      dataChannel.position(currentPosition);

      if (read != 5) {
        throw new UnsupportedOperationException("Implement me");
      }

      byte[] tail;
      tail = tailBuffer.array();

      if (tail[0] != CR) {
        return false;
      }

      if (tail[1] != LF) {
        return false;
      }

      if (tail[2] != DOT) {
        return false;
      }

      if (tail[3] != CR) {
        return false;
      }

      if (tail[4] != LF) {
        return false;
      }

      return true;
    }

    private ProcessingResult processData0() throws IOException {
      long size;
      size = dataChannel.size();

      if (size < 5) {
        return ProcessingResult.KEEP_RECEIVING;
      }

      boolean foundAtTail;
      foundAtTail = peekTail();

      if (foundAtTail) {
        return ProcessingResult.COMPLETE;
      }

      throw new UnsupportedOperationException("Implement me");
    }

    private void writeMeta() {
      metaBuffer.flip();

      try {
        while (metaBuffer.hasRemaining()) {
          metaChannel.write(metaBuffer);
        }
      } catch (IOException e) {

      }
    }

  }

}
