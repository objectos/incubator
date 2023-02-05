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
package br.com.objectos.lexer.impl.ah;

import br.com.objectos.lexer.charexp.CharExpression;

class CharExpressionConditionLink extends AbstractConditionLink<CharExpressionConditionLink> {

  private final CharExpression expression;

  CharExpressionConditionLink(CharExpression expression) {
    this.expression = expression;
  }

  @Override
  public final State toState() {
    return new CharExpressionConditionState(
        expression,
        failState(),
        matchState());
  }

  @Override
  final CharExpressionConditionLink self() {
    return this;
  }

  @Override
  final StateWriter toStringCondition(StateWriter w) {
    return w.begin().write(expression.description);
  }

}