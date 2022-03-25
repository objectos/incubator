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
package br.com.objectos.parser.spec;

import br.com.objectos.parser.grammar.Constructor6;
import br.com.objectos.parser.grammar.ParserGrammarDsl06Add;
import br.com.objectos.parser.grammar.ParserGrammarDsl06Quantifier;
import br.com.objectos.parser.grammar.ParserGrammarDsl07Add;
import java.util.List;

class SpecBuilder06<E, R, T, A1, A2, A3, A4, A5, A6>
    extends AbstractSpecBuilderStep<R, T>
    implements
    ParserGrammarDsl06Add<E, R, T, A1, A2, A3, A4, A5, A6>,
    ParserGrammarDsl06Quantifier<E, R, T, A1, A2, A3, A4, A5, A6> {

  SpecBuilder06(SpecBuilder<R, T> outer) {
    super(outer);
  }

  @Override
  public final <A7 extends T> ParserGrammarDsl07Add<E, R, T, A1, A2, A3, A4, A5, A6, A7> addToken(
      A7 tokenValue) {
    outer.addTokenValue(tokenValue);
    return new SpecBuilder07<>(outer);
  }

  @Override
  public final void andCreateWith(Constructor6<E, A1, A2, A3, A4, A5, A6> constructor) {
    outer.andCreateWith(constructor);
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl06Quantifier<E, R, T, A1, A2, A3, A4, A5, List<A6>> oneOrMore() {
    outer.oneOrMore();
    return (ParserGrammarDsl06Quantifier<E, R, T, A1, A2, A3, A4, A5, List<A6>>) this;
  }

  @Override
  public final ParserGrammarDsl06Quantifier<E, R, T, A1, A2, A3, A4, A5, A6> optional() {
    outer.optional();
    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public ParserGrammarDsl06Quantifier<E, R, T, A1, A2, A3, A4, A5, List<A6>> zeroOrMore() {
    outer.zeroOrMore();
    return (ParserGrammarDsl06Quantifier<E, R, T, A1, A2, A3, A4, A5, List<A6>>) this;
  }

}
