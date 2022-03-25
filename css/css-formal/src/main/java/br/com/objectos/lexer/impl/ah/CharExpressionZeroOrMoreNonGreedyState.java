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
package br.com.objectos.lexer.impl.ah;

import br.com.objectos.lexer.charexp.CharExpression;

class CharExpressionZeroOrMoreNonGreedyState extends AbstractCharExpressionState {

  private final int stringValueCount;
  private final NonGreedyBound bound;

  CharExpressionZeroOrMoreNonGreedyState(CharExpression expression, int stringValueCount, NonGreedyBound state) {
    super(expression, state);
    this.stringValueCount = stringValueCount;
    bound = state;
  }

  @Override
  public final State next(StateSubject subject) {
    return bound.nextZeroOrMoreNonGreedy(subject, expression, stringValueCount);
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.ZERO_OR_MORE_NON_GREEDY;
  }

}