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
package objectos.ssg;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.http.media.MediaType;
import java.io.IOException;
import java.net.URL;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import objectos.ssg.stage.SiteRenderable;
import objectos.ssg.stage.SiteResource;

public abstract class AbstractSiteDsl implements SiteDsl {

  private final Map<Class<?>, String> hrefMap = new IdentityHashMap<>();

  private final List<SiteRenderable> renderables = new MutableList<>();

  private final StringBuilder stringBuilder = new StringBuilder();

  protected AbstractSiteDsl() {}

  @Override
  public final void addDirectory(SiteDirectory directory) {
    Checks.checkNotNull(directory, "directory == null");

    addDirectory0("", directory);
  }

  @Override
  public final void addDirectory(String name, SiteDirectory directory) {
    Checks.checkNotNull(name, "name == null");
    Checks.checkNotNull(directory, "directory == null");

    addDirectory0(name, directory);
  }

  public final void addSite(Site site) throws IOException {
    site.acceptSiteDsl(this);
  }

  public final void addSites(Iterable<? extends Site> sites) throws IOException {
    for (Site site : sites) {
      site.acceptSiteDsl(this);
    }
  }

  @Override
  public final <T> T getInstance(Class<? extends T> key) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final <T> ImmutableList<T> getInstancesByType(Class<? extends T> type) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public boolean isDevelopment() {
    return false;
  }

  @Override
  public boolean isProduction() {
    return false;
  }

  @Override
  public final void render() {
    for (SiteRenderable renderable : renderables) {
      renderable.render(this);
    }
  }

  public abstract void renderSitePage(String fullPath, SitePage page);

  public abstract void renderSiteResource(SiteResource resource);

  public abstract void renderSiteStyleSheet(String fullPath, SiteStyleSheet sheet);

  final StringBuilder hrefBuilder() {
    stringBuilder.setLength(0);

    String baseHref;
    baseHref = getBaseHref();

    stringBuilder.append(baseHref);

    stringBuilder.append('/');

    return stringBuilder;
  }

  final void renderSitePage(SitePage page) {
    String fullPath;
    fullPath = getHref(page);

    renderSitePage(fullPath, page);
  }

  final void renderSiteStyleSheet(SiteStyleSheet sheet) {
    String fullPath;
    fullPath = getHref(sheet);

    renderSiteStyleSheet(fullPath, sheet);
  }

  final void validateName(String name) {
    // TODO: implement me
  }

  private void addDirectory0(String name, SiteDirectory directory) {
    StringBuilder hrefBuilder;
    hrefBuilder = hrefBuilder();

    validateName(name);

    hrefBuilder.append(name);

    if (!name.isEmpty()) {
      hrefBuilder.append('/');
    }

    String href;
    href = hrefBuilder.toString();

    ThisDirectory d;
    d = new ThisDirectory(directory, href);

    d.configure();
  }

  private String getHref(Object o) {
    Class<?> key;
    key = o.getClass();

    String href;
    href = hrefMap.get(key);

    if (href == null) {
      String msg;
      msg = """
            href value not found for object of type:

               %s
            """.formatted(key);

      throw new IllegalArgumentException(msg);
    }

    return href;
  }

  private class ThisDirectory implements SiteDirectory.Configuration {
    private final SiteDirectory directory;

    private final String directoryHref;

    public ThisDirectory(SiteDirectory directory, String href) {
      this.directory = directory;

      this.directoryHref = href;
    }

    @Override
    public final void addDirectory(String name, SiteDirectory directory) {
      Checks.checkNotNull(name, "name == null");
      Checks.checkNotNull(directory, "directory == null");

      validateName(name);

      String href;
      href = directoryHref + name + "/";

      ThisDirectory d;
      d = new ThisDirectory(directory, href);

      d.configure();
    }

    @Override
    public final void addPage(String fileName, SitePage page) {
      Checks.checkNotNull(fileName, "fileName == null");
      Checks.checkNotNull(page, "page == null");

      renderables.add(page);

      putHref(page, fileName);
    }

    @Override
    public final void addResource(String resourceName) {
      addResource(resourceName, resourceName);
    }

    @Override
    public final void addResource(String path, String resourceName) {
      Checks.checkNotNull(path, "path == null");
      Checks.checkNotNull(resourceName, "resourceName == null");

      addResource0(path, resourceName, null);
    }

    @Override
    public final void addResource(String path, String resourceName, MediaType mediaType) {
      Checks.checkNotNull(path, "path == null");
      Checks.checkNotNull(resourceName, "resourceName == null");
      Checks.checkNotNull(mediaType, "mediaType == null");

      addResource0(path, resourceName, mediaType);
    }

    @Override
    public final void addStyleSheet(String fileName, SiteStyleSheet sheet) {
      Checks.checkNotNull(fileName, "fileName == null");
      Checks.checkNotNull(sheet, "sheet == null");

      renderables.add(sheet);

      putHref(sheet, fileName);
    }

    final void configure() {
      directory.configure(this);
    }

    private void addResource0(String path, String resourceName, MediaType mediaType) {
      String href;
      href = safeHref(path);

      URL url;
      url = url(resourceName);

      SiteResource resource;
      resource = new SiteResource(href, url, mediaType);

      renderables.add(resource);
    }

    private void putHref(Object o, String name) {
      String href;
      href = safeHref(name);

      Class<?> key;
      key = o.getClass();

      hrefMap.put(key, href);
    }

    private String safeHref(String name) {
      validateName(name);

      return directoryHref + name;
    }

    private URL url(String resourceName) {
      Class<? extends SiteDirectory> clazz;
      clazz = directory.getClass();

      URL url;
      url = clazz.getResource(resourceName);

      if (url == null) {
        String msg;
        msg = """
              Could not find resource named:

                  %s

              relative to class:

                  %s
              """.formatted(resourceName, clazz);

        throw new IllegalArgumentException(msg);
      }

      return url;
    }
  }

}
