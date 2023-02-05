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

import br.com.objectos.lexer.charexp.CharExpression;

public interface LexerGrammarDslDefineRuleType<E, T, B> {

  LexerGrammarDslAsEnum<E, T, B> asEnum();

  LexerGrammarDslAsEnum<E, T, B> asEnumRange(E from, E to);

  <A1 extends B> LexerGrammarDsl01AddBrick<E, T, B, A1> addBrick(A1 brick);

  <A1 extends B> LexerGrammarDsl01AddBrick<E, T, B, A1> addBrick(Class<A1> brickType);

  LexerGrammarDsl01AddBrick<E, T, B, B> alternatives(@SuppressWarnings("unchecked") B... bricks);

  LexerGrammarDsl01AddBrick<E, T, B, B> alternatives(@SuppressWarnings("unchecked") Class<? extends B>... bricks);

  LexerGrammarDsl01AddString<E, T, B> addChar(char c);

  LexerGrammarDsl01AddString<E, T, B> addChar(CharExpression expression);

  LexerGrammarDsl01AddString<E, T, B> addString(String string);

}