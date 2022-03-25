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

import br.com.objectos.parser.spec.Empty;
import br.com.objectos.parser.spec.Eof;
import br.com.objectos.parser.spec.Expression;
import br.com.objectos.parser.spec.NonTerminal;
import br.com.objectos.parser.spec.Symbol;
import br.com.objectos.parser.spec.SymbolVisitor;
import br.com.objectos.parser.spec.Terminal;

class ProductionNext implements SymbolVisitor<State, Void> {

  private final ProductionState state;
  private final State previous;

  ProductionNext(ProductionState state, State previous) {
    this.state = state;
    this.previous = previous;
  }

  public final State next() {
    Expression expression = state.expression();
    int index = previous.offset() + 1;
    if (expression.hasNext(index)) {
      Symbol symbol = expression.get(index);
      return symbol.acceptSymbolVisitor(this, null);
    } else {
      return state.nextMatchedState(previous);
    }
  }

  @Override
  public final State visitEmpty(Empty empty, Void p) {
    return state.nextEmptyState(previous, empty);
  }

  @Override
  public final State visitEof(Eof eof, Void p) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final State visitNonTerminal(NonTerminal nonTerminal, Void p) {
    return state.nextNonTerminalState(previous, nonTerminal);
  }

  @Override
  public final State visitTerminal(Terminal terminal, Void p) {
    return state.nextTerminalState(previous, terminal);
  }

}