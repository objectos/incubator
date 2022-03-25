/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.parser.select;

import br.com.objectos.css.parser.IsTerminal;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.lexer.charexp.CharExpression;
import br.com.objectos.lexer.grammar.AbstractLexerGrammar;

class SelectorLexerGrammar extends AbstractLexerGrammar<IsTerminal, Brick> {

  SelectorLexerGrammar() {}

  @Override
  protected final void definition() {
    CharExpression identStart = CharExpression.or(
        CharExpression.isLetter(),
        CharExpression.is('_'));
    CharExpression identMore = CharExpression.or(
        CharExpression.isLetterOrDigit(),
        CharExpression.is('-', '_'));

    defineToken(IdentToken.class)
        .addChar(identStart)
        .addChar(identMore).zeroOrMore()
        .andCreateWith(IdentToken::new);

    defineToken(SelectorToken.class)
        .asEnum()
        .andCreateWith(SelectorToken::value);

    CharExpression pseudoStart = CharExpression.or(
        CharExpression.isLetter(),
        CharExpression.is('-', '_'));

    defineToken(PseudoClassToken.class)
        .addChar(':')
        .addChar(pseudoStart)
        .addChar(identMore).zeroOrMore().concat()
        .andCreateWith(PseudoClassToken::new);

    defineToken(PseudoElementToken.class)
        .addString("::")
        .addChar(pseudoStart)
        .addChar(identMore).zeroOrMore().concat()
        .andCreateWith(PseudoElementToken::new);

    defineToken(AttributeValueOperator.class)
        .asEnum()
        .andCreateWith(AttributeValueOperator::getSymbol);

    defineToken(DoubleQuotedString.class)
        .addChar('"')
        .addChar(CharExpression.not('"')).zeroOrMore()
        .addChar('"')
        .andCreateWith(DoubleQuotedString::new);

    defineToken(SingleQuotedString.class)
        .addChar('\'')
        .addChar(CharExpression.not('\'')).zeroOrMore()
        .addChar('\'')
        .andCreateWith(SingleQuotedString::new);

    defineToken(Combinator.CHILD)
        .addChar(CharExpression.isWhitespace()).zeroOrMore()
        .addChar('>')
        .addChar(CharExpression.isWhitespace()).zeroOrMore()
        .andReturnSelf();

    defineToken(Combinator.ADJACENT_SIBLING)
        .addChar(CharExpression.isWhitespace()).zeroOrMore()
        .addChar('+')
        .addChar(CharExpression.isWhitespace()).zeroOrMore()
        .andReturnSelf();

    defineToken(Combinator.GENERAL_SIBLING)
        .addChar(CharExpression.isWhitespace()).zeroOrMore()
        .addChar('~')
        .addChar(CharExpression.isWhitespace()).zeroOrMore()
        .andReturnSelf();

    defineToken(Separator.COMMA)
        .addChar(CharExpression.isWhitespace()).zeroOrMore()
        .addChar(',')
        .addChar(CharExpression.isWhitespace()).zeroOrMore()
        .andReturnSelf();

    defineToken(Combinator.DESCENDANT)
        .addChar(CharExpression.isWhitespace()).oneOrMore()
        .andReturnSelf();
  }

}
