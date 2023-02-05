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
package br.com.objectos.http.server;

import java.util.Objects;

public abstract class Resolution {

  private static final Resolution BAD_REQUEST = new BadRequest();
  private static final Resolution NOT_FOUND = new NotFound();

  Resolution() {
  }

  public static Resolution badRequest() {
    return BAD_REQUEST;
  }

  public static Resolution httpException(HttpException exception) {
    throw new UnsupportedOperationException("Implement me!");
  }

  public static Resolution notFound() {
    return NOT_FOUND;
  }

  public static Resolution resolved(HttpAction action, Request request) {
    return new Resolved(action, request);
  }

  public abstract Response execute() throws HttpException;

  private static class BadRequest extends Resolution {
    @Override
    public Response execute() {
      return StatusCode.BAD_REQUEST.response();
    }
  }

  private static class NotFound extends Resolution {
    @Override
    public Response execute() {
      return StatusCode.NOT_FOUND.response();
    }
  }

  private static class Resolved extends Resolution {

    private final HttpAction action;
    private final Request request;

    public Resolved(HttpAction action, Request request) {
      this.action = action;
      this.request = request;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (!(obj instanceof Resolved)) {
        return false;
      }
      Resolved that = (Resolved) obj;
      return Objects.equals(action, that.action)
          && Objects.equals(request, that.request);
    }

    @Override
    public Response execute() throws HttpException {
      return action.execute(request);
    }

  }

}