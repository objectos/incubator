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
  = bold

  - constrained
  - words only
  - well-formed

  0123456789012
  '''
  *a* *b*, *c*
  '''

  P0: ^ * B1,2 * B3,4 * B5,6 * B7,9 * B10,11 * $ LF
      ^ $ EOF

  P2: ^ <B R1,2 B> R3,4 <B R5,6 B> R7,9 <B R10,11 B> $ LF
      ^ $ EOF
  """)
  public final void bold01() {
    test(
      """
      *a* *b*, *c*
      """,

      p0(
        Token.LINE_START,
        Token.BOLD_START, 0,
        Token.BLOB, 1, 2,
        Token.BOLD_END, 2,
        Token.BLOB, 3, 4,
        Token.BOLD_START, 4,
        Token.BLOB, 5, 6,
        Token.BOLD_END, 6,
        Token.BLOB, 7, 9,
        Token.BOLD_START, 9,
        Token.BLOB, 10, 11,
        Token.BOLD_END, 11,
        Token.LINE_END,
        Token.LF,
        Token.LINE_START,
        Token.LINE_END,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 1, 31,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(
          Text.BOLD_START,
          Text.REGULAR, 1, 2,
          Text.BOLD_END,
          Text.REGULAR, 3, 4,
          Text.BOLD_START,
          Text.REGULAR, 5, 6,
          Text.BOLD_END,
          Text.REGULAR, 7, 9,
          Text.BOLD_START,
          Text.REGULAR, 10, 11,
          Text.BOLD_END
        )
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p><strong>a</strong> <strong>b</strong>, <strong>c</strong></p>
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

  P0: ^ H1-0,2 B2,14 $ EOF

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
        Token.BLOB, 2, 14,
        Token.LINE_END,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.HEADING_START, 1,
        Code.TOKENS, 5, 8,
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

  @Test(description = //
  """
  doctitle + NL

  - happy path
  - title ends @ NL

  '''
  = The doctitle
  '''

  P0: ^ H1-0,2 B2,14 $ LF
      ^ $ EOF

  P1: DOC_START
      HEADING_START
      1 P0 start end
      HEADING_END
      DOC_END
  """)
  public final void doctitle02() {
    test(
      """
      = The doctitle
      """,

      p0(
        Token.LINE_START,
        Token.HEADING, 1, 0, 2,
        Token.BLOB, 2, 14,
        Token.LINE_END,
        Token.LF,
        Token.LINE_START,
        Token.LINE_END,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.HEADING_START, 1,
        Code.TOKENS, 5, 8,
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
      </div>
      """
    );
  }

  @Test(description = //
  """
  doctitle (not a doctitle)

  - not a title (no space after symbol '=')

  0123456789
  '''
  =Not Title
  '''

  P0: ^ B0,10 $ LF
      ^ $ EOF

  P1: DOC_START
      PREAMBLE_START
      PARAGRAPH P0 start end
      PREAMBLE_END
      DOC_END
  """)
  public final void doctitle03() {
    test(
      """
      =Not Title
      """,

      p0(
        Token.LINE_START,
        Token.BLOB, 0, 10,
        Token.LINE_END,
        Token.LF,
        Token.LINE_START,
        Token.LINE_END,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 1, 7,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(Text.REGULAR, 0, 10)
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

  @Test(description = //
  """
  monospace

  - constrained
  - words only
  - well-formed

  0123456789012
  '''
  `a` `b`, `c`
  '''

  P0: ^ ` B1,2 ` B3,4 ` B5,6 ` B7,9 ` B10,11 ` $ LF
      ^ $ EOF

  P2: ^ <M R1,2 M> R3,4 <M R5,6 M> R7,9 <M R10,11 M> $ LF
      ^ $ EOF
  """)
  public final void monospace01() {
    test(
      """
      `a` `b`, `c`
      """,

      p0(
        Token.LINE_START,
        Token.MONO_START, 0,
        Token.BLOB, 1, 2,
        Token.MONO_END, 2,
        Token.BLOB, 3, 4,
        Token.MONO_START, 4,
        Token.BLOB, 5, 6,
        Token.MONO_END, 6,
        Token.BLOB, 7, 9,
        Token.MONO_START, 9,
        Token.BLOB, 10, 11,
        Token.MONO_END, 11,
        Token.LINE_END,
        Token.LF,
        Token.LINE_START,
        Token.LINE_END,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 1, 31,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(
          Text.MONOSPACE_START,
          Text.REGULAR, 1, 2,
          Text.MONOSPACE_END,
          Text.REGULAR, 3, 4,
          Text.MONOSPACE_START,
          Text.REGULAR, 5, 6,
          Text.MONOSPACE_END,
          Text.REGULAR, 7, 9,
          Text.MONOSPACE_START,
          Text.REGULAR, 10, 11,
          Text.MONOSPACE_END
        )
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p><code>a</code> <code>b</code>, <code>c</code></p>
      </div>
      </div>
      </body>
      """
    );
  }

  @Test(description = //
  """
  monospace

  - constrained
  - phrases
  - well-formed

            1
  012345678901234567
  '''
  `a b` `c d`, `e f`
  '''

  L0: ^ ` W1,2 SP W3,4 ` SP ` W7,8 SP W9,10 ` X11,12 SP ` W14,15 SP W16,17 ` $ LF
      ^ $ EOF

  L1: ^ <M R1,4 M> R4,6 <M R7,10 M> R11,13 <M R14,17 M> $ LF
      ^ $ EOF
  """)
  public final void monospace02() {
    test(
      """
      `a b` `c d`, `e f`
      """,

      p0(
        Token.LINE_START,
        Token.MONO_START, 0,
        Token.BLOB, 1, 4,
        Token.MONO_END, 4,
        Token.BLOB, 5, 6,
        Token.MONO_START, 6,
        Token.BLOB, 7, 10,
        Token.MONO_END, 10,
        Token.BLOB, 11, 13,
        Token.MONO_START, 13,
        Token.BLOB, 14, 17,
        Token.MONO_END, 17,
        Token.LINE_END,
        Token.LF,
        Token.LINE_START,
        Token.LINE_END,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 1, 31,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(
          Text.MONOSPACE_START,
          Text.REGULAR, 1, 4,
          Text.MONOSPACE_END,
          Text.REGULAR, 5, 6,
          Text.MONOSPACE_START,
          Text.REGULAR, 7, 10,
          Text.MONOSPACE_END,
          Text.REGULAR, 11, 13,
          Text.MONOSPACE_START,
          Text.REGULAR, 14, 17,
          Text.MONOSPACE_END
        )
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p><code>a b</code> <code>c d</code>, <code>e f</code></p>
      </div>
      </div>
      </body>
      """
    );
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