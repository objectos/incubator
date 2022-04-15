/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.www.objectos;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.object.Checks;
import br.com.objectos.html.spi.type.UlValue;
import objectos.ssg.SiteFragment;

public class Navbar extends SiteFragment {

  private ImmutableList<NavbarPage> pages;

  @Override
  protected final void definition() {
    nav(
      ul(
        items()
      )
    );
  }

  final void setPages(ImmutableList<NavbarPage> pages) {
    this.pages = Checks.checkNotNull(pages, "pages == null");
  }

  private UlValue[] items() {
    UlValue[] lis;
    lis = new UlValue[pages.size()];

    for (int i = 0; i < lis.length; i++) {
      NavbarPage page;
      page = pages.get(i);

      lis[i] = li(
        a(href(page), t(page.navbarTitle()))
      );
    }

    return lis;
  }

}
