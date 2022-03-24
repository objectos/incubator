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

import static br.com.objectos.http.server.Method.GET;
import static br.com.objectos.http.server.Method.HEAD;
import static br.com.objectos.http.server.Resolution.resolved;
import static org.testng.Assert.assertEquals;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.http.AbstractHttpCoreTest;
import br.com.objectos.http.server.AbstractHttpModule;
import br.com.objectos.http.server.AlwaysOk;
import br.com.objectos.http.server.Method;
import br.com.objectos.http.server.Resolution;
import org.testng.annotations.Test;

public class RouterTest extends AbstractHttpCoreTest {

  @Test
  public void getAnyShould200EmptyPath() {
    Router reg = Router.of(RouteFake.GET_any);
    Resolution res = reg.resolve(proto(GET, path("/")));
    assertEquals(res, resolved(AlwaysOk.INSTANCE, req(list())));
  }

  @Test
  public void intPart() {
    Route route;
    route = new Route(
        Method.GET,
        Location.parse("/docs/1/index.html"),
        AlwaysOk.INSTANCE
    );

    Router router;
    router = Router.of(route);

    Resolution res;
    res = router.resolve(proto(GET, path("/docs/1/index.html")));

    assertEquals(res, resolved(AlwaysOk.INSTANCE, req(list())));
  }

  @Test
  public void root() {
    Router reg = Router.of(RouteFake.GET_root);
    Resolution res = reg.resolve(proto(GET, path("/")));
    assertEquals(res, resolved(AlwaysOk.INSTANCE, req(list())));
  }

  @Test
  public void routeListShouldMatchExpectedList() {
    Route getFile = new Route(
        Method.GET,
        Location.builder().fixed("file.txt").build(),
        AlwaysOk.INSTANCE
    );
    Route postApi = new Route(
        Method.POST,
        Location.builder().fixed("api").fixed("post").build(),
        AlwaysOk.INSTANCE
    );
    Router router = Router.of(new AbstractHttpModule() {
      @Override
      protected void configure() {
        route(Location.parse("/file.txt"), get(AlwaysOk.INSTANCE));
        route(Location.parse("/api/post"), post(AlwaysOk.INSTANCE));
      }
    });
    assertEquals(
        router.routes(),
        ImmutableList.of(getFile, postApi)
    );
  }

  @Test
  public void whenMethodIsDifferentShould404() {
    Router reg = Router.of(RouteFake.GET_x);
    Resolution res = reg.resolve(proto(HEAD, path("/x")));
    assertEquals(res, Resolution.notFound());
  }

  @Test
  public void whenMethodIsTheSameAndPathIsEqualShould200() {
    Router reg = Router.of(RouteFake.GET_x);
    Resolution res = reg.resolve(proto(GET, path("/x")));
    assertEquals(res, resolved(AlwaysOk.INSTANCE, req(list())));
  }

  @Test
  public void whenMethodIsTheSameButPathIsDifferentShould404() {
    Router reg = Router.of(RouteFake.GET_x);
    Resolution res = reg.resolve(proto(HEAD, path("/xpto")));
    assertEquals(res, Resolution.notFound());
  }

}