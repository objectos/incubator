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
import br.com.objectos.parser.spec.ProductionQuery;
import br.com.objectos.parser.spec.Spec;
import java.util.Deque;
import java.util.Iterator;

abstract class AbstractProcessor implements StateSubject {

  @Override
  public final ProductionQuery productionQuery(NonTerminal nonTerminal) {
    return spec().productionQuery(nonTerminal);
  }

  @Override
  public final void addToken(Object token) {
    tokenList().add(token);
  }

  @Override
  public final boolean hasToken() {
    return !trackbackList().isEmpty() || iterator().hasNext();
  }

  @Override
  public final Object token() {
    return !trackbackList().isEmpty() ? trackbackList().pop() : iterator().next();
  }

  @Override
  public final void trackback() {
    Object token = tokenList().removeLast();
    trackbackList().push(token);
  }

  abstract Iterator<?> iterator();

  abstract Spec spec();

  abstract Deque<Object> tokenList();

  abstract Deque<Object> trackbackList();

}