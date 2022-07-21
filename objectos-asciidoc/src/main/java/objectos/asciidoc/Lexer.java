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

  private static final int _LINE_START = 1 << 2;
  private static final int _MONO_START = 1 << 3;

  // token

  private static final int _TITLE = 1 << 4;
  private static final int _REGULAR = 1 << 5;
  private static final int _MONOSPACE = 1 << 6;

  private String source;

  private int sourceIndex;

  private int state;

  private int[] symbol;

  private int symbolCounter;

  private int symbolIndex;

  private int beginIndex;

  private int maybeTitleLevel;

  private int monospaceIndex;

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

    state = _LINE_START;

    while (state != _EOF) {
      state = state(state);
    }

    symbolCounter = 0;
  }

  final int[] toSymbol() {
    return Arrays.copyOf(symbol, symbolIndex);
  }

  private void add(int s0, int s1) {
    symbol = IntArrays.copyIfNecessary(symbol, symbolIndex + 1);

    symbol[symbolIndex++] = s0;
    symbol[symbolIndex++] = s1;
  }

  private void add(int s0, int s1, int s2) {
    symbol = IntArrays.copyIfNecessary(symbol, symbolIndex + 2);

    symbol[symbolIndex++] = s0;
    symbol[symbolIndex++] = s1;
    symbol[symbolIndex++] = s2;
  }

  private void add(int s0, int s1, int s2, int s3, int s4) {
    symbol = IntArrays.copyIfNecessary(symbol, symbolIndex + 4);

    symbol[symbolIndex++] = s0;
    symbol[symbolIndex++] = s1;
    symbol[symbolIndex++] = s2;
    symbol[symbolIndex++] = s3;
    symbol[symbolIndex++] = s4;
  }

  private int advance(int state) {
    sourceIndex++;

    return state;
  }

  private boolean hasChar() { return sourceIndex < source.length(); }

  private char peek() { return source.charAt(sourceIndex); }

  /*
  
  @startuml
  hide empty description
  [*] --> _LINE_START

  _LINE_START --> _MAYBE::_MONO_START : backtick
  
  _MAYBE::_MONO_START --> _MAYBE::_MONOSPACE : not ws
  
  _MAYBE::_MONOSPACE --> _MAYBE::_MONOSPACE : not backtick\nnot ws
  _MAYBE::_MONOSPACE --> _MONOSPACE_START : ws
  _MAYBE::_MONOSPACE --> _MAYBE::_MONOSPACE_WORD : backtick
  
  _MAYBE::_MONOSPACE_WORD --> _MONOSPACE_WORD : not word
  @enduml
  
   */

  private int state(int state) {
    if (!hasChar()) {
      return tokenizeEof();
    }

    return switch (state) {
      case _LINE_START -> {
        beginIndex = monospaceIndex = sourceIndex;

        yield switch (peek()) {
          case '\t', '\u000b', '\f', ' ' -> advance(_WS);
          case '\n' -> {
            add(Symbol.LF, sourceIndex);

            yield advance(_LINE_START);
          }
          case '=' -> advance(_MAYBE | _TITLE);
          case '`' -> advance(_MAYBE | _MONO_START);
          default -> advance(_REGULAR);
        };
      }

      case _MAYBE | _MONO_START -> switch (peek()) {
        case '\n' -> {
          add(
            Symbol.REGULAR, beginIndex, sourceIndex,
            Symbol.LF, sourceIndex
          );

          yield advance(_LINE_START);
        }
        case '`' -> advance(_MAYBE | _MONOSPACE);
        default -> advance(state);
      };

      case _MAYBE | _MONOSPACE -> {
        var c = peek();

        if (isWord(c)) {
          yield advance(_MONO_START);
        }

        yield switch (c) {
          case '\n' -> {
            add(Symbol.LF, sourceIndex);

            yield advance(_LINE_START);
          }
          default -> {
            add(Symbol.MONOSPACE, monospaceIndex + 1, sourceIndex - 1);

            beginIndex = sourceIndex;

            yield advance(_REGULAR);
          }
        };
      }

      case _MAYBE | _REGULAR -> switch (peek()) {
        case '\t', '\u000b', '\f', ' ' -> advance(state);
        case '\n' -> {
          add(Symbol.LF, sourceIndex);

          yield advance(_LINE_START);
        }
        default -> {
          beginIndex = sourceIndex - 1;

          yield advance(_REGULAR);
        }
      };

      case _REGULAR -> switch (peek()) {
        case '\t', '\u000b', '\f', ' ' -> advance(_REGULAR | _WS);
        case '\n' -> {
          add(
            Symbol.REGULAR, beginIndex, sourceIndex,
            Symbol.LF, sourceIndex
          );

          yield advance(_LINE_START);
        }
        default -> advance(state);
      };

      case _REGULAR | _MAYBE | _MONOSPACE -> switch (peek()) {
        case '\t', '\u000b', '\f', ' ' -> {
          add(Symbol.REGULAR, beginIndex, monospaceIndex);
          add(Symbol.MONOSPACE, monospaceIndex + 1, sourceIndex - 1);

          yield advance(_MAYBE | _REGULAR);
        }
        case '\n' -> {
          add(Symbol.LF, sourceIndex);

          yield advance(_LINE_START);
        }
        default -> advance(_REGULAR | _MONO_START);
      };

      case _REGULAR | _MONO_START -> switch (peek()) {
        case '\n' -> {
          add(
            Symbol.REGULAR, beginIndex, sourceIndex,
            Symbol.LF, sourceIndex
          );

          yield advance(_LINE_START);
        }
        case '`' -> advance(_REGULAR | _MAYBE | _MONOSPACE);
        default -> advance(state);
      };

      case _REGULAR | _MAYBE | _MONO_START -> switch (peek()) {
        case '\t', '\u000b', '\f', ' ' -> advance(_REGULAR | _WS);
        default -> {
          monospaceIndex = sourceIndex - 1;

          yield advance(_REGULAR | _MONO_START);
        }
      };

      case _REGULAR | _WS -> switch (peek()) {
        case '\t', '\u000b', '\f', ' ' -> advance(state);
        case '\n' -> {
          add(Symbol.LF, sourceIndex);

          yield advance(_LINE_START);
        }
        case '`' -> advance(_REGULAR | _MAYBE | _MONO_START);
        default -> advance(_REGULAR);
      };

      case _MAYBE | _TITLE -> switch (peek()) {
        case '\t', '\u000b', '\f', ' ' -> {
          maybeTitleLevel = sourceIndex - beginIndex;

          yield advance(_MAYBE | _TITLE | _WS);
        }
        case '\n' -> {
          add(
            Symbol.REGULAR, beginIndex, sourceIndex,
            Symbol.LF, sourceIndex
          );

          yield advance(_LINE_START);
        }
        case '=' -> advance(state);
        default -> advance(_REGULAR);
      };

      case _MAYBE | _TITLE | _WS -> switch (peek()) {
        case '\t', '\u000b', '\f', ' ' -> advance(state);
        case '\n' -> {
          add(
            Symbol.REGULAR, beginIndex, sourceIndex,
            Symbol.LF, sourceIndex
          );

          yield advance(_LINE_START);
        }
        case '`' -> {
          add(Symbol.TITLE, maybeTitleLevel);

          monospaceIndex = sourceIndex;

          yield advance(_MAYBE | _MONO_START);
        }
        default -> {
          add(Symbol.TITLE, maybeTitleLevel);

          beginIndex = sourceIndex;

          yield advance(_REGULAR);
        }
      };

      default -> throw new UnsupportedOperationException(
        "Implement me :: state=" + Integer.toBinaryString(state));
    };
  }

  private int tokenizeEof() {
    switch (state) {
      case _LINE_START -> { /* noop */ }
      case _MAYBE | _MONOSPACE -> add(Symbol.MONOSPACE, monospaceIndex + 1, sourceIndex - 1);
      case _REGULAR -> add(Symbol.REGULAR, beginIndex, sourceIndex);
      default -> throw new UnsupportedOperationException(
        "Implement me :: state=" + Integer.toBinaryString(state));
    }

    add(Symbol.EOF, sourceIndex);

    return _EOF;
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