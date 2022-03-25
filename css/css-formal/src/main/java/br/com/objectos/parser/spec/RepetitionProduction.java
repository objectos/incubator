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

import java.util.Iterator;

class RepetitionProduction extends Production {

  public RepetitionProduction(Repetition symbol, Expression expression) {
    super(symbol, expression);
  }

  @Override
  public final Object get(Iterator<?> iterator) {
    return ((Repetition) symbol).get(iterator);
  }

  @Override
  final void acceptSpecBuilder(Spec.Builder builder) {
    builder.putProduction(symbol, this);
  }

  @Override
  final void acceptStage02Builder(Stage02Builder builder, NonTerminal superType) {
    symbol.acceptStage02Builder(builder, superType);
  }

  @Override
  final String toStringSymbol() {
    return symbol.toString();
  }

}