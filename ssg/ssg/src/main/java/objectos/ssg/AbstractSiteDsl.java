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

import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.core.io.Resource;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.map.MutableMap;
import br.com.objectos.core.object.Checks;
import br.com.objectos.http.media.MediaType;
import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class AbstractSiteDsl implements SiteDsl {

  private final Map<Class<?>, ImmutableList<?>> byTypeMap = MutableMap.create();

  private final Map<Class<?>, String> hrefMap = new IdentityHashMap<>();

  private final Map<Class<?>, Object> objectMap = new LinkedHashMap<>();

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
    if (objectMap.containsKey(key)) {
      Object instance;
      instance = objectMap.get(key);

      return key.cast(instance);
    } else {
      String name;
      name = key.getCanonicalName();

      throw new NoSuchElementException(name);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public final <T> ImmutableList<T> getInstancesByType(Class<? extends T> type) {
    ImmutableList<?> result;
    result = byTypeMap.get(type);

    if (result == null) {
      MutableList<Object> list;
      list = new MutableList<>();

      for (Object v : objectMap.values()) {
        if (type.isInstance(v)) {
          list.add(v);
        }
      }

      result = list.toImmutableList();

      byTypeMap.put(type, result);
    }

    return (ImmutableList<T>) result;
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
    Set<Entry<Class<?>, Object>> entries = objectMap.entrySet();

    for (Entry<Class<?>, Object> entry : entries) {
      Class<?> key;
      key = entry.getKey();

      String href;
      href = hrefMap.get(key);

      if (href == null) {
        continue;
      }

      Object v;
      v = entry.getValue();

      if (v instanceof SitePage p) {
        addTemplate(href, p);
      }

      else if (v instanceof SiteStyleSheet s) {
        addStyleSheet(href, s);
      }

      else {
        throw new IllegalArgumentException("Unknown type " + key);
      }
    }
  }

  final StringBuilder hrefBuilder() {
    stringBuilder.setLength(0);

    String baseHref;
    baseHref = getBaseHref();

    stringBuilder.append(baseHref);

    stringBuilder.append('/');

    return stringBuilder;
  }

  final void put(Class<? extends Object> key, Object value) {
    Checks.checkNotNull(value, "value == null");

    if (objectMap.containsKey(key)) {
      throw new IllegalArgumentException(
        "An object of type " + key + " was already registered.");
    }

    if (value instanceof SiteFragment) {
      SiteFragment f;
      f = (SiteFragment) value;

      f.setSite(this);
    }

    objectMap.put(key, value);
  }

  final void put(Object value) {
    Class<? extends Object> key;
    key = value.getClass();

    put(key, value);
  }

  final void validateName(String name) {
    // TODO: implement me
  }

  private void addDirectory0(String name, SiteDirectory directory) {
    put(directory);

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

  private class ThisDirectory implements SiteDirectory.Configuration {
    private final SiteDirectory directory;

    private final String directoryHref;

    public ThisDirectory(SiteDirectory directory, String href) {
      this.directory = directory;

      this.directoryHref = href;
    }

    @Override
    public final void addPage(String fileName, SitePage page) {
      Checks.checkNotNull(fileName, "fileName == null");
      Checks.checkNotNull(page, "page == null");

      put(page);

      putHref(page, fileName);
    }

    @Override
    public final void addResource(String resourceName) {
      Class<? extends SiteDirectory> directoryClass;
      directoryClass = directory.getClass();

      Resource resource;
      resource = Resource.getResource(directoryClass, resourceName);

      addResource0(resourceName, resource);
    }

    @Override
    public final void addResource(String path, InputStreamSource resource) {
      Checks.checkNotNull(path, "path == null");
      Checks.checkNotNull(resource, "resource == null");

      addResource0(path, resource);
    }

    @Override
    public final void addResource(String path, InputStreamSource resource, MediaType mediaType) {
      Checks.checkNotNull(path, "path == null");
      Checks.checkNotNull(resource, "resource == null");
      Checks.checkNotNull(mediaType, "mediaType == null");

      String href;
      href = safeHref(path);

      AbstractSiteDsl.this.addResource(href, resource, mediaType);
    }

    protected void addResource0(String path, InputStreamSource resource) {
      String href;
      href = safeHref(path);

      AbstractSiteDsl.this.addResource(href, resource);
    }

    final void configure() {
      directory.configure(this);
    }

    private void putHref(HasHref o, String name) {
      String href;
      href = safeHref(name);

      Class<? extends HasHref> key;
      key = o.getClass();

      hrefMap.put(key, href);
    }

    private String safeHref(String name) {
      validateName(name);

      return directoryHref + name;
    }
  }

}
