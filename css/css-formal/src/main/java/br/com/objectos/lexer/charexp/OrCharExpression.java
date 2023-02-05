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
package br.com.objectos.lexer.charexp;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OrCharExpression extends CharExpression {

  private final CharExpression[] values;

  OrCharExpression(CharExpression[] values) {
    super(Stream.of(values).map(e -> e.description).collect(Collectors.joining("||")));
    this.values = values;
  }

  @Override
  public final boolean matches(char next) {
    for (CharExpression expression : values) {
      if (expression.matches(next)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public final char[] toCharArray() {
    int totalLength = 0;
    char[][] arrays = new char[values.length][];

    for (int i = 0; i < values.length; i++) {
      char[] thisArray = values[i].toCharArray();
      totalLength += thisArray.length;
      arrays[i] = thisArray;
    }

    char[] sumArray = new char[totalLength];
    int destPos = 0;

    for (int i = 0; i < values.length; i++) {
      char[] src = arrays[i];
      System.arraycopy(src, 0, sumArray, destPos, src.length);
      destPos += src.length;
    }

    Arrays.sort(sumArray);
    return sumArray;
  }

}