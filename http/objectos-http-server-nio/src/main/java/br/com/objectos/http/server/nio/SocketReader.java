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
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

class SocketReader implements AutoCloseable {

  private final CharIterator iterator;

  public SocketReader(CharIterator iterator) {
    this.iterator = iterator;
  }

  public static SocketReader of(ReadableByteChannel channel, ByteBuffer buffer) {
    buffer.clear();
    CharIterator iterator = new ReadableByteChannelCharIterator(channel, buffer, 8192);
    return new SocketReader(iterator);
  }

  @Override
  public void close() throws IOException {
    iterator.close();
  }

  public String readAll() {
    StringBuilder builder = new StringBuilder();
    while (iterator.hasNext()) {
      builder.append(iterator.next());
    }
    return builder.toString();
  }

  public String readLine() {
    StringBuilder builder = new StringBuilder();
    outer: while (iterator.hasNext()) {
      char c = iterator.next();
      switch (c) {
      case '\n':
        break outer;
      default:
        builder.append(c);
        break;
      }
    }
    return builder.toString().trim();
  }

  public String readString() {
    StringBuilder builder = new StringBuilder();

    while (iterator.hasNext()) {
      char c = iterator.next();
      if (Character.isWhitespace(c)) {
        break;
      }
      builder.append(c);
    }

    return builder.toString();
  }

}