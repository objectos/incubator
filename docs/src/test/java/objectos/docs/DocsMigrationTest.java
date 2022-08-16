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

import java.io.IOException;
import objectos.docs.style.JavaCss;
import objectos.docs.style.SyntaxCss;
import objectos.docs.style.XmlCss;
import org.jsoup.Jsoup;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DocsMigrationTest {

  private DocumentProcessor processor;

  private String[] reps;

  @BeforeClass
  public void _beforeClass() {
    processor = new DocumentProcessor();

    reps = new String[] {
        "jyf", SyntaxCss._PRE.className(),
        "njs", XmlCss._TEXT.className(),
        "jrq", XmlCss._SYMBOL.className(),
        "cyc", XmlCss._TAG_NAME.className(),

        "igz", JavaCss._IDENTIFIER.className(),
        "tu9", JavaCss._WS.className(),
        "nhu", JavaCss._TOKEN.className(),
        "iec", JavaCss._KEYWORD.className(),
        "qbr", JavaCss._DIGITS.className(),
        "uc6", JavaCss._STRING.className(),
        "wjs", JavaCss._COMMENT.className(),
        "juq", JavaCss._ANNOTATION.className()
    };
  }

  @Test
  public void next() throws IOException {
    test(reps, Version.NEXT);
  }

  @Test
  public void v0001() throws IOException {
    test(reps, Version.V0_1_0);
  }

  @Test
  public void v0002() throws IOException {
    test(reps, Version.V0_2_0);
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
    var source = doc.contents();

    var document = Jsoup.parseBodyFragment(source);

    var body = document.body();

    var article = body.selectFirst("article");

    return article.toString();
  }

  private void test(String slug, String key, String... replacements) throws IOException {
    Document doc;

    try {
      doc = processor.load(slug, key);
    } catch (UnsupportedOperationException e) {
      throw new IOException(key, e);
    }

    var actual = normalize(doc);

    var expected = load(slug, key);

    var rep = 0;

    while (rep < replacements.length) {
      expected = expected.replace(
        replacements[rep++],
        replacements[rep++]
      );
    }

    if (!actual.equals(expected)) {
      int len = Math.min(actual.length(), expected.length());

      var index = 0;

      for (int i = 0; i < len; i++) {
        char ca = actual.charAt(i);
        char ce = expected.charAt(i);

        if (ca == ce) {
          continue;
        }

        index = i;

        break;
      }

      var start = Math.max(0, index - 20);

      var end = Math.min(len, index + 30);

      Assert.fail(
        """

        key=%s
        ----
        %s
        %s
        ----
        %s
        ----
        %s
        ----
        """.formatted(
          key,
          actual.substring(start, end),
          expected.subSequence(start, end),
          actual,
          expected
        ));
    }
  }

  private void test(String[] reps, Version version) throws IOException {
    var slug = version.slug();

    processor.slug(slug);

    var resourceDirectory = version.resourceDirectory();

    for (var key : version.keys()) {
      test(resourceDirectory, key, reps);
    }
  }

}