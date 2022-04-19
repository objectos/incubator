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

public abstract class SiteDirectory implements SiteLifecycle {

  private String path;

  private Site site;

  protected SiteDirectory() {}

  @Override
  public final void postSiteGeneration() {
    path = null;

    site = null;
  }

  protected final void addDirectory(String fileName, SiteDirectory directory) {
    throw new UnsupportedOperationException("Implement me");
  }

  protected final void addObject(Object object) {
    throw new UnsupportedOperationException("Implement me");
  }

  protected final <T extends SitePage> T addPage(String fileName, T page) {
    String path;
    path = toPath(fileName);

    return site.addPage0(page, path);
  }

  protected final void addResource(String resourceName) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Deprecated
  protected final void addResource(String path, String resourceName) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Deprecated
  protected final void addResource(String path, String resourceName, MediaType mediaType) {
    throw new UnsupportedOperationException("Implement me");
  }

  protected final <T extends SiteStyleSheet> T addStyleSheet(String fileName, T sheet) {
    String path;
    path = toPath(fileName);

    return site.addStyleSheet0(sheet, path);
  }

  protected abstract void configure();

  protected final <T> T getObject(Class<? extends T> key) {
    throw new UnsupportedOperationException("Implement me");
  }

  protected final <T>
      ImmutableList<T> getObjectsByType(Class<? extends T> type) {
    throw new UnsupportedOperationException("Implement me");
  }

  final void set(String path, Site site) {
    Checks.checkState(this.path == null, "path was already set");
    Checks.checkState(this.site == null, "site was already set");

    this.path = path;
    this.site = site;

    configure();
  }

  private String toPath(String fileName) {
    return site.toPath(path, fileName);
  }

}