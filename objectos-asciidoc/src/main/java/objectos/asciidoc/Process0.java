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

class Process0 {

  class Token {
    static final int LINE_START = -1;

    static final int LINE_END = -2;

    static final int HEADING = -3;

    static final int EOF = -4;

    static final int WORD = -5;

    static final int SP = -6;
  }

  private static final int EOF = 0;

  private static final int LINE_START = 1;

  private static final int LINE = 2;

  private static final int HEADING_START = 3;

  private static final int HEADING = 4;

  private static final int WORD = 5;

  private String source;

  private int sourceIndex;

  private int state;

  private int[] token;

  private int tokenCounter = Integer.MAX_VALUE;

  private int tokenIndex;

  private int counter;

  private int lineStart;

  private int wordStart;

  Process0() {
    token = new int[512];
  }

  public final void execute(String source) {
    Check.state(
      state == EOF,

      """
      Concurrent process (0) is not supported.

      It seems a previous AsciiDoc document process (0):

      - is currently running; or
      - finished abruptly (most likely due to a bug in this component, sorry...).
      """);

    this.source = source;

    sourceIndex = 0;

    tokenIndex = 0;

    state = LINE_START;

    while (state != EOF) {
      state = state(state);
    }

    tokenCounter = 0;
  }

  final boolean hasToken() {
    return tokenCounter < tokenIndex;
  }

  final boolean isWord(char c) {
    int type = Character.getType(c);

    return switch (type) {
      case Character.LOWERCASE_LETTER:
      case Character.MODIFIER_LETTER:
      case Character.OTHER_LETTER:
      case Character.TITLECASE_LETTER:
      case Character.UPPERCASE_LETTER:

      case Character.NON_SPACING_MARK:
      case Character.COMBINING_SPACING_MARK:
      case Character.ENCLOSING_MARK:

      case Character.DECIMAL_DIGIT_NUMBER:
      case Character.LETTER_NUMBER:
      case Character.OTHER_NUMBER:

      case Character.CONNECTOR_PUNCTUATION:

        yield true;
      default:
        yield false;
    };
  }

  final String source(int beginIndex, int endIndex) {
    return source.substring(beginIndex, endIndex);
  }

  final int[] toToken() {
    return Arrays.copyOf(token, tokenIndex);
  }

  private void add(int s0) {
    token = IntArrays.copyIfNecessary(token, tokenIndex);

    token[tokenIndex++] = s0;
  }

  private void add(int s0, int s1) {
    token = IntArrays.copyIfNecessary(token, tokenIndex + 1);

    token[tokenIndex++] = s0;
    token[tokenIndex++] = s1;
  }

  private void add(int s0, int s1, int s2, int s3) {
    token = IntArrays.copyIfNecessary(token, tokenIndex + 3);

    token[tokenIndex++] = s0;
    token[tokenIndex++] = s1;
    token[tokenIndex++] = s2;
    token[tokenIndex++] = s3;
  }

  private void add(int s0, int s1, int s2, int s3, int s4) {
    token = IntArrays.copyIfNecessary(token, tokenIndex + 4);

    token[tokenIndex++] = s0;
    token[tokenIndex++] = s1;
    token[tokenIndex++] = s2;
    token[tokenIndex++] = s3;
    token[tokenIndex++] = s4;
  }

  private int advance(int state) {
    sourceIndex++;

    return state;
  }

  private boolean hasChar() { return sourceIndex < source.length(); }

  private char peek() { return source.charAt(sourceIndex); }

  private int state(int state) {
    return switch (state) {
      case LINE_START -> stateLineStart();

      case LINE -> stateLine();

      case HEADING_START -> stateHeadingStart();

      case HEADING -> stateHeading();

      case WORD -> stateWord();

      default -> uoe();
    };
  }

  private int stateHeading() {
    if (!hasChar()) {
      throw new UnsupportedOperationException("Implement me :: empty heading|not H[1-6]?");
    }

    return switch (peek()) {
      case ' ' -> advance(state);
      default -> {
        add(Token.HEADING, counter, lineStart, sourceIndex);

        yield LINE;
      }
    };
  }

  private int stateHeadingStart() {
    if (!hasChar()) {
      throw new UnsupportedOperationException("Implement me :: not H[1-6]");
    }

    return switch (peek()) {
      case '=' -> {
        counter++;

        yield advance(state);
      }
      case ' ' -> advance(HEADING);
      default -> uoe();
    };
  }

  private int stateLine() {
    if (!hasChar()) {
      add(Token.LINE_END, Token.EOF);

      return EOF;
    }

    var c = peek();

    if (isWord(c)) {
      wordStart = sourceIndex;

      return advance(WORD);
    }

    throw new UnsupportedOperationException("Implement me");
  }

  private int stateLineStart() {
    add(Token.LINE_START);

    if (!hasChar()) {
      add(Token.LINE_END, Token.EOF);

      return EOF;
    }

    lineStart = sourceIndex;

    return switch (peek()) {
      case '=' -> {
        counter = 1;

        yield advance(HEADING_START);
      }
      default -> uoe();
    };
  }

  private int stateWord() {
    if (!hasChar()) {
      add(
        Token.WORD, wordStart, sourceIndex,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    var c = peek();

    if (isWord(c)) {
      return advance(WORD);
    }

    return switch (c) {
      case ' ' -> {
        add(
          Token.WORD, wordStart, sourceIndex,
          Token.SP
        );

        yield advance(LINE);
      }
      default -> uoe(c);
    };
  }

  private int uoe() {
    throw new UnsupportedOperationException("Implement me :: state=" + state);
  }

  private int uoe(char c) {
    throw new UnsupportedOperationException("Implement me :: state=" + state + "; char=" + c);
  }

}