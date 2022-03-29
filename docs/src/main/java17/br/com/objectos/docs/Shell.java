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

final class Shell extends SiteFragment {

  private static final IdSelector _BD = Css.randomHash(3);

  private static final IdSelector _SHELL = Css.randomHash(3);

  final StyleSheet css = new AbstractStyleSheet() {
    @Override
    protected final void definition() {
      Shell outer;
      outer = Shell.this;

      install(outer.header.css);

      install(outer.leftPanel.css);

      style(
        _BD,

        height(pct(100)),
        overflowY(hidden)
      );

      style(
        _SHELL,

        display(flex),
        flexDirection(column),
        height(pct(100))
      );
    }
  };

  final String js
      = """
        /* Shell.java */
        (function() {
          function onClick(id, listener) {
            const el = document.getElementById(id);

            el.addEventListener("click", listener);
          }

          function setStyle(id, propName, value) {
            const el = document.getElementById(id);

            el.style[propName] = value;
          }

          function menuCloseClicked(event) {
            setStyle("{body}", "overflowY", null);
            setStyle("{menuClose}", "display", null);
            setStyle("{menuOpen}", "display", null);
            setStyle("{leftPanel}", "display", null);
          }

          function menuOpenClicked(event) {
            setStyle("{body}", "overflowY", "hidden");
            setStyle("{menuClose}", "display", "flex");
            setStyle("{menuOpen}", "display", "none");
            setStyle("{leftPanel}", "display", "block");
          }

          function domLoaded() {
            onClick("{menuClose}", menuCloseClicked);
            onClick("{menuOpen}", menuOpenClicked);
          }

          window.addEventListener('DOMContentLoaded', domLoaded);
        })();
        """
        .replace("\n", "")
        .replace("{body}", Index._BODY.id())
        .replace("{leftPanel}", ShellLeftPanel._ID.id())
        .replace("{menuClose}", ShellHeader._MENU_CLOSE.id())
        .replace("{menuOpen}", ShellHeader._MENU_OPEN.id());

  private DocsPage current;

  private final ShellHeader header;

  private final ShellLeftPanel leftPanel;

  Shell(ShellHeader header, ShellLeftPanel leftPanel) {
    this.header = header;

    this.leftPanel = leftPanel;
  }

  public final void setCurrent(DocsPage page) {
    current = page;

    header.setCurrent(page);

    leftPanel.setCurrent(page);
  }

  @Override
  protected final void definition() {
    div(
      _SHELL,

      f(header),

      f(leftPanel),

      div(
        _BD,

        f(current::bd)
      )
    );
  }

}