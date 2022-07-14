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
package objectos.asciidoc;

import java.util.Arrays;
import java.util.List;
import objectos.lang.Check;
import objectos.util.GrowableList;
import objectos.util.IntArrays;

class Lexer {

  class Symbol {
    static final int EOF = -1;

    static final int LINE_NO = -2;

    static final int TEXT = -3;

    static final int TITLE_EQUALS = -4;
  }

  private static final int _LINE_START = 1;

  private static final int _STOP = 0;

  private static final int _TEXT = 2;

  private int line;

  private String source;

  private int sourceIndex;

  private int state;

  private final List<String> strings = new GrowableList<>();

  private int[] symbol;

  private int symbolCounter;

  private int symbolIndex;

  Lexer() {
    symbol = new int[512];
  }

  final boolean hasSymbol() {
    return symbolCounter < symbolIndex;
  }

  final int nextSymbol() {
    return symbol[symbolCounter++];
  }

  final String string(int index) {
    return strings.get(index);
  }

  final void tokenize(String source) {
    Check.state(
      state == _STOP,

      """
      Concurrent parsing is not supported.

      It seems a previous AsciiDoc document parsing:

      - is currently running; or
      - finished abruptly (most likely due to a bug in this parser, sorry...).
      """);

    line = 0;

    this.source = source;

    sourceIndex = 0;

    state = _LINE_START;

    strings.clear();

    symbolIndex = 0;

    while (state != _STOP) {
      state = tokenize(state);
    }
  }

  final int[] toSymbol() {
    return Arrays.copyOf(symbol, symbolIndex);
  }

  private void addSymbol(int value) {
    symbol = IntArrays.copyIfNecessary(symbol, symbolIndex);

    symbol[symbolIndex++] = value;
  }

  private boolean hasChar() { return sourceIndex < source.length(); }

  private char nextChar() { return source.charAt(sourceIndex++); }

  private char peekChar() { return source.charAt(sourceIndex); }

  private int tokenize(int state) {
    return switch (state) {
      case _LINE_START -> tokenizeLineStart();
      case _TEXT -> tokenizeText();
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int tokenizeFinally() {
    addSymbol(Symbol.EOF);

    symbolCounter = 0;

    return _STOP;
  }

  private int tokenizeLineStart() {
    addSymbol(Symbol.LINE_NO);
    addSymbol(++line);

    if (!hasChar()) {
      return tokenizeFinally();
    }

    var c = peekChar();

    return switch (c) {
      case '=' -> tokenizeMaybeTitle(Symbol.TITLE_EQUALS);
      default -> throw new UnsupportedOperationException("Implement me");
    };
  }

  private int tokenizeMaybeTitle(int symbol) {
    int level = 0;

    var title = false;

    outer: while (hasChar()) {
      char c = nextChar();

      switch (c) {
        case ' ':
          title = true;

          break outer;

        case '=':
          level++;

          continue outer;

        default:
          throw new UnsupportedOperationException("Implement me :: to text?");
      }
    }

    if (!title) {
      throw new UnsupportedOperationException("Implement me :: not found (eof?)");
    }

    addSymbol(symbol);
    addSymbol(level);

    return _TEXT;
  }

  private int tokenizeText() {
    var beginIndex = sourceIndex;

    var eol = false;

    while (hasChar()) {
      var c = nextChar();

      switch (c) {
        default -> {}
      }
    }

    if (!eol) {
      if (beginIndex < sourceIndex) {
        var s = source.substring(beginIndex, sourceIndex);

        addSymbol(Symbol.TEXT);
        addSymbol(strings.size());

        strings.add(s);
      }

      return tokenizeFinally();
    } else {
      throw new UnsupportedOperationException("Implement me");
    }
  }

}