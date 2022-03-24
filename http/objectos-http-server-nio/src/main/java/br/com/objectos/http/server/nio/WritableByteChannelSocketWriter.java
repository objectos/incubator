/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.http.server.nio;

import br.com.objectos.http.server.SocketWriter;
import br.com.objectos.io.InputStreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

class WritableByteChannelSocketWriter implements SocketWriter {

  private static final String NEW_LINE = "\r\n";

  private final ByteBuffer buffer;
  private final WritableByteChannel channel;

  private WritableByteChannelSocketWriter(WritableByteChannel channel, ByteBuffer buffer) {
    this.channel = channel;
    this.buffer = buffer;
  }

  public static WritableByteChannelSocketWriter of(WritableByteChannel channel, ByteBuffer buffer) {
    buffer.clear();
    return new WritableByteChannelSocketWriter(channel, buffer);
  }

  @Override
  public void close() throws IOException {
    channel.close();
  }

  @Override
  public void flush() throws IOException {
    buffer.flip();
    channel.write(buffer);
    buffer.clear();
  }

  @Override
  public void newLine() throws IOException {
    writeString(NEW_LINE);
  }

  @Override
  public void write(InputStreamSource source) throws IOException {
    try (InputStream in = source.openInputStream()) {
      byte[] bytes = new byte[buffer.capacity()];
      while (true) {
        int space = buffer.capacity() - buffer.position();

        if (space == 0) {
          flush();
          continue;
        }

        int read = in.read(bytes, 0, space);

        if (read == -1) {
          break;
        }

        buffer.put(bytes, 0, read);
      }
    }
  }

  @Override
  public void writeString(String text) throws IOException {
    byte[] bytes = text.getBytes();
    int index = 0;
    int remaining = bytes.length;

    while (remaining > 0) {
      int space = buffer.capacity() - buffer.position();

      if (space == 0) {
        flush();
        continue;
      }

      int count = Math.min(space, remaining);
      buffer.put(bytes, index, count);
      index += count;
      remaining -= count;
    }
  }

}