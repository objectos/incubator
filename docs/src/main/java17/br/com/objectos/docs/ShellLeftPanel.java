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
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.spi.type.AValue;
import br.com.objectos.html.spi.type.NavValue;

final class ShellLeftPanel extends SiteFragment {

  static final IdSelector _ID = Css.randomHash(3);

  private static final ClassSelector _CURRENT = Css.randomDot(3);

  private static final ClassSelector _NAV_LINK = Css.randomDot(3);

  final StyleSheet css = new AbstractStyleSheet() {
    @Override
    protected final void definition() {
      style(
        _CURRENT,

        color(Colors.INDIGO5)
      );

      style(
        _ID,

        backgroundColor(white),
        bottom(zero()),
        display(none),
        padding(Spacing.V06),
        position(absolute),
        top(Spacing.V16),
        width(pct(100))
      );

      style(
        _NAV_LINK,

        lineHeight(Spacing.V10)
      );
    }
  };

  private DocsPage current;

  public final void setCurrent(DocsPage page) {
    current = Checks.checkNotNull(page, "page == null");
  }

  @Override
  protected final void definition() {
    div(
      _ID,

      nav(
        navItems()
      )
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

      if (page == current) {
        isCurrent = _CURRENT;
      }

      items[i] = a(
        _NAV_LINK, isCurrent, href(page), div(page.topNavbarTitle())
      );
    }

    return items;
  }

}