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

import br.com.objectos.be.site.SitePage;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.css.Css;
import br.com.objectos.css.framework.reset.Reset;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.http.media.ImageType;

public abstract class DocsPage extends SitePage {

  static final ClassSelector _LEFT_DRAWER_OPEN = Css.randomDot(3);

  private static final IdSelector _UI = Css.randomHash(3);

  private static final IdSelector _UI_AREA = Css.randomHash(3);

  private static final IdSelector _UI_MAIN = Css.randomHash(3);

  private final String js
      = """
        /* DocsPage.java */
        const ui = "{ui}";

        const toggleClass = (id, cl) => {
          const el = document.getElementById(id);

          el.classList.toggle(cl);
        };
        """
          .replace("\n", "")
          .replace("{ui}", _UI.id());

  public abstract String topBarTitle();

  @Override
  protected final void definition() {
    var leftDrawer = getInstance(LeftDrawer.class);

    leftDrawer.setCurrent(this);

    var topBar = getInstance(TopBar.class);

    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      body(
        _UI,

        f(topBar),

        div(
          _UI_AREA,

          f(leftDrawer),

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
    link(href("/docs/prism.css"), rel("stylesheet"));

    script(src("/docs/prism.js"));

    StringBuilder sb;
    sb = getInstance(StringBuilder.class);

    sb.setLength(0);

    sb.append(js);

    ImmutableList<DocsPageJs> jsProviders;
    jsProviders = getInstancesByType(DocsPageJs.class);

    for (int i = 0; i < jsProviders.size(); i++) {
      DocsPageJs p;
      p = jsProviders.get(i);

      sb.append(p.js());
    }

    script(
      raw(sb.toString())
    );

    sb.setLength(0);

    StyleSheet styleSheet;
    styleSheet = thisStyleSheet();

    sb.append(styleSheet.printMinified());

    ImmutableList<DocsPageCss> cssProviders;
    cssProviders = getInstancesByType(DocsPageCss.class);

    for (int i = 0; i < cssProviders.size(); i++) {
      DocsPageCss p;
      p = cssProviders.get(i);

      sb.append(p.css());
    }

    style(
      raw(sb.toString())
    );
  }

  protected ThisStyleSheet thisStyleSheet() {
    return new ThisStyleSheet();
  }

  protected abstract void uiMain();

  protected static class ThisStyleSheet extends AbstractStyleSheet {
    private final Reset reset = new Reset();

    protected final void articleCode() {
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
        article, sp(), code, attr("class", startsWith("language-")),

        fontSize(FontSize.SM),
        fontWeight(500),
        lineHeight(FontSize.SM)
      );
    }

    protected final void articleTable() {
      style(
        article, sp(), table,

        borderTop(px(1), solid, Colors.GRAY4),
        marginBottom(Spacing.V08),
        width(pct(100))
      );

      style(
        article, sp(), tbody,

        fontSize(FontSize.SM)
      );

      style(
        article, sp(), td,

        padding(Spacing.V04)
      );

      style(
        article, sp(), th,

        backgroundColor(Colors.GRAY2),
        padding(Spacing.V04),
        textAlign(left)
      );

      style(
        article, sp(), tr,

        borderBottom(px(1), solid, Colors.GRAY4)
      );
    }

    protected final void articleUl() {
      style(
        article, sp(), ul,

        listStyle(disc, inside),
        paddingLeft(Spacing.V10)
      );

      style(
        article, sp(), li,

        margin(Spacing.V03, zero())
      );
    }

    @Override
    protected void definition() {
      install(reset);

      style(
        article, sp(), a,

        color(Colors.INDIGO6)
      );

      style(
        article, sp(), a, HOVER,

        textDecoration(underline)
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
        article, sp(), h2,

        fontSize(FontSize.XL2),
        fontWeight(600),
        marginBottom(Spacing.V04),
        marginTop(Spacing.V14)
      );

      style(
        article, sp(), header,

        padding(Spacing.V12, Spacing.V0)
      );

      style(
        article, sp(), p,

        margin(Spacing.V04, zero())
      );

      style(
        html,

        height(pct(100))
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
        _UI_MAIN,

        padding(Spacing.V04)
      );

      media(
        screen, minWidth(Breakpoint.LG),

        style(
          _UI_AREA,

          display(flex),
          overflowY(hidden)
        ),

        style(
          _UI_MAIN,

          flexGrow(1),
          overflowY(auto)
        )
      );
    }
  }

}