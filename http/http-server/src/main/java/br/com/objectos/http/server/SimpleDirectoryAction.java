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

import br.com.objectos.fs.Directory;
import br.com.objectos.fs.PathNameVisitor;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import java.io.IOException;

public class SimpleDirectoryAction implements PathNameVisitor<Response, Void>, HttpAction {

  private final Directory directory;

  public SimpleDirectoryAction(Directory directory) {
    this.directory = directory;
  }

  @Override
  public Response execute(Request request) {
    try {
      String pathname;
      pathname = request.getString(0);

      ResolvedPath query;
      query = directory.resolve(pathname);

      return query.acceptPathNameVisitor(this, null);
    } catch (IOException e) {
      return StatusCode.INTERNAL_SERVER_ERROR.response();
    }
  }

  @Override
  public Response visitDirectory(Directory directory, Void p) throws IOException {
    ResolvedPath index;
    index = directory.resolve("index.html");

    return index.acceptPathNameVisitor(this, p);
  }

  @Override
  public Response visitNotFound(ResolvedPath notFound, Void p) {
    return StatusCode.NOT_FOUND.response();
  }

  @Override
  public Response visitRegularFile(RegularFile file, Void p) {
    return new FileResponse(file);
  }

}