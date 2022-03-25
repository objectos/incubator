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
package br.com.objectos.parser.spec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Stage02 {

  final List<Production> originalList;
  final List<Production> polymorphicList;

  Stage02(Stage02Builder builder) {
    originalList = builder.originalList();
    polymorphicList = builder.polymorphicList();
  }

  public final Spec toSpec() {
    return new ThisSpecBuilder().build();
  }

  private class ThisSpecBuilder implements Spec.Builder {

    private final Set<Production> productionList = new LinkedHashSet<>();
    private final Map<NonTerminal, Set<Production>> nonTerminalMap = new HashMap<>();

    public ThisSpecBuilder() {
      addList(originalList);
      addList(polymorphicList);
    }

    public Spec build() {
      return new Spec(this);
    }

    @Override
    public List<Production> productionList() {
      return new ArrayList<>(productionList);
    }

    @Override
    public Map<NonTerminal, ProductionQuery> nonTerminalMap() {
      Map<NonTerminal, ProductionQuery> res = new HashMap<>(nonTerminalMap.size());

      for (Entry<NonTerminal, Set<Production>> entry : nonTerminalMap.entrySet()) {
        NonTerminal nonTerminal = entry.getKey();
        Set<Production> set = entry.getValue();
        List<Production> list = new ArrayList<>(set);
        res.put(nonTerminal, new ProductionQuery(list));
      }

      return Collections.unmodifiableMap(res);
    }

    @Override
    public void putProduction(NonTerminal symbol, Production production) {
      nonTerminalMap.computeIfAbsent(symbol, this::newLinkedHashSet).add(production);
    }

    private void addList(List<Production> list) {
      for (Production production : list) {
        productionList.add(production);
        production.acceptSpecBuilder(this);
      }
    }

    private <K, V> Set<V> newLinkedHashSet(K key) {
      return new LinkedHashSet<>();
    }

  }

}