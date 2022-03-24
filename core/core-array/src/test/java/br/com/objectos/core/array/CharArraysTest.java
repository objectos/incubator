/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package br.com.objectos.core.array;

import static br.com.objectos.core.array.CharArrays.mismatch;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import br.com.objectos.core.string.RandomString;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CharArraysTest {

  private static final RandomString RANDOM = new RandomString();

  @Test
  public void append() {
    char[] subject;
    subject = new char[3];

    char[] noGrowthRequired;
    noGrowthRequired = CharArrays.append(subject, 1, "bc");

    assertEquals(noGrowthRequired[0], '\0');
    assertEquals(noGrowthRequired[1], 'b');
    assertEquals(noGrowthRequired[2], 'c');

    assertSame(noGrowthRequired, subject);
    assertEquals(noGrowthRequired.length, 3);

    char[] growthRequired;
    growthRequired = CharArrays.append(subject, 3, "def");

    assertEquals(growthRequired[0], '\0');
    assertEquals(growthRequired[1], 'b');
    assertEquals(growthRequired[2], 'c');
    assertEquals(growthRequired[3], 'd');
    assertEquals(growthRequired[4], 'e');
    assertEquals(growthRequired[5], 'f');
    assertEquals(growthRequired[6], '\0');

    assertNotSame(growthRequired, subject);
    assertTrue(growthRequired.length > subject.length);
  }

  @Test
  public void copyIfNecessary() {
    char[] chars;
    chars = new char[3];

    char[] charsNoGrowthRequired;
    charsNoGrowthRequired = CharArrays.copyIfNecessary(chars, 2);

    assertSame(charsNoGrowthRequired, chars);
    assertEquals(charsNoGrowthRequired.length, 3);

    char[] charsGrowthRequired = CharArrays.copyIfNecessary(chars, 3);

    assertNotSame(charsGrowthRequired, chars);
    assertTrue(charsGrowthRequired.length > chars.length);

    try {
      CharArrays.copyIfNecessary(chars, -1);

      Assert.fail();
    } catch (IllegalArgumentException expected) {

    }
  }

  @Test
  public void examples() {
    assertEquals(-1, mismatch(a(), a()));
    assertEquals(-1, mismatch(a('a'), a('a')));
    assertEquals(-1, mismatch(a('a', 'b'), a('a', 'b')));

    assertEquals(0, mismatch(a(), a('x')));
    assertEquals(0, mismatch(a('x'), a()));
    assertEquals(0, mismatch(a('x'), a('o')));
    assertEquals(0, mismatch(a('o'), a('x')));
    assertEquals(0, mismatch(a('x', 'x'), a('o')));

    assertEquals(1, mismatch(a('x'), a('x', 'o')));
    assertEquals(1, mismatch(a('x', 'x'), a('x', 'o')));
    assertEquals(1, mismatch(a('x', 'o'), a('x', 'x')));

    assertEquals(2, mismatch(a('x', 'x'), a('x', 'x', 'o')));
    assertEquals(2, mismatch(a('x', 'x', 'x'), a('x', 'x', 'o')));
    assertEquals(2, mismatch(a('x', 'x', 'o'), a('x', 'x', 'x')));
  }

  @Test(dataProvider = "mismatchParam")
  public void mismatch1(int len) {
    String s;
    s = RANDOM.nextString(len);

    char[] a;
    a = s.toCharArray();

    char[] b;
    b = Arrays.copyOf(a, len);

    test(-1, a, a);
    test(-1, a, b);
    test(-1, b, a);

    b = Arrays.copyOf(a, len + 1);

    test(len, a, b);
    test(len, b, a);

    b[0] = (char) (a[0] + 1);

    test(0, a, b);
    test(0, b, a);

    b[0] = a[0];
    b[len - 1] = (char) (a[len - 1] + 1);

    test(len - 1, a, b);
    test(len - 1, b, a);
  }

  @Test
  public void mismatch2() {
    test(-1, "x123y", 1, 4, "123", 0, 3);
    test(-1, "123", 0, 3, "xxx123y", 3, 6);

    test(0, "xxx123", 3, 6, "xxx987", 3, 6);
    test(0, "xxx123", 3, 6, "xx987", 2, 4);
    test(0, "xxx123", 3, 6, "987", 0, 3);
    test(0, "xxx987", 3, 6, "xxx123", 3, 6);
    test(0, "xx987", 2, 4, "xxx123", 3, 6);
    test(0, "987", 0, 3, "xxx123", 3, 6);

    test(1, "xxx123", 3, 6, "xxx187", 3, 6);
    test(1, "xxx123", 3, 6, "xx187", 2, 4);
    test(1, "xxx123", 3, 6, "187", 0, 3);
    test(1, "xxx187", 3, 6, "xxx123", 3, 6);
    test(1, "xx187", 2, 4, "xxx123", 3, 6);
    test(1, "187", 0, 3, "xxx123", 3, 6);
  }

  @Test(
      dataProvider = "mismatch2_AIOBEParam",
      expectedExceptions = ArrayIndexOutOfBoundsException.class)
  public void mismatch2_AIOBE(
      String a, int aFromIndex, int aToIndex,
      String b, int bFromIndex, int bToIndex) {
    CharArrays.mismatch(
        a.toCharArray(), aFromIndex, aToIndex,
        b.toCharArray(), bFromIndex, bToIndex
    );

    Assert.fail();
  }

  @DataProvider
  public Object[][] mismatch2_AIOBEParam() {
    return new Object[][] {
        {"abcde", -1, 2, "abcde", 2, 3},
        {"abcde", 0, 6, "abcde", 2, 3},
        {"abcde", 0, 300, "abcde", 2, 3},

        {"abcde", 2, 3, "abcde", -2, 3},
        {"abcde", 2, 3, "abcde", 2, 6},
        {"abcde", 2, 3, "abcde", 2, 10}
    };
  }

  @Test(
      dataProvider = "mismatch2_IAEParam",
      expectedExceptions = IllegalArgumentException.class)
  public void mismatch2_IAE(
      String a, int aFromIndex, int aToIndex,
      String b, int bFromIndex, int bToIndex) {
    CharArrays.mismatch(
        a.toCharArray(), aFromIndex, aToIndex,
        b.toCharArray(), bFromIndex, bToIndex
    );

    Assert.fail();
  }

  @DataProvider
  public Object[][] mismatch2_IAEParam() {
    return new Object[][] {
        {"abcde", 3, 2, "abcde", 2, 3},
        {"abcde", 2, 3, "abcde", 3, 1}
    };
  }

  @DataProvider
  public Object[][] mismatchParam() {
    Object[][] result;
    result = new Object[63][];

    for (int i = 0; i < result.length; i++) {
      result[i] = new Object[] {i + 1};
    }

    return result;
  }

  private char[] a(char... values) {
    return values;
  }

  private void test(int expected, char[] a, char[] b) {
    Assert.assertEquals(
        CharArrays.mismatch(a, b),

        expected
    );
  }

  private void test(
      int expected,
      String a, int aFromIndex, int aToIndex,
      String b, int bFromIndex, int bToIndex) {
    Assert.assertEquals(
        CharArrays.mismatch(
            a.toCharArray(), aFromIndex, aToIndex,
            b.toCharArray(), bFromIndex, bToIndex
        ),

        expected
    );
  }

}