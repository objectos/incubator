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
package objectos.docs;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DocsMigrationTest {

  private DocumentProcessor processor;

  @BeforeClass
  public void _beforeClass() {
    processor = new DocumentProcessor();
  }

  @Test(enabled = false)
  public void index() throws IOException {
    var doc = processor.load("v0002", "index");

    assertEquals(
      normalize(doc),
      load("v0002/index.html")
    );
  }

  @Test(enabled = false)
  public void introIndex() throws IOException {
    var doc = processor.load("v0002", "intro/index");

    assertEquals(
      normalize(doc),
      load("v0002/intro/index.html")
    );
  }

  @Test(enabled = false)
  public void introInstallation() throws IOException {
    test("v0002", "intro/installation");
  }

  @Test
  public void introOverview() throws IOException {
    test("v0002", "intro/overview");
  }

  private String load(String resourceName) throws IOException {
    var c = getClass();

    try (var in = c.getResourceAsStream(resourceName)) {
      var soup = Jsoup.parse(in, "UTF-8", "");

      var article = soup.selectFirst("article");

      return article.toString();
    }
  }

  private String load(String slug, String key) throws IOException {
    var resourceName = slug + "/" + key + ".html";

    var c = getClass();

    try (var in = c.getResourceAsStream(resourceName)) {
      var soup = Jsoup.parse(in, "UTF-8", "");

      var article = soup.selectFirst("article");

      return article.toString();
    }
  }

  private String normalize(Document doc) {
    var source = doc.html;

    var document = Jsoup.parseBodyFragment(source);

    var body = document.body();

    var article = body.selectFirst("article");

    return article.toString();
  }

  private void test(String slug, String key) throws IOException {
    var doc = processor.load(slug, key);

    var actual = normalize(doc);

    var expected = load(slug, key);

    if (!actual.equals(expected)) {
      int len = Math.min(actual.length(), expected.length());

      var sba = new StringBuilder(len);
      var sbe = new StringBuilder(len);

      for (int i = 0; i < len; i++) {
        char ca = actual.charAt(i);
        sba.append(ca);

        char ce = expected.charAt(i);
        sbe.append(ce);

        assertEquals(ca, ce, """
        index=%d

        actual
        %s

        expected
        %s
        """.formatted(i, sba, sbe));
      }
    }
  }

}