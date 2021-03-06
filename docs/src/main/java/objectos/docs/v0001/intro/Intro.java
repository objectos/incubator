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
package objectos.docs.v0001.intro;

import objectos.docs.ui.DocsPage;
import objectos.ssg.SiteDirectory;

public final class Intro extends SiteDirectory {

  public static final Class<? extends DocsPage> INDEX = IntroIndex.class;

  public static final Class<? extends DocsPage> INSTALLATION = IntroInstallation.class;

  public static final Class<? extends DocsPage> OVERVIEW = IntroOverview.class;

  @Override
  protected final void configure() {
    addPage("index.html", new IntroIndex());
    addPage("overview.html", new IntroOverview());
    addPage("install.html", new IntroInstallation());
  }

}