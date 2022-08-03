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

class InlineMacroTest extends AbstractAsciiDocTest {

  InlineMacroTest(AsciiDocTest outer) { super(outer); }

  @Test(description = //
  """
  https link

            1         2
  012345678901234567890123
  '''
  https://example.com[Ex]
  '''

  P0: IM-0,5 T-6,19 [ AVAL20,22 ] LF
      EOF
  """)
  public void testCase01() {
    test(
      """
      https://example.com[Ex]
      """,

      p0(
        Token.INLINE_MACRO, 0, 5,
        Token.BLOB, 6, 19,
        Token.ATTR_LIST_START,
        Token.ATTR_VALUE, 20, 22,
        Token.ATTR_LIST_END,
        Token.LF,

        Token.EOF
      ),

      p1(
        Code.DOCUMENT_START,
        Code.PREAMBLE_START,
        Code.PARAGRAPH_START,
        Code.INLINE_MACRO, 0, 5,
        Code.MACRO_TARGET, 6, 19,
        Code.ATTR_POSITIONAL, 1, 20, 22,
        Code.TOKENS, 11, 12,
        Code.PARAGRAPH_END,
        Code.PREAMBLE_END,
        Code.DOCUMENT_END
      ),

      p2(
        t()
      ),

      """
      <body>
      <div id="header">
      </div>
      <div id="content">
      <div class="paragraph">
      <p><a href="https://example.com">Ex</a></p>
      </div>
      </div>
      </body>
      """
    );
  }

}