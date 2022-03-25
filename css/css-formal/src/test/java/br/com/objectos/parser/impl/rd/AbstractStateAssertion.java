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

abstract class AbstractStateAssertion<SELF extends AbstractStateAssertion<SELF, S>, S extends State>
    extends AbstractAssertion<SELF, S> {

  private final StateDriverDef outer;

  AbstractStateAssertion(S subject) {
    this(subject, null);
  }

  AbstractStateAssertion(S subject, StateDriverDef outer) {
    super(subject);
    this.outer = outer;
  }

  public final SELF hasPosition(int depth, int offset) {
    assertEquals(subject().depth(), depth);
    assertEquals(subject().offset(), offset);
    return self();
  }

  public final StateDriverDef whenNext() {
    return outer.whenNext();
  }

}