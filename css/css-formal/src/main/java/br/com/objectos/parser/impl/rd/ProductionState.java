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

import br.com.objectos.parser.spec.Empty;
import br.com.objectos.parser.spec.Expression;
import br.com.objectos.parser.spec.NonTerminal;
import br.com.objectos.parser.spec.Production;
import br.com.objectos.parser.spec.Terminal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

class ProductionState extends AbstractState implements NonTerminalParent {

  final NonTerminalState nonTerminalState;
  final int index;

  final Deque<SymbolState> symbolList = new ArrayDeque<>();

  private Object result;

  ProductionState(StateSubject subject, NonTerminalState nonTerminalState, int index) {
    super(subject);
    this.nonTerminalState = nonTerminalState;
    this.index = index;
  }

  @Override
  public final int depth() {
    return nonTerminalState.depth();
  }

  @Override
  public final Object getResult() {
    return result;
  }

  @Override
  public final boolean hasNext() {
    return true;
  }

  @Override
  public final State next() {
    return next(new ProductionHead(this));
  }

  @Override
  public final State next(State previous) {
    return new ProductionNext(this, previous).next();
  }

  @Override
  public final State nextProduction() {
    return nonTerminalState.nextProduction(index + 1);
  }

  @Override
  public final int offset() {
    return nonTerminalState.offset();
  }

  @Override
  public final State onMatchedState(MatchedState state) {
    return nonTerminalState.onMatchedState(state);
  }

  @Override
  public final void removeLast() {
    symbolList.removeLast();
  }

  @Override
  public final int tokenCount() {
    throw new UnsupportedOperationException();
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return nonTerminalState.toString(w)
        .writeNewLine()
        .writeChar('P')
        .writeInt(index)
        .writeChar(':')
        .writeChar('[')
        .writeObject(production())
        .writeChar(']');
  }

  @Override
  public final State trackback() {
    return nextProduction();
  }

  public final List<Object> valueList() {
    return symbolList.stream()
        .map(SymbolState::getResult)
        .collect(Collectors.toList());
  }

  final Expression expression() {
    return production().expression;
  }

  final State nextEmptyState(State previous, Empty empty) {
    EmptyState state = new EmptyState(subject, this, previous);
    return addToSymbolListAndReturn(state);
  }

  final State nextMatchedState(State matched) {
    Production production = production();
    Iterator<Object> iterator = symbolList
        .stream()
        .filter(SymbolState::hasResult)
        .map(SymbolState::getResult)
        .iterator();
    result = production.get(iterator);
    return new MatchedState(subject, this, matched);
  }

  final State nextNonTerminalState(State previous, NonTerminal nonTerminal) {
    NonTerminalState state = new NonTerminalState(subject, this, previous, nonTerminal);
    return addToSymbolListAndReturn(state);
  }

  final State nextTerminalState(State previous, Terminal terminal) {
    TerminalState state = new TerminalState(subject, this, previous, terminal);
    return addToSymbolListAndReturn(state);
  }

  final Production production() {
    return nonTerminalState.production(index);
  }

  private State addToSymbolListAndReturn(SymbolState state) {
    symbolList.add(state);
    return state;
  }

}
