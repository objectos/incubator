/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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
package br.com.objectos.code.model.element;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import javax.tools.FileObject;

final class FileObjectProcessingResource implements ProcessingResource {

  private final FileObject object;

  public FileObjectProcessingResource(FileObject object) {
    this.object = object;
  }

  @Override
  public final String getName() {
    return object.getName();
  }

  @Override
  public final InputStream openInputStream() throws IOException {
    return object.openInputStream();
  }

  @Override
  public final Reader openReader(Charset charset) throws IOException {
    InputStream inputStream;
    inputStream = openInputStream();

    return new InputStreamReader(inputStream, charset);
  }

}
