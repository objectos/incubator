/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.lexer.impl.ah;

import br.com.objectos.lexer.impl.ah.model.CharExpressions;
import org.testng.annotations.Test;

public class LinkerTest extends LinkerDriver {

  @Test
  public void tokenStringLexerGrammar() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addString("Lexer")
            .trailingBlock(CreateWithActionFake.TOKEN_STRING))
        .thenLinkerToString(
            "( 'Lexer' | T:<TokenString> )");
  }

  @Test
  public void answerOnlyLexerGrammar() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addString("NO")
            .trailingBlock(ReturnSelfActionFake.Answer_NO))
        .thenLinkerToString(
            "( 'NO' | T:<Answer.NO> )")

        .whenTrailingBlock(new StartingBlock()
            .addString("YES")
            .trailingBlock(ReturnSelfActionFake.Answer_YES))
        .thenLinkerToString(
            "( 'N' 'O' | T:<Answer.NO> )",
            "( 'Y' 'ES' | T:<Answer.YES> )");
  }

  @Test
  public void answerWsLexerGrammar() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addString("NO")
            .trailingBlock(ReturnSelfActionFake.Answer_NO))
        .thenLinkerToString(
            "( 'NO' | T:<Answer.NO> )")

        .whenTrailingBlock(new StartingBlock()
            .addString("YES")
            .trailingBlock(ReturnSelfActionFake.Answer_YES))
        .thenLinkerToString(
            "( 'N' 'O' | T:<Answer.NO> )",
            "( 'Y' 'ES' | T:<Answer.YES> )")

        .whenTrailingBlock(new StartingBlock()
            .addChar(CharExpressions.WS).oneOrMore()
            .trailingBlock(IgnoreAction.INSTANCE))
        .thenLinkerToString(
            "( '\\9' ['\\9','\\10','\\13','\\32']* | <ignore> )",
            "( '\\10' ['\\9','\\10','\\13','\\32']* | <ignore> )",
            "( '\\13' ['\\9','\\10','\\13','\\32']* | <ignore> )",
            "( '\\32' ['\\9','\\10','\\13','\\32']* | <ignore> )",
            "( 'N' 'O' | T:<Answer.NO> )",
            "( 'Y' 'ES' | T:<Answer.YES> )");
  }

  @Test
  public void answerAndTokenStringLexerGrammar() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addString("NO")
            .trailingBlock(ReturnSelfActionFake.Answer_NO))
        .thenLinkerToString(
            "( 'NO' | T:<Answer.NO> )")

        .whenTrailingBlock(new StartingBlock()
            .addString("YES")
            .trailingBlock(ReturnSelfActionFake.Answer_YES))
        .thenLinkerToString(
            "( 'N' 'O' | T:<Answer.NO> )",
            "( 'Y' 'ES' | T:<Answer.YES> )")

        .whenTrailingBlock(new StartingBlock()
            .addChar(CharExpressions.YES).oneOrMore()
            .trailingBlock(CreateWithActionFake.TOKEN_STRING))
        .thenLinkerToString(
            "( 'E' ['E','S','Y']* | T:<TokenString> )",
            "( 'N' 'O' | T:<Answer.NO> )",
            "( 'S' ['E','S','Y']* | T:<TokenString> )",
            "( 'Y' 'ES' = ['E','S','Y'] = ['E','S','Y']* | T:<TokenString> )",
            "( 'Y' 'ES' = ['E','S','Y'] ! | T:<Answer.YES> )",
            "( 'Y' 'ES' ! ['E','S','Y']* | T:<TokenString> )");
  }

  @Test
  public void answerAndTokenStringLexerGrammar_rank() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addChar(CharExpressions.YES).oneOrMore()
            .trailingBlock(CreateWithActionFake.TOKEN_STRING))
        .thenLinkerToString(
            "( ['E','S','Y']+ | T:<TokenString> )")

        .whenTrailingBlock(new StartingBlock()
            .addString("YES")
            .trailingBlock(ReturnSelfActionFake.Answer_YES))
        .thenLinkerToString(
            "( ['E','S','Y']+ | T:<TokenString> )")

        .whenTrailingBlock(new StartingBlock()
            .addString("NO")
            .trailingBlock(ReturnSelfActionFake.Answer_NO))
        .thenLinkerToString(
            "( 'E' ['E','S','Y']* | T:<TokenString> )",
            "( 'N' 'O' | T:<Answer.NO> )",
            "( 'S' ['E','S','Y']* | T:<TokenString> )",
            "( 'Y' ['E','S','Y']* | T:<TokenString> )");
  }

  @Test
  public void returnSelfLexerGrammar() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addChar('N')
            .addChar('o')
            .trailingBlock(ReturnSelfActionFake.Answer_NO))
        .thenLinkerToString(
            "( 'N' | 'o' | T:<Answer.NO> )")

        .whenTrailingBlock(new StartingBlock()
            .addString("Yes")
            .trailingBlock(ReturnSelfActionFake.Answer_YES))
        .thenLinkerToString(
            "( 'N' | 'o' | T:<Answer.NO> )",
            "( 'Y' 'es' | T:<Answer.YES> )");
  }

  @Test
  public void lexemeConcatLexerGrammar() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addChar('1')
            .addChar('x').oneOrMore().concat()
            .trailingBlock(CreateWithActionFake.TOKEN_STRING))
        .thenLinkerToString(
            "( '1' 'x'+ | T:<TokenString> )")

        .whenTrailingBlock(new StartingBlock()
            .addChar('2')
            .addChar('x').oneOrMore()
            .trailingBlock(CreateWithActionFake.TOKEN_STRING_2))
        .thenLinkerToString(
            "( '1' 'x'+ | T:<TokenString> )",
            "( '2' | 'x'+ | T:<TokenString> )");
  }

  @Test
  public void charExpressionProtoNextLexerGrammar() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addChar('y').oneOrMore()
            .addChar('e')
            .addChar('s').oneOrMore()
            .trailingBlock(ReturnSelfActionFake.Answer_YES))
        .thenLinkerToString(
            "( 'y'+ | 'e' | 's'+ | T:<Answer.YES> )")

        .whenTrailingBlock(new StartingBlock()
            .addChar('N').zeroOrMore()
            .addChar('n')
            .addChar('o').oneOrMore()
            .trailingBlock(ReturnSelfActionFake.Answer_NO))
        .thenLinkerToString(
            "( 'N' 'N'* | 'n' | 'o'+ | T:<Answer.NO> )",
            "( 'n' | 'o'+ | T:<Answer.NO> )",
            "( 'y' 'y'* | 'e' | 's'+ | T:<Answer.YES> )");
  }

  @Test
  public void charExpressionStartingOneLexerGrammar() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addChar(CharExpressions.YES)
            .trailingBlock(ReturnSelfActionFake.Answer_YES))
        .thenLinkerToString(
            "( ['E','S','Y'] | T:<Answer.YES> )");
  }

  @Test
  public void startingCharSingleZeroOneOrMoreLexerGrammar() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addChar('1').zeroOrMore()
            .addChar('0')
            .trailingBlock(ReturnSelfActionFake.Bit_ZERO))
        .thenLinkerToString(
            "( '1'* | '0' | T:<Bit.ZERO> )")

        .whenTrailingBlock(new StartingBlock()
            .addChar('1').oneOrMore()
            .trailingBlock(ReturnSelfActionFake.Bit_ONE))
        .thenLinkerToString(
            "( '1' = '1'* | '0' = | T:<Bit.ZERO> )",
            "( '1' = '1'* | '0' ! T:<Bit.ONE> )",
            "( '1' ! | '0' | T:<Bit.ZERO> )");
  }

  @Test
  public void startingCharExpZeroOneOrMoreLexerGrammar() {
    it().givenLinker()
        .whenTrailingBlock(new StartingBlock()
            .addChar(CharExpressions._1).zeroOrMore()
            .addChar('0')
            .trailingBlock(ReturnSelfActionFake.Bit_ZERO))
        .thenLinkerToString(
            "( ['1','i']* | '0' | T:<Bit.ZERO> )")

        .whenTrailingBlock(new StartingBlock()
            .addChar(CharExpressions._1).oneOrMore()
            .trailingBlock(ReturnSelfActionFake.Bit_ONE))
        .thenLinkerToString(
            "( ['1','i'] = ['1','i']* | '0' = | T:<Bit.ZERO> )",
            "( ['1','i'] = ['1','i']* | '0' ! T:<Bit.ONE> )",
            "( ['1','i'] ! | '0' | T:<Bit.ZERO> )");
  }

}