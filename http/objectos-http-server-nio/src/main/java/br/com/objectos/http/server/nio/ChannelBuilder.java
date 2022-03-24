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

import static br.com.objectos.preconditions.Preconditions.checkNotNull;

import java.io.IOException;
import java.net.InetSocketAddress;

abstract class ChannelBuilder {

  private static class Hostname extends ChannelBuilder {
    private final String hostname;

    public Hostname(String hostname, int port) {
      super(port);
      this.hostname = hostname;
    }

    @Override
    InetSocketAddress address() {
      return new InetSocketAddress(hostname, port);
    }

    @Override
    String hostname() {
      return hostname;
    }
  }

  private static class Localhost extends ChannelBuilder {
    Localhost(int port) {
      super(port);
    }

    @Override
    InetSocketAddress address() {
      return new InetSocketAddress(port);
    }

    @Override
    String hostname() {
      return "localhost";
    }
  }

  final int port;

  ChannelBuilder(int port) {
    this.port = port;
  }

  public static ChannelBuilder hostname(String hostname, int port) {
    checkNotNull(hostname, "hostname == null");
    return new Hostname(hostname, port);
  }

  public static ChannelBuilder localhost(int port) {
    return new Localhost(port);
  }

  public final Channel build() throws IOException {
    return Channel.get(address());
  }

  public final String intraUrl() {
    String res = "http://" + address().getAddress().getHostAddress();

    if (port != 80) {
      res = res + ":" + port;
    }

    return res;
  }

  @Override
  public final String toString() {
    String res = "http://" + hostname();

    if (port != 80) {
      res = res + ":" + port;
    }

    return res;
  }

  abstract InetSocketAddress address();

  abstract String hostname();

}