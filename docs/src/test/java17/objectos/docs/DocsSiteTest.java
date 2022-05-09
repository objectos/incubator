/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs;

import objectos.ssg.SiteConfiguration;
import org.testng.annotations.BeforeSuite;

public abstract class DocsSiteTest {

  private static SiteConfiguration generator;

  @BeforeSuite
  public static void _beforeSuite() {
    DocsSite site;
    site = new DocsSite();

    generator = new SiteConfiguration();

    site.configure(generator);
  }

  protected final <T> T getObject(Class<? extends T> key) {
    return generator.getObject(key);
  }

}
