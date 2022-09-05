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

import br.com.objectos.css.Css;
import br.com.objectos.css.framework.background.BackgroundColor;
import br.com.objectos.css.framework.border.Border;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.border.BorderTop;
import br.com.objectos.css.framework.border.Rounded;
import br.com.objectos.css.framework.flexbox.JustifyContent;
import br.com.objectos.css.framework.layout.Display;
import br.com.objectos.css.framework.sizing.MaxWidth;
import br.com.objectos.css.framework.spacing.MarginBottom;
import br.com.objectos.css.framework.spacing.MarginLeft;
import br.com.objectos.css.framework.spacing.MarginRight;
import br.com.objectos.css.framework.spacing.MarginTop;
import br.com.objectos.css.framework.spacing.MarginX;
import br.com.objectos.css.framework.spacing.Padding;
import br.com.objectos.css.framework.spacing.PaddingBottom;
import br.com.objectos.css.framework.spacing.PaddingX;
import br.com.objectos.css.framework.typography.FontSize;
import br.com.objectos.css.framework.typography.LetterSpacing;
import br.com.objectos.css.framework.typography.TextAlign;
import br.com.objectos.css.framework.typography.TextColor;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.html.element.StandardElementName;

final class VersionsTemplate extends DocsTemplate {

  static final ClassSelector DATE = Css.randomDot(3);

  static final ClassSelector TITLE = Css.randomDot(3);

  VersionsTemplate(DocsInjector injector) {
    super(injector);
  }

  @Override
  public final void headingStart(int level) {
    switch (level) {
      case 1 -> {
        tagStart(); // <h1>

        addValue0(
          FontSize.xLarge4,
          FontSize.md.xLarge5,
          LetterSpacing.tight,
          MarginTop.v14
        );
      }

      default -> super.headingStart(level);
    }
  }

  @Override
  public void preambleEnd() {
    tagEnd(StandardElementName.DIV);
  }

  @Override
  public void preambleStart() {
    tagStart(); // <div>

    addValue0(
      FontSize.xLarge,
      MarginBottom.v10,
      MarginTop.v02,
      TextColor.gray800
    );
  }

  @Override
  final void main0() {
    main(
      BorderColor.slate200,
      BorderTop.v1,
      PaddingBottom.v64,
      PaddingX.v04,
      PaddingX.md.v06,

      header(
        MarginX.auto,
        MaxWidth.screenXl,

        f(this::renderDocument)
      ),

      section(
        MarginX.auto,
        MaxWidth.screenXl,

        h2(
          FontSize.xLarge2,
          LetterSpacing.tight,
          MarginTop.v14,

          t("v0.x series")
        ),

        p(t("Our current release stream.")),

        div(
          Display.lg.flex,
          MarginTop.v10,

          f(this::v0x)
        )
      )
    );
  }

  private void v0x() {
    for (var version : Version.VALUES) {
      a(
        BackgroundColor.hover.gray100,
        Border.v1,
        BorderColor.gray300,
        Display.block,
        MarginLeft.lg.v04,
        MarginLeft.firstChild.v0,
        MarginTop.v04,
        Padding.v03,
        Padding.lg.v08,
        Rounded.medium,

        href(injector.$href(version.key("index"))),

        div(
          Display.flex,
          Display.lg.block,
          JustifyContent.between,
          TextAlign.lg.center,

          h3(
            MarginBottom.lg.v06,

            t("Version "), t(version.name)
          ),

          dl(
            Display.md.flex,
            Display.lg.block,

            dt(
              MarginRight.md.v02,
              MarginRight.lg.v0,

              t("Release date")
            ),
            dl(version.releaseDateString())
          )
        )
      );
    }
  }

}