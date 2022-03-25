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

class CharSingleConsumer implements Consumer {

  private final char value;

  CharSingleConsumer(char value) {
    this.value = value;
  }

  @Override
  public final Linker linkOne(Linker linker) {
    return linker.charSingleOne(value);
  }

  @Override
  public final Linker linkOneOrMore(Linker linker) {
    return linker.charSingleOneOrMore(value);
  }

  @Override
  public final Linker linkZeroOrMore(Linker linker) {
    return linker.charSingleZeroOrMore(value);
  }

  @Override
  public final Linker linkZeroOrMoreNonGreedy(Linker linker) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final Linker linkOptional(Linker linker) {
    return linker.charSingleOptional(value);
  }

  @Override
  public final StateWriter toString(StateWriter sw) {
    throw new UnsupportedOperationException("Implement me");
  }

}