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

import br.com.objectos.parser.grammar.ParserGrammar;
import br.com.objectos.parser.spec.NonTerminal;
import br.com.objectos.parser.spec.Spec;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

class StateDriver extends StateDriverDef implements StateSubject {

  private Spec spec;
  private Iterator<Object> iterator;

  private Deque<Object> trackback;
  private Deque<Object> tokenList;

  private State state;

  @Override
  void givenGrammarImpl(ParserGrammar<?, ?> grammar) {
    spec = RecursiveDescentParser.toSpec(grammar);
  }

  @Override
  void givenSourceImpl(Object... source) {
    iterator = Arrays.asList(source).iterator();
  }

  @Override
  void givenGoalStateImpl(Class<?> type) {
    NonTerminal nonTerminal = NonTerminal.get(type);
    state = new GoalState(this, nonTerminal);
  }

  @Override
  void whenNextImpl() {
    assertTrue(state.hasNext());
    state = state.next();
  }

  @Override
  EofStateAssertion thenEofStateImpl() {
    return StateAssertion.assertThat(state).isEofState(this);
  }

  @Override
  MatchedStateAssertion thenMatchedStateImpl() {
    return StateAssertion.assertThat(state).isMatchedState(this);
  }

  @Override
  NonTerminalStateAssertion thenNonTerminalStateImpl() {
    return StateAssertion.assertThat(state).isNonTerminalState(this);
  }

  @Override
  ProductionStateAssertion thenProductionStateImpl() {
    return StateAssertion.assertThat(state).isProductionState(this);
  }

  @Override
  TerminalStateAssertion thenTerminalStateImpl() {
    return StateAssertion.assertThat(state).isTerminalState(this);
  }

  @Override
  void thenTokenListImpl(Object[] expected) {
    assertEquals(new ArrayList<>(tokenList), Arrays.asList(expected));
  }

  @Override
  void thenTrackbackListImpl(Object[] expected) {
    assertEquals(new ArrayList<>(trackback), Arrays.asList(expected));
  }

  @Override
  StateDriverDef it() {
    trackback = new ArrayDeque<>();
    tokenList = new ArrayDeque<>();
    return super.it();
  }

  @Override
  public void log(String message, Object... params) {
  }

  @Override
  final Iterator<?> iterator() {
    return iterator;
  }

  @Override
  final Spec spec() {
    return spec;
  }

  @Override
  final Deque<Object> tokenList() {
    return tokenList;
  }

  @Override
  final Deque<Object> trackbackList() {
    return trackback;
  }

}