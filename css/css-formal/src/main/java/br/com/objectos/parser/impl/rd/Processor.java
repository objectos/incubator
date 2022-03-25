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

import br.com.objectos.parser.spec.NonTerminal;
import br.com.objectos.parser.spec.Spec;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

class Processor extends AbstractProcessor {

  private final Spec spec;
  private final Iterator<?> iterator;

  private final Deque<Object> trackback = new ArrayDeque<>();
  private final Deque<Object> tokenList = new ArrayDeque<>();

  public Processor(Spec spec, Iterator<?> iterator) {
    this.spec = spec;
    this.iterator = iterator;
  }

  @Override
  public final void log(String message, Object... params) {
    // logOn(message, params);
  }

  public <E> E parse(Class<E> goalType) {
    NonTerminal goalSymbol = NonTerminal.get(goalType);
    State state = new GoalState(this, goalSymbol);

    while (state.hasNext()) {
      state = state.next();
    }

    Object result = state.getResult();

    if (!goalType.isInstance(result)) {
      throw new UnsupportedOperationException("Implement me");
    }

    return goalType.cast(result);
  }

  @Override
  public final String toString() {
    return "source=" + iterator + "; trackback=" + trackback + "; tokenList=" + tokenList;
  }

  @Override
  final Iterator<?> iterator() {
    return iterator;
  }

  @Override
  final Spec spec() {
    return spec;
  }

  @Override
  final Deque<Object> tokenList() {
    return tokenList;
  }

  @Override
  final Deque<Object> trackbackList() {
    return trackback;
  }

  final void logOn(String message, Object... params) {
    String log = message;
    if (params.length != 0) {
      log = String.format(message, params);
    }
    System.out.println(log);
  }

}