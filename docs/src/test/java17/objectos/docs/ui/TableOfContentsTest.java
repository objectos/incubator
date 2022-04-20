/*
 * Copyright (C) 2022 Objectos Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

  @Test(enabled = false)
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

  @Test(enabled = false)
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

  @Test(enabled = false)
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