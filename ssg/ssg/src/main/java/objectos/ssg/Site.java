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
import br.com.objectos.core.object.Checks;
import br.com.objectos.core.throwable.Try;
import java.io.IOException;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class Site {

  private Context context;

  private final Map<Object, Object> objects = new IdentityHashMap<>();

  private final StringBuilder stringBuilder = new StringBuilder();

  protected Site() {}

  public final synchronized void generate(Generator generator) {}

  public final void generate(SiteWriter writer, RenderingOption... options) throws IOException {
    context = new Context() {
      @SuppressWarnings("unchecked")
      @Override
      public final <T> T getObject(Class<? extends T> key) {
        Checks.checkNotNull(key, "key == null");

        Object object;
        object = objects.get(key);

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
        throw new UnsupportedOperationException("Implement me");
      }
    };

    objects.clear();

    stringBuilder.setLength(0);

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

    Collection<Object> values;
    values = objects.values();

    try {
      for (Object o : values) {
        if (o instanceof SiteWriteable w) {
          w.writeTo(writer);
        }
      }
    } finally {
      postSiteGeneration();
    }
  }

  protected final void addDirectory(String fileName, SiteDirectory directory) {}

  protected final void addObject(Object object) {}

  protected final <T extends SitePage> T addPage(String fileName, T page) {
    String path;
    path = toPath(fileName);

    return addPage0(page, path);
  }

  protected final void addResource(String resourceName) {}

  protected final <T extends SiteStyleSheet> T addStyleSheet(String fileName, T sheet) {
    String path;
    path = toPath(fileName);

    return addStyleSheet0(sheet, path);
  }

  protected abstract void configure();

  protected void generate() {}

  final String toPath(String fileName) {
    Hrefs.validateName(fileName);

    stringBuilder.setLength(0);

    stringBuilder.append('/');

    stringBuilder.append(fileName);

    return stringBuilder.toString();
  }

  private void addObject0(Object o) {
    Class<?> key;
    key = o.getClass();

    Object existing;
    existing = objects.get(key);

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

    objects.put(key, o);
  }

  private <T extends SitePage> T addPage0(T page, String path) {
    Checks.checkNotNull(page, "page == null");

    page.set(context, path);

    addObject0(page);

    return page;
  }

  private <T extends SiteStyleSheet> T addStyleSheet0(T sheet, String path) {
    Checks.checkNotNull(sheet, "sheet == null");

    sheet.set(context, path);

    addObject0(sheet);

    return sheet;
  }

  private void postSiteGeneration() {
    Collection<Object> values;
    values = objects.values();

    for (Object o : values) {
      if (o instanceof SiteLifecycle l) {
        l.postSiteGeneration();
      }
    }
  }

  public interface Configuration {

    void addDirectory(String path, SiteDirectory directory);

    void addObject(Object object);

    void addPage(String fileName, SitePage page);

    void addResource(Class<?> contextClass, String resourceName);

    void addStyleSheet(String fileName, SiteStyleSheet sheet);

  }

  public interface Context {

    <T> T getObject(Class<? extends T> key);

    <T> ImmutableList<T> getObjectsByType(Class<? extends T> key);

  }

  public interface Generator extends Configuration, Context {

    void generate();

  }

  public interface RenderingOption {

  }

}
