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

import br.com.objectos.http.server.CatchAll;
import br.com.objectos.http.server.FileResponse;
import br.com.objectos.http.server.Get;
import br.com.objectos.http.server.HttpRuntime;
import br.com.objectos.http.server.Module;
import br.com.objectos.http.server.Response;
import br.com.objectos.http.server.StatusCode;
import br.com.objectos.comuns.io.Directory;
import br.com.objectos.comuns.io.File;
import br.com.objectos.way.html.Document;
import br.com.objectos.way.html.Html.Html0;

@SuppressWarnings("deprecation")
@Module("/response")
abstract class ResponseModule {

  @Get("/file/:filename")
  File file(@CatchAll String filename) {
    return Directory.USER_HOME.fileAt(filename);
  }

  @Get("/status")
  StatusCode status() {
    return StatusCode.OK;
  }

  @Get("/text")
  String text() {
    return "A text/plain response";
  }
  
  @Get("/html/document")
  Document htmlDocument() {
    Html0 html = Document.html()._html();
    return Document.html5(html);
  }

  @Get("/html/html")
  Html0 htmlHtml() {
    return Document.html()._html();
  }

  @Get("/int/:id")
  String intParam(int id) {
    return Integer.toString(id);
  }

}