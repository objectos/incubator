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
import br.com.objectos.http.media.MediaType;
import java.io.IOException;
import objectos.lang.Check;

public abstract class SiteDirectory
    implements
    SiteResourceHolder,
    SiteWriteable {

  private String path;

  private SiteConfiguration site;

  protected SiteDirectory() {}

  @Override
  public void releaseResources() {
    path = null;

    site = null;
  }

  @Override
  public final void writeTo(SiteWriter writer) throws IOException {
    writeStart();
  }

  protected final <T extends SiteDirectory> T addDirectory(String fileName, T directory) {
    String path;
    path = toPath(fileName);

    return site.addDirectory0(directory, path);
  }

  protected final <T> T addObject(T object) {
    return site.addObject(object);
  }

  protected final <T extends SitePage> T addPage(String fileName, T page) {
    String path;
    path = toPath(fileName);

    return site.addPage0(page, path);
  }

  protected final SitePath addResource(String resourceName) {
    String path;
    path = toPath(resourceName);

    Class<?> contextClass;
    contextClass = getClass();

    return site.addResource0(path, contextClass, resourceName, null);
  }

  @Deprecated
  protected final SitePath addResource(String fileName, String resourceName) {
    String path;
    path = toPath(fileName);

    Class<?> contextClass;
    contextClass = getClass();

    return site.addResource0(path, contextClass, resourceName, null);
  }

  @Deprecated
  protected final SitePath addResource(String fileName, String resourceName, MediaType mediaType) {
    String path;
    path = toPath(fileName);

    Class<?> contextClass;
    contextClass = getClass();

    return site.addResource0(path, contextClass, resourceName, mediaType);
  }

  protected final <T extends SiteStyleSheet> T addStyleSheet(String fileName, T sheet) {
    String path;
    path = toPath(fileName);

    return site.addStyleSheet0(sheet, path);
  }

  protected abstract void configure();

  protected final <T> T getObject(Class<? extends T> key) {
    return site.getObject(key);
  }

  protected final <T>
      ImmutableList<T> getObjectsByType(Class<? extends T> type) {
    return site.getObjectsByType(type);
  }

  protected void writeStart() {}

  final void set(String path, SiteConfiguration site) {
    Check.state(this.path == null, "path was already set");
    Check.state(this.site == null, "site was already set");

    this.path = path;
    this.site = site;
  }

  private String toPath(String fileName) {
    return site.toPath(path, fileName);
  }

}