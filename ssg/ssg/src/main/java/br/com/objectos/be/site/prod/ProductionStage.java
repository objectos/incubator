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
package br.com.objectos.be.site.prod;

import br.com.objectos.be.resource.BaseUrl;
import br.com.objectos.be.resource.GenericResource;
import br.com.objectos.be.resource.RelativeBaseUrl;
import br.com.objectos.be.resource.Resources;
import br.com.objectos.be.site.AbstractSiteDsl;
import br.com.objectos.be.site.DirectoryDsl;
import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.core.io.Read;
import br.com.objectos.core.io.Resource;
import br.com.objectos.core.io.Write;
import br.com.objectos.core.object.Checks;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.html.tmpl.Template;
import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.path.Location;
import java.io.IOException;
import java.io.UncheckedIOException;

public class ProductionStage extends AbstractSiteDsl {

  private final String baseHref;

  private final Directory target;

  public ProductionStage(String baseHref, Directory target) {
    this.baseHref = Checks.checkNotNull(baseHref, "baseHref == null");

    this.target = Checks.checkNotNull(target, "target == null");
  }

  @Override
  public final void addResource(String fullPath, InputStreamSource resource) {
    addResource(fullPath, resource, null);
  }

  @Override
  public final void addResource(String fullPath, InputStreamSource resource, MediaType mediaType) {
    try {
      byte[] data;
      data = Read.byteArray(resource);

      Location location;
      location = Location.parse(fullPath);

      RegularFile dest;
      dest = location.getOrCreateRegularFile(target);

      dest.truncate();

      Write.byteArray(dest, data);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public final void addStyleSheet(String fullPath, StyleSheet styleSheet) {
    write(fullPath, styleSheet.printMinified());
  }

  @Override
  public final void addTemplate(String fullPath, Template template) {
    write(fullPath, template.printMinified());
  }

  @Override
  public final String getBaseHref() {
    return baseHref;
  }

  @Override
  public final BaseUrl getBaseUrl() {
    return RelativeBaseUrl.root();
  }

  @Override
  public final DirectoryDsl getDirectoryDsl(BaseUrl baseUrl) {
    Checks.checkNotNull(baseUrl, "baseUrl == null");
    return new ThisModuleDsl(baseUrl);
  }

  @Override
  public final boolean isProduction() {
    return true;
  }

  private void write(String fullPath, String text) {
    try {
      Location location;
      location = Location.parse(fullPath);

      RegularFile file;
      file = location.getOrCreateRegularFile(target);

      file.truncate();

      Write.string(file, Charsets.utf8(), text);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private class ThisModuleDsl extends DirectoryDsl {

    ThisModuleDsl(BaseUrl baseUrl) {
      super(baseUrl);
    }

    @Override
    public final void addResource(Class<?> contextClass, String resourceName) {
      try {
        Resource resource;
        resource = Resource.getResource(contextClass, resourceName);

        RegularFile src;
        src = LocalFs.getRegularFile(resource);

        byte[] data;
        data = Read.byteArray(src);

        Directory parent;
        parent = baseUrl.getDirectory(target);

        String fileName;
        fileName = src.getName();

        RegularFile dest;
        dest = parent.getOrCreateRegularFile(fileName);

        dest.truncate();

        Write.byteArray(dest, data);
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }

    @Override
    public final void addResource(GenericResource resource) {
      try {
        Directory parent;
        parent = baseUrl.getDirectory(target);

        String fileName;
        fileName = resource.filename();

        RegularFile dest;
        dest = parent.getOrCreateRegularFile(fileName);

        dest.truncate();

        Write.byteArray(dest, resource.readAllBytes());
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }

    @Override
    public final void addStyleSheet(StyleSheet sheet) {
      write(Resources.getFilename(sheet), sheet.printMinified());
    }

    @Override
    public final void addTemplate(Template template) {
      write(Resources.getFilename(template), template.printMinified());
    }

    @Override
    protected final DirectoryDsl newInstance(BaseUrl baseUrl) {
      return new ThisModuleDsl(baseUrl);
    }

    private void write(String filename, String text) {
      try {
        Directory parent;
        parent = baseUrl.getDirectory(target);

        RegularFile file;
        file = parent.getOrCreateRegularFile(filename);

        file.truncate();

        Write.string(file, Charsets.utf8(), text);
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }

  }

}
