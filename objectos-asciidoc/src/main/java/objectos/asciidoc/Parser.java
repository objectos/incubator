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

class Parser {

  static class Code {
    static final int END_DOCUMENT = 1;

    static final int END_PARAGRAPH = 2;

    static final int END_PREAMBLE = 3;

    static final int END_TITLE = 4;

    static final int START_DOCUMENT = 5;

    static final int START_PARAGRAPH = 6;

    static final int START_PREAMBLE = 7;

    static final int START_TITLE = 8;

    static final int TEXT = 9;
  }

  static class Context {
    static final int DOCUMENT = 1;

    static final int PARAGRAPH = 2;

    static final int PREAMBLE = 3;

    static final int TITLE = 4;
  }

  private static final int _FINALLY = 1;

  private static final int _START = 2;

  private static final int _START_CONTEXT = 3;

  private static final int _STOP = 0;

  private static final int _TEXT = 4;

  private static final int _TEXT_CONSUME = 5;

  private static final int _TEXT_NL = 6;

  private static final int _TEXT_RESULT = 7;

  private int[] code;

  private int codeCounter;

  private int codeIndex;

  private int[] context;

  private int contextIndex;

  private String source;

  private int sourceIndex;

  private int state;

  private final List<String> strings = new GrowableList<>();

  private int textAux;

  Parser() {
    code = new int[1024];

    context = new int[64];
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

    contextIndex = -1;

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

  final int[] toCode() {
    return Arrays.copyOf(code, codeIndex);
  }

  private void addCode(int value) {
    code = IntArrays.copyIfNecessary(code, codeIndex);

    code[codeIndex++] = value;
  }

  private void addText(int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return;
    }

    var s = source.substring(beginIndex, endIndex);

    addCode(Code.TEXT); // code
    addCode(strings.size()); // string index

    strings.add(s);
  }

  private int endContext() {
    int ctx = popCtx();

    addCode(
      switch (ctx) {
        case Context.DOCUMENT -> Code.END_DOCUMENT;
        case Context.PARAGRAPH -> Code.END_PARAGRAPH;
        case Context.PREAMBLE -> Code.END_PREAMBLE;
        case Context.TITLE -> Code.END_TITLE;
        default -> throw new UnsupportedOperationException("Implement me :: ctx=" + ctx);
      }
    );

    return ctx;
  }

  private int execute(int state) {
    return switch (state) {
      case _FINALLY -> executeFinally();
      case _START -> executeStart();
      case _START_CONTEXT -> executeStartContext();
      case _TEXT -> executeText();
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int executeFinally() {
    while (hasCtx()) {
      endContext();
    }

    codeCounter = 0;

    source = null;

    return _STOP;
  }

  private int executeMaybeTitle() {
    //int beginIndex = sourceIndex;

    int level = 0;

    var title = false;

    outer: while (hasRemaining()) {
      char c = nextChar();

      switch (c) {
        case ' ':
          title = true;

          break outer;

        case '=':
          level++;

          continue outer;

        default:
          throw new UnsupportedOperationException("Implement me :: to text block");
      }
    }

    if (!title) {
      throw new UnsupportedOperationException("Implement me :: not found (eof?)");
    }

    if (state == _START && level == 1) {
      return executeTitle(level);
    }

    throw new UnsupportedOperationException("Implement me :: not doctitle");
  }

  private int executeParagraph() {
    addCode(Code.START_PARAGRAPH);

    pushCtx(Context.PARAGRAPH);

    return _TEXT;
  }

  private int executeStart() {
    addCode(Code.START_DOCUMENT);

    pushCtx(Context.DOCUMENT);

    if (!hasRemaining()) {
      return executeFinally();
    }

    char c = peekChar();

    return executeStart0(c);
  }

  private int executeStart0(char c) {
    return switch (c) {
      case '=' -> executeMaybeTitle();
      default -> executeParagraph();
    };
  }

  private int executeStartContext() {
    if (!hasRemaining()) {
      return executeFinally();
    }

    var found = false;

    var c = '\0';

    loop: do {
      c = peekChar();

      switch (c) {
        case '\r':
        case '\n':
          sourceIndex++;

          continue;
        default:
          found = true;

          break loop;
      }
    } while (hasRemaining());

    if (!found) {
      throw new UnsupportedOperationException("Implement me :: not found");
    }

    int ctx = peekCtx();

    switch (ctx) {
      case Context.DOCUMENT -> {
        addCode(Code.START_PREAMBLE);
        pushCtx(Context.PREAMBLE);
      }
      default -> throw new UnsupportedOperationException("Implement me :: ctx=" + ctx);
    }

    return executeStart0(c);
  }

  private int executeText() {
    while (state != _TEXT_RESULT) {
      state = switch (state) {
        case _TEXT -> executeTextStart();
        case _TEXT_CONSUME -> executeTextConsume();
        case _TEXT_NL -> executeTextNewLine();
        default -> throw new UnsupportedOperationException("Implement me :: text state=" + state);
      };
    }

    return textAux;
  }

  private int executeTextConsume() {
    if (hasRemaining()) {
      var c = nextChar();

      return switch (c) {
        case '\n' -> _TEXT_NL;
        default -> _TEXT_CONSUME;
      };
    } else {
      return toTextResult(_FINALLY);
    }
  }

  private int executeTextNewLine() {
    var ctx = peekCtx();

    if (ctx == Context.TITLE) {
      return toTextResult(_START_CONTEXT);
    } else {
      return _TEXT_CONSUME;
    }
  }

  private int executeTextStart() {
    textAux = sourceIndex;

    return executeTextConsume();
  }

  private int executeTitle(int level) {
    addCode(Code.START_TITLE);
    addCode(level);
    pushCtx(Context.TITLE);

    return _TEXT;
  }

  private boolean hasCtx() {
    return contextIndex >= 0;
  }

  private boolean hasRemaining() {
    return sourceIndex < source.length();
  }

  private char nextChar() {
    return source.charAt(sourceIndex++);
  }

  private char peekChar() {
    return source.charAt(sourceIndex);
  }

  private int peekCtx() {
    return context[contextIndex];
  }

  private int popCtx() {
    return context[contextIndex--];
  }

  private void pushCtx(int value) {
    contextIndex++;

    context = IntArrays.copyIfNecessary(context, contextIndex);

    context[contextIndex] = value;
  }

  private int toTextResult(int result) {
    addText(textAux, sourceIndex);

    endContext();

    textAux = result;

    return _TEXT_RESULT;
  }

}