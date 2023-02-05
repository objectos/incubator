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
package br.com.objectos.parser.spec;

public enum Eof implements Symbol {

  INSTANCE;

  @Override
  public final <R, P> R acceptSymbolVisitor(SymbolVisitor<R, P> visitor, P p) {
    return visitor.visitEof(this, p);
  }

  @Override
  public final void acceptStage01Builder(Stage01Builder builder) {
    // noop
  }

  @Override
  public final void acceptStage02Builder(Stage02Builder builder, NonTerminal superType) {
    // noop
  }

  @Override
  public final String toString() {
    return "<eof>";
  }

}