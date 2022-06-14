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
package br.com.objectos.parser.impl.rd;

import br.com.objectos.parser.spec.NonTerminal;
import br.com.objectos.parser.spec.Production;
import br.com.objectos.parser.spec.ProductionQuery;
import br.com.objectos.parser.spec.Symbol;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

class FakeStateSubjectBuilder {

  final List<Symbol> expression = new ArrayList<>();
  final Map<NonTerminal, List<Production>> nonTerminalMap = new HashMap<>();
  Object result;
  Iterator<Object> source = ImmutableList.of().iterator();
  final List<Object> tokenList = new ArrayList<>();
  final Deque<Object> valueList = new ArrayDeque<>();

  public FakeStateSubjectBuilder addSymbol(Symbol symbol) {
    expression.add(symbol);
    return this;
  }

  //

  public FakeStateSubjectBuilder addValue(Object value) {
    valueList.add(value);
    return this;
  }

  public FakeStateSubject build() {
    return new FakeStateSubject(this);
  }

  public FakeStateSubjectBuilder putProduction(NonTerminal nonTerminal, Production production) {
    nonTerminalMap.computeIfAbsent(nonTerminal, k -> new ArrayList<>()).add(production);
    return this;
  }

  public FakeStateSubjectBuilder putProductionQuery(NonTerminal nonTerminal,
      ProductionQuery productionQuery) {
    List<Production> list = nonTerminalMap.computeIfAbsent(nonTerminal, k -> new ArrayList<>());
    for (Production production : productionQuery) {
      list.add(production);
    }
    return this;
  }

  public FakeStateSubjectBuilder set(Object aValue) {
    result = aValue;
    return this;
  }

  public FakeStateSubjectBuilder source(Object... values) {
    source = Arrays.asList(values).iterator();
    return this;
  }

  ProductionQuery productionQuery(NonTerminal nonTerminal) {
    List<Production> list = nonTerminalMap.getOrDefault(nonTerminal, Collections.emptyList());
    return new ProductionQuery(list);
  }

  Iterator<Object> valueIterator(int size) {
    MutableList<Object> list = new MutableList<>();

    for (int i = 0; i < size; i++) {
      list.add(valueList.removeLast());
    }

    return list.iterator();
  }

}