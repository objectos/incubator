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
import br.com.objectos.core.map.MutableMap;
import br.com.objectos.core.object.Checks;
import br.com.objectos.core.set.MutableSet;
import br.com.objectos.core.throwable.Try;
import br.com.objectos.http.media.MediaType;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;

public abstract class Site implements SiteResourceHolder {

  final StringBuilder stringBuilder = new StringBuilder();

  private final Map<Class<?>, Object> byClassMap = new MutableMap<>();

  private Context context;

  private final List<Object> objects = new MutableList<>();

  private final Set<String> paths = new MutableSet<>();

  protected Site() {}

  public final void generate(SiteWriter writer, RenderingOption... options) throws IOException {
    context = new Context() {
      @SuppressWarnings("unchecked")
      @Override
      public final <T> T getObject(Class<? extends T> key) {
        Checks.checkNotNull(key, "key == null");

        Object object;
        object = byClassMap.get(key);

        if (object == null) {
          String msg;
          msg = """

                No object was found for key:

                    %s
                """.formatted(key);

          throw new NoSuchElementException(msg);
        }

        return (T) object;
      }

      @Override
      public final <T> ImmutableList<T> getObjectsByType(Class<? extends T> key) {
        Stream<Object> stream;
        stream = objects.stream();

        Stream<Object> filter;
        filter = stream.filter(key::isInstance);

        Stream<? extends T> cast;
        cast = filter.map(key::cast);

        return ImmutableList.copyOf(cast.iterator());
      }
    };

    byClassMap.clear();

    objects.clear();

    paths.clear();

    stringBuilder.setLength(0);

    addByClass(this);

    Throwable rethrow;
    rethrow = Try.begin();

    try {
      configure();
    } catch (Throwable e) {
      rethrow = e;
    }

    if (rethrow != null) {
      postSiteGeneration();

      Try.rethrowIfPossible(rethrow, IOException.class);
    }

    try {
      for (Object o : objects) {
        if (o instanceof SiteWriteable w) {
          w.writeTo(writer);
        }
      }
    } finally {
      postSiteGeneration();
    }
  }

  @Override
  public void releaseResources() {}

  protected final <T extends SiteDirectory> T addDirectory(String fileName, T directory) {
    String path;
    path = toPath(fileName);

    return addDirectory0(directory, path);
  }

  protected final <T> T addObject(T object) {
    addByClass(object);

    return object;
  }

  protected final <T extends SitePage> T addPage(String fileName, T page) {
    String path;
    path = toPath(fileName);

    return addPage0(page, path);
  }

  protected final SitePath addResource(String resourceName) {
    String path;
    path = toPath(resourceName);

    Class<? extends Site> contextClass;
    contextClass = getClass();

    return addResource0(path, contextClass, resourceName, null);
  }

  protected final <T extends SiteStyleSheet> T addStyleSheet(String fileName, T sheet) {
    String path;
    path = toPath(fileName);

    return addStyleSheet0(sheet, path);
  }

  protected abstract void configure();

  protected void generate() {}

  final <T extends SiteDirectory> T addDirectory0(T directory, String path) {
    directory.set(path, this);

    addByClass(directory);

    return directory;
  }

  final <T extends SitePage> T addPage0(T page, String path) {
    Checks.checkNotNull(page, "page == null");

    addByClass(page);

    page.setPath(path);

    return page;
  }

  final SitePath addResource0(
      String path, Class<?> contextClass, String resourceName, MediaType mediaType) {
    URL url;
    url = url(contextClass, resourceName);

    SiteResource resource;
    resource = new SiteResource(path, url, mediaType);

    objects.add(resource);

    return resource;
  }

  final <T extends SiteStyleSheet> T addStyleSheet0(T sheet, String path) {
    Checks.checkNotNull(sheet, "sheet == null");

    addByClass(sheet);

    sheet.setPath(path);

    return sheet;
  }

  final String toPath(String basePath, String fileName) {
    Hrefs.validateName(fileName);

    stringBuilder.setLength(0);

    stringBuilder.append(basePath);

    stringBuilder.append('/');

    stringBuilder.append(fileName);

    String path;
    path = stringBuilder.toString();

    if (!paths.add(path)) {
      String msg;
      msg = """
            Path was already registered:

                %s
            """.formatted(path);

      throw new IllegalArgumentException(msg);
    }

    return path;
  }

  private void addByClass(Object o) {
    Class<?> key;
    key = o.getClass();

    Object existing;
    existing = byClassMap.get(key);

    if (existing != null) {
      String msg;
      msg = """
            An object was already registered:

                %s

            for the key:

                %s
            """.formatted(existing, key);

      throw new IllegalArgumentException(msg);
    }

    byClassMap.put(key, o);

    objects.add(o);

    if (o instanceof SiteComponent c) {
      c.configure(context);
    }
  }

  private void postSiteGeneration() {
    for (Object o : objects) {
      if (o instanceof SiteResourceHolder h) {
        h.releaseResources();
      }
    }
  }

  private String toPath(String fileName) {
    return toPath("", fileName);
  }

  private URL url(Class<?> clazz, String resourceName) {
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

  public interface Context {

    <T> T getObject(Class<? extends T> key);

    <T> ImmutableList<T> getObjectsByType(Class<? extends T> key);

  }

  public interface RenderingOption {

  }

}
