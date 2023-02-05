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

import br.com.objectos.parser.spec.NonTerminal;
import br.com.objectos.parser.spec.Production;
import br.com.objectos.parser.spec.ProductionQuery;

class NonTerminalState extends AbstractState implements SymbolState {

  final NonTerminalParent parent;
  final State previous;
  final NonTerminal nonTerminal;
  final ProductionQuery productionQuery;

  private ProductionState currentProductionState;

  NonTerminalState(StateSubject subject,
                   NonTerminalParent parent,
                   State previous,
                   NonTerminal nonTerminal) {
    super(subject);
    this.parent = parent;
    this.previous = previous;
    this.nonTerminal = nonTerminal;
    productionQuery = subject.productionQuery(nonTerminal);
  }

  @Override
  public final int depth() {
    return parent.depth() + 1;
  }

  @Override
  public final Object getResult() {
    return currentProductionState.getResult();
  }

  @Override
  public final boolean hasNext() {
    return true;
  }

  @Override
  public final boolean hasResult() {
    return true;
  }

  @Override
  public final State next() {
    return newProductionState(0);
  }

  public final State nextProduction(int index) {
    if (productionQuery.has(index)) {
      return newProductionState(index);
    } else {
      parent.removeLast();
      return previous.trackback();
    }
  }

  @Override
  public final int offset() {
    return previous.offset() + 1;
  }

  public final State onMatchedState(MatchedState state) {
    return parent.next(state);
  }

  public final Production production(int index) {
    return productionQuery.get(index);
  }

  @Override
  public final int tokenCount() {
    return previous.tokenCount();
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return parent.toString(w)
        .writeNewLine()
        .writeObject(nonTerminal)
        .writeChar('[').writeInt(offset()).writeChar(']');
  }

  @Override
  public final State trackback() {
    throw new UnsupportedOperationException("Implement me");
  }

  private ProductionState newProductionState(int index) {
    return currentProductionState = new ProductionState(subject, this, index);
  }

}
