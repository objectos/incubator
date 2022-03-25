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
package br.com.objectos.lexer.impl.ah;

class BrickStartState implements State {

  private final State state;

  BrickStartState(State state) {
    this.state = state;
  }

  @Override
  public final boolean hasNext() {
    return true;
  }

  @Override
  public final State next(StateSubject subject) {
    ValueList valueList = subject.valueList();
    valueList.push();
    return state;
  }

  @Override
  public final String toString() {
    return State.toString(this);
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return state.toString(w.begin().write("("));
  }

}