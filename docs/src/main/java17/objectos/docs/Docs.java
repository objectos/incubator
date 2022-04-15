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
import objectos.docs.intro.IntroDir;
import objectos.docs.logging.LoggingDir;
import objectos.docs.ui.Locator;
import objectos.docs.ui.Md;
import objectos.docs.ui.PageSwitcher;

public final class Docs extends SiteDirectory {

  public static final Class<? extends HasHref> INDEX = Index.class;

  @Override
  protected final void configure() {
    Locator l;
    l = this::locator;

    putInstance(new Md(l));
    putInstance(new StringBuilder());

    Index index;
    index = new Index();

    addPage("index.html", index);

    addDirectory("intro", new IntroDir());
    addDirectory("logging", new LoggingDir());

    PageSwitcher switcher;
    switcher = new PageSwitcher(l);

    switcher.load(index);

    putInstance(switcher);
  }

  private <T> T locator(Class<? extends T> key) {
    return getInstance(key);
  }

}