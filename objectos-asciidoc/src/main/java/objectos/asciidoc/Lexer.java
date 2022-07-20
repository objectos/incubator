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

  // mods

  private static final int _MAYBE = 1 << 0;
  private static final int _WS = 1 << 1;

  // ctx

  private static final int _LSTART = 1 << 2;

  // token

  private static final int _REGULAR = 1 << 3;
  private static final int _TITLE = 1 << 4;

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

    state = _LSTART;

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

  private void addTwo(int symbol, int position) {
    addSymbol(symbol);
    addSymbol(position);
  }

  private int advance(int state) {
    sourceIndex++;

    return state;
  }

  private boolean hasChar() { return sourceIndex < source.length(); }

  private char peek() { return source.charAt(sourceIndex); }

  private int state(int state) {
    if (!hasChar()) {
      addTwo(Symbol.EOF, sourceIndex);

      return _EOF;
    }

    return switch (state) {
      case _LSTART -> {
        beginIndex = sourceIndex;

        yield switch (peek()) {
          case '\t', '\u000b', '\f', ' ' -> advance(_WS);
          case '\n' -> {
            addTwo(Symbol.LF, sourceIndex);

            yield advance(_LSTART);
          }
          case '=' -> advance(_MAYBE | _TITLE);
          default -> {
            addTwo(Symbol.REGULAR, sourceIndex);

            yield advance(_REGULAR);
          }
        };
      }

      case _MAYBE | _TITLE -> {
        yield switch (peek()) {
          case '\t', '\u000b', '\f', ' ' -> {
            maybeTitleLevel = sourceIndex - beginIndex;

            yield advance(_MAYBE | _TITLE | _WS);
          }
          case '\n' -> {
            addTwo(Symbol.REGULAR, beginIndex);
            addTwo(Symbol.LF, sourceIndex);

            yield advance(_LSTART);
          }
          case '=' -> advance(state);
          default -> {
            addTwo(Symbol.REGULAR, beginIndex);

            yield advance(_REGULAR);
          }
        };
      }

      case _MAYBE | _TITLE | _WS -> {
        yield switch (peek()) {
          case '\t', '\u000b', '\f', ' ' -> advance(state);
          case '\n' -> {
            addTwo(Symbol.REGULAR, beginIndex);
            addTwo(Symbol.LF, sourceIndex);

            yield advance(_LSTART);
          }
          default -> {
            addTwo(Symbol.TITLE, maybeTitleLevel);
            addTwo(Symbol.REGULAR, sourceIndex);

            yield advance(_REGULAR);
          }
        };
      }

      case _REGULAR -> {
        yield switch (peek()) {
          case '\t', '\u000b', '\f', ' ' -> advance(_REGULAR | _WS);
          case '\n' -> {
            addTwo(Symbol.LF, sourceIndex);

            yield advance(_LSTART);
          }
          default -> advance(state);
        };
      }

      case _REGULAR | _WS -> {
        yield switch (peek()) {
          case '\t', '\u000b', '\f', ' ' -> advance(state);
          case '\n' -> {
            addTwo(Symbol.LF, sourceIndex);

            yield advance(_LSTART);
          }
          default -> advance(_REGULAR);
        };
      }

      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
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