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

  private Generator generator;

  protected Site() {}

  public final synchronized void generate(Generator generator) {
    Checks.checkState(this.generator == null, "A generator was already set");

    this.generator = Checks.checkNotNull(generator, "generator == null");

    try {
      configure();
    } finally {
      this.generator = null;
    }

    try {
      generate();
    } finally {

    }

    generator.generate();
  }

  protected final void addDirectory(String path, SiteDirectory directory) {
    cfg().addDirectory(path, directory);
  }

  protected final void addObject(Object object) {
    cfg().addObject(object);
  }

  protected final void addPage(String fileName, SitePage page) {
    cfg().addPage(fileName, page);
  }

  protected final void addResource(String resourceName) {
    cfg().addResource(getClass(), resourceName);
  }

  protected final void addStyleSheet(String fileName, SiteStyleSheet sheet) {
    cfg().addStyleSheet(fileName, sheet);
  }

  protected final Configuration cfg() {
    Checks.checkState(generator != null, "Please call this under the configure() method");

    return generator;
  }

  protected abstract void configure();

  protected void generate() {}

  public interface Configuration {

    void addDirectory(String path, SiteDirectory directory);

    void addObject(Object object);

    void addPage(String fileName, SitePage page);

    void addResource(Class<?> contextClass, String resourceName);

    void addStyleSheet(String fileName, SiteStyleSheet sheet);

  }

  public interface Context {

    String getHref(Class<?> key);

    <T> T getObject(Class<? extends T> key);

    <T> ImmutableList<T> getObjectsByType(Class<? extends T> key);

  }

  public interface Generator extends Configuration, Context {

    void generate();

  }

}
