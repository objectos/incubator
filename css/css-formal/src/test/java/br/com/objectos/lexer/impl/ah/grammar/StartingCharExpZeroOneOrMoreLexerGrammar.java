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
package br.com.objectos.lexer.impl.ah.grammar;

import br.com.objectos.lexer.grammar.AbstractLexerGrammar;
import br.com.objectos.lexer.impl.ah.model.Bit;
import br.com.objectos.lexer.impl.ah.model.CharExpressions;
import br.com.objectos.lexer.impl.ah.model.IsBrick;
import br.com.objectos.lexer.impl.ah.model.IsToken;

public class StartingCharExpZeroOneOrMoreLexerGrammar extends AbstractLexerGrammar<IsToken, IsBrick> {

  private static final StartingCharExpZeroOneOrMoreLexerGrammar INSTANCE = new StartingCharExpZeroOneOrMoreLexerGrammar();

  private StartingCharExpZeroOneOrMoreLexerGrammar() {
  }

  public static StartingCharExpZeroOneOrMoreLexerGrammar get() {
    return INSTANCE;
  }

  @Override
  protected final void definition() {
    defineToken(Bit.ZERO)
        .addChar(CharExpressions._1).zeroOrMore()
        .addChar('0')
        .andReturnSelf();

    defineToken(Bit.ONE)
        .addChar(CharExpressions._1).oneOrMore()
        .andReturnSelf();
  }

}