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
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.spi.type.AValue;
import br.com.objectos.html.spi.type.NavValue;

public final class LeftDrawer extends SiteFragment implements DocsPageCss {

  private static final ClassSelector _CURRENT = Css.randomDot(3);

  private static final ClassSelector _NAV_LINK = Css.randomDot(3);

  private static final IdSelector _UI_LEFTDRAWER = Css.randomHash(3);

  private final StyleSheet css = new AbstractStyleSheet() {
    @Override
    protected final void definition() {
      style(
        _CURRENT,

        color(Colors.INDIGO5)
      );

      style(
        _NAV_LINK,

        lineHeight(Spacing.V10)
      );

      style(
        _UI_LEFTDRAWER,

        backgroundColor(white),
        bottom(zero()),
        display(none),
        padding(Spacing.V06),
        position(absolute),
        top(Spacing.V12),
        width(pct(100))
      );

      style(
        body, DocsPage._LEFT_DRAWER_OPEN, sp(), _UI_LEFTDRAWER,

        display(block)
      );

      media(
        screen, minWidth(Breakpoint.LG),

        style(
          _UI_LEFTDRAWER,

          bottom(unset),
          display(block),
          flex(l(0), l(0), Spacing.V72),
          overflowY(scroll),
          position(staticKw),
          top(unset)
        )
      );
    }
  };

  private DocsPage current;

  @Override
  public final String css() {
    return css.printMinified();
  }

  public final void setCurrent(DocsPage current) {
    this.current = current;
  }

  @Override
  protected final void definition() {
    div(
      _UI_LEFTDRAWER,

      nav(
        navItems()
      )
    );
  }

  private NavValue[] navItems() {
    ImmutableList<DocsPage> pages;
    pages = getInstancesByType(DocsPage.class);

    MutableList<NavValue> items;
    items = new MutableList<>();

    for (int i = 0; i < pages.size(); i++) {
      DocsPage page;
      page = pages.get(i);

      String title;
      title = page.topBarTitle();

      if (title == null) {
        continue;
      }

      AValue isCurrent;
      isCurrent = noop();

      if (page == current) {
        isCurrent = _CURRENT;
      }

      items.add(
        a(
          _NAV_LINK, isCurrent, href(page), div(title)
        )
      );
    }

    return items.toArray(NavValue[]::new);
  }

}