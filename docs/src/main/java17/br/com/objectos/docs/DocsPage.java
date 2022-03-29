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

  static final IdSelector _BODY = Css.randomHash(3);

  private TopNavbar topNavbar;

  protected abstract void bd();

  @Override
  protected final void definition() {
    topNavbar = getInstance(TopNavbar.class);

    topNavbar.setCurrent(this);

    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      body(
        _BODY,

        f(topNavbar),

        f(this::bd)
      )
    );
  }

  protected void head0() {
    meta(charset("utf-8"));
    meta(httpEquiv("x-ua-compatible"), content("ie=edge"));
    meta(name("viewport"), content("width=device-width, initial-scale=1, shrink-to-fit=no"));
    link(rel("shortcut icon"), type(ImageType.ICON.qualifiedName()), href("/favicon.ico"));

    script(raw(topNavbar.js));

    StyleSheet styleSheet;
    styleSheet = styleSheet();

    String css;
    css = styleSheet.printMinified();

    style(raw(css));
  }

  protected StyleSheet styleSheet() {
    return new AbstractStyleSheet() {
      @Override
      protected final void definition() {
        install(new Reset());

        install(topNavbar.css);

        style(
          body,

          height(pct(100))
        );

        style(
          main,

          padding(Spacing.V0, Spacing.V04)
        );

        style(
          p,

          marginBottom(Spacing.V05)
        );
      }
    };
  }

  protected abstract String topNavbarTitle();

}