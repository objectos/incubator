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
      Welcome to the Objectos documentation.""");

    p("""
      Objectos is a suite of open-source Java libraries. In this first public release
      it provides a type-safe logging API for Java applications.
      It does not provide an implementation for this API yet.""");

    p("""
      One can safely say Objectos is under construction.""");

    h2("Facts");

    p("""
      Here we present some facts about the Objectos suite of Java libraries.
      We hope these encourage you to:""");

    ul(
      li("adopt Objectos in your current Java project;"),
      li("consider using Objectos in your next Java project; or"),
      li("bookmark this project and frequently return.")
    );

    h3("Built from scratch");

    p("""
      All of the Objectos' Java libraries are built from scratch.
      While we believe it to be a positive thing, we understand if this fact
      discourages you. The reasons why we think it is a positive thing are listed
      below:""");

    ul(
      li("""
         we do not depend on a third-party for addresing vulnerabilities.
         Objectos' libraries have zero third-party dependencies; and"""),
      li("""
         greater control on thread creation, object instantiation and
         performance.""")
    );

    h3("CPU and memory cost-concious");

    p("""
      We are not performance experts.""");

    p("""
      We believe that applications should be able to run in appropriately
      sized servers. US dollar or Euro based prices for cloud computing can
      be costly to small and medium sized businesses in countries with a
      currency like the Brazilian Real.""");

    h3("Embrace current JDKs");

    p(t("""
        We hope to become a "boring" technology. Since this is a somewhat vague
        definition, here's a quote from the\040"""),
      a(t("SQLite documentation"), href("https://sqlite.org/lts.html")),
      t(":"));

    blockquote(
      """
      Nobody is completely immune to trends and fads,
      but the SQLite developers work hard to avoid being sucked into the
      latest programming fashion. Our aim is to produce timeless code that will be readable,
      understandable, and maintainable by programmers who have not yet been born.""");

    p("""
      With that said, OpenJDK has been offering both memory and performance improvements
      with every release. We should embrace those gains.""");

    h3("Documentation is a first-class citizen");

    p("""
      We do not claim to have good nor great documentation.""");

    p("""
      We have learned the hard way that a project does not exist without proper
      documentation. We will work, to the best of our abilities""");

    h3("We eat our own dog food");

    p("""
      This documentation site is a static website created with Objectos CSS, Objectos HTML
      and Objectos SSG.
      Please note that these libraries are not publicly released yet.
      But you can find the source code in our incubator git repository.""");

    h2("License");

    p(t(
      """
      Objectos is open-source software licensed under the\040"""),
      a(t("Apache License, version 2.0"), href("https://www.apache.org/licenses/LICENSE-2.0")),
      t(".")
    );
  }

  @Override
  protected final ThisStyleSheet thisStyleSheet() {
    return new ThisStyleSheet() {
      @Override
      protected final void definition() {
        super.definition();

        articleUl();
      }
    };
  }

}