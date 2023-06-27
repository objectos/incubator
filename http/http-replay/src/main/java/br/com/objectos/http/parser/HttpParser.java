/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.http.parser;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

final class HttpParser {

  public static final char COLON = ':';

  public static final char SP = ' ';

  static final Charset US_ASCII = Charset.forName("US-ASCII");

  private HttpParser() {}

  public static RequestParser createRequestParser(ByteBuffer byteBuffer, CharBuffer charBuffer) {
    return RequestParser.create(byteBuffer, charBuffer);
  }

  public static boolean isTokenChar(char c) {
    if (Character.isAlphabetic(c)) {
      return true;
    }

    if (Character.isDigit(c)) {
      return true;
    }

    switch (c) {
      case '!':
      case '#':
      case '$':
      case '%':
      case '&':
      case '\'':
      case '*':
      case '+':
      case '-':
      case '.':
      case '^':
      case '_':
      case '`':
      case '|':
      case '~':
        return true;
      default:
        return false;
    }
  }

  public static CharsetDecoder newUsAsciiDecoder() {
    return US_ASCII.newDecoder();
  }

  static int toUsAsciiDigit(char c) {
    return c - 48;
  }

}
