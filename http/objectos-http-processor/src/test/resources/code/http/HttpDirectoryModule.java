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

import br.com.objectos.io.Directory;
import br.com.objectos.http.server.Configuration;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.Request;
import br.com.objectos.http.server.Response;
import javax.annotation.Generated;

@Generated("br.com.objectos.http.processor.ModuleProcessor")
public final class HttpDirectoryModule extends DirectoryModule implements HttpModule {
  public HttpDirectoryModule(Directory directory) {
    super(directory);
  }

  @Override
  public void configure(Configuration configuration) {
    configuration
        .route("/directory/:filename")
        .fixedPart("directory")
        .catchAllPart()
        .onGet(this::___get___);
  }

  private Response ___get___(Request request) throws HttpException {
    String filename = request.getString(0);
    return get(filename);
  }
}
