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
package objectos.docs.next;

import objectos.docs.next.intro.Intro;
import objectos.docs.next.lang.Lang;
import objectos.docs.next.relnotes.RelNotes;
import objectos.docs.ui.DocsPage;
import objectos.docs.ui.VersionHolder;
import objectos.ssg.SiteDirectory;
import objectos.ssg.SitePath;

public final class Next extends SiteDirectory {

  public static final String VERSION = "0.2.0-SNAPSHOT";

  public static final Class<? extends DocsPage> INDEX = Index.class;

  public static final Class<? extends SitePath> WHAT = Intro.OVERVIEW;

  @Override
  protected final void configure() {
    addPage("index.html", new Index());

    addDirectory("intro", new Intro());
    addDirectory("objectos-lang", new Lang());
    addDirectory("relnotes", new RelNotes());
  }

  @Override
  protected final void writeStart() {
    VersionHolder vh;
    vh = getObject(VersionHolder.class);

    vh.set(VERSION);
  }

}