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
package br.com.objectos.lexer.grammar;

import br.com.objectos.lexer.lang.Constructor1;

public interface LexerGrammarDsl01Qty<E, T, B, A1> {

  void andIgnore();

  void andCreateWith(Constructor1<E, A1> constructor);

  <A2 extends B> LexerGrammarDsl02AddBrick<E, T, B, A1, A2> addBrick(A2 brick);

  LexerGrammarDsl02AddString<E, T, B, A1> addString(String string);

}