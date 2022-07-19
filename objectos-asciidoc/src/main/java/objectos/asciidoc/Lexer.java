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
    static final int BACKTICK = -1;

    static final int CBOLD0 = -2;

    static final int CBOLD9 = -3;

    static final int CONSTRAINED_M0 = -4;
    static final int CONSTRAINED_MX = -5;

    static final int EMPTY = -6;

    static final int EOF = -7;

    static final int EOL = -8;

    static final int LF = 0;

    static final int PARAGRAPH = -9;

    static final int TITLE = -10;

    static final int TITLE_LEVEL = -11;

    static final int WORD = -12;

    static final int WS = -13;
  }

  /*

  @startuml
  hide empty description

  [*] --> START_LINE

  CONSTRAINED --> CONSTRAINED : !special char
  CONSTRAINED --> MAYBE_END_CONSTRAINED : special char && previous != ws

  MAYBE_END_CONSTRAINED --> TEXT : !word
  MAYBE_END_CONSTRAINED --> CONSTRAINED : word
  
  MAYBE_START_CONSTRAINED --> CONSTRAINED : !isBigS(c)
  
  NOT_WORD --> MAYBE_START_CONSTRAINED : (backtick |\nasterisk |\nunderscore) &&\nvalid state
  
  START_LINE --> TEXT

  TEXT --> NOT_WORD : non word

  @enduml

   */

  private static final int _FINALLY = 1;

  private static final int _START_LINE = 2;

  private static final int _STOP = 0;

  private static final int _TEXT = 3;

  private static final int _TEXT_WORD = 4;

  private static final int _TEXT_WS = 5;

  private static final int _TITLE = 6;

  private int counter;

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
      state == _STOP,

      """
      Concurrent lexical analysis is not supported.

      It seems a previous AsciiDoc document lexical analysis:

      - is currently running; or
      - finished abruptly (most likely due to a bug in this lexer, sorry...).
      """);

    counter = 0;

    this.source = source;

    sourceIndex = 0;

    symbolIndex = 0;

    state = _START_LINE;

    while (state != _STOP) {
      state = tokenize(state);
    }
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
    return switch (state) {
      case _FINALLY -> tokenizeFinally();
      case _START_LINE -> tokenizeStartLine();
      case _TEXT -> tokenizeText();
      case _TEXT_WORD -> tokenizeTextWord();
      case _TEXT_WS -> tokenizeTextWs();
      case _TITLE -> tokenizeTitle();
      default -> throw new UnsupportedOperationException("Implement me :: state=" + state);
    };
  }

  private int tokenizeFinally() {
    addSymbol(Symbol.EOF);
    addSymbol(sourceIndex);

    symbolCounter = 0;

    return _STOP;
  }

  private int tokenizeStartLine() {
    if (!hasChar()) {
      atChar(Symbol.EMPTY);

      return _FINALLY;
    }

    return switch (peek()) {
      case '\n' -> {
        atChar(Symbol.EMPTY);

        yield advance(_START_LINE);
      }
      case '=' -> {
        counter = 1;

        yield advance(_TITLE);
      }
      default -> {
        atChar(Symbol.PARAGRAPH);

        yield tokenizeText0();
      }
    };
  }

  private int tokenizeText() {
    if (!hasChar()) {
      return _FINALLY;
    }

    return tokenizeText0();
  }

  private int tokenizeText0() {
    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> {
        atChar(Symbol.WS);

        yield advance(_TEXT_WS);
      }
      case '\n' -> {
        atChar(Symbol.LF);

        yield advance(_START_LINE);
      }
      default -> {
        atChar(Symbol.WORD);

        yield advance(_TEXT_WORD);
      }
    };
  }

  private int tokenizeTextWord() {
    if (!hasChar()) {
      return _FINALLY;
    }

    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> {
        atChar(Symbol.WS);

        yield advance(_TEXT_WS);
      }
      case '\n' -> {
        atChar(Symbol.LF);

        yield advance(_START_LINE);
      }
      default -> {
        yield advance(_TEXT_WORD);
      }
    };
  }

  private int tokenizeTextWs() {
    if (!hasChar()) {
      return _FINALLY;
    }

    return switch (peek()) {
      case '\t', '\u000b', '\f', ' ' -> {
        yield advance(_TEXT_WS);
      }
      case '\n' -> {
        atChar(Symbol.LF);

        yield advance(_START_LINE);
      }
      default -> {
        atChar(Symbol.WORD);

        yield advance(_TEXT_WORD);
      }
    };
  }

  private int tokenizeTitle() {
    if (!hasChar()) {
      return _FINALLY;
    }

    return switch (peek()) {
      case ' ' -> {
        addSymbol(Symbol.TITLE);
        addSymbol(symbolIndex - counter);

        addSymbol(Symbol.TITLE_LEVEL);
        addSymbol(counter);

        yield advance(_TEXT);
      }
      case '=' -> {
        counter++;

        yield advance(state);
      }
      default -> {
        addSymbol(Symbol.PARAGRAPH);
        addSymbol(symbolIndex - counter);

        yield tokenizeText0();
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