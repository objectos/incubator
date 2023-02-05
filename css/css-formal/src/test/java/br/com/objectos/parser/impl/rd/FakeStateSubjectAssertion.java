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
package br.com.objectos.parser.impl.rd;

class FakeStateSubjectAssertion
    extends AbstractAssertion<FakeStateSubjectAssertion, FakeStateSubject> {

  private FakeStateSubjectAssertion(FakeStateSubject subject) {
    super(subject);
  }

  public static FakeStateSubjectAssertion assertThat(FakeStateSubject subject) {
    return new FakeStateSubjectAssertion(subject);
  }

  public final FakeStateSubjectAssertion hasIndex(int expected) {
    subject().hasIndex(expected);
    return this;
  }

  public FakeStateSubjectAssertion hasSource(Object... expected) {
    subject().hasSource(expected);
    return this;
  }

  public final FakeStateSubjectAssertion hasTokenList(Object... expected) {
    subject().hasTokenList(expected);
    return this;
  }

  public final FakeStateSubjectAssertion hasValueList(Object... expected) {
    subject().hasValueList(expected);
    return this;
  }

  public final FakeStateSubjectAssertion isFailed(boolean expected) {
    subject().isFailed(expected);
    return this;
  }

  public final FakeStateSubjectAssertion isMatched(boolean expected) {
    subject().isMatched(expected);
    return this;
  }

  @Override
  protected FakeStateSubjectAssertion self() {
    return this;
  }

}