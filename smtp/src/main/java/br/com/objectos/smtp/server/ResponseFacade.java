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
package br.com.objectos.smtp.server;

import br.com.objectos.smtp.command.CommandVisitor;
import br.com.objectos.smtp.mail.ClientName;
import br.com.objectos.smtp.mail.ForwardPath;
import br.com.objectos.smtp.mail.ReversePath;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class ResponseFacade implements CommandVisitor {

  private WritableByteChannel client;
  private final OpeningMessage openingMessage;

  private final ResponseBuffer responseBuffer;

  ResponseFacade(OpeningMessage openingMessage, ResponseBuffer responseBuffer) {
    this.openingMessage = openingMessage;
    this.responseBuffer = responseBuffer;
  }

  public final void executeOpeningMessage() throws IOException {
    openingMessage.execute(client);
  }

  public final void executeProcessDataComplete() {
    responseBuffer.addLine("250 2.0.0 OK");
  }

  public final void startSession(WritableByteChannel client) {
    this.client = client;

    responseBuffer.reset();
  }

  @Override
  public final void visitDATA() {
    responseBuffer.addLine("354 End data with <CR><LF>.<CR><LF>");
  }

  @Override
  public final void visitEHLO(ClientName client) {
    responseBuffer.addLine("250-localhost");
    responseBuffer.addLine("250 ENHANCEDSTATUSCODES");
  }

  @Override
  public final void visitMAIL(ReversePath reversePath) {
    responseBuffer.addLine("250 2.1.0 OK");
  }

  @Override
  public final void visitQUIT() {
    responseBuffer.addLine("221 2.0.0 OK");
  }

  @Override
  public final void visitRCPT(ForwardPath forwardPath) {
    responseBuffer.addLine("250 2.1.5 OK");
  }

  public final boolean write() throws IOException {
    try {
      return responseBuffer.write(client);
    } finally {
      responseBuffer.reset();
    }
  }

  public abstract static class Builder {

    protected Builder() {}

    public final ResponseFacade build() {
      String hostName;
      hostName = getHostName();

      Charset charset;
      charset = getCharset();

      final OpeningMessage openingMessage;
      openingMessage = OpeningMessage.create(hostName, charset);

      CharsetEncoder charsetEncoder;
      charsetEncoder = charset.newEncoder();

      final ResponseBuffer responseBuffer;
      responseBuffer = new ResponseBuffer(charsetEncoder);

      return new ResponseFacade(openingMessage, responseBuffer);
    }

    protected abstract Charset getCharset();

    protected abstract String getHostName();

  }

}