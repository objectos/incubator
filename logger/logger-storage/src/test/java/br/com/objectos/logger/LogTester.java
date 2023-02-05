/*
 * Copyright (C) 2021-2023 Objectos Software LTDA.
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
package br.com.objectos.logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

final class LogTester {

  private final String line;
  private int index;

  LogTester(String line) {
    this.line = line;
  }

  public final void digit(String hasLength) {
    String actual;
    actual = get(hasLength);

    char[] charArray;
    charArray = actual.toCharArray();

    for (char c : charArray) {
      assertTrue(Character.isDigit(c));
    }
  }

  public final void skip(int length) {
    index += length;
  }

  public final void test(char expected) {
    char c;
    c = line.charAt(index++);

    assertEquals(c, expected);
  }

  public final void test(String expected) {
    String actual;
    actual = get(expected);

    assertEquals(actual, expected);
  }

  public void test(String expected, int length) {
    String actual;
    actual = get(expected);

    assertEquals(actual, expected);

    int toSkip;
    toSkip = length - expected.length();

    skip(toSkip);
  }

  private String get(String expected) {
    int start;
    start = index;

    int length;
    length = expected.length();

    index += length;

    return line.substring(start, index);
  }

}
