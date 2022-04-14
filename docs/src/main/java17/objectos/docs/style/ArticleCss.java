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
package objectos.docs.style;

import br.com.objectos.css.sheet.AbstractStyleSheet;

public final class ArticleCss extends AbstractStyleSheet {

  @Override
  protected final void definition() {
    // typography

    style(
      article, sp(), blockquote,

      fontStyle(italic),
      paddingLeft(Spacing.V10)
    );

    style(
      article, sp(), h1,

      fontSize(px(40)),
      fontWeight(600),
      letterSpacing(px(-0.5)),
      lineHeight(px(48)),
      marginBottom(Spacing.V04)
    );

    style(
      article, sp(), h1, plus(), p,

      marginTop(Spacing.V07)
    );

    style(
      article, sp(), h2,

      fontSize(FontSize.XL2),
      fontWeight(600),
      marginBottom(Spacing.V04),
      marginTop(Spacing.V14)
    );

    style(
      article, sp(), h3,

      fontSize(FontSize.XL),
      fontWeight(500),
      letterSpacing(px(-0.25)),
      marginBottom(Spacing.V04),
      marginTop(Spacing.V08)
    );

    style(
      article, sp(), p,

      margin(Spacing.V04, zero())
    );

    // anchors

    style(
      article, sp(), a,

      color(Colors.BLUE8)
    );

    style(
      article, sp(), a, HOVER,

      textDecoration(underline)
    );

    // code

    style(
      article, sp(), p, sp(), code,

      backgroundColor(Colors.GRAY1),
      border(px(1), solid, Colors.GRAY3),
      color(Colors.GRAY9),
      fontSize(FontSize.SM),
      fontWeight(500),
      lineHeight(FontSize.SM),
      padding(Spacing.PX, Spacing.V01)
    );

    style(
      article, sp(), pre, gt(), code,

      fontSize(FontSize.SM),
      fontWeight(500)
    );

    // lists

    style(
      article, sp(), ul,

      listStyle(disc),
      paddingLeft(Spacing.V10)
    );

    style(
      article, sp(), li,

      margin(Spacing.V03, zero())
    );
  }

}