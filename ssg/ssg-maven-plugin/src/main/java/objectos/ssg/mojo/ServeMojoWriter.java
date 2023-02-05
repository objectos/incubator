/*
 * Copyright (C) 2011-2023 Objectos Software LTDA.
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
package objectos.ssg.mojo;

import static br.com.objectos.http.server.Method.GET;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.path.Location;
import br.com.objectos.http.path.Route;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.HttpModuleDsl;
import br.com.objectos.http.server.HttpServerBuilder;
import br.com.objectos.http.server.MutableHttpServer;
import java.io.IOException;
import objectos.ssg.SiteWriter;
import objectos.util.GrowableList;

final class ServeMojoWriter implements HttpModule, SiteWriter {

  private final GrowableList<Route> routes = new GrowableList<>();

  @Override
  public final void acceptHttpModuleDsl(HttpModuleDsl dsl) {
    for (Route route : routes) {
      dsl.addRoute(route);
    }
  }

  public final MutableHttpServer buildHttpServer(HttpServerBuilder factory) {
    return factory.buildMutable(this);
  }

  public final void writeBytes(
      String path, MediaType mediaType, byte[] contents) throws IOException {
    System.out.println(path);

    Location location;
    location = Location.parse(path);

    ByteArrayHttpAction action;
    action = new ByteArrayHttpAction(contents, mediaType);

    Route route;
    route = new Route(GET, location, action);

    routes.add(route);
  }

  public final void writeString(
      String path, MediaType mediaType, String contents) throws IOException {
    byte[] utf8;
    utf8 = contents.getBytes(Charsets.utf8());

    writeBytes(path, mediaType, utf8);
  }

}
