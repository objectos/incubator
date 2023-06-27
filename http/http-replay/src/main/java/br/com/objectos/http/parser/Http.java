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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Locale;
import java.util.TimeZone;

public final class Http {

  public static final char CR = '\r';

  static final byte CR_BYTE = CR;

  public static final String CRLF = "\r\n";

  public static final char LF = '\n';

  static final byte LF_BYTE = LF;

  static final char COLON = ':';

  static final byte COLON_BYTE = COLON;

  static final int DEFAULT_BUFFER_SIZE = 4096;

  static final char HTAB = '\t';

  static final char SP = ' ';

  static final byte SP_BYTE = SP;

  private Http() {}

  static DateFormat createDateFormat() {
    DateFormat format;
    format = new SimpleDateFormat("EEE, dd LLL yyyy HH:mm:ss zzz", Locale.US);

    TimeZone gmt;
    gmt = TimeZone.getTimeZone("GMT");

    format.setTimeZone(gmt);

    return format;
  }

  static <E> ArrayDeque<E> newArrayDeque(int capacity) {
    return new ArrayDeque<E>(capacity);
  }

}
