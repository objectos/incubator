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

public enum StatusCode {

  OK(200, "200 OK"),
  
  SEE_OTHER(303, "303 See Other"),

  BAD_REQUEST(400, "400 Bad Request"),

  NOT_FOUND(404, "404 Not Found"),

  INTERNAL_SERVER_ERROR(500, "500 Internal Server Error");

  private final int code;
  private final String value;

  private StatusCode(int code, String value) {
    this.code = code;
    this.value = value;
  }

  public int intValue() {
    return code;
  }

  public Response response() {
    return new ThisResponse(this);
  }

  void writeTo(SocketWriter writer) throws IOException {
    writer.writeString(value);
  }

  private static class ThisResponse implements Response {

    private final StatusCode code;

    public ThisResponse(StatusCode code) {
      this.code = code;
    }

    @Override
    public void writeTo(ResponseWriter writer) throws HttpException {
      writer.say(code).write();
    }

  }

}