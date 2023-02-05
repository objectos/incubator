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
package br.com.objectos.smtp;

import br.com.objectos.comuns.net.SocketChannelFactory;
import br.com.objectos.fs.Directory;
import br.com.objectos.smtp.client.NonBlockingClient;
import br.com.objectos.smtp.mail.FsMailStore;
import br.com.objectos.smtp.server.NonBlockingServer;
import objectos.lang.Check;
import objectos.lang.NoteSink;

public final class Smtp {

  private Smtp() {}

  public static Client client(
      ClientOption e1) throws ConfigurationException {
    Check.notNull(e1, "e1 == null");

    ClientBuilder b = NonBlockingClient.builder();

    e1.acceptClientBuilder(b);

    return b.build();
  }

  public static ServerOption listenSmtp(final SocketChannelFactory channelFactory) {
    Check.notNull(channelFactory, "channelFactory == null");

    return new ServerOption() {
      @Override
      public final void acceptServerBuilder(ServerBuilder builder) {
        builder.setListenSmtp(channelFactory);
      }
    };
  }

  public static ClientOrServerOption logger(final NoteSink logger) {
    Check.notNull(logger, "logger == null");

    return new ClientOrServerOption() {
      @Override
      public final void acceptClientBuilder(ClientBuilder builder) {
        builder.setLogger(logger);
      }

      @Override
      public final void acceptServerBuilder(ServerBuilder builder) {
        builder.setLogger(logger);
      }
    };
  }

  public static SocketChannelFactory loopback(int port) {
    return SocketChannelFactory.loopback(port);
  }

  public static MailStore newFileSystemMailStore(
      Directory directory) throws ConfigurationException {
    return FsMailStore.create(directory);
  }

  public static Server server(
      ServerOption e1,
      ServerOption e2) throws ConfigurationException {
    Check.notNull(e1, "e1 == null");
    Check.notNull(e2, "e2 == null");

    ServerBuilder b;
    b = NonBlockingServer.builder();

    e1.acceptServerBuilder(b);
    e2.acceptServerBuilder(b);

    return b.build();
  }

  public static Server server(
      ServerOption e1,
      ServerOption e2,
      ServerOption e3) throws ConfigurationException {
    Check.notNull(e1, "e1 == null");
    Check.notNull(e2, "e2 == null");
    Check.notNull(e3, "e3 == null");

    ServerBuilder b;
    b = NonBlockingServer.builder();

    e1.acceptServerBuilder(b);
    e2.acceptServerBuilder(b);
    e3.acceptServerBuilder(b);

    return b.build();
  }

}
