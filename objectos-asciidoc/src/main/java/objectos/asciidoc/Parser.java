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

  static class Context {
    static final int DOCUMENT = -1;

    static final int DOCUMENT_TITLE = -2;

    static final int DOCUMENT_METADATA = -3;

    static final int MAYBE_DOCUMENT_METADATA = -4;

    static final int MAYBE_PREAMBLE = -5;

    static final int PARAGRAPH = -6;

    static final int PREAMBLE = -7;
  }

  static class Text {
    static final int _BACKTICK = 1;

    static final int REGULAR = -1;
  }

  private static class State {
    static final int EOF = 0;

    static final int DOC = 1;

    static final int DOCTITLE = 2;

    static final int DOCTITLE_TEXT = 3;
  }

  private int beginIndexText = Integer.MAX_VALUE;

  private int[] code;

  private int codeCounter;

  private int codeIndex;

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
      state == State.EOF,

      """
      Concurrent parsing is not supported.

      It seems a previous AsciiDoc document parsing:

      - is currently running; or
      - finished abruptly (most likely due to a bug in this parser, sorry...).
      """);

    beginIndexText = Integer.MAX_VALUE;

    codeIndex = 0;

    state = State.DOC;

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

  private void consumeText(int endIndex) {
    if (beginIndexText < endIndex) {
      var s = source(beginIndexText, endIndex);

      addCode(Code.TEXT);
      addCode(strings.size());

      strings.add(s);

      beginIndexText = Integer.MAX_VALUE;
    }
  }

  private void parse0() {
    while (hasSymbol()) {
      var symbol = nextSymbol();
      var value = nextSymbol();

      state = switch (symbol) {
        case Symbol.EOF -> parseEof(value);
        case Symbol.TITLE -> parseTitle(value);
        case Symbol.TITLE_LEVEL -> parseTitleLevel(value);
        case Symbol.WORD -> parseWord(value);
        case Symbol.WS -> parseWs(value);
        default -> throw new UnsupportedOperationException("Implement me :: symbol=" + symbol);
      };
    }

    if (state != State.EOF) {
      throw new UnsupportedOperationException("Implement me :: state=" + state);
    }
  }

  private int parseEof(int value) {
    return switch (state) {
      case State.DOCTITLE_TEXT -> {
        consumeText(value);

        addCode(Code.END_TITLE);
        addCode(Code.END_DOCUMENT);

        yield State.EOF;
      }
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int parseTitle(int value) {
    return switch (state) {
      case State.DOC -> {
        addCode(Code.START_DOCUMENT);

        yield state;
      }
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int parseTitleLevel(int value) {
    return switch (state) {
      case State.DOC -> {
        var level = value;

        if (level == 1) {
          addCode(Code.START_TITLE);
          addCode(value); // level

          yield State.DOCTITLE;
        } else {
          throw new UnsupportedOperationException("Implement me :: start section?");
        }
      }
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int parseWord(int value) {
    return switch (state) {
      case State.DOCTITLE -> {
        beginIndexText = value;

        yield State.DOCTITLE_TEXT;
      }
      case State.DOCTITLE_TEXT -> state;
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int parseWs(int value) {
    return switch (state) {
      case State.DOCTITLE_TEXT -> state;
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

}