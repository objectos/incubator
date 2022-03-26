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

import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.core.io.Resource;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.http.media.MediaType;

public abstract class SiteDirectory {

  private final MutableList<SiteDirectory> directories = MutableList.create();

  private AbstractSiteDsl dsl;

  private String name;

  private final MutableList<SitePage> pages = MutableList.create();

  private SiteDirectory parent;

  private final MutableList<SiteStyleSheet> sheets = MutableList.create();

  protected SiteDirectory() {}

  public final StringBuilder hrefBuilder() {
    StringBuilder href;

    if (parent != null) {
      href = parent.hrefBuilder();
    } else {
      href = dsl.hrefBuilder();
    }

    if (name != null) {
      acceptHrefBuilder(href, name);
    }

    return href;
  }

  protected void acceptHrefBuilder(StringBuilder href, String name) {
    href.append(name);

    href.append('/');
  }

  protected final void addDirectory(String name, SiteDirectory directory) {
    directories.addWithNullMessage(directory, "directory == null");

    directory.setName(name);

    directory.setParent(this);

    dsl.put(directory);

    directory.acceptSiteDsl(dsl);
  }

  protected final void addPage(String fileName, SitePage page) {
    pages.addWithNullMessage(page, "page == null");

    page.setDirectory(this);

    page.setFileName(fileName);

    dsl.put(page);
  }

  protected final void addResource(String resourceName) {
    Resource resource;
    resource = Resource.getResource(getClass(), resourceName);

    String href;
    href = safeHref(resourceName);

    dsl.addResource(href, resource);
  }

  protected final void addResource(String path, InputStreamSource resource) {
    Checks.checkNotNull(path, "path == null");
    Checks.checkNotNull(resource, "resource == null");

    String href;
    href = safeHref(path);

    dsl.addResource(href, resource);
  }

  protected final void addResource(String path, InputStreamSource resource, MediaType mediaType) {
    Checks.checkNotNull(path, "path == null");
    Checks.checkNotNull(resource, "resource == null");
    Checks.checkNotNull(mediaType, "mediaType == null");

    String href;
    href = safeHref(path);

    dsl.addResource(href, resource, mediaType);
  }

  protected final void addResource(String resourceName, MediaType mediaType) {
    Resource resource;
    resource = Resource.getResource(getClass(), resourceName);

    String href;
    href = safeHref(resourceName);

    dsl.addResource(href, resource, mediaType);
  }

  protected final void addStyleSheet(String fileName, SiteStyleSheet sheet) {
    sheets.addWithNullMessage(sheet, "sheet == null");

    sheet.setDirectory(this);

    sheet.setFileName(fileName);

    dsl.put(sheet);
  }

  protected abstract void configure();

  protected final <T> T getInstance(Class<? extends T> key) {
    return dsl.getInstance(key);
  }

  protected final <T> ImmutableList<T> getInstancesByType(Class<? extends T> type) {
    return dsl.getInstancesByType(type);
  }

  protected final void putInstance(Object value) {
    dsl.put(value);
  }

  protected void renderSiteDirectory() {
    for (SitePage page : pages) {
      page.renderSitePage();
    }

    for (SiteStyleSheet sheet : sheets) {
      String href;
      href = sheet.getHref();

      dsl.addStyleSheet(href, sheet);
    }

    for (SiteDirectory directory : directories) {
      directory.render(dsl);
    }
  }

  final void acceptSiteDsl(AbstractSiteDsl dsl) {
    this.dsl = Checks.checkNotNull(dsl, "dsl == null");

    try {
      configure();
    } finally {
      this.dsl = null;
    }
  }

  final void addTemplate(String href, SitePage sitePage) {
    dsl.addTemplate(href, sitePage);
  }

  final void render(AbstractSiteDsl dsl) {
    this.dsl = Checks.checkNotNull(dsl, "dsl == null");

    try {
      renderSiteDirectory();
    } finally {
      this.dsl = null;
    }
  }

  final void setName(String name) {
    Checks.checkState(this.name == null, "name was already set");

    this.name = name;
  }

  final void setParent(SiteDirectory parent) {
    Checks.checkState(this.parent == null, "parent was already set");

    this.parent = Checks.checkNotNull(parent, "parent == null");
  }

  private String safeHref(String fileName) {
    // TODO do not allow: absolute, subdir, path navigation

    StringBuilder builder;
    builder = hrefBuilder();

    builder.append(fileName);

    return builder.toString();
  }

}