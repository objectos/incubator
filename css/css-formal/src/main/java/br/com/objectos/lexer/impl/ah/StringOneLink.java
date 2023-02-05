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

class StringOneLink extends AbstractStringLink {

  StringOneLink(String string, Link link) {
    super(string, link);
  }

  @Override
  public final Link acceptBrickMap(BrickMap brickMap) {
    return new StringOneLink(string, link.acceptBrickMap(brickMap));
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeStringOneLink(this);
  }

  @Override
  public final State acceptCharExpressionZeroOrMoreNonGreedyStateBuilder(
      CharExpressionZeroOrMoreNonGreedyStateBuilder builder) {
    return builder.buildWith(toState());
  }

  @Override
  public final Link addLast(Link last) {
    return new StringOneLink(string, link.addLast(last));
  }

  @Override
  public final Link mergeBrickValueOneLink(BrickValueOneLink that) {
    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeCharExpressionZeroOrMoreLink(CharExpressionZeroOrMoreLink that) {
    int matchingLength = that.matchingLength(this);
    String leading = leading(matchingLength);

    if (leading.isEmpty()) {
      throw new UnsupportedOperationException("Implement me");
    }

    String trailing = trailing(matchingLength);

    if (!trailing.isEmpty()) {
      throw new UnsupportedOperationException("Implement me");
    }

    return new StringConditionLink(leading)
        .onMatch(that.condition(link))
        .onFail(that);
  }

  @Override
  public final Link mergeStringOneLink(StringOneLink that) {
    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link mergeStringValueLink(StringValueLink that) {
    return new CharArrayLink().merge(this).merge(that);
  }

  @Override
  public final Link reverse(Link next) {
    return link.reverse(new StringOneLink(string, next));
  }

  @Override
  public final StringOneState toState() {
    return new StringOneState(string, link.toState());
  }

  public final String trailing(int length) {
    return string.substring(length);
  }

  public final Link trailingLink(int length) {
    String trailing = trailing(length);
    return trailing.length() == 1
        ? new CharSingleOneLink(trailing.charAt(0), link)
        : new StringOneLink(trailing(length), link);
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.ONE;
  }

}