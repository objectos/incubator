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
package br.com.objectos.parser.grammar;

import java.util.List;

public interface ParserGrammarDsl01Add<E, R, T, A1>
    extends
    ParserGrammarDslQuantifier {

  <A2 extends R> ParserGrammarDsl02Add<E, R, T, A1, A2> addRule(Class<A2> ruleType);

  <A2 extends T> ParserGrammarDsl02Add<E, R, T, A1, A2> addToken(Class<A2> tokenType);

  <A2 extends T> ParserGrammarDsl02Add<E, R, T, A1, A2> addToken(A2 tokenValue);

  void andCreateWith(Constructor1<E, A1> constructor);

  @Override
  ParserGrammarDsl01Quantifier<E, R, T, List<A1>> oneOrMore();

  @Override
  ParserGrammarDsl01Quantifier<E, R, T, A1> optional();

  @Override
  ParserGrammarDsl01Quantifier<E, R, T, List<A1>> zeroOrMore();

}