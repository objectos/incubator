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
import br.com.objectos.lexer.grammar.LexerGrammarDsl01AddBrick;
import br.com.objectos.lexer.grammar.LexerGrammarDsl01AddString;
import br.com.objectos.lexer.grammar.LexerGrammarDslAsEnum;
import br.com.objectos.lexer.grammar.LexerGrammarDslDefineRuleType;
import java.util.EnumSet;

class SpecCompilerDefineRuleType<E, T, B> implements LexerGrammarDslDefineRuleType<E, T, B> {

  private final SpecBuilder spec;

  SpecCompilerDefineRuleType(SpecBuilder spec) {
    this.spec = spec;
  }

  @Override
  public final LexerGrammarDslAsEnum<E, T, B> asEnum() {
    EnumSet<?> set = spec.toEnumSet();
    return new SpecCompilerAsEnum<>(spec, set);
  }

  @Override
  public LexerGrammarDslAsEnum<E, T, B> asEnumRange(E from, E to) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public <A1 extends B> LexerGrammarDsl01AddBrick<E, T, B, A1> addBrick(A1 brick) {
    spec.addBrickValue(brick);
    return new SpecCompiler01AddBrick<>(spec);
  }

  @Override
  public <A1 extends B> LexerGrammarDsl01AddBrick<E, T, B, A1> addBrick(Class<A1> brickType) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public LexerGrammarDsl01AddBrick<E, T, B, B> alternatives(@SuppressWarnings("unchecked") B... bricks) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  @SafeVarargs
  public final LexerGrammarDsl01AddBrick<E, T, B, B> alternatives(Class<? extends B>... bricks) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final LexerGrammarDsl01AddString<E, T, B> addChar(char c) {
    spec.addChar(c);
    return new SpecCompiler01AddString<>(spec);
  }

  @Override
  public final LexerGrammarDsl01AddString<E, T, B> addChar(CharExpression expression) {
    spec.addChar(expression);
    return new SpecCompiler01AddString<>(spec);
  }

  @Override
  public final LexerGrammarDsl01AddString<E, T, B> addString(String string) {
    spec.addString(string);
    return new SpecCompiler01AddString<>(spec);
  }

}