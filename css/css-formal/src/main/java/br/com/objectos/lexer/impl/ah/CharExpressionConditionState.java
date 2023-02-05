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

class CharExpressionConditionState extends AbstractConditionState {

  private final CharExpression expression;

  CharExpressionConditionState(CharExpression expression, State failState, State matchState) {
    super(failState, matchState);
    this.expression = expression;
  }

  @Override
  public final boolean hasNext() {
    return true;
  }

  @Override
  public final State next(StateSubject subject) {
    LexemeSubject lexeme = subject.lexemeSubject();
    if (lexeme.matchesChar(expression)) {
      lexeme.consumeChar();
      return matchState;
    } else {
      return failState;
    }
  }

  @Override
  final StateWriter toStringCondition(StateWriter w) {
    return w.begin().write(expression.description);
  }

}