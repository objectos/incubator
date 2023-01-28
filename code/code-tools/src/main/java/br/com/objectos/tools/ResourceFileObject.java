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
package br.com.objectos.tools;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Read;
import br.com.objectos.core.io.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import javax.tools.FileObject;

final class ResourceFileObject implements FileObject {

  private final String relativeName;

  private final Resource resource;

  ResourceFileObject(String relativeName,
                     Resource resource) {
    this.relativeName = relativeName;
    this.resource = resource;
  }

  @Override
  public boolean delete() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    return Read.string(resource, Charsets.utf8());
  }

  @Override
  public long getLastModified() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final String getName() {
    return relativeName;
  }

  @Override
  public final InputStream openInputStream() throws IOException {
    return resource.openInputStream();
  }

  @Override
  public final OutputStream openOutputStream() throws IOException {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final Reader openReader(boolean ignoreEncodingErrors) throws IOException {
    return new InputStreamReader(resource.openInputStream());
  }

  @Override
  public final Writer openWriter() throws IOException {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final URI toUri() {
    return resource.toUri();
  }

}
