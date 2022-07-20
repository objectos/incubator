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

  AsciiDoc asciiDoc;

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

      lexer(
        Lexer.Symbol.TITLE, 1,
        Lexer.Symbol.REGULAR, 2,
        Lexer.Symbol.EOF, 14
      ),

      parser(
        Parser.Code.START_DOCUMENT,
        Parser.Code.START_TITLE, 1,
        Parser.Code.TEXT, 0,
        Parser.Code.END_TITLE,
        Parser.Code.END_DOCUMENT
      ),

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

      lexer(
        Lexer.Symbol.TITLE, 1,
        Lexer.Symbol.REGULAR, 2,
        Lexer.Symbol.LF, 15,
        Lexer.Symbol.LF, 16,
        Lexer.Symbol.REGULAR, 17,
        Lexer.Symbol.LF, 30,
        Lexer.Symbol.EOF, 31
      ),

      parser(
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

      lexer(
        Lexer.Symbol.TITLE, 1,
        Lexer.Symbol.REGULAR, 2,
        Lexer.Symbol.MONOSPACE, 6, 10,
        Lexer.Symbol.REGULAR, 11,
        Lexer.Symbol.LF, 17,
        Lexer.Symbol.EOF, 18
      ),

      parser(
        Parser.Code.START_DOCUMENT,
        Parser.Code.START_TITLE, 1,
        Parser.Code.TEXT, 0,
        Parser.Code.START_MONOSPACE,
        Parser.Code.TEXT, 1,
        Parser.Code.END_MONOSPACE,
        Parser.Code.TEXT, 2,
        Parser.Code.END_TITLE,
        Parser.Code.END_DOCUMENT
      ),

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
  public final void testCase05() {
    test(
      """
      =Not Title
      """,

      lexer(
        //        Lexer.Symbol.WORD, 0,
        //        Lexer.Symbol.WORD, 5,
        Lexer.Symbol.LF, 10,
        Lexer.Symbol.EOF, 11
      ),

      parser(
        Parser.Code.START_DOCUMENT,
        Parser.Code.START_PREAMBLE,
        Parser.Code.START_PARAGRAPH,
        Parser.Code.TEXT, 0,
        Parser.Code.END_PARAGRAPH,
        Parser.Code.END_PREAMBLE,
        Parser.Code.END_DOCUMENT
      ),

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

  @Test(enabled = false, description = //
  """
  = Monospace

  - start of line
  - middle of line
  - end of line
  """)
  public final void testCaseX1() {
    test(
      """
      `at` start of line
      at `the middle` of line
      at the end `of the line`
      """,

      lexer(
        //        Lexer.Symbol.PARAGRAPH, 0,
        //        Lexer.Symbol.BACKTICK, 0,
        //        Lexer.Symbol.BACKTICK, 3,
        //        Lexer.Symbol.EOL, 18,
        //
        //        Lexer.Symbol.PARAGRAPH, 19,
        //        Lexer.Symbol.BACKTICK, 22,
        //        Lexer.Symbol.BACKTICK, 33,
        //        Lexer.Symbol.EOL, 42,
        //
        //        Lexer.Symbol.PARAGRAPH, 43,
        //        Lexer.Symbol.BACKTICK, 54,
        //        Lexer.Symbol.BACKTICK, 66,
        //        Lexer.Symbol.EOL, 67,
        //
        //        Lexer.Symbol.EMPTY, 68,
        Lexer.Symbol.EOF, 68
      ),

      parser(
        Parser.Code.START_DOCUMENT,
        Parser.Code.START_PREAMBLE,
        Parser.Code.START_PARAGRAPH,
        Parser.Code.START_MONOSPACE,
        Parser.Code.TEXT, 0, // at
        Parser.Code.END_MONOSPACE,
        Parser.Code.TEXT, 1, // start of line at
        Parser.Code.START_MONOSPACE,
        Parser.Code.TEXT, 2, // the middle
        Parser.Code.END_MONOSPACE,
        Parser.Code.TEXT, 3, // of line at the end
        Parser.Code.START_MONOSPACE,
        Parser.Code.TEXT, 4, // of the line
        Parser.Code.END_MONOSPACE,
        Parser.Code.TEXT, 5, // ws

        Parser.Code.END_PARAGRAPH,
        Parser.Code.END_PREAMBLE,
        Parser.Code.END_DOCUMENT
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p><code>at</code> start of line at <code>the middle</code> of line at the end <code>of the line</code></p>
      </div>
      </div>
      </body>
      """
    );
  }

  @Test(enabled = false, description = //
  """
  = Bold (constrained)

  - start of line
  - ends in space
  - ends in punctuation
  - not bold, just star char
  """)
  public final void testCaseX2() {
    test(
      """
      *a* *b*, *c*; *d*. *e*
      """,

      lexer(
        //        Lexer.Symbol.PARAGRAPH, 0,
        //        Lexer.Symbol.CBOLD0, 0,
        //        Lexer.Symbol.CBOLD9, 2, // a
        //        Lexer.Symbol.CBOLD0, 0,
        //        Lexer.Symbol.CBOLD9, 2, // b
        //        Lexer.Symbol.CBOLD0, 0,
        //        Lexer.Symbol.CBOLD9, 2, // c
        //        Lexer.Symbol.CBOLD0, 0,
        //        Lexer.Symbol.CBOLD9, 2, // d
        //        Lexer.Symbol.CBOLD0, 0,
        //        Lexer.Symbol.CBOLD9, 2, // e
        //        Lexer.Symbol.EOL, 42,
        //
        //        Lexer.Symbol.EMPTY, 68,
        Lexer.Symbol.EOF, 68
      ),

      parser(
        Parser.Code.START_DOCUMENT,
        Parser.Code.START_PREAMBLE,
        Parser.Code.START_PARAGRAPH,
        Parser.Code.START_MONOSPACE,
        Parser.Code.TEXT, 0, // at
        Parser.Code.END_MONOSPACE,
        Parser.Code.TEXT, 1, // start of line at
        Parser.Code.START_MONOSPACE,
        Parser.Code.TEXT, 2, // the middle
        Parser.Code.END_MONOSPACE,
        Parser.Code.TEXT, 3, // of line at the end
        Parser.Code.START_MONOSPACE,
        Parser.Code.TEXT, 4, // of the line
        Parser.Code.END_MONOSPACE,
        Parser.Code.TEXT, 5, // ws

        Parser.Code.END_PARAGRAPH,
        Parser.Code.END_PREAMBLE,
        Parser.Code.END_DOCUMENT
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p><code>at</code> start of line at <code>the middle</code> of line at the end <code>of the line</code></p>
      </div>
      </div>
      </body>
      """
    );
  }

  String convert(String source) {
    asciiDoc.process0(processor);

    var html = processor.toString();

    return normalize(html);
  }

  final String normalize(String html) {
    Document fragment = Jsoup.parseBodyFragment(html);

    Element body = fragment.body();

    return body.toString();
  }

  private int[] lexer(int... values) { return values; }

  private int[] parser(int... values) { return values; }

  private void test(String source, int[] expectedLexer, int[] expectedParser, String expectedHtml) {
    if (asciiDoc != null) {
      asciiDoc.tokenize(source);

      if (expectedLexer.length > 0) {
        var symbol = asciiDoc.toSymbol();

        assertEquals(
          symbol,
          expectedLexer,
          """

        Lexer assertion failed
        actual  =%s
        expected=%s

        """.formatted(Arrays.toString(symbol), Arrays.toString(expectedLexer))
        );
      }

      asciiDoc.parse();

      if (expectedParser.length > 0) {
        var parser = asciiDoc.toCode();

        assertEquals(
          parser,
          expectedParser,
          """

        Parser assertion failed
        actual  =%s
        expected=%s

        """.formatted(Arrays.toString(parser), Arrays.toString(expectedParser))
        );
      }
    }

    assertEquals(
      convert(source),

      normalize(expectedHtml)
    );
  }

}