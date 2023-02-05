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

class EofState extends AbstractState {

  final GoalState parent;
  final State previous;
  final Object result;

  EofState(StateSubject subject, GoalState parent, State previous, Object result) {
    super(subject);
    this.parent = parent;
    this.previous = previous;
    this.result = result;
  }

  @Override
  public final int depth() {
    return 0;
  }

  @Override
  public final boolean hasNext() {
    return true;
  }

  @Override
  public final State next() {
    if (!subject.hasToken()) {
      return new DoneState(subject, result);
    } else {
      return previous.trackback();
    }
  }

  @Override
  public final int offset() {
    return 0;
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return parent.toString(w)
        .writeNewLine()
        .writeString("%EofState%");
  }

}