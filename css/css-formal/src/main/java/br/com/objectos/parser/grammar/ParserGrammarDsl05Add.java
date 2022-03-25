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

public interface ParserGrammarDsl05Add<E, R, T, A1, A2, A3, A4, A5>
    extends
    ParserGrammarDslQuantifier {
  
  <A6 extends R> ParserGrammarDsl06Add<E, R, T, A1, A2, A3, A4, A5, A6> addRule(Class<A6> ruleType);

  <A6 extends T> ParserGrammarDsl06Add<E, R, T, A1, A2, A3, A4, A5, A6> addToken(A6 tokenValue);

  void andCreateWith(Constructor5<E, A1, A2, A3, A4, A5> constructor);

  @Override
  ParserGrammarDsl05Quantifier<E, R, T, A1, A2, A3, A4, List<A5>> oneOrMore();

  @Override
  ParserGrammarDsl05Quantifier<E, R, T, A1, A2, A3, A4, A5> optional();

  @Override
  ParserGrammarDsl05Quantifier<E, R, T, A1, A2, A3, A4, List<A5>> zeroOrMore();

}