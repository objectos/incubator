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
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;

final class TopNavbar extends SiteFragment {

  private static final ClassSelector _CURRENT = Css.randomDot(3);

  private static final IdSelector _HEADER = Css.randomHash(3);

  private static final IdSelector _HEADER1 = Css.randomHash(3);

  private static final IdSelector _HEADER2 = Css.randomHash(3);

  private static final IdSelector _MENU_CLOSE = Css.randomHash(3);

  private static final IdSelector _MENU_OPEN = Css.randomHash(3);

  private static final IdSelector _MENU_SVG = Css.randomHash(3);

  final StyleSheet css = new AbstractStyleSheet() {
    @Override
    protected final void definition() {
      style(
        _CURRENT,

        color(Colors.INDIGO5)
      );

      style(
        _HEADER1,

        alignItems(center),
        backgroundColor(Colors.GRAY0),
        borderBottom(Spacing.PX, solid, Colors.GRAY2),
        display(flex),
        height(Spacing.V16),
        padding(Spacing.V0, Spacing.V06)
      );

      style(
        _HEADER2,

        backgroundColor(white),
        bottom(zero()),
        display(none),
        padding(Spacing.V06),
        position(absolute),
        top(Spacing.V16),
        width(pct(100))
      );

      style(
        _MENU_CLOSE,

        display(none)
      );

      style(
        _MENU_OPEN,

        display(flex)
      );

      style(
        _MENU_CLOSE, or(), _MENU_OPEN,

        alignItems(center),
        outline(none)
      );

      style(
        _MENU_SVG,

        display(inlineBlock),
        height(Spacing.V04),
        marginRight(Spacing.V02),
        width(Spacing.V04)
      );
    }
  };

  final String js
      = """
        /* TopNavbar.java */
        function onClick(id, listener) {
          const el = document.getElementById(id);

          el.addEventListener("click", listener);
        }

        function setStyle(id, propName, value) {
          const el = document.getElementById(id);

          el.style[propName] = value;
        }

        function menuCloseClicked(event) {
          setStyle("{body}", "overflowY", "auto");
          setStyle("{menuClose}", "display", "none");
          setStyle("{menuOpen}", "display", "flex");
          setStyle("{header2}", "display", "none");
        }

        function menuOpenClicked(event) {
          setStyle("{body}", "overflowY", "hidden");
          setStyle("{menuClose}", "display", "flex");
          setStyle("{menuOpen}", "display", "none");
          setStyle("{header2}", "display", "block");
        }

        function domLoaded() {
          onClick("{menuClose}", menuCloseClicked);
          onClick("{menuOpen}", menuOpenClicked);
        }

        window.addEventListener('DOMContentLoaded', domLoaded);
        """
        .replace("\n", "")
        .replace("{body}", Index._BODY.id())
        .replace("{header2}", _HEADER2.id())
        .replace("{menuClose}", _MENU_CLOSE.id())
        .replace("{menuOpen}", _MENU_OPEN.id());

  private String title;

  public final void setTitle(String title) {
    this.title = title;
  }

  @Override
  protected final void definition() {
    header(
      _HEADER,

      div(
        _HEADER1,

        button(
          _MENU_CLOSE,

          type("button"),

          svg(
            _MENU_SVG,

            xmlns("http://www.w3.org/2000/svg"), viewBox("0 0 20 20"), fill("currentColor"),
            path(
              fillRule("evenodd"),
              d("M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"),
              clipRule("evenodd")
            )
          ),

          span(title)
        ),

        button(
          _MENU_OPEN,

          type("button"),

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
      ),

      div(
        _HEADER2,

        nav(
          a(
            _CURRENT,

            href(Index.class),

            div("Home")
          )
        )
      )
    );
  }

}