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
package br.com.objectos.smtp.client;

import br.com.objectos.comuns.net.SocketChannelFactory;
import br.com.objectos.core.object.Checks;
import br.com.objectos.smtp.Client;
import br.com.objectos.smtp.ClientBuilder;
import br.com.objectos.smtp.ClientSession;
import br.com.objectos.smtp.ConfigurationException;
import br.com.objectos.smtp.mail.Charsets;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import objectos.logging.Event1;
import objectos.logging.Logger;

public class NonBlockingClient implements Client {

  private static final Event1<IOException> CLOSE_FAILED = Event1.error();

  private final Logger logger;

  private final ThisClientSession[] sessions = new ThisClientSession[2];

  private int sessionsSize;

  private NonBlockingClient(Logger logger) {
    this.logger = logger;
  }

  public static ClientBuilder builder() {
    return new Builder();
  }

  @Override
  public final void close() {
    for (int i = 0; i < sessions.length; i++) {
      ThisClientSession session;
      session = sessions[i];

      if (session == null) {
        continue;
      }

      session.close();
    }
  }

  @Override
  public final ClientSession connect(SocketChannelFactory serverAddress) throws IOException {
    Checks.checkNotNull(serverAddress, "serverAddress == null");

    if (sessionsSize > sessions.length) {
      throw new AssertionError("Unexpected... investigate");
    }

    if (sessionsSize == sessions.length) {
      throw new UnsupportedOperationException("Implement me");
    }

    SocketChannel server;
    server = serverAddress.connectSocketChannel();

    server.configureBlocking(false);

    Charset ascii;
    ascii = Charsets.ASCII;

    ThisClientSession session;
    session = new ThisClientSession(server, ascii);

    addSession(session);

    return session;
  }

  @Override
  public final void executeOne() {
    for (int i = 0; i < sessions.length; i++) {
      ThisClientSession session;
      session = sessions[i];

      if (session == null) {
        continue;
      }

      if (!session.isOpen()) {
        sessions[i] = null;
        sessionsSize--;
        continue;
      }

      session.executeOne();
    }
  }

  private void addSession(ThisClientSession session) {
    synchronized (sessions) {
      addSession0(session);
    }
  }

  private void addSession0(ThisClientSession session) {
    for (int i = 0; i < sessions.length; i++) {
      if (sessions[i] == null) {
        sessions[i] = session;
        sessionsSize++;
        break;
      }
    }
  }

  private static class Builder implements ClientBuilder {

    private Logger logger;

    @Override
    public final Client build() throws ConfigurationException {
      Logger theLogger = logger;

      if (theLogger == null) {
        throw new ConfigurationException("logger was not set");
      }

      return new NonBlockingClient(theLogger);
    }

    @Override
    public final void setLogger(Logger logger) {
      Checks.checkState(this.logger == null, "logger was already set");

      this.logger = Checks.checkNotNull(logger, "logger == null");
    }

  }

  private class ThisClientSession implements ClientSession {

    private final char[] charArray;

    private int charIndex;

    private final CharsetDecoder decoder;

    private final CharsetEncoder encoder;

    private String lastResponse;

    private final ByteBuffer readable = ByteBuffer.allocateDirect(512);

    private final CharBuffer readableCharBuffer = CharBuffer.allocate(2048);

    private final SocketChannel server;

    private final ByteBuffer writable = ByteBuffer.allocateDirect(512);

    private final CharBuffer writableCharBuffer = CharBuffer.allocate(512);

    public ThisClientSession(SocketChannel server, Charset charset) {
      this.server = server;

      charArray = readableCharBuffer.array();

      decoder = charset.newDecoder();

      decoder.reset();

      encoder = charset.newEncoder();
    }

    @Override
    public final void buffer(String data) {
      encoder.reset();

      writableCharBuffer.clear();

      writableCharBuffer.put(data);

      writableCharBuffer.flip();

      encoder.encode(writableCharBuffer, writable, true);

      encoder.flush(writable);
    }

    @Override
    public final void bufferLine(String string) {
      encoder.reset();

      writableCharBuffer.clear();

      writableCharBuffer.put(string);
      writableCharBuffer.put("\r\n");

      writableCharBuffer.flip();

      encoder.encode(writableCharBuffer, writable, true);

      encoder.flush(writable);
    }

    @Override
    public final void close() {
      try {
        server.close();
      } catch (IOException e) {
        logger.log(CLOSE_FAILED, e);
      }
    }

    @Override
    public final boolean hasResponse() {
      return lastResponse != null;
    }

    @Override
    public final boolean hasResponseCode(int code) {
      if (lastResponse == null) {
        return false;
      }

      String prefix;
      prefix = Integer.toString(code);

      return lastResponse.startsWith(prefix);
    }

    final void executeOne() {
      try {
        writeOne();
        readOne();
      } catch (IOException e) {
        throw new UnsupportedOperationException("Implement me");
      }
    }

    final boolean isOpen() {
      return server.isOpen();
    }

    private void readOne() throws IOException {
      readable.clear();

      lastResponse = null;

      int count;
      count = server.read(readable);

      if (count <= 0) {
        return;
      }

      readable.flip();

      CoderResult result;
      result = decoder.decode(readable, readableCharBuffer, false);

      if (result.isError()) {
        result.throwException();
      }

      int length;
      length = readableCharBuffer.position() - charIndex;

      lastResponse = new String(charArray, charIndex, length);

      charIndex += length;
    }

    private void writeOne() throws IOException {
      writable.flip();

      server.write(writable);

      writable.compact();
    }

  }

}
