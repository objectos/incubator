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

import br.com.objectos.http.path.Argument;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Request {

  private final List<Argument> argumentList;
  private final RequestProto proto;
  private final RequestParameters parameters;
  private final RequestHeaders headers;

  public Request(List<Argument> argumentList, RequestProto proto) {
    this.argumentList = argumentList;
    this.proto = proto;
    this.parameters = proto.parameters();
    this.headers = proto.headers();
  }

  public final RequestHeaders headers() {
    return headers;
  }

  public final int getInt(int index) {
    return arg(index).getInt();
  }

  public final String getString(int index) {
    return arg(index).getString();
  }

  public final String param(String key) {
    return parameters.get(key);
  }

  public final boolean paramBoolean(String key) {
    return parameters.getBoolean(key);
  }

  public final int paramInt(String key) {
    return parameters.getInt(key);
  }
  
  public final String requestUrl() {
    return proto.requestUrl();
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Request)) {
      return false;
    }
    Request that = (Request) obj;
    return Objects.equals(argumentList, that.argumentList)
        && Objects.equals(parameters, that.parameters);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(argumentList, parameters);
  }

  private Argument arg(int index) {
    if (index == argumentList.size()) {
      throw new NoSuchElementException();
    }
    return argumentList.get(index);
  }

}