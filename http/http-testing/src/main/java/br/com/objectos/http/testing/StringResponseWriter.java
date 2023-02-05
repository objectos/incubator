/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.http.testing;

import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.server.Header;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.MessageBody;
import br.com.objectos.http.server.ResponseWriter;
import br.com.objectos.http.server.ResponseWriter.MessageBodyDsl;
import br.com.objectos.http.server.StatusCode;
import br.com.objectos.http.server.StringSocketWriter;
import br.com.objectos.http.server.WrittenResponse;
import java.io.IOException;
import objectos.lang.Check;
import objectos.util.GrowableList;

public class StringResponseWriter implements ResponseWriter, MessageBodyDsl {

  private MessageBody body = MessageBody.empty();
  private final GrowableList<Header> headerList = new GrowableList<>();
  private StatusCode status;
  private final StringSocketWriter writer = new StringSocketWriter();

  public StringResponseWriter() {}

  @Override
  public ResponseWriter contentLength(long size) {
    headerList.add(Header.contentLength(size));
    return this;
  }

  @Override
  public ResponseWriter contentType(MediaType contentType) {
    headerList.add(Header.contentType(contentType));
    return this;
  }

  @Override
  public ResponseWriter header(Header header) {
    headerList.add(header);
    return this;
  }

  @Override
  public MessageBodyDsl messageBody(InputStreamSource source) {
    body = MessageBody.ofByteSource(source);
    return this;
  }

  @Override
  public MessageBodyDsl messageBody(String text) {
    body = MessageBody.ofString(text);
    return this;
  }

  @Override
  public ResponseWriter say(StatusCode code) {
    Check.notNull(code, "code == null");
    return status(code);
  }

  public ResponseWriter sayBadRequest() {
    return status(StatusCode.INTERNAL_SERVER_ERROR);
  }

  public ResponseWriter sayError() {
    return status(StatusCode.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseWriter sayNotFound() {
    return status(StatusCode.NOT_FOUND);
  }

  @Override
  public ResponseWriter sayOk() {
    return status(StatusCode.OK);
  }

  @Override
  public String toString() {
    return writer.toString();
  }

  @Override
  public void write() throws HttpException {
    try {
      WrittenResponse response = new WrittenResponse(status, headerList.toUnmodifiableList(), body);
      response.respond(writer);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  private ResponseWriter status(StatusCode code) {
    status = code;
    return this;
  }

}