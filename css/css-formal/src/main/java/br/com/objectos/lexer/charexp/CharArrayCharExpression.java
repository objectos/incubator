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
package br.com.objectos.lexer.charexp;

import java.util.Arrays;

class CharArrayCharExpression extends CharExpression {

  private final char[] value;

  CharArrayCharExpression(char[] value) {
    super(toString(sort(value)));
    this.value = value;
  }

  private static char[] sort(char[] value) {
    Arrays.sort(value);
    return value;
  }

  private static String toString(char[] charArray) {
    StringBuilder sb = new StringBuilder();
    char prefix = '[';

    for (char c : charArray) {
      sb.append(prefix);
      if (Character.isAlphabetic(c) || Character.isDigit(c)) {
        sb.append('\'').append(c).append('\'');
      } else {
        sb.append('\'').append('\\').append(Integer.toString(c)).append('\'');
      }
      prefix = ',';
    }

    return sb.append(']').toString();
  }

  @Override
  public final boolean matches(char next) {
    return Arrays.binarySearch(value, next) >= 0;
  }

  @Override
  public final char[] toCharArray() {
    return value;
  }

}