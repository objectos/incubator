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
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

class Channel implements AutoCloseable {

  private final ServerSocketChannel channel;
  private final Selector selector;

  private Channel(ServerSocketChannel channel, Selector selector) {
    this.channel = channel;
    this.selector = selector;
  }

  public static Channel get(InetSocketAddress address) throws IOException {
    ServerSocketChannel channel = ServerSocketChannel.open();
    channel.bind(address);
    channel.configureBlocking(false);

    Selector selector = Selector.open();
    channel.register(selector, SelectionKey.OP_ACCEPT);

    return new Channel(channel, selector);
  }

  public Socket accept(ByteBuffer buffer) throws IOException {
    Socket selected = NoopSocket.instance();

    int select = selector.select();
    if (select != 0) {
      selected = select(buffer);
    }

    return selected;
  }

  @Override
  public void close() throws IOException {
    try {
      channel.close();
    } finally {
      selector.close();
    }
  }

  private Socket select(ByteBuffer buffer) throws IOException {
    Socket selected = NoopSocket.instance();

    Set<SelectionKey> selectedKeys = selector.selectedKeys();
    Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
    while (keyIterator.hasNext()) {
      SelectionKey selectedKey = keyIterator.next();
      keyIterator.remove();

      if (!selectedKey.isValid()) {
        continue;
      }

      if (selectedKey.isAcceptable()) {
        SocketChannel accepted = channel.accept();
        selected = HttpSocket.of(accepted, buffer);
        break;
      }
    }

    return selected;
  }

}