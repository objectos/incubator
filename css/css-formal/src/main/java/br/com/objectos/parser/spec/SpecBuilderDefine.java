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

import br.com.objectos.parser.grammar.ParserGrammarDsl01Add;
import br.com.objectos.parser.grammar.ParserGrammarDslDefine;

class SpecBuilderDefine<E, R, T>
    extends AbstractSpecBuilderStep<R, T>
    implements ParserGrammarDslDefine<E, R, T> {

  SpecBuilderDefine(SpecBuilder<R, T> outer) {
    super(outer);
  }

  @Override
  public final <A1 extends R> ParserGrammarDsl01Add<E, R, T, A1> addRule(Class<A1> ruleType) {
    outer.addRuleType(ruleType);
    return new SpecBuilder01<>(outer);
  }

  @Override
  public final <A1 extends T> ParserGrammarDsl01Add<E, R, T, A1> addToken(Class<A1> tokenType) {
    outer.addTokenType(tokenType);
    return new SpecBuilder01<>(outer);
  }

  @Override
  public final <A1 extends T> ParserGrammarDsl01Add<E, R, T, A1> addToken(A1 token) {
    outer.addTokenValue(token);
    return new SpecBuilder01<>(outer);
  }

}