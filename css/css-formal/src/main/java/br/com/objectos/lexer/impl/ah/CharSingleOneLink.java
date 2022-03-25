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

class CharSingleOneLink extends AbstractCharSingleLink {

  CharSingleOneLink(char value, Link link) {
    super(value, link);
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new CharSingleOneLink(value, link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeCharSingleOneLink(this);
  }

  @Override
  public final Link addLast(Link last) {
    return new CharSingleOneLink(value, link.addLast(last));
  }

  @Override
  public final Link mergeCharArrayLink(CharArrayLink that) {
    return that.merge(this);
  }

  @Override
  public final Link mergeCharExpressionZeroOrMoreLink(CharExpressionZeroOrMoreLink that) {
    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    if (that.matches(value)) {
      throw new UnsupportedOperationException("Implement me");
    } else {
      return new CharArrayLink().merge(this).merge(that);
    }
  }

  @Override
  public final Link mergeCharSingleOneLink(CharSingleOneLink that) {
    if (hasSameValue(that)) {
      throw new UnsupportedOperationException("Implement me");
    }

    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeStringOneLink(StringOneLink that) {
    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeStringValueLink(StringValueLink that) {
    return new CharSingleConditionLink(value)
        .onMatch(link)
        .onFail(that);
  }

  @Override
  public final Link mergeTrailingLink(TrailingLink that) {
    return new CharSingleConditionLink(value)
        .onMatch(link)
        .onFail(that);
  }

  @Override
  public final Link reverse(Link next) {
    return link.reverse(new CharSingleOneLink(value, next));
  }

  @Override
  public final State toState() {
    return new CharSingleOneState(value, link.toState());
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.ONE;
  }

}