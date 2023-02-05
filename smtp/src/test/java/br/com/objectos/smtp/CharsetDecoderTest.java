/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import org.testng.annotations.Test;

public class CharsetDecoderTest {

  @Test
  public void test() {
    Charset ascii;
    ascii = Charset.forName("US-ASCII");

    CharsetDecoder decoder;
    decoder = ascii.newDecoder();

    int capacity;
    capacity = 128;

    ByteBuffer byteBuffer;
    byteBuffer = ByteBuffer.allocate(capacity);

    CharBuffer charBuffer;
    charBuffer = CharBuffer.allocate(capacity);

    char[] charArray;
    charArray = charBuffer.array();

    assertEquals(charBuffer.position(), 0);
    assertEquals(charBuffer.limit(), capacity);
    assertEquals(charArray[0], '\0');
    assertEquals(charArray[1], '\0');
    assertEquals(charArray[2], '\0');
    assertEquals(charArray[3], '\0');
    assertEquals(charArray[4], '\0');
    assertEquals(charArray[5], '\0');
    assertEquals(charArray[6], '\0');

    decoder.reset();

    byteBuffer.put("ABC".getBytes(ascii));

    byteBuffer.flip();

    CoderResult result;
    result = decoder.decode(byteBuffer, charBuffer, false);

    assertFalse(result.isError());

    assertEquals(charBuffer.position(), 3);
    assertEquals(charBuffer.limit(), capacity);
    assertEquals(charArray[0], 'A');
    assertEquals(charArray[1], 'B');
    assertEquals(charArray[2], 'C');
    assertEquals(charArray[3], '\0');
    assertEquals(charArray[4], '\0');
    assertEquals(charArray[5], '\0');
    assertEquals(charArray[6], '\0');

    byteBuffer.clear();

    byteBuffer.put("DEF".getBytes(ascii));

    byteBuffer.flip();

    result = decoder.decode(byteBuffer, charBuffer, true);

    assertFalse(result.isError());

    decoder.flush(charBuffer);

    assertEquals(charBuffer.position(), 6);
    assertEquals(charBuffer.limit(), capacity);
    assertEquals(charArray[0], 'A');
    assertEquals(charArray[1], 'B');
    assertEquals(charArray[2], 'C');
    assertEquals(charArray[3], 'D');
    assertEquals(charArray[4], 'E');
    assertEquals(charArray[5], 'F');
    assertEquals(charArray[6], '\0');
  }

}
