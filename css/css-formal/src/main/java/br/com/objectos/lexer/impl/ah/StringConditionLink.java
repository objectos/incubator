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

class StringConditionLink extends AbstractConditionLink<StringConditionLink> {

  private final String condition;

  StringConditionLink(String condition) {
    this.condition = condition;
  }

  @Override
  public final Link mergeStringOneLink(StringOneLink that) {
    System.out.println("this=" + this + ";that=" + that);
    return super.mergeStringOneLink(that);
  }

  @Override
  public final State toState() {
    return new StringConditionState(
        condition,
        failState(),
        matchState());
  }

  @Override
  final StringConditionLink self() {
    return this;
  }

  @Override
  final StateWriter toStringCondition(StateWriter w) {
    return w.begin().writeQuote(condition);
  }

}