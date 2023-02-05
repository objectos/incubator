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

class CharSingleZeroOrMoreState extends AbstractCharSingleState {

  CharSingleZeroOrMoreState(char value, State state) {
    super(value, state);
  }

  @Override
  public final State next(StateSubject subject) {
    LexemeSubject lexeme = subject.lexemeSubject();
    String peek = lexeme.peekString(value);
    lexeme.consumeString(peek);
    return state;
  }

  @Override
  final Quantifier quantifier() {
    return Quantifier.ZERO_OR_MORE;
  }

}