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

import br.com.objectos.be.site.SitePage;
import br.com.objectos.www.objectos.css.CssDirectory;

final class Index extends SitePage implements NavbarPage {

  private String navbarTitle;

  @Override
  public final String navbarTitle() {
    return navbarTitle;
  }

  @Override
  protected final void definition() {
    Navbar navbar;
    navbar = getInstance(Navbar.class);

    html(
      head(
        link(CssDirectory.STYLES)
      ),
      body(
        f(navbar),
        ul(
          li(a(href(Index.class)))
        )
      )
    );
  }

  @Override
  protected final void register() {
    navbarTitle = "Home";
  }

}
