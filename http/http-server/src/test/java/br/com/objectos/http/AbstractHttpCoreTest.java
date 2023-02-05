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
package br.com.objectos.http;

import br.com.objectos.http.path.Argument;
import br.com.objectos.http.server.EmptyRequestProto;
import br.com.objectos.http.server.FakeRequestProto;
import br.com.objectos.http.server.Method;
import br.com.objectos.http.server.Request;
import br.com.objectos.http.server.RequestProto;
import br.com.objectos.http.server.RequestedPath;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractHttpCoreTest {

  protected final List<Argument> list(Argument... args) {
    return Arrays.asList(args);
  }

  protected final RequestedPath path(String path) {
    return new RequestedPath(path);
  }

  protected final RequestProto proto(Method method, RequestedPath path) {
    return new FakeRequestProto(method, path);
  }

  protected final Request req(List<Argument> argumentList) {
    return new Request(
        argumentList,
        EmptyRequestProto.INSTANCE);
  }

}
