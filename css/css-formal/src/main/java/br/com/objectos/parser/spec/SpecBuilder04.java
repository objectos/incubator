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

import br.com.objectos.parser.grammar.Constructor4;
import br.com.objectos.parser.grammar.ParserGrammarDsl04Add;
import br.com.objectos.parser.grammar.ParserGrammarDsl04Quantifier;
import br.com.objectos.parser.grammar.ParserGrammarDsl05Add;
import java.util.List;

class SpecBuilder04<E, R, T, A1, A2, A3, A4>
    extends AbstractSpecBuilderStep<R, T>
    implements
    ParserGrammarDsl04Add<E, R, T, A1, A2, A3, A4>,
    ParserGrammarDsl04Quantifier<E, R, T, A1, A2, A3, A4> {

  SpecBuilder04(SpecBuilder<R, T> outer) {
    super(outer);
  }

  @Override
  public final <A5 extends R> ParserGrammarDsl05Add<E, R, T, A1, A2, A3, A4, A5> addRule(Class<A5> ruleType) {
    outer.addRuleType(ruleType);
    return new SpecBuilder05<>(outer);
  }

  @Override
  public final <A5 extends T> ParserGrammarDsl05Add<E, R, T, A1, A2, A3, A4, A5> addToken(A5 tokenValue) {
    outer.addTokenValue(tokenValue);
    return new SpecBuilder05<>(outer);
  }

  @Override
  public final void andCreateWith(Constructor4<E, A1, A2, A3, A4> constructor) {
    outer.andCreateWith(constructor);
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl04Quantifier<E, R, T, A1, A2, A3, List<A4>> oneOrMore() {
    outer.oneOrMore();
    return (ParserGrammarDsl04Quantifier<E, R, T, A1, A2, A3, List<A4>>) this;
  }

  @Override
  public final ParserGrammarDsl04Quantifier<E, R, T, A1, A2, A3, A4> optional() {
    outer.optional();
    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl04Quantifier<E, R, T, A1, A2, A3, List<A4>> zeroOrMore() {
    outer.zeroOrMore();
    return (ParserGrammarDsl04Quantifier<E, R, T, A1, A2, A3, List<A4>>) this;
  }

}