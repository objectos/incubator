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

abstract class LexerDriverDef {

  final LexerDriverDef givenLexer(LexerGrammar<IsToken, IsBrick> grammar) {
    givenLexerImpl(grammar);
    return this;
  }

  final LexerDriverDef whenInput(String input) {
    whenInputImpl(input);
    return this;
  }

  final LexerDriverDef thenHasNext(boolean expected) {
    thenHasNextImpl(expected);
    return this;
  }

  final LexerDriverDef thenNext(IsToken expected) {
    thenNextImpl(expected);
    return this;
  }

  LexerDriverDef it() {
    return this;
  }

  abstract void givenLexerImpl(LexerGrammar<IsToken, IsBrick> grammar);

  abstract void whenInputImpl(String input);

  abstract void thenHasNextImpl(boolean expected);

  abstract void thenNextImpl(IsToken expected);

}