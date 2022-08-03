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
        new InlineMacroTest(this)
    };
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
        Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 0, 28,
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
  bold

  - constrained
  - phrases
  - well-formed

            1
  012345678901234567
  '''
  *a b* *c d*, *e f*
  '''

  L0: ^ * W1,2 SP W3,4 * SP * W7,8 SP W9,10 * X11,12 SP * W14,15 SP W16,17 * $ LF
      ^ $ EOF

  L1: ^ <B R1,4 B> R4,6 <B R7,10 B> R11,13 <B R14,17 B> $ LF
      ^ $ EOF
  """)
  public final void bold02() {
    test(
      """
      *a b* *c d*, *e f*
      """,

      p0(
        Token.BOLD_START, 0,
        Token.BLOB, 1, 4,
        Token.BOLD_END, 4,
        Token.BLOB, 5, 6,
        Token.BOLD_START, 6,
        Token.BLOB, 7, 10,
        Token.BOLD_END, 10,
        Token.BLOB, 11, 13,
        Token.BOLD_START, 13,
        Token.BLOB, 14, 17,
        Token.BOLD_END, 17,
        Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 0, 28,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(
          Text.BOLD_START,
          Text.REGULAR, 1, 4,
          Text.BOLD_END,
          Text.REGULAR, 5, 6,
          Text.BOLD_START,
          Text.REGULAR, 7, 10,
          Text.BOLD_END,
          Text.REGULAR, 11, 13,
          Text.BOLD_START,
          Text.REGULAR, 14, 17,
          Text.BOLD_END
        )
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p><strong>a b</strong> <strong>c d</strong>, <strong>e f</strong></p>
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
        Code.TOKENS, 0, 4,
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
        Code.TOKENS, 0, 28,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

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
        Code.TOKENS, 0, 28,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

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

  @Test(description = //
  """
  listing block

  - delimited
  - single line

  01234
  56789
  01234
  '''
  ----
  code
  ----
  '''

  P0: ^ LBD $ LF
      ^ B5,9 $ LF
      ^ LBD $ LF
      ^ $ EOF
  """)
  public final void listingBlock01() {
    test(
      """
      ----
      code
      ----
      """,

      p0(
        Token.LISTING_BLOCK_DELIM, 4, Token.LF,

        Token.BLOB, 5, 9, Token.LF,

        Token.LISTING_BLOCK_DELIM, 4, Token.LF,

        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.LISTING_BLOCK_START,
        Code.VERBATIM, 3, 6,
        Code.LISTING_BLOCK_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="listingblock">
      <div class="content">
      <pre>code</pre>
      </div>
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
        Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 0, 28,
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
        Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 0, 28,
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

  @Test(description = //
  """
  section

  - level 1
  - single paragraph

  012345
  6
  789012
  3
  456789
  0
  123456
  '''
  = doc

  pream

  == L1

  parag
  '''

  P0: ^ H1-0,2 B2,5 $ LF
      ^ $ LF
      ^ B7,12 $ LF
      ^ $ LF
      ^ H2-14,17 B17,19 $ LF
      ^ $ LF
      ^ B21,26 $ LF
      ^ $ EOF

  P1: DOC_START
      HEADING_START 1
      P0 start end
      HEADING_END
      PREAMBLE_START
      PARAGRAPH P0 start end
      PREAMBLE_END
      SECTION_START 1
      HEADING_START 2
      P0 start end
      HEADING_END
      PARAGRAPH P0 start end
      SECTION_END
      DOC_END
  """)
  public final void section01() {
    test(
      """
      = doc

      pream

      == L1

      parag
      """,

      p0(
        Token.HEADING, 1, 0, 2,
        Token.BLOB, 2, 5,
        Token.LF,
        Token.LF,
        Token.BLOB, 7, 12,
        Token.LF,
        Token.LF,
        Token.HEADING, 2, 14, 17,
        Token.BLOB, 17, 19,
        Token.LF,
        Token.LF,
        Token.BLOB, 21, 26,
        Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.HEADING_START, 1, Code.TOKENS, 4, 7, Code.HEADING_END,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START, Code.TOKENS, 9, 13, Code.PARAGRAPH_END,
        Code.PREAMBLE_END,

        Code.SECTION_START, 1,
        Code.HEADING_START, 2, Code.TOKENS, 18, 21, Code.HEADING_END,
        Code.PARAGRAPH_START, Code.TOKENS, 23, 27, Code.PARAGRAPH_END,
        Code.SECTION_END,

        Code.DOCUMENT_END
      ),

      p2(
        t(Text.REGULAR, 2, 5),
        t(Text.REGULAR, 7, 12),
        t(Text.REGULAR, 17, 19),
        t(Text.REGULAR, 21, 26)
      ),

      """
      <div id="header">
      <h1>doc</h1>
      </div>
      <div id="content">
      <div id="preamble">
      <div class="sectionbody">
      <div class="paragraph">
      <p>pream</p>
      </div>
      </div>
      </div>
      <div class="sect1">
      <h2 id="_l1">L1</h2>
      <div class="sectionbody">
      <div class="paragraph">
      <p>parag</p>
      </div>
      </div>
      </div>
      </div>
      """
    );
  }

  @Test(description = //
  """
  section

  - level 1
  - level 2
  - single paragraph in each

  012345
  6
  789012
  3
  456789
  0
  123456
  7
  890123
  4
  567890
  '''
  = doc

  pream

  == L1

  sect1

  === 2

  sect2
  '''

  P0: ^ H1-0,2 B2,5 $ LF
      ^ $ LF
      ^ B7,12 $ LF
      ^ $ LF
      ^ H2-14,17 B17,19 $ LF
      ^ $ LF
      ^ B21,26 $ LF
      ^ $ LF
      ^ H3-28,32 B32,33 $ LF
      ^ $ LF
      ^ B35,40 $ LF
      ^ $ EOF

  P1: DOC_START
      HEADING_START 1
      P0 start end
      HEADING_END
      PREAMBLE_START
      PARAGRAPH P0 start end
      PREAMBLE_END
      SECTION_START 1
      HEADING_START 2
      P0 start end
      HEADING_END
      PARAGRAPH P0 start end
      SECTION_END
      SECTION_START 2
      HEADING_START 3
      P0 start end
      HEADING_END
      PARAGRAPH P0 start end
      SECTION_END
      DOC_END
  """)
  public final void section02() {
    test(
      """
      = doc

      pream

      == L1

      sect1

      === 2

      sect2
      """,

      p0(
        Token.HEADING, 1, 0, 2,
        Token.BLOB, 2, 5,
        Token.LF,
        Token.LF,
        Token.BLOB, 7, 12,
        Token.LF,
        Token.LF,

        Token.HEADING, 2, 14, 17,
        Token.BLOB, 17, 19,
        Token.LF,
        Token.LF,

        Token.BLOB, 21, 26,
        Token.LF,
        Token.LF,

        Token.HEADING, 3, 28, 32,
        Token.BLOB, 32, 33,
        Token.LF,
        Token.LF,

        Token.BLOB, 35, 40,
        Token.LF,

        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.HEADING_START, 1, Code.TOKENS, 4, 7, Code.HEADING_END,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START, Code.TOKENS, 9, 13, Code.PARAGRAPH_END,
        Code.PREAMBLE_END,

        Code.SECTION_START, 1,
        Code.HEADING_START, 2, Code.TOKENS, 18, 21, Code.HEADING_END,
        Code.PARAGRAPH_START, Code.TOKENS, 23, 27, Code.PARAGRAPH_END,

        Code.SECTION_START, 2,
        Code.HEADING_START, 3, Code.TOKENS, 32, 35, Code.HEADING_END,
        Code.PARAGRAPH_START, Code.TOKENS, 37, 41, Code.PARAGRAPH_END,

        Code.SECTION_END,
        Code.SECTION_END,

        Code.DOCUMENT_END
      ),

      p2(
        t(Text.REGULAR, 2, 5),
        t(Text.REGULAR, 7, 12),
        t(Text.REGULAR, 17, 19),
        t(Text.REGULAR, 21, 26),
        t(Text.REGULAR, 32, 33),
        t(Text.REGULAR, 35, 40)
      ),

      """
      <div id="header">
      <h1>doc</h1>
      </div>
      <div id="content">
      <div id="preamble">
      <div class="sectionbody">
      <div class="paragraph">
      <p>pream</p>
      </div>
      </div>
      </div>
      <div class="sect1">
      <h2 id="_l1">L1</h2>
      <div class="sectionbody">
      <div class="paragraph">
      <p>sect1</p>
      </div>
      <div class="sect2">
      <h3 id="_2">2</h3>
      <div class="paragraph">
      <p>sect2</p>
      </div>
      </div>
      </div>
      </div>
      </div>
      """
    );
  }

  @Test(description = //
  """
  section

  - attribute list
  - level 1
  - no doctitle
  - single paragraph

  012345
  678901
  2
  345678
  '''
  [nam]
  == L1

  sect1
  '''

  P0: ^ [ B1,4 ] $ LF
      ^ H2-6,9 B9,11 $ LF
      ^ $ LF
      ^ B13,18 $ LF
      ^ $ EOF
  """)
  public final void section03() {
    test(
      """
      [nam]
      == L1

      sect1
      """,

      p0(
        Token.ATTR_LIST_START,
        Token.ATTR_VALUE, 1, 4,
        Token.ATTR_LIST_END,
        Token.LF,

        Token.HEADING, 2, 6, 9,
        Token.BLOB, 9, 11,
        Token.LF,
        Token.LF,

        Token.BLOB, 13, 18,
        Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,

        Code.ATTR_POSITIONAL, 1, 1, 4,
        Code.SECTION_START, 1,
        Code.HEADING_START, 2, Code.TOKENS, 10, 13, Code.HEADING_END,
        Code.PARAGRAPH_START, Code.TOKENS, 15, 19, Code.PARAGRAPH_END,
        Code.SECTION_END,

        Code.DOCUMENT_END
      ),

      p2(
        t(Text.REGULAR, 9, 11),
        t(Text.REGULAR, 13, 18)
      ),

      """
      <div id="header">
      </div>
      <div id="content">
      <div class="sect1">
      <h2 id="_l1">L1</h2>
      <div class="sectionbody">
      <div class="paragraph">
      <p>sect1</p>
      </div>
      </div>
      </div>
      </div>
      """
    );
  }

  @Test(description = //
  """
  source code block

  - implict source style
  - delimited
  - single line

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

  P0: [ AVAL1,1 , AVAL3,6 ] $ LF
      LBD $ LF
      B13,19 $ LF
      LBD $ LF
      $ EOF
  """)
  public final void sourceCodeBlock01() {
    test(
      """
      [,java]
      ----
      break;
      ----
      """,

      p0(
        Token.ATTR_LIST_START,
        Token.ATTR_VALUE, 1, 1,
        Token.SEPARATOR,
        Token.ATTR_VALUE, 2, 6,
        Token.ATTR_LIST_END,
        Token.LF,

        Token.LISTING_BLOCK_DELIM, 4,
        Token.LF,

        Token.BLOB, 13, 19,
        Token.LF,

        Token.LISTING_BLOCK_DELIM, 4,
        Token.LF,

        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.ATTR_POSITIONAL, 1, 1, 1,
        Code.ATTR_POSITIONAL, 2, 2, 6,
        Code.PREAMBLE_START,
        Code.LISTING_BLOCK_START,
        Code.VERBATIM, 13, 16,
        Code.LISTING_BLOCK_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="listingblock">
      <div class="content">
      <pre class="highlight"><code class="language-java" data-lang="java">break;</code></pre>
      </div>
      </div>
      </div>
      </body>
      """
    );
  }

  @Test(description = //
  """
      source code block

      - implict source style
      - delimited
      - multiple lines

      01234567
      89012
      3456789
      01234
      '''
      [source,java]
      ----
      class A {

      }
      ----
      '''

      P0: [ AVAL1,1 , AVAL3,6 ] $ LF
          LBD $ LF
          B13,19 $ LF
          LBD $ LF
          $ EOF
      """)
  public final void sourceCodeBlock02() {
    test(
      """
      [source,java]
      ----
      class A {

      }
      ----
      """,

      p0(
        Token.ATTR_LIST_START,
        Token.ATTR_VALUE, 1, 7,
        Token.SEPARATOR,
        Token.ATTR_VALUE, 8, 12,
        Token.ATTR_LIST_END,
        Token.LF,

        Token.LISTING_BLOCK_DELIM, 4,
        Token.LF,

        Token.BLOB, 19, 28,
        Token.LF,
        Token.LF,
        Token.BLOB, 30, 31,
        Token.LF,

        Token.LISTING_BLOCK_DELIM, 4,
        Token.LF,

        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.ATTR_POSITIONAL, 1, 1, 7,
        Code.ATTR_POSITIONAL, 2, 8, 12,
        Code.PREAMBLE_START,
        Code.LISTING_BLOCK_START,
        Code.VERBATIM, 13, 21,
        Code.LISTING_BLOCK_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="listingblock">
      <div class="content">
      <pre class="highlight"><code class="language-java" data-lang="java">class A {

      }</code></pre>
      </div>
      </div>
      </div>
      </body>
      """
    );
  }

  @Test(description = //
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

  P0: -0,1 B2,3 LF
      -4,5 B6,7 LF
      -8,9 B10,11 LF
      EOF

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
    test(
      """
      - a
      - b
      - c
      """,

      p0(
        Token.ULIST_HYPHEN, 0, 1, Token.BLOB, 2, 3, Token.LF,
        Token.ULIST_HYPHEN, 4, 5, Token.BLOB, 6, 7, Token.LF,
        Token.ULIST_HYPHEN, 8, 9, Token.BLOB, 10, 11, Token.LF,

        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.ULIST_START,

        Code.LI_START, Code.TOKENS, 3, 6, Code.LI_END,

        Code.LI_START, Code.TOKENS, 10, 13, Code.LI_END,

        Code.LI_START, Code.TOKENS, 17, 20, Code.LI_END,

        Code.ULIST_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(Text.REGULAR, 2, 3),
        t(Text.REGULAR, 6, 7),
        t(Text.REGULAR, 10, 11)
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="ulist">
      <ul>
      <li><p>a</p></li>
      <li><p>b</p></li>
      <li><p>c</p></li>
      </ul>
      </div>
      </div>
      </body>
      """
    );
  }

  @Test(description = //
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

  L0: -0,1 B2,7 LF
      B4,7 LF
      -8,9 B10,11 $ LF
      B12,15 LF
      EOF
  """)
  public final void unorderedList02() {
    test(
      """
      - a
      bcd
      - e
      fgh
      """,

      p0(
        Token.ULIST_HYPHEN, 0, 1, Token.BLOB, 2, 3, Token.LF,
        Token.BLOB, 4, 7, Token.LF,
        Token.ULIST_HYPHEN, 8, 9, Token.BLOB, 10, 11, Token.LF,
        Token.BLOB, 12, 15, Token.LF,

        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.ULIST_START,

        Code.LI_START, Code.TOKENS, 3, 10, Code.LI_END,

        Code.LI_START, Code.TOKENS, 14, 21, Code.LI_END,

        Code.ULIST_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(Text.REGULAR, 2, 7),
        t(Text.REGULAR, 10, 15)
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="ulist">
      <ul>
      <li><p>a bcd</p></li>
      <li><p>e fgh</p></li>
      </ul>
      </div>
      </div>
      </body>
      """
    );
  }

  @Test(description = //
  """
  unordered list

  - simple elements
  - dash
  - single level

  0123
  4567
  8901
  '''
  * a
  * b
  * c
  '''

  P0: *0,1 B2,3 LF
      *4,5 B6,7 LF
      *8,9 B10,11 LF
      EOF
  """)
  public final void unorderedList03() {
    test(
      """
      * a
      * b
      * c
      """,

      p0(
        Token.ULIST_ASTERISK, 1, 0, 1, Token.BLOB, 2, 3, Token.LF,
        Token.ULIST_ASTERISK, 1, 4, 5, Token.BLOB, 6, 7, Token.LF,
        Token.ULIST_ASTERISK, 1, 8, 9, Token.BLOB, 10, 11, Token.LF,

        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.ULIST_START,

        Code.LI_START, Code.TOKENS, 4, 7, Code.LI_END,

        Code.LI_START, Code.TOKENS, 12, 15, Code.LI_END,

        Code.LI_START, Code.TOKENS, 20, 23, Code.LI_END,

        Code.ULIST_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(Text.REGULAR, 2, 3),
        t(Text.REGULAR, 6, 7),
        t(Text.REGULAR, 10, 11)
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="ulist">
      <ul>
      <li><p>a</p></li>
      <li><p>b</p></li>
      <li><p>c</p></li>
      </ul>
      </div>
      </div>
      </body>
      """
    );
  }

  @Test(description = //
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

  P0: *0,1 B2,3 $ LF
      **4,6 B7,8 $ LF
      **9,11 B12,13 $ LF
      *14,15 B16,17 $ LF
      EOF
  """)
  public final void unorderedList04() {
    test(
      """
      * a
      ** b
      ** c
      * d
      """,

      p0(
        Token.ULIST_ASTERISK, 1, 0, 1, Token.BLOB, 2, 3, Token.LF,
        Token.ULIST_ASTERISK, 2, 4, 6, Token.BLOB, 7, 8, Token.LF,
        Token.ULIST_ASTERISK, 2, 9, 11, Token.BLOB, 12, 13, Token.LF,
        Token.ULIST_ASTERISK, 1, 14, 15, Token.BLOB, 16, 17, Token.LF,

        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.ULIST_START,

        Code.LI_START, Code.TOKENS, 4, 7,

        Code.ULIST_START,
        Code.LI_START, Code.TOKENS, 12, 15, Code.LI_END,

        Code.LI_START, Code.TOKENS, 20, 23, Code.LI_END,
        Code.ULIST_END,
        Code.LI_END,

        Code.LI_START, Code.TOKENS, 28, 31, Code.LI_END,

        Code.ULIST_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(Text.REGULAR, 2, 3),
        t(Text.REGULAR, 7, 8),
        t(Text.REGULAR, 12, 13),
        t(Text.REGULAR, 16, 17)
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="ulist">
      <ul>
      <li><p>a</p>
      <div class="ulist">
      <ul>
      <li><p>b</p></li>
      <li><p>c</p></li>
      </ul>
      </div></li>
      <li><p>d</p></li>
      </ul>
      </div>
      </div>
      </body>
      """
    );
  }

  @Test(description = //
  """
  unordered list

  - indented nested unordered list
  - asterisk

  0123
  456789
  012345
  6789
  '''
  * a
   ** b
   ** c
  * d
  '''

  P0: * B2,3 LF
      ** B8,9 LF
      ** B14,15 LF
      * B18,19 LF
      EOF
  """)
  public final void unorderedList05() {
    test(
      """
      * a
       ** b
       ** c
      * d
      """,

      p0(
        Token.ULIST_ASTERISK, 1, 0, 1, Token.BLOB, 2, 3, Token.LF,
        Token.ULIST_ASTERISK, 2, 4, 7, Token.BLOB, 8, 9, Token.LF,
        Token.ULIST_ASTERISK, 2, 10, 13, Token.BLOB, 14, 15, Token.LF,
        Token.ULIST_ASTERISK, 1, 16, 17, Token.BLOB, 18, 19, Token.LF,

        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.ULIST_START,

        Code.LI_START, Code.TOKENS, 4, 7,

        Code.ULIST_START,
        Code.LI_START, Code.TOKENS, 12, 15, Code.LI_END,

        Code.LI_START, Code.TOKENS, 20, 23, Code.LI_END,
        Code.ULIST_END,
        Code.LI_END,

        Code.LI_START, Code.TOKENS, 28, 31, Code.LI_END,

        Code.ULIST_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t(Text.REGULAR, 2, 3),
        t(Text.REGULAR, 8, 9),
        t(Text.REGULAR, 14, 15),
        t(Text.REGULAR, 18, 19)
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="ulist">
      <ul>
      <li><p>a</p>
      <div class="ulist">
      <ul>
      <li><p>b</p></li>
      <li><p>c</p></li>
      </ul>
      </div></li>
      <li><p>d</p></li>
      </ul>
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