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

public class HttpRuntime implements CanToQualifiedPath {

  private final String hostname;
  private final int port;

  private HttpRuntime(String hostname, int port) {
    this.hostname = hostname;
    this.port = port;
  }

  static HttpRuntime of(int port) {
    return new HttpRuntime("localhost", port);
  }

  static HttpRuntime of(String hostname, int port) {
    return new HttpRuntime(hostname, port);
  }

  public int port() {
    return port;
  }

  @Override
  public String toQualifiedPath(String simplePath) {
    StringBuilder sb = new StringBuilder("http://").append(hostname);
    if (port != 80) {
      sb.append(':').append(port);
    }
    if (simplePath.isEmpty() || simplePath.charAt(0) != '/') {
      sb.append('/');
    }
    return sb.append(simplePath).toString();
  }

}