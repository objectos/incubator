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
package br.com.objectos.docs;

import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.docs.ui.DocsPage;
import br.com.objectos.docs.v0001.V0001Directory;

final class Index extends DocsPage {

  private static final ClassSelector _BLOCK = Css.randomDot(3);

  private static final IdSelector _PRIMARY = Css.randomHash(3);

  @Override
  public final String topBarTitle() {
    return "Home";
  }

  @Override
  protected final ThisStyleSheet thisStyleSheet() {
    return new ThisStyleSheet() {
      @Override
      protected final void definition() {
        super.definition();

        style(
          _BLOCK,

          padding(Spacing.V06)
        );

        style(
          _BLOCK, sp(), a, HOVER,

          textDecoration(underline)
        );

        style(
          _BLOCK, sp(), div,

          marginBottom(Spacing.V04)
        );

        style(
          _BLOCK, sp(), h2,

          fontSize(FontSize.XL2),
          fontWeight(500),
          letterSpacing(px(-0.25)),
          marginBottom(Spacing.V04)
        );

        style(
          _PRIMARY,

          backgroundColor(Colors.INDIGO6),
          color(white)
        );
      }
    };
  }

  @Override
  protected final void uiMain() {
    header(
      h1("Develop Java applications with Objectos"),

      p("""
        Explore our tutorials, guides and reference materials to get you
        started with Objectos. Please note this site is under construction:
        our goal is for developers of all experience levels to find what they need.""")
    );

    section(
      div(
        _BLOCK,
        _PRIMARY,

        h2("Get Started"),

        div(
          a(href(V0001Directory.QUICK_START), t("Quick start"))
        ),

        div(
          a(href("#"), t("Overview"))
        )
      )
    );
  }

}