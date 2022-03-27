/*
 * Copyright (C) 2022-2022 Objectos Software LTDA.
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

import br.com.objectos.be.site.SiteFragment;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;

final class TopNavbar extends SiteFragment {

  private static final IdSelector _BACKGROUND = Css.randomHash(3);

  private static final IdSelector _HEADER = Css.randomHash(3);

  private static final IdSelector _MENU_BTN = Css.randomHash(3);

  private static final IdSelector _MENU_SVG = Css.randomHash(3);

  final StyleSheet css = new AbstractStyleSheet() {
    @Override
    protected final void definition() {
      style(
        _BACKGROUND,

        backgroundColor(Colors.GRAY0),
        borderBottom(Spacing.PX, solid, Colors.GRAY2),
        height(Spacing.V16)
      );

      style(
        _HEADER,

        alignItems(center),
        display(flex),
        height(pct(100)),
        padding(Spacing.V0, Spacing.V04)
      );

      style(
        _MENU_BTN,

        alignItems(center),
        display(flex)
      );

      style(
        _MENU_SVG,

        display(inlineBlock),
        height(Spacing.V04),
        margin(zero(), Spacing.V02),
        width(Spacing.V04)
      );
    }
  };

  private String title;

  public final void setTitle(String title) {
    this.title = title;
  }

  @Override
  protected final void definition() {
    div(
      _BACKGROUND,

      header(
        _HEADER,

        button(
          _MENU_BTN,

          svg(
            _MENU_SVG,

            xmlns("http://www.w3.org/2000/svg"), viewBox("0 0 20 20"), fill("currentColor"),
            path(
              fillRule("evenodd"),
              d("M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"),
              clipRule("evenodd")
            )
          ),

          span(title)
        )
      )
    );
  }

}