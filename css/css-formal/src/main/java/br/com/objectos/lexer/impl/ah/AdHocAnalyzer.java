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

import br.com.objectos.lexer.Analyzer;
import br.com.objectos.lexer.UndefinedTokenException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

class AdHocAnalyzer<T> implements Analyzer<T>, StateSubject {

  private final Deque<Spec> specStack = new ArrayDeque<>();

  private final Source source;
  private final ValueList valueList;
  private final LexemeSubject lexemeSubject;

  private boolean computed;
  private boolean ignore;
  private T next;

  AdHocAnalyzer(Spec spec, Source source) {
    specStack.push(spec);

    this.source = source;
    valueList = new ValueList();
    lexemeSubject = new LexemeSubject(source, valueList);
  }

  @Override
  public final void clear() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void close() throws IOException {
    source.close();
  }

  @Override
  public final boolean hasNext() throws UndefinedTokenException {
    if (!computed) {
      computeNext();
      computed = true;
    }
    return next != null;
  }

  @Override
  public final void ignore() {
    ignore = true;
  }

  @Override
  public final T next() throws UndefinedTokenException {
    if (next != null || hasNext()) {
      T result = next;
      computed = false;
      next = null;
      return result;
    } else {
      throw new NoSuchElementException();
    }
  }

  @Override
  public final LexemeSubject lexemeSubject() {
    return lexemeSubject;
  }

  @Override
  public final void popLexer(int count) {
    for (int i = 0; i < count; i++) {
      specStack.pop();
    }
  }

  @Override
  public final void pushLexer(Spec spec) {
    specStack.push(spec);
  }

  @Override
  public final ValueList valueList() {
    return valueList;
  }

  @Override
  public final String toString() {
    return source.toString();
  }

  private void computeNext() {
    if (source.hasNext()) {
      computeNext0();
    }
  }

  @SuppressWarnings("unchecked")
  private void computeNext0() {
    do {
      State state = spec().startingState();
      while (state.hasNext()) {
        state = state.next(this);
      }
    } while (tripIgnore());

    next = (T) valueList.get();
  }

  private Spec spec() {
    return specStack.peek();
  }

  private boolean tripIgnore() {
    if (ignore) {
      ignore = false;
      return true;
    } else {
      return false;
    }
  }

}