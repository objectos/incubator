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
import java.io.IOException;

public abstract class Site implements SiteResourceHolder {

  private SiteConfiguration configuration;

  protected Site() {}

  public final void configure(SiteConfiguration configuration) {
    Checks.checkState(this.configuration == null, "concurrent configurations are not allowed");

    this.configuration = configuration;

    try {
      configure();
    } finally {
      releaseResources();
    }
  }

  public final void generate(SiteWriter writer, RenderingOption... options) throws IOException {
    SiteGeneration generation;
    generation = SiteGeneration.create(writer, options);

    generation.addSite(this);

    generation.write();
  }

  @Override
  public void releaseResources() {
    configuration = null;
  }

  protected final <T extends SiteDirectory> T addDirectory(String fileName, T directory) {
    return configuration.addDirectory(fileName, directory);
  }

  protected final <T> T addObject(T object) {
    return configuration.addObject(object);
  }

  protected final <T extends SitePage> T addPage(String fileName, T page) {
    return configuration.addPage(fileName, page);
  }

  protected final SitePath addResource(String resourceName) {
    Class<? extends Site> contextClass;
    contextClass = getClass();

    return configuration.addResource(contextClass, resourceName);
  }

  protected final <T extends Site> T addSite(String fileName, T site) {
    return configuration.addSite(fileName, site);
  }

  protected final <T extends SiteStyleSheet> T addStyleSheet(String fileName, T sheet) {
    return configuration.addStyleSheet(fileName, sheet);
  }

  protected abstract void configure();

  final void generate0(SiteGeneration generation) throws IOException {}

  public interface Context {

    <T> T getObject(Class<? extends T> key);

    <T> ImmutableList<T> getObjectsByType(Class<? extends T> key);

  }

  public interface RenderingOption {

  }

}
