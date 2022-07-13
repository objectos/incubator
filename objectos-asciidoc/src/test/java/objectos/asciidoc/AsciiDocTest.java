/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package objectos.asciidoc;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AsciiDocTest {

  private class ThisProcessor implements AsciiDoc.Processor {
    private boolean contentStarted;

    private int level;

    private final StringBuilder sb = new StringBuilder();

    @Override
    public final void endDocument() {
      if (!contentStarted) {
        sb.append(
          """
          <div id="content">

          """);
      }

      sb.append("</div>");
    }

    @Override
    public final void endParagraph() {
      sb.append("</p></div>");
    }

    @Override
    public final void endPreamble() {
    }

    @Override
    public final void endTitle() {
      sb.append("</h");
      sb.append(level);
      sb.append(">\n</div>\n");
    }

    @Override
    public final void startDocument() {
      contentStarted = false;

      level = 0;

      sb.setLength(0);
    }

    @Override
    public final void startParagraph() {
      sb.append("<div class=\"paragraph\">\n<p>");
    }

    @Override
    public final void startPreamble() {
      startContent();
    }

    @Override
    public final void startTitle(int level) {
      sb.append("<div id=\"header\">\n");
      sb.append("<h");
      sb.append(level);
      sb.append(">");

      this.level = level;
    }

    @Override
    public final void text(String s) {
      sb.append(s);
    }

    @Override
    public final String toString() {
      asciiDoc.process0(this);

      return sb.toString();
    }

    private void startContent() {
      if (!contentStarted) {
        sb.append("<div id=\"content\">\n");

        contentStarted = true;
      }
    }
  }

  private AsciiDoc asciiDoc;

  private ThisProcessor processor;

  @BeforeClass
  public void _beforeClass() {
    asciiDoc = AsciiDoc.create();

    processor = new ThisProcessor();
  }

  @Test(description = //
  """
  = Document title

  - happy path
  - title ends @ eof
  """)
  public final void testCase01() {
    test(
      """
      = The doctitle""",

      """
      <div id="header">
      <h1>The doctitle</h1>
      </div>
      <div id="content">

      </div>
      """
    );
  }

  @Test(description = //
  """
  = Document title

  - happy path
  - title ends @ NL
  - with preamble
  """)
  public final void testCase02() {
    test(
      """
      = Test document

      Some preamble
      """,

      codes(
        Parser.Code.START_DOCUMENT,
        Parser.Code.START_TITLE, 1,
        Parser.Code.TEXT, 0,
        Parser.Code.END_TITLE,
        Parser.Code.START_PREAMBLE,
        Parser.Code.START_PARAGRAPH,
        Parser.Code.TEXT, 1,
        Parser.Code.END_PARAGRAPH,
        Parser.Code.END_PREAMBLE,
        Parser.Code.END_DOCUMENT
      ),

      """
      <div id="header">
      <h1>Test document</h1>
      </div>
      <div id="content">
      <div class="paragraph">
      <p>Some preamble</p>
      </div>
      </div>
      """
    );
  }

  @Test(enabled = false, description = //
  """
  = Document title

  - title has inline element (code)
  - title ends @ NL
  """)
  public final void testCase03() {
    test(
      """
      = The `Foo` class
      """,

      """
      <div id="header">
      <h1>The <code>Foo</code> class</h1>
      </div>
      <div id="content">

      </div>
      </div>
      """
    );
  }

  @Test(enabled = false, description = //
  """
  = Document title

  - not a title (no space after symbol '=')
  """)
  public final void testCase04() {
    test(
      """
      =Not Title
      """,

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p>=Not Title</p>
      </div>
      </div>
      </body>
      """
    );
  }

  String convert(String source) {
    var html = processor.toString();

    return normalize(html);
  }

  final String normalize(String html) {
    Document fragment = Jsoup.parseBodyFragment(html);

    Element body = fragment.body();

    return body.toString();
  }

  private int[] codes(int... values) { return values; }

  private void test(String source, int[] expectedCodes, String expectedHtml) {
    int[] actual;

    if (asciiDoc != null) {
      asciiDoc.parse(source);

      actual = asciiDoc.toCode();
    } else {
      actual = expectedCodes;
    }

    assertEquals(
      actual,
      expectedCodes,
      """

      actual  =%s
      expected=%s

      """.formatted(Arrays.toString(actual), Arrays.toString(expectedCodes))
    );

    assertEquals(
      convert(source),

      normalize(expectedHtml)
    );
  }

  private void test(String source, String expectedHtml) {
    if (asciiDoc != null) {
      asciiDoc.parse(source);
    }

    assertEquals(
      convert(source),

      normalize(expectedHtml)
    );
  }

}