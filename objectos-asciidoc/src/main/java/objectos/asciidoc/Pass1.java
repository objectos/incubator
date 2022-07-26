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

  private static final int MAYBE = 1 << 0;

  private static final int NL = 1 << 1;

  private static final int DOCUMENT = 1 << 2;

  private static final int METADATA = 1 << 3;

  private static final int PREAMBLE = 1 << 4;

  private static final int SECTION = 1 << 5;

  private static final int HEADING = 1 << 6;

  private static final int PARAGRAPH = 1 << 7;

  private static final int ATTR = 1 << 8;

  private int[] code;

  private int codeCursor;

  private int codeIndex;

  private int[] section;

  private int sectionIndex = -1;

  private int state;

  private Source source;

  private int tokenIndex;

  private int tokenStart;

  Pass1() {
    code = new int[512];

    section = new int[8];
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

    sectionIndex = -1;

    state = MAYBE;

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

  private void add(int p0) {
    code = IntArrays.copyIfNecessary(code, codeIndex);

    code[codeIndex++] = p0;
  }

  private void add(int p0, int p1, int p2) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 2);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
    code[codeIndex++] = p2;
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
    while (hasNext()) {
      tokenIndex = source.tokenCursor();

      var token = next();

      state = switch (token) {
        case Token.LINE_START -> parseLineStart();

        case Token.LINE_END -> parseLineEnd(next());

        case Token.HEADING -> parseHeading(next(), next(), next());

        case Token.BLOB -> parseTokens(next(), next());

        case Token.LF -> parseLineFeed();

        case Token.ATTR_LIST_START -> { add(Code.ATTR_LIST_START); yield state | ATTR; }

        case Token.ATTR_LIST_END -> { add(Code.ATTR_LIST_END); yield state; }

        case Token.ATTR_POS -> { add(Code.ATTR_POS, next(), next()); yield state; }

        case Token.BOLD_START, Token.BOLD_END -> parseTokens(next());

        case Token.ITALIC_START, Token.ITALIC_END -> parseTokens(next());

        case Token.MONO_START, Token.MONO_END -> parseTokens(next());

        default -> throw new UnsupportedOperationException("Implement me :: token=" + token);
      };
    }

    if (state != EOF) {
      throw new UnsupportedOperationException("Implement me :: state=" + state);
    }
  }

  private boolean hasNext() {
    return source.hasToken();
  }

  private boolean hasSection() {
    return sectionIndex >= 0;
  }

  private int next() {
    return source.nextToken();
  }

  private int parseHeading(int level, int start, int end) {
    return switch (state) {
      case MAYBE | DOCUMENT | HEADING -> {
        addCode(Code.HEADING_START, level);

        yield DOCUMENT | HEADING;
      }

      case MAYBE | PREAMBLE -> {
        var section = level - 1;

        pushSection(section);

        addCode(
          Code.SECTION_START, section,
          Code.HEADING_START, level
        );

        yield SECTION | HEADING;
      }

      case PREAMBLE -> {
        var section = level - 1;

        pushSection(section);

        addCode(
          Code.PREAMBLE_END,
          Code.SECTION_START, section,
          Code.HEADING_START, level
        );

        yield SECTION | HEADING;
      }

      case SECTION -> {
        var section = level - 1;

        pushSection(section);

        addCode(
          Code.SECTION_START, section,
          Code.HEADING_START, level
        );

        yield SECTION | HEADING;
      }

      default -> uoe();
    };
  }

  private int parseLineEnd(int terminator) {
    return switch (terminator) {
      case Token.EOF -> switch (state) {
        case DOCUMENT | HEADING -> {
          addCode(
            Code.TOKENS, tokenStart, tokenIndex,
            Code.HEADING_END,
            Code.DOCUMENT_END
          );

          yield EOF;
        }

        case MAYBE | DOCUMENT | METADATA -> {
          add(Code.DOCUMENT_END);

          yield EOF;
        }

        case PREAMBLE | PARAGRAPH | NL -> {
          addCode(
            Code.TOKENS, tokenStart, tokenIndex,
            Code.PARAGRAPH_END,
            Code.PREAMBLE_END,
            Code.DOCUMENT_END
          );

          yield EOF;
        }

        case SECTION | PARAGRAPH | NL -> {
          addCode(
            Code.TOKENS, tokenStart, tokenIndex,
            Code.PARAGRAPH_END
          );

          while (hasSection()) {
            popSection();
            add(Code.SECTION_END);
          }

          add(Code.DOCUMENT_END);

          yield EOF;
        }

        default -> uoe();
      };

      case Token.LF -> switch (state) {
        case MAYBE | DOCUMENT | HEADING | ATTR -> MAYBE | PREAMBLE;

        case DOCUMENT | HEADING -> {
          addCode(
            Code.TOKENS, tokenStart, tokenIndex,
            Code.HEADING_END
          );

          yield MAYBE | DOCUMENT | METADATA;
        }

        case MAYBE | DOCUMENT | METADATA -> MAYBE | PREAMBLE;

        case PREAMBLE | PARAGRAPH -> PREAMBLE | PARAGRAPH | NL;

        case PREAMBLE | PARAGRAPH | NL -> {
          addCode(
            Code.TOKENS, tokenStart, tokenIndex,
            Code.PARAGRAPH_END
          );

          yield PREAMBLE;
        }

        case SECTION -> state;

        case SECTION | HEADING -> {
          addCode(
            Code.TOKENS, tokenStart, tokenIndex,
            Code.HEADING_END
          );

          yield SECTION;
        }

        case SECTION | PARAGRAPH -> SECTION | PARAGRAPH | NL;

        case SECTION | PARAGRAPH | NL -> {
          addCode(
            Code.TOKENS, tokenStart, tokenIndex,
            Code.PARAGRAPH_END
          );

          yield SECTION;
        }

        default -> uoe();
      };

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
      case MAYBE -> {
        add(Code.DOCUMENT_START);

        yield MAYBE | DOCUMENT | HEADING;
      }

      case MAYBE | DOCUMENT | METADATA -> state;

      case DOCUMENT | METADATA -> state;

      case MAYBE | PREAMBLE -> state;

      case PREAMBLE -> state;

      case PREAMBLE | PARAGRAPH -> state;

      case PREAMBLE | PARAGRAPH | NL -> state;

      case SECTION -> state;

      case SECTION | PARAGRAPH | NL -> state;

      default -> uoe();
    };
  }

  private int parseTokens(int v0) {
    return parseTokens(v0, 0);
  }

  private int parseTokens(int v0, int v1) {
    return switch (state) {
      case MAYBE | DOCUMENT | HEADING -> {
        tokenStart = tokenIndex;

        add(Code.PREAMBLE_START);
        add(Code.PARAGRAPH_START);

        yield PREAMBLE | PARAGRAPH;
      }

      case DOCUMENT | HEADING -> {
        tokenStart = tokenIndex;

        yield state;
      }

      case MAYBE | PREAMBLE -> {
        tokenStart = tokenIndex;

        add(Code.PREAMBLE_START);
        add(Code.PARAGRAPH_START);

        yield PREAMBLE | PARAGRAPH;
      }

      case PREAMBLE | PARAGRAPH -> state;

      case SECTION -> {
        tokenStart = tokenIndex;

        add(Code.PARAGRAPH_START);

        yield SECTION | PARAGRAPH;
      }

      case SECTION | HEADING -> {
        tokenStart = tokenIndex;

        yield state;
      }

      default -> uoe();
    };
  }

  private int popSection() {
    return section[sectionIndex--];
  }

  private void pushSection(int level) {
    sectionIndex++;

    section = IntArrays.copyIfNecessary(section, sectionIndex);

    section[sectionIndex] = level;
  }

  private int uoe() {
    var s = Integer.toBinaryString(state);

    throw new UnsupportedOperationException("Implement me :: state=" + s);
  }

}