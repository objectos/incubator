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

import org.testng.annotations.Test;

final class SourceCodeBlockTest extends AbstractAsciiDocTest {

  SourceCodeBlockTest(AsciiDocTest outer) { super(outer); }

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
  public void testCase01() {
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
        Token.SEPARATOR, 1, 2,
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
        Code.PREAMBLE_START,
        Code.ATTR_POSITIONAL, 1, 1, 1,
        Code.ATTR_POSITIONAL, 2, 2, 6,
        Code.LISTING_BLOCK_START,
        Code.VERBATIM, 15, 18,
        Code.LISTING_BLOCK_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      docAttr(),

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

  - explict source style
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
  public void testCase02() {
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
        Token.SEPARATOR, 7, 8,
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
        Code.PREAMBLE_START,
        Code.ATTR_POSITIONAL, 1, 1, 7,
        Code.ATTR_POSITIONAL, 2, 8, 12,
        Code.LISTING_BLOCK_START,
        Code.VERBATIM, 15, 23,
        Code.LISTING_BLOCK_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      docAttr(),

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
  source code block

  - implict source style
  - delimited
  - indented content

  01234
  56789
  01
  234567
  89012
  '''
  [,a]
  ----
  b
      c
  ----
  '''
  """)
  public void testCase03() {
    test(
      """
      [,a]
      ----
      b
          c
      ----
      """,

      p0(
        Token.ATTR_LIST_START,
        Token.ATTR_VALUE, 1, 1,
        Token.SEPARATOR, 1, 2,
        Token.ATTR_VALUE, 2, 3,
        Token.ATTR_LIST_END, Token.LF,
        Token.LISTING_BLOCK_DELIM, 4, Token.LF,
        Token.BLOB, 10, 11, Token.LF,
        Token.LITERALI, 12, 16, Token.BLOB, 16, 17, Token.LF,
        Token.LISTING_BLOCK_DELIM, 4, Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.ATTR_POSITIONAL, 1, 1, 1,
        Code.ATTR_POSITIONAL, 2, 2, 3,
        Code.LISTING_BLOCK_START,
        Code.VERBATIM, 15, 25,
        Code.LISTING_BLOCK_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      docAttr(),

      p2(),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="listingblock">
      <div class="content">
      <pre class="highlight"><code class="language-a" data-lang="a">b
          c</code></pre>
      </div>
      </div>
      </div>
      </body>
      """
    );
  }

}