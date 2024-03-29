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

public class FakeRequestProto implements RequestProto {

  private final Method method;
  private final RequestedPath path;

  public FakeRequestProto(Method method, RequestedPath path) {
    this.method = method;
    this.path = path;
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
    return FakeRequestHeaders.empty();
  }

  @Override
  public RequestParameters parameters() {
    return FakeRequestParameters.empty();
  }

  @Override
  public String requestUrl() {
    throw new UnsupportedOperationException("Implement me");
  }

}