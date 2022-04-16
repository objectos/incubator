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
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import objectos.ssg.stage.SiteRenderable;
import objectos.ssg.stage.SiteResource;

public abstract class AbstractSiteDsl implements SiteDsl, SiteComponent.Context {

  private final Map<Class<?>, SiteComponent> components = new IdentityHashMap<>();

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

  @Override
  public final void addFragment(SiteFragment fragment) {
    registerComponent(fragment);
  }

  public final void addSite(Site site) throws IOException {
    site.acceptSiteDsl(this);
  }

  public final void addSites(Iterable<? extends Site> sites) throws IOException {
    for (Site site : sites) {
      site.acceptSiteDsl(this);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public final <T extends SiteComponent> T getComponent(Class<? extends T> key) {
    Checks.checkNotNull(key, "key == null");

    SiteComponent component;
    component = components.get(key);

    if (component == null) {
      String msg;
      msg = """
            No component was found with key:

                %s
            """.formatted(key);

      throw new NoSuchElementException(msg);
    }

    return (T) component;
  }

  @Override
  public final <T extends SiteComponent>
      ImmutableList<T> getComponentsByType(Class<? extends T> key) {
    Collection<SiteComponent> values;
    values = components.values();

    Stream<SiteComponent> stream;
    stream = values.stream();

    Stream<SiteComponent> filter;
    filter = stream.filter(key::isInstance);

    Stream<? extends T> cast;
    cast = filter.map(key::cast);

    return ImmutableList.copyOf(cast.iterator());
  }

  @Override
  public final String getHref(Class<?> key) {
    return getHref0(key);
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

    Collection<SiteComponent> values;
    values = components.values();

    for (SiteComponent component : values) {
      component.unregister();
    }
  }

  public abstract void renderSitePage(String fullPath, SitePage page);

  public abstract void renderSiteResource(SiteResource resource);

  public abstract void renderSiteStyleSheet(String fullPath, SiteStyleSheet sheet);

  protected String getHref0(Class<?> key) {
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

  final StringBuilder hrefBuilder() {
    stringBuilder.setLength(0);

    String baseHref;
    baseHref = getBaseHref();

    stringBuilder.append(baseHref);

    stringBuilder.append('/');

    return stringBuilder;
  }

  final void registerComponent(SiteComponent component) {
    Checks.checkNotNull(component, "component == null");

    Class<? extends SiteComponent> key;
    key = component.getClass();

    SiteComponent existing;
    existing = components.get(key);

    if (existing != null) {
      String msg;
      msg = """
            A component was already registered:

                %s

            for the key:

                %s
            """.formatted(existing, key);

      throw new IllegalArgumentException(msg);
    }

    components.put(key, component);
  }

  final void registerRenderable(SiteRenderable renderable) {
    if (renderable instanceof SiteComponent c) {
      registerComponent(c);

      c.configure(this);
    }

    renderables.add(renderable);
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

    return getHref0(key);
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
    public final void addFragment(SiteFragment fragment) {
      AbstractSiteDsl.this.addFragment(fragment);
    }

    @Override
    public final void addPage(String fileName, SitePage page) {
      Checks.checkNotNull(fileName, "fileName == null");
      Checks.checkNotNull(page, "page == null");

      registerRenderable(page);

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

      registerRenderable(sheet);

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
