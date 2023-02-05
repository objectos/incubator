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

class CharSingleConditionLink extends AbstractConditionLink<CharSingleConditionLink> {

  private final char value;

  CharSingleConditionLink(char value) {
    this.value = value;
  }

  @Override
  public final Link acceptLinkMerger(LinkMerger merger) {
    return merger.mergeCharSingleConditionLink(this);
  }
  
  @Override
  public final Link mergeCharExpressionOneOrMoreLink(CharExpressionOneOrMoreLink that) {
    System.out.println("this=" + this + ";that=" + that);
    return super.mergeCharExpressionOneOrMoreLink(that);
  }

  @Override
  public final Link mergeCharSingleOneLink(CharSingleOneLink that) {
    if (value == that.value) {
      throw new UnsupportedOperationException("Implement me");
    } else {
      return new CharSingleConditionLink(value)
          .onMatch(matchLink)
          .onFail(failLink != null ? failLink.merge(that) : that);
    }
  }

  @Override
  public final Link mergeStringOneLink(StringOneLink that) {
    if (value == that.firstChar()) {
      throw new UnsupportedOperationException("Implement me");
    } else {
      return new CharSingleConditionLink(value)
          .onMatch(matchLink)
          .onFail(failLink != null ? failLink.merge(that) : that);
    }
  }

  @Override
  public final State toState() {
    return new CharSingleConditionState(
        value,
        failState(),
        matchState());
  }

  @Override
  final CharSingleConditionLink self() {
    return this;
  }

  @Override
  final StateWriter toStringCondition(StateWriter w) {
    return w.begin().writeQuote(value);
  }

}