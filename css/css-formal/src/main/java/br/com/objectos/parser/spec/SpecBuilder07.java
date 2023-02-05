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
package br.com.objectos.parser.spec;

import br.com.objectos.parser.grammar.Constructor7;
import br.com.objectos.parser.grammar.ParserGrammarDsl07Add;
import br.com.objectos.parser.grammar.ParserGrammarDsl07Quantifier;
import java.util.List;

class SpecBuilder07<E, R, T, A1, A2, A3, A4, A5, A6, A7>
    extends AbstractSpecBuilderStep<R, T>
    implements
    ParserGrammarDsl07Add<E, R, T, A1, A2, A3, A4, A5, A6, A7>,
    ParserGrammarDsl07Quantifier<E, R, T, A1, A2, A3, A4, A5, A6, A7> {

  SpecBuilder07(SpecBuilder<R, T> outer) {
    super(outer);
  }

  @Override
  public final void andCreateWith(Constructor7<E, A1, A2, A3, A4, A5, A6, A7> constructor) {
    outer.andCreateWith(constructor);
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl07Quantifier<E, R, T, A1, A2, A3, A4, A5, A6, List<A7>> oneOrMore() {
    outer.oneOrMore();
    return (ParserGrammarDsl07Quantifier<E, R, T, A1, A2, A3, A4, A5, A6, List<A7>>) this;
  }

  @Override
  public final ParserGrammarDsl07Quantifier<E, R, T, A1, A2, A3, A4, A5, A6, A7> optional() {
    outer.optional();
    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public ParserGrammarDsl07Quantifier<E, R, T, A1, A2, A3, A4, A5, A6, List<A7>> zeroOrMore() {
    outer.zeroOrMore();
    return (ParserGrammarDsl07Quantifier<E, R, T, A1, A2, A3, A4, A5, A6, List<A7>>) this;
  }

}
