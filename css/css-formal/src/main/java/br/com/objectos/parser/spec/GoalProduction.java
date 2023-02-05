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

import br.com.objectos.parser.spec.Spec.Builder;
import java.util.Iterator;

class GoalProduction extends Production {

  GoalProduction(NonTerminal symbol) {
    super(symbol, symbol.toGoalExpression());
  }

  @Override
  public final Object get(Iterator<?> iterator) {
    return iterator.next();
  }

  @Override
  final void acceptSpecBuilder(Builder builder) {
    // noop
  }

  @Override
  final void acceptStage02Builder(Stage02Builder builder, NonTerminal superType) {
    // noop
  }

  @Override
  final String toStringSymbol() {
    return "GOAL";
  }

}