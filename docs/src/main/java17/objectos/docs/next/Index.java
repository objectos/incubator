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
import objectos.docs.next.logging.LoggingDir;
import objectos.docs.ui.DocsPage;

final class Index extends DocsPage {

  @Override
  protected final void configure() {
    titleText = "Home";
  }

  @Override
  protected final void main0() {
    article(
      h1("Documentation for Objectos developers"),

      p("""
        Welcome to the Objectos documentation.
        Here you will find tutorials, topic guides, how-to guides and
        the reference manuals of individual libraries."""),

      section(
        h2("I am new to Objectos"),

        p("Read these to get started with Objectos:"),

        ul(
          li(a(Intro.OVERVIEW, "What is Objectos?")),

          li(a(Intro.INSTALLATION, "Installation"))
        )
      ),

      section(
        h2("Core libraries"),

        p("""
          Built from scratch core utilities for Java applications.
          Core utilities are those that most Java applications will require:"""),

        ul(
          li(a(LoggingDir.INDEX, "Logging"))
        )
      )
    );
  }

}