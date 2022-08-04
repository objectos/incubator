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

final class ParagraphTest extends AbstractAsciiDocTest {

  ParagraphTest(AsciiDocTest outer) { super(outer); }

  @Test(description = //
  """
  two lines paragraph

  0123
  4567
  '''
  abc
  def
  '''

  P0: B0,3 LF
      B4,7 LF
      EOF
  """)
  public void testCase01() {
    test(
      """
      abc
      def
      """,

      p0(
        Token.BLOB, 0, 3, Token.LF,
        Token.BLOB, 4, 7, Token.LF,
        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.TOKENS, 0, 8,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      docAttr(),

      p2(
        t(Text.REGULAR, 0, 7)
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p>abc def</p>
      </div>
      </div>
      </body>
      """
    );
  }

}
