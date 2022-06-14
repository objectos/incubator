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

import static br.com.objectos.collections.Collections.newMutableList;
import static br.com.objectos.preconditions.Preconditions.checkNotNull;

import br.com.objectos.collections.MutableList;
import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.server.CanOpenSocketWriter;
import br.com.objectos.http.server.Code500InternalServerErrorException;
import br.com.objectos.http.server.Header;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.MessageBody;
import br.com.objectos.http.server.ResponseWriter;
import br.com.objectos.http.server.StatusCode;
import br.com.objectos.http.server.WrittenResponse;
import br.com.objectos.io.InputStreamSource;
import java.io.IOException;

class NioResponseWriter
    implements
    ResponseWriter,
    ResponseWriter.MessageBodyDsl {

  private MessageBody body = MessageBody.empty();

  private final MutableList<Header> headerList = new MutableList<>();
  private final CanOpenSocketWriter socket;
  private StatusCode status;

  public NioResponseWriter(CanOpenSocketWriter socket) {
    this.socket = socket;
  }

  @Override
  public NioResponseWriter contentLength(long size) {
    headerList.add(Header.contentLength(size));
    return this;
  }

  @Override
  public NioResponseWriter contentType(MediaType contentType) {
    headerList.add(Header.contentType(contentType));
    return this;
  }

  @Override
  public ResponseWriter header(Header header) {
    headerList.add(header);
    return this;
  }

  @Override
  public NioResponseWriter messageBody(InputStreamSource source) {
    body = MessageBody.ofByteSource(source);
    return this;
  }

  @Override
  public NioResponseWriter messageBody(String text) {
    body = MessageBody.ofString(text);
    return this;
  }

  @Override
  public ResponseWriter say(StatusCode code) {
    checkNotNull(code, "code == null");
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
  public void write() throws HttpException {
    try {
      write0().respond(socket);
    } catch (IOException e) {
      throw new Code500InternalServerErrorException(e);
    }
  }

  private ResponseWriter status(StatusCode code) {
    status = code;
    return this;
  }

  private WrittenResponse write0() {
    return new WrittenResponse(status, headerList.toImmutableList(), body);
  }

}