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
package br.com.objectos.parser.grammar;

import java.util.List;

public interface ParserGrammarDsl03Add<E, R, T, A1, A2, A3>
    extends
    ParserGrammarDslQuantifier {

  <A4 extends R> ParserGrammarDsl04Add<E, R, T, A1, A2, A3, A4> addRule(Class<A4> ruleType);

  <A4 extends T> ParserGrammarDsl04Add<E, R, T, A1, A2, A3, A4> addToken(A4 tokenValue);

  <A4 extends T> ParserGrammarDsl04Add<E, R, T, A1, A2, A3, A4> addToken(Class<A4> tokenType);

  void andCreateWith(Constructor3<E, A1, A2, A3> constructor);

  @Override
  ParserGrammarDsl03Quantifier<E, R, T, A1, A2, List<A3>> oneOrMore();

  @Override
  ParserGrammarDsl03Quantifier<E, R, T, A1, A2, A3> optional();

  @Override
  ParserGrammarDsl03Quantifier<E, R, T, A1, A2, List<A3>> zeroOrMore();


}