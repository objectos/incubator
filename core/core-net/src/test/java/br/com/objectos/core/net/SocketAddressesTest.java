/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package br.com.objectos.core.net;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import org.testng.annotations.Test;

public class SocketAddressesTest {

  @Test
  public void test() throws IOException {
    InetAddress loopback;
    loopback = InetAddresses.getLoopbackAddress();

    InetSocketAddress socketAddress;
    socketAddress = new InetSocketAddress(loopback, 4001);

    ServerSocketChannel server;
    server = SocketAddresses.openServerSocketChannel(socketAddress);

    assertTrue(server.isBlocking());

    server.configureBlocking(false);

    SocketChannel client;
    client = SocketChannel.open(socketAddress);

    assertTrue(client.isBlocking());

    client.configureBlocking(false);

    try {
      SocketChannel reading;
      reading = server.accept();

      reading.configureBlocking(false);

      String helloWord;
      helloWord = "Hello world!";

      byte[] helloWorldBytes;
      helloWorldBytes = helloWord.getBytes();

      ByteBuffer writeBuffer;
      writeBuffer = ByteBuffer.wrap(helloWorldBytes);

      client.write(writeBuffer);

      ByteBuffer readingBuffer;
      readingBuffer = ByteBuffer.allocate(helloWorldBytes.length);

      int read;
      read = reading.read(readingBuffer);

      assertEquals(read, helloWorldBytes.length);

      String result;
      result = new String(readingBuffer.array());

      assertEquals(result, helloWord);
    } finally {
      close(client);
      close(server);
    }
  }

  private void close(Closeable c) {
    try {
      c.close();
    } catch (IOException e) {
    }
  }

}
