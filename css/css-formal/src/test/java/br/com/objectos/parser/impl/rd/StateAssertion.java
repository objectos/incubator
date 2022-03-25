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
import static org.testng.Assert.assertTrue;

class StateAssertion extends AbstractAssertion<StateAssertion, State> {

  private StateAssertion(State subject) {
    super(subject);
  }

  public static StateAssertion assertThat(State subject) {
    return new StateAssertion(subject);
  }

  public final StateAssertion hasNext(boolean expected) {
    assertEquals(subject().hasNext(), expected);
    return this;
  }

  public final EofStateAssertion isEofState() {
    return isEofState(null);
  }

  public final EofStateAssertion isEofState(StateDriverDef outer) {
    assertInstanceOf(subject(), EofState.class);
    EofState cast = (EofState) subject();
    return new EofStateAssertion(cast, outer);
  }

  public final MatchedStateAssertion isMatchedState() {
    return isMatchedState(null);
  }

  public final MatchedStateAssertion isMatchedState(StateDriverDef outer) {
    assertInstanceOf(subject(), MatchedState.class);
    MatchedState cast = (MatchedState) subject();
    return new MatchedStateAssertion(cast, outer);
  }

  public final NonTerminalStateAssertion isNonTerminalState() {
    return isNonTerminalState(null);
  }

  public NonTerminalStateAssertion isNonTerminalState(StateDriverDef outer) {
    assertInstanceOf(subject(), NonTerminalState.class);
    NonTerminalState cast = (NonTerminalState) subject();
    return new NonTerminalStateAssertion(cast, outer);
  }

  public final ProductionStateAssertion isProductionState() {
    return isProductionState(null);
  }

  public final ProductionStateAssertion isProductionState(StateDriverDef outer) {
    assertInstanceOf(subject(), ProductionState.class);
    ProductionState cast = (ProductionState) subject();
    return new ProductionStateAssertion(cast, outer);
  }

  public final TerminalStateAssertion isTerminalState() {
    return isTerminalState(null);
  }

  public final TerminalStateAssertion isTerminalState(StateDriverDef outer) {
    assertInstanceOf(subject(), TerminalState.class);
    TerminalState cast = (TerminalState) subject();
    return new TerminalStateAssertion(cast, outer);
  }

  @Override
  protected final StateAssertion self() {
    return this;
  }

  private void assertInstanceOf(Object subject, Class<?> expected) {
    assertTrue(expected.isInstance(subject), "actual class=" + subject.getClass());
  }

}