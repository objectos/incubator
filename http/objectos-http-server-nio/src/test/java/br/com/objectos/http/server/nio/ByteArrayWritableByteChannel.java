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

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

class ByteArrayWritableByteChannel implements WritableByteChannel {

  private final byte[] bytes = new byte[1024];
  private int index;

  @Override
  public int write(ByteBuffer src) throws IOException {
    int remaining = src.remaining();
    src.get(bytes, index, remaining);
    index += remaining;
    return remaining;
  }

  @Override
  public boolean isOpen() {
    return true;
  }

  @Override
  public void close() throws IOException {
  }

  @Override
  public String toString() {
    byte[] copy = new byte[index];
    System.arraycopy(bytes, 0, copy, 0, index);
    return new String(copy);
  }

}