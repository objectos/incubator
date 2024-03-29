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
package br.com.objectos.css.parser.select;

import br.com.objectos.parser.grammar.AbstractParserGrammar;
import objectos.css.parser.IsNonTerminal;
import objectos.css.parser.IsTerminal;
import objectos.css.select.AdjacentSiblingSelector;
import objectos.css.select.AttributeSelector;
import objectos.css.select.AttributeValueOperator;
import objectos.css.select.AttributeValueSelector;
import objectos.css.select.ChildSelector;
import objectos.css.select.ClassSelector;
import objectos.css.select.Combinator;
import objectos.css.select.ComplexSelectorHead;
import objectos.css.select.ComplexSelectorTail;
import objectos.css.select.CompoundSelector;
import objectos.css.select.DescendantSelector;
import objectos.css.select.GeneralSiblingSelector;
import objectos.css.select.IdSelector;
import objectos.css.select.PseudoClassSelector;
import objectos.css.select.PseudoElementSelector;
import objectos.css.select.Selector;
import objectos.css.select.SelectorList;
import objectos.css.select.SelectorListHead;
import objectos.css.select.SelectorListTail;
import objectos.css.select.SimpleSelector;
import objectos.css.select.TypeSelector;
import objectos.css.select.UniversalSelector;

class SelectorParserGrammar extends AbstractParserGrammar<IsNonTerminal, IsTerminal> {

  @Override
  protected final void definition() {
    define(Goal.class)
        .addRule(Selector.class)
        .andCreateWith(Goal::new);

    // *
    define(UniversalSelector.class)
        .addToken(SelectorToken.STAR)
        .andCreateWith(this::universalSelector);

    // a
    define(TypeSelector.class)
        .addToken(IdentToken.class)
        .andCreateWith(this::typeSelector);

    // #id
    define(IdSelector.class)
        .addToken(SelectorToken.HASH)
        .addToken(IdentToken.class)
        .andCreateWith(this::idSelector);

    // .class
    define(ClassSelector.class)
        .addToken(SelectorToken.DOT)
        .addToken(IdentToken.class)
        .andCreateWith(this::classSelector);

    // [attr]
    define(AttributeSelector.class)
        .addToken(SelectorToken.ATTR_OPEN)
        .addToken(IdentToken.class)
        .addToken(SelectorToken.ATTR_CLOSE)
        .andCreateWith(this::attributeSelector);

    // [attr=value]
    define(AttributeValueSelector.class)
        .addToken(SelectorToken.ATTR_OPEN)
        .addToken(IdentToken.class)
        .addToken(AttributeValueOperator.class)
        .addToken(HasStringValue.class)
        .addToken(SelectorToken.ATTR_CLOSE)
        .andCreateWith(this::attributeValue);

    // :class
    define(PseudoClassSelector.class)
        .addToken(PseudoClassToken.class)
        .andCreateWith(PseudoClassToken::asPseudoClassSelector);

    // ::element
    define(PseudoElementSelector.class)
        .addToken(PseudoElementToken.class)
        .andCreateWith(PseudoElementToken::asPseudoElementSelector);

    define(CompoundSelector.class)
        .addRule(SimpleSelector.class)
        .addRule(SimpleSelector.class).oneOrMore()
        .andCreateWith(this::compoundSelector);

    // ul li
    define(DescendantSelector.class)
        .addRule(ComplexSelectorHead.class)
        .addToken(Combinator.DESCENDANT)
        .addRule(ComplexSelectorTail.class)
        .andCreateWith(this::descendantSelector);

    // ul > li
    define(ChildSelector.class)
        .addRule(ComplexSelectorHead.class)
        .addToken(Combinator.CHILD)
        .addRule(ComplexSelectorTail.class)
        .andCreateWith(this::childSelector);

    // li + p
    define(AdjacentSiblingSelector.class)
        .addRule(ComplexSelectorHead.class)
        .addToken(Combinator.ADJACENT_SIBLING)
        .addRule(ComplexSelectorTail.class)
        .andCreateWith(this::adjacentSiblingSelector);

    // li ~ p
    define(GeneralSiblingSelector.class)
        .addRule(ComplexSelectorHead.class)
        .addToken(Combinator.GENERAL_SIBLING)
        .addRule(ComplexSelectorTail.class)
        .andCreateWith(this::generalSiblingSelector);

    // a, ul > li
    define(SelectorList.class)
        .addRule(SelectorListHead.class)
        .addToken(Separator.COMMA)
        .addRule(SelectorListTail.class)
        .andCreateWith(this::selectorList);
  }

  private AdjacentSiblingSelector adjacentSiblingSelector(
      ComplexSelectorHead head, Combinator token, ComplexSelectorTail tail) {
    return AdjacentSiblingSelector.ofParser(head, tail);
  }

  private AttributeSelector attributeSelector(
      SelectorToken open, IdentToken name, SelectorToken close) {
    return name.asAttributeSelector();
  }

  private AttributeValueSelector attributeValue(
      SelectorToken open,
      IdentToken name, AttributeValueOperator operator, HasStringValue value,
      SelectorToken close) {
    return name.asAttributeValueSelector(operator, value);
  }

  private ChildSelector childSelector(
      ComplexSelectorHead head, Combinator token, ComplexSelectorTail tail) {
    return ChildSelector.ofParser(head, tail);
  }

  private ClassSelector classSelector(SelectorToken dot, IdentToken name) {
    return name.asClassSelector();
  }

  private CompoundSelector compoundSelector(
      SimpleSelector first, Iterable<SimpleSelector> more) {
    return CompoundSelector.ofParser(first, more);
  }

  private DescendantSelector descendantSelector(
      ComplexSelectorHead head, Combinator token, ComplexSelectorTail tail) {
    return DescendantSelector.ofParser(head, tail);
  }

  private GeneralSiblingSelector generalSiblingSelector(
      ComplexSelectorHead head, Combinator token, ComplexSelectorTail tail) {
    return GeneralSiblingSelector.ofParser(head, tail);
  }

  private IdSelector idSelector(SelectorToken hash, IdentToken name) {
    return name.asIdSelector();
  }

  private SelectorList selectorList(SelectorListHead head, Separator comma, SelectorListTail tail) {
    return SelectorList.ofParser(head, tail);
  }

  private TypeSelector typeSelector(IdentToken name) {
    return name.asTypeSelector();
  }

  private UniversalSelector universalSelector(SelectorToken start) {
    return UniversalSelector.getInstance();
  }

}