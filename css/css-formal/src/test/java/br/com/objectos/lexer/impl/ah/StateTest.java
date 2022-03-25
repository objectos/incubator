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

import br.com.objectos.lexer.impl.ah.grammar.AnswerAndTokenStringLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.AnswerOnlyLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.AnswerWsLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.LexemeConcatLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.ReturnSelfLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.TokenStringLexerGrammar;
import br.com.objectos.lexer.impl.ah.model.Answer;
import br.com.objectos.lexer.impl.ah.model.TokenString;
import org.testng.annotations.Test;

public class StateTest extends StateDriver {

  @Test
  public void tokenStringLexerGrammar() {
    it().givenGrammar(TokenStringLexerGrammar.get("Lexer"))
        .givenInput("Lexer")
        .whenNext()
        /**/.thenState(StringOneState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("Lexer")
        /**/.thenValueList()
        .whenNext()
        /**/.thenTrailingState(CreateWithAction.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("Lexer")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList(new TokenString("Lexer"))
        /**/.thenHasNext(false);
  }

  @Test
  public void answerOnlyLexerGrammar() {
    it().givenGrammar(AnswerOnlyLexerGrammar.get())
        .givenInput("YES")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringOneState.class)
        /**/.thenLexemeSubject("Y")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("YES")
        /**/.thenValueList()
        .whenNext()
        /**/.thenTrailingState(ReturnSelfAction.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("YES")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList(Answer.YES)
        /**/.thenHasNext(false);
  }

  @Test
  public void answerWsLexerGrammar() {
    it().givenGrammar(AnswerWsLexerGrammar.get())

        // single keyword

        .givenInput("YES")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringOneState.class)
        /**/.thenLexemeSubject("Y")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("YES")
        /**/.thenValueList()
        .whenNext()
        /**/.thenTrailingState(ReturnSelfAction.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("YES")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList(Answer.YES)
        /**/.thenHasNext(false)

        // single WS

        .givenInput(" ")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(CharExpressionZeroOrMoreState.class)
        /**/.thenLexemeSubject(" ")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject(" ")
        /**/.thenValueList()
        .whenNext()
        /**/.thenTrailingState(IgnoreAction.class)
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList()
        /**/.thenIgnore(true)
        /**/.thenHasNext(false)

        // many WS

        .givenInput("\t \n")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(CharExpressionZeroOrMoreState.class)
        /**/.thenLexemeSubject("\t")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("\t \n")
        /**/.thenValueList()
        .whenNext()
        /**/.thenTrailingState(IgnoreAction.class)
        /**/.thenLexemeSubject("")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList()
        /**/.thenIgnore(true)
        /**/.thenHasNext(false);
  }

  @Test
  public void answerAndTokenStringLexerGrammar() {
    it().givenGrammar(AnswerAndTokenStringLexerGrammar.get())

        // CharExp > String

        .givenInput("YESE")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringConditionState.class)
        /**/.thenLexemeSubject("Y")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(CharExpressionConditionState.class)
        /**/.thenLexemeSubject("YES")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(CharExpressionZeroOrMoreState.class)
        /**/.thenLexemeSubject("YESE")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("YESE")
        /**/.thenValueList()
        .whenNext()
        /**/.thenTrailingState(CreateWithAction.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("YESE")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList(TokenString.get("YESE"))
        /**/.thenHasNext(false)

        // CharExp < String

        .givenInput("YE")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringConditionState.class)
        /**/.thenLexemeSubject("Y")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(CharExpressionZeroOrMoreState.class)
        /**/.thenLexemeSubject("Y")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("YE")
        /**/.thenValueList()
        .whenNext()
        /**/.thenTrailingState(CreateWithAction.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("YE")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList(TokenString.get("YE"))
        /**/.thenHasNext(false)

        // CharExp == String

        .givenInput("YES")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringConditionState.class)
        /**/.thenLexemeSubject("Y")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(CharExpressionConditionState.class)
        /**/.thenLexemeSubject("YES")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("YES")
        /**/.thenValueList()
        .whenNext()
        /**/.thenTrailingState(ReturnSelfAction.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("YES")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList(Answer.YES)
        /**/.thenHasNext(false);
  }

  @Test
  public void returnSelfLexerGrammar() {
    it().givenGrammar(ReturnSelfLexerGrammar.get())
        .givenInput("Yes")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringOneState.class)
        /**/.thenLexemeSubject("Y")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("Yes")
        /**/.thenValueList()
        .whenNext()
        /**/.thenTrailingState(ReturnSelfAction.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("Yes")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList(Answer.YES)
        /**/.thenHasNext(false)

        .givenInput("No")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("N")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(CharSingleOneState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("N")
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("o")
        /**/.thenValueList("N")
        .whenNext()
        /**/.thenTrailingState(ReturnSelfAction.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("N", "o")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList(Answer.NO)
        /**/.thenHasNext(false);
  }

  @Test
  public void lexemeConcatLexerGrammar() {
    it().givenGrammar(LexemeConcatLexerGrammar.get())
        .givenInput("1xx")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(CharSingleOneState.class)
        /**/.thenLexemeSubject("1")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(CharSingleZeroOrMoreState.class)
        /**/.thenLexemeSubject("1x")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("1xx")
        /**/.thenValueList()
        .whenNext()
        /**/.thenTrailingState(CreateWithAction.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("1xx")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList(TokenString.get("1xx"))
        /**/.thenHasNext(false)

        .givenInput("2xx")
        .whenNext()
        /**/.thenState(CharArrayState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("2")
        /**/.thenValueList()
        .whenNext()
        /**/.thenState(CharSingleOneState.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("2")
        .whenNext()
        /**/.thenState(CharSingleZeroOrMoreState.class)
        /**/.thenLexemeSubject("x")
        /**/.thenValueList("2")
        .whenNext()
        /**/.thenState(StringValueState.class)
        /**/.thenLexemeSubject("xx")
        /**/.thenValueList("2")
        .whenNext()
        /**/.thenTrailingState(CreateWithAction.class)
        /**/.thenLexemeSubject("")
        /**/.thenValueList("2", "xx")
        .whenNext()
        /**/.thenEndState()
        /**/.thenValueList(TokenString.addTwo("2", "xx"))
        /**/.thenHasNext(false);
  }

}