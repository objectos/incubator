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

import br.com.objectos.lexer.grammar.LexerGrammar;
import br.com.objectos.lexer.grammar.LexerGrammarDslAsEnum;
import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Function;

class SpecCompilerAsEnum<E, T, B> implements LexerGrammarDslAsEnum<E, T, B> {

  private final SpecBuilder spec;
  private final EnumSet<?> set;

  SpecCompilerAsEnum(SpecBuilder spec, EnumSet<?> set) {
    this.spec = spec;
    this.set = set;
  }

  @SuppressWarnings("unchecked")
  @Override
  public final void andCreateWith(Function<E, String> mapper) {
    Objects.requireNonNull(mapper);

    for (Object enumConstant : set) {
      String enumName = mapper.apply((E) enumConstant);
      spec.addString(enumName);
      spec.andReturnSelf(enumConstant, 1);
    }
  }

  @Override
  public final void andPushLexer(Function<E, String> mapper, LexerGrammar<T, B> lexer) {
    throw new UnsupportedOperationException("Implement me");
  }

}