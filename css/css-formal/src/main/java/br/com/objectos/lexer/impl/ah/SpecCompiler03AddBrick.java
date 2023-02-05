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

import br.com.objectos.lexer.grammar.LexerGrammarDsl03AddBrick;
import br.com.objectos.lexer.grammar.LexerGrammarDsl03QtyBrick;

class SpecCompiler03AddBrick<E, T, B, A1, A2, A3>
    extends
    SpecCompiler03<LexerGrammarDsl03QtyBrick<E, T, B, A1, A2, A3>, E, T, B, A1, A2, A3>
    implements
    LexerGrammarDsl03AddBrick<E, T, B, A1, A2, A3>,
    LexerGrammarDsl03QtyBrick<E, T, B, A1, A2, A3> {

  SpecCompiler03AddBrick(SpecBuilder spec) {
    super(spec);
  }

  @Override
  public LexerGrammarDsl03QtyBrick<E, T, B, A1, A3, Iterable<A3>> asIterable() {
    throw new UnsupportedOperationException("Implement me");
    // spec.asIterable();
    // return (LexerGrammarDsl03QtyBrick<E, T, B, A1, A3, Iterable<A3>>) this;
  }

  @Override
  public LexerGrammarDsl03AddBrick<E, T, B, A1, A2, A3> optional() {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  LexerGrammarDsl03QtyBrick<E, T, B, A1, A2, A3> qself() {
    return this;
  }

}
