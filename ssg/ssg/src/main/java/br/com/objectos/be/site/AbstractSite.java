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
package br.com.objectos.be.site;

import br.com.objectos.be.resource.BaseUrl;
import br.com.objectos.core.object.Checks;

public abstract class AbstractSite implements Site {

  private SiteDsl dsl;

  protected AbstractSite() {}

  @Override
  public final void acceptSiteDsl(SiteDsl dsl) {
    this.dsl = Checks.checkNotNull(dsl, "dsl == null");

    try {
      configure();

      dsl.install();
    } finally {
      this.dsl = null;
    }
  }

  protected final void addDirectory(SiteDirectory directory) {
    dsl.addDirectory(directory);
  }

  protected final void addDirectory(String path, SiteDirectory directory) {
    dsl.addDirectory(path, directory);
  }

  protected abstract void configure();

  protected final SiteDsl dsl() {
    Checks.checkState(dsl != null, "Please call this under the configure() method");

    return dsl;
  }

  protected final BaseUrl getBaseUrl(String path) {
    BaseUrl baseUrl = dsl().getBaseUrl();

    return baseUrl.getChild(path);
  }

  protected final void install(BaseUrl baseUrl, Directory directory) {
    Checks.checkNotNull(baseUrl, "baseUrl == null");
    Checks.checkNotNull(directory, "directory == null");

    DirectoryDsl moduleDsl;
    moduleDsl = dsl().getDirectoryDsl(baseUrl);

    directory.acceptDirectoryDsl(moduleDsl);
  }

}
