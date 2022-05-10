/*
 * Copyright 2022 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.logger;

final class Logging {

  private Logging() {}

  static void abbreviate(StringBuilder out, String source, int length) {
    String result;
    result = source;

    int resultLength;
    resultLength = result.length();

    if (resultLength > length) {
      int start;
      start = resultLength - length;

      result = result.substring(start, resultLength);
    }

    out.append(result);

    int pad;
    pad = length - result.length();

    for (int i = 0; i < pad; i++) {
      out.append(' ');
    }
  }

  static String format(Object o) {
    if (o != null) {
      return o.toString();
    } else {
      return "null";
    }
  }

  static void pad(StringBuilder out, String source, int length) {
    String result;
    result = source;

    if (result.length() > length) {
      result = result.substring(0, length);
    }

    out.append(result);

    int pad;
    pad = length - result.length();

    for (int i = 0; i < pad; i++) {
      out.append(' ');
    }
  }

}