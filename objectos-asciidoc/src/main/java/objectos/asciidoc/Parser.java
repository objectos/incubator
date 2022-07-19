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

class Parser extends Lexer {

  static class Code {
    static final int END_DOCUMENT = -1;

    static final int END_MONOSPACE = -2;

    static final int END_PARAGRAPH = -3;

    static final int END_PREAMBLE = -4;

    static final int END_TITLE = -5;

    static final int START_DOCUMENT = -6;

    static final int START_MONOSPACE = -7;

    static final int START_PARAGRAPH = -8;

    static final int START_PREAMBLE = -9;

    static final int START_TITLE = -10;

    static final int TEXT = -11;
  }

  // mods

  private static final int _EOF = 0;
  private static final int _MAYBE = 1;
  private static final int _MULTILINE = 2;

  // sections

  private static final int _START = 100_000;
  private static final int _DOCUMENT = 200_000;
  private static final int _PREAMBLE = 300_000;

  // blocks

  private static final int _TITLE = 1_000;
  private static final int _METADATA = 2_000;
  private static final int _PARAGRAPH = 3_000;

  // inline
  private static final int _MONOSPACE = 100;

  private int beginIndexText = Integer.MAX_VALUE;

  private int[] code;

  private int codeCounter;

  private int codeIndex;

  private int level;

  private int monospace = Integer.MAX_VALUE;

  private int state;

  private final List<String> strings = new GrowableList<>();

  Parser() {
    code = new int[1024];
  }

  final boolean hasCode() {
    return codeCounter < codeIndex;
  }

  final int nextCode() {
    return code[codeCounter++];
  }

  final void parse() {
    Check.state(
      state == _EOF,

      """
      Concurrent parsing is not supported.

      It seems a previous AsciiDoc document parsing:

      - is currently running; or
      - finished abruptly (most likely due to a bug in this parser, sorry...).
      """
    );

    beginIndexText = Integer.MAX_VALUE;

    codeIndex = 0;

    level = 0;

    monospace = Integer.MAX_VALUE;

    state = _START;

    strings.clear();

    parse0();

    codeCounter = 0;
  }

  final String string(int index) {
    return strings.get(index);
  }

  final int[] toCode() {
    return Arrays.copyOf(code, codeIndex);
  }

  private void addCode(int value) {
    code = IntArrays.copyIfNecessary(code, codeIndex);

    code[codeIndex++] = value;
  }

  private void consumeText(int value) {
    var endIndex = value;

    if (monospace != Integer.MAX_VALUE) {
      endIndex = monospace;
    }

    if (beginIndexText < endIndex) {
      var s = source(beginIndexText, endIndex);

      addCode(Code.TEXT);
      addCode(strings.size());

      strings.add(s);

      beginIndexText = Integer.MAX_VALUE;
    }

    if (monospace == Integer.MAX_VALUE) {
      return;
    }

    monospace++;

    if (monospace < value) {
      var s = source(monospace, value);

      addCode(Code.START_MONOSPACE);
      addCode(Code.TEXT);
      addCode(strings.size());
      addCode(Code.END_MONOSPACE);

      strings.add(s);

      monospace = Integer.MAX_VALUE;
    }
  }

  private void parse0() {
    while (hasSymbol()) {
      var symbol = nextSymbol();
      var value = nextSymbol();

      state = switch (symbol) {
        case Symbol.BACKTICK -> parseBacktick(value);
        case Symbol.EOF -> parseEof(value);
        case Symbol.EQUALS -> parseEquals(value);
        case Symbol.LF -> parseLf(value);
        case Symbol.WORD -> parseWord(value);
        default -> throw new UnsupportedOperationException("Implement me :: symbol=" + symbol);
      };
    }

    if (state != _EOF) {
      throw new UnsupportedOperationException("Implement me :: state=" + state);
    }

    codeCounter = 0;
  }

  private int parseBacktick(int value) {
    return switch (state) {
      case _DOCUMENT + _TITLE -> {
        monospace = value;

        yield _DOCUMENT + _TITLE + _MAYBE + _MONOSPACE;
      }
      case _DOCUMENT + _TITLE + _MAYBE + _MONOSPACE -> {
        consumeText(value);

        beginIndexText = value + 1;

        yield _DOCUMENT + _TITLE;
      }
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int parseEof(int value) {
    switch (state) {
      case _DOCUMENT + _TITLE -> {
        consumeText(value);

        addCode(Code.END_TITLE);
        addCode(Code.END_DOCUMENT);
      }
      case _MAYBE + _DOCUMENT + _METADATA -> {
        consumeText(value);

        addCode(Code.END_DOCUMENT);
      }
      case _PREAMBLE + _PARAGRAPH + _MULTILINE -> {
        consumeText(value);

        addCode(Code.END_PARAGRAPH);
        addCode(Code.END_PREAMBLE);
        addCode(Code.END_DOCUMENT);
      }
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    }

    return _EOF;
  }

  private int parseEquals(int value) {
    return switch (state) {
      case _START -> {
        level = 1;

        yield _MAYBE + _DOCUMENT + _TITLE;
      }
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int parseLf(int value) {
    return switch (state) {
      case _DOCUMENT + _TITLE -> {
        consumeText(value);

        addCode(Code.END_TITLE);

        yield _MAYBE + _DOCUMENT + _METADATA;
      }
      case _MAYBE + _DOCUMENT + _METADATA -> _MAYBE + _PREAMBLE;
      case _PREAMBLE + _PARAGRAPH -> _PREAMBLE + _PARAGRAPH + _MULTILINE;
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int parseWord(int value) {
    return switch (state) {
      case _DOCUMENT + _TITLE -> state;
      case _DOCUMENT + _TITLE + _MAYBE + _MONOSPACE -> state;
      case _MAYBE + _DOCUMENT + _TITLE -> {
        if (level == 1) {
          addCode(Code.START_DOCUMENT);
          addCode(Code.START_TITLE);
          addCode(level);

          beginIndexText = value;

          yield _DOCUMENT + _TITLE;
        } else {
          throw new UnsupportedOperationException("Implement me :: start section?");
        }
      }
      case _MAYBE + _PREAMBLE -> {
        addCode(Code.START_PREAMBLE);

        beginIndexText = value;

        addCode(Code.START_PARAGRAPH);

        yield _PREAMBLE + _PARAGRAPH;
      }
      case _PREAMBLE + _PARAGRAPH -> state;
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

}