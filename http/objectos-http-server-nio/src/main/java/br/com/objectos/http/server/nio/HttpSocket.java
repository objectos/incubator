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

import br.com.objectos.io.CharIterator;
import br.com.objectos.io.ReadableByteChannelCharIterator;
import br.com.objectos.http.server.SocketWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

class HttpSocket extends Socket {

  private final SocketChannel channel;
  private final ByteBuffer buffer;

  private HttpSocket(SocketChannel channel, ByteBuffer buffer) {
    this.channel = channel;
    this.buffer = buffer;
  }

  public static HttpSocket of(SocketChannel channel, ByteBuffer buffer) {
    return new HttpSocket(channel, buffer);
  }

  @Override
  public void close() throws IOException {
    channel.close();
  }

  @Override
  public SocketReader openReader() {
    CharIterator iterator = new ReadableByteChannelCharIterator(channel, buffer, buffer.capacity());
    return new SocketReader(iterator);
  }

  @Override
  public SocketWriter openWriter() {
    return WritableByteChannelSocketWriter.of(channel, buffer);
  }

  @Override
  public SocketRequest parse() {
    return SocketRequest.parse(this);
  }

}