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
package br.com.objectos.lexer.impl.ah;

class PopLexerAction implements SpecAction {

  private final SpecAction delegate;
  private final int count;

  PopLexerAction(SpecAction delegate, int count) {
    this.delegate = delegate;
    this.count = count;
  }

  @Override
  public final void execute(StateSubject subject) {
    delegate.execute(subject);
    subject.popLexer(count);
  }

  @Override
  public final int rank() {
    return delegate.rank();
  }

  @Override
  public final StateWriter toString(StateWriter w) {
    return delegate.toString(w).begin().write("pop(").write(count).write(')');
  }

}