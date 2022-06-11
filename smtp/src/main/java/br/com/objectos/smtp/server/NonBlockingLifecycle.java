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
import objectos.lang.Note1;
import objectos.lang.NoteSink;

public final class NonBlockingLifecycle implements Lifecycle {

  private static final Note1<IOException> CLOSE_FAILED = Note1.error();

  private static final Note1<Exception> UNEXPECTED_EXECUTION_EXCEPTION = Note1.error();

  private final NoteSink logger;

  private final ServerSocketChannel serverSocketChannel;

  private final SessionProvider sessionProvider;

  private final Session[] sessions = new Session[10];

  private int sessionsSize;

  private NonBlockingLifecycle(NoteSink logger,
                               ServerSocketChannel serverSocketChannel,
                               SessionProvider sessionProvider) {
    this.logger = logger;
    this.serverSocketChannel = serverSocketChannel;
    this.sessionProvider = sessionProvider;
  }

  public static NonBlockingLifecycle create(
      NoteSink logger,
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
      logger.send(UNEXPECTED_EXECUTION_EXCEPTION, e);
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
      logger.send(CLOSE_FAILED, e);
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
