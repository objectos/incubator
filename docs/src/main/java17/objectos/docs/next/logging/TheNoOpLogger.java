/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs.next.logging;

import objectos.ssg.SiteDirectory;

final class TheNoOpLogger extends SiteDirectory {

  @Override
  protected final void configure() {
    addPage("index.html", new TheNoOpLoggerIndex());
    addPage("testing.html", new TheNoOpLoggerTesting());
    addPage("debugging.html", new TheNoOpLoggerDebugging());
  }

}