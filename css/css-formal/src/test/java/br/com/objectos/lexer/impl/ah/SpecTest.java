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

import static org.testng.Assert.assertEquals;

import br.com.objectos.lexer.grammar.LexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.AnswerAndTokenStringLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.AnswerOnlyLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.AnswerWsLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.CharExpressionProtoNextLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.IdentifierIntegerLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.LexemeConcatLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.ReturnSelfLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.StartingBrickAndAnswerLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.TokenStringLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.ValueTypeTokenLexerGrammar;
import org.testng.annotations.Test;

public class SpecTest {

  @Test
  public void tokenStringLexerGrammar() {
    assertEquals(
        spec(TokenStringLexerGrammar.get("Lexer")).toString(),
        "'Lexer' | T:<TokenString>"
    );
  }

  @Test
  public void answerOnlyLexerGrammar() {
    assertEquals(
        spec(AnswerOnlyLexerGrammar.get()).toString(), ""
            + "'N' 'O' | T:<Answer.NO>\n"
            + "'Y' 'ES' | T:<Answer.YES>"
    );
  }

  @Test
  public void answerWsLexerGrammar() {
    assertEquals(
        spec(AnswerWsLexerGrammar.get()).toString(),
        "" +
            "'\\9' ['\\9','\\10','\\13','\\32']* | <ignore>\n" +
            "'\\10' ['\\9','\\10','\\13','\\32']* | <ignore>\n" +
            "'\\13' ['\\9','\\10','\\13','\\32']* | <ignore>\n" +
            "'\\32' ['\\9','\\10','\\13','\\32']* | <ignore>\n" +
            "'N' 'O' | T:<Answer.NO>\n" +
            "'Y' 'ES' | T:<Answer.YES>"
    );
  }

  @Test
  public void answerAndTokenStringLexerGrammar() {
    assertEquals(
        spec(AnswerAndTokenStringLexerGrammar.get()).toString(),
        "" +
            "'E' ['E','S','Y']* | T:<TokenString>\n" +
            "'N' 'O' | T:<Answer.NO>\n" +
            "'S' ['E','S','Y']* | T:<TokenString>\n" +
            "'Y' 'ES' ['E','S','Y'] ['E','S','Y']* | T:<TokenString>\n" +
            "'Y' 'ES' | T:<Answer.YES>\n" +
            "'Y' ['E','S','Y']* | T:<TokenString>"
    );
  }

  @Test
  public void returnSelfLexerGrammar() {
    assertEquals(
        spec(ReturnSelfLexerGrammar.get()).toString(), "" +
            "'N' | 'o' | T:<Answer.NO>\n" +
            "'Y' 'es' | T:<Answer.YES>"
    );
  }

  @Test
  public void lexemeConcatLexerGrammar() {
    assertEquals(
        spec(LexemeConcatLexerGrammar.get()).toString(), "" +
            "'1' 'x' 'x'* | T:<TokenString>\n" +
            "'2' | 'x' 'x'* | T:<TokenString>"
    );
  }

  @Test
  public void charExpressionProtoNextLexerGrammar() {
    assertEquals(
        spec(CharExpressionProtoNextLexerGrammar.get()).toString(), "" +
            "'N' 'N'* | 'n' | 'o' 'o'* | T:<Answer.NO>\n" +
            "'n' | 'o' 'o'* | T:<Answer.NO>\n" +
            "'y' 'y'* | 'e' | 's' 's'* | T:<Answer.YES>"
    );
  }

  @Test
  public void startingBrickAndAnswerLexerGrammar() {
    assertEquals(
        spec(StartingBrickAndAnswerLexerGrammar.get()).toString(), "" +
            "'N' 'O' | T:<Answer.NO>\n" +
            "'Y' 'ES' | T:<Answer.YES>\n" +
            "( 'Z' | B:<Letter.Z> ) B:<Letter.Z> 'ero' | T:<Bit.ZERO>"
    );
  }

  @Test
  public void valueTypeTokenLexerGrammar() {
    assertEquals(
        spec(ValueTypeTokenLexerGrammar.get()).toString(), ""
            + "( '\\60' | B:<AngBracket.OPEN> ) B:<AngBracket.OPEN> "
            + "['a','b','c'] ['a','b','c']* | "
            + "( '\\62' | B:<AngBracket.CLOSE> ) B:<AngBracket.CLOSE> T:<TokenString>"
    );
  }

  @Test
  public void identifierIntegerLexerGrammar() {
    assertEquals(
        spec(IdentifierIntegerLexerGrammar.get()).toString(), "" +
            "'\\45'? '1' ['1','2','3']* | T:<IntToken>\n" +
            "'\\45'? '2' ['1','2','3']* | T:<IntToken>\n" +
            "'\\45'? '3' ['1','2','3']* | T:<IntToken>\n" +
            "'\\45'? 'a' -||['a','b','c']* | T:<TokenString>\n" +
            "'\\45'? 'b' -||['a','b','c']* | T:<TokenString>\n" +
            "'\\45'? 'c' -||['a','b','c']* | T:<TokenString>"
    );
  }

  private <T, B> State spec(LexerGrammar<T, B> grammar) {
    Spec spec = new SpecCompiler<>(grammar).compile();
    return spec.startingState();
  }

}