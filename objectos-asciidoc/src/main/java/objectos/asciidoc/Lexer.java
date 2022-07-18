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
import objectos.lang.Check;
import objectos.util.IntArrays;

class Lexer {

  class Symbol {
    static final int BACKTICK = -1;

    static final int EMPTY = -2;

    static final int EOF = -3;

    static final int EOL = -4;

    static final int PARAGRAPH = -5;

    static final int TITLE = -6;

    static final int TITLE_LEVEL = -7;

    static final int TITLE_TEXT = -8;
  }

  private static final int _FINALLY = 1;

  private static final int _LINE_START = 2;

  private static final int _STOP = 0;

  private static final int _TEXT = 3;

  private static final int _TITLE = 4;

  private int counter;

  private String source;

  private int sourceIndex;

  private int state;

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

  final String source(int beginIndex, int endIndex) {
    return source.substring(beginIndex, endIndex);
  }

  final void tokenize(String source) {
    Check.state(
      state == _STOP,

      """
      Concurrent lexical analysis is not supported.

      It seems a previous AsciiDoc document lexical analysis:

      - is currently running; or
      - finished abruptly (most likely due to a bug in this lexer, sorry...).
      """);

    counter = 0;

    this.source = source;

    sourceIndex = 0;

    symbolIndex = 0;

    state = _LINE_START;

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

  private void atChar(int symbol) {
    addSymbol(symbol);
    addSymbol(sourceIndex);
  }

  private void atPrevious(int symbol) {
    addSymbol(symbol);
    addSymbol(sourceIndex - 1);
  }

  private boolean hasChar() { return sourceIndex < source.length(); }

  private char nextChar() { return source.charAt(sourceIndex++); }

  private char peekChar() { return source.charAt(sourceIndex); }

  private int tokenize(int state) {
    return switch (state) {
      case _FINALLY -> tokenizeFinally();
      case _LINE_START -> tokenizeLineStart();
      case _TEXT -> tokenizeText();
      case _TITLE -> tokenizeTitle();
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int tokenizeFinally() {
    addSymbol(Symbol.EOF);
    addSymbol(sourceIndex);

    symbolCounter = 0;

    return _STOP;
  }

  private int tokenizeLineStart() {
    if (!hasChar()) {
      atChar(Symbol.EMPTY);

      return _FINALLY;
    }

    var c = nextChar();

    return switch (c) {
      case '\n' -> {
        atPrevious(Symbol.EMPTY);

        yield _LINE_START;
      }
      case '=' -> {
        counter = 1;

        yield _TITLE;
      }
      default -> {
        atPrevious(Symbol.PARAGRAPH);

        switch (c) {
          case '`' -> { atPrevious(Symbol.BACKTICK); }
        }

        yield _TEXT;
      }
    };
  }

  private int tokenizeText() {
    if (!hasChar()) {
      return _FINALLY;
    }

    var c = nextChar();

    return switch (c) {
      case '\n' -> {
        atPrevious(Symbol.EOL);

        yield _LINE_START;
      }
      case '`' -> {
        atPrevious(Symbol.BACKTICK);

        yield _TEXT;
      }
      default -> _TEXT;
    };
  }

  private int tokenizeTitle() {
    if (!hasChar()) {
      return _FINALLY;
    }

    char c = peekChar();

    return switch (c) {
      case ' ' -> {
        addSymbol(Symbol.TITLE);
        addSymbol(symbolIndex - counter);

        nextChar();

        addSymbol(Symbol.TITLE_LEVEL);
        addSymbol(counter);

        atChar(Symbol.TITLE_TEXT);

        yield _TEXT;
      }

      case '=' -> {
        counter++;

        yield state;
      }
      default -> {
        addSymbol(Symbol.PARAGRAPH);
        addSymbol(symbolIndex - counter);

        yield _TEXT;
      }
    };
  }

}