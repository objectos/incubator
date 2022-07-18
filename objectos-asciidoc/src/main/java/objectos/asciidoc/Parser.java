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

  private int beginIndexText = Integer.MAX_VALUE;

  private int[] code;

  private int codeCounter;

  private int codeIndex;

  private int[] context;

  private int contextIndex = -1;

  @SuppressWarnings("unused")
  private int line;

  private int monospace = Integer.MAX_VALUE;

  private final List<String> strings = new GrowableList<>();

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

  final void parse() {
    Check.state(
      contextIndex < 0,

      """
      Concurrent parsing is not supported.

      It seems a previous AsciiDoc document parsing:

      - is currently running; or
      - finished abruptly (most likely due to a bug in this parser, sorry...).
      """);

    beginIndexText = Integer.MAX_VALUE;

    monospace = Integer.MAX_VALUE;

    codeIndex = 0;

    contextIndex = -1;

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

  private int endContext() {
    int ctx = popCtx();

    switch (ctx) {
      case Context.DOCUMENT -> addCode(Code.END_DOCUMENT);
      case Context.DOCUMENT_TITLE -> addCode(Code.END_TITLE);
      case Context.MAYBE_PREAMBLE -> { /*noop*/ }
      case Context.PARAGRAPH -> addCode(Code.END_PARAGRAPH);
      case Context.PREAMBLE -> addCode(Code.END_PREAMBLE);
      default -> throw new UnsupportedOperationException("Implement me :: ctx=" + ctx);
    }

    return ctx;
  }

  private boolean hasCtx() { return contextIndex >= 0; }

  private void parse0() {
    while (hasSymbol()) {
      var symbol = nextSymbol();
      var value = nextSymbol();

      switch (symbol) {
        case Symbol.BACKTICK -> parseBacktick(value);
        case Symbol.EMPTY -> parseEmpty(value);
        case Symbol.EOF -> parseEof(value);
        case Symbol.EOL -> parseEol(value);
        case Symbol.PARAGRAPH -> parseParagraph(value);
        case Symbol.TITLE -> parseTitle(value);
        case Symbol.TITLE_LEVEL -> parseTitleLevel(value);
        case Symbol.TITLE_TEXT -> parseTitleText(value);
        default -> throw new UnsupportedOperationException("Implement me :: symbol=" + symbol);
      }
    }

    while (hasCtx()) {
      endContext();
    }
  }

  private void parseBacktick(int value) {
    if (monospace == Integer.MAX_VALUE) {
      monospace = value;

      return;
    }

    // TODO: check bold and italic

    consumeText(monospace);

    var beginIndex = monospace + 1;
    var endIndex = value;
    beginIndexText = endIndex + 1;

    if (beginIndex < endIndex) {
      var s = source(beginIndex, value);

      addCode(Code.START_MONOSPACE);
      addCode(Code.TEXT);
      addCode(strings.size());
      addCode(Code.END_MONOSPACE);

      strings.add(s);

      monospace = Integer.MAX_VALUE;
    }
  }

  private void parseEmpty(int value) {
    int ctx = popCtx();

    switch (ctx) {
      case Context.MAYBE_DOCUMENT_METADATA -> {
        pushCtx(Context.MAYBE_PREAMBLE);
      }
      case Context.PARAGRAPH -> {
        consumeText(value);

        addCode(Code.END_PARAGRAPH);
      }
      default -> throw new UnsupportedOperationException("Implement me :: context=" + ctx);
    }
  }

  private void parseEof(int value) {
    consumeText(value);
  }

  private void parseEol(int value) {
    int ctx = popCtx();

    switch (ctx) {
      case Context.DOCUMENT_TITLE -> {
        consumeText(value);

        addCode(Code.END_TITLE);

        pushCtx(Context.MAYBE_DOCUMENT_METADATA);
      }
      default -> pushCtx(ctx);
    }
  }

  private void parseParagraph(int value) {
    line++;

    if (!hasCtx()) {
      addCode(Code.START_DOCUMENT);
      pushCtx(Context.DOCUMENT);
      pushCtx(Context.MAYBE_PREAMBLE);
    }

    int ctx = popCtx();

    switch (ctx) {
      case Context.MAYBE_PREAMBLE -> {
        addCode(Code.START_PREAMBLE);
        pushCtx(Context.PREAMBLE);

        addCode(Code.START_PARAGRAPH);
        pushCtx(Context.PARAGRAPH);

        beginIndexText = value;
      }
      case Context.PARAGRAPH -> {
        pushCtx(ctx);
      }
      default -> throw new UnsupportedOperationException("Implement me :: context=" + ctx);
    }
  }

  private void parseTitle(int value) {
    line++;

    if (!hasCtx()) {
      addCode(Code.START_DOCUMENT);
      pushCtx(Context.DOCUMENT);
    } else {
      throw new UnsupportedOperationException("Implement me :: start section?");
    }
  }

  private void parseTitleLevel(int value) {
    int ctx = peekCtx();

    switch (ctx) {
      case Context.DOCUMENT -> {
        var level = value;

        if (level == 1) {
          addCode(Code.START_TITLE);
          addCode(value); // level
          pushCtx(Context.DOCUMENT_TITLE);
        } else {
          throw new UnsupportedOperationException("Implement me :: start section?");
        }
      }
      default -> throw new UnsupportedOperationException("Implement me :: context=" + ctx);
    }
  }

  private void parseTitleText(int value) {
    beginIndexText = value;
  }

  private int peekCtx() { return context[contextIndex]; }

  private int popCtx() { return context[contextIndex--]; }

  private void pushCtx(int value) {
    contextIndex++;

    context = IntArrays.copyIfNecessary(context, contextIndex);

    context[contextIndex] = value;
  }

}