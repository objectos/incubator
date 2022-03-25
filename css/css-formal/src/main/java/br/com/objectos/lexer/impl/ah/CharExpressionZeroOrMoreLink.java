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

class CharExpressionZeroOrMoreLink extends AbstractCharExpressionLink {

  CharExpressionZeroOrMoreLink(CharExpression expression, Link link) {
    super(expression, link);
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new CharExpressionZeroOrMoreLink(expression, link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeCharExpressionZeroOrMoreLink(this);
  }

  @Override
  public final Link addLast(Link last) {
    return new CharExpressionZeroOrMoreLink(expression, link.addLast(last));
  }

  public final CharExpressionCountLink charExpressionCountLink(int min, int max) {
    return new CharExpressionCountLink(expression, min, max, link);
  }

  public final CharExpressionConditionLink condition(Link onFail) {
    return new CharExpressionConditionLink(expression)
        .onMatch(this)
        .onFail(onFail);
  }

  @Override
  public final Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    if (hasSameValue(that)) {
      return new CharExpressionConditionLink(expression)
          .onMatch(new CharExpressionZeroOrMoreLink(expression, link.merge(that.link)))
          .onFail(link);
    } else {
      return new CharArrayLink().merge(that).merge(this);
    }
  }

  @Override
  public final Link mergeCharExpressionZeroOrMoreLink(CharExpressionZeroOrMoreLink that) {
    if (hasSameValue(that)) {
      return new CharExpressionZeroOrMoreLink(expression, link.merge(that.link));
    } else {
      throw new UnsupportedOperationException("Implement me");
    }
  }

  @Override
  public final Link mergeCharSingleOneLink(CharSingleOneLink that) {
    return that.mergeCharExpressionZeroOrMoreLink(this);
  }

  @Override
  public final Link mergeStringOneLink(StringOneLink that) {
    return that.mergeCharExpressionZeroOrMoreLink(this);
  }

  @Override
  public final Link mergeStringValueLink(StringValueLink that) {
    return new CharExpressionConditionLink(expression)
        .onMatch(this)
        .onFail(link.merge(that));
  }

  @Override
  public final Link reverse(Link next) {
    return link.reverse(new CharExpressionZeroOrMoreLink(expression, next));
  }

  @Override
  public final State toState() {
    return new CharExpressionZeroOrMoreState(expression, link.toState());
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.ZERO_OR_MORE;
  }

}