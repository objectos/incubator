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
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.spi.type.TableValue;
import br.com.objectos.html.spi.type.TbodyValue;
import java.util.List;
import objectos.docs.style.Colors;
import objectos.docs.style.Spacing;

final class VersionsPage extends BaseTemplate {

  private static final ClassSelector DATE = Css.randomDot(3);

  private static final ClassSelector TITLE = Css.randomDot(3);

  private final StyleSheet css = new AbstractStyleSheet() {
    @Override
    protected final void definition() {
      install(new ArticleCss());

      style(h1,

        margin(Spacing.V16, zero()));

      style(table, sp(), td,

        paddingTop(Spacing.V02), verticalAlign(baseline));

      style(thead, sp(), th,

        borderBottom(px(1), solid, Colors.GRAY2));

      style(DATE,

        textAlign(center), width(Spacing.V28));

      style(TITLE,

        textAlign(left));
    }
  };

  private final String baseHref;

  private final List<Version> versions;

  VersionsPage(String baseHref, List<Version> versions) {
    this.baseHref = baseHref;

    this.versions = versions;
  }

  @Override
  final void body0() {
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

        tbody0()
      )
    );
  }

  @Override
  final StyleSheet styleSheet() { return css; }

  @Override
  final String thisTitle() { return "All Objectos releases"; }

  private TableValue tbody0() {
    var size = versions.size();

    var values = new TbodyValue[size];

    for (int i = 0; i < size; i++) {
      var version = versions.get(i);

      var href = baseHref + "/" + version.slug() + "/index.html";

      var date = version.releaseDate();

      values[i] = tr(
        td(
          TITLE,

          a(href(href), t(version.name()))
        ),

        td(
          DATE,

          t(date == null ? "unreleased" : date.toString())
        )
      );
    }

    return tbody(values);
  }

}