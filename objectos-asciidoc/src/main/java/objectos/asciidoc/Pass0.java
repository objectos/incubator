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

class Pass0 implements Pass1.Source, Pass2.Source {

  private static final int EOF = 0;

  private static final int LINE_START = 1;

  private static final int LINE_START_LIKE = 2;

  private static final int HEADING_START = 3;

  private static final int HEADING = 4;

  private static final int BLOB = 5;

  private static final int SPACE_LIKE = 6;

  private static final int BOLD_OR_LIST = 7;

  private static final int BOLD_START = 8;
  private static final int BOLD_END = 9;

  private static final int ITALIC_START = 10;
  private static final int ITALIC_END = 11;

  private static final int MONO_START = 12;
  private static final int MONO_END = 13;

  private static final int ATTR_NAME = 14;
  private static final int ATTR_LIST_END = 15;

  private static final int LISTING_BLOCK = 16;

  private static final int LIST = 17;

  private String source;

  private int sourceIndex;

  private int state;

  private int[] token;

  private int tokenCursor;

  private int tokenIndex;

  private int counter;

  private int lineStart;

  private int blobStart;

  private int attributeNameStart;

  Pass0() {
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
      """
    );

    this.source = source;

    sourceIndex = 0;

    tokenIndex = 0;

    state = LINE_START;

    while (state != EOF) {
      state = state(state);
    }

    tokenCursor = 0;
  }

  @Override
  public final boolean hasToken() { return tokenCursor < tokenIndex; }

  @Override
  public final int nextToken() { return token[tokenCursor++]; }

  @Override
  public final int token(int index) { return token[index]; }

  @Override
  public final int tokenCursor() { return tokenCursor; }

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

  private void add(int s0, int s1, int s2) {
    token = IntArrays.copyIfNecessary(token, tokenIndex + 2);

    token[tokenIndex++] = s0;
    token[tokenIndex++] = s1;
    token[tokenIndex++] = s2;
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

  private void add(int s0, int s1, int s2, int s3, int s4, int s5, int s6) {
    token = IntArrays.copyIfNecessary(token, tokenIndex + 6);

    token[tokenIndex++] = s0;
    token[tokenIndex++] = s1;
    token[tokenIndex++] = s2;
    token[tokenIndex++] = s3;
    token[tokenIndex++] = s4;
    token[tokenIndex++] = s5;
    token[tokenIndex++] = s6;
  }

  private int advance(int state) {
    sourceIndex++;

    return state;
  }

  private boolean hasChar() { return sourceIndex < source.length(); }

  private boolean isWord(char c) {
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

  private char peek() { return source.charAt(sourceIndex); }

  private int rollbackAttributes() {
    sourceIndex = lineStart;
    sourceIndex++; // skips initial '['

    tokenIndex = tokenCursor;

    return BLOB;
  }

  private int state(int state) {
    return switch (state) {
      case LINE_START -> stateLineStart();

      case LINE_START_LIKE -> stateLineStartLike();

      case HEADING_START -> stateHeadingStart();

      case HEADING -> stateHeading();

      case BLOB -> stateBlob();

      case SPACE_LIKE -> stateSpaceLike();

      case BOLD_OR_LIST -> stateBoldOrList();

      case BOLD_START -> stateBoldStart();

      case BOLD_END -> stateBoldEnd();

      case ITALIC_START -> stateItalicStart();

      case ITALIC_END -> stateItalicEnd();

      case MONO_START -> stateMonoStart();

      case MONO_END -> stateMonoEnd();

      case ATTR_LIST_END -> stateAttrListEnd();

      case ATTR_NAME -> stateAttrName();

      case LISTING_BLOCK -> stateListingBlock();

      default -> uoe();
    };
  }

  private int stateAttrListEnd() {
    if (!hasChar()) {
      add(
        Token.ATTR_LIST_END,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    return switch (peek()) {
      case '\n' -> {
        add(
          Token.ATTR_LIST_END,
          Token.LINE_END, Token.LF
        );

        yield advance(LINE_START);
      }

      case ' ', '\t', '\f', '\u000B' -> advance(ATTR_LIST_END);

      default -> rollbackAttributes();
    };
  }

  private int stateAttrName() {
    if (!hasChar()) {
      return rollbackAttributes();
    }

    return switch (peek()) {
      case '\n' -> rollbackAttributes();

      case ']' -> {
        add(Token.ATTR_POS, attributeNameStart, sourceIndex);

        yield advance(ATTR_LIST_END);
      }

      default -> advance(state);
    };
  }

  private int stateBlob() {
    if (!hasChar()) {
      add(
        Token.BLOB, blobStart, sourceIndex,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    return stateBlob0();
  }

  private int stateBlob0() {
    return switch (peek()) {
      case '\n' -> {
        add(
          Token.BLOB, blobStart, sourceIndex,
          Token.LINE_END, Token.LF
        );

        yield advance(LINE_START);
      }

      case ' ' -> advance(SPACE_LIKE);

      case '*' -> advance(BOLD_END);

      case ']' -> advance(ATTR_LIST_END);

      case '_' -> advance(ITALIC_END);

      case '`' -> advance(MONO_END);

      default -> advance(BLOB);
    };
  }

  private int stateBoldEnd() {
    var endIndex = sourceIndex - 1;

    if (!hasChar()) {
      add(
        Token.BLOB, blobStart, endIndex,
        Token.BOLD_END, endIndex,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    var c = peek();

    if (isWord(c)) {
      return advance(BLOB);
    }

    add(
      Token.BLOB, blobStart, endIndex,
      Token.BOLD_END, endIndex
    );

    blobStart = sourceIndex;

    return switch (c) {
      case '\n' -> {
        add(Token.LINE_END, Token.LF);

        yield advance(LINE_START);
      }

      case ' ', '\t', '\f', '\u000B' -> advance(SPACE_LIKE);

      default -> advance(BLOB);
    };
  }

  private int stateBoldOrList() {
    if (!hasChar()) {
      add(
        Token.BLOB, blobStart, sourceIndex,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    return switch (peek()) {
      case '\n' -> {
        add(
          Token.BLOB, blobStart, sourceIndex,
          Token.LINE_END, Token.LF
        );

        yield advance(LINE_START);
      }

      case ' ', '\t', '\f', '\u000B' -> advance(LIST);

      default -> {
        var endIndex = sourceIndex - 1;

        if (blobStart != lineStart) {
          add(Token.BLOB, blobStart, endIndex);
        }

        add(Token.BOLD_START, endIndex);

        blobStart = sourceIndex;

        yield advance(BLOB);
      }
    };
  }

  private int stateBoldStart() {
    if (!hasChar()) {
      add(
        Token.BLOB, blobStart, sourceIndex,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    return switch (peek()) {
      case '\n' -> {
        add(
          Token.BLOB, blobStart, sourceIndex,
          Token.LINE_END, Token.LF
        );

        yield advance(LINE_START);
      }

      case ' ', '\t', '\f', '\u000B' -> advance(SPACE_LIKE);

      default -> {
        var endIndex = sourceIndex - 1;

        if (blobStart != lineStart) {
          add(Token.BLOB, blobStart, endIndex);
        }

        add(Token.BOLD_START, endIndex);

        blobStart = sourceIndex;

        yield advance(BLOB);
      }
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

        yield LINE_START_LIKE;
      }
    };
  }

  private int stateHeadingStart() {
    if (!hasChar()) {
      throw new UnsupportedOperationException("Implement me :: not H[1-6]");
    }

    var c = peek();

    return switch (c) {
      case '=' -> {
        counter++;

        yield advance(state);
      }

      case ' ' -> advance(HEADING);

      default -> {
        blobStart = lineStart;

        yield advance(BLOB);
      }
    };
  }

  private int stateItalicEnd() {
    var endIndex = sourceIndex - 1;

    if (!hasChar()) {
      add(
        Token.BLOB, blobStart, endIndex,
        Token.ITALIC_END, endIndex,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    var c = peek();

    if (isWord(c)) {
      return advance(BLOB);
    }

    add(
      Token.BLOB, blobStart, endIndex,
      Token.ITALIC_END, endIndex
    );

    blobStart = sourceIndex;

    return switch (c) {
      case '\n' -> {
        add(Token.LINE_END, Token.LF);

        yield advance(LINE_START);
      }

      case ' ', '\t', '\f', '\u000B' -> advance(SPACE_LIKE);

      default -> advance(BLOB);
    };
  }

  private int stateItalicStart() {
    if (!hasChar()) {
      add(
        Token.BLOB, blobStart, sourceIndex,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    return switch (peek()) {
      case '\n' -> {
        add(
          Token.BLOB, blobStart, sourceIndex,
          Token.LINE_END, Token.LF
        );

        yield advance(LINE_START);
      }

      case ' ', '\t', '\f', '\u000B' -> advance(SPACE_LIKE);

      default -> {
        var endIndex = sourceIndex - 1;

        if (blobStart != lineStart) {
          add(Token.BLOB, blobStart, endIndex);
        }

        add(Token.ITALIC_START, endIndex);

        blobStart = sourceIndex;

        yield advance(BLOB);
      }
    };
  }

  private int stateLineStart() {
    add(Token.LINE_START);

    if (!hasChar()) {
      add(Token.LINE_END, Token.EOF);

      return EOF;
    }

    lineStart = blobStart = sourceIndex;

    return switch (peek()) {
      case '\n' -> {
        add(Token.LINE_END, Token.LF);

        yield advance(state);
      }

      case '-' -> {
        counter = 1;

        yield advance(LISTING_BLOCK);
      }

      case '=' -> {
        counter = 1;

        yield advance(HEADING_START);
      }

      case '*' -> advance(BOLD_OR_LIST);

      case '[' -> {
        attributeNameStart = sourceIndex + 1;

        tokenCursor = tokenIndex;

        add(Token.ATTR_LIST_START);

        yield advance(ATTR_NAME);
      }

      case '_' -> advance(ITALIC_START);

      case '`' -> advance(MONO_START);

      default -> advance(BLOB);
    };
  }

  private int stateLineStartLike() {
    if (!hasChar()) {
      add(Token.LINE_END, Token.EOF);

      return EOF;
    }

    blobStart = sourceIndex;

    return stateBlob0();
  }

  private int stateListingBlock() {
    if (!hasChar()) {
      if (counter >= 4) {
        add(
          Token.LISTING_BLOCK_DELIM, counter,
          Token.LINE_END, Token.EOF
        );
      } else {
        add(
          Token.BLOB, blobStart, sourceIndex,
          Token.LINE_END, Token.EOF
        );
      }

      return EOF;
    }

    return switch (peek()) {
      case '\n' -> {
        if (counter >= 4) {
          add(Token.LISTING_BLOCK_DELIM, counter, Token.LINE_END, Token.LF);
        } else {
          add(Token.LINE_END, Token.LF);
        }

        yield advance(LINE_START);
      }

      case ' ', '\t', '\f', '\u000B' -> {
        var next = counter >= 4 ? state : BLOB;

        yield next;
      }

      case '-' -> {
        counter++;

        yield advance(state);
      }

      default -> advance(BLOB);
    };
  }

  private int stateMonoEnd() {
    var endIndex = sourceIndex - 1;

    if (!hasChar()) {
      add(
        Token.BLOB, blobStart, endIndex,
        Token.MONO_END, endIndex,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    var c = peek();

    if (isWord(c)) {
      return advance(BLOB);
    }

    add(
      Token.BLOB, blobStart, endIndex,
      Token.MONO_END, endIndex
    );

    blobStart = sourceIndex;

    return switch (c) {
      case '\n' -> {
        add(Token.LINE_END, Token.LF);

        yield advance(LINE_START);
      }

      case ' ', '\t', '\f', '\u000B' -> advance(SPACE_LIKE);

      default -> advance(BLOB);
    };
  }

  private int stateMonoStart() {
    if (!hasChar()) {
      add(
        Token.BLOB, blobStart, sourceIndex,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    return switch (peek()) {
      case '\n' -> {
        add(
          Token.BLOB, blobStart, sourceIndex,
          Token.LINE_END, Token.LF
        );

        yield advance(LINE_START);
      }

      case ' ', '\t', '\f', '\u000B' -> advance(SPACE_LIKE);

      default -> {
        var endIndex = sourceIndex - 1;

        if (blobStart != lineStart) {
          add(Token.BLOB, blobStart, endIndex);
        }

        add(Token.MONO_START, endIndex);

        blobStart = sourceIndex;

        yield advance(BLOB);
      }
    };
  }

  private int stateSpaceLike() {
    if (!hasChar()) {
      add(
        Token.BLOB, blobStart, sourceIndex,
        Token.LINE_END, Token.EOF
      );

      return EOF;
    }

    return switch (peek()) {
      case '\n' -> {
        add(
          Token.BLOB, blobStart, sourceIndex,
          Token.LINE_END, Token.LF
        );

        yield advance(LINE_START);
      }

      case ' ' -> advance(state);

      case '*' -> advance(BOLD_START);

      case '_' -> advance(ITALIC_START);

      case '`' -> advance(MONO_START);

      default -> advance(BLOB);
    };
  }

  private int uoe() {
    throw new UnsupportedOperationException("Implement me :: state=" + state);
  }

}