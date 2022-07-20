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

class Lexer {

  class Symbol {
    static final int EOF = -1;

    static final int LF = -2;

    static final int MONOSPACE = -3;

    static final int REGULAR = -4;

    static final int TITLE = -5;
  }

  private static final int _EOF = 0;

  private static final int _LINE_FIRST = 1;

  private static final int _MAYBE_START_MONOSPACE = 2;

  private static final int _MAYBE_TITLE0 = 3;

  private static final int _MAYBE_TITLE1 = 4;

  private static final int _WORD = 5;

  private static final int _WS = 6;

  private String source;

  private int sourceIndex;

  private int state;

  private int[] symbol;

  private int symbolCounter;

  private int symbolIndex;

  private int beginIndex;

  private int maybeTitleLevel;

  Lexer() {
    symbol = new int[512];
  }

  final boolean hasSymbol() {
    return symbolCounter < symbolIndex;
  }

  final boolean isBigS(char c) {
    return switch (c) {
      case ' ', '\t', '\r', '\n', '\f', '\u000B' -> true;
      default -> false;
    };
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

  final int nextSymbol() {
    return symbol[symbolCounter++];
  }

  final int previousSymbol() {
    return symbol[symbolCounter - 4];
  }

  final String source(int beginIndex, int endIndex) {
    return source.substring(beginIndex, endIndex);
  }

  final void tokenize(String source) {
    Check.state(
      state == _EOF,

      """
      Concurrent lexical analysis is not supported.

      It seems a previous AsciiDoc document lexical analysis:

      - is currently running; or
      - finished abruptly (most likely due to a bug in this lexer, sorry...).
      """);

    this.source = source;

    sourceIndex = 0;

    symbolIndex = 0;

    state = _LINE_FIRST;

    while (state != _EOF) {
      state = state(state);
    }

    symbolCounter = 0;
  }

  final int[] toSymbol() {
    return Arrays.copyOf(symbol, symbolIndex);
  }

  private void addSymbol(int value) {
    symbol = IntArrays.copyIfNecessary(symbol, symbolIndex);

    symbol[symbolIndex++] = value;
  }

  private int advance(int state) {
    sourceIndex++;

    return state;
  }

  private void atChar(int symbol) {
    addSymbol(symbol);
    addSymbol(sourceIndex);
  }

  private int consumeLf() {
    atChar(Symbol.LF);

    return advance(_LINE_FIRST);
  }

  private int consumeWs() {
    return advance(_WS);
  }

  private boolean hasChar() { return sourceIndex < source.length(); }

  private char peek() { return source.charAt(sourceIndex); }

  private int state(int state) {
    if (!hasChar()) {
      return tokenizeEof();
    }

    return switch (state) {
      case _LINE_FIRST -> stateLineFirst();
      case _MAYBE_TITLE0 -> stateMaybeTitle0();
      case _MAYBE_TITLE1 -> stateMaybeTitle1();
      case _WORD -> stateWord();
      case _WS -> stateWs();
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int stateLineFirst() {
    beginIndex = sourceIndex;

    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> consumeWs();
      case '\n' -> consumeLf();
      case '=' -> advance(_MAYBE_TITLE0);
      default -> tokenizeFirstChar();
    };
  }

  private int stateMaybeTitle0() {
    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> {
        maybeTitleLevel = sourceIndex - beginIndex;

        yield advance(_MAYBE_TITLE1);
      }
      case '\n' -> tokenizeRegularLf();
      case '=' -> advance(state);
      default -> tokenizeFirstChar();
    };
  }

  private int stateMaybeTitle1() {
    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> advance(_MAYBE_TITLE1);
      case '\n' -> tokenizeRegularLf();
      default -> {
        addSymbol(Symbol.TITLE);
        addSymbol(maybeTitleLevel);

        yield tokenizeFirstChar();
      }
    };
  }

  private int stateWord() {
    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> advance(_WS);
      case '\n' -> tokenizeLf();
      default -> advance(_WORD);
    };
  }

  private int stateWs() {
    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> advance(state);
      case '\n' -> tokenizeLf();
      case '`' -> advance(_MAYBE_START_MONOSPACE);
      default -> tokenizeNextChar();
    };
  }

  private int tokenizeEof() {
    atChar(Symbol.EOF);

    return _EOF;
  }

  private int tokenizeFirstChar() {
    return switch (peek()) {
      case '`' -> advance(_MAYBE_START_MONOSPACE);
      default -> {
        addSymbol(Symbol.REGULAR);
        addSymbol(sourceIndex);

        yield advance(_WORD);
      }
    };
  }

  private int tokenizeLf() {
    addSymbol(Symbol.LF);
    addSymbol(sourceIndex);

    return advance(_LINE_FIRST);
  }

  private int tokenizeNextChar() {
    return switch (peek()) {
      case '`' -> advance(_MAYBE_START_MONOSPACE);
      default -> advance(_WORD);
    };
  }

  private int tokenizeRegularLf() {
    addSymbol(Symbol.REGULAR);
    addSymbol(beginIndex);

    return tokenizeLf();
  }

  /*

  # *strong*
  [:strong, :constrained, /(^|[^#{CC_WORD};:}])(?:#{QuoteAttributeListRxt})?\*(\S|\S#{CC_ALL}*?\S)\*(?!#{CG_WORD})/m],

  /\S/ - A non-whitespace character: /[^ \t\r\n\f\v]/
  /\p{Word}/ - A member of one of the following Unicode general category Letter, Mark, Number, Connector_Punctuation

  A Unicode character's General Category value can also be matched with \p{Ab} where Ab is the category's abbreviation as described below:

  /\p{Ll}/ - 'Letter: Lowercase'
  /\p{Lm}/ - 'Letter: Mark'
  /\p{Lo}/ - 'Letter: Other'
  /\p{Lt}/ - 'Letter: Titlecase'
  /\p{Lu}/ - 'Letter: Uppercase
  /\p{Lo}/ - 'Letter: Other'

  /\p{Mn}/ - 'Mark: Nonspacing'
  /\p{Mc}/ - 'Mark: Spacing Combining'
  /\p{Me}/ - 'Mark: Enclosing'

  /\p{Nd}/ - 'Number: Decimal Digit'
  /\p{Nl}/ - 'Number: Letter'
  /\p{No}/ - 'Number: Other'

  /\p{Pc}/ - 'Punctuation: Connector'

   */

}