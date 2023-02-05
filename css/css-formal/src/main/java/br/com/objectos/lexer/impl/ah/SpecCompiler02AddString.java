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

import br.com.objectos.lexer.grammar.LexerGrammarDsl01AddString;
import br.com.objectos.lexer.grammar.LexerGrammarDsl02AddString;
import br.com.objectos.lexer.grammar.LexerGrammarDsl02QtyString;

class SpecCompiler02AddString<E, T, B, A1>
    extends
    SpecCompiler02<LexerGrammarDsl02QtyString<E, T, B, A1>, E, T, B, A1, String>
    implements
    LexerGrammarDsl02AddString<E, T, B, A1>,
    LexerGrammarDsl02QtyString<E, T, B, A1> {

  SpecCompiler02AddString(SpecBuilder spec) {
    super(spec);
  }

  @Override
  public final LexerGrammarDsl01AddString<E, T, B> concat() {
    spec.concat();
    return new SpecCompiler01AddString<>(spec);
  }

  @Override
  LexerGrammarDsl02QtyString<E, T, B, A1> qself() {
    return this;
  }

}