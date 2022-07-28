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

  private static final int LISTING_BLOCK = 1 << 9;

  private int attrCount;

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

  final int codeAt(int index) {
    return code[index];
  }

  final int codeCursor() {
    return codeCursor;
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

  private void add(int p0, int p1) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 1);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
  }

  private void add(int p0, int p1, int p2) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 2);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
    code[codeIndex++] = p2;
  }

  private void add(int p0, int p1, int p2, int p3) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 3);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
    code[codeIndex++] = p2;
    code[codeIndex++] = p3;
  }

  private void add(int p0, int p1, int p2, int p3, int p4, int p5) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 5);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
    code[codeIndex++] = p2;
    code[codeIndex++] = p3;
    code[codeIndex++] = p4;
    code[codeIndex++] = p5;
  }

  private void addCode(int p0, int p1, int p2, int p3, int p4) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 4);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
    code[codeIndex++] = p2;
    code[codeIndex++] = p3;
    code[codeIndex++] = p4;
  }

  private void execute0() {
    add(Code.DOCUMENT_START);

    while (hasNext()) {
      tokenIndex = source.tokenCursor();

      var token = next();

      state = switch (token) {
        case Token.LF -> parseLineEnd();

        case Token.EOF -> parseLineEndEof();

        case Token.HEADING -> parseHeading(next(), next(), next());

        case Token.BLOB -> parseBlob(next(), next());

        case Token.ATTR_LIST_START -> parseAttrListStart();

        case Token.ATTR_LIST_END -> parseAttrListEnd();

        case Token.ATTR_VALUE -> parseAttrValue(next(), next());

        case Token.BOLD_START, Token.BOLD_END -> parseTokens(next());

        case Token.ITALIC_START, Token.ITALIC_END -> parseTokens(next());

        case Token.MONO_START, Token.MONO_END -> parseTokens(next());

        case Token.LISTING_BLOCK_DELIM -> parseListingBlockDelim(next());

        case Token.SEPARATOR -> parseSeparator();

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

  private int parseAttrListEnd() {
    state = state & ~(ATTR);

    return switch (state) {
      case MAYBE -> MAYBE | ATTR;

      default -> uoe();
    };
  }

  private int parseAttrListStart() {
    attrCount = 1;

    return switch (state) {
      case MAYBE -> state | ATTR;

      default -> uoe();
    };
  }

  private int parseAttrValue(int start, int end) {
    int attr = state & (ATTR);

    return switch (attr) {
      case ATTR -> {
        add(Code.ATTR_POSITIONAL, attrCount, start, end);

        yield state;
      }

      default -> uoe(attr);
    };
  }

  private int parseBlob(int start, int end) {
    return switch (state) {
      case PREAMBLE | LISTING_BLOCK -> {
        add(Code.BLOB, start, end);

        yield state;
      }

      default -> parseTokens(start, end);
    };
  }

  private int parseHeading(int level, int start, int end) {
    return switch (state) {
      case MAYBE -> {
        add(Code.HEADING_START, level);

        yield DOCUMENT | HEADING;
      }

      case MAYBE | ATTR -> {
        var section = level - 1;

        pushSection(section);

        add(
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

        add(
          Code.SECTION_START, section,
          Code.HEADING_START, level
        );

        yield SECTION | HEADING;
      }

      default -> uoe();
    };
  }

  private int parseLineEnd() {
    return switch (state) {
      case MAYBE | ATTR -> state;

      case DOCUMENT | HEADING -> {
        add(
          Code.TOKENS, tokenStart, tokenIndex,
          Code.HEADING_END
        );

        yield MAYBE | DOCUMENT | METADATA;
      }

      case MAYBE | DOCUMENT | METADATA -> MAYBE | PREAMBLE;

      case PREAMBLE -> state;

      case PREAMBLE | PARAGRAPH -> PREAMBLE | PARAGRAPH | NL;

      case PREAMBLE | PARAGRAPH | NL -> {
        add(
          Code.TOKENS, tokenStart, tokenIndex,
          Code.PARAGRAPH_END
        );

        yield PREAMBLE;
      }

      case PREAMBLE | LISTING_BLOCK | MAYBE -> PREAMBLE | LISTING_BLOCK;

      case PREAMBLE | LISTING_BLOCK -> {
        tokenStart = tokenIndex;

        yield PREAMBLE | LISTING_BLOCK | NL;
      }

      case SECTION -> state;

      case SECTION | HEADING -> {
        add(
          Code.TOKENS, tokenStart, tokenIndex,
          Code.HEADING_END
        );

        yield SECTION;
      }

      case SECTION | PARAGRAPH -> SECTION | PARAGRAPH | NL;

      case SECTION | PARAGRAPH | NL -> {
        add(
          Code.TOKENS, tokenStart, tokenIndex,
          Code.PARAGRAPH_END
        );

        yield SECTION;
      }

      default -> uoe();
    };
  }

  private int parseLineEndEof() {
    return switch (state) {
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

      case PREAMBLE -> {
        add(Code.PREAMBLE_END, Code.DOCUMENT_END);

        yield EOF;
      }

      case PREAMBLE | PARAGRAPH | NL -> {
        add(
          Code.TOKENS, tokenStart, tokenIndex,
          Code.PARAGRAPH_END,
          Code.PREAMBLE_END,
          Code.DOCUMENT_END
        );

        yield EOF;
      }

      case SECTION | PARAGRAPH | NL -> {
        add(
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
  }

  private int parseListingBlockDelim(int dashes) {
    return switch (state) {
      case MAYBE, MAYBE | ATTR -> {
        add(Code.PREAMBLE_START, Code.LISTING_BLOCK_START);

        pushSection(dashes);

        yield PREAMBLE | LISTING_BLOCK | MAYBE;
      }

      case PREAMBLE | LISTING_BLOCK | NL -> {
        var current = popSection();

        if (current != dashes) {
          pushSection(current);

          throw new UnsupportedOperationException("Implement me :: literal dashes?");
        }

        add(Code.LISTING_BLOCK_END);

        yield PREAMBLE;
      }

      default -> uoe();
    };
  }

  private int parseSeparator() {
    return switch (state) {
      case MAYBE | ATTR -> { attrCount++; yield state; }

      default -> uoe();
    };
  }

  private int parseTokens(int v0) {
    return parseTokens(v0, 0);
  }

  private int parseTokens(int v0, int v1) {
    return switch (state) {
      case MAYBE -> {
        tokenStart = tokenIndex;

        add(Code.PREAMBLE_START, Code.PARAGRAPH_START);

        yield PREAMBLE | PARAGRAPH;
      }

      case DOCUMENT | HEADING -> {
        tokenStart = tokenIndex;

        yield state;
      }

      case MAYBE | PREAMBLE -> {
        tokenStart = tokenIndex;

        add(Code.PREAMBLE_START, Code.PARAGRAPH_START);

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
    return uoe(state);
  }

  private int uoe(int value) {
    var s = Integer.toBinaryString(value);

    throw new UnsupportedOperationException("Implement me :: state=" + s);
  }

}