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

import objectos.docs.ui.ArticlePage;

final class Overview extends ArticlePage {

  @Override
  public final String topBarTitle() {
    return "Overview";
  }

  @Override
  protected final void contents() {
    h1("Overview");

    p("""
      Objectos is a suite of Java libraries. In this first public release
      it provides a type-safe logging API for Java applications.
      It does not provide an implementation for this API yet.
      So one can safely say Objectos is under construction.
      In the long term, the intention is to provide a platform
      for building web applications in the Java programming language.""");

    p("""
      This document tries to explain what Objectos is and some of the reasonings
      behind its design. It should give you some initial information on whether
      you should pick Objectos for your next project or not.""");

    h2("It is all about trade-offs");

    p("""
      Decisions are balacing trade-offs. It is not different when designing a software
      library. And sometimes requirements may be conflicting between """);

    h3("Beginner friendly");

    p("""
      Whether a person is new to the Java programming language or to programming in general
      Objectos should provide """);

    p("""
      The goal is to reduce onboarding time and training costs.""");

    h3("Keep experts interested");

    p("""
      Should not cost the interest of the experts.""");

    h3("");

    h3("Built from scratch");

    h2("License");

    p(t(
      """
      Objectos is open-source software licensed under the\040"""),
      a(t("Apache License, version 2.0"), href("https://www.apache.org/licenses/LICENSE-2.0")),
      t(".")
    );
  }

}