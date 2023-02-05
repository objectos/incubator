/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.comuns.net;

import br.com.objectos.latest.Concrete;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

@Concrete(modifiers = "public", simpleName = "SocketChannelFactory")
abstract class AbstractSocketChannelFactory {

  private final InetSocketAddress address;

  AbstractSocketChannelFactory(InetSocketAddress address) {
    this.address = address;
  }

  public static SocketChannelFactory loopback(int port) {
    InetAddress localhost;
    localhost = InetAddresses.getLoopbackAddress();

    InetSocketAddress address;
    address = new InetSocketAddress(localhost, port);

    return new SocketChannelFactory(address);
  }

  public final SocketChannel connectSocketChannel() throws IOException {
    SocketChannel socketChannel;
    socketChannel = SocketChannel.open(address);

    return socketChannel;
  }

  public final String getHostName() {
    return address.getHostName();
  }

  public final int getPort() {
    return address.getPort();
  }

  public final ServerSocketChannel openServerSocketChannel() throws IOException {
    ServerSocketChannel socketChannel;
    socketChannel = openServerSocketChannelImpl(address);

    return socketChannel;
  }

  abstract ServerSocketChannel openServerSocketChannelImpl(
      InetSocketAddress address) throws IOException;

}
