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

public abstract class Site {

  private Generator gen;

  protected Site() {}

  public final synchronized void configure(Generator generator) {
    this.gen = Checks.checkNotNull(generator, "generator == null");

    try {
      configure();
    } finally {
      this.gen = null;
    }
  }

  protected final void addDirectory(String path, SiteDirectory directory) {
    gen().addDirectory(path, directory);
  }

  protected final void addObject(Object object) {
    gen().addObject(object);
  }

  protected final void addPage(String fileName, SitePage page) {
    gen().addPage(fileName, page);
  }

  protected final void addResource(String resourceName) {
    gen().addResource(getClass(), resourceName);
  }

  protected final void addStyleSheet(String fileName, SiteStyleSheet sheet) {
    gen().addStyleSheet(fileName, sheet);
  }

  protected abstract void configure();

  protected final Generator gen() {
    Checks.checkState(gen != null, "Please call this under the configure() method");

    return gen;
  }

  public interface Generator extends ObjectLocator {

    void addDirectory(String path, SiteDirectory directory);

    void addObject(Object object);

    void addPage(String fileName, SitePage page);

    void addResource(Class<?> contextClass, String resourceName);

    void addStyleSheet(String fileName, SiteStyleSheet sheet);

    void generate();

    String getBaseHref();

    boolean isDevelopment();

    boolean isProduction();

  }

  public interface ObjectLocator {

    <T> T getObject(Class<? extends T> key);

    <T> ImmutableList<T> getObjectsByType(Class<? extends T> key);

  }

}
