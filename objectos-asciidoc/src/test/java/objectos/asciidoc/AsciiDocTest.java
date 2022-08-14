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
import java.util.LinkedHashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class AsciiDocTest {

  AsciiDoc asciiDoc;

  private ThisProcessor processor;

  @BeforeClass
  public void _beforeClass() {
    if (asciiDoc == null) {
      asciiDoc = AsciiDoc.create();

      processor = new ThisProcessor();
    }
  }

  @Factory
  public Object[] _factory() {
    return new Object[] {
        new AttributeListTest(this),
        new ConstrainedBoldTest(this),
        new ConstrainedMonospaceTest(this),
        new DocumentAttributeTest(this),
        new InlineMacroTest(this),
        new ListingBlockTest(this),
        new ParagraphTest(this),
        new PreambleTest(this),
        new SectionTest(this),
        new SourceCodeBlockTest(this),
        new UnorderedListTest(this),
        new UrlMacroTest(this)
    };
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
        Token.HEADING, 1, 0, 2,
        Token.BLOB, 2, 14,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.HEADING_START, 1,
        Code.TOKENS, 4, 7,
        Code.HEADING_END,
        Code.DOCUMENT_END
      ),

      docAttr(),

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
        Token.HEADING, 1, 0, 2,
        Token.BLOB, 2, 14,
        Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.HEADING_START, 1,
        Code.TOKENS, 4, 7,
        Code.HEADING_END,
        Code.DOCUMENT_END
      ),

      docAttr(),

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
        Token.BLOB, 0, 10,
        Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 0, 3,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      docAttr(),

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

  @Test(description = //
  """
  = italic

  - constrained
  - words only
  - well-formed

  0123456789012
  '''
  _a_ _b_, _c_
  '''

  P0: ^ _ B1,2 _ B3,4 _ B5,6 _ B7,9 _ B10,11 _ $ LF
      ^ $ EOF

  P2: ^ <I R1,2 I> R3,4 <I R5,6 I> R7,9 <I R10,11 I> $ LF
      ^ $ EOF
  """)
  public final void italic01() {
    test(
      """
      _a_ _b_, _c_
      """,

      p0(
        Token.ITALIC_START, 0,
        Token.BLOB, 1, 2,
        Token.ITALIC_END, 2,
        Token.BLOB, 3, 4,
        Token.ITALIC_START, 4,
        Token.BLOB, 5, 6,
        Token.ITALIC_END, 6,
        Token.BLOB, 7, 9,
        Token.ITALIC_START, 9,
        Token.BLOB, 10, 11,
        Token.ITALIC_END, 11,
        Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 0, 27,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      docAttr(),

      p2(
        t(
          Text.ITALIC_START,
          Text.REGULAR, 1, 2,
          Text.ITALIC_END,
          Text.REGULAR, 3, 4,
          Text.ITALIC_START,
          Text.REGULAR, 5, 6,
          Text.ITALIC_END,
          Text.REGULAR, 7, 9,
          Text.ITALIC_START,
          Text.REGULAR, 10, 11,
          Text.ITALIC_END
        )
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p><em>a</em> <em>b</em>, <em>c</em></p>
      </div>
      </div>
      </body>
      """
    );
  }

  @Test(description = //
  """
  italic

  - constrained
  - phrases
  - well-formed

  012345678901234567
  '''
  _a b_ _c d_, _e f_
  '''

  L0: ^ _ W1,2 SP W3,4 _ SP _ W7,8 SP W9,10 _ X11,12 SP _ W14,15 SP W16,17 _ $ LF
      ^ $ EOF

  L1: ^ <I R1,4 I> R4,6 <I R7,10 I> R11,13 <I R14,17 I> $ LF
      ^ $ EOF
  """)
  public final void italic02() {
    test(
      """
      _a b_ _c d_, _e f_
      """,

      p0(
        Token.ITALIC_START, 0,
        Token.BLOB, 1, 4,
        Token.ITALIC_END, 4,
        Token.BLOB, 5, 6,
        Token.ITALIC_START, 6,
        Token.BLOB, 7, 10,
        Token.ITALIC_END, 10,
        Token.BLOB, 11, 13,
        Token.ITALIC_START, 13,
        Token.BLOB, 14, 17,
        Token.ITALIC_END, 17,
        Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 0, 27,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      docAttr(),

      p2(
        t(
          Text.ITALIC_START,
          Text.REGULAR, 1, 4,
          Text.ITALIC_END,
          Text.REGULAR, 5, 6,
          Text.ITALIC_START,
          Text.REGULAR, 7, 10,
          Text.ITALIC_END,
          Text.REGULAR, 11, 13,
          Text.ITALIC_START,
          Text.REGULAR, 14, 17,
          Text.ITALIC_END
        )
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p><em>a b</em> <em>c d</em>, <em>e f</em></p>
      </div>
      </div>
      </body>
      """
    );
  }

  final String normalize(String html) {
    Document fragment = Jsoup.parseBodyFragment(html);

    Element body = fragment.body();

    return body.toString();
  }

  void test(
      String source,
      int[] p0,
      int[] p1, Map<String, String> docAttr,
      int[][] p2,
      String expectedHtml) {
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

  private Map<String, String> docAttr(String... pairs) {
    var map = new LinkedHashMap<String, String>(pairs.length);

    var index = 0;

    while (index < pairs.length) {
      var key = pairs[index++];
      var value = pairs[index++];

      map.put(key, value);
    }

    return map;
  }

  private int[] p0(int... values) { return values; }

  private int[] p1(int... values) { return values; }

  private int[][] p2(int[]... values) { return values; }

  private int[] t(int... values) { return values; }

}