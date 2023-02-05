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

import br.com.objectos.lexer.grammar.LexerGrammarDsl03Add;
import br.com.objectos.lexer.grammar.LexerGrammarDsl03Qty;
import br.com.objectos.lexer.lang.Constructor3;

abstract class SpecCompiler03<QTY, E, T, B, A1, A2, A3>
    extends
    AbstractSpecCompilerQuantifier<QTY, T, B>
    implements
    LexerGrammarDsl03Add<QTY, E, T, B, A1, A2, A3>,
    LexerGrammarDsl03Qty<E, T, B, A1, A2, A3> {

  SpecCompiler03(SpecBuilder spec) {
    super(spec);
  }

  @Override
  public final void andCreateWith(Constructor3<E, A1, A2, A3> constructor) {
    spec.andCreateWith(constructor);
  }

}