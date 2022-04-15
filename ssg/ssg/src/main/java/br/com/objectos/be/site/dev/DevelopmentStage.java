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
package br.com.objectos.be.site.dev;

import br.com.objectos.be.site.AbstractSiteDsl;
import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.tmpl.Template;
import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.media.MediaTypes;
import br.com.objectos.http.path.Location;
import br.com.objectos.http.path.Route;
import br.com.objectos.http.server.HttpAction;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.HttpModuleDsl;
import br.com.objectos.http.server.HttpServerBuilder;
import br.com.objectos.http.server.Method;
import br.com.objectos.http.server.MutableHttpServer;

public class DevelopmentStage extends AbstractSiteDsl implements HttpModule {

  private final MutableList<Route> routes = MutableList.create();

  @Override
  public final void acceptHttpModuleDsl(HttpModuleDsl dsl) {
    for (Route route : routes) {
      dsl.addRoute(route);
    }
  }

  @Override
  public final void addResource(String fullPath, InputStreamSource resource) {
    MediaType mediaType;
    mediaType = MediaTypes.ofFileName(fullPath);

    addResource(fullPath, resource, mediaType);
  }

  @Override
  public final void addResource(String fullPath, InputStreamSource resource, MediaType mediaType) {
    InputStreamSourceHttpAction action;
    action = new InputStreamSourceHttpAction(resource, mediaType);

    addHttpAction(fullPath, action);
  }

  @Override
  public final void addStyleSheet(String fullPath, StyleSheet styleSheet) {
    StyleSheetHttpAction action;
    action = new StyleSheetHttpAction(styleSheet);

    addHttpAction(fullPath, action);
  }

  @Override
  public final void addTemplate(String fullPath, Template template) {
    TemplateHttpAction action;
    action = new TemplateHttpAction(template);

    addHttpAction(fullPath, action);
  }

  public final MutableHttpServer buildHttpServer(HttpServerBuilder factory) {
    return factory.buildMutable(this);
  }

  @Override
  public final String getBaseHref() {
    return "";
  }

  @Override
  public final boolean isDevelopment() {
    return true;
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
