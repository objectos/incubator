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

import br.com.objectos.lexer.grammar.LexerGrammar;
import br.com.objectos.lexer.impl.ah.model.IsBrick;
import br.com.objectos.lexer.impl.ah.model.IsToken;

abstract class StateDriverDef {

  final StateDriverDef givenGrammar(LexerGrammar<IsToken, IsBrick> grammar) {
    givenGrammarImpl(grammar);
    return this;
  }

  final StateDriverDef givenInput(String string) {
    givenInputImpl(string);
    return this;
  }

  final StateDriverDef whenNext() {
    whenNextImpl();
    return this;
  }

  final StateDriverDef thenEndState() {
    thenEndStateImpl();
    return this;
  }

  final StateDriverDef thenHasNext(boolean expected) {
    thenHasNextImpl(expected);
    return this;
  }

  final StateDriverDef thenIgnore(boolean expected) {
    thenIgnoreImpl(expected);
    return this;
  }

  final StateDriverDef thenMarkerState(MarkerState expected) {
    thenMarkerStateImpl(expected);
    return this;
  }

  final StateDriverDef thenState(Class<?> expectedType) {
    thenStateImpl(expectedType);
    return this;
  }

  final StateDriverDef thenTrailingState(Class<?> actionType) {
    thenTrailingStateImpl(actionType);
    return this;
  }

  final StateDriverDef thenLexemeSubject(String expected) {
    thenLexemeSubjectImpl(expected);
    return this;
  }

  final StateDriverDef thenValueList(Object... expected) {
    thenValueListImpl(expected);
    return this;
  }

  StateDriverDef it() {
    return this;
  }

  abstract void givenGrammarImpl(LexerGrammar<IsToken, IsBrick> grammar);

  abstract void givenInputImpl(String string);

  abstract void whenNextImpl();

  abstract void thenCharExpressionStateImpl();

  abstract void thenEndStateImpl();

  abstract void thenHasNextImpl(boolean expected);

  abstract void thenIgnoreImpl(boolean expected);

  abstract void thenMarkerStateImpl(MarkerState expected);

  abstract void thenReturnEnumStateImpl();

  abstract void thenStateImpl(Class<?> expectedType);

  abstract void thenTrailingStateImpl(Class<?> actionType);

  abstract void thenLexemeSubjectImpl(String expected);

  abstract void thenValueListImpl(Object[] expected);

}