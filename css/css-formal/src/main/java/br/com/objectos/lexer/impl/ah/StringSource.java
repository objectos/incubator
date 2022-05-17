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
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import objectos.lang.Checks;

class StringSource implements Source {

  final char[] charArray;
  private int index;

  StringSource(String string) {
    charArray = string.toCharArray();
  }

  @Override
  public final void advance() {
    index++;
  }

  @Override
  public final void advance(String value) {
    index += value.length();
  }

  @Override
  public final void close() throws IOException {
    // noop
  }

  @Override
  public final boolean hasNext() {
    return index < charArray.length;
  }

  @Override
  public final boolean matchesChar(char value) {
    return hasNext() ? currentChar() == value : false;
  }

  @Override
  public final boolean matchesChar(CharExpression expression) {
    return hasNext() ? expression.matches(currentChar()) : false;
  }

  @Override
  public final boolean matchesString(String string) {
    int thisLength = charArray.length - index;
    char[] testArray = string.toCharArray();
    int testLength = testArray.length;

    if (testLength > thisLength) {
      return false;
    }

    for (int i = 0; i < testLength; i++) {
      char testChar = testArray[i];
      char thisChar = charArray[index + i];
      if (testChar != thisChar) {
        return false;
      }
    }

    return true;
  }

  @Override
  public final char nextChar() {
    return charArray[index++];
  }

  @Override
  public final char peekChar() {
    return hasNext() ? currentChar() : nsee();
  }

  @Override
  public final String peekString(char value) {
    StringBuilder sb = new StringBuilder();
    for (int i = index; i < charArray.length; i++) {
      char c = charArray[i];
      if (c != value) {
        break;
      }
      sb.append(c);
    }
    return sb.toString();
  }

  @Override
  public final String peekString(CharExpression expression) {
    StringBuilder sb = new StringBuilder();
    for (int i = index; i < charArray.length; i++) {
      char c = charArray[i];
      if (!expression.matches(c)) {
        break;
      }
      sb.append(c);
    }
    return sb.toString();
  }

  @Override
  public final Optional<String> peekString(String value) {
    Checks.checkArgument(value.length() > 0, "length > 0");
    char[] testArray = value.toCharArray();
    int testIndex = 0;

    StringBuilder sb = new StringBuilder();
    for (int i = index; i < charArray.length; i++) {
      char c = charArray[i];
      sb.append(c);

      if (testArray[testIndex] != c) {
        continue;
      }

      testIndex++;

      if (testIndex == testArray.length) {
        sb.setLength(sb.length() - testArray.length);
        return Optional.of(sb.toString());
      }
    }

    return Optional.empty();
  }

  @Override
  public final String toString() {
    return new String(charArray);
  }

  private char currentChar() {
    return charArray[index];
  }

  private char nsee() {
    throw new NoSuchElementException();
  }

}