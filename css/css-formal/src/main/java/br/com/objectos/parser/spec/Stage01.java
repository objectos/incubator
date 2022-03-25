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
import java.util.List;
import java.util.Set;

class Stage01 implements Stage02Builder {

  final List<Production> productionList;
  final Set<NonTerminal> nonTerminalSet;
  private List<Production> polymorphicList;

  Stage01(Stage01Builder builder) {
    productionList = builder.productionList;
    nonTerminalSet = builder.nonTerminalSet;
  }

  @Override
  public final void addProduction(Production production) {
    polymorphicList.add(production);
  }

  @Override
  public final List<Production> originalList() {
    return productionList;
  }

  @Override
  public final List<Production> polymorphicList() {
    polymorphicList = new ArrayList<>();
    for (NonTerminal maybeSuperType : nonTerminalSet) {
      for (Production production : productionList) {
        production.acceptStage02Builder(this, maybeSuperType);
      }
    }
    return polymorphicList;
  }

  public final Stage02 toStage02() {
    return new Stage02(this);
  }

}