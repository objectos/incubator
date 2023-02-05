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

import static org.testng.Assert.assertEquals;

import br.com.objectos.parser.spec.Terminal;
import java.util.Arrays;

class TerminalStateAssertion extends AbstractStateAssertion<TerminalStateAssertion, TerminalState> {

  TerminalStateAssertion(TerminalState subject, StateDriverDef outer) {
    super(subject, outer);
  }

  public final TerminalStateAssertion hasTerminal(Terminal terminal) {
    assertEquals(subject().terminal, terminal);
    return this;
  }

  public final TerminalStateAssertion hasTerminal(String string) {
    assertEquals(subject().terminal.toString(), string);
    return this;
  }

  public final TerminalStateAssertion hasValueList(Object... expected) {
    ProductionState parent = subject().parent;
    assertEquals(parent.valueList(), Arrays.asList(expected));
    return this;
  }

  @Override
  protected TerminalStateAssertion self() {
    return this;
  }

}