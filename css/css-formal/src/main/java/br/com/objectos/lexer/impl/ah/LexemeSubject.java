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
package br.com.objectos.lexer.impl.ah;

import br.com.objectos.lexer.charexp.CharExpression;
import java.util.Optional;

class LexemeSubject {

  private final Source source;
  private final ValueList valueList;
  private final StringBuilder buffer = new StringBuilder();

  public LexemeSubject(Source source, ValueList valueList) {
    this.source = source;
    this.valueList = valueList;
  }

  public final void commit() {
    String string = buffer.toString();
    valueList.add(string);
    buffer.setLength(0);
  }

  public final void consumeChar() {
    char nextChar = source.nextChar();
    buffer.append(nextChar);
  }

  public final void consumeChar(char c) {
    source.advance();
    buffer.append(c);
  }

  public final void consumeString(String string) {
    source.advance(string);
    buffer.append(string);
  }

  public final boolean hasNext() {
    return source.hasNext();
  }

  public final boolean matchesChar(char c) {
    return source.matchesChar(c);
  }

  public final boolean matchesChar(CharExpression expression) {
    return source.matchesChar(expression);
  }

  public final boolean matchesString(String string) {
    return source.matchesString(string);
  }

  public final char peekChar() {
    return source.peekChar();
  }

  public final String peekString(char c) {
    return source.peekString(c);
  }

  public final String peekString(CharExpression expression) {
    return source.peekString(expression);
  }

  public final Optional<String> peekString(String value) {
    return source.peekString(value);
  }

  @Override
  public final String toString() {
    return buffer.toString();
  }

}