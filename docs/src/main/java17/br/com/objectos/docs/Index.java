/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.docs;

import br.com.objectos.be.site.SitePage;
import br.com.objectos.css.framework.reset.Reset;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.http.media.ImageType;

final class Index extends SitePage {

  private TopNavbar topNavbar;

  @Override
  protected final void definition() {
    topNavbar = getInstance(TopNavbar.class);

    doctype();
    html(
      lang("en"),
      head(
        f(this::head0)
      ),
      body(
        f(topNavbar)
      )
    );
  }

  protected void head0() {
    meta(charset("utf-8"));
    meta(httpEquiv("x-ua-compatible"), content("ie=edge"));
    meta(name("viewport"), content("width=device-width, initial-scale=1, shrink-to-fit=no"));
    link(rel("shortcut icon"), type(ImageType.ICON.qualifiedName()), href("/favicon.ico"));

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
      }
    };
  }

}