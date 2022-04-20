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

import objectos.docs.intro.IntroDir;
import objectos.docs.logging.LoggingDir;
import objectos.docs.ui.Md;
import objectos.docs.ui.PageSwitcher;
import objectos.docs.ui.TableOfContents;
import objectos.ssg.Site;

public final class DocsSite extends Site {

  public static final Class<?> INDEX = Index.class;

  private Toc toc;

  @Override
  public final void releaseResources() {
    toc = null;
  }

  @Override
  protected final void configure() {
    addObject(new Md());
    addObject(new StringBuilder());

    PageSwitcher switcher;
    switcher = addObject(new PageSwitcher());

    toc = addPage("toc.html", new Toc());

    addPage("index.html", new Index());

    addObject(new TableOfContents());

    addDirectory("intro", new IntroDir());
    addDirectory("logging", new LoggingDir());

    switcher.load(toc);
  }

}
