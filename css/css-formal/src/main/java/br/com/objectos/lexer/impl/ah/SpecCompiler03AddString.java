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

import br.com.objectos.lexer.grammar.LexerGrammarDsl02AddString;
import br.com.objectos.lexer.grammar.LexerGrammarDsl03AddString;
import br.com.objectos.lexer.grammar.LexerGrammarDsl03QtyString;

class SpecCompiler03AddString<E, T, B, A1, A2>
    extends
    SpecCompiler03<LexerGrammarDsl03QtyString<E, T, B, A1, A2>, E, T, B, A1, A2, String>
    implements
    LexerGrammarDsl03AddString<E, T, B, A1, A2>,
    LexerGrammarDsl03QtyString<E, T, B, A1, A2> {

  SpecCompiler03AddString(SpecBuilder spec) {
    super(spec);
  }

  @Override
  public final LexerGrammarDsl02AddString<E, T, B, A1> concat() {
    spec.concat();
    return new SpecCompiler02AddString<>(spec);
  }

  @Override
  LexerGrammarDsl03QtyString<E, T, B, A1, A2> qself() {
    return this;
  }

}