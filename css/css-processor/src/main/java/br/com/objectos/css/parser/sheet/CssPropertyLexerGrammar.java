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

class CssPropertyLexerGrammar extends AbstractLexerGrammar {

  private static final CssPropertyLexerGrammar INSTANCE = new CssPropertyLexerGrammar();

  private CssPropertyLexerGrammar() {}

  public static CssPropertyLexerGrammar get() {
    return INSTANCE;
  }

  @Override
  final void definitionMore() {
    CharExpression identifierSymbols = CharExpression.is('-');
    CharExpression identifierLettersLower = CharExpression.range('a', 'z');
    CharExpression identifierLettersUpper = CharExpression.range('A', 'Z');
    CharExpression identifierLetters = CharExpression.or(
        identifierLettersLower,
        identifierLettersUpper
    );
    CharExpression identifierAll = CharExpression.or(
        identifierSymbols,
        identifierLetters
    );

    defineToken(LengthToken.class)
        .asEnum()
        .andCreateWith(LengthToken::name);

    defineToken(Identifier.class)
        .addChar('-').optional()
        .addChar(identifierLetters).concat()
        .addChar(identifierAll).zeroOrMore().concat()
        .andCreateWith(Identifier::new);

    defineToken(IntValue.class)
        .addChar('-').optional()
        .addChar(CharExpression.isDigit()).oneOrMore().concat()
        .andCreateWith(IntValue::valueOf);

    defineToken(DoubleValue.class)
        .addChar('-').optional()
        .addChar(CharExpression.isDigit()).oneOrMore().concat()
        .addChar('.').concat()
        .addChar(CharExpression.isDigit()).oneOrMore().concat()
        .andCreateWith(DoubleValue::valueOf);

    CharExpression stringValidChars = CharExpression.not('\"');

    defineToken(StringValue.class)
        .addChar('"')
        .addChar(stringValidChars).zeroOrMore()
        .addChar('"')
        .andCreateWith(this::stringValue);

    CharExpression hexColorChars = CharExpression.or(
        CharExpression.isDigit(),
        CharExpression.is('a', 'b', 'c', 'd', 'e', 'f'),
        CharExpression.is('A', 'B', 'C', 'D', 'E', 'F')
    );

    defineToken(ColorValue.class)
        .addChar('#')
        .addChar(hexColorChars).oneOrMore().concat()
        .andCreateWith(ColorValue::new);

    defineToken(DeclarationToken.class)
        .asEnum()
        .andCreateWith(DeclarationToken::toString);

    defineToken(PercentageToken.INSTANCE)
        .addChar('%')
        .andReturnSelf();

    defineToken(Curly.CLOSE)
        .addChar('}')
        .andPopLexer();

    defineToken(Whitespace.INSTANCE)
        .addChar(CharExpression.isWhitespace()).oneOrMore()
        .andIgnore();
  }

  private StringValue stringValue(String oq, String value, String cq) {
    return new StringValue(value);
  }

}