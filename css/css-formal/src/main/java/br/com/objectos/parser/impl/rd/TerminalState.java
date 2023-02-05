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

import br.com.objectos.parser.spec.Terminal;

class TerminalState extends AbstractState implements SymbolState {

  final ProductionState parent;
  final State previous;
  final Terminal terminal;

  private Object token;

  TerminalState(StateSubject subject,
                ProductionState parent,
                State previous,
                Terminal terminal) {
    super(subject);
    this.parent = parent;
    this.previous = previous;
    this.terminal = terminal;
  }

  @Override
  public final int depth() {
    return parent.depth() + 1;
  }

  @Override
  public final Object getResult() {
    return token;
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
    if (subject.hasToken()) {
      token = subject.token();
      return next0(token);
    } else {
      return previous.trackback();
    }
  }

  @Override
  public final int offset() {
    return previous.offset() + 1;
  }

  @Override
  public final int tokenCount() {
    return previous.tokenCount() + 1;
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return parent.toString(w)
        .writeNewLine()
        .writeObject(terminal)
        .writeString(" :: ")
        .writeObject(subject);
  }

  @Override
  public final State trackback() {
    trackbackToken();
    parent.removeLast();
    return previous.trackback();
  }

  private State next0(Object token) {
    subject.addToken(token);
    if (terminal.matches(token)) {
      State next = parent.next(this);
      log("%s == %s :: %s", terminal, token, subject);
      return next;
    } else {
      return trackback();
    }
  }

  private void trackbackToken() {
    if (token != null) {
      subject.trackback();
      token = null;
    }
  }

}