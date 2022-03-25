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

class CharSingleOneOrMoreLink extends AbstractCharSingleLink {

  CharSingleOneOrMoreLink(char value, Link link) {
    super(value, link);
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new CharSingleOneOrMoreLink(value, link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeCharSingleOneOrMoreLink(this);
  }

  @Override
  public final Link addLast(Link last) {
    return new CharSingleOneOrMoreLink(value, link.addLast(last));
  }

  @Override
  public final Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    if (that.matches(value)) {
      throw new UnsupportedOperationException("Implement me");
    }

    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeCharSingleZeroOrMoreLink(CharSingleZeroOrMoreLink that) {
    if (hasSameValue(that)) {
      throw new UnsupportedOperationException("Implement me");
    }

    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link reverse(Link next) {
    return link.reverse(new CharSingleOneOrMoreLink(value, next));
  }

  @Override
  public final State toState() {
    CharSingleZeroOrMoreState zeroOrMore = new CharSingleZeroOrMoreState(value, link.toState());
    return new CharSingleOneState(value, zeroOrMore);
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.ONE_OR_MORE;
  }

}