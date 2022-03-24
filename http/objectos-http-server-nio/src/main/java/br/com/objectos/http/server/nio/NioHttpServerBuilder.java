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

import br.com.objectos.http.path.Router;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.HttpServerBuilder;
import br.com.objectos.http.server.ImmutableHttpServer;
import br.com.objectos.http.server.MutableHttpServer;
import java.io.IOException;
import java.io.UncheckedIOException;

public class NioHttpServerBuilder implements HttpServerBuilder {

  private final String hostname = null;
  private int port = 80;

  public final NioHttpServerBuilder port(int newPort) {
    port = newPort;
    return this;
  }

  @Override
  public final ImmutableHttpServer buildImmutable(HttpModule module) {
    try {
      return new ImmutableHttpServerImpl(
          newChannel(),
          module
      );
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public final MutableHttpServer buildMutable() {
    try {
      return new MutableHttpServerImpl(
          newChannel(),
          Router.empty()
      );
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public final MutableHttpServer buildMutable(HttpModule module) {
    try {
      return new MutableHttpServerImpl(
          newChannel(),
          Router.of(module)
      );
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private Channel newChannel() throws IOException {
    if (hostname != null) {
      return ChannelBuilder.hostname(hostname, port).build();
    } else {
      return ChannelBuilder.localhost(port).build();
    }
  }

}
