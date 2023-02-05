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

class CharExpressionOneOrMoreLink extends AbstractCharExpressionLink {

  CharExpressionOneOrMoreLink(CharExpression expression, Link link) {
    super(expression, link);
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new CharExpressionOneOrMoreLink(expression, link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeCharExpressionOneOrMoreLink(this);
  }

  @Override
  public final Link addLast(Link last) {
    return new CharExpressionOneOrMoreLink(expression, link.addLast(last));
  }

  @Override
  public final Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    if (hasSameValue(that)) {
      return new CharExpressionOneOrMoreLink(
          expression,
          link.merge(that.link));
    } else {
      return new CharArrayLink().merge(this).merge(that);
    }
  }

  @Override
  public final Link mergeCharSingleOneLink(CharSingleOneLink that) {
    if (expression.matches(that.value)) {
      throw new UnsupportedOperationException("Implement me");
    } else {
      return new CharArrayLink().merge(this).merge(that);
    }
  }

  @Override
  public final Link mergeCharSingleOptionalLink(CharSingleOptionalLink that) {
    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeStringOneLink(StringOneLink that) {
    int matchingLength = matchingLength(that);
    String leading = that.leading(matchingLength);

    if (leading.isEmpty()) {
      return new CharArrayLink().merge(this).merge(that);
    }

    String trailing = that.trailing(matchingLength);

    if (!trailing.isEmpty()) {
      throw new UnsupportedOperationException("Implement me");
    }

    return this;
  }

  @Override
  public final Link reverse(Link next) {
    return link.reverse(new CharExpressionOneOrMoreLink(expression, next));
  }

  @Override
  public final State toState() {
    CharExpressionZeroOrMoreState zeroOrMore = new CharExpressionZeroOrMoreState(expression, link.toState());
    return new CharExpressionOneState(expression, zeroOrMore);
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.ONE_OR_MORE;
  }

}