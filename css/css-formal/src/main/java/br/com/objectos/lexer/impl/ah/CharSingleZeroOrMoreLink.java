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

class CharSingleZeroOrMoreLink extends AbstractCharSingleLink {

  CharSingleZeroOrMoreLink(char value, Link link) {
    super(value, link);
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new CharSingleZeroOrMoreLink(value, link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeCharSingleZeroOrMoreLink(this);
  }

  @Override
  public final Link addLast(Link last) {
    return new CharSingleZeroOrMoreLink(value, link.addLast(last));
  }

  @Override
  public final Link mergeCharSingleOneOrMoreLink(CharSingleOneOrMoreLink that) {
    if (hasSameValue(that)) {
      return new CharSingleConditionLink(value)
          .onMatch(new CharSingleZeroOrMoreLink(value, link.merge(that.link)))
          .onFail(link);
    } else {
      throw new UnsupportedOperationException("Implement me");
    }
  }

  @Override
  public final Link reverse(Link next) {
    return link.reverse(new CharSingleZeroOrMoreLink(value, next));
  }

  @Override
  public final State toState() {
    return new CharSingleZeroOrMoreState(value, link.toState());
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.ZERO_OR_MORE;
  }

}