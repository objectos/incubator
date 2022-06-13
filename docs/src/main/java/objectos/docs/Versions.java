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
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.StyleSheet;
import objectos.docs.next.Next;
import objectos.docs.style.Colors;
import objectos.docs.style.Spacing;
import objectos.docs.ui.DocsPage;
import objectos.docs.ui.Pages;
import objectos.docs.v0001.V0001;
import objectos.docs.v0002.V0002;
import objectos.ssg.SiteStyleSheet;
import objectos.util.MutableList;

final class Versions extends DocsPage implements Pages.IgnoreMe {

  private static final ClassSelector DATE = Css.randomDot(3);

  private static final ClassSelector TITLE = Css.randomDot(3);

  private final StyleSheet css = new SiteStyleSheet() {
    @Override
    protected final void definition() {
      style(
        h1,

        margin(Spacing.V16, zero())
      );

      style(
        table, sp(), td,

        paddingTop(Spacing.V02),
        verticalAlign(baseline)
      );

      style(
        thead, sp(), th,

        borderBottom(px(1), solid, Colors.GRAY2)
      );

      style(
        DATE,

        textAlign(center),
        width(Spacing.V28)
      );

      style(
        TITLE,

        textAlign(left)
      );
    }
  };

  @Override
  protected final void body0() {
    article(
      header(
        h1("Versions")
      ),

      p("Choose the version of the Objectos documentation from the list below."),

      table(
        thead(
          tr(
            th(
              TITLE,

              t("Version")
            ),

            th(
              DATE,

              t("Release date")
            )
          )
        ),

        tbody(
          tr(
            td(
              TITLE,

              a(href(Next.INDEX), t("0.3.0-SNAPSHOT"))
            ),

            td(
              DATE,

              t("unreleased")
            )
          ),

          tr(
            td(
              TITLE,

              a(href(V0002.INDEX), t("0.2.0"))
            ),

            td(
              DATE,

              t("2022-06-13")
            )
          ),

          tr(
            td(
              TITLE,

              a(href(V0001.INDEX), t("0.1.0"))
            ),

            td(
              DATE,

              t("2022-05-16")
            )
          )
        )
      )
    );
  }

  @Override
  protected final MutableList<StyleSheet> styleSheets() {
    MutableList<StyleSheet> sheets;
    sheets = super.styleSheets();

    sheets.add(css);

    return sheets;
  }

}