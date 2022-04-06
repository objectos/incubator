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
package objectos.docs;

import br.com.objectos.be.site.HasHref;
import br.com.objectos.be.site.SiteDirectory;
import objectos.docs.ui.LeftDrawer;
import objectos.docs.ui.TopBar;
import objectos.docs.v0001.V0001Directory;

public final class DocsDirectory extends SiteDirectory {

  public static final Class<? extends HasHref> INDEX = Index.class;

  @Override
  protected final void configure() {
    putInstance(new LeftDrawer());
    putInstance(new TopBar());
    putInstance(new StringBuilder());

    addResource("prism.css");
    addResource("prism.js");

    addPage("index.html", new Index());

    addDirectory("latest", new V0001Directory());
  }

}