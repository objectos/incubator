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

import br.com.objectos.css.parser.IsNonTerminal;
import br.com.objectos.css.parser.IsTerminal;
import br.com.objectos.css.select.Selector;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.parser.grammar.AbstractParserGrammar;
import java.util.List;

class CssParserGrammar extends AbstractParserGrammar<IsNonTerminal, IsTerminal> {

  static final CssParserGrammar INSTANCE = new CssParserGrammar();

  private CssParserGrammar() {}

  @Override
  protected final void definition() {
    define(StyleSheet.class)
        .addRule(Rule.class).zeroOrMore()
        .andCreateWith(this::styleSheet);

    define(Rule.class)
        .addToken(SelectorToken.class)
        .addToken(Curly.OPEN)
        .addRule(Declaration.class).zeroOrMore()
        .addToken(Curly.CLOSE)
        .andCreateWith(this::ruleSet);

    define(CommaValue.class)
        .addToken(DeclarationToken.COMMA)
        .addRule(ThisValue.class)
        .andCreateWith(CommaValue::new);

    define(Declaration.class)
        .addToken(Identifier.class)
        .addToken(DeclarationToken.COLON)
        .addRule(ThisValue.class)
        .addToken(DeclarationToken.SEMI)
        .andCreateWith(this::declaration1);

    define(Declaration.class)
        .addToken(Identifier.class)
        .addToken(DeclarationToken.COLON)
        .addRule(ThisValue.class)
        .addRule(ThisValue.class)
        .addToken(DeclarationToken.SEMI)
        .andCreateWith(this::declaration2);

    define(Declaration.class)
        .addToken(Identifier.class)
        .addToken(DeclarationToken.COLON)
        .addRule(ThisValue.class)
        .addRule(ThisValue.class)
        .addRule(ThisValue.class)
        .addToken(DeclarationToken.SEMI)
        .andCreateWith(this::declaration3);

    define(Declaration.class)
        .addToken(Identifier.class)
        .addToken(DeclarationToken.COLON)
        .addRule(ThisValue.class)
        .addRule(ThisValue.class)
        .addRule(ThisValue.class)
        .addRule(ThisValue.class)
        .addToken(DeclarationToken.SEMI)
        .andCreateWith(this::declaration4);

    define(Declaration.class)
        .addToken(Identifier.class)
        .addToken(DeclarationToken.COLON)
        .addRule(ThisValue.class)
        .addRule(CommaValue.class).oneOrMore()
        .addToken(DeclarationToken.SEMI)
        .andCreateWith(this::declarationMulti);

    define(IntValue.class)
        .addToken(IntValue.class)
        .andCreateWith(this::intValue);

    define(DoubleValue.class)
        .addToken(DoubleValue.class)
        .andCreateWith(this::doubleValue);

    define(IntLength.class)
        .addToken(IntValue.class)
        .addToken(LengthToken.class)
        .andCreateWith(LengthToken::getInt);

    define(DoubleLength.class)
        .addToken(DoubleValue.class)
        .addToken(LengthToken.class)
        .andCreateWith(LengthToken::getDouble);

    define(IntPercentage.class)
        .addToken(IntValue.class)
        .addToken(PercentageToken.INSTANCE)
        .andCreateWith(PercentageToken::getInt);

    define(DoublePercentage.class)
        .addToken(DoubleValue.class)
        .addToken(PercentageToken.INSTANCE)
        .andCreateWith(PercentageToken::getDouble);

    define(ColorValue.class)
        .addToken(ColorValue.class)
        .andCreateWith(this::color);

    define(StringValue.class)
        .addToken(StringValue.class)
        .andCreateWith(this::stringValue);

    define(ThisValue.class)
        .addToken(Identifier.class)
        .andCreateWith(this::keyword);
  }

  private ColorValue color(ColorValue value) {
    return value;
  }

  private Declaration declaration1(
      Identifier identifier,
      DeclarationToken colon,
      ThisValue value,
      DeclarationToken semi) {
    return Declaration.get1(identifier, value);
  }

  private Declaration declaration2(
      Identifier identifier,
      DeclarationToken colon,
      ThisValue value1, ThisValue value2,
      DeclarationToken semi) {
    return Declaration.get2(identifier, value1, value2);
  }

  private Declaration declaration3(
      Identifier identifier,
      DeclarationToken colon,
      ThisValue value1, ThisValue value2, ThisValue value3,
      DeclarationToken semi) {
    return Declaration.get3(identifier, value1, value2, value3);
  }

  private Declaration declaration4(
      Identifier identifier,
      DeclarationToken colon,
      ThisValue value1, ThisValue value2, ThisValue value3, ThisValue value4,
      DeclarationToken semi) {
    return Declaration.get4(identifier, value1, value2, value3, value4);
  }

  private Declaration declarationMulti(
      Identifier identifier,
      DeclarationToken colon,
      ThisValue first,
      List<CommaValue> rest,
      DeclarationToken semi) {
    return Declaration.getMulti(identifier, first, rest);
  }

  private DoubleValue doubleValue(DoubleValue value) {
    return value;
  }

  private IntValue intValue(IntValue value) {
    return value;
  }

  private ThisValue keyword(Identifier keyword) {
    return keyword.asValue();
  }

  private Rule ruleSet(
      SelectorToken identifier, Curly open, List<Declaration> declarations, Curly close) {
    Selector selector;
    selector = identifier.asSelector();

    return new Rule(selector, declarations);
  }

  private StringValue stringValue(StringValue value) {
    return value;
  }

  private StyleSheet styleSheet(List<Rule> rules) {
    return new ParsedStyleSheet(rules);
  }

}