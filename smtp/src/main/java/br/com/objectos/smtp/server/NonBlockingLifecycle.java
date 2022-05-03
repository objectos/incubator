/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.smtp.server;

import br.com.objectos.comuns.net.SocketChannelFactory;
import br.com.objectos.smtp.ConfigurationException;
import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import objectos.logging.Event1;
import objectos.logging.Events;
import objectos.logging.Logger;

public final class NonBlockingLifecycle implements Lifecycle {

  private static final Event1<IOException> CLOSE_FAILED;

  private static final Event1<Exception> UNEXPECTED_EXECUTION_EXCEPTION;

  static {
    Class<?> s;
    s = NonBlockingLifecycle.class;

    CLOSE_FAILED = Events.error(
        s, "CLOSE_FAILED", IOException.class);

    UNEXPECTED_EXECUTION_EXCEPTION = Events.error(
        s, "UNEXPECTED_EXECUTION_EXCEPTION", Exception.class);
  }

  private final Logger logger;

  private final ServerSocketChannel serverSocketChannel;

  private final SessionProvider sessionProvider;

  private final Session[] sessions = new Session[10];

  private int sessionsSize;

  private NonBlockingLifecycle(Logger logger,
                               ServerSocketChannel serverSocketChannel,
                               SessionProvider sessionProvider) {
    this.logger = logger;
    this.serverSocketChannel = serverSocketChannel;
    this.sessionProvider = sessionProvider;
  }

  public static NonBlockingLifecycle create(
      Logger logger,
      SocketChannelFactory element,
      SessionProvider sessionProvider) throws ConfigurationException {
    try {
      ServerSocketChannel socketChannel;
      socketChannel = element.openServerSocketChannel();

      socketChannel.configureBlocking(false);

      return new NonBlockingLifecycle(logger, socketChannel, sessionProvider);
    } catch (IOException e) {
      throw new ConfigurationException("Failed to open ServerSocketChannel", e);
    }
  }

  @Override
  public final void close() {
    close(serverSocketChannel);
  }

  @Override
  public final void executeOne() {
    try {
      executeActiveSessions();
      executeAcceptConnection();
    } catch (Exception e) {
      logger.log(UNEXPECTED_EXECUTION_EXCEPTION, e);
      close();
    }
  }

  private void addSession(Session session) {
    for (int i = 0; i < sessions.length; i++) {
      if (sessions[i] == null) {
        sessions[i] = session;
        sessionsSize++;
        break;
      }
    }
  }

  private void close(Closeable c) {
    try {
      c.close();
    } catch (IOException e) {
      logger.log(CLOSE_FAILED, e);
    }
  }

  private void executeAcceptConnection() {
    if (sessionsSize < sessions.length) {
      executeAcceptConnection0();
    }
  }

  private void executeAcceptConnection0() {
    SocketChannel clientChannel;

    try {
      clientChannel = serverSocketChannel.accept();
    } catch (IOException e) {
      throw new UnsupportedOperationException("Implement me", e);
    }

    if (clientChannel == null) {
      return;
    }

    try {
      clientChannel.configureBlocking(false);
    } catch (IOException e) {
      throw new UnsupportedOperationException("Implement me", e);
    }

    Session session;
    session = sessionProvider.getSession();

    session.startSession(clientChannel);

    addSession(session);

    session.execute();
  }

  private void executeActiveSessions() {
    for (int i = 0; i < sessions.length; i++) {
      Session session;
      session = sessions[i];

      if (session == null) {
        continue;
      }

      if (!session.isActive()) {
        sessions[i] = null;
        sessionsSize--;
      }

      session.execute();
    }
  }

}
