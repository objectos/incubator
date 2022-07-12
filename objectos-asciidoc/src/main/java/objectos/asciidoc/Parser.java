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

import java.util.List;
import objectos.lang.Check;
import objectos.util.GrowableList;
import objectos.util.IntArrays;

class Parser {

  static final int TEXT = 1;
  static final int TITLE = 2;

  private static final int _START = 1;

  private static final int _STOP = 0;

  private static final int _TEXT = 2;

  private int[] code;

  private int codeCounter;

  private int codeIndex;

  private String source;

  private int sourceIndex;

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

  final void parse(String source) {
    Check.state(
      state == _STOP,

      """
      Concurrent parsing is not supported.

      It seems a previous AsciiDoc document parsing:

      - is currently running; or
      - finished abruptly (most likely due to a bug in this parser, sorry...).
      """);

    codeIndex = 0;

    this.source = source;

    sourceIndex = 0;

    state = _START;

    strings.clear();

    while (state != _STOP) {
      state = execute(state);
    }
  }

  final String string(int index) {
    return strings.get(index);
  }

  private void addCode(int value) {
    code = IntArrays.copyIfNecessary(code, codeIndex);

    code[codeIndex++] = value;
  }

  private void addText(int beginIndex, int endIndex) {
    var s = source.substring(beginIndex, endIndex);

    addCode(TEXT); // code
    addCode(strings.size()); // string index

    strings.add(s);
  }

  private int execute(int state) {
    return switch (state) {
      case _START -> executeStart();
      case _TEXT -> executeText();
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int executeFinally() {
    codeCounter = 0;

    source = null;

    return _STOP;
  }

  private int executeStart() {
    if (!hasRemaining()) {
      return executeFinally();
    }

    char c = nextChar();

    return switch (c) {
      case '=' -> executeTitle();
      default -> throw new UnsupportedOperationException("Implement me :: char=" + c);
    };
  }

  private int executeText() {
    int startIndex = sourceIndex;
    int endIndex = startIndex;

    while (hasRemaining()) {
      char c = nextChar();

      if (isInlineStart(c)) {
        throw new UnsupportedOperationException("Implement me");
      }

      endIndex = sourceIndex;
    }

    if (endIndex > startIndex) {
      addText(startIndex, endIndex);

      return _STOP;
    }

    throw new UnsupportedOperationException("Implement me");
  }

  private int executeTitle() {
    int level = 1;

    var found = false;

    while (hasRemaining()) {
      char c = nextChar();

      if (c == ' ') {
        found = true;

        break;
      }

      throw new UnsupportedOperationException("Implement me :: not space char");
    }

    if (!found) {
      throw new UnsupportedOperationException("Implement me :: not found");
    }

    if (state == _START && level == 1) {
      addCode(TITLE);

      return _TEXT;
    }

    throw new UnsupportedOperationException("Implement me :: not doctitle");
  }

  private boolean hasRemaining() {
    return sourceIndex < source.length();
  }

  private boolean isInlineStart(char c) {
    return false;
  }

  private char nextChar() {
    return source.charAt(sourceIndex++);
  }

}