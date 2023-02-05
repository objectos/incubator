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

public interface ParserGrammarDsl06Add<E, R, T, A1, A2, A3, A4, A5, A6>
    extends
    ParserGrammarDslQuantifier {

  <A7 extends T> ParserGrammarDsl07Add<E, R, T, A1, A2, A3, A4, A5, A6, A7> addToken(A7 tokenValue);

  void andCreateWith(Constructor6<E, A1, A2, A3, A4, A5, A6> constructor);

  @Override
  ParserGrammarDsl06Quantifier<E, R, T, A1, A2, A3, A4, A5, List<A6>> oneOrMore();

  @Override
  ParserGrammarDsl06Quantifier<E, R, T, A1, A2, A3, A4, A5, A6> optional();

  @Override
  ParserGrammarDsl06Quantifier<E, R, T, A1, A2, A3, A4, A5, List<A6>> zeroOrMore();

}