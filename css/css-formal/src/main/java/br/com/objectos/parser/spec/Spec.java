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

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Spec implements ProductionService {

  static interface Builder {
    List<Production> productionList();

    Map<NonTerminal, ProductionQuery> nonTerminalMap();

    void putProduction(NonTerminal symbol, Production production);
  }

  final List<Production> productionList;
  private final Map<NonTerminal, ProductionQuery> nonTerminalMap;

  Spec(Builder builder) {
    productionList = builder.productionList();
    nonTerminalMap = builder.nonTerminalMap();
  }

  @Override
  public final ProductionQuery productionQuery(NonTerminal nonTerminal) {
    Objects.requireNonNull(nonTerminal);
    return nonTerminalMap.getOrDefault(nonTerminal, ProductionQuery.empty());
  }

  // @VisibleForTesting
  final int productionListSize() {
    return productionList.size();
  }

  // @VisibleForTesting
  final List<String> productionListToString() {
    return productionList.stream()
        .map(Object::toString)
        .collect(Collectors.toList());
  }

}