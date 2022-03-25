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

import br.com.objectos.lexer.charexp.CharExpression;
import br.com.objectos.lexer.grammar.LexerGrammar;
import br.com.objectos.lexer.grammar.LexerGrammarDsl02Add;
import br.com.objectos.lexer.grammar.LexerGrammarDsl02Qty;
import br.com.objectos.lexer.grammar.LexerGrammarDsl03AddBrick;
import br.com.objectos.lexer.grammar.LexerGrammarDsl03AddString;
import br.com.objectos.lexer.lang.Constructor2;
import java.util.Objects;

abstract class SpecCompiler02<QTY, E, T, B, A1, A2>
    extends
    AbstractSpecCompilerQuantifier<QTY, T, B>
    implements
    LexerGrammarDsl02Add<QTY, E, T, B, A1, A2>,
    LexerGrammarDsl02Qty<E, T, B, A1, A2> {

  SpecCompiler02(SpecBuilder spec) {
    super(spec);
  }

  @Override
  public final void andCreateWith(Constructor2<E, A1, A2> constructor) {
    spec.andCreateWith(constructor);
  }

  @Override
  public final void andPopLexer(Constructor2<E, A1, A2> constructor) {
    andPopLexer(constructor, 1);
  }

  @Override
  public final void andPopLexer(Constructor2<E, A1, A2> constructor, int count) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void andPushLexer(Constructor2<E, A1, A2> constructor, LexerGrammar<T, B> lexer) {
    Objects.requireNonNull(lexer);
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public <A3 extends B> LexerGrammarDsl03AddBrick<E, T, B, A1, A2, A3> addBrick(A3 brick) {
    spec.addBrickValue(brick);
    return new SpecCompiler03AddBrick<>(spec);
  }

  @Override
  public final LexerGrammarDsl03AddString<E, T, B, A1, A2> addChar(char value) {
    spec.addChar(value);
    return new SpecCompiler03AddString<>(spec);
  }

  @Override
  public LexerGrammarDsl03AddString<E, T, B, A1, A2> addChar(CharExpression expression) {
    spec.addChar(expression);
    return new SpecCompiler03AddString<>(spec);
  }

  @Override
  public LexerGrammarDsl03AddString<E, T, B, A1, A2> addString(String string) {
    spec.addString(string);
    return new SpecCompiler03AddString<>(spec);
  }

}
