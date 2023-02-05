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

import br.com.objectos.parser.grammar.Constructor2;
import br.com.objectos.parser.grammar.ParserGrammarDsl02Add;
import br.com.objectos.parser.grammar.ParserGrammarDsl02Quantifier;
import br.com.objectos.parser.grammar.ParserGrammarDsl03Add;
import java.util.List;

class SpecBuilder02<E, R, T, A1, A2>
    extends AbstractSpecBuilderStep<R, T>
    implements
    ParserGrammarDsl02Add<E, R, T, A1, A2>,
    ParserGrammarDsl02Quantifier<E, R, T, A1, A2> {

  SpecBuilder02(SpecBuilder<R, T> outer) {
    super(outer);
  }

  @Override
  public final <A3 extends R> ParserGrammarDsl03Add<E, R, T, A1, A2, A3> addRule(Class<A3> ruleType) {
    outer.addRuleType(ruleType);
    return new SpecBuilder03<>(outer);
  }

  @Override
  public final <A3 extends T> ParserGrammarDsl03Add<E, R, T, A1, A2, A3> addToken(A3 tokenValue) {
    outer.addTokenValue(tokenValue);
    return new SpecBuilder03<>(outer);
  }

  @Override
  public final <A3 extends T> ParserGrammarDsl03Add<E, R, T, A1, A2, A3> addToken(Class<A3> tokenType) {
    outer.addTokenType(tokenType);
    return new SpecBuilder03<>(outer);
  }

  @Override
  public final void andCreateWith(Constructor2<E, A1, A2> constructor) {
    outer.andCreateWith(constructor);
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl02Quantifier<E, R, T, A1, List<A2>> oneOrMore() {
    outer.oneOrMore();
    return (ParserGrammarDsl02Quantifier<E, R, T, A1, List<A2>>) this;
  }

  @Override
  public final ParserGrammarDsl02Quantifier<E, R, T, A1, A2> optional() {
    outer.optional();
    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl02Quantifier<E, R, T, A1, List<A2>> zeroOrMore() {
    outer.zeroOrMore();
    return (ParserGrammarDsl02Quantifier<E, R, T, A1, List<A2>>) this;
  }

}