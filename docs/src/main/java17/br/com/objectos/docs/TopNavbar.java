/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.docs;

import br.com.objectos.be.site.SiteFragment;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;

final class TopNavbar extends SiteFragment {

  private static final IdSelector _CONTAINER = Css.randomHash(3);

  final StyleSheet css = new AbstractStyleSheet() {
    @Override
    protected final void definition() {
      style(
        _CONTAINER,

        backgroundColor(Colors.GRAY0),
        borderBottom(Spacing.PX, solid, Colors.GRAY2),
        height(Spacing.V16)
      );
    }
  };

  @Override
  protected final void definition() {
    div(
      _CONTAINER
    );
  }

}