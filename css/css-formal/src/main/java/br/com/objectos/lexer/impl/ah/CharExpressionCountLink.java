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

class CharExpressionCountLink implements Link {

  private final CharExpression expression;
  private final int min;
  private final int max;
  private final Link link;

  CharExpressionCountLink(CharExpression expression, int min, int max, Link link) {
    this.expression = expression;
    this.min = min;
    this.max = max;
    this.link = link;
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new CharExpressionCountLink(expression, min, max, link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link addLast(Link last) {
    return new CharExpressionCountLink(expression, min, max, link.addLast(last));
  }

  @Override
  public final Link reverse(Link next) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final State toState() {
    return new CharExpressionCountState(expression, min, max, link.toState());
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return link.toString(w.begin()
        .write(expression.description)
        .write('{').write(min).write(',').write(max).write('}'));
  }

}