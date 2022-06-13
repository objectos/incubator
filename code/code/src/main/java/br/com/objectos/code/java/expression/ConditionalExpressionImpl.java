/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.java.expression;

import br.com.objectos.code.java.element.AbstractImmutableCodeElement;
import br.com.objectos.code.java.element.CodeElement;
import objectos.util.ImmutableList;

final class ConditionalExpressionImpl
    extends AbstractImmutableCodeElement
    implements ConditionalExpression {

  private ConditionalExpressionImpl(CodeElement... elements) {
    super(elements);
  }

  private ConditionalExpressionImpl(ImmutableList<CodeElement> elements) {
    super(elements);
  }

  static ConditionalExpression ternary0(
      ConditionalOrExpression condition,
      Expression trueExpression, Expression falseExpression) {
    return new ConditionalExpressionImpl(
        condition,
        space(), questionMark(), space(),
        trueExpression,
        space(), colon(), space(),
        falseExpression
    );
  }

  @Override
  public final ConditionalExpression nl() {
    return new ConditionalExpressionImpl(appendNextLine());
  }

}
