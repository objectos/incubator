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

class StringValueLink implements Link, LinkMerger {

  final Link link;

  StringValueLink(Link link) {
    this.link = link;
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new StringValueLink(link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeStringValueLink(this);
  }

  @Override
  public final State acceptCharExpressionZeroOrMoreNonGreedyStateBuilder(
      CharExpressionZeroOrMoreNonGreedyStateBuilder builder) {
    return link.acceptCharExpressionZeroOrMoreNonGreedyStateBuilder(builder.addStringValueLink());
  }

  @Override
  public final Link addLast(Link last) {
    return new StringValueLink(link.addLast(last));
  }

  @Override
  public final void concat() {
    link.concat();
  }

  @Override
  public final Link concatLink() {
    return link;
  }

  @Override
  public final boolean isConcat() {
    return true;
  }

  @Override
  public final Link merge(Link nextLink) {
    return nextLink.acceptLinkMerger(this);
  }

  @Override
  public final Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    return link.merge(that);
  }

  @Override
  public final Link mergeCharExpressionZeroOrMoreLink(CharExpressionZeroOrMoreLink that) {
    return that.mergeStringValueLink(this);
  }

  @Override
  public final Link mergeCharSingleOneLink(CharSingleOneLink that) {
    return new CharSingleConditionLink(that.value)
        .onMatch(that.link)
        .onFail(this);
  }

  @Override
  public final Link mergeStringValueLink(StringValueLink that) {
    return new StringValueLink(link.merge(that.link));
  }

  @Override
  public final Link reverse(Link next) {
    return link.reverse(new StringValueLink(next));
  }

  @Override
  public final State toState() {
    return new StringValueState(link.toState());
  }

  @Override
  public final String toString() {
    return Link.toString(this);
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return link.toString(w.begin().write('|'));
  }

}