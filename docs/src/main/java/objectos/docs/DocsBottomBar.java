/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs;

import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.border.BorderTop;
import br.com.objectos.css.framework.flexbox.Flex;
import br.com.objectos.css.framework.sizing.Height;
import br.com.objectos.css.framework.sizing.MaxWidth;
import br.com.objectos.css.framework.spacing.MarginX;
import br.com.objectos.css.framework.spacing.PaddingY;
import br.com.objectos.css.framework.typography.FontSize;
import br.com.objectos.css.framework.typography.TextAlign;
import br.com.objectos.html.tmpl.AbstractFragment;

final class DocsBottomBar extends AbstractFragment implements Docs.BottomBar {

  @Override
  public final AbstractFragment toFragment() { return this; }

  @Override
  protected final void definition() {
    footer(
      BorderColor.slate200,
      BorderTop.v1,
      Flex.none,
      Height.v16,
      PaddingY.v02,

      nav(
        MarginX.auto,
        MaxWidth.screenX2l,

        p(
          FontSize.small,
          PaddingY.v32,
          TextAlign.center,

          t("© 2010-2022 Objectos Software LTDA. All rights reserved.")
        )
      )
    );
  }

}