/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.css.parser.sheet;

import br.com.objectos.lexer.charexp.CharExpression;

class CssLexerGrammar extends AbstractLexerGrammar {

  static final CssLexerGrammar INSTANCE = new CssLexerGrammar();

  private CssLexerGrammar() {}

  @Override
  final void definitionMore() {
    CharExpression selStartSymbols = CharExpression.is(
        '#', '.', '[', ':', '-', '"', '\'', '*'
    );
    CharExpression selStart = CharExpression.or(
        CharExpression.or(CharExpression.range('A', 'Z'), CharExpression.range('a', 'z')),
        selStartSymbols);

    defineToken(SelectorToken.class)
        .addChar(selStart)
        .addChar(CharExpression.not('{')).zeroOrMore().concat()
        .andCreateWith(SelectorToken::new);

    defineToken(Curly.OPEN)
        .addChar('{')
        .andPushLexer(CssPropertyLexerGrammar.get());

    defineToken(Whitespace.INSTANCE)
        .addChar(CharExpression.isWhitespace()).oneOrMore()
        .andIgnore();
  }

}