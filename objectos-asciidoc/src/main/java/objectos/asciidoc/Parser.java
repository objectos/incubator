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
    static final int DOCUMENT = 1;

    static final int PARAGRAPH = 2;

    static final int PREAMBLE = 3;
  }

  private static class Text {
    static final int PARAGRAPH = -1;

    static final int TITLE = -2;
  }

  private static final int _EOF = 0;

  private static final int _FINALLY = 1;

  private static final int _START_CONTEXT = 2;

  private static final int _START_DOCUMENT = 3;

  private static final int _START_LINE = 4;

  private static final int _TEXT = 5;

  private int[] code;

  private int codeCounter;

  private int codeIndex;

  private int[] context;

  private int contextIndex;

  private String source;

  private int sourceIndex;

  private int state;

  private final List<String> strings = new GrowableList<>();

  private int[] text;

  private int textIndex;

  Parser() {
    code = new int[1024];

    context = new int[64];

    text = new int[64];
  }

  void addString() {
    var beginIndex = popText();

    if (beginIndex < sourceIndex) {
      var s = source.substring(beginIndex, sourceIndex);

      addCode(Code.TEXT);
      addCode(strings.size());

      strings.add(s);
    }
  }

  final boolean hasCode() {
    return codeCounter < codeIndex;
  }

  final int nextCode() {
    return code[codeCounter++];
  }

  final void parse(String source) {
    Check.state(
      state == _EOF,

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

    state = _START_DOCUMENT;

    strings.clear();

    textIndex = -1;

    while (state != _EOF) {
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

  private int endContext() {
    int ctx = popCtx();

    addCode(
      switch (ctx) {
        case Context.DOCUMENT -> Code.END_DOCUMENT;
        case Context.PARAGRAPH -> Code.END_PARAGRAPH;
        case Context.PREAMBLE -> Code.END_PREAMBLE;
        default -> throw new UnsupportedOperationException("Implement me :: ctx=" + ctx);
      }
    );

    return ctx;
  }

  private int execute(int state) {
    return switch (state) {
      case _FINALLY -> executeFinally();
      case _START_CONTEXT -> executeStartContext();
      case _START_DOCUMENT -> executeStartDocument();
      case _START_LINE -> executeStartLine();
      case _TEXT -> executeText();
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int executeEndParagraph(int status) {
    popText(); // Text.PARAGRAPH

    addString();

    addCode(Code.END_PARAGRAPH);

    popCtx();

    return status;
  }

  private int executeEndTitle(int status) {
    popText(); // Text.TITLE

    addString();

    addCode(Code.END_TITLE);

    return status;
  }

  private int executeFinally() {
    if (hasText()) {
      executeTextEof();
    }

    while (hasCtx()) {
      endContext();
    }

    codeCounter = 0;

    source = null;

    return _EOF;
  }

  private int executeMaybeTitle(char symbol, int level) {
    if (!hasChar()) {
      throw new UnsupportedOperationException("Implement me :: single '='");
    }

    var c = peekChar();

    if (c == ' ') {
      nextChar();

      return executeStartTitle(level);
    } else if (c == symbol) {
      nextChar();

      return executeMaybeTitle(symbol, level + 1);
    } else {
      throw new UnsupportedOperationException("Implement me :: text block");
    }
  }

  private int executeStart0(char c) {
    return switch (c) {
      case '=' -> executeMaybeTitle(c, 1);
      default -> executeStartParagraph();
    };
  }

  private int executeStartContext() {
    if (!hasChar()) {
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
    } while (hasChar());

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

  private int executeStartDocument() {
    addCode(Code.START_DOCUMENT);
    pushCtx(Context.DOCUMENT);

    if (!hasChar()) {
      return executeFinally();
    }

    return _START_LINE;
  }

  private int executeStartLine() {
    //lineStart = sourceIndex;

    if (!hasChar()) {
      return _FINALLY;
    }

    char c = nextChar();

    return switch (c) {
      case '=' -> executeMaybeTitle(c, 1);
      default -> executeStartParagraph();
    };
  }

  private int executeStartParagraph() {
    int ctx = peekCtx();

    switch (ctx) {
      case Context.PARAGRAPH:
        break;
      case Context.DOCUMENT:
        addCode(Code.START_PREAMBLE);
        pushCtx(Context.PREAMBLE);
        // fall-through
      default:
        addCode(Code.START_PARAGRAPH);
        pushCtx(Context.PARAGRAPH);

        pushText(sourceIndex - 1);
        pushText(Text.PARAGRAPH);

        break;
    }

    return _TEXT;
  }

  private int executeStartTitle(int level) {
    int ctx = peekCtx();

    if (ctx == Context.DOCUMENT) {
      if (level != 1) {
        throw new UnsupportedOperationException("Implement me :: doctitle level != 1");
      }

      addCode(Code.START_TITLE);
      addCode(level);

      pushText(sourceIndex);
      pushText(Text.TITLE);

      return _TEXT;
    } else {
      throw new UnsupportedOperationException("Implement me :: section title?");
    }
  }

  private int executeText() {
    if (!hasChar()) {
      return executeTextEof();
    }

    var c = nextChar();

    return switch (c) {
      case '\n' -> executeTextLF();
      default -> _TEXT;
    };
  }

  private int executeTextEof() {
    int text = peekText();

    return switch (text) {
      case Text.PARAGRAPH -> executeEndParagraph(_FINALLY);
      case Text.TITLE -> executeEndTitle(_FINALLY);
      default -> throw new UnsupportedOperationException("Implement me :: text=" + text);
    };
  }

  private int executeTextLF() {
    int text = peekText();

    return switch (text) {
      case Text.PARAGRAPH -> _START_LINE;
      case Text.TITLE -> executeEndTitle(_START_LINE);
      default -> throw new UnsupportedOperationException("Implement me :: text=" + text);
    };
  }

  private boolean hasChar() { return sourceIndex < source.length(); }

  private boolean hasCtx() { return contextIndex >= 0; }

  private boolean hasText() { return textIndex >= 0; }

  private char nextChar() { return source.charAt(sourceIndex++); }

  private char peekChar() { return source.charAt(sourceIndex); }

  private int peekCtx() { return context[contextIndex]; }

  private int peekText() { return text[textIndex]; }

  private int popCtx() { return context[contextIndex--]; }

  private int popText() { return text[textIndex--]; }

  private void pushCtx(int value) {
    contextIndex++;

    context = IntArrays.copyIfNecessary(context, contextIndex);

    context[contextIndex] = value;
  }

  private void pushText(int value) {
    textIndex++;

    text = IntArrays.copyIfNecessary(text, textIndex);

    text[textIndex] = value;
  }

}