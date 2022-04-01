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
import br.com.objectos.css.Css;
import br.com.objectos.css.framework.reset.Reset;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.http.media.ImageType;

public abstract class DocsPage extends SitePage {

  private static final IdSelector _LOGO = Css.randomHash(3);

  private static final IdSelector _MENU_CLOSE = Css.randomHash(3);

  private static final IdSelector _MENU_OPEN = Css.randomHash(3);

  private static final IdSelector _MENU_SVG = Css.randomHash(3);

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

        height(px(24)),
        width(auto)
      );

      style(
        _MENU_CLOSE,

        display(none)
      );

      style(
        _MENU_CLOSE, or(), _MENU_OPEN,

        alignItems(center),
        marginRight(Spacing.V04),
        outline(none)
      );

      style(
        _MENU_OPEN,

        alignItems(center),
        display(flex),
        height(pct(100))
      );

      style(
        _MENU_SVG,

        display(inlineBlock),
        height(Spacing.V04),
        marginRight(Spacing.V02),
        width(Spacing.V04)
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
        borderBottom(Spacing.PX, solid, Colors.GRAY4),
        display(flex),
        flex(l(0), l(0), Spacing.V12),
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

          display(flex),
          overflowY(hidden)
        ),

        style(
          _UI_LEFTDRAWER,

          bottom(unset),
          display(block),
          flex(l(0), l(0), px(230)),
          position(staticKw),
          top(unset)
        ),

        style(
          _UI_MAIN,

          flexGrow(1),
          overflowY(auto)
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
    LeftDrawer leftDrawer;
    leftDrawer = getInstance(LeftDrawer.class);

    leftDrawer.setCurrent(this);

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

            f(leftDrawer)
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

    LeftDrawer leftDrawer;
    leftDrawer = getInstance(LeftDrawer.class);

    style(
      raw(css.printMinified()),
      raw(leftDrawer.css())
    );
  }

  protected abstract String topBarTitle();

  protected abstract void uiMain();

  private void uiTopBar() {
    header(
      _UI_TOPBAR,

      button(
        _MENU_CLOSE,

        type("button"),

        svg(
          xmlns("http://www.w3.org/2000/svg"), viewBox("0 0 24 24"), height("24"), width("24"),
          path(
            d("M0 0h24v24H0z"), fill("none")
          ),
          path(
            d("M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z")
          )
        )
      ),

      button(
        _MENU_OPEN,

        type("button"),

        svg(
          xmlns("http://www.w3.org/2000/svg"), viewBox("0 0 24 24"), height("24"), width("24"),
          path(
            d("M0 0h24v24H0z"), fill("none")
          ),
          path(
            d("M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z")
          )
        )
      ),

      a(
        _LOGO,

        href(Index.class),

        svg(
          xmlns("http://www.w3.org/2000/svg"),
          viewBox("0 0 44.36 8.61"),
          height("24"),
          path(
            d("m7.943 4.407v-0.2125l-0.0589-3.917v-0.1222h-0.1212l-3.654-0.07203-0.2086-0.0109-3.623-0.07311h-0.2772v0.2794l0.07202 3.918v0.2116l0.0742 3.946v0.1211h0.1211l3.622 0.062 0.2084 0.01 3.903 0.062-0.064-4.194m-0.3698-0.2124v0.212l-0.054 3.704-3.412 0.055-0.2084 0.01-3.498 0.055 0.0698-3.82v-0.2079l0.0674-3.649 3.357-0.06762h0.2084l3.527-0.07093-0.0584 3.791"),
            fill("#4484cb")
          ),
          path(
            d("m42.63 6.683q-0.417 0-0.877-0.1545-0.458-0.1516-0.763-0.5134-0.06-0.079-0.05-0.1686 0-0.097 0.09-0.161 0.07-0.056 0.168-0.042 0.09 0.014 0.152 0.089 0.234 0.2738 0.562 0.3861 0.335 0.1048 0.731 0.1048 0.676 0 0.957-0.2407 0.278-0.2412 0.278-0.5625 0-0.3143-0.298-0.5151-0.294-0.2077-0.914-0.296-0.808-0.1137-1.191-0.4344-0.386-0.3218-0.386-0.7474 0-0.4016 0.199-0.6757 0.203-0.2815 0.547-0.4166 0.356-0.1453 0.804-0.1453 0.545 0 0.923 0.2008 0.38 0.1932 0.611 0.5228 0.06 0.08 0.04 0.1766 0 0.089-0.119 0.1443-0.08 0.04-0.168 0.026-0.08-0.016-0.155-0.098-0.194-0.2574-0.486-0.3849-0.279-0.1376-0.66-0.1376-0.504 0-0.785 0.2173-0.28 0.2172-0.28 0.5225 0 0.2098 0.11 0.3701 0.121 0.1517 0.385 0.2642 0.269 0.1048 0.708 0.169 0.604 0.08 0.948 0.2741 0.355 0.1919 0.502 0.4493 0.149 0.2489 0.149 0.5384 0 0.3777-0.242 0.6589-0.239 0.2819-0.635 0.4345-0.397 0.145-0.849 0.145zm-4.635 0q-0.644 0-1.16-0.2892-0.505-0.298-0.804-0.8048-0.286-0.5139-0.286-1.149 0-0.6425 0.286-1.149 0.299-0.5152 0.804-0.8044 0.516-0.2968 1.16-0.2968 0.641 0 1.146 0.2968 0.511 0.2892 0.797 0.8044 0.3 0.5064 0.3 1.149 0 0.6353-0.3 1.149-0.286 0.5068-0.797 0.8048-0.505 0.2892-1.146 0.2892zm0-0.4509q0.504 0 0.899-0.2339 0.401-0.2401 0.626-0.6424 0.233-0.4097 0.233-0.9247 0-0.5052-0.233-0.9078-0.225-0.4094-0.626-0.6427-0.395-0.2414-0.899-0.2414-0.499 0-0.9 0.2414-0.402 0.2333-0.633 0.6427-0.235 0.4026-0.235 0.9165 0 0.5063 0.235 0.916 0.231 0.4023 0.633 0.6424 0.401 0.2339 0.9 0.2339zm-3.147 0.4013q-0.438-0.0111-0.782-0.2007-0.347-0.2006-0.541-0.5457-0.191-0.3549-0.191-0.7968v-4.002q0-0.1124 0.06-0.1768 0.06-0.0731 0.176-0.0731 0.115 0 0.179 0.0731 0.06 0.0646 0.06 0.1768v4.002q0 0.4672 0.287 0.7641 0.289 0.2893 0.748 0.2893h0.179q0.111 0 0.175 0.072 0.06 0.063 0.06 0.1779 0 0.1023-0.06 0.1754-0.06 0.064-0.175 0.064zm-2.145-3.801q-0.09 0-0.16-0.056-0.05-0.065-0.05-0.1606 0-0.096 0.05-0.1516 0.06-0.066 0.16-0.066h2.18q0.09 0 0.155 0.066 0.06 0.055 0.06 0.1516 0 0.097-0.06 0.1606-0.05 0.056-0.155 0.056zm-2.167 3.85q-0.626 0-1.124-0.2981-0.49-0.2981-0.779-0.8034-0.283-0.5064-0.283-1.142 0-0.6425 0.267-1.149 0.266-0.5066 0.731-0.7958 0.466-0.2978 1.068-0.2978 0.514 0 0.941 0.2008 0.424 0.1934 0.738 0.5948 0.06 0.081 0.05 0.1689 0 0.089-0.111 0.1443-0.06 0.056-0.16 0.049-0.08-0.015-0.157-0.096-0.515-0.6112-1.31-0.6112-0.473 0-0.833 0.2325-0.357 0.2334-0.559 0.635-0.192 0.4017-0.192 0.9243 0 0.5063 0.219 0.916 0.215 0.4023 0.595 0.6424 0.383 0.2339 0.89 0.2339 0.327 0 0.619-0.096 0.299-0.097 0.517-0.2976 0.06-0.065 0.159-0.072 0.08-0.01 0.155 0.056 0.07 0.073 0.07 0.1692 0 0.089-0.06 0.1526-0.591 0.5393-1.463 0.5393zm-4.589-0.01q-0.634 0-1.133-0.2804-0.491-0.2907-0.771-0.7962-0.281-0.5077-0.281-1.158 0-0.6594 0.266-1.158 0.265-0.5064 0.729-0.7956 0.467-0.2892 1.072-0.2892 0.594 0 1.052 0.2817 0.457 0.2728 0.713 0.7638 0.257 0.4813 0.257 1.116 0 0.1048-0.06 0.1691-0.06 0.055-0.168 0.055h-3.539v-0.417h3.664l-0.354 0.264q0.011-0.5212-0.177-0.9228-0.192-0.4028-0.555-0.6275-0.353-0.2325-0.833-0.2325-0.458 0-0.821 0.2325-0.353 0.2247-0.561 0.6275-0.203 0.4016-0.203 0.9321 0 0.5223 0.216 0.923 0.219 0.403 0.606 0.6352 0.383 0.2259 0.881 0.2259 0.315 0 0.627-0.1048 0.323-0.1121 0.507-0.2891 0.06-0.063 0.153-0.063 0.09-0.01 0.16 0.047 0.08 0.073 0.08 0.1609 0 0.088-0.06 0.1613-0.258 0.2326-0.674 0.3852-0.411 0.1528-0.789 0.1528zm-5.119 1.839q-0.09 0-0.177-0.071-0.06-0.063-0.06-0.1683 0-0.1141 0.06-0.1856 0.06-0.062 0.177-0.062 0.41 0 0.716-0.1765 0.314-0.1785 0.491-0.4913 0.177-0.3147 0.177-0.7164v-4.164q0-0.1123 0.06-0.1768 0.06-0.063 0.176-0.063 0.113 0 0.177 0.063 0.06 0.065 0.06 0.1768v4.164q0 0.5466-0.239 0.9649-0.24 0.4256-0.667 0.6662-0.419 0.2405-0.965 0.2405zm1.616-7.201q-0.154 0-0.265-0.1031-0.111-0.1137-0.111-0.274 0-0.1844 0.113-0.2728 0.122-0.0972 0.266-0.0972 0.135 0 0.248 0.0972 0.121 0.0884 0.121 0.2728 0 0.1604-0.111 0.274-0.09 0.1031-0.266 0.1031zm-3.567 5.369q-0.628 0-1.133-0.2891-0.505-0.2908-0.804-0.7884-0.288-0.4988-0.306-1.126v-3.881q0-0.1124 0.06-0.1779 0.06-0.0633 0.176-0.0633 0.113 0 0.176 0.0633 0.06 0.0655 0.06 0.1779v2.507q0.257-0.4179 0.716-0.6668 0.466-0.2488 1.044-0.2488 0.642 0 1.149 0.2968 0.513 0.2892 0.804 0.7957 0.297 0.5063 0.297 1.149 0 0.644-0.297 1.158-0.291 0.5061-0.804 0.8042-0.507 0.2891-1.149 0.2891zm0-0.4504q0.506 0 0.899-0.2336 0.403-0.2395 0.635-0.6426 0.235-0.4095 0.235-0.9247 0-0.5138-0.235-0.9154-0.232-0.4024-0.635-0.6357-0.393-0.2412-0.899-0.2412-0.499 0-0.901 0.2412-0.401 0.2333-0.635 0.635-0.224 0.4018-0.224 0.9155 0 0.5152 0.224 0.9247 0.234 0.403 0.635 0.6426 0.402 0.2336 0.901 0.2336zm-5.384 0.4501q-0.643 0-1.157-0.2891-0.507-0.2984-0.803-0.8045-0.289-0.514-0.289-1.149 0-0.643 0.289-1.149 0.296-0.5152 0.803-0.8044 0.514-0.2968 1.157-0.2968 0.642 0 1.15 0.2968 0.504 0.2892 0.794 0.8044 0.298 0.5063 0.298 1.149 0 0.6349-0.298 1.149-0.29 0.5061-0.794 0.8042-0.508 0.2891-1.15 0.2891zm0-0.4504q0.505 0 0.9-0.2336 0.402-0.2395 0.626-0.6426 0.234-0.4095 0.234-0.9247 0-0.5051-0.234-0.908-0.224-0.4095-0.626-0.6428-0.395-0.2412-0.9-0.2412-0.498 0-0.9 0.2412-0.402 0.2333-0.635 0.6425-0.234 0.4029-0.234 0.9171 0 0.5061 0.234 0.9156 0.233 0.403 0.635 0.6426 0.402 0.2336 0.9 0.2336z"),
            fill("#545454"),
            strokeWidth(".4667")
          )
        )
      )
    );
  }

}