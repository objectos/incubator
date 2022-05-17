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

import br.com.objectos.http.path.Location;
import br.com.objectos.http.path.Route;
import objectos.lang.Checks;

public abstract class AbstractHttpModule implements HttpModule {

  private HttpModuleDsl dsl;

  protected AbstractHttpModule() {}

  @Override
  public final void acceptHttpModuleDsl(HttpModuleDsl dsl) {
    this.dsl = Checks.checkNotNull(dsl, "dsl == null");
    try {
      configure();
    } finally {
      this.dsl = null;
    }
  }

  protected abstract void configure();

  protected final RouteElement get(HttpAction action) {
    Checks.checkNotNull(action, "action == null");
    return new RouteElement(Method.GET, action);
  }

  protected final RouteElement post(HttpAction action) {
    Checks.checkNotNull(action, "action == null");
    return new RouteElement(Method.POST, action);
  }

  protected final void route(Location location, RouteElement e1) {
    Checks.checkNotNull(location, "location == null");
    Checks.checkNotNull(e1, "e1 == null");
    e1.acceptLocation(location);
  }

  protected final void route(Location location, RouteElement e1, RouteElement e2) {
    Checks.checkNotNull(location, "location == null");
    Checks.checkNotNull(e1, "e1 == null");
    Checks.checkNotNull(e2, "e2 == null");
    e1.acceptLocation(location);
    e2.acceptLocation(location);
  }

  protected class RouteElement {

    private final HttpAction action;
    private final Method method;

    private RouteElement(Method method, HttpAction action) {
      this.method = method;
      this.action = action;
    }

    final void acceptLocation(Location location) {
      dsl.addRoute(new Route(method, location, action));
    }

  }

}
