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

import objectos.lang.Check;

/**
 * <p>
 * This class provides {@code static} methods for operating on {@code char}
 * arrays.
 *
 * @since 2
 */
public final class CharArrays {

  static final char[] EMPTY_CHAR_ARRAY = new char[0];

  private CharArrays() {}

  /**
   * Returns the specified array (or a larger copy if necessary)
   * with all characters from the {@code CharSequence} appended at the specified
   * array offset. More formally:
   *
   * <p>
   * If the argument {@code s} is empty (i.e., s.length() returns zero) then the
   * {@code array} is returned unchanged.
   *
   * <p>
   * If the argument {@code s} is not empty and is such that
   * {@code offset + s.length() <= array.length} then the original array
   * is used for the next step. If not, then a copy of the
   * {@code array} is made such that {@code offset + s.length <= copy.length}.
   * The array copy is used for the next step.
   *
   * <p>
   * All characters from {@code s} are then copied into the array coming from
   * the previous step. The character at index 0 of
   * the sequence {@code s} is copied to the array position {@code offset};
   * the character at index 1 of the sequence {@code s} is copied to the array
   * position {@code offset + 1}; and so on until all characters from sequence
   * {@code s} are copied. The array is then returned.
   *
   * @param array
   *        the destination array instance. Characters will copied into this
   *        array (or a larger copy if necessary)
   * @param offset
   *        the starting {@code array} position. Indicates the array index where
   *        the first character will be copied to
   * @param s
   *        the {@code CharSequence} containing the characters to be copied from
   *
   * @return an array containing all the characters from {@code s} starting from
   *         the array position {@code offset}
   *
   * @throws IllegalArgumentException
   *         if {@code offset < 0}
   */
  public static char[] append(char[] array, int offset, CharSequence s) {
    Check.argument(offset >= 0, "offset cannot be negative");

    int length;
    length = s.length();

    if (length == 0) {
      return array;
    }

    int requiredIndex;
    requiredIndex = offset + length - 1;

    char[] result;
    result = copyIfNecessary(array, requiredIndex);

    for (int i = 0; i < length; i++) {
      result[offset + i] = s.charAt(i);
    }

    return result;
  }

  /**
   * Copies the values of the array into a larger one (if necessary) so that a
   * value can be inserted at the required index. More formally:
   *
   * <p>
   * If the {@code requiredIndex} is smaller than {@code 0} then an
   * {@link java.lang.IllegalArgumentException} is thrown.
   *
   * <p>
   * If the {@code requiredIndex} is smaller than {@code array.length} then the
   * array is not copied and is returned unchanged.
   *
   * <p>
   * If the {@code requiredIndex} is equal to or is greater than
   * {@code array.length} then:
   *
   * <ol>
   * <li>a new {@code char} array instance is created. The length of the new
   * array is guaranteed to be greater than {@code requiredIndex};</li>
   * <li>all values from the original array are copied into the new array so
   * that, for all valid indices in the original array, the new array contains
   * an identical value for the same index; and</li>
   * <li>the new array instance is returned.</li>
   * </ol>
   *
   * <p>
   * A typical usage is:
   *
   * <pre>
   * char c = readChar();
   * array = CharArrays.copyIfNecessary(array, currentIndex);
   * array[currentIndex++] = c;</pre>
   *
   * @param array
   *        the array instance to be copied if necessary
   * @param requiredIndex
   *        the index where a value is to be inserted
   *
   * @return the {@code array} instance itself or a larger copy of the
   *         original
   *
   * @throws IllegalArgumentException
   *         if {@code requiredIndex < 0}
   */
  public static char[] copyIfNecessary(char[] array, int requiredIndex) {
    Check.argument(requiredIndex >= 0, "requiredIndex cannot be negative");

    int length;
    length = array.length;

    if (requiredIndex < length) {
      return array;
    }

    int newLength;
    newLength = MoreArrays.growArrayLength(length, requiredIndex);

    char[] result;
    result = new char[newLength];

    System.arraycopy(array, 0, result, 0, length);

    return result;
  }

  /**
   * Returns the index of the first mismatch between two {@code char} arrays,
   * returns {@code -1} if there is no mismatch. The returned index will always
   * be in the range of {@code 0} (inclusive) to the length (inclusive) of the
   * smaller array.
   *
   * <p>
   * Examples:
   *
   * <pre>{@code
   * public void examples() {
   *   assertEquals(-1, mismatch(a(), a()));
   *   assertEquals(-1, mismatch(a('a'), a('a')));
   *   assertEquals(-1, mismatch(a('a', 'b'), a('a', 'b')));
   *
   *   assertEquals(0, mismatch(a(), a('x')));
   *   assertEquals(0, mismatch(a('x'), a()));
   *   assertEquals(0, mismatch(a('x'), a('o')));
   *   assertEquals(0, mismatch(a('o'), a('x')));
   *   assertEquals(0, mismatch(a('x', 'x'), a('o')));
   *
   *   assertEquals(1, mismatch(a('x'), a('x', 'o')));
   *   assertEquals(1, mismatch(a('x', 'x'), a('x', 'o')));
   *   assertEquals(1, mismatch(a('x', 'o'), a('x', 'x')));
   *
   *   assertEquals(2, mismatch(a('x', 'x'), a('x', 'x', 'o')));
   *   assertEquals(2, mismatch(a('x', 'x', 'x'), a('x', 'x', 'o')));
   *   assertEquals(2, mismatch(a('x', 'x', 'o'), a('x', 'x', 'x')));
   * }
   *
   * private char[] a(char... values) {
   *   return values;
   * }
   * }</pre>
   *
   * @param a
   *        the first array
   * @param b
   *        the second array
   *
   * @return the index of the first mismatch between the two arrays, otherwise
   *         {@code -1}.
   */
  public static int mismatch(char[] a, char[] b) {
    return MismatchSingleton.INSTANCE.mismatch(a, b);
  }

  /**
   * Returns the relative index of the first mismatch between two {@code char}
   * arrays over specified ranges, returns {@code -1} if there is no mismatch.
   * The returned index will always be in the range of {@code 0} (inclusive) to
   * the length (inclusive) of the smaller range.
   *
   * <p>
   * Examples:
   *
   * <pre>{@code
   * public void examples() {
   *   test(-1, "x123y", 1, 4, "123", 0, 3);
   *   test(-1, "123", 0, 3, "xxx123y", 3, 6);
   *
   *   test(0, "xxx123", 3, 6, "xxx987", 3, 6);
   *   test(0, "xxx123", 3, 6, "xx987", 2, 4);
   *   test(0, "xxx123", 3, 6, "987", 0, 3);
   *   test(0, "xxx987", 3, 6, "xxx123", 3, 6);
   *   test(0, "xx987", 2, 4, "xxx123", 3, 6);
   *   test(0, "987", 0, 3, "xxx123", 3, 6);
   *
   *   test(1, "xxx123", 3, 6, "xxx187", 3, 6);
   *   test(1, "xxx123", 3, 6, "xx187", 2, 4);
   *   test(1, "xxx123", 3, 6, "187", 0, 3);
   *   test(1, "xxx187", 3, 6, "xxx123", 3, 6);
   *   test(1, "xx187", 2, 4, "xxx123", 3, 6);
   *   test(1, "187", 0, 3, "xxx123", 3, 6);
   * }
   *
   * private void test(
   *     int expected,
   *     String a, int aFromIndex, int aToIndex,
   *     String b, int bFromIndex, int bToIndex) {
   *   assertEquals(
   *       expected,
   *
   *       mismatch(
   *           a.toCharArray(), aFromIndex, aToIndex,
   *           b.toCharArray(), bFromIndex, bToIndex
   *       )
   *   );
   * }
   * }</pre>
   *
   * @param a
   *        the first array
   * @param aFromIndex
   *        the index (inclusive) of the first element in the first array to be
   *        tested
   * @param aToIndex
   *        the index (exclusive) of the last element in the first array to be
   *        tested
   * @param b
   *        the second array
   * @param bFromIndex t
   *        he index (inclusive) of the first element in the second array to be
   *        tested
   * @param bToIndex
   *        the index (exclusive) of the last element in the second array to be
   *        tested
   *
   * @return the relative index of the first mismatch between the two arrays
   *         over the specified ranges, otherwise {@code -1}.
   *
   * @throws IllegalArgumentException
   *         if {@code aFromIndex > aToIndex} or
   *         if {@code bFromIndex > bToIndex}
   * @throws ArrayIndexOutOfBoundsException
   *         if {@code aFromIndex < 0 or aToIndex > a.length} or
   *         if {@code bFromIndex < 0 or bToIndex > b.length}
   */
  public static int mismatch(
      char[] a, int aFromIndex, int aToIndex,
      char[] b, int bFromIndex, int bToIndex) {
    return MismatchSingleton.INSTANCE.mismatch(
        a, aFromIndex, aToIndex,
        b, bFromIndex, bToIndex);
  }

}