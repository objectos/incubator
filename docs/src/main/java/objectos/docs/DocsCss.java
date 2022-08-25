/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs;

import br.com.objectos.css.Css;
import br.com.objectos.css.framework.Framework;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.sheet.AbstractStyleSheet;

final class DocsCss extends AbstractStyleSheet {

  static final ClassSelector LG_WIDTH_70 = Css.randomDot(3);

  private final Framework framework = new Framework();

  @Override
  protected final void definition() {
    install(framework);

    media(
      screen, minWidth(px(1024)),

      style(
        LG_WIDTH_70,

        width(rem(17.5))
      )
    );
  }

}