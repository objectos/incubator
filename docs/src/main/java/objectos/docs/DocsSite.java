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

import objectos.docs.next.Next;
import objectos.docs.ui.Breadcrumbs;
import objectos.docs.ui.Md;
import objectos.docs.ui.NextBanner;
import objectos.docs.ui.PageSwitcher;
import objectos.docs.ui.Pages;
import objectos.docs.ui.TableOfContents;
import objectos.docs.ui.VersionHolder;
import objectos.docs.v0001.V0001;
import objectos.docs.v0002.V0002;
import objectos.ssg.Site;
import objectos.ssg.SitePath;

public final class DocsSite extends Site {

  public static final Class<? extends SitePath> INDEX = V0002.INDEX;

  public static final Class<? extends SitePath> WHAT = V0002.WHAT;

  public static final Class<? extends SitePath> VERSIONS = Versions.class;

  public static final String VERSION = V0002.VERSION;

  @Override
  protected final void configure() {
    addObject(new Breadcrumbs());
    addObject(new Md());
    addObject(new NextBanner());
    addObject(new Pages());
    addObject(new PageSwitcher());
    addObject(new StringBuilder());
    addObject(new TableOfContents());
    addObject(new VersionHolder());

    addPage("versions.html", new Versions());

    addDirectory("next", new Next());
    addDirectory("0.1", new V0001());
    addDirectory("0.2", new V0002());
  }

}
