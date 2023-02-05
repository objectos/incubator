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
package br.com.objectos.lexer.impl.ah;

class StateWriter {

  private static final char ESCAPE = '\\';
  private static final char QUOTE = '\'';
  private final StringBuilder sb = new StringBuilder();

  private char kind = 'X';
  private String separator;
  private int lastNewLine;

  public final StateWriter begin() {
    if (sb.length() != 0 && lastNewLine != sb.length()) {
      sb.append(' ');
    }
    return this;
  }

  @Override
  public final String toString() {
    return sb.toString();
  }

  final StateWriter deleteLastChar() {
    sb.setLength(sb.length() - 1);
    return this;
  }

  final String removePrefix() {
    String prefix = sb.substring(lastNewLine, sb.length());
    sb.setLength(lastNewLine);
    return prefix;
  }

  final StateWriter setBrick() {
    kind = 'B';
    return this;
  }

  final StateWriter setSeparator(String string) {
    separator = string;
    return this;
  }

  final StateWriter setToken() {
    kind = 'T';
    return this;
  }

  final StateWriter write(char c) {
    sb.append(c);
    return this;
  }

  final StateWriter write(int value) {
    sb.append(value);
    return this;
  }

  final StateWriter write(String string) {
    sb.append(string);
    return this;
  }

  final StateWriter writeEnum(Object value) {
    Enum<?> enumValue = (Enum<?>) value;
    sb.append(enumValue.getClass().getSimpleName()).append('.').append(enumValue.name());
    return this;
  }

  final StateWriter writeFinal() {
    sb.append(" -> ");
    return this;
  }

  final StateWriter writeKind() {
    sb.append(kind);
    return this;
  }

  final StateWriter writeNewLine() {
    sb.append('\n');
    lastNewLine = sb.length();
    return this;
  }

  final StateWriter writeSeparator() {
    if (separator != null) {
      sb.append(separator);
      separator = null;
    }
    return this;
  }

  final StateWriter writeQuote(char c) {
    sb.append(QUOTE);
    if (Character.isLetterOrDigit(c)) {
      sb.append(c);
    } else {
      sb.append(ESCAPE);
      sb.append((int) c);
    }
    sb.append(QUOTE);
    return this;
  }

  final StateWriter writeQuantifier(Quantifier quantifier) {
    return write(quantifier.symbol);
  }

  final StateWriter writeQuote(String value) {
    sb.append(QUOTE).append(value).append(QUOTE);
    return this;
  }

  final StateWriter writeValue(Object value) {
    return toStringValue(writeKind().write(':').write('<'), value).write('>');
  }

  private StateWriter toStringValue(StateWriter w, Object value) {
    w = w.write(value.getClass().getSimpleName());
    return value.getClass().isEnum() ? toStringValueEnum(w, value) : w;
  }

  private StateWriter toStringValueEnum(StateWriter w, Object value) {
    Enum<?> enumValue = (Enum<?>) value;
    return w.write('.').write(enumValue.name());
  }

}