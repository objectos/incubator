/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.http.server.jetty;

import br.com.objectos.http.path.Router;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.HttpServerBuilder;
import br.com.objectos.http.server.ImmutableHttpServer;
import br.com.objectos.http.server.MutableHttpServer;
import java.net.InetSocketAddress;
import objectos.lang.Checks;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;

public class JettyHttpServerBuilder implements HttpServerBuilder {

  private String hostname = null;
  private int port = 80;

  @Override
  public final ImmutableHttpServer buildImmutable(HttpModule module) {
    return new ImmutableHttpServerImpl(
        getServer(
            new ImmutableRouterHandler(module)
        )
    );
  }

  @Override
  public final MutableHttpServer buildMutable() {
    MutableRouterHandler handler = new MutableRouterHandler(
        Router.empty()
    );
    return new MutableHttpServerImpl(
        getServer(handler),
        handler
    );
  }

  @Override
  public final MutableHttpServer buildMutable(HttpModule module) {
    MutableRouterHandler handler = new MutableRouterHandler(
        Router.of(module)
    );
    return new MutableHttpServerImpl(
        getServer(handler),
        handler
    );
  }

  public final JettyHttpServerBuilder hostname(String newHostname) {
    hostname = Checks.checkNotNull(newHostname, "newHostname == null");
    return this;
  }

  public final JettyHttpServerBuilder port(int newPort) {
    port = newPort;
    return this;
  }

  private Server getServer(Handler handler) {
    Server server;
    if (hostname != null) {
      server = new Server(new InetSocketAddress(hostname, port));
    } else {
      server = new Server(port);
    }
    server.setHandler(handler);
    return server;
  }

}
