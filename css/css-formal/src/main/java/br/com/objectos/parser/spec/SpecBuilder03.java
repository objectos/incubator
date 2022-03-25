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

import br.com.objectos.parser.grammar.Constructor3;
import br.com.objectos.parser.grammar.ParserGrammarDsl03Add;
import br.com.objectos.parser.grammar.ParserGrammarDsl03Quantifier;
import br.com.objectos.parser.grammar.ParserGrammarDsl04Add;
import java.util.List;

class SpecBuilder03<E, R, T, A1, A2, A3>
    extends AbstractSpecBuilderStep<R, T>
    implements
    ParserGrammarDsl03Add<E, R, T, A1, A2, A3>,
    ParserGrammarDsl03Quantifier<E, R, T, A1, A2, A3> {

  SpecBuilder03(SpecBuilder<R, T> outer) {
    super(outer);
  }

  @Override
  public final <A4 extends R> ParserGrammarDsl04Add<E, R, T, A1, A2, A3, A4> addRule(Class<A4> ruleType) {
    outer.addRuleType(ruleType);
    return new SpecBuilder04<>(outer);
  }

  @Override
  public final <A4 extends T> ParserGrammarDsl04Add<E, R, T, A1, A2, A3, A4> addToken(A4 tokenValue) {
    outer.addTokenValue(tokenValue);
    return new SpecBuilder04<>(outer);
  }

  @Override
  public final <A4 extends T> ParserGrammarDsl04Add<E, R, T, A1, A2, A3, A4> addToken(Class<A4> tokenType) {
    outer.addTokenType(tokenType);
    return new SpecBuilder04<>(outer);
  }

  @Override
  public final void andCreateWith(Constructor3<E, A1, A2, A3> constructor) {
    outer.andCreateWith(constructor);
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl03Quantifier<E, R, T, A1, A2, List<A3>> oneOrMore() {
    outer.oneOrMore();
    return (ParserGrammarDsl03Quantifier<E, R, T, A1, A2, List<A3>>) this;
  }

  @Override
  public final ParserGrammarDsl03Quantifier<E, R, T, A1, A2, A3> optional() {
    outer.optional();
    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl03Quantifier<E, R, T, A1, A2, List<A3>> zeroOrMore() {
    outer.zeroOrMore();
    return (ParserGrammarDsl03Quantifier<E, R, T, A1, A2, List<A3>>) this;
  }

}