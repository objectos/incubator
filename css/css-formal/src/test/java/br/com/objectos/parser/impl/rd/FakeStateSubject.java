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
package br.com.objectos.parser.impl.rd;

import static org.testng.Assert.assertEquals;

import br.com.objectos.parser.spec.NonTerminal;
import br.com.objectos.parser.spec.ProductionQuery;
import java.util.Arrays;
import objectos.util.ImmutableList;

class FakeStateSubject implements StateSubject {

  private final FakeStateSubjectBuilder builder;
  private boolean failed;
  private int index;
  private boolean matched;

  public FakeStateSubject(FakeStateSubjectBuilder builder) {
    this.builder = builder;
  }

  @Override
  public void addToken(Object token) {
    builder.tokenList.add(token);
  }

  @Override
  public boolean hasToken() {
    return builder.source.hasNext();
  }

  @Override
  public void log(String message, Object... params) {}

  @Override
  public ProductionQuery productionQuery(NonTerminal nonTerminal) {
    return builder.productionQuery(nonTerminal);
  }

  @Override
  public Object token() {
    return builder.source.next();
  }

  @Override
  public void trackback() {}

  final void hasIndex(int expected) {
    assertEquals(index, expected);
  }

  final void hasSource(Object[] expected) {
    assertEquals(ImmutableList.copyOf(builder.source), ImmutableList.copyOf(expected));
  }

  final void hasTokenList(Object[] expected) {
    assertEquals(builder.tokenList, Arrays.asList(expected));
  }

  final void hasValueList(Object[] expected) {
    assertEquals(ImmutableList.copyOf(builder.valueList), ImmutableList.copyOf(expected));
  }

  final void isFailed(boolean expected) {
    assertEquals(failed, expected);
  }

  final void isMatched(boolean expected) {
    assertEquals(matched, expected);
  }

}