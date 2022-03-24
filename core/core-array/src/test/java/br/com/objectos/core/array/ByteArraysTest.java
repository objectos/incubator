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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import br.com.objectos.random.testing.Next;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ByteArraysTest {

  @Test
  public void copyIfNecessary() {
    byte[] bytes;
    bytes = new byte[3];

    byte[] bytesNoGrowthRequired;
    bytesNoGrowthRequired = ByteArrays.copyIfNecessary(bytes, 2);

    assertSame(bytesNoGrowthRequired, bytes);

    assertEquals(bytesNoGrowthRequired.length, 3);

    byte[] bytesGrowthRequired;
    bytesGrowthRequired = ByteArrays.copyIfNecessary(bytes, 3);

    assertNotSame(bytesGrowthRequired, bytes);

    assertTrue(bytesGrowthRequired.length > bytes.length);

    try {
      ByteArrays.copyIfNecessary(bytes, -1);

      Assert.fail();
    } catch (IllegalArgumentException expected) {

    }
  }

  @Test
  public void emptyByteArray() {
    assertEquals(ByteArrays.empty().length, 0);
  }

  @Test
  public void toHexString() {
    toHexStringImpl("");
    toHexStringImpl("00", 0x00);
    toHexStringImpl("01", 0x01);
    toHexStringImpl("0123", 0x01, 0x23);
    toHexStringImpl("012345", 0x01, 0x23, 0x45);
    toHexStringImpl("01234567", 0x01, 0x23, 0x45, 0x67);
    toHexStringImpl("0123456789", 0x01, 0x23, 0x45, 0x67, 0x89);
    toHexStringImpl("0123456789ab", 0x01, 0x23, 0x45, 0x67, 0x89, 0xab);
    toHexStringImpl("0123456789abcd", 0x01, 0x23, 0x45, 0x67, 0x89, 0xab, 0xcd);
    toHexStringImpl("0123456789abcdef", 0x01, 0x23, 0x45, 0x67, 0x89, 0xab, 0xcd, 0xef);

    byte[] longBytes;
    longBytes = new byte[8];

    for (int i = 0; i < 1024; i++) {
      Next.bytes(longBytes);

      long longValue;
      longValue = ByteArrays.longValue(longBytes);

      String expected;
      expected = Long.toHexString(longValue);

      int pad;
      pad = 16 - expected.length();

      char[] zeroes;
      zeroes = new char[pad];

      Arrays.fill(zeroes, '0');

      assertEquals(ByteArrays.toHexString(longBytes), new String(zeroes) + expected);
    }

    try {
      ByteArrays.toHexString(null);

      Assert.fail();
    } catch (NullPointerException expected) {

    }
  }

  private void toHexStringImpl(String expected, int... ints) {
    int length;
    length = ints.length;

    byte[] bytes;
    bytes = new byte[length];

    for (int i = 0; i < length; i++) {
      int intValue;
      intValue = ints[i];

      bytes[i] = (byte) (intValue & 0xff);
    }

    assertEquals(ByteArrays.toHexString(bytes), expected);
  }

}