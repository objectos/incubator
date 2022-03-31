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
package br.com.objectos.lexer.impl.ah.grammar;

import br.com.objectos.lexer.grammar.AbstractLexerGrammar;
import br.com.objectos.lexer.impl.ah.model.IsBrick;
import br.com.objectos.lexer.impl.ah.model.IsToken;
import br.com.objectos.lexer.impl.ah.model.TokenString;

public class LexemeConcatLexerGrammar extends AbstractLexerGrammar<IsToken, IsBrick> {

  private static final LexemeConcatLexerGrammar INSTANCE = new LexemeConcatLexerGrammar();

  private LexemeConcatLexerGrammar() {
  }

  public static LexemeConcatLexerGrammar get() {
    return INSTANCE;
  }

  @Override
  protected final void definition() {
    defineToken(TokenString.class)
        .addChar('1')
        .addChar('x').oneOrMore().concat()
        .andCreateWith(TokenString::new);

    defineToken(TokenString.class)
        .addChar('2')
        .addChar('x').oneOrMore()
        .andCreateWith(TokenString::addTwo);
  }

}