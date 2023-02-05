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
package br.com.objectos.http.server.jetty;

import br.com.objectos.http.server.RequestHeaders;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;
import objectos.util.UnmodifiableMap;
import objectos.util.GrowableMap;

class JettyRequestHeaders implements RequestHeaders {

  private UnmodifiableMap<String, String> map;

  private final HttpServletRequest request;

  JettyRequestHeaders(HttpServletRequest request) {
    this.request = request;
  }

  @Override
  public boolean contains(String header) {
    init();
    return map.containsKey(header);
  }

  @Override
  public String get(String header) {
    init();
    if (!contains(header)) {
      throw new NoSuchElementException(header);
    } else {
      return map.get(header);
    }
  }

  @Override
  public String getOrDefault(String header, String defaultValue) {
    init();

    String value;
    value = map.get(header);

    if (value == null) {
      value = defaultValue;
    }

    return value;
  }

  private void init() {
    if (map == null) {
      synchronized (this) {
        if (map == null) {
          map = initMap();
        }
      }
    }
  }

  private UnmodifiableMap<String, String> initMap() {
    GrowableMap<String, String> map = new GrowableMap<>();

    Enumeration<String> names = request.getHeaderNames();
    while (names.hasMoreElements()) {
      String name = names.nextElement();
      StringBuilder sb = new StringBuilder();
      Enumeration<String> headers = request.getHeaders(name);
      if (headers.hasMoreElements()) {
        sb.append(headers.nextElement());
        while (headers.hasMoreElements()) {
          sb.append(", ");
          sb.append(headers.nextElement());
        }
      }
      map.put(name, sb.toString());
    }

    return map.toUnmodifiableMap();
  }

}