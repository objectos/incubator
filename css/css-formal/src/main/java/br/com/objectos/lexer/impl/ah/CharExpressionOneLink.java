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

class CharExpressionOneLink extends AbstractCharExpressionLink {

  CharExpressionOneLink(CharExpression expression, Link link) {
    super(expression, link);
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new CharExpressionOneLink(expression, link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeCharExpressionOneLink(this);
  }

  @Override
  public final Link addLast(Link last) {
    return new CharExpressionOneLink(expression, link.addLast(last));
  }

  @Override
  public final Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeCharSingleOneLink(CharSingleOneLink that) {
    if (expression.matches(that.value)) {
      throw new UnsupportedOperationException("Implement me");
    }

    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link reverse(Link next) {
    return link.reverse(new CharExpressionOneLink(expression, next));
  }

  @Override
  public final State toState() {
    return new CharExpressionOneState(expression, link.toState());
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.ONE;
  }

}