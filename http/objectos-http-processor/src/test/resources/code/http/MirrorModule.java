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
import br.com.objectos.io.File;
import br.com.objectos.http.server.CatchAll;
import br.com.objectos.http.server.FileResponse;
import br.com.objectos.http.server.Get;
import br.com.objectos.http.server.HttpRuntime;
import br.com.objectos.http.server.Module;
import br.com.objectos.http.server.Response;

@SuppressWarnings("deprecation")
@Module("/mirror")
abstract class MirrorModule {

  @Get("/:filename")
  Response get(@CatchAll String filename) {
    File file = Directory.USER_HOME.fileAt(filename);
    return new FileResponse(file);
  }
  
  void ignoreMe() {
    System.out.println("As I am not annotated!");
  }
  
  abstract HttpRuntime runtime();
  
}