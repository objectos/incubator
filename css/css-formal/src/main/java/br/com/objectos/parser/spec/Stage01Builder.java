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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Stage01Builder implements SpecBuilderDelegate {

  final List<Production> productionList = new ArrayList<>();
  final Set<NonTerminal> nonTerminalSet = new HashSet<>();

  @Override
  public final void addProduction(Production production) {
    productionList.add(production);
    production.acceptStage01Builder(this);
  }

  public final Stage01 build() {
    return new Stage01(this);
  }

  final void addNonTerminalSet(NonTerminal symbol) {
    nonTerminalSet.add(symbol);
  }

}