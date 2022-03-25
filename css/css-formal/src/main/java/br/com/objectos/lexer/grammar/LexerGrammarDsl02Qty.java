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
package br.com.objectos.lexer.grammar;

import br.com.objectos.lexer.charexp.CharExpression;
import br.com.objectos.lexer.lang.Constructor2;

public interface LexerGrammarDsl02Qty<E, T, B, A1, A2> {

  void andIgnore();

  void andCreateWith(Constructor2<E, A1, A2> constructor);

  void andPopLexer(Constructor2<E, A1, A2> constructor);

  void andPopLexer(Constructor2<E, A1, A2> constructor, int count);

  void andPushLexer(Constructor2<E, A1, A2> constructor, LexerGrammar<T, B> lexer);

  <A3 extends B> LexerGrammarDsl03AddBrick<E, T, B, A1, A2, A3> addBrick(A3 brick);

  LexerGrammarDsl03AddString<E, T, B, A1, A2> addChar(char value);

  LexerGrammarDsl03AddString<E, T, B, A1, A2> addChar(CharExpression expression);

  LexerGrammarDsl03AddString<E, T, B, A1, A2> addString(String string);

}