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
package br.com.objectos.http.server;

import java.io.IOException;
import java.util.Objects;
import objectos.util.ImmutableList;

public class WrittenResponse {

  private final MessageBody body;
  private final ImmutableList<Header> headerList;
  private final StatusCode status;

  public WrittenResponse(StatusCode status, ImmutableList<Header> headerList, MessageBody body) {
    this.status = status;
    this.headerList = headerList;
    this.body = body;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof WrittenResponse)) {
      return false;
    }
    WrittenResponse that = (WrittenResponse) obj;
    return Objects.equals(status, that.status)
        && Objects.equals(headerList, that.headerList)
        && Objects.equals(body, that.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, headerList, body);
  }

  public void respond(CanOpenSocketWriter socket) throws IOException {
    SocketWriter writer = socket.openWriter();
    respond(writer);
    writer.flush();
  }

  public void respond(SocketWriter writer) throws IOException {
    writer.writeString("HTTP/1.1");
    writer.writeString(" ");
    status.writeTo(writer);

    for (Header header : headerList) {
      writer.newLine();
      header.writeTo(writer);
    }

    body.writeTo(writer);
  }

}