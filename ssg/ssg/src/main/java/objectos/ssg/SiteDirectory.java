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
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.http.media.MediaType;

public abstract class SiteDirectory {

  private Configuration cfg;

  private AbstractSiteDsl dsl;

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
    throw new UnsupportedOperationException("Implement me");
  }

  protected final void addPage(String fileName, SitePage page) {
    cfg().addPage(fileName, page);
  }

  protected final void addResource(String resourceName) {
    cfg().addResource(resourceName);
  }

  protected final void addResource(String path, InputStreamSource resource) {
    cfg().addResource(path, resource);
  }

  protected final void addResource(String path, InputStreamSource resource, MediaType mediaType) {
    cfg().addResource(path, resource, mediaType);
  }

  protected final void addStyleSheet(String fileName, SiteStyleSheet sheet) {
    cfg().addStyleSheet(fileName, sheet);
  }

  protected abstract void configure();

  protected final <T> T getInstance(Class<? extends T> key) {
    return dsl.getInstance(key);
  }

  protected final <T> ImmutableList<T> getInstancesByType(Class<? extends T> type) {
    return dsl.getInstancesByType(type);
  }

  protected final void putInstance(Object value) {
    dsl.put(value);
  }

  final void acceptSiteDsl(AbstractSiteDsl dsl) {
    this.dsl = Checks.checkNotNull(dsl, "dsl == null");

    try {
      configure();
    } finally {
      this.dsl = null;
    }
  }

  final void addTemplate(String href, SitePage sitePage) {
    dsl.addTemplate(href, sitePage);
  }

  private Configuration cfg() {
    Checks.checkState(cfg != null, "Please invoke this method under the configure() method");

    return cfg;
  }

  public static interface Configuration {

    void addPage(String fileName, SitePage page);

    void addResource(String resourceName);

    void addResource(String path, InputStreamSource resource);

    void addResource(String path, InputStreamSource resource, MediaType mediaType);

    void addStyleSheet(String fileName, SiteStyleSheet sheet);

  }

}