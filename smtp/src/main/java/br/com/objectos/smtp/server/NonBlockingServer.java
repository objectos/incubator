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
import br.com.objectos.smtp.MailStore;
import br.com.objectos.smtp.Server;
import br.com.objectos.smtp.ServerBuilder;
import br.com.objectos.smtp.command.CommandFacade;
import br.com.objectos.smtp.command.CommandParser;
import br.com.objectos.smtp.command.CommandParserAdapter;
import br.com.objectos.smtp.mail.Charsets;
import br.com.objectos.smtp.mail.ReversePathFacade;
import br.com.objectos.smtp.mail.SimpleReversePathFacade;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import objectos.lang.Checks;
import objectos.lang.Event1;
import objectos.lang.Logger;

public final class NonBlockingServer implements Server {

  private static final Event1<Exception> SESSION_EXCEPTION = Event1.error();

  private final Lifecycle lifecycle;

  private final SocketChannelFactory listenSmtp;

  private NonBlockingServer(SocketChannelFactory listenSmtp, Lifecycle lifecycle) {
    this.listenSmtp = listenSmtp;
    this.lifecycle = lifecycle;
  }

  public static ServerBuilder builder() {
    return new Builder();
  }

  @Override
  public final void close() {
    lifecycle.close();
  }

  @Override
  public final void executeOne() {
    lifecycle.executeOne();
  }

  @Override
  public final String getHostName() {
    return listenSmtp.getHostName();
  }

  @Override
  public final int getPort() {
    return listenSmtp.getPort();
  }

  private static class Builder implements ServerBuilder {

    private SocketChannelFactory listenSmtp;
    private Logger logger;
    private MailStore mailStore;

    private Builder() {}

    @Override
    public final Server build() throws ConfigurationException {
      if (logger == null) {
        throw new ConfigurationException("logger was not defined");
      }

      if (listenSmtp == null) {
        throw new ConfigurationException("listenSmtp was not defined");
      }

      if (mailStore == null) {
        throw new ConfigurationException("mailStore was not defined");
      }

      LoggerAdapter loggerAdapter;
      loggerAdapter = new LoggerAdapter(logger);

      final Charset charset;
      charset = Charsets.ASCII;

      CharsetDecoder charsetDecoder;
      charsetDecoder = charset.newDecoder();

      CommandParser commandParser;
      commandParser = new CommandParser(loggerAdapter, charsetDecoder);

      ReversePathFacade reversePathFacade;
      reversePathFacade = new SimpleReversePathFacade();

      CommandFacade commandFacade;
      commandFacade = new CommandFacade(commandParser, reversePathFacade);

      final RequestFacade requestFacade;
      requestFacade = new RequestFacade(commandFacade);

      class ResponseFacadeBuilder extends ResponseFacade.Builder {
        @Override
        protected final Charset getCharset() {
          return charset;
        }

        @Override
        protected final String getHostName() {
          return listenSmtp.getHostName();
        }
      }

      ResponseFacade.Builder responseFacadeBuilder;
      responseFacadeBuilder = new ResponseFacadeBuilder();

      SessionProvider nonBlockingSessionProvider;
      nonBlockingSessionProvider = new NonBlockingSessionProvider(
        loggerAdapter, requestFacade, responseFacadeBuilder, mailStore);

      SessionProvider sessionProvider;
      sessionProvider = new SingletonSessionProvider(nonBlockingSessionProvider);

      Lifecycle portListener;
      portListener = NonBlockingLifecycle.create(logger, listenSmtp, sessionProvider);

      return new NonBlockingServer(listenSmtp, portListener);
    }

    @Override
    public final void setListenSmtp(SocketChannelFactory listen) {
      Checks.checkState(listenSmtp == null, "already set");

      listenSmtp = Checks.checkNotNull(listen, "listen == null");
    }

    @Override
    public final void setLogger(Logger logger) {
      Checks.checkState(this.logger == null, "already set");

      this.logger = Checks.checkNotNull(logger, "logger == null");
    }

    @Override
    public final void setMailStore(MailStore store) {
      Checks.checkState(mailStore == null, "already set");

      mailStore = Checks.checkNotNull(store, "store == null");
    }

  }

  private static class LoggerAdapter implements CommandParserAdapter, SessionLogger {

    private final Logger logger;

    public LoggerAdapter(Logger logger) {
      this.logger = logger;
    }

    @Override
    public final void logSessionException(Exception e, String message) {
      logger.log(SESSION_EXCEPTION, e);
    }

    @Override
    public final void onChannelRead(int totalRead, int byteBufferCapacity) {

    }

  }

  private static class SingletonSessionProvider implements SessionProvider {

    private final SessionProvider delegate;

    private Session session;

    public SingletonSessionProvider(SessionProvider delegate) {
      this.delegate = delegate;
    }

    @Override
    public final Session getSession() {
      if (session == null) {
        session = delegate.getSession();
      }

      return session;
    }

  }

}
