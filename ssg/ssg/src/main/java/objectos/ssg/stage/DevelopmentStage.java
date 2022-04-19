/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package objectos.ssg.stage;

import br.com.objectos.core.list.MutableList;
import br.com.objectos.http.path.Location;
import br.com.objectos.http.path.Route;
import br.com.objectos.http.server.HttpAction;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.HttpModuleDsl;
import br.com.objectos.http.server.HttpServerBuilder;
import br.com.objectos.http.server.Method;
import br.com.objectos.http.server.MutableHttpServer;
import objectos.ssg.SitePage;
import objectos.ssg.SiteResource;
import objectos.ssg.SiteStyleSheet;
import objectos.ssg.Stage;

public class DevelopmentStage extends Stage implements HttpModule {

  private final MutableList<Route> routes = MutableList.create();

  @Override
  public final void acceptHttpModuleDsl(HttpModuleDsl dsl) {
    for (Route route : routes) {
      dsl.addRoute(route);
    }
  }

  public final MutableHttpServer buildHttpServer(HttpServerBuilder factory) {
    return factory.buildMutable(this);
  }

  @Override
  public final String getBaseHref() {
    return "";
  }

  @Override
  public final void renderSitePage(String fullPath, SitePage page) {
    TemplateHttpAction action;
    action = new TemplateHttpAction(page);

    addHttpAction(fullPath, action);
  }

  @Override
  public final void renderSiteResource(SiteResource resource) {
    String fullPath;
    fullPath = resource.path();

    SiteResourceHttpAction action;
    action = new SiteResourceHttpAction(resource);

    addHttpAction(fullPath, action);
  }

  @Override
  public final void renderSiteStyleSheet(String fullPath, SiteStyleSheet sheet) {
    StyleSheetHttpAction action;
    action = new StyleSheetHttpAction(sheet);

    addHttpAction(fullPath, action);
  }

  private void addHttpAction(String fullPath, HttpAction action) {
    System.out.println(fullPath);

    Location location;
    location = Location.parse(fullPath);

    Route route;
    route = new Route(Method.GET, location, action);

    routes.add(route);
  }

}
