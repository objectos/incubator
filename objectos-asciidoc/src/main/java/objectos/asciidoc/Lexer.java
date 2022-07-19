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

    static final int EQUALS = -2;

    static final int LF = -3;

    static final int WORD = -4;

    static final int WS = -5;
  }

  private static class State {
    private static final int STOP = 0;

    private static final int EQUALS = 1;

    private static final int START_LINE = 2;

    private static final int WORD = 3;

    private static final int WS = 4;
  }

  private String source;

  private int sourceIndex;

  private int state;

  private int[] symbol;

  private int symbolCounter;

  private int symbolIndex;

  Lexer() {
    symbol = new int[512];
  }

  final boolean hasSymbol() {
    return symbolCounter < symbolIndex;
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

  final String source(int beginIndex, int endIndex) {
    return source.substring(beginIndex, endIndex);
  }

  final void tokenize(String source) {
    Check.state(
      state == State.STOP,

      """
      Concurrent lexical analysis is not supported.

      It seems a previous AsciiDoc document lexical analysis:

      - is currently running; or
      - finished abruptly (most likely due to a bug in this lexer, sorry...).
      """);

    this.source = source;

    sourceIndex = 0;

    symbolIndex = 0;

    state = State.START_LINE;

    while (state != State.STOP) {
      state = tokenize(state);
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

  private boolean hasChar() { return sourceIndex < source.length(); }

  private char peek() { return source.charAt(sourceIndex); }

  private int tokenize(int state) {
    if (!hasChar()) {
      return tokenizeEof();
    }

    return switch (state) {
      case State.EQUALS -> tokenizeEquals();
      case State.START_LINE -> tokenizeStartLine();
      case State.WORD -> tokenizeWord();
      case State.WS -> tokenizeWs();
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int tokenizeEof() {
    atChar(Symbol.EOF);

    return State.STOP;
  }

  private int tokenizeEquals() {
    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> {
        atChar(Symbol.WS);

        yield advance(State.WS);
      }
      case '\n' -> {
        atChar(Symbol.LF);

        yield advance(State.START_LINE);
      }
      case '=' -> advance(State.EQUALS);
      default -> {
        atChar(Symbol.WORD);

        yield advance(State.WORD);
      }
    };
  }

  private int tokenizeStartLine() {
    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> {
        atChar(Symbol.WS);

        yield advance(State.WS);
      }
      case '\n' -> {
        atChar(Symbol.LF);

        yield advance(State.START_LINE);
      }
      case '=' -> {
        atChar(Symbol.EQUALS);

        yield advance(State.EQUALS);
      }
      default -> {
        atChar(Symbol.WORD);

        yield advance(State.WORD);
      }
    };
  }

  private int tokenizeWord() {
    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> {
        atChar(Symbol.WS);

        yield advance(State.WS);
      }
      case '\n' -> {
        atChar(Symbol.LF);

        yield advance(State.START_LINE);
      }
      default -> advance(State.WORD);
    };
  }

  private int tokenizeWs() {
    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> advance(State.WS);
      case '\n' -> {
        atChar(Symbol.LF);

        yield advance(State.START_LINE);
      }
      default -> {
        atChar(Symbol.WORD);

        yield advance(State.WORD);
      }
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