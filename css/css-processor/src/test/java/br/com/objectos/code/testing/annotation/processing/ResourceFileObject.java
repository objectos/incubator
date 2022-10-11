/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.testing.annotation.processing;

import br.com.objectos.core.io.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import javax.tools.FileObject;

class ResourceFileObject implements FileObject {

  private final Resource resource;

  ResourceFileObject(Resource resource) {
    this.resource = resource;
  }

  @Override
  public boolean delete() {
    return false;
  }

  @Override
  public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    return null;
  }

  @Override
  public long getLastModified() {
    return 0;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public InputStream openInputStream() throws IOException {
    return resource.openInputStream();
  }

  @Override
  public OutputStream openOutputStream() throws IOException {
    return null;
  }

  @Override
  public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
    return null;
  }

  @Override
  public Writer openWriter() throws IOException {
    return null;
  }

  @Override
  public URI toUri() {
    return null;
  }

}