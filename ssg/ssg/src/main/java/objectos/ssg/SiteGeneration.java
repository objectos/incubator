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
import br.com.objectos.http.media.MediaType;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;
import objectos.ssg.Site.RenderingOption;

final class SiteGeneration implements Site.Context {

  final StringBuilder stringBuilder = new StringBuilder();

  private final Map<Class<?>, Object> byClassMap = new MutableMap<>();

  private final List<Object> objects = new MutableList<>();

  private final Set<String> paths = new MutableSet<>();

  private ThisSiteRegistry registry = NoOpSiteRegistry.INSTANCE;

  private String sitePath = "";

  private final SiteWriter writer;

  public SiteGeneration(SiteWriter writer) {
    this.writer = writer;
  }

  public static SiteGeneration create(SiteWriter writer, RenderingOption[] options) {
    Checks.checkNotNull(writer, "writer == null");
    Checks.checkNotNull(options, "options == null");

    return new SiteGeneration(writer);
  }

  public final <T extends SiteDirectory> T addDirectory(String fileName, T directory) {
    String path;
    path = toPath(fileName);

    return addDirectory0(directory, path);
  }

  public final <T> T addObject(T object) {
    addByClass(object);

    return object;
  }

  public final <T extends SitePage> T addPage(String fileName, T page) {
    String path;
    path = toPath(fileName);

    return addPage0(page, path);
  }

  public final SitePath addResource(Class<?> contextClass, String resourceName) {
    String path;
    path = toPath(resourceName);

    return addResource0(path, contextClass, resourceName, null);
  }

  public final void addSite(Site site) {
    addByClass(site);

    site.configure(this);
  }

  public final <T extends Site> T addSite(String fileName, T site) {
    String oldPath;
    oldPath = sitePath;

    try {
      sitePath = toPath(fileName);

      site.configure(this);

      return site;
    } finally {
      sitePath = oldPath;
    }
  }

  public final <T extends SiteStyleSheet> T addStyleSheet(String fileName, T sheet) {
    String path;
    path = toPath(fileName);

    return addStyleSheet0(sheet, path);
  }

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

  public final void write() throws IOException {
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

  final <T extends SiteDirectory> T addDirectory0(T directory, String path) {
    directory.set(path, this);

    registry.visitSiteDirectory(directory);

    directory.configure();

    addByClass(directory);

    registry.postVisitSiteDirectory(directory);

    return directory;
  }

  final <T extends SitePage> T addPage0(T page, String path) {
    Checks.checkNotNull(page, "page == null");

    addByClass(page);

    page.setPath(path);

    registry.visitSitePage(page);

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

  final void postSiteGeneration() {
    for (Object o : objects) {
      if (o instanceof SiteResourceHolder h) {
        h.releaseResources();
      }
    }

    byClassMap.clear();

    objects.clear();

    paths.clear();

    stringBuilder.setLength(0);

    sitePath = null;
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
      c.configure(this);
    }

    if (o instanceof SiteVisitor r) {
      registry = registry.add(r);
    }
  }

  private String toPath(String fileName) {
    return toPath(sitePath, fileName);
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

  private static class NoOpSiteRegistry extends ThisSiteRegistry {
    static final ThisSiteRegistry INSTANCE = new NoOpSiteRegistry();

    @Override
    public final void postVisitSiteDirectory(SiteDirectory directory) {}

    @Override
    public final void visitSiteDirectory(SiteDirectory directory) {}

    @Override
    public final void visitSitePage(SitePage page) {}

    @Override
    final ThisSiteRegistry add(SiteVisitor r) {
      SimpleSiteRegistry registry;
      registry = new SimpleSiteRegistry();

      return registry.add(r);
    }
  }

  private static class SimpleSiteRegistry extends ThisSiteRegistry {
    private final List<SiteVisitor> values = new MutableList<>();

    @Override
    public final void postVisitSiteDirectory(SiteDirectory directory) {
      for (int i = 0, size = values.size(); i < size; i++) {
        SiteVisitor r;
        r = values.get(i);

        r.postVisitSiteDirectory(directory);
      }
    }

    @Override
    public final void visitSiteDirectory(SiteDirectory directory) {
      for (int i = 0, size = values.size(); i < size; i++) {
        SiteVisitor r;
        r = values.get(i);

        r.visitSiteDirectory(directory);
      }
    }

    @Override
    public final void visitSitePage(SitePage page) {
      for (int i = 0, size = values.size(); i < size; i++) {
        SiteVisitor r;
        r = values.get(i);

        r.visitSitePage(page);
      }
    }

    @Override
    ThisSiteRegistry add(SiteVisitor r) {
      values.add(r);

      return this;
    }
  }

  private static abstract class ThisSiteRegistry implements SiteVisitor {
    abstract ThisSiteRegistry add(SiteVisitor r);
  }

}