/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.smtp;

import java.util.UUID;
import objectos.lang.Checks;

public class UUIDs {

  private static final int BYTE_MASK = 0xFF;
  private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
  private static final int HEX_MASK = 0xF;

  private UUIDs() {}

  public static UUID fromByteArray(byte[] bytes) {
    Checks.checkNotNull(bytes, "bytes == null");
    Checks.checkArgument(bytes.length == 16, "bytes.length != 16");

    long msb;
    msb = getMostSignificantBits(bytes);

    long lsb;
    lsb = getLeastSignificantBits(bytes);

    return new UUID(msb, lsb);
  }

  public static byte[] toByteArray(UUID uuid) {
    Checks.checkNotNull(uuid, "uuid == null");

    long msb;
    msb = uuid.getMostSignificantBits();

    long lsb;
    lsb = uuid.getLeastSignificantBits();

    return new byte[] {
        (byte) (msb >>> 56),
        (byte) (msb >>> 48),
        (byte) (msb >>> 40),
        (byte) (msb >>> 32),
        (byte) (msb >>> 24),
        (byte) (msb >>> 16),
        (byte) (msb >>> 8),
        (byte) msb,

        (byte) (lsb >>> 56),
        (byte) (lsb >>> 48),
        (byte) (lsb >>> 40),
        (byte) (lsb >>> 32),
        (byte) (lsb >>> 24),
        (byte) (lsb >>> 16),
        (byte) (lsb >>> 8),
        (byte) lsb
    };
  }

  public static String toHexString(byte[] bytes) {
    Checks.checkNotNull(bytes, "bytes == null");
    Checks.checkArgument(bytes.length == 16, "bytes.length != 16");

    char[] charArray;
    charArray = new char[32];

    for (int i = 0, j = 0; i < bytes.length; i++) {
      int b;
      b = bytes[i] & BYTE_MASK;

      charArray[j++] = HEX_CHARS[b >>> 4];
      charArray[j++] = HEX_CHARS[b & HEX_MASK];
    }

    return new String(charArray);
  }

  public static String toHexString(UUID uuid) {
    Checks.checkNotNull(uuid, "uuid == null");

    long msb;
    msb = uuid.getMostSignificantBits();

    long lsb;
    lsb = uuid.getLeastSignificantBits();

    int v0 = (int) (msb >>> 32);
    int v1 = (int) msb;
    int v2 = (int) (lsb >>> 32);
    int v3 = (int) lsb;

    return new String(
        new char[] {
            HEX_CHARS[(v0 >>> 28)],
            HEX_CHARS[(v0 >>> 24) & HEX_MASK],
            HEX_CHARS[(v0 >>> 20) & HEX_MASK],
            HEX_CHARS[(v0 >>> 16) & HEX_MASK],
            HEX_CHARS[(v0 >>> 12) & HEX_MASK],
            HEX_CHARS[(v0 >>> 8) & HEX_MASK],
            HEX_CHARS[(v0 >>> 4) & HEX_MASK],
            HEX_CHARS[(v0) & HEX_MASK],

            HEX_CHARS[(v1 >>> 28)],
            HEX_CHARS[(v1 >>> 24) & HEX_MASK],
            HEX_CHARS[(v1 >>> 20) & HEX_MASK],
            HEX_CHARS[(v1 >>> 16) & HEX_MASK],
            HEX_CHARS[(v1 >>> 12) & HEX_MASK],
            HEX_CHARS[(v1 >>> 8) & HEX_MASK],
            HEX_CHARS[(v1 >>> 4) & HEX_MASK],
            HEX_CHARS[(v1) & HEX_MASK],

            HEX_CHARS[(v2 >>> 28)],
            HEX_CHARS[(v2 >>> 24) & HEX_MASK],
            HEX_CHARS[(v2 >>> 20) & HEX_MASK],
            HEX_CHARS[(v2 >>> 16) & HEX_MASK],
            HEX_CHARS[(v2 >>> 12) & HEX_MASK],
            HEX_CHARS[(v2 >>> 8) & HEX_MASK],
            HEX_CHARS[(v2 >>> 4) & HEX_MASK],
            HEX_CHARS[(v2) & HEX_MASK],

            HEX_CHARS[(v3 >>> 28)],
            HEX_CHARS[(v3 >>> 24) & HEX_MASK],
            HEX_CHARS[(v3 >>> 20) & HEX_MASK],
            HEX_CHARS[(v3 >>> 16) & HEX_MASK],
            HEX_CHARS[(v3 >>> 12) & HEX_MASK],
            HEX_CHARS[(v3 >>> 8) & HEX_MASK],
            HEX_CHARS[(v3 >>> 4) & HEX_MASK],
            HEX_CHARS[(v3) & HEX_MASK]
        }
    );
  }

  private static long getLeastSignificantBits(byte[] bytes) {
    long result = bytes[8] & BYTE_MASK;
    result = (result << 8) | (bytes[9] & BYTE_MASK);
    result = (result << 8) | (bytes[10] & BYTE_MASK);
    result = (result << 8) | (bytes[11] & BYTE_MASK);
    result = (result << 8) | (bytes[12] & BYTE_MASK);
    result = (result << 8) | (bytes[13] & BYTE_MASK);
    result = (result << 8) | (bytes[14] & BYTE_MASK);
    result = (result << 8) | (bytes[15] & BYTE_MASK);
    return result;
  }

  private static long getMostSignificantBits(byte[] bytes) {
    long result = bytes[0] & BYTE_MASK;
    result = (result << 8) | (bytes[1] & BYTE_MASK);
    result = (result << 8) | (bytes[2] & BYTE_MASK);
    result = (result << 8) | (bytes[3] & BYTE_MASK);
    result = (result << 8) | (bytes[4] & BYTE_MASK);
    result = (result << 8) | (bytes[5] & BYTE_MASK);
    result = (result << 8) | (bytes[6] & BYTE_MASK);
    result = (result << 8) | (bytes[7] & BYTE_MASK);
    return result;
  }

}
