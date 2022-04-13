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
import objectos.docs.ui.DocsPage;

final class Index extends DocsPage {

  @Override
  protected final void main0() {
    article(
      h1("Documentation for Objectos developers"),

      p("""
        Welcome to the Objectos documentation.
        Here you will find tutorials, topic guides, how-to guides and
        the reference manuals of individual libraries."""),

      section(
        h2("First steps"),

        p("""
          Start here if you are new to Objectos."""),

        div(
          h3("From scratch"),

          ul(
            li(a(IntroDir.OVERVIEW, "Overview")),

            li(a(IntroDir.INSTALLATION, "Installation"))
          )
        )
      )
    );
  }

  @Override
  protected final void register() {
    nextPage = IntroDir.INDEX;

    titleText = "Index";
  }

}