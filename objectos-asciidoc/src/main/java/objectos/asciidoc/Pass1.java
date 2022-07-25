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

class Pass1 {

  interface Source {
    boolean hasToken();

    int nextToken();

    int tokenCursor();
  }

  private static final int EOF = 0;

  private static final int START = 1 << 0;

  private static final int NEXT = 1 << 1;

  private static final int DOCUMENT = 1 << 2;

  private static final int METADATA = 1 << 3;

  private static final int PREAMBLE = 1 << 4;

  private static final int HEADING = 1 << 5;

  private static final int PARAGRAPH = 1 << 6;

  private int[] code;

  private int codeCursor;

  private int codeIndex;

  private int state;

  private Source source;

  private int tokenIndex;

  private int tokenStart;

  Pass1() {
    code = new int[512];
  }

  public final void execute(Source source) {
    Check.state(
      state == EOF,

      """
      Concurrent pass (1) is not supported.

      It seems a previous AsciiDoc document pass (1):

      - is currently running; or
      - finished abruptly (most likely due to a bug in this component, sorry...).
      """
    );

    this.source = source;

    codeIndex = 0;

    state = START;

    execute0();

    codeCursor = 0;
  }

  final boolean hasCode() {
    return codeCursor < codeIndex;
  }

  final int nextCode() {
    return code[codeCursor++];
  }

  final int[] toCode() {
    return Arrays.copyOf(code, codeIndex);
  }

  private void addCode(int p0) {
    code = IntArrays.copyIfNecessary(code, codeIndex);

    code[codeIndex++] = p0;
  }

  private void addCode(int p0, int p1) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 1);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
  }

  private void addCode(int p0, int p1, int p2, int p3) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 3);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
    code[codeIndex++] = p2;
    code[codeIndex++] = p3;
  }

  private void addCode(int p0, int p1, int p2, int p3, int p4) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 4);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
    code[codeIndex++] = p2;
    code[codeIndex++] = p3;
    code[codeIndex++] = p4;
  }

  private void addCode(int p0, int p1, int p2, int p3, int p4, int p5) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 5);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
    code[codeIndex++] = p2;
    code[codeIndex++] = p3;
    code[codeIndex++] = p4;
    code[codeIndex++] = p5;
  }

  private void execute0() {
    while (hasToken()) {
      tokenIndex = source.tokenCursor();

      var token = nextToken();

      state = switch (token) {
        case Token.LINE_START -> parseLineStart();

        case Token.LINE_END -> parseLineEnd(nextToken());

        case Token.HEADING -> parseHeading(nextToken(), nextToken(), nextToken());

        case Token.BLOB -> parseTokens(nextToken(), nextToken());

        case Token.LF -> parseLineFeed();

        case Token.BOLD_START, Token.BOLD_END -> parseTokens(nextToken());

        case Token.ITALIC_START, Token.ITALIC_END -> parseTokens(nextToken());

        case Token.MONO_START, Token.MONO_END -> parseTokens(nextToken());

        default -> throw new UnsupportedOperationException("Implement me :: token=" + token);
      };
    }

    if (state != EOF) {
      throw new UnsupportedOperationException("Implement me :: state=" + state);
    }
  }

  private boolean hasToken() {
    return source.hasToken();
  }

  private int nextToken() {
    return source.nextToken();
  }

  private int parseHeading(int level, int start, int end) {
    return switch (state) {
      case DOCUMENT | HEADING -> {
        addCode(Code.HEADING_START, level);

        yield DOCUMENT | HEADING | START;
      }

      default -> uoe();
    };
  }

  private int parseLineEnd(int terminator) {
    return switch (terminator) {
      case Token.EOF -> switch (state) {
        case DOCUMENT | HEADING | NEXT -> {
          addCode(
            Code.TOKENS, tokenStart, tokenIndex,
            Code.HEADING_END,
            Code.DOCUMENT_END
          );

          yield EOF;
        }

        case DOCUMENT | METADATA -> {
          addCode(Code.DOCUMENT_END);

          yield EOF;
        }

        case PREAMBLE | PARAGRAPH | START -> {
          addCode(
            Code.TOKENS, tokenStart, tokenIndex,
            Code.PARAGRAPH_END,
            Code.PREAMBLE_END,
            Code.DOCUMENT_END
          );

          yield EOF;
        }

        default -> uoe();
      };

      case Token.LF -> switch (state) {
        case DOCUMENT | HEADING | NEXT -> {
          addCode(
            Code.TOKENS, tokenStart, tokenIndex,
            Code.HEADING_END
          );

          yield DOCUMENT | METADATA;
        }

        case PREAMBLE | PARAGRAPH -> state;

        default -> uoe();
      };

      case DOCUMENT | METADATA -> PREAMBLE;

      default -> uoe();
    };
  }

  private int parseLineFeed() {
    return switch (state) {
      case DOCUMENT | METADATA -> state;

      default -> uoe();
    };
  }

  private int parseLineStart() {
    return switch (state) {
      case START -> {
        addCode(Code.DOCUMENT_START);

        yield DOCUMENT | HEADING;
      }

      case DOCUMENT | METADATA -> state;

      case PREAMBLE | PARAGRAPH -> PREAMBLE | PARAGRAPH | START;

      default -> uoe();
    };
  }

  private int parseTokens(int v0) {
    return parseTokens(v0, 0);
  }

  private int parseTokens(int v0, int v1) {
    return switch (state) {
      case DOCUMENT | HEADING -> {
        tokenStart = tokenIndex;

        addCode(Code.PREAMBLE_START);
        addCode(Code.PARAGRAPH_START);

        yield PREAMBLE | PARAGRAPH;
      }

      case DOCUMENT | HEADING | START -> {
        tokenStart = tokenIndex;

        yield DOCUMENT | HEADING | NEXT;
      }

      case PREAMBLE | PARAGRAPH -> state;

      default -> uoe();
    };
  }

  private int uoe() {
    var s = Integer.toBinaryString(state);

    throw new UnsupportedOperationException("Implement me :: state=" + s);
  }

}