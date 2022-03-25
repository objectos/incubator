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

abstract class AbstractCharExpressionLink implements Link, LinkMerger {

  final CharExpression expression;
  Link link;

  AbstractCharExpressionLink(CharExpression expression, Link link) {
    this.expression = expression;
    this.link = link;
  }

  @Override
  public final void concat() {
    if (link.isConcat()) {
      link = link.concatLink();
    } else {
      link.concat();
    }
  }

  @Override
  public final Link merge(Link nextLink) {
    return nextLink.acceptLinkMerger(this);
  }

  public final char[] toCharArray() {
    return expression.toCharArray();
  }

  @Override
  public final String toString() {
    return Link.toString(this);
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return link.toString(w.begin()
        .write(expression.description)
        .writeQuantifier(quantifier()));
  }

  public final CharExpressionZeroOrMoreLink zeroOrMoreLink() {
    return new CharExpressionZeroOrMoreLink(expression, link);
  }

  final boolean hasSameValue(AbstractCharExpressionLink that) {
    return expression.equals(that.expression);
  }

  final boolean matches(char value) {
    return expression.matches(value);
  }

  final int matchingLength(AbstractStringLink link) {
    return expression.matchingLength(link.string);
  }

  abstract Quantifier quantifier();

}