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

import br.com.objectos.http.server.HttpInterceptor;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.HttpModuleDsl;
import br.com.objectos.http.server.RequestProto;
import br.com.objectos.http.server.RequestedPart;
import br.com.objectos.http.server.Resolution;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import objectos.lang.Check;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

public class Router {

  private static final Router EMPTY = new Router(ImmutableList.of());

  private final ImmutableList<Route> routes;

  private Router(ImmutableList<Route> routes) {
    this.routes = routes;
  }

  public static Router empty() {
    return EMPTY;
  }

  public static Router of(HttpModule module) {
    Check.notNull(module, "module == null");
    Builder b = new Builder();
    module.acceptHttpModuleDsl(b);
    return b.build();
  }

  static Router of(Route... routes) {
    return new Router(ImmutableList.copyOf(routes));
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Router)) {
      return false;
    }
    Router that = (Router) obj;
    return routes.equals(that.routes);
  }

  @Override
  public final int hashCode() {
    return routes.hashCode();
  }

  public final Resolution resolve(RequestProto request) {
    MutableList<RouteParser> parserList;
    parserList = MutableList.create();

    for (Route route : routes) {
      if (route.hasSameMethod(request)) {
        RouteParser parser;
        parser = route.parser(request);

        parserList.add(parser);
      }
    }

    return !request.hasNextRequestedPart()
        ? resolveEmpty(parserList)
        : resolvePath(parserList, request);
  }

  final ImmutableList<Route> routes() {
    return routes;
  }

  private Resolution resolveEmpty(Iterable<RouteParser> parserList) {
    MutableList<Resolution> resolutions;
    resolutions = MutableList.create();

    for (RouteParser parser : parserList) {
      if (parser.matchesEmpty()) {
        Resolution resolution = parser.resolve();

        resolutions.add(resolution);
      }
    }

    Iterator<Resolution> iterator = resolutions.iterator();

    if (iterator.hasNext()) {
      return iterator.next();
    } else {
      return Resolution.notFound();
    }
  }

  private Resolution resolvePath(Iterable<RouteParser> iterable, RequestProto request) {
    List<RouteParser> parserList = new ArrayList<>();

    for (RouteParser routeParser : iterable) {
      parserList.add(routeParser);
    }

    while (request.hasNextRequestedPart()) {
      RequestedPart part = request.nextRequestedPart();

      Iterator<RouteParser> iterator = parserList.iterator();
      while (iterator.hasNext()) {
        RouteParser parser = iterator.next();

        if (!parser.matches(part)) {
          iterator.remove();
        }
      }
    }

    for (RouteParser parser : parserList) {
      if (parser.wasLastPart()) {
        return parser.resolve();
      }
    }

    return Resolution.notFound();
  }

  private static class Builder
      implements
      HttpModuleDsl {

    private final MutableList<Route> routes = MutableList.create();

    private Builder() {}

    @Override
    public final void addRoute(Route route) {
      Check.notNull(route, "route == null");
      routes.add(beforeAdd(route));
    }

    public final Router build() {
      return new Router(routes());
    }

    @Override
    public final void install(HttpModule module) {
      Check.notNull(module, "module == null");
      module.acceptHttpModuleDsl(this);
    }

    @Override
    public final void installAndIntercept(HttpModule module, HttpInterceptor interceptor) {
      Check.notNull(module, "module == null");
      Check.notNull(interceptor, "interceptor == null");
      Builder builder = new WithInterceptor(interceptor);
      module.acceptHttpModuleDsl(builder);
      routes.addAll(builder.routes);
    }

    Route beforeAdd(Route route) {
      return route;
    }

    final ImmutableList<Route> routes() {
      return routes.toImmutableList();
    }

  }

  private static class WithInterceptor extends Builder {

    private final HttpInterceptor interceptor;

    WithInterceptor(HttpInterceptor interceptor) {
      this.interceptor = interceptor;
    }

    @Override
    final Route beforeAdd(Route route) {
      return route.with(interceptor);
    }

  }

}