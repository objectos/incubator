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

public interface ParserGrammarDsl04Add<E, R, T, A1, A2, A3, A4>
    extends
    ParserGrammarDslQuantifier {

  <A5 extends R> ParserGrammarDsl05Add<E, R, T, A1, A2, A3, A4, A5> addRule(Class<A5> ruleType);

  <A5 extends T> ParserGrammarDsl05Add<E, R, T, A1, A2, A3, A4, A5> addToken(A5 tokenValue);

  void andCreateWith(Constructor4<E, A1, A2, A3, A4> constructor);

  @Override
  ParserGrammarDsl04Quantifier<E, R, T, A1, A2, A3, List<A4>> oneOrMore();

  @Override
  ParserGrammarDsl04Quantifier<E, R, T, A1, A2, A3, A4> optional();

  @Override
  ParserGrammarDsl04Quantifier<E, R, T, A1, A2, A3, List<A4>> zeroOrMore();

}