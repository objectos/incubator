/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs.intro;

import br.com.objectos.be.site.HasHref;
import br.com.objectos.be.site.SiteDirectory;
import objectos.docs.ui.ArticlePage;

public final class IntroDir extends SiteDirectory {

  public static final Class<? extends ArticlePage> INDEX = Index.class;

  public static final Class<? extends HasHref> INSTALLATION = Installation.class;

  public static final Class<? extends HasHref> OVERVIEW = Overview.class;

  @Override
  protected final void configure() {
    addPage("index.html", new Index());
    addPage("overview.html", new Overview());
    addPage("install.html", new Installation());
  }

}