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
package br.com.objectos.http.server.jetty;

import br.com.objectos.http.server.Method;
import br.com.objectos.http.server.RequestHeaders;
import br.com.objectos.http.server.RequestParameters;
import br.com.objectos.http.server.RequestProto;
import br.com.objectos.http.server.RequestedPart;
import br.com.objectos.http.server.RequestedPath;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import objectos.util.GrowableMap;

class JettyRequestProto implements RequestProto {

  private final Method method;
  private final HttpServletRequest request;
  private final RequestedPath requestedPath;

  private JettyRequestProto(HttpServletRequest request,
                            Method method,
                            RequestedPath requestedPath) {
    this.request = request;
    this.method = method;
    this.requestedPath = requestedPath;
  }

  public static JettyRequestProto of(HttpServletRequest request) {
    return new JettyRequestProto(
      request,
      Method.get(request.getMethod()),
      new RequestedPath(request.getPathInfo()));
  }

  @Override
  public final boolean hasNextRequestedPart() {
    return requestedPath.hasNext();
  }

  @Override
  public final RequestHeaders headers() {
    return new JettyRequestHeaders(request);
  }

  @Override
  public final boolean matches(Method method) {
    return this.method.equals(method);
  }

  @Override
  public final RequestedPart nextRequestedPart() {
    return requestedPath.next();
  }

  @Override
  public final RequestParameters parameters() {
    GrowableMap<String, String> map;
    map = new GrowableMap<>();

    Enumeration<String> parameterNames;
    parameterNames = request.getParameterNames();

    while (parameterNames.hasMoreElements()) {
      String name = parameterNames.nextElement();

      String value = request.getParameter(name);

      map.put(name, value);
    }

    return new JettyParamMap(map.toUnmodifiableMap());
  }

  @Override
  public final String requestUrl() {
    return request.getRequestURL().toString();
  }

}