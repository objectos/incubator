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

  @Test(enabled = false, description = //
  """
  = Bold (constrained)

  - start of line
  - ends in space
  - ends in punctuation
  - not bold, just star char
  """)
  public final void bold01() {
    test(
      """
      *a* *b*, *c*; *d*. *e*
      """,

      p0(
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
      //        Lexer.Symbol.EOF, 68
      ),

      p1(
        //        Parser.Code.START_DOCUMENT,
      //        Parser.Code.START_PREAMBLE,
      //        Parser.Code.START_PARAGRAPH,
      //        Parser.Code.START_MONOSPACE,
      //        Parser.Code.TEXT, 0, // at
      //        Parser.Code.END_MONOSPACE,
      //        Parser.Code.TEXT, 1, // start of line at
      //        Parser.Code.START_MONOSPACE,
      //        Parser.Code.TEXT, 2, // the middle
      //        Parser.Code.END_MONOSPACE,
      //        Parser.Code.TEXT, 3, // of line at the end
      //        Parser.Code.START_MONOSPACE,
      //        Parser.Code.TEXT, 4, // of the line
      //        Parser.Code.END_MONOSPACE,
      //        Parser.Code.TEXT, 5, // ws
      //
      //        Parser.Code.END_PARAGRAPH,
      //        Parser.Code.END_PREAMBLE,
      //        Parser.Code.END_DOCUMENT
      ),

      p2(),

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

  @Test(description = //
  """
  doctitle + eof

  - happy path
  - title ends @ eof

  '''
  = The doctitle'''

  P0: ^ H1-0,2 W2,5 SP W6,14 $ EOF

  P1: DOC_START
      HEADING_START
      1 P0 start end
      HEADING_END
      DOC_END
  """)
  public final void doctitle01() {
    test(
      """
      = The doctitle""",

      p0(
        Token.LINE_START,
        Token.HEADING, 1, 0, 2,
        Token.WORD, 2, 5,
        Token.SP,
        Token.WORD, 6, 14,
        Token.LINE_END,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.HEADING_START, 1,
        Code.TOKENS, 5, 12,
        Code.HEADING_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(Text.REGULAR, 2, 14)
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

  @Test(enabled = false, description = //
  """
  doctitle + NL

  - happy path
  - title ends @ NL
  - with preamble

  '''
  = Test document

  Some preamble
  '''

  P0: ^ = SP W2,6 SP W7,15 $ LF
      ^ $ LF
      ^ W17,21 SP W22,30 $ LF
      ^ $ EOF

  P1: DOC_START
      TITLE 1 P0 start end
      PREAMBLE_START
      PARAGRAPH P0 start end
      PREAMBLE_END
      DOC_END
  """)
  public final void doctitle02() {
    test(
      """
      = Test document

      Some preamble
      """,

      p0(
        //        Lexer.Symbol.TITLE, 1,
      //        Lexer.Symbol.REGULAR, 2, 15,
      //        Lexer.Symbol.LF, 15,
      //        Lexer.Symbol.LF, 16,
      //        Lexer.Symbol.REGULAR, 17, 30,
      //        Lexer.Symbol.LF, 30,
      //        Lexer.Symbol.EOF, 31
      ),

      p1(
        //        Parser.Code.START_DOCUMENT,
      //        Parser.Code.START_TITLE, 1,
      //        Parser.Code.TEXT, 0,
      //        Parser.Code.END_TITLE,
      //        Parser.Code.START_PREAMBLE,
      //        Parser.Code.START_PARAGRAPH,
      //        Parser.Code.TEXT, 1,
      //        Parser.Code.NL,
      //        Parser.Code.END_PARAGRAPH,
      //        Parser.Code.END_PREAMBLE,
      //        Parser.Code.END_DOCUMENT
      ),

      p2(),

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
  doctitle + monospace

  - title has inline element (monospace)
  - title ends @ NL

  '''
  = The `Foo` class
  '''

  L0: ^ = SP W2,5 SP ` W7,10 ` SP W12,17 $ LF
      ^ $ EOF

  L1: ^ T1 R2,6 <M R7,10 M> R11,17 $ LF
      ^ $ EOF
  """)
  public final void doctitle03() {
    test(
      """
      = The `Foo` class
      """,

      p0(
        //        Lexer.Symbol.TITLE, 1,
      //        Lexer.Symbol.REGULAR, 2, 6,
      //        Lexer.Symbol.MONOSPACE, 7, 10,
      //        Lexer.Symbol.REGULAR, 11, 17,
      //        Lexer.Symbol.LF, 17,
      //        Lexer.Symbol.EOF, 18
      ),

      p1(
        //        Parser.Code.START_DOCUMENT,
      //        Parser.Code.START_TITLE, 1,
      //        Parser.Code.TEXT, 0,
      //        Parser.Code.START_MONOSPACE,
      //        Parser.Code.TEXT, 1,
      //        Parser.Code.END_MONOSPACE,
      //        Parser.Code.TEXT, 2,
      //        Parser.Code.END_TITLE,
      //        Parser.Code.END_DOCUMENT
      ),

      p2(),

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
  doctitle + monospace (@ start)

  - title has inline element (monospace)
  - inline elements starts the title
  - title ends @ NL
  """)
  public final void doctitle04() {
    test("= `A`",

      p0(
        //        Lexer.Symbol.TITLE, 1,
      //        Lexer.Symbol.MONOSPACE, 3, 4,
      //        Lexer.Symbol.EOF, 5
      ),

      p1(
        //        Parser.Code.START_DOCUMENT,
      //        Parser.Code.START_TITLE, 1,
      //        Parser.Code.START_MONOSPACE,
      //        Parser.Code.TEXT, 0,
      //        Parser.Code.END_MONOSPACE,
      //        Parser.Code.END_TITLE,
      //        Parser.Code.END_DOCUMENT
      ),

      p2(),

      """
      <div id="header">
      <h1><code>A</code></h1>
      </div>
      <div id="content">

      </div>
      </div>
      """
    );
  }

  @Test(enabled = false, description = //
  """
  doctitle (not a doctitle)

  - not a title (no space after symbol '=')

  0123456789
  '''
  =NotTitle
  '''

  P0: ^ = X1,9 $ LF
      ^ $ EOF

  P1: DOC_START
      PREAMBLE_START
      PARAGRAPH P0 start end
      PREAMBLE_END
      DOC_END
  """)
  public final void doctitle05() {
    test(
      """
      =Not Title
      """,

      p0(
        //        Lexer.Symbol.REGULAR, 0, 10,
      //        Lexer.Symbol.LF, 10,
      //        Lexer.Symbol.EOF, 11
      ),

      p1(
        //        Parser.Code.START_DOCUMENT,
      //        Parser.Code.START_PREAMBLE,
      //        Parser.Code.START_PARAGRAPH,
      //        Parser.Code.TEXT, 0,
      //        Parser.Code.NL,
      //        Parser.Code.END_PARAGRAPH,
      //        Parser.Code.END_PREAMBLE,
      //        Parser.Code.END_DOCUMENT
      ),

      p2(),

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
  listing block

  - w/ attribute
  - delimited

  01234567
  89012
  3456789
  01234
  '''
  [,java]
  ----
  break;
  ----
  '''

  P0: ^ [ , W2,6 ] $ LF
      ^ DLB $ LF
      ^ X13,19 $ LF
      ^ DLB $ LF
      ^ $ EOF

  P1: DOCUMENT_START
      DELIMITED_LISTING_BLOCK_START
      ATTR 1,1
      ATTR 2,6
      P0 start end
      DELIMITED_LISTING_BLOCK_END
      DOCUMENT_END
  """)
  public final void listingBlock01() {
  }

  @Test(enabled = false, description = //
  """
  monospace

  - constrained
  - words only
  - well-formed

  '''
  `a` `b`, `c`
  '''

  L0: ^ ` W1,2 ` SP ` W5,6 ` X7,8 SP ` W10,11 ` $ LF
      ^ $ EOF

  L1: ^ <M R1,2 M> R3,4 <M R5,6 M> R7,9 <M R10,11 M> $ LF
      ^ $ EOF
  """)
  public final void monospace01() {
    test(
      """
      `a` `b`, `c`; `d`. `e`
      """,

      p0(
        //        Lexer.Symbol.MONOSPACE, 1, 2,
      //        Lexer.Symbol.REGULAR, 3, 4,
      //        Lexer.Symbol.MONOSPACE, 5, 6,
      //        Lexer.Symbol.REGULAR, 7, 9,
      //        Lexer.Symbol.MONOSPACE, 10, 11,
      //        Lexer.Symbol.EOF, 68
      ),

      p1(
        //        Parser.Code.START_DOCUMENT,
      //        Parser.Code.START_PREAMBLE,
      //        Parser.Code.START_PARAGRAPH,
      //        Parser.Code.START_MONOSPACE,
      //        Parser.Code.TEXT, 0, // at
      //        Parser.Code.END_MONOSPACE,
      //        Parser.Code.TEXT, 1, // start of line at
      //        Parser.Code.START_MONOSPACE,
      //        Parser.Code.TEXT, 2, // the middle
      //        Parser.Code.END_MONOSPACE,
      //        Parser.Code.TEXT, 3, // of line at the end
      //        Parser.Code.START_MONOSPACE,
      //        Parser.Code.TEXT, 4, // of the line
      //        Parser.Code.END_MONOSPACE,
      //        Parser.Code.TEXT, 5, // ws
      //
      //        Parser.Code.END_PARAGRAPH,
      //        Parser.Code.END_PREAMBLE,
      //        Parser.Code.END_DOCUMENT
      ),

      p2(),

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
  monospace

  - constrained
  - phrases
  - well-formed

            1
  012345678901234567
  '''
  `a b` `c d`, `d e`
  '''

  L0: ^ ` W1,2 SP W3,4 ` SP ` W7,8 SP W9,10 ` X11,12 SP ` W14,15 SP W16,17 ` $ LF
      ^ $ EOF

  L1: ^ <M R1,4 M> R4,6 <M R7,10 M> R11,13 <M R14,17 M> $ LF
      ^ $ EOF
  """)
  public final void monospace02() {
  }

  @Test(enabled = false, description = //
  """
  monospace + nested bold

  - mono constrained
  - bold constrained
  - same length
  - words
  - well-formed

            1
  012345678901234567
  '''
  `*a*` `*b*`; `*c*`
  '''

  L0: ^ ` * W2,3 * ` SP ` * W8,9 * ` X11,12 SP ` * W15,16 * ` $ LF
      ^ $ EOF

  L1: ^ <M R2,3 M> R5,6 <M R8,9 M> R11,13 <M R15,16 M> $ LF
      ^ $ EOF
  """)
  public final void monospace03() {
  }

  @Test(enabled = false, description = //
  """
  monospace (not monospace)

  - ill-formed -> chunk

  0123
  '''
  `a b
  '''

  L0: ^ ` W1,2 SP W3,4 $ LF
      ^ $ EOF

  L1: ^ R0,4 $ LF
      ^ $ EOF
  """)
  public final void monospace04() {
  }

  @Test(enabled = false, description = //
  """
  monospace

  - multiline

  0123
  4567
  '''
  `a b
  c` e
  '''

  L0: ^ ` W1,2 SP W3,4 $ LF
      ^ W4,5 ` SP W7,8 $ LF
      ^ $ EOF

  L1: ^ <M R1,4 $ LF
      ^ R4,5 >M R6,8 $ LF
      ^ $ EOF
  """)
  public final void monospace05() {
  }

  @Test(enabled = false, description = //
  """
  unordered list

  - simple elements
  - dash
  - single level

  0123
  4567
  8901
  '''
  - a
  - b
  - c
  '''

  P0: ^ - SP W2,3 $ LF
      ^ - SP W6,7 $ LF
      ^ - SP W10,11 $ LF
      ^ $ EOF

  P1: DOC_START
      PREAMBLE_START
      ULIST_START
      LI_START 1
      PARAGRAPH P0 start end
      LI_START 1
      PARAGRAPH P0 start end
      LI_START 1
      PARAGRAPH P0 start end
      ULIST_END
      PREAMBLE_END
      DOC_END
  """)
  public final void unorderedList01() {
  }

  @Test(enabled = false, description = //
  """
  unordered list

  - complex elements
  - dash
  - single level

  0123
  4567
  8901
  2345
  '''
  - a
  bcd
  - e
  fgh
  '''

  L0: ^ - SP W2,3 $ LF
      ^ W4,7 $ LF
      ^ - SP W10,11 $ LF
      ^ W12,15 $ LF
      ^ $ EOF

  P1: DOC_START
      PREAMBLE_START
      ULIST_START
      LI_START 1
      PARAGRAPH P0 start end
      LI_START 1
      PARAGRAPH P0 start end
      ULIST_END
      PREAMBLE_END
      DOC_END
  """)
  public final void unorderedList02() {
  }

  @Test(enabled = false, description = //
  """
  unordered list

  - nested unordered list
  - asterisk

  0123
  45678
  90123
  4567
  '''
  * a
  ** b
  ** c
  * d
  '''

  P0: ^ * SP W2,3 $ LF
      ^ * * SP W7,8 $ LF
      ^ * * SP W12,13 $ LF
      ^ * SP W16,17 $ LF
      ^ $ EOF

  P1: DOC_START
      PREAMBLE_START
      ULIST_START
      LI_START
      PARAGRAPH P0 start end
      ULIST_START
      LI_START
      PARAGRAPH P0 start end
      LI_START
      PARAGRAPH P0 start end
      ULIST_END
      LI_START
      PARAGRAPH P0 start end
      ULIST_END
      PREAMBLE_END
      DOC_END
  """)
  public final void unorderedList03() {
  }

  @Test(enabled = false, description = //
  """
  unordered list

  - indented nested unordered list
  - asterisk

  0123
  4567
  8901
  2345
  '''
  - a
  bcd
  - e
  fgh
  '''

  L0: ^ - SP W2,3 $ LF
      ^ W4,7 $ LF
      ^ - SP W10,11 $ LF
      ^ W12,15 $ LF
      ^ $ EOF

  L1: ^ UL1 R2,3 $ LF
      ^ R4,7 $ LF
      ^ UL1 R10,11 $ LF
      ^ R12,15 $ LF
      ^ $ EOF
  """)
  public final void unorderedList04() {
  }

  final String normalize(String html) {
    Document fragment = Jsoup.parseBodyFragment(html);

    Element body = fragment.body();

    return body.toString();
  }

  void test(
      String source, int[] expected0, int[] expected1, int[][] expected2, String expectedHtml) {
    asciiDoc.process(source, processor);

    var result = processor.toString();

    testHtml(result, expectedHtml);
  }

  final void testArrays(int[] result, int[] expected, String header) {
    var msg = """

    %s
    actual  =%s
    expected=%s

    """.formatted(header, Arrays.toString(result), Arrays.toString(expected));

    assertEquals(result, expected, msg);
  }

  final void testHtml(String result, String expected) {
    assertEquals(normalize(result), normalize(expected));
  }

  private int[] p0(int... values) { return values; }

  private int[] p1(int... values) { return values; }

  private int[][] p2(int[]... values) { return values; }

  private int[] t(int... values) { return values; }

}