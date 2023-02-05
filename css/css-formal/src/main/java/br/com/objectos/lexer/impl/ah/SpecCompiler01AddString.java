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
import br.com.objectos.lexer.grammar.LexerGrammarDsl01QtyString;

class SpecCompiler01AddString<E, T, B>
    extends
    SpecCompiler01<LexerGrammarDsl01QtyString<E, T, B>, E, T, B, String>
    implements
    LexerGrammarDsl01AddString<E, T, B>,
    LexerGrammarDsl01QtyString<E, T, B> {

  SpecCompiler01AddString(SpecBuilder spec) {
    super(spec);
  }

  @Override
  public final LexerGrammarDsl01AddString<E, T, B> optional() {
    spec.optional();
    return this;
  }

  @Override
  LexerGrammarDsl01QtyString<E, T, B> qself() {
    return this;
  }

}