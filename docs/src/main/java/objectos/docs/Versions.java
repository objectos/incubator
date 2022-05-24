/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs;

import objectos.docs.ui.DocsPage;
import objectos.docs.ui.Pages;

final class Versions extends DocsPage implements Pages.IgnoreMe {

  @Override
  protected final void body0() {
    article(
      header(
        h1("Versions")
      )
    );
  }

}