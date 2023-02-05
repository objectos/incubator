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
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

@Concrete.Bridge
class SocketChannelFactoryJava6 extends AbstractSocketChannelFactory {

  @Concrete.Constructor
  SocketChannelFactoryJava6(InetSocketAddress address) {
    super(address);
  }

  @Override
  final ServerSocketChannel openServerSocketChannelImpl(
      InetSocketAddress address) throws IOException {
    ServerSocketChannel channel;
    channel = ServerSocketChannel.open();

    ServerSocket socket;
    socket = channel.socket();

    socket.bind(address);

    return channel;
  }

}
