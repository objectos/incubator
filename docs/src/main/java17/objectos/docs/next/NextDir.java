/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs.next;

import objectos.docs.next.intro.IntroDir;
import objectos.docs.next.logging.LoggingDir;
import objectos.docs.ui.PageSwitcher;
import objectos.docs.ui.TableOfContents;
import objectos.ssg.SiteDirectory;
import objectos.ssg.SitePath;

public final class NextDir extends SiteDirectory {

  public static final Class<? extends SitePath> INDEX = Index.class;

  @Override
  protected final void configure() {
    addObject(new PageSwitcher());

    addPage("toc.html", new Toc());

    addObject(new TableOfContents());

    addPage("index.html", new Index());

    addDirectory("intro", new IntroDir());
    addDirectory("logging", new LoggingDir());
  }

}