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

class MatchedState extends AbstractState {

  final ProductionState parent;
  final State matched;

  MatchedState(StateSubject subject, ProductionState parent, State matched) {
    super(subject);
    this.parent = parent;
    this.matched = matched;
  }

  @Override
  public final int depth() {
    return parent.depth();
  }

  @Override
  public final boolean hasNext() {
    return true;
  }

  @Override
  public final State next() {
    return parent.onMatchedState(this);
  }

  @Override
  public final int offset() {
    return parent.offset();
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return parent.toString(w)
        .writeNewLine()
        .writeString("%MatchedState%");
  }

  @Override
  public final State trackback() {
    return matched.trackback();
  }

}