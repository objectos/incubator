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
import br.com.objectos.css.Css;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.html.spi.type.AValue;
import br.com.objectos.html.spi.type.NavValue;

public final class LeftDrawer extends SiteFragment {

  private static final ClassSelector _CURRENT = Css.randomDot(3);

  private static final ClassSelector _NAV_LINK = Css.randomDot(3);

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
    }
  };

  private DocsPage current;

  @Override
  protected final void definition() {
    nav(
      navItems()
    );
  }

  final String css() {
    return css.printMinified();
  }

  final void setCurrent(DocsPage current) {
    this.current = current;
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
        _NAV_LINK, isCurrent, href(page), div(page.topBarTitle())
      );
    }

    return items;
  }

}