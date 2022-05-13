/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package objectos.ssg;

import static org.testng.Assert.assertEquals;

import br.com.objectos.core.io.Resource;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.http.media.ImageType;
import br.com.objectos.http.media.TextType;
import java.io.IOException;
import org.testng.annotations.Test;

public class SiteTest {

  @Test
  public void testCase00() throws IOException {
    TestableSiteWriter w;
    w = gen(new TestCase00());

    testPathList(
      w,

      "/index.html"
    );

    w.testString(
      "/index.html",

      TextType.HTML,

      """
      <html>\
      <body>TestCase00:index</body>\
      </html>"""
    );
  }

  @Test
  public void testCase01() throws IOException {
    TestableSiteWriter w;
    w = gen(new TestCase01());

    testPathList(
      w,

      "/index.html",
      "/styles.css"
    );

    w.testString(
      "/index.html",

      TextType.HTML,

      """
      <html>\
      <head>\
      <link href="/styles.css" rel="stylesheet">\
      </head>\
      </html>"""
    );

    w.testString(
      "/styles.css",

      TextType.CSS,

      """
      body{\
      font-family:MyCustomFont\
      }"""
    );
  }

  @Test
  public void testCase02() throws IOException {
    TestableSiteWriter w;
    w = gen(new TestCase02());

    testPathList(
      w,

      "/5x2.jpg",
      "/index.html"
    );

    w.testBytes(
      "/5x2.jpg",

      ImageType.JPEG,

      Resource.getResource(getClass(), "5x2.jpg")
    );

    w.testString(
      "/index.html",

      TextType.HTML,

      """
      <html>\
      <img src="/5x2.jpg">\
      </html>"""
    );
  }

  @Test
  public void testCase03() throws IOException {
    TestableSiteWriter w;
    w = gen(new TestCase03());

    testPathList(
      w,

      "/sub/index.html",
      "/sub/styles.css"
    );

    w.testString(
      "/sub/index.html",

      TextType.HTML,

      """
      <html>\
      <head><link href="/sub/styles.css" rel="stylesheet"></head>\
      <body>TestCase03:index</body>\
      </html>"""
    );

    w.testString(
      "/sub/styles.css",

      TextType.CSS,

      """
      body{\
      font-family:MyCustomFont\
      }"""
    );
  }

  @Test
  public void testCase04() throws IOException {
    TestableSiteWriter w;
    w = gen(new TestCase04());

    testPathList(
      w,

      "/sub/5x2.jpg",
      "/sub/foo.jpg",
      "/sub/foo.txt",
      "/sub/index.html"
    );

    Resource r;
    r = Resource.getResource(getClass(), "5x2.jpg");

    w.testBytes("/sub/5x2.jpg", ImageType.JPEG, r);
    w.testBytes("/sub/foo.jpg", ImageType.JPEG, r);
    w.testBytes("/sub/foo.txt", TextType.PLAIN, r);

    w.testString(
      "/sub/index.html",

      TextType.HTML,

      """
      <html>\
      <a href="/sub/5x2.jpg">ico</a>\
      <a href="/sub/foo.jpg">jpg</a>\
      <a href="/sub/foo.txt">txt</a>\
      </html>"""
    );
  }

  @Test
  public void testCase05() throws IOException {
    TestableSiteWriter w;
    w = gen(new TestCase05());

    testPathList(
      w,

      "/foo/bar/index.html"
    );

    w.testString(
      "/foo/bar/index.html",

      TextType.HTML,

      """
      <html>\
      <body>TestCase05:index</body>\
      </html>"""
    );
  }

  @Test
  public void testCase06() throws IOException {
    TestableSiteWriter w;
    w = gen(new TestCase06());

    testPathList(
      w,

      "/index.html"
    );

    w.testString(
      "/index.html",

      TextType.HTML,

      """
      <html>\
      <body>\
      <header>\
      <a href="/index.html">Objectos</a>\
      </header>\
      </body>\
      </html>"""
    );
  }

  @Test
  public void testCase07() throws IOException {
    TestableSiteWriter w;
    w = gen(new TestCase07());

    w.testString(
      "/toc.html",

      TextType.HTML,

      """
      <html>\
      <ul>\
      <li>0:/page1.html</li>\
      <li>0:/page2.html</li>\
      <li>1:/l1/page1.html</li>\
      <li>1:/l1/page2.html</li>\
      <li>2:/l1/l2/page1.html</li>\
      <li>1:/l1/page3.html</li>\
      <li>0:/page3.html</li>\
      </ul>\
      </html>"""
    );
  }

  @Test
  public void testCase08() throws IOException {
    TestableSiteWriter w;
    w = gen(new TestCase08());

    testPathList(
      w,

      "/docs/index.html"
    );

    w.testString(
      "/docs/index.html",

      TextType.HTML,

      """
      <html>\
      <body>TestCase08:index</body>\
      </html>"""
    );
  }

  @Test
  public void testCase09() throws IOException {
    TestCase09 tc;
    tc = new TestCase09();

    TestableSiteWriter w;
    w = gen(tc);

    testPathList(
      w,

      "/index.html"
    );

    w.testString(
      "/index.html",

      TextType.HTML,

      """
      <html>\
      <body>TestCase09:index</body>\
      </html>"""
    );

    assertEquals(
      tc.result,

      """
      <html>\
      <body>TestCase09:index</body>\
      </html>"""
    );
  }

  @Test
  public void testCase10() throws IOException {
    TestCase10 tc;
    tc = new TestCase10();

    TestableSiteWriter w;
    w = gen(tc);

    testPathList(
      w,

      "/1/index.html",
      "/2/index.html"
    );

    w.testString(
      "/1/index.html",

      TextType.HTML,

      """
      <html>\
      <body>TestCase10:1</body>\
      </html>"""
    );

    w.testString(
      "/2/index.html",

      TextType.HTML,

      """
      <html>\
      <body>TestCase10:2</body>\
      </html>"""
    );
  }

  protected void testPathList(TestableSiteWriter w, String... paths) {
    assertEquals(w.pathList(), ImmutableList.copyOf(paths));
  }

  private TestableSiteWriter gen(Site site) throws IOException {
    TestableSiteWriter w;
    w = new TestableSiteWriter();

    site.generate(w);

    return w;
  }

}