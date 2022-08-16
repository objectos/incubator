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

final class ListingBlockTest extends AbstractAsciiDocTest {

  ListingBlockTest(AsciiDocTest outer) { super(outer); }

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
  public void testCase01() {
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

      docAttr(),

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
  listing block code with ']'

  01234
  56789
  01234
  '''
  ----
  foo]
  ----
  '''
  """)
  public void testCase02() {
    test(
      """
      ----
      foo]
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

      docAttr(),

      p2(),

      """
          <body>
          <div id="header">
          </div>
          <div id="content">
          <div class="listingblock">
          <div class="content">
          <pre>foo]</pre>
          </div>
          </div>
          </div>
          </body>
          """
    );
  }

}