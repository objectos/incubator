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
package objectos.docs.ui;

import br.com.objectos.be.site.SiteFragment;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import objectos.docs.Breakpoint;
import objectos.docs.Colors;
import objectos.docs.DocsDirectory;
import objectos.docs.Spacing;

public final class TopBar extends SiteFragment implements DocsPageCss, DocsPageJs {

  private static final ClassSelector _BTN = Css.randomDot(3);

  private static final IdSelector _BTN_CLOSE = Css.randomHash(3);

  private static final IdSelector _BTN_OPEN = Css.randomHash(3);

  private static final IdSelector _LOGO = Css.randomHash(3);

  private static final ClassSelector _SVG = Css.randomDot(3);

  private static final IdSelector _UI_TOPBAR0 = Css.randomHash(3);

  private final StyleSheet css = new AbstractStyleSheet() {
    @Override
    protected final void definition() {
      style(
        _BTN,

        alignItems(center),
        height(pct(100)),
        marginLeft(px(-8)),
        padding(Spacing.V01),
        outline(none)
      );

      style(
        _BTN, FOCUS,

        outline(none)
      );

      style(
        _BTN, HOVER, sp(), _SVG,

        backgroundColor(Colors.GRAY1)
      );

      style(
        _BTN_CLOSE,

        display(none)
      );

      style(
        _BTN_OPEN,

        display(flex)
      );

      style(
        _LOGO,

        height(px(24)),
        width(auto)
      );

      style(
        _SVG,

        borderRadius(pct(50)),
        height(px(40)),
        padding(px(8)),
        width(px(40))
      );

      style(
        _UI_TOPBAR0,

        alignItems(center),
        borderBottom(Spacing.PX, solid, Colors.GRAY4),
        display(flex),
        flex(l(0), l(0), Spacing.V12),
        padding(Spacing.V0, Spacing.V04)
      );

      style(
        body, DocsPage._LEFT_DRAWER_OPEN, sp(), _BTN_CLOSE,

        display(flex)
      );

      style(
        body, DocsPage._LEFT_DRAWER_OPEN, sp(), _BTN_OPEN,

        display(none)
      );

      media(
        screen, minWidth(Breakpoint.LG),

        style(
          _BTN_CLOSE, or(), _BTN_OPEN,

          display(none)
        ),

        style(
          _UI_TOPBAR0,

          padding(Spacing.V0, Spacing.V06)
        ),

        style(
          body, DocsPage._LEFT_DRAWER_OPEN, sp(), _BTN_CLOSE,

          display(none)
        )
      );
    }
  };

  @Override
  public final String css() {
    return css.printMinified();
  }

  @Override
  public final String js() {
    return """
           window.addEventListener("DOMContentLoaded", () => {
             const c = document.getElementById("{close}");
             const o = document.getElementById("{open}");

             const click = () => toggleClass(ui, "{toggle}");

             c.onclick = click;
             o.onclick = click;
           });
           """
        .replace("\n", "")
        .replace("{close}", _BTN_CLOSE.id())
        .replace("{open}", _BTN_OPEN.id())
        .replace("{toggle}", DocsPage._LEFT_DRAWER_OPEN.className());
  }

  @Override
  protected final void definition() {
    header(
      _UI_TOPBAR0,

      button(
        _BTN_CLOSE,
        _BTN,

        type("button"),

        div(
          _SVG,

          svg(

            xmlns("http://www.w3.org/2000/svg"), viewBox("0 0 24 24"), height("24"), width("24"),
            path(
              d("M0 0h24v24H0z"), fill("none")
            ),
            path(
              d("M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z")
            )
          )
        )
      ),

      button(
        _BTN_OPEN,
        _BTN,

        type("button"),

        div(
          _SVG,

          svg(

            xmlns("http://www.w3.org/2000/svg"), viewBox("0 0 24 24"), height("24"), width("24"),
            path(
              d("M0 0h24v24H0z"), fill("none")
            ),
            path(
              d("M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z")
            )
          )
        )
      ),

      a(
        _LOGO,

        href(DocsDirectory.INDEX),

        svg(
          xmlns("http://www.w3.org/2000/svg"),
          viewBox("0 0 129.9 31.99"),
          height("24"),
          path(
            d("m123.1 24.81q-1.63 0-3.43-0.599-1.8-0.6-3-2.018-0.26-0.313-0.22-0.662 0.1-0.378 0.41-0.63 0.3-0.223 0.66-0.155 0.38 0.06 0.6 0.347 0.91 1.072 2.2 1.514 1.33 0.409 2.87 0.409 2.65 0 3.75-0.946 1.11-0.945 1.11-2.206 0-1.229-1.2-2.017-1.16-0.823-3.62-1.17-3.15-0.441-4.66-1.701-1.52-1.261-1.52-2.932 0-1.576 0.79-2.647 0.79-1.103 2.15-1.639 1.38-0.5673 3.15-0.5673 2.14 0 3.62 0.788 1.48 0.7566 2.39 2.049 0.27 0.314 0.16 0.693-0.1 0.348-0.48 0.568-0.3 0.158-0.66 0.09-0.34-0.06-0.6-0.378-0.78-1.008-1.92-1.513-1.1-0.5359-2.58-0.5359-1.98 0-3.08 0.8511-1.11 0.8515-1.11 2.048 0 0.82 0.44 1.45 0.48 0.599 1.52 1.04 1.03 0.41 2.77 0.662 2.36 0.314 3.71 1.071 1.39 0.757 1.96 1.765 0.6 0.977 0.6 2.112 0 1.482-0.95 2.585-0.94 1.104-2.49 1.702-1.54 0.568-3.34 0.568zm-18.18 0q-2.52 0-4.53-1.135-1.993-1.167-3.157-3.152-1.134-2.018-1.134-4.507 0-2.522 1.134-4.508 1.164-2.017 3.157-3.151 2.01-1.166 4.53-1.166t4.51 1.166q1.98 1.135 3.12 3.151 1.17 1.986 1.17 4.508 0 2.489-1.17 4.507-1.14 1.985-3.12 3.152-1.99 1.135-4.51 1.135zm0-1.767q1.99 0 3.53-0.913 1.58-0.946 2.46-2.522 0.91-1.607 0.91-3.624 0-1.986-0.91-3.561-0.88-1.608-2.46-2.522-1.54-0.9456-3.53-0.9456-1.95 0-3.53 0.9456-1.572 0.914-2.491 2.522-0.91 1.575-0.91 3.593 0 1.985 0.91 3.592 0.919 1.576 2.491 2.522 1.58 0.913 3.53 0.913zm-12.32 1.577q-1.734-0.04-3.087-0.788-1.357-0.789-2.113-2.143-0.756-1.387-0.756-3.12v-15.7q0-0.4415 0.265-0.6935 0.302-0.2835 0.691-0.2835 0.442 0 0.696 0.2835 0.302 0.2532 0.302 0.6935v15.7q0 1.827 1.134 2.994 1.134 1.134 2.929 1.134h0.691q0.443 0 0.696 0.284 0.302 0.253 0.302 0.694 0 0.409-0.302 0.693-0.265 0.253-0.696 0.253zm-8.416-14.91q-0.378 0-0.632-0.2192-0.226-0.2532-0.226-0.6304 0-0.3779 0.226-0.5987 0.265-0.2532 0.632-0.2532h8.541q0.378 0 0.597 0.2532 0.265 0.2192 0.265 0.5987 0 0.3783-0.265 0.6304-0.227 0.2192-0.597 0.2192zm-8.512 15.1q-2.456 0-4.41-1.166-1.924-1.167-3.058-3.153-1.103-1.986-1.103-4.475 0-2.522 1.039-4.508 1.039-1.985 2.869-3.12 1.829-1.166 4.191-1.166 2.018 0 3.689 0.788 1.67 0.7563 2.898 2.332 0.265 0.314 0.189 0.662-0.07 0.348-0.412 0.567-0.264 0.223-0.627 0.189-0.34-0.06-0.601-0.378-2.014-2.395-5.136-2.395-1.86 0-3.277 0.9143-1.387 0.914-2.177 2.49-0.756 1.576-0.756 3.624 0 1.986 0.851 3.593 0.85 1.576 2.332 2.522 1.515 0.912 3.499 0.912 1.293 0 2.427-0.377 1.168-0.378 2.018-1.166 0.265-0.253 0.631-0.283 0.34-0.04 0.597 0.219 0.303 0.283 0.303 0.662 0 0.348-0.265 0.599-2.302 2.113-5.737 2.113zm-17.99-0.04q-2.49 0-4.444-1.103-1.924-1.129-3.024-3.115-1.103-1.985-1.103-4.538 0-2.585 1.035-4.539 1.043-1.986 2.869-3.12 1.829-1.135 4.195-1.135 2.332 0 4.127 1.103 1.799 1.072 2.804 2.994 1.009 1.892 1.009 4.382 0 0.409-0.264 0.661-0.265 0.219-0.661 0.219h-13.86v-1.637h14.37l-1.384 1.04q0.08-2.049-0.695-3.624-0.756-1.577-2.173-2.459-1.387-0.9139-3.277-0.9139-1.799 0-3.216 0.9139-1.387 0.882-2.207 2.459-0.787 1.575-0.787 3.656 0 2.048 0.851 3.624 0.85 1.576 2.366 2.49 1.511 0.882 3.465 0.882 1.229 0 2.457-0.409 1.262-0.441 1.984-1.135 0.265-0.253 0.601-0.253 0.378-0.04 0.631 0.189 0.34 0.283 0.34 0.63 0 0.348-0.264 0.629-1.013 0.914-2.65 1.513-1.606 0.6-3.087 0.6zm-20.08 7.216q-0.408 0-0.691-0.265-0.265-0.264-0.265-0.661 0-0.442 0.265-0.726 0.264-0.265 0.691-0.265 1.61 0 2.809-0.691 1.228-0.696 1.923-1.923 0.692-1.22 0.692-2.797v-16.33q0-0.4411 0.264-0.6932 0.265-0.2532 0.692-0.2532 0.442 0 0.695 0.2532 0.265 0.2532 0.265 0.6932v16.33q0 2.144-0.945 3.78-0.945 1.671-2.615 2.619-1.64 0.945-3.787 0.945zm6.338-28.25q-0.597 0-1.039-0.4101-0.412-0.441-0.412-1.072 0-0.725 0.442-1.071 0.473-0.379 1.04-0.379 0.536 0 0.979 0.379 0.472 0.347 0.472 1.071 0 0.6305-0.442 1.072-0.408 0.4101-1.04 0.4101zm-14 21.05q-2.456 0-4.444-1.134-1.985-1.12-3.149-3.075-1.137-1.954-1.198-4.412v-15.22q0-0.441 0.265-0.694 0.302-0.253 0.695-0.253 0.443 0 0.692 0.253 0.265 0.254 0.265 0.694v9.833q1.009-1.638 2.804-2.615 1.829-0.9773 4.097-0.9773 2.524 0 4.509 1.166 2.018 1.135 3.152 3.12 1.164 1.985 1.164 4.507 0 2.521-1.164 4.539-1.134 1.985-3.152 3.152-1.985 1.135-4.509 1.135zm0-1.766q1.988 0 3.53-0.913 1.576-0.946 2.491-2.522 0.914-1.607 0.914-3.625 0-2.017-0.914-3.593-0.915-1.56-2.491-2.474-1.542-0.9456-3.53-0.9456-1.954 0-3.53 0.9456-1.576 0.914-2.487 2.49-0.884 1.576-0.884 3.593t0.884 3.624q0.911 1.576 2.487 2.522 1.576 0.913 3.53 0.913zm-21.12 1.766q-2.521 0-4.539-1.134-1.985-1.167-3.152-3.153-1.134-2.017-1.134-4.507 0-2.521 1.134-4.507 1.167-2.017 3.152-3.152 2.018-1.166 4.539-1.166t4.509 1.166q1.984 1.135 3.118 3.152 1.167 1.986 1.167 4.507 0 2.49-1.167 4.507-1.134 1.986-3.118 3.153-1.988 1.134-4.509 1.134zm0-1.766q1.988 0 3.53-0.913 1.576-0.946 2.46-2.522 0.911-1.607 0.911-3.625 0-1.985-0.911-3.561-0.884-1.607-2.46-2.522-1.542-0.9456-3.53-0.9456-1.954 0-3.53 0.9456-1.576 0.9145-2.491 2.522-0.915 1.576-0.915 3.593 0 1.986 0.915 3.593 0.915 1.576 2.491 2.522 1.576 0.913 3.53 0.913z"),
            fill("#4c7cda"),
            strokeWidth(".591")
          )
        )
      )
    );
  }

}