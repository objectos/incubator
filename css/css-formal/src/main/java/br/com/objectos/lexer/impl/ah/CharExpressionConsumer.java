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

class CharExpressionConsumer implements Consumer {

  private final CharExpression expression;

  CharExpressionConsumer(CharExpression expression) {
    this.expression = expression;
  }

  @Override
  public final Linker linkOne(Linker linker) {
    return linker.charExpressionOne(expression);
  }

  @Override
  public final Linker linkOneOrMore(Linker linker) {
    return linker.charExpressionOneOrMore(expression);
  }

  @Override
  public final Linker linkZeroOrMore(Linker linker) {
    return linker.charExpressionZeroOrMore(expression);
  }

  @Override
  public final Linker linkZeroOrMoreNonGreedy(Linker linker) {
    return linker.charExpressionZeroOrMoreNonGreedy(expression);
  }

  @Override
  public final Linker linkOptional(Linker linker) {
    return linker.charExpressionOptional(expression);
  }

  @Override
  public final StateWriter toString(StateWriter sw) {
    return sw.begin().write(expression.description);
  }

}