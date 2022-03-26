/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.docs;

import br.com.objectos.be.site.SiteDirectory;

public final class Tester extends SiteDirectory {

  @Override
  protected final void configure() {
    putInstance(new TopNavbar());

    addPage("index.html", new Index());
  }

}