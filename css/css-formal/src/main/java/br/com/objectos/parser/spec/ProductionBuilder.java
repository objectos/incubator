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
package br.com.objectos.parser.spec;

import br.com.objectos.parser.grammar.Constructor1;
import br.com.objectos.parser.grammar.Constructor2;
import br.com.objectos.parser.grammar.Constructor3;
import br.com.objectos.parser.grammar.Constructor4;
import br.com.objectos.parser.grammar.Constructor5;
import br.com.objectos.parser.grammar.Constructor6;
import br.com.objectos.parser.grammar.Constructor7;
import java.util.List;
import java.util.Objects;
import objectos.util.GrowableList;

class ProductionBuilder {

  private CollectionKind collectionKind;
  private Repeatable currentSymbol;
  private final GrowableList<Symbol> expressionList = new GrowableList<>();
  private final ParserKind parserKind;

  private final GrowableList<Production> productionList = new GrowableList<>();
  private Quantifier quantifier;
  private final NonTerminal symbol;

  ProductionBuilder(ParserKind parserKind, Class<?> type) {
    this(parserKind, NonTerminal.get(type));
  }

  ProductionBuilder(ParserKind parserKind, NonTerminal symbol) {
    this.parserKind = parserKind;
    this.symbol = symbol;
    reset();
  }

  public final ProductionBuilder addRuleType(Class<?> type) {
    return addSymbol(NonTerminal.get(type));
  }

  public final ProductionBuilder addTokenType(Class<?> tokenType) {
    return addSymbol(Terminal.get(tokenType));
  }

  public final ProductionBuilder addTokenValue(Object value) {
    return addSymbol(Terminal.get(value));
  }

  public final <E, A1> List<Production> andCreateWith(Constructor1<E, A1> constructor) {
    return andCreateWith0(Factory.factoryOf(constructor));
  }

  public final <E, A1, A2> List<Production> andCreateWith(Constructor2<E, A1, A2> constructor) {
    return andCreateWith0(Factory.factoryOf(constructor));
  }

  public final <E, A1, A2, A3> List<Production>
      andCreateWith(Constructor3<E, A1, A2, A3> constructor) {
    return andCreateWith0(Factory.factoryOf(constructor));
  }

  public final <E, A1, A2, A3, A4> List<Production>
      andCreateWith(Constructor4<E, A1, A2, A3, A4> constructor) {
    return andCreateWith0(Factory.factoryOf(constructor));
  }

  public final <E, A1, A2, A3, A4, A5> List<Production>
      andCreateWith(Constructor5<E, A1, A2, A3, A4, A5> constructor) {
    return andCreateWith0(Factory.factoryOf(constructor));
  }

  public final <E, A1, A2, A3, A4, A5, A6> List<Production> andCreateWith(
      Constructor6<E, A1, A2, A3, A4, A5, A6> constructor) {
    return andCreateWith0(Factory.factoryOf(constructor));
  }

  public final <E, A1, A2, A3, A4, A5, A6, A7> List<Production> andCreateWith(
      Constructor7<E, A1, A2, A3, A4, A5, A6, A7> constructor) {
    return andCreateWith0(Factory.factoryOf(constructor));
  }

  public final ProductionBuilder oneOrMore() {
    quantifier = Quantifier.ONE_OR_MORE;
    return this;
  }

  public final ProductionBuilder optional() {
    quantifier = Quantifier.OPTIONAL;
    return this;
  }

  public final ProductionBuilder zeroOrMore() {
    quantifier = Quantifier.ZERO_OR_MORE;
    return this;
  }

  final void addOne() {
    expressionList.add(currentSymbol);
  }

  final void addOneOrMore() {
    addRepetition(Repetition.oneOrMore(currentSymbol, collectionKind));
  }

  final void addOptional() {
    Optional optional = Optional.get(currentSymbol);
    expressionList.add(optional);
    optional.acceptProductionList(productionList);
  }

  final void addZeroOrMore() {
    addRepetition(Repetition.zeroOrMore(currentSymbol, collectionKind));
  }

  private void addRepetition(Repetition repetition) {
    expressionList.add(repetition);
    parserKind.acceptRepetition(repetition, productionList);
  }

  private ProductionBuilder addSymbol(Repeatable newSymbol) {
    addSymbolIfNecessary();
    currentSymbol = Objects.requireNonNull(newSymbol);
    return this;
  }

  private void addSymbolIfNecessary() {
    if (currentSymbol != null) {
      quantifier.add(this);
      reset();
    }
  }

  private List<Production> andCreateWith0(Factory factory) {
    addSymbolIfNecessary();
    productionList.add(buildParent(factory));
    return productionList;
  }

  private Production buildParent(Factory factory) {
    Expression expression = new Expression(expressionList.toUnmodifiableList());
    return new FactoryProduction(symbol, expression, factory);
  }

  private void reset() {
    currentSymbol = null;
    quantifier = Quantifier.ONE;
    collectionKind = CollectionKind.LIST;
  }

  private enum Quantifier {

    ONE {
      @Override
      final void add(ProductionBuilder outer) {
        outer.addOne();
      }
    },

    ONE_OR_MORE {
      @Override
      final void add(ProductionBuilder outer) {
        outer.addOneOrMore();
      }
    },

    OPTIONAL {
      @Override
      final void add(ProductionBuilder outer) {
        outer.addOptional();
      }
    },

    ZERO_OR_MORE {
      @Override
      final void add(ProductionBuilder outer) {
        outer.addZeroOrMore();
      }
    };

    abstract void add(ProductionBuilder outer);

  }

}