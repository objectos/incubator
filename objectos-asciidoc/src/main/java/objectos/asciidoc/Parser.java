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

class Parser extends Lexer {

  static class Code {
    static final int START_DOCUMENT = -1;
    static final int END_DOCUMENT = -2;

    static final int START_TITLE = -3;
    static final int END_TITLE = -4;

    static final int START_PREAMBLE = -5;
    static final int END_PREAMBLE = -6;

    static final int START_PARAGRAPH = -7;
    static final int END_PARAGRAPH = -8;

    static final int START_MONOSPACE = -9;
    static final int END_MONOSPACE = -10;

    static final int NOOP = -11;

    static final int TEXT = -12;
    static final int TEXT_EOF = -13;
    static final int LF = -14;
  }

  static class Context {
    static final int DOCUMENT = -1;

    static final int PARAGRAPH = -2;

    static final int PREAMBLE = -3;

    static final int TITLE = -4;
  }

  private int[] code;

  private int codeCounter;

  private int codeIndex;

  private int[] context;

  private int contextIndex = -1;

  @SuppressWarnings("unused")
  private int currentLine;

  Parser() {
    code = new int[1024];

    context = new int[32];
  }

  final boolean hasCode() {
    return codeCounter < codeIndex;
  }

  final int nextCode() {
    return code[codeCounter++];
  }

  final void parse() {
    Check.state(
      contextIndex < 0,

      """
      Concurrent parsing is not supported.

      It seems a previous AsciiDoc document parsing:

      - is currently running; or
      - finished abruptly (most likely due to a bug in this parser, sorry...).
      """);

    codeIndex = 0;

    contextIndex = -1;

    pushCtx(Context.DOCUMENT);

    while (hasSymbol()) {
      parse(nextSymbol());
    }

    codeCounter = 0;
  }

  final int[] toCode() {
    return Arrays.copyOf(code, codeIndex);
  }

  private void addCode(int value) {
    code = IntArrays.copyIfNecessary(code, codeIndex);

    code[codeIndex++] = value;
  }

  private boolean hasCtx() { return contextIndex >= 0; }

  private void parse(int symbol) {
    switch (symbol) {
      case Symbol.EOF -> parseEof();
      case Symbol.LINE_NO -> parseLineNo();
      case Symbol.TEXT -> parseText();
      case Symbol.TITLE_EQUALS -> parseTitle();
      default -> throw new UnsupportedOperationException("Implement me :: symbol=" + symbol);
    }
  }

  private void parseEof() {
    while (hasCtx()) {
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
    }
  }

  private void parseLineNo() {
    currentLine = nextSymbol();

    var ctx = peekCtx();

    switch (ctx) {
      case Context.DOCUMENT -> {
        addCode(Code.START_DOCUMENT);
      }
      default -> throw new UnsupportedOperationException("Implement me :: ctx=" + ctx);
    }
  }

  private void parseText() {
    addCode(Code.TEXT);
    addCode(nextSymbol());
  }

  private void parseTitle() {
    int level = nextSymbol();

    int ctx = peekCtx();

    switch (ctx) {
      case Context.DOCUMENT -> {
        addCode(Code.START_TITLE);
        addCode(level);
        pushCtx(Context.TITLE);
      }
      default -> throw new UnsupportedOperationException("Implement me :: ctx=" + ctx);
    }
  }

  // private int peekCode() { return code[codeIndex - 1]; }

  private int peekCtx() { return context[contextIndex]; }

  private int popCtx() { return context[contextIndex--]; }

  private void pushCtx(int value) {
    contextIndex++;

    context = IntArrays.copyIfNecessary(context, contextIndex);

    context[contextIndex] = value;
  }

}