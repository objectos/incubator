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
package br.com.objectos.lexer.impl.ah;

import br.com.objectos.lexer.charexp.CharExpression;
import java.io.Closeable;
import java.util.Optional;

interface Source extends Closeable {

  void advance();

  void advance(String value);

  boolean hasNext();

  boolean matchesChar(char value);

  boolean matchesChar(CharExpression expression);

  boolean matchesString(String string);

  char nextChar();

  char peekChar();

  String peekString(char value);

  String peekString(CharExpression expression);

  Optional<String> peekString(String value);

}