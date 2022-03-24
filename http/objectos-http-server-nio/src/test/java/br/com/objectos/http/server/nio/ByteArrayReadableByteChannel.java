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
import java.nio.channels.ReadableByteChannel;

class ByteArrayReadableByteChannel implements ReadableByteChannel {

  private final byte[] bytes;
  private int offset;

  public ByteArrayReadableByteChannel(byte[] bytes) {
    this.bytes = bytes;
  }

  @Override
  public int read(ByteBuffer dst) throws IOException {
    int capacity = dst.capacity();
    int read = Math.min(capacity, bytes.length - offset);
    int max = offset + read;
    for (int i = offset; i < max; i++) {
      dst.put(bytes[i]);
    }
    offset += read;
    return read;
  }

  // noops...

  @Override
  public boolean isOpen() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void close() throws IOException {
    throw new IOException();
  }

}