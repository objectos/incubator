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

import br.com.objectos.code.java.element.AbstractDefaultImmutableCodeElement;
import br.com.objectos.code.java.element.CodeElement;
import br.com.objectos.code.java.type.NamedReferenceType;
import objectos.util.UnmodifiableList;

final class RelationalExpressionImpl
    extends AbstractDefaultImmutableCodeElement
    implements RelationalExpression {

  private RelationalExpressionImpl(CodeElement... elements) {
    super(elements);
  }

  private RelationalExpressionImpl(UnmodifiableList<CodeElement> elements) {
    super(elements);
  }

  static RelationalExpression instanceOf0(
      RelationalExpression subject, NamedReferenceType test) {
    return new RelationalExpressionImpl(
        subject, space(), RelationalOperator.INSTANCE_OF, space(), test
    );
  }

  static RelationalExpression of0(
      RelationalOperator operator, RelationalExpression lhs, ShiftExpression rhs) {
    return new RelationalExpressionImpl(
        lhs, space(), operator, space(), rhs
    );
  }

  @Override
  public final RelationalExpression nl() {
    return new RelationalExpressionImpl(appendNextLine());
  }

  @Override
  protected final ArrayReferenceExpression selfArrayReferenceExpression() {
    throw newUoe(RelationalExpression.class);
  }

  @Override
  protected final Callee selfCallee() {
    throw newUoe(RelationalExpression.class);
  }

  @Override
  protected final ConditionalAndExpression selfConditionalAndExpression() {
    return this;
  }

  @Override
  protected final LeftHandSide selfLeftHandSide() {
    throw newUoe(RelationalExpression.class);
  }

  @Override
  protected final MethodReferenceReferenceExpression selfMethodReferenceReferenceExpression() {
    throw newUoe(RelationalExpression.class);
  }

  @Override
  protected final MultiplicativeExpression selfMultiplicativeExpression() {
    throw newUoe(RelationalExpression.class);
  }

  @Override
  protected final PostfixExpression selfPostfixExpression() {
    throw newUoe(RelationalExpression.class);
  }

  @Override
  protected final RelationalExpression selfRelationalExpression() {
    return this;
  }

}