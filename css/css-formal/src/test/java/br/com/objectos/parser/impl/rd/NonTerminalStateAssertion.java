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
import java.util.Arrays;

class NonTerminalStateAssertion extends AbstractStateAssertion<NonTerminalStateAssertion, NonTerminalState> {

  NonTerminalStateAssertion(NonTerminalState subject, StateDriverDef outer) {
    super(subject, outer);
  }

  public final NonTerminalStateAssertion hasNonTerminal(NonTerminal expected) {
    assertEquals(subject().nonTerminal,expected);
    return this;
  }

  public final NonTerminalStateAssertion hasNonTerminal(String string) {
    assertEquals(subject().nonTerminal.toString(), string);
    return this;
  }

  public NonTerminalStateAssertion hasValueList(Object... expected) {
    ProductionState parent = (ProductionState) subject().parent;
    assertEquals(parent.valueList(), Arrays.asList(expected));
    return this;
  }

  @Override
  protected NonTerminalStateAssertion self() {
    return this;
  }

}