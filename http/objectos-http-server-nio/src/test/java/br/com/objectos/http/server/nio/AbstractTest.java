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
import br.com.objectos.io.Io;
import br.com.objectos.io.ReadableByteChannelCharIterator;
import br.com.objectos.io.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

abstract class AbstractTest {

  byte[] ascii(String str) {
    try {
      return str.getBytes("US-ASCII");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  ReadableByteChannel byteChannel(String str) {
    byte[] bytes = ascii(str);
    return new ByteArrayReadableByteChannel(bytes);
  }

  Channel newChannel() throws IOException {
    return Channel.get(new InetSocketAddress(8080));
  }

  byte[] readAllBytes(String resourceName) {
    try {
      Resource resource = Resource.getResource(resourceName);

      return Io.readByteArray(resource);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  void runInThread(Runnable runnable) {
    Thread thread = new Thread(runnable);
    thread.start();
  }

  SocketReader socketReaderOf(String resourceName, int bufferSize) {
    ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
    byte[] bytes = readAllBytes(resourceName);
    ReadableByteChannel channel = new ByteArrayReadableByteChannel(bytes);
    CharIterator iterator = new ReadableByteChannelCharIterator(channel, buffer, bufferSize);
    return new SocketReader(iterator);
  }

  SocketReader socketReaderWrap(String str) {
    ReadableByteChannel channel = byteChannel(str);
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    return SocketReader.of(channel, buffer);
  }

}