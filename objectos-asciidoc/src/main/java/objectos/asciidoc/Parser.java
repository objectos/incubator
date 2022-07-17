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

    static final int PARAGRAPH = -4;

    static final int PREAMBLE = -5;
  }

  static class Text {
    static final int REGULAR = -1;
  }

  private int[] code;

  private int codeCounter;

  private int codeIndex;

  private int[] context;

  private int contextIndex = -1;

  @SuppressWarnings("unused")
  private int line;

  private final List<String> strings = new GrowableList<>();

  private int[] text;

  private int textIndex = -1;

  Parser() {
    code = new int[1024];

    context = new int[64];

    text = new int[32];
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

    strings.clear();

    textIndex = -1;

    while (hasSymbol()) {
      var symbol = nextSymbol();
      var value = nextSymbol();

      switch (symbol) {
        case Symbol.EMPTY -> parseEmpty(value);
        case Symbol.EOF -> parseEof(value);
        case Symbol.EOL -> parseEol(value);
        case Symbol.EQUALS -> parseEquals(value);
        case Symbol.LINE -> parseLine(value);
        case Symbol.TEXT -> parseText(value);
        default -> throw new UnsupportedOperationException("Implement me :: symbol=" + symbol);
      }
    }

    while (hasCtx()) {
      endContext();
    }

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

  private void addStringIfNotEmpty(int beginIndex, int endIndex) {
    if (beginIndex < endIndex) {
      var s = source(beginIndex, endIndex);

      addCode(Code.TEXT);
      addCode(strings.size());

      strings.add(s);
    }
  }

  private void consumeText(int value) {
    while (hasText()) {
      int text = popText();

      switch (text) {
        case Text.REGULAR -> {
          var beginIndex = popText();
          var endIndex = value;

          addStringIfNotEmpty(beginIndex, endIndex);
        }
        default -> throw new UnsupportedOperationException("Implement me :: text=" + text);
      }
    }
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

  private boolean hasCtx() { return contextIndex >= 0; }

  private boolean hasText() { return textIndex >= 0; }

  private void parseEmpty(int value) {
    int ctx = popCtx();

    switch (ctx) {
      case Context.DOCUMENT_METADATA -> {
        pushCtx(Context.PREAMBLE);
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

    while (hasCtx()) {
      int ctx = popCtx();

      addCode(
        switch (ctx) {
          case Context.DOCUMENT -> Code.END_DOCUMENT;
          case Context.DOCUMENT_TITLE -> Code.END_TITLE;
          case Context.PARAGRAPH -> Code.END_PARAGRAPH;
          case Context.PREAMBLE -> Code.END_PREAMBLE;
          default -> throw new UnsupportedOperationException("Implement me :: ctx=" + ctx);
        }
      );
    }
  }

  private void parseEol(int value) {
    int ctx = popCtx();

    switch (ctx) {
      case Context.DOCUMENT_TITLE -> {
        consumeText(value);

        addCode(Code.END_TITLE);

        pushCtx(Context.DOCUMENT_METADATA);
      }
      default -> pushCtx(ctx);
    }
  }

  private void parseEquals(int value) {
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

  private void parseLine(int value) {
    line++;

    if (!hasCtx()) {
      addCode(Code.START_DOCUMENT);
      pushCtx(Context.DOCUMENT);

      return;
    }

    int ctx = peekCtx();

    switch (ctx) {
      case Context.PREAMBLE -> {
        addCode(Code.START_PREAMBLE);
        addCode(Code.START_PARAGRAPH);
        pushCtx(Context.PARAGRAPH);
      }
      default -> throw new UnsupportedOperationException("Implement me :: context=" + ctx);
    }
  }

  private void parseText(int value) {
    pushText(value);
    pushText(Text.REGULAR);
  }

  private int peekCtx() { return context[contextIndex]; }

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