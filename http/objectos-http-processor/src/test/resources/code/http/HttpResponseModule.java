/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.testing.way;

import br.com.objectos.http.server.Configuration;
import br.com.objectos.http.server.FileResponse;
import br.com.objectos.http.server.HtmlResponse;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.Request;
import br.com.objectos.http.server.Response;
import br.com.objectos.http.server.TextResponse;
import javax.annotation.Generated;

@Generated("br.com.objectos.http.processor.ModuleProcessor")
public final class HttpResponseModule extends ResponseModule implements HttpModule {
  HttpResponseModule() {
    super();
  }

  @Override
  public void configure(Configuration configuration) {
    configuration
        .route("/response/file/:filename")
        .fixedPart("response")
        .fixedPart("file")
        .catchAllPart()
        .onGet(this::___file___)
        .route("/response/status")
        .fixedPart("response")
        .fixedPart("status")
        .onGet(this::___status___)
        .route("/response/text")
        .fixedPart("response")
        .fixedPart("text")
        .onGet(this::___text___)
        .route("/response/html/document")
        .fixedPart("response")
        .fixedPart("html")
        .fixedPart("document")
        .onGet(this::___htmlDocument___)
        .route("/response/html/html")
        .fixedPart("response")
        .fixedPart("html")
        .fixedPart("html")
        .onGet(this::___htmlHtml___)
        .route("/response/int/:id")
        .fixedPart("response")
        .fixedPart("int")
        .intPart()
        .onGet(this::___intParam___);
  }

  private Response ___file___(Request request) {
    String filename = request.getString(0);
    return new FileResponse(file(filename));
  }

  private Response ___status___(Request request) {
    return status().response();
  }

  private Response ___text___(Request request) {
    return new TextResponse(text());
  }

  private Response ___htmlDocument___(Request request) {
    return new HtmlResponse(htmlDocument().toString());
  }

  private Response ___htmlHtml___(Request request) {
    return new HtmlResponse(htmlHtml().toString());
  }

  private Response ___intParam___(Request request) {
    int id = request.getInt(0);
    return new TextResponse(intParam(id));
  }
}
