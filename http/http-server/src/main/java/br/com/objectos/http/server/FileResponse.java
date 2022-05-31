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

import br.com.objectos.fs.RegularFile;
import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.media.MediaTypes;
import java.io.IOException;
import objectos.lang.Check;

public class FileResponse implements HttpAction, Response {

  private final RegularFile file;

  public FileResponse(RegularFile file) {
    this.file = Check.notNull(file, "file == null");
  }

  @Override
  public final Response execute(Request request) throws HttpException {
    return this;
  }

  @Override
  public final void writeTo(ResponseWriter writer) throws HttpException {
    try {
      if (file.exists()) {
        String name = file.getName();

        MediaType mediaType = MediaTypes.ofFileName(name);

        writer.sayOk()
            .contentLength(file.size())
            .contentType(mediaType)
            .messageBody(file)
            .write();
      } else {
        writer.sayNotFound()
            .write();
      }
    } catch (IOException e) {
      throw new Code500InternalServerErrorException(e);
    }
  }

}