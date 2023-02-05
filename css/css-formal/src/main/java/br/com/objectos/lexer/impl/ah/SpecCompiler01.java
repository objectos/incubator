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

import br.com.objectos.lexer.charexp.CharExpression;
import br.com.objectos.lexer.grammar.LexerGrammarDsl01Add;
import br.com.objectos.lexer.grammar.LexerGrammarDsl01Qty;
import br.com.objectos.lexer.grammar.LexerGrammarDsl02AddBrick;
import br.com.objectos.lexer.grammar.LexerGrammarDsl02AddString;
import br.com.objectos.lexer.lang.Constructor1;

abstract class SpecCompiler01<QTY, E, T, B, A1>
    extends
    AbstractSpecCompilerQuantifier<QTY, T, B>
    implements
    LexerGrammarDsl01Add<QTY, E, T, B, A1>,
    LexerGrammarDsl01Qty<E, T, B, A1> {

  SpecCompiler01(SpecBuilder spec) {
    super(spec);
  }

  @Override
  public final void andCreateWith(Constructor1<E, A1> constructor) {
    spec.andCreateWith(constructor);
  }

  @Override
  public final void andPopLexer(Constructor1<E, A1> constructor) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public <A2 extends B> LexerGrammarDsl02AddBrick<E, T, B, A1, A2> addBrick(A2 brick) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public <A2 extends B> LexerGrammarDsl02AddBrick<E, T, B, A1, A2> addBrick(Class<A2> brickType) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final LexerGrammarDsl02AddString<E, T, B, A1> addChar(char c) {
    spec.addChar(c);
    return new SpecCompiler02AddString<>(spec);
  }

  @Override
  public final LexerGrammarDsl02AddString<E, T, B, A1> addChar(CharExpression expression) {
    spec.addChar(expression);
    return new SpecCompiler02AddString<>(spec);
  }

  @Override
  public LexerGrammarDsl02AddString<E, T, B, A1> addString(String string) {
    throw new UnsupportedOperationException("Implement me");
  }

}