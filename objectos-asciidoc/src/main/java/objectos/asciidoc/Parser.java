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
  private static final int _MAYBE = 1 << 0;
  private static final int _MULTILINE = 1 << 1;

  // sections

  private static final int _START = 1 << 2;
  private static final int _DOCUMENT = 1 << 3;
  private static final int _PREAMBLE = 1 << 4;

  // blocks

  private static final int _TITLE = 1 << 5;
  private static final int _METADATA = 1 << 6;
  private static final int _PARAGRAPH = 1 << 7;

  // text
  private static final int _REGULAR = 1 << 8;
  //private static final int _MONOSPACE = 1 << 9;

  private int beginIndexText = Integer.MAX_VALUE;

  private int[] code;

  private int codeCounter;

  private int codeIndex;

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

      state = switch (symbol) {
        case Symbol.EOF -> parseEof(nextSymbol());
        case Symbol.LF -> parseLf(nextSymbol());
        case Symbol.REGULAR -> parseRegular(nextSymbol());
        case Symbol.TITLE -> parseTitle(nextSymbol());
        default -> throw new UnsupportedOperationException("Implement me :: symbol=" + symbol);
      };
    }

    if (state != _EOF) {
      throw new UnsupportedOperationException("Implement me :: state=" + state);
    }

    codeCounter = 0;
  }

  private int parseEof(int value) {
    switch (state) {
      case _DOCUMENT | _TITLE | _REGULAR -> {
        consumeText(value);

        addCode(Code.END_TITLE);
        addCode(Code.END_DOCUMENT);
      }
      case _PREAMBLE | _PARAGRAPH | _REGULAR | _MULTILINE -> {
        consumeText(value);

        addCode(Code.END_PARAGRAPH);
        addCode(Code.END_PREAMBLE);
        addCode(Code.END_DOCUMENT);
      }
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    }

    return _EOF;
  }

  private int parseLf(int value) {
    return switch (state) {
      case _DOCUMENT | _TITLE | _REGULAR -> {
        consumeText(value);

        addCode(Code.END_TITLE);

        yield _MAYBE | _DOCUMENT | _METADATA;
      }
      case _MAYBE | _DOCUMENT | _METADATA -> _MAYBE | _PREAMBLE;
      case _PREAMBLE | _PARAGRAPH | _REGULAR -> _PREAMBLE | _PARAGRAPH | _REGULAR | _MULTILINE;
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int parseRegular(int value) {
    return switch (state) {
      case _DOCUMENT | _TITLE -> {
        beginIndexText = value;

        yield _DOCUMENT | _TITLE | _REGULAR;
      }
      case _MAYBE | _PREAMBLE -> {
        beginIndexText = value;

        addCode(Code.START_PREAMBLE);
        addCode(Code.START_PARAGRAPH);

        yield _PREAMBLE | _PARAGRAPH | _REGULAR;
      }
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int parseTitle(int value) {
    return switch (state) {
      case _START -> {
        var level = value;

        if (level == 1) {
          addCode(Code.START_DOCUMENT);
          addCode(Code.START_TITLE);
          addCode(level);

          yield _DOCUMENT | _TITLE;
        } else {
          throw new UnsupportedOperationException("Implement me :: start section?");
        }
      }
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

}