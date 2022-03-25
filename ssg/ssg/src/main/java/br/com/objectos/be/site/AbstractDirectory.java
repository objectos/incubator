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
package br.com.objectos.be.site;

import br.com.objectos.be.resource.GenericResource;
import br.com.objectos.core.io.Read;
import br.com.objectos.core.io.Resource;
import br.com.objectos.core.object.Checks;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.tmpl.Template;
import br.com.objectos.http.media.MediaType;
import java.io.IOException;

public abstract class AbstractDirectory implements Directory {

  private DirectoryDsl dsl;

  protected AbstractDirectory() {}

  @Override
  public final void acceptDirectoryDsl(DirectoryDsl dsl) {
    this.dsl = Checks.checkNotNull(dsl, "dsl == null");
    try {
      configure();
    } finally {
      this.dsl = null;
    }
  }

  protected final void addResource(MediaType type, String resourceName) {
    Checks.checkNotNull(type, "type == null");
    Checks.checkNotNull(resourceName, "resourceName == null");
    dsl.addResource(
      new ThisGenericResource(
        type,
        resourceName,
        Resource.getResource(getClass(), resourceName)
      )
    );
  }

  protected final void addStyleSheet(StyleSheet sheet) {
    dsl.addStyleSheet(sheet);
  }

  protected final void addTemplate(Template template) {
    dsl.addTemplate(template);
  }

  protected abstract void configure();

  private static class ThisGenericResource implements GenericResource {

    private final String filename;
    private final Resource resource;
    private final MediaType type;

    ThisGenericResource(MediaType type, String filename, Resource resource) {
      this.type = type;
      this.filename = filename;
      this.resource = resource;
    }

    @Override
    public final String filename() {
      return filename;
    }

    @Override
    public final byte[] readAllBytes() throws IOException {
      return Read.byteArray(resource);
    }

    @Override
    public final MediaType type() {
      return type;
    }

  }

}
