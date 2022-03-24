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
package br.com.objectos.http.path;

import br.com.objectos.http.server.HttpAction;
import br.com.objectos.http.server.HttpInterceptor;
import br.com.objectos.http.server.Method;
import br.com.objectos.http.server.RequestProto;
import java.util.Objects;

public class Route {

  private final Method method;
  private final Location location;
  private final HttpAction action;

  public Route(Method method, Location location, HttpAction action) {
    this.method = method;
    this.location = location;
    this.action = action;
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Route)) {
      return false;
    }
    Route that = (Route) obj;
    return Objects.equals(method, that.method)
        && Objects.equals(location, that.location)
        && Objects.equals(action, that.action);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(method, location, action);
  }

  public final boolean hasSameMethod(RequestProto proto) {
    return proto.matches(method);
  }

  public final RouteParser parser(RequestProto request) {
    return location.newRouteParser(action, request);
  }

  public final Route with(HttpInterceptor filter) {
    return new Route(method, location, new WithFilterAction(filter, action));
  }

}