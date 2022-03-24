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

import br.com.objectos.http.path.Router;
import br.com.objectos.http.server.Code400BadRequestException;
import br.com.objectos.http.server.Code500InternalServerErrorException;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.Method;
import br.com.objectos.http.server.RequestHeaders;
import br.com.objectos.http.server.RequestParameters;
import br.com.objectos.http.server.RequestProto;
import br.com.objectos.http.server.RequestedPart;
import br.com.objectos.http.server.RequestedPath;
import br.com.objectos.http.server.Resolution;

abstract class SocketRequest {

  SocketRequest() {
  }

  static SocketRequest parse(Socket socket) {
    try {
      SocketReader reader = socket.openReader();
      Thread.sleep(10); // TODO implementar Content-Length...
      Method method = parseMethod(reader);
      RequestedPath requestedPath = new RequestedPath(reader.readString());
      NioRequestHeaders headers = NioRequestHeaders.parse(reader);
      NioRequestParameters parameters = new NioRequestParameters(reader);
      return new ResolvedSocketRequest(
          method,
          requestedPath,
          headers,
          parameters);
    } catch (Code400BadRequestException e) {
      return new ExceptionSocketRequest(e);
    } catch (InterruptedException e) {
      return new ExceptionSocketRequest(new Code500InternalServerErrorException(e));
    }
  }

  static Method parseMethod(SocketReader reader) throws Code400BadRequestException {
    String name = reader.readString();

    if (!Method.containsName(name)) {
      throw Code400BadRequestException.invalidMethod(name);
    }

    return Method.get(name);
  }

  public abstract Resolution resolve(Router registry);

  private static class ExceptionSocketRequest extends SocketRequest {

    private final HttpException exception;

    public ExceptionSocketRequest(HttpException exception) {
      this.exception = exception;
    }

    @Override
    public Resolution resolve(Router registry) {
      return Resolution.httpException(exception);
    }

  }

  private static class ResolvedSocketRequest
      extends SocketRequest
      implements RequestProto {

    private final Method method;
    private final RequestedPath path;
    private final RequestHeaders headers;
    private final RequestParameters parameters;

    ResolvedSocketRequest(Method method,
                          RequestedPath path,
                          RequestHeaders headers,
                          RequestParameters parameters) {
      this.method = method;
      this.path = path;
      this.headers = headers;
      this.parameters = parameters;
    }

    @Override
    public Resolution resolve(Router registry) {
      return registry.resolve(this);
    }

    @Override
    public String toString() {
      return String.format("ResolvedSocketRequest{method=%s, path=%s}", method, path);
    }

    @Override
    public boolean hasNextRequestedPart() {
      return path.hasNext();
    }

    @Override
    public RequestedPart nextRequestedPart() {
      return path.next();
    }

    @Override
    public boolean matches(Method method) {
      return this.method.equals(method);
    }

    @Override
    public RequestHeaders headers() {
      return headers;
    }

    @Override
    public RequestParameters parameters() {
      return parameters;
    }

    @Override
    public String requestUrl() {
      throw new UnsupportedOperationException("Implement me");
    }

  }

}