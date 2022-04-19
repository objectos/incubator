/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package objectos.docs.ui;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TableOfContentsTest {

  private TableOfContents toc;

  @BeforeMethod
  public void _beforeMethod() {
    toc = new TableOfContents();

    toc.configure(new NoOpContext() {});
  }

  @Test
  public void level0() {
    toc.add("Page 0", Page0.class);
    toc.add("Page 1", Page1.class);
    toc.add("Page 2", Page2.class);

    assertEquals(
      toc.toString(),

      """
      <ul>\
      <li><a href="/page0">Page 0</a></li>\
      <li><a href="/page1">Page 1</a></li>\
      <li><a href="/page2">Page 2</a></li>\
      </ul>"""
    );
  }

  @Test
  public void level1() {
    toc.add("Page 0", Page0.class);
    toc.incLevel("Page 1", Page1.class);
    toc.add("Page 2", Page2.class);

    assertEquals(
      toc.toString(),

      """
      <ul>\
      <li><a href="/page0">Page 0</a></li>\
      <li>\
      <a href="/page1">Page 1</a>\
      <ul>\
      <li><a href="/page2">Page 2</a></li>\
      </ul>\
      </li>\
      </ul>"""
    );
  }

  @Test
  public void level2() {
    toc.add("Page 0", Page0.class);
    toc.incLevel("Page 1", Page1.class);
    toc.add("Page 2", Page2.class);
    toc.decLevel();
    toc.add("Page 3", Page0.class);

    assertEquals(
      toc.toString(),

      """
      <ul>\
      <li><a href="/page0">Page 0</a></li>\
      <li>\
      <a href="/page1">Page 1</a>\
      <ul>\
      <li><a href="/page2">Page 2</a></li>\
      </ul>\
      </li>\
      <li><a href="/page0">Page 3</a></li>\
      </ul>"""
    );
  }

}