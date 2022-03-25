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

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.core.map.MutableMap;
import br.com.objectos.core.object.Checks;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class AbstractSiteDsl implements SiteDsl {

  private final Map<Class<?>, ImmutableList<?>> byTypeMap = MutableMap.create();

  private final MutableList<SiteDirectory> directories = MutableList.create();

  private final Map<Class<?>, Object> objectMap = new LinkedHashMap<>();

  private final StringBuilder stringBuilder = new StringBuilder();

  protected AbstractSiteDsl() {}

  @Override
  public final void addDirectory(SiteDirectory directory) {
    addDirectory0(null, directory);
  }

  @Override
  public final void addDirectory(String path, SiteDirectory directory) {
    Checks.checkNotNull(path, "path == null");

    addDirectory0(path, directory);
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
  public final void install() {
    for (SiteDirectory directory : directories) {
      directory.render(this);
    }
  }

  @Override
  public boolean isDevelopment() {
    return false;
  }

  @Override
  public boolean isProduction() {
    return false;
  }

  final <T> T getInstance(Class<? extends T> key) {
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

  @SuppressWarnings("unchecked")
  final <T> ImmutableList<T> getInstancesByType(Class<? extends T> type) {
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

  private void addDirectory0(String name, SiteDirectory directory) {
    directories.addWithNullMessage(directory, "directory == null");

    if (name != null) {
      directory.setName(name);
    }

    directory.acceptSiteDsl(this);
  }

}
