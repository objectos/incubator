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
import br.com.objectos.lexer.impl.ah.grammar.CharExpressionProtoNextLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.CharExpressionStartingOneLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.CommentLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.EndingBrickLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.LexemeConcatLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.PushPopLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.ReturnSelfLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.StartingBrickLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.StartingCharExpZeroOneOrMoreLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.StartingCharSingleZeroOneOrMoreLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.TokenStringAndBitLexerGrammar;
import br.com.objectos.lexer.impl.ah.grammar.TokenStringLexerGrammar;
import br.com.objectos.lexer.impl.ah.model.Answer;
import br.com.objectos.lexer.impl.ah.model.Bit;
import br.com.objectos.lexer.impl.ah.model.Bracket;
import br.com.objectos.lexer.impl.ah.model.Comment;
import br.com.objectos.lexer.impl.ah.model.TokenString;
import br.com.objectos.lexer.impl.ah.model.Whitespace;
import org.testng.annotations.Test;

public class LexerTest extends LexerDriver {

  @Test
  public void tokenStringLexerGrammar() {
    it().givenLexer(TokenStringLexerGrammar.get("Lexer"))
        .whenInput("Lexer")
        /**/.thenHasNext(true)
        /**/.thenNext(new TokenString("Lexer"))
        /**/.thenHasNext(false)
        .whenInput("LexerLexer")
        /**/.thenHasNext(true)
        /**/.thenNext(new TokenString("Lexer"))
        /**/.thenHasNext(true)
        /**/.thenNext(new TokenString("Lexer"))
        /**/.thenHasNext(false);
  }

  @Test
  public void answerOnlyLexerGrammar() {
    it().givenLexer(AnswerOnlyLexerGrammar.get())
        .whenInput("YES")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(false)
        .whenInput("YESNO")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.NO)
        /**/.thenHasNext(false);
  }

  @Test
  public void answerWsLexerGrammar() {
    it().givenLexer(AnswerWsLexerGrammar.get())
        .whenInput("YES")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(false)
        .whenInput("YESNO")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.NO)
        /**/.thenHasNext(false)
        .whenInput("YES NO \t YES")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.NO)
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(false);
  }

  @Test
  public void answerAndTokenStringLexerGrammar() {
    it().givenLexer(AnswerAndTokenStringLexerGrammar.get())
        .whenInput("YESE")
        /**/.thenHasNext(true)
        /**/.thenNext(TokenString.get("YESE"))
        /**/.thenHasNext(false)
        .whenInput("YE")
        /**/.thenHasNext(true)
        /**/.thenNext(TokenString.get("YE"))
        /**/.thenHasNext(false)
        .whenInput("YES")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(false);
  }

  @Test
  public void returnSelfLexerGrammar() {
    it().givenLexer(ReturnSelfLexerGrammar.get())
        .whenInput("Yes")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(false)
        .whenInput("No")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.NO)
        /**/.thenHasNext(false);
  }

  @Test
  public void lexemeConcatLexerGrammar() {
    it().givenLexer(LexemeConcatLexerGrammar.get())
        .whenInput("1xx")
        /**/.thenHasNext(true)
        /**/.thenNext(TokenString.get("1xx"))
        /**/.thenHasNext(false)
        .whenInput("2xx")
        /**/.thenHasNext(true)
        /**/.thenNext(TokenString.get("2xx"))
        /**/.thenHasNext(false);
  }

  @Test
  public void charExpressionProtoNextLexerGrammar() {
    it().givenLexer(CharExpressionProtoNextLexerGrammar.get())
        .whenInput("yes")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(false)
        .whenInput("yyess")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(false)
        .whenInput("no")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.NO)
        /**/.thenHasNext(false)
        .whenInput("nooo")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.NO)
        /**/.thenHasNext(false)
        .whenInput("Nnooo")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.NO)
        /**/.thenHasNext(false);
  }

  @Test
  public void charExpressionStartingOneLexerGrammar() {
    it().givenLexer(CharExpressionStartingOneLexerGrammar.get())
        .whenInput("YES")
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(true)
        /**/.thenNext(Answer.YES)
        /**/.thenHasNext(false);
  }

  @Test
  public void startingCharSingleZeroOneOrMoreLexerGrammar() {
    it().givenLexer(StartingCharSingleZeroOneOrMoreLexerGrammar.get())
        .whenInput("0")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ZERO)
        /**/.thenHasNext(false)
        .whenInput("00")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ZERO)
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ZERO)
        /**/.thenHasNext(false)
        .whenInput("1")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ONE)
        /**/.thenHasNext(false)
        .whenInput("10")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ZERO)
        /**/.thenHasNext(false);
  }

  @Test
  public void startingCharExpZeroOneOrMoreLexerGrammar() {
    it().givenLexer(StartingCharExpZeroOneOrMoreLexerGrammar.get())
        .whenInput("0")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ZERO)
        /**/.thenHasNext(false)
        .whenInput("00")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ZERO)
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ZERO)
        /**/.thenHasNext(false)
        .whenInput("1")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ONE)
        /**/.thenHasNext(false)
        .whenInput("10")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ZERO)
        /**/.thenHasNext(false)
        .whenInput("i1i1i")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ONE)
        /**/.thenHasNext(false);
  }

  @Test
  public void tokenStringAndBitLexerGrammar() {
    it().givenLexer(TokenStringAndBitLexerGrammar.get())
        .whenInput("abc")
        /**/.thenHasNext(true)
        /**/.thenNext(TokenString.get("abc"))
        /**/.thenHasNext(false)
        .whenInput("a1b")
        /**/.thenHasNext(true)
        /**/.thenNext(TokenString.get("a"))
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ONE)
        /**/.thenNext(TokenString.get("b"))
        /**/.thenHasNext(false);
  }

  @Test
  public void pushPopLexerGrammar() {
    it().givenLexer(PushPopLexerGrammar.get())
        .whenInput("{a}")
        /**/.thenHasNext(true)
        /**/.thenNext(Bracket.OPEN)
        /**/.thenHasNext(true)
        /**/.thenNext(TokenString.get("a"))
        /**/.thenHasNext(true)
        /**/.thenNext(Bracket.CLOSE)
        /**/.thenHasNext(false)
        .whenInput(" { a}")
        /**/.thenHasNext(true)
        /**/.thenNext(Bracket.OPEN)
        /**/.thenHasNext(true)
        /**/.thenNext(Whitespace.SPACE)
        /**/.thenHasNext(true)
        /**/.thenNext(TokenString.get("a"))
        /**/.thenHasNext(true)
        /**/.thenNext(Bracket.CLOSE)
        /**/.thenHasNext(false);
  }
  
  @Test
  public void startingBrickLexerGrammar() {
    it().givenLexer(StartingBrickLexerGrammar.get())
        .whenInput("Zero")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ZERO)
        /**/.thenHasNext(false);
  }

  @Test
  public void endingBrickLexerGrammar() {
    it().givenLexer(EndingBrickLexerGrammar.get())
        .whenInput("Zero")
        /**/.thenHasNext(true)
        /**/.thenNext(Bit.ZERO)
        /**/.thenHasNext(false);
  }

  @Test
  public void commentLexerGrammar() {
    it().givenLexer(CommentLexerGrammar.get())
        .whenInput("aabb/*xpto*/c")
        /**/.thenHasNext(true)
        /**/.thenNext(TokenString.get("aabb"))
        /**/.thenHasNext(true)
        /**/.thenNext(new Comment("xpto"))
        /**/.thenHasNext(true)
        /**/.thenNext(TokenString.get("c"))
        /**/.thenHasNext(false);
  }

}