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
import br.com.objectos.http.media.MediaType;

public abstract class SiteDirectory {

  private Configuration cfg;

  protected SiteDirectory() {}

  public final void configure(Configuration configuration) {
    this.cfg = Checks.checkNotNull(configuration, "configuration == null");

    try {
      configure();
    } finally {
      this.cfg = null;
    }
  }

  protected void acceptHrefBuilder(StringBuilder href, String name) {
    href.append(name);

    href.append('/');
  }

  protected final void addDirectory(String name, SiteDirectory directory) {
    cfg().addDirectory(name, directory);
  }

  protected final void addPage(String fileName, SitePage page) {
    cfg().addPage(fileName, page);
  }

  protected final void addResource(String resourceName) {
    cfg().addResource(resourceName);
  }

  @Deprecated
  protected final void addResource(String path, String resourceName) {
    cfg().addResource(path, resourceName);
  }

  @Deprecated
  protected final void addResource(String path, String resourceName, MediaType mediaType) {
    cfg().addResource(path, resourceName, mediaType);
  }

  protected final void addStyleSheet(String fileName, SiteStyleSheet sheet) {
    cfg().addStyleSheet(fileName, sheet);
  }

  protected abstract void configure();

  protected final <T> T getInstance(Class<? extends T> key) {
    throw new UnsupportedOperationException("Implement me");
  }

  protected final <T> ImmutableList<T> getInstancesByType(Class<? extends T> type) {
    throw new UnsupportedOperationException("Implement me");
  }

  protected final void putInstance(Object value) {
    if (value instanceof SiteFragment f) {
      cfg().addFragment(f);
    }
  }

  private Configuration cfg() {
    Checks.checkState(cfg != null, "Please invoke this method under the configure() method");

    return cfg;
  }

  public static interface Configuration {

    void addDirectory(String name, SiteDirectory directory);

    void addFragment(SiteFragment fragment);

    void addPage(String fileName, SitePage page);

    void addResource(String resourceName);

    void addResource(String path, String resourceName);

    void addResource(String path, String resourceName, MediaType mediaType);

    void addStyleSheet(String fileName, SiteStyleSheet sheet);

  }

}