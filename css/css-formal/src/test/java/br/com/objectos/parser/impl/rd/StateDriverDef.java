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

import br.com.objectos.parser.grammar.ParserGrammar;

abstract class StateDriverDef extends AbstractProcessor {

  final StateDriverDef givenGrammar(ParserGrammar<?, ?> grammar) {
    givenGrammarImpl(grammar);
    return this;
  }

  final StateDriverDef givenSource(Object... source) {
    givenSourceImpl(source);
    return this;
  }

  final StateDriverDef givenGoalState(Class<?> type) {
    givenGoalStateImpl(type);
    return this;
  }

  final StateDriverDef whenNext() {
    whenNextImpl();
    return this;
  }

  final EofStateAssertion thenEofState() {
    return thenEofStateImpl();
  }

  final MatchedStateAssertion thenMatchedState() {
    return thenMatchedStateImpl();
  }

  final NonTerminalStateAssertion thenNonTerminalState() {
    return thenNonTerminalStateImpl();
  }

  final ProductionStateAssertion thenProductionState() {
    return thenProductionStateImpl();
  }

  final TerminalStateAssertion thenTerminalState() {
    return thenTerminalStateImpl();
  }

  final StateDriverDef thenTokenList(Object... expected) {
    thenTokenListImpl(expected);
    return this;
  }

  final StateDriverDef thenTrackbackList(Object... expected) {
    thenTrackbackListImpl(expected);
    return this;
  }

  StateDriverDef it() {
    return this;
  }

  abstract void givenGrammarImpl(ParserGrammar<?, ?> grammar);

  abstract void givenSourceImpl(Object... source);

  abstract void givenGoalStateImpl(Class<?> type);

  abstract void whenNextImpl();

  abstract EofStateAssertion thenEofStateImpl();

  abstract MatchedStateAssertion thenMatchedStateImpl();

  abstract NonTerminalStateAssertion thenNonTerminalStateImpl();

  abstract ProductionStateAssertion thenProductionStateImpl();

  abstract TerminalStateAssertion thenTerminalStateImpl();

  abstract void thenTokenListImpl(Object[] expected);

  abstract void thenTrackbackListImpl(Object[] expected);

}