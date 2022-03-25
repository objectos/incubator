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

import br.com.objectos.parser.grammar.Constructor5;
import br.com.objectos.parser.grammar.ParserGrammarDsl05Add;
import br.com.objectos.parser.grammar.ParserGrammarDsl05Quantifier;
import br.com.objectos.parser.grammar.ParserGrammarDsl06Add;
import java.util.List;

class SpecBuilder05<E, R, T, A1, A2, A3, A4, A5>
    extends AbstractSpecBuilderStep<R, T>
    implements
    ParserGrammarDsl05Add<E, R, T, A1, A2, A3, A4, A5>,
    ParserGrammarDsl05Quantifier<E, R, T, A1, A2, A3, A4, A5> {

  SpecBuilder05(SpecBuilder<R, T> outer) {
    super(outer);
  }

  @Override
  public final <A6 extends R> ParserGrammarDsl06Add<E, R, T, A1, A2, A3, A4, A5, A6> addRule(
      Class<A6> ruleType) {
    outer.addRuleType(ruleType);
    return new SpecBuilder06<>(outer);
  }

  @Override
  public final <A6 extends T> ParserGrammarDsl06Add<E, R, T, A1, A2, A3, A4, A5, A6> addToken(
      A6 tokenValue) {
    outer.addTokenValue(tokenValue);
    return new SpecBuilder06<>(outer);
  }

  @Override
  public final void andCreateWith(Constructor5<E, A1, A2, A3, A4, A5> constructor) {
    outer.andCreateWith(constructor);
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl05Quantifier<E, R, T, A1, A2, A3, A4, List<A5>> oneOrMore() {
    outer.oneOrMore();
    return (ParserGrammarDsl05Quantifier<E, R, T, A1, A2, A3, A4, List<A5>>) this;
  }

  @Override
  public final ParserGrammarDsl05Quantifier<E, R, T, A1, A2, A3, A4, A5> optional() {
    outer.optional();
    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public final ParserGrammarDsl05Quantifier<E, R, T, A1, A2, A3, A4, List<A5>> zeroOrMore() {
    outer.zeroOrMore();
    return (ParserGrammarDsl05Quantifier<E, R, T, A1, A2, A3, A4, List<A5>>) this;
  }

}