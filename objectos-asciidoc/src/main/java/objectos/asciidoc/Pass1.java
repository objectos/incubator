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
import java.util.Map;
import objectos.lang.Check;
import objectos.util.GrowableMap;
import objectos.util.IntArrays;

class Pass1 {

  interface Source {
    boolean hasToken();

    int nextToken();

    String substring(int start, int end);

    int tokenCursor();

    void tokenCursor(int value);
  }

  private static final int DOCUMENT = 1;

  private static final int HEADING = 2;

  private static final int DOCATTR = 3;

  private static final int PREAMBLE = 4;

  private static final int SECTION = 5;

  private static final int BLOCK_ATTRLIST = 6;
  private static final int MACRO_ATTRLIST = 7;

  private static final int PARAGRAPH = 8;
  private static final int PARAGRAPH_NL = 9;

  private static final int LISTING_BLOCK = 10;

  private static final int ULIST = 11;

  private static final int INLINE_MACRO = 12;

  private static final int _SKIP_NL = 13;
  private static final int _TOKEN_START = 14;

  private int attrCount;

  private String attributeName;

  private final StringBuilder attributeValue = new StringBuilder();

  private int[] code;

  private int codeCursor;

  private int codeIndex;

  private int[] context;

  private int contextIndex = -1;

  private final Map<String, String> docattr = new GrowableMap<>();

  private int[] list;

  private int listIndex = -1;

  private int[] section;

  private int sectionIndex = -1;

  private Source source;

  private int tokenIndex;

  private int tokenStart;

  Pass1() {
    code = new int[512];

    context = new int[32];

    list = new int[8];

    section = new int[8];
  }

  public final void execute(Source source) {
    Check.state(
      contextIndex == -1,

      """
      Concurrent pass (1) is not supported.

      It seems a previous AsciiDoc document pass (1):

      - is currently running; or
      - finished abruptly (most likely due to a bug in this component, sorry...).
      """
    );

    this.source = source;

    codeIndex = 0;

    contextIndex = -1;

    docattr.clear();

    listIndex = -1;

    sectionIndex = -1;

    execute0();

    codeCursor = 0;
  }

  final String attribute(String key) {
    return docattr.get(key);
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

  private void add(int p0, int p1, int p2, int p3, int p4) {
    code = IntArrays.copyIfNecessary(code, codeIndex + 4);

    code[codeIndex++] = p0;
    code[codeIndex++] = p1;
    code[codeIndex++] = p2;
    code[codeIndex++] = p3;
    code[codeIndex++] = p4;
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

  private void execute0() {
    add(Code.DOCUMENT_START);
    push(DOCUMENT);

    while (hasNext()) {
      tokenIndex = source.tokenCursor();

      var token = next();

      switch (token) {
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

        case Token.ULIST_ASTERISK -> parseUlistAsterisk(next(), next(), next());

        case Token.ULIST_HYPHEN -> parseUlistHyphen(next(), next());

        case Token.INLINE_MACRO -> parseInlineMacro(next(), next());

        case Token.DOCATTR -> parseDocattr(next(), next());

        default -> throw new UnsupportedOperationException("Implement me :: token=" + token);
      }
    }

    while (contextIndex >= 0) {
      int ctx = pop();

      switch (ctx) {
        case DOCUMENT -> add(Code.DOCUMENT_END);

        case HEADING -> add(Code.HEADING_END);

        case PREAMBLE -> add(Code.PREAMBLE_END);

        case SECTION -> add(Code.SECTION_END);

        case PARAGRAPH -> add(Code.PARAGRAPH_END);

        default -> throw new UnsupportedOperationException("Implement me :: ctx=" + ctx);
      }
    }
  }

  private boolean hasNext() {
    return source.hasToken();
  }

  private int next() {
    return source.nextToken();
  }

  private void parseAttrListEnd() {
    int ctx = pop();

    switch (ctx) {
      case BLOCK_ATTRLIST -> push(_SKIP_NL);

      case MACRO_ATTRLIST -> {
        int macro = pop();

        if (macro != INLINE_MACRO) {
          throw new UnsupportedOperationException("Implement me :: not a macro, got=" + macro);
        }

        tokenStart = source.tokenCursor();
      }

      default -> uoe(ctx);
    }
  }

  private void parseAttrListStart() {
    attrCount = 1;

    switch (peek()) {
      case DOCUMENT -> push(BLOCK_ATTRLIST);

      case INLINE_MACRO -> push(MACRO_ATTRLIST);

      default -> uoe();
    }
  }

  private void parseAttrValue(int start, int end) {
    int ctx = pop();

    switch (ctx) {
      case BLOCK_ATTRLIST, MACRO_ATTRLIST -> {
        add(Code.ATTR_POSITIONAL, attrCount, start, end);

        push(ctx);
      }

      default -> uoe(ctx);
    }
  }

  private void parseBlob(int start, int end) {
    switch (peek()) {
      case DOCATTR -> {
        var s = source.substring(start, end);

        attributeValue.append(s);
      }

      case INLINE_MACRO -> {
        add(Code.MACRO_TARGET, start, end);
      }

      default -> parseTokens(start, end);
    }
  }

  private void parseDocattr(int start, int end) {
    int ctx = pop();

    switch (ctx) {
      case DOCUMENT -> {
        attributeName = source.substring(start, end);

        attributeValue.setLength(0);

        push(ctx, DOCATTR);
      }

      default -> uoe(ctx);
    }
  }

  private void parseHeading(int level, int start, int end) {
    int ctx = pop();

    switch (ctx) {
      case DOCUMENT -> {
        if (level == 1) {
          add(Code.HEADING_START, level);

          push(ctx, HEADING, _TOKEN_START);
        } else {
          push(ctx);

          parseHeadingSection(level);
        }
      }

      case PREAMBLE -> {
        add(Code.PREAMBLE_END);

        parseHeadingSection(level);
      }

      case SECTION -> {
        var section = level - 1;

        var prevSection = popSection();

        if (section > prevSection) {
          push(ctx);

          parseHeadingSection(level);
        }

        else if (section == prevSection) {
          add(Code.SECTION_END);

          parseHeadingSection(level);
        }

        else {
          throw new UnsupportedOperationException("Implement me");
        }
      }

      case ULIST -> {
        popList();

        var tokenEnd = tokenIndex - 1;

        if (tokenStart < tokenEnd) {
          add(Code.TOKENS, tokenStart, tokenEnd);
        }

        add(Code.LI_END, Code.ULIST_END);

        source.tokenCursor(tokenIndex);
      }

      default -> uoe(ctx);
    }
  }

  private void parseHeadingSection(int level) {
    var section = level - 1;

    pushSection(section);

    add(Code.SECTION_START, section, Code.HEADING_START, level);

    push(SECTION, HEADING, _TOKEN_START);
  }

  private void parseInlineMacro(int start, int end) {
    int ctx = pop();

    if (ctx == _TOKEN_START) {
      ctx = pop();
    }

    switch (ctx) {
      case DOCUMENT -> {
        add(
          Code.PREAMBLE_START, Code.PARAGRAPH_START,
          Code.INLINE_MACRO, start, end
        );

        push(ctx, PREAMBLE, PARAGRAPH, INLINE_MACRO);
      }

      case PARAGRAPH -> {
        var tokenEnd = tokenIndex - 1; // do not add INLINE_MACRO

        add(
          Code.TOKENS, tokenStart, tokenEnd,
          Code.INLINE_MACRO, start, end
        );

        push(ctx, INLINE_MACRO);
      }

      case ULIST -> {
        add(Code.INLINE_MACRO, start, end);

        push(ctx, INLINE_MACRO);
      }

      default -> uoe(ctx);
    }
  }

  private void parseLineEnd() {
    int ctx = pop();

    switch (ctx) {
      case DOCUMENT -> {
        add(Code.PREAMBLE_START);

        push(ctx, PREAMBLE);
      }

      case HEADING -> add(Code.TOKENS, tokenStart, tokenIndex, Code.HEADING_END);

      case DOCATTR -> {
        var value = attributeValue.toString();

        docattr.put(attributeName, value);
      }

      case SECTION -> push(ctx);

      case PARAGRAPH -> push(ctx, PARAGRAPH_NL);

      case PARAGRAPH_NL -> {
        add(Code.TOKENS, tokenStart, tokenIndex, Code.PARAGRAPH_END);

        pop(); // pop PARAGRAPH
      }

      case LISTING_BLOCK -> push(ctx);

      case ULIST -> push(ctx);

      case _SKIP_NL -> {}

      default -> uoe(ctx);
    }
  }

  private void parseLineEndEof() {
    int ctx = pop();

    switch (ctx) {
      case DOCUMENT, PREAMBLE -> push(ctx);

      case HEADING -> {
        push(ctx);

        add(Code.TOKENS, tokenStart, tokenIndex);
      }

      case PARAGRAPH_NL -> {
        add(Code.TOKENS, tokenStart, tokenIndex);
      }

      case ULIST -> {
        popList();

        var tokenEnd = tokenIndex - 1; // ignore NL

        if (tokenStart < tokenEnd) {
          add(Code.TOKENS, tokenStart, tokenEnd);
        }

        add(Code.LI_END, Code.ULIST_END);
      }

      default -> uoe(ctx);
    }
  }

  private void parseListingBlockDelim(int dashes) {
    var ctx = pop();

    switch (ctx) {
      case DOCUMENT -> {
        add(Code.PREAMBLE_START, Code.LISTING_BLOCK_START);

        pushSection(dashes);

        push(ctx, PREAMBLE, LISTING_BLOCK, _TOKEN_START, _SKIP_NL);
      }

      case LISTING_BLOCK -> {
        var current = popSection();

        if (current != dashes) {
          pushSection(current);

          throw new UnsupportedOperationException("Implement me :: literal dashes?");
        }

        var tokenEnd = tokenIndex - 1; // ignore last LF

        add(Code.VERBATIM, tokenStart, tokenEnd, Code.LISTING_BLOCK_END);

        push(_SKIP_NL);
      }

      default -> uoe(ctx);
    }
  }

  private void parseSeparator() {
    switch (peek()) {
      case BLOCK_ATTRLIST -> { attrCount++; }

      default -> uoe();
    }
  }

  private void parseTokens(int v0) {
    parseTokens(v0, 0);
  }

  private void parseTokens(int v0, int v1) {
    int ctx = pop();

    switch (ctx) {
      case DOCUMENT -> {
        tokenStart = tokenIndex;

        add(Code.PREAMBLE_START, Code.PARAGRAPH_START);

        push(ctx, PREAMBLE, PARAGRAPH);
      }

      case PREAMBLE -> {
        tokenStart = tokenIndex;

        add(Code.PARAGRAPH_START);

        push(ctx, PARAGRAPH);
      }

      case SECTION -> {
        tokenStart = tokenIndex;

        add(Code.PARAGRAPH_START);

        push(ctx, PARAGRAPH);
      }

      case PARAGRAPH, LISTING_BLOCK, ULIST -> push(ctx);

      case PARAGRAPH_NL -> {}

      case _TOKEN_START -> { tokenStart = tokenIndex; }

      default -> uoe(ctx);
    }
  }

  private void parseUlist(char symbol, int count) {
    int ctx = pop();

    switch (ctx) {
      case DOCUMENT -> {
        add(Code.PREAMBLE_START, Code.ULIST_START, Code.LI_START);

        pushList(symbol, count);

        push(ctx, PREAMBLE, ULIST, _TOKEN_START);
      }

      case SECTION -> {
        add(Code.ULIST_START, Code.LI_START);

        pushList(symbol, count);

        push(ctx, ULIST, _TOKEN_START);
      }

      case ULIST -> {
        var prevSymbol = peekListSymbol();
        var prevCount = peekListCount();

        var tokenEnd = tokenIndex - 1; // ignore NL

        if (tokenStart < tokenEnd) {
          add(Code.TOKENS, tokenStart, tokenEnd);
        }

        if (prevSymbol == symbol) {
          if (prevCount == count) {
            add(Code.LI_END, Code.LI_START);
          }

          else if (prevCount < count) {
            pushList(symbol, count);

            add(Code.ULIST_START, Code.LI_START);
          }

          else {
            popList();

            add(Code.LI_END, Code.ULIST_END, Code.LI_END, Code.LI_START);
          }
        } else {
          pushList(symbol, count);

          add(Code.LI_END, Code.ULIST_START, Code.LI_START);
        }

        push(ctx, _TOKEN_START);
      }

      default -> uoe(ctx);
    }
  }

  private void parseUlistAsterisk(int count, int start, int end) {
    parseUlist('*', count);
  }

  private void parseUlistHyphen(int start, int end) {
    parseUlist('-', 1);
  }

  private int peek() {
    return context[contextIndex];
  }

  private int peekListCount() {
    return list[listIndex - 1];
  }

  private char peekListSymbol() {
    return (char) list[listIndex];
  }

  private int pop() {
    return context[contextIndex--];
  }

  private void popList() {
    listIndex -= 2;
  }

  private int popSection() {
    return section[sectionIndex--];
  }

  private void push(int c0) {
    contextIndex++;

    context = IntArrays.copyIfNecessary(context, contextIndex);

    context[contextIndex] = c0;
  }

  private void push(int c0, int c1) {
    context = IntArrays.copyIfNecessary(context, contextIndex + 2);

    context[++contextIndex] = c0;
    context[++contextIndex] = c1;
  }

  private void push(int c0, int c1, int c2) {
    context = IntArrays.copyIfNecessary(context, contextIndex + 3);

    context[++contextIndex] = c0;
    context[++contextIndex] = c1;
    context[++contextIndex] = c2;
  }

  private void push(int c0, int c1, int c2, int c3) {
    context = IntArrays.copyIfNecessary(context, contextIndex + 4);

    context[++contextIndex] = c0;
    context[++contextIndex] = c1;
    context[++contextIndex] = c2;
    context[++contextIndex] = c3;
  }

  private void push(int c0, int c1, int c2, int c3, int c4) {
    context = IntArrays.copyIfNecessary(context, contextIndex + 5);

    context[++contextIndex] = c0;
    context[++contextIndex] = c1;
    context[++contextIndex] = c2;
    context[++contextIndex] = c3;
    context[++contextIndex] = c4;
  }

  private void pushList(char symbol, int count) {
    list = IntArrays.copyIfNecessary(list, listIndex + 2);

    list[++listIndex] = count;
    list[++listIndex] = symbol;
  }

  private void pushSection(int level) {
    sectionIndex++;

    section = IntArrays.copyIfNecessary(section, sectionIndex);

    section[sectionIndex] = level;
  }

  private void uoe() {
    if (contextIndex == -1) {
      throw new UnsupportedOperationException("Implement me :: stack=[]");
    }

    var length = contextIndex + 1;

    int[] copy = Arrays.copyOf(context, length);

    throw new UnsupportedOperationException("Implement me :: stack=" + Arrays.toString(copy));
  }

  private void uoe(int value) {
    push(value);

    uoe();
  }

}