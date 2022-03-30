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

import br.com.objectos.be.site.SitePage;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.css.Css;
import br.com.objectos.css.framework.reset.Reset;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.element.ElementName;
import br.com.objectos.html.spi.type.AValue;
import br.com.objectos.html.spi.type.NavValue;
import br.com.objectos.http.media.ImageType;

public abstract class DocsPage extends SitePage {

  private static final ClassSelector _CURRENT = Css.randomDot(3);

  private static final IdSelector _LOGO = Css.randomHash(3);

  private static final IdSelector _MENU_CLOSE = Css.randomHash(3);

  private static final IdSelector _MENU_OPEN = Css.randomHash(3);

  private static final IdSelector _MENU_SVG = Css.randomHash(3);

  private static final ClassSelector _NAV_LINK = Css.randomDot(3);

  private static final IdSelector _UI = Css.randomHash(3);

  private static final IdSelector _UI_AREA = Css.randomHash(3);

  private static final IdSelector _UI_LEFTDRAWER = Css.randomHash(3);

  private static final IdSelector _UI_MAIN = Css.randomHash(3);

  private static final IdSelector _UI_TOPBAR = Css.randomHash(3);

  private final StyleSheet css = new AbstractStyleSheet() {
    private final Reset reset = new Reset();

    @Override
    protected final void definition() {
      install(reset);

      style(
        html,

        height(pct(100))
      );

      style(
        p,

        marginBottom(Spacing.V05)
      );

      style(
        _LOGO,

        display(none)
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

      style(
        _CURRENT,

        color(Colors.INDIGO5)
      );

      style(
        _NAV_LINK,

        lineHeight(Spacing.V10)
      );

      style(
        _UI,

        display(flex),
        flexDirection(column),
        height(pct(100)),
        overflow(hidden)
      );

      style(
        _UI_AREA,

        height(pct(100)),
        overflowY(auto)
      );

      style(
        _UI_LEFTDRAWER,

        backgroundColor(white),
        bottom(zero()),
        display(none),
        padding(Spacing.V06),
        position(absolute),
        top(Spacing.V16),
        width(pct(100))
      );

      style(
        _UI_MAIN,

        padding(Spacing.V06)
      );

      style(
        _UI_MAIN, sp(), header, sp(), h1,

        fontSize(FontSize.XL4),
        fontWeight(bold)
      );

      style(
        _UI_MAIN, sp(), header, sp(), p,

        fontSize(FontSize.XL2),
        fontWeight(300),
        lineHeight(1.2),
        marginTop(Spacing.V03)
      );

      style(
        _UI_TOPBAR,

        alignItems(center),
        backgroundColor(Colors.GRAY0),
        borderBottom(Spacing.PX, solid, Colors.GRAY2),
        display(flex),
        flex(l(0), l(0), Spacing.V16),
        padding(Spacing.V0, Spacing.V06)
      );

      media(
        screen, minWidth(Breakpoint.LG),

        style(
          _LOGO,

          alignItems(center),
          display(flex),
          height(pct(100))
        ),

        style(
          _LOGO, sp(), svg,

          height(px(24)),
          width(auto),
          marginTop(px(5))
        ),

        style(
          _MENU_CLOSE, or(), _MENU_OPEN,

          display(none)
        ),

        style(
          _UI_AREA,

          display(flex)
        ),

        style(
          _UI_LEFTDRAWER,

          bottom(unset),
          display(block),
          position(staticKw),
          top(unset)
        )
      );
    }
  };

  private final String js
      = """
        /* DocsPage.java */
        function onClick(id, listener) {
          const el = document.getElementById(id);

          el.addEventListener("click", listener);
        }

        function setStyle(id, propName, value) {
          const el = document.getElementById(id);

          el.style[propName] = value;
        }

        function menuCloseClicked(event) {
          setStyle("{menuClose}", "display", null);
          setStyle("{menuOpen}", "display", null);
          setStyle("{leftPanel}", "display", null);
        }

        function menuOpenClicked(event) {
          setStyle("{menuClose}", "display", "flex");
          setStyle("{menuOpen}", "display", "none");
          setStyle("{leftPanel}", "display", "block");
        }

        function domLoaded() {
          onClick("{menuClose}", menuCloseClicked);
          onClick("{menuOpen}", menuOpenClicked);
        }

        window.addEventListener('DOMContentLoaded', domLoaded);
        """
        .replace("\n", "")
        .replace("{leftPanel}", _UI_LEFTDRAWER.id())
        .replace("{menuClose}", _MENU_CLOSE.id())
        .replace("{menuOpen}", _MENU_OPEN.id());

  @Override
  protected final void definition() {
    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      body(
        _UI,

        f(this::uiTopBar),

        div(
          _UI_AREA,

          div(
            _UI_LEFTDRAWER,

            f(this::uiLeftDrawer)
          ),

          main(
            _UI_MAIN,

            f(this::uiMain)
          )
        )
      )
    );
  }

  protected void head0() {
    meta(charset("utf-8"));
    meta(httpEquiv("x-ua-compatible"), content("ie=edge"));
    meta(name("viewport"), content("width=device-width, initial-scale=1, shrink-to-fit=no"));
    link(rel("shortcut icon"), type(ImageType.ICON.qualifiedName()), href("/favicon.ico"));

    script(
      raw(js)
    );

    style(
      raw(css.printMinified())
    );
  }

  protected abstract String topBarTitle();

  protected abstract void uiMain();

  private ElementName menuBtn(IdSelector id, String pathd) {
    return button(
      id,

      type("button"),

      svg(
        _MENU_SVG,

        xmlns("http://www.w3.org/2000/svg"), viewBox("0 0 20 20"), fill("currentColor"),
        path(
          fillRule("evenodd"),
          d(pathd),
          clipRule("evenodd")
        )
      ),

      span(topBarTitle())
    );
  }

  private NavValue[] navItems() {
    ImmutableList<DocsPage> pages;
    pages = getInstancesByType(DocsPage.class);

    NavValue[] items;
    items = new NavValue[pages.size()];

    for (int i = 0; i < items.length; i++) {
      DocsPage page;
      page = pages.get(i);

      AValue isCurrent;
      isCurrent = noop();

      if (page == this) {
        isCurrent = _CURRENT;
      }

      items[i] = a(
        _NAV_LINK, isCurrent, href(page), div(page.topBarTitle())
      );
    }

    return items;
  }

  private void uiLeftDrawer() {
    nav(
      navItems()
    );
  }

  private void uiTopBar() {
    header(
      _UI_TOPBAR,

      menuBtn(
        _MENU_CLOSE,

        "M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
      ),

      menuBtn(
        _MENU_OPEN,

        "M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
      ),

      a(
        _LOGO,

        href(Index.class),

        svg(
          xmlns("http://www.w3.org/2000/svg"),
          width("100mm"),
          height("14.78mm"),
          viewBox("0 0 100 14.78"),
          path(
            d("m56.86 11.44q-0.76 0-1.59-0.279-0.83-0.28-1.38-0.93-0.12-0.15-0.11-0.31 0-0.17 0.19-0.29 0.15-0.1 0.31-0.1 0.17 0 0.28 0.16 0.42 0.49 1.01 0.7 0.62 0.19 1.33 0.19 1.22 0 1.73-0.44t0.51-1.02q0-0.57-0.55-0.93-0.54-0.38-1.68-0.54-1.45-0.2-2.15-0.78-0.7-0.59-0.7-1.36 0-0.73 0.36-1.22 0.37-0.51 0.99-0.76 0.65-0.26 1.46-0.26 0.99 0 1.67 0.36 0.69 0.35 1.11 0.95 0.12 0.15 0.1 0.32 0 0.16-0.22 0.26-0.15 0.1-0.31 0-0.16 0-0.27-0.18-0.37-0.46-0.89-0.7-0.51-0.24-1.2-0.24-0.91 0-1.42 0.39t-0.51 0.94q0 0.38 0.2 0.67 0.22 0.28 0.7 0.48 0.48 0.19 1.28 0.31 1.09 0.15 1.72 0.5 0.64 0.34 0.9 0.81 0.28 0.45 0.28 0.98 0 0.68-0.44 1.19-0.43 0.51-1.15 0.79-0.71 0.26-1.54 0.26zm-8.4 0q-1.17 0-2.1-0.529-0.92-0.541-1.45-1.45-0.529-0.93-0.529-2.08 0-1.17 0.529-2.09 0.53-0.93 1.45-1.45 0.93-0.54 2.1-0.54 1.16 0 2.08 0.54 0.92 0.52 1.44 1.45 0.54 0.92 0.54 2.09 0 1.15-0.54 2.08-0.52 0.909-1.44 1.45-0.92 0.529-2.08 0.529zm0-0.82q0.91 0 1.63-0.42 0.73-0.439 1.13-1.159 0.43-0.75 0.43-1.68 0-0.92-0.43-1.64-0.4-0.75-1.13-1.17-0.72-0.43-1.63-0.43t-1.63 0.43q-0.73 0.42-1.15 1.17-0.43 0.72-0.43 1.66 0 0.91 0.43 1.66 0.42 0.72 1.15 1.159 0.72 0.42 1.63 0.42zm-5.694 0.73q-0.801 0-1.426-0.359-0.627-0.37-0.976-0.99-0.349-0.64-0.349-1.44v-7.25q0-0.21 0.116-0.32 0.131-0.14 0.32-0.14 0.204 0 0.321 0.14 0.131 0.11 0.131 0.32v7.25q0 0.84 0.524 1.38 0.524 0.52 1.353 0.52h0.321q0.203 0 0.32 0.13 0.131 0.12 0.131 0.32 0 0.19-0.131 0.32-0.117 0.12-0.32 0.12zm-3.887-6.889q-0.174 0-0.291-0.1-0.102-0.11-0.102-0.29 0-0.17 0.102-0.27 0.117-0.12 0.291-0.12h3.945q0.175 0 0.277 0.12 0.116 0.1 0.116 0.27 0 0.18-0.116 0.29-0.102 0.1-0.277 0.1zm-3.929 6.979q-1.135 0-2.038-0.539-0.888-0.54-1.412-1.46-0.51-0.91-0.51-2.06 0-1.17 0.481-2.09 0.481-0.91 1.324-1.44 0.845-0.54 1.937-0.54 0.931 0 1.702 0.37 0.772 0.35 1.34 1.08 0.116 0.14 0.09 0.3-0.03 0.16-0.189 0.26-0.131 0.11-0.291 0.1-0.16 0-0.276-0.17-0.932-1.11-2.373-1.11-0.859 0-1.514 0.42-0.641 0.42-1.004 1.15-0.35 0.73-0.35 1.68 0 0.91 0.393 1.66 0.393 0.72 1.078 1.159 0.698 0.42 1.615 0.42 0.597 0 1.121-0.17 0.538-0.18 0.931-0.539 0.132-0.12 0.291-0.13 0.161 0 0.278 0.1 0.145 0.13 0.145 0.309 0.02 0.16-0.117 0.27-1.062 0.98-2.649 0.98zm-8.31 0q-1.15 0-2.053-0.51-0.887-0.519-1.397-1.439-0.51-0.91-0.51-2.09 0-1.2 0.48-2.1 0.481-0.92 1.325-1.44 0.844-0.53 1.936-0.53 1.077 0 1.908 0.51 0.829 0.5 1.295 1.39 0.465 0.87 0.465 2.02 0 0.19-0.116 0.31-0.116 0.1-0.305 0.1h-6.406v-0.76h6.638l-0.64 0.48q0.03-0.94-0.32-1.67-0.349-0.73-1.005-1.14-0.64-0.42-1.514-0.42-0.83 0-1.485 0.42-0.64 0.41-1.018 1.14-0.365 0.73-0.365 1.69 0 0.94 0.393 1.67t1.093 1.149q0.698 0.41 1.601 0.41 0.568 0 1.135-0.19 0.582-0.21 0.917-0.529 0.117-0.11 0.276-0.11 0.175 0 0.292 0.1 0.16 0.129 0.16 0.289 0.02 0.16-0.131 0.29-0.466 0.42-1.223 0.7-0.743 0.27-1.426 0.27zm-9.273 3.34q-0.189 0-0.32-0.13-0.131-0.12-0.131-0.31 0-0.2 0.131-0.34 0.131-0.11 0.32-0.11 0.742 0 1.296-0.32 0.567-0.32 0.888-0.89 0.32-0.57 0.32-1.3v-7.539q0-0.2 0.117-0.32 0.131-0.11 0.319-0.11 0.205 0 0.321 0.11 0.131 0.12 0.131 0.32v7.539q0 0.99-0.437 1.75-0.437 0.77-1.208 1.21-0.757 0.44-1.747 0.44zm2.926-13.05q-0.277 0-0.48-0.19-0.19-0.2-0.19-0.49 0-0.34 0.204-0.5 0.219-0.17 0.48-0.17 0.248 0 0.452 0.17 0.219 0.16 0.219 0.5 0 0.29-0.204 0.49-0.19 0.19-0.481 0.19zm-6.462 9.729q-1.135 0-2.052-0.53-0.918-0.519-1.456-1.419-0.5237-0.91-0.5527-2.04v-7.03q0-0.21 0.117-0.32 0.1307-0.12 0.3187-0.12 0.204 0 0.321 0.12 0.116 0.11 0.116 0.32v4.54q0.466-0.76 1.296-1.21 0.844-0.45 1.892-0.45 1.165 0 2.082 0.54 0.932 0.52 1.456 1.44 0.538 0.92 0.538 2.08 0 1.17-0.538 2.1-0.524 0.909-1.456 1.449-0.917 0.53-2.082 0.53zm0-0.82q0.917 0 1.631-0.42 0.728-0.439 1.149-1.159 0.423-0.75 0.423-1.68t-0.423-1.66q-0.421-0.73-1.149-1.15-0.714-0.43-1.631-0.43-0.902 0-1.63 0.43-0.727 0.42-1.15 1.15-0.408 0.73-0.408 1.66t0.408 1.68q0.423 0.72 1.15 1.159 0.728 0.42 1.63 0.42zm-9.752 0.82q-1.164 0-2.096-0.53-0.917-0.54-1.456-1.449-0.524-0.93-0.524-2.08 0-1.17 0.524-2.09 0.539-0.93 1.456-1.45 0.932-0.54 2.096-0.54 1.165 0 2.082 0.54 0.917 0.52 1.441 1.45 0.538 0.92 0.538 2.09 0 1.15-0.538 2.08-0.524 0.909-1.441 1.449-0.917 0.53-2.082 0.53zm0-0.82q0.917 0 1.631-0.42 0.727-0.439 1.135-1.159 0.422-0.75 0.422-1.68 0-0.92-0.422-1.64-0.408-0.75-1.135-1.17-0.714-0.43-1.631-0.43-0.902 0-1.63 0.43-0.728 0.42-1.15 1.17-0.422 0.72-0.422 1.66 0 0.91 0.422 1.66 0.422 0.72 1.15 1.159 0.728 0.42 1.63 0.42z"),
            color("#000000"),
            fill("#545454"),
            strokeWidth(".8453")
          ),
          path(
            d("m96.88 11.71q-0.75 0-1.58-0.28-0.83-0.279-1.39-0.929-0.11-0.151-0.1-0.311 0-0.169 0.19-0.289 0.15-0.1 0.31-0.1 0.17 0 0.27 0.16 0.42 0.489 1.02 0.7 0.61 0.189 1.33 0.189 1.22 0 1.73-0.439 0.51-0.44 0.51-1.02 0-0.57-0.55-0.93-0.54-0.38-1.68-0.54-1.45-0.2-2.15-0.79-0.7-0.58-0.7-1.35 0-0.73 0.36-1.22 0.37-0.51 0.99-0.76 0.64-0.26 1.46-0.26 0.99 0 1.67 0.36 0.687 0.35 1.107 0.95 0.12 0.14 0.1 0.32 0 0.16-0.22 0.26-0.14 0.1-0.3 0-0.157 0-0.277-0.17-0.36-0.47-0.89-0.7-0.51-0.25-1.19-0.25-0.92 0-1.43 0.4-0.51 0.39-0.51 0.94 0 0.38 0.21 0.67 0.21 0.28 0.69 0.48 0.49 0.19 1.29 0.31 1.09 0.14 1.71 0.49 0.647 0.35 0.907 0.82 0.266 0.45 0.266 0.97 0 0.69-0.426 1.2-0.437 0.51-1.147 0.79-0.72 0.26-1.55 0.26zm-7.26 0q-1.14 0-2.04-0.54-0.89-0.539-1.41-1.459-0.51-0.92-0.51-2.07 0-1.16 0.48-2.08t1.32-1.44q0.85-0.54 1.94-0.54 0.93 0 1.71 0.37 0.77 0.35 1.34 1.07 0.11 0.15 0.1 0.31 0 0.16-0.19 0.26-0.13 0.1-0.29 0.1t-0.27-0.18q-0.94-1.1-2.38-1.1-0.86 0-1.51 0.42-0.64 0.42-1.01 1.15-0.35 0.73-0.35 1.67 0 0.92 0.4 1.66 0.39 0.729 1.07 1.169 0.7 0.42 1.62 0.42 0.6 0 1.12-0.17 0.54-0.18 0.93-0.54 0.13-0.119 0.29-0.13 0.16 0 0.28 0.1 0.15 0.13 0.15 0.31 0 0.16-0.12 0.27-1.06 0.98-2.65 0.98zm-9.21 0q-1.17 0-2.1-0.53-0.92-0.54-1.46-1.449-0.52-0.94-0.52-2.09 0-1.16 0.52-2.08 0.54-0.93 1.46-1.45 0.93-0.54 2.1-0.54 1.16 0 2.08 0.54 0.92 0.52 1.44 1.45 0.54 0.92 0.54 2.08 0 1.15-0.54 2.09-0.52 0.909-1.44 1.449-0.92 0.53-2.08 0.53zm0-0.82q0.91 0 1.63-0.42 0.73-0.44 1.13-1.169 0.43-0.74 0.43-1.67 0-0.92-0.43-1.65-0.4-0.74-1.13-1.16-0.72-0.44-1.63-0.44t-1.63 0.44q-0.73 0.42-1.16 1.16-0.42 0.73-0.42 1.66 0 0.92 0.42 1.66 0.43 0.729 1.16 1.169 0.72 0.42 1.63 0.42zm-13.59 0.73q-0.19 0-0.32-0.119-0.11-0.131-0.11-0.321v-10.5q0-0.2 0.11-0.32 0.13-0.11 0.32-0.11h2.62q1.26 0 2.29 0.42 1.05 0.41 1.81 1.18 0.77 0.76 1.18 1.79 0.42 1.03 0.42 2.29 0 1.25-0.42 2.3-0.41 1.03-1.18 1.799-0.76 0.76-1.81 1.18-1.03 0.41-2.29 0.41zm0.44-0.789h2.18q1.42 0 2.49-0.641 1.1-0.639 1.71-1.739 0.62-1.11 0.62-2.52 0-1.42-0.62-2.51-0.61-1.11-1.71-1.75-1.07-0.64-2.49-0.64h-2.18z"),
            fill("#4c7cda"),
            strokeWidth(".3851")
          )
        )
      )
    );
  }

}