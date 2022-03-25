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

import br.com.objectos.parser.grammar.Constructor1;
import br.com.objectos.parser.grammar.ParserGrammarDsl01Add;
import br.com.objectos.parser.grammar.ParserGrammarDsl01Quantifier;
import br.com.objectos.parser.grammar.ParserGrammarDsl02Add;
import java.util.List;

class SpecBuilder01<E, R, T, A1>
    extends AbstractSpecBuilderStep<R, T>
    implements
    ParserGrammarDsl01Add<E, R, T, A1>,
    ParserGrammarDsl01Quantifier<E, R, T, A1> {

  SpecBuilder01(SpecBuilder<R, T> outer) {
    super(outer);
  }

  @Override
  public final <A2 extends R> ParserGrammarDsl02Add<E, R, T, A1, A2> addRule(Class<A2> ruleType) {
    outer.addRuleType(ruleType);
    return new SpecBuilder02<>(outer);
  }

  @Override
  public final <A2 extends T> ParserGrammarDsl02Add<E, R, T, A1, A2> addToken(Class<A2> tokenType) {
    outer.addTokenType(tokenType);
    return new SpecBuilder02<>(outer);
  }

  @Override
  public final <A2 extends T> ParserGrammarDsl02Add<E, R, T, A1, A2> addToken(A2 tokenValue) {
    outer.addTokenValue(tokenValue);
    return new SpecBuilder02<>(outer);
  }

  @Override
  public final void andCreateWith(Constructor1<E, A1> constructor) {
    outer.andCreateWith(constructor);
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl01Quantifier<E, R, T, List<A1>> oneOrMore() {
    outer.oneOrMore();
    return (ParserGrammarDsl01Quantifier<E, R, T, List<A1>>) this;
  }

  @Override
  public final ParserGrammarDsl01Quantifier<E, R, T, A1> optional() {
    outer.optional();
    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl01Quantifier<E, R, T, List<A1>> zeroOrMore() {
    outer.zeroOrMore();
    return (ParserGrammarDsl01Quantifier<E, R, T, List<A1>>) this;
  }

}