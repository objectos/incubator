/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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

import br.com.objectos.lexer.impl.ah.model.AngBracket;
import br.com.objectos.lexer.impl.ah.model.Answer;
import br.com.objectos.lexer.impl.ah.model.Bit;
import br.com.objectos.lexer.impl.ah.model.CharExpressions;
import br.com.objectos.lexer.impl.ah.model.IsToken;
import br.com.objectos.lexer.impl.ah.model.Letter;
import br.com.objectos.lexer.impl.ah.model.TokenString;
import org.testng.annotations.Test;

public class SpecBuilderTest extends SpecBuilderDriver {

  @Test
  public void tokenStringLexerGrammar() {
    it().defineToken(TokenString.class)
        .addString("Lexer")
        .andCreateWith(TokenString::new)
        .thenStateToString("'Lexer' | T:<TokenString>");
  }

  @Test
  public void answerOnlyLexerGrammar() {
    it().defineToken(Answer.class)
        .addString("NO")
        .andReturnSelf(Answer.NO)
        .thenStateToString(
            "'NO' | T:<Answer.NO>")
        .addString("YES")
        .andReturnSelf(Answer.YES)
        .thenStateToString(
            "'N' 'O' | T:<Answer.NO>",
            "'Y' 'ES' | T:<Answer.YES>");
  }

  @Test
  public void answerWsLexerGrammar() {
    it().defineToken(Answer.class)
        .addString("NO")
        .andReturnSelf(Answer.NO)
        .thenStateToString(
            "'NO' | T:<Answer.NO>")
        .addString("YES")
        .andReturnSelf(Answer.YES)
        .thenStateToString(
            "'N' 'O' | T:<Answer.NO>",
            "'Y' 'ES' | T:<Answer.YES>")

        .defineToken(IsToken.class)
        .addChar(CharExpressions.WS).oneOrMore()
        .andIgnore()
        .thenStateToString(
            "'\\9' ['\\9','\\10','\\13','\\32']* | <ignore>",
            "'\\10' ['\\9','\\10','\\13','\\32']* | <ignore>",
            "'\\13' ['\\9','\\10','\\13','\\32']* | <ignore>",
            "'\\32' ['\\9','\\10','\\13','\\32']* | <ignore>",
            "'N' 'O' | T:<Answer.NO>",
            "'Y' 'ES' | T:<Answer.YES>");
  }

  @Test
  public void answerAndTokenStringLexerGrammar() {
    it().defineToken(Answer.class)
        .addString("NO")
        .andReturnSelf(Answer.NO)
        .thenStateToString(
            "'NO' | T:<Answer.NO>")
        .addString("YES")
        .andReturnSelf(Answer.YES)
        .thenStateToString(
            "'N' 'O' | T:<Answer.NO>",
            "'Y' 'ES' | T:<Answer.YES>")

        .defineToken(TokenString.class)
        .addChar(CharExpressions.YES).oneOrMore()
        .andCreateWith(TokenString::new)
        .thenStateToString(
            "'E' ['E','S','Y']* | T:<TokenString>",
            "'N' 'O' | T:<Answer.NO>",
            "'S' ['E','S','Y']* | T:<TokenString>",
            "'Y' 'ES' ['E','S','Y'] ['E','S','Y']* | T:<TokenString>",
            "'Y' 'ES' | T:<Answer.YES>",
            "'Y' ['E','S','Y']* | T:<TokenString>");
  }

  @Test
  public void returnSelfLexerGrammar() {
    it().defineToken(Answer.NO)
        .addChar('N')
        .addChar('o')
        .andReturnSelf()
        .thenStateToString(
            "'N' | 'o' | T:<Answer.NO>")

        .defineToken(Answer.YES)
        .addString("Yes")
        .andReturnSelf()
        .thenStateToString(
            "'N' | 'o' | T:<Answer.NO>",
            "'Y' 'es' | T:<Answer.YES>");
  }

  @Test
  public void lexemeConcatLexerGrammar() {
    it().defineToken(TokenString.class)
        .addChar('1')
        .addChar('x').oneOrMore().concat()
        .andCreateWith(TokenString::new)
        .thenStateToString(
            "'1' 'x' 'x'* | T:<TokenString>")

        .defineToken(TokenString.class)
        .addChar('2')
        .addChar('x').oneOrMore()
        .andCreateWith(TokenString::addTwo)
        .thenStateToString(
            "'1' 'x' 'x'* | T:<TokenString>",
            "'2' | 'x' 'x'* | T:<TokenString>");
  }

  @Test
  public void charExpressionProtoNextLexerGrammar() {
    it().defineToken(Answer.YES)
        .addChar('y').oneOrMore()
        .addChar('e')
        .addChar('s').oneOrMore()
        .andReturnSelf()
        .thenStateToString(
            "'y' 'y'* | 'e' | 's' 's'* | T:<Answer.YES>")

        .defineToken(Answer.NO)
        .addChar('N').zeroOrMore()
        .addChar('n')
        .addChar('o').oneOrMore()
        .andReturnSelf()
        .thenStateToString(
            "'N' 'N'* | 'n' | 'o' 'o'* | T:<Answer.NO>",
            "'n' | 'o' 'o'* | T:<Answer.NO>",
            "'y' 'y'* | 'e' | 's' 's'* | T:<Answer.YES>");
  }

  @Test
  public void charExpressionStartingOneLexerGrammar() {
    it().defineToken(Answer.YES)
        .addChar(CharExpressions.YES)
        .andReturnSelf()
        .thenStateToString(
            "['E','S','Y'] | T:<Answer.YES>");
  }

  @Test
  public void startingZeroOneOrMoreLexerGrammar() {
    it().defineToken(Bit.ZERO)
        .addChar('1').zeroOrMore()
        .addChar('0')
        .andReturnSelf()
        .thenStateToString(
            "'1'* | '0' | T:<Bit.ZERO>")

        .defineToken(Bit.ONE)
        .addChar('1').oneOrMore()
        .andReturnSelf()
        .thenStateToString(
            "'1' '1'* | '0' | T:<Bit.ZERO>",
            "'1' '1'* | T:<Bit.ONE>",
            "| '0' | T:<Bit.ZERO>");
  }

  @Test
  public void startingCharExpZeroOneOrMoreLexerGrammar() {
    it().defineToken(Bit.ZERO)
        .addChar(CharExpressions._1).zeroOrMore()
        .addChar('0')
        .andReturnSelf()
        .thenStateToString(
            "['1','i']* | '0' | T:<Bit.ZERO>")

        .defineToken(Bit.ONE)
        .addChar(CharExpressions._1).oneOrMore()
        .andReturnSelf()
        .thenStateToString(
            "['1','i'] ['1','i']* | '0' | T:<Bit.ZERO>",
            "['1','i'] ['1','i']* | T:<Bit.ONE>",
            "| '0' | T:<Bit.ZERO>");
  }

  @Test
  public void selectorCombinatorLexerGrammar() {
    it().defineToken(Letter.A)
        .addChar(CharExpressions._1).zeroOrMore()
        .addChar('A')
        .addChar(CharExpressions._1).zeroOrMore()
        .andReturnSelf()
        .thenStateToString(
            "['1','i']* | 'A' | ['1','i']* | T:<Letter.A>")

        .defineToken(Letter.B)
        .addChar(CharExpressions._1).zeroOrMore()
        .addChar('B')
        .addChar(CharExpressions._1).zeroOrMore()
        .andReturnSelf()
        .thenStateToString(
            "['1','i']* | 'A' | ['1','i']* | T:<Letter.A>",
            "['1','i']* | 'B' | ['1','i']* | T:<Letter.B>")

        .defineToken(Letter.C)
        .addChar(CharExpressions._1).oneOrMore()
        .andReturnSelf()
        .thenStateToString(
            "['1','i'] ['1','i']* | 'A' | ['1','i']* | T:<Letter.A>",
            "['1','i'] ['1','i']* | 'B' | ['1','i']* | T:<Letter.B>",
            "['1','i'] ['1','i']* | T:<Letter.C>",
            "| 'A' | ['1','i']* | T:<Letter.A>",
            "| 'B' | ['1','i']* | T:<Letter.B>");
  }

  @Test
  public void startingBrickLexerGrammar() {
    it().defineToken(Bit.ZERO)
        .addBrick(Letter.Z)
        .addString("ero")
        .andReturnSelf()
        .thenStateToString(
            "B:<Letter.Z> 'ero' | T:<Bit.ZERO>")

        .defineBrick(Letter.Z)
        .addChar('Z')
        .andReturnSelf()
        .thenStateToString(
            "( 'Z' | B:<Letter.Z> ) B:<Letter.Z> 'ero' | T:<Bit.ZERO>");
  }
  
  @Test
  public void valueTypeTokenLexerGrammar() {
    it().defineToken(TokenString.class)
        .addBrick(AngBracket.OPEN)
        .addChar(CharExpressions.ABC).oneOrMore()
        .addBrick(AngBracket.CLOSE)
        .andCreateWith(this::valueTypeToken)
        .thenStateToString(""
            + "B:<AngBracket.OPEN> "
            + "['a','b','c'] ['a','b','c']* | "
            + "B:<AngBracket.CLOSE> T:<TokenString>")

        .defineBrick(AngBracket.class)
        .addString("<")
        .andReturnSelf(AngBracket.OPEN)
        .thenStateToString(""
            + "( '\\60' | B:<AngBracket.OPEN> ) B:<AngBracket.OPEN> "
            + "['a','b','c'] ['a','b','c']* | "
            + "B:<AngBracket.CLOSE> T:<TokenString>")
        .addString(">")
        .andReturnSelf(AngBracket.CLOSE)
        .thenStateToString(""
            + "( '\\60' | B:<AngBracket.OPEN> ) B:<AngBracket.OPEN> "
            + "['a','b','c'] ['a','b','c']* | "
            + "( '\\62' | B:<AngBracket.CLOSE> ) B:<AngBracket.CLOSE> T:<TokenString>");
  }

  private TokenString valueTypeToken(AngBracket open, String value, AngBracket close) {
    return new TokenString(value);
  }

}