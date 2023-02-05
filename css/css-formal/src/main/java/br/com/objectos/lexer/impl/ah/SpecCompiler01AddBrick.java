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

import br.com.objectos.lexer.grammar.LexerGrammarDsl01AddBrick;
import br.com.objectos.lexer.grammar.LexerGrammarDsl01QtyBrick;

class SpecCompiler01AddBrick<E, T, B, A1>
    extends
    SpecCompiler01<LexerGrammarDsl01QtyBrick<E, T, B, A1>, E, T, B, A1>
    implements
    LexerGrammarDsl01AddBrick<E, T, B, A1>,
    LexerGrammarDsl01QtyBrick<E, T, B, A1> {

  SpecCompiler01AddBrick(SpecBuilder spec) {
    super(spec);
  }

  @Override
  public LexerGrammarDsl01QtyBrick<E, T, B, Iterable<A1>> asIterable() {
    throw new UnsupportedOperationException("Implement me");
    // spec.asIterable();
    // return (LexerGrammarDsl01QtyBrick<E, T, B, Iterable<A1>>) this;
  }

  @Override
  LexerGrammarDsl01QtyBrick<E, T, B, A1> qself() {
    return this;
  }

}
