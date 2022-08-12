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

class Pass2 {

  interface Source {
    int token(int index);
  }

  private static final int EOF = 0;

  private static final int START = 1 << 0;

  private static final int REGULAR = 1 << 1;

  private static final int MONOSPACE = 1 << 2;

  private static final int BOLD = 1 << 3;

  private static final int ITALIC = 1 << 4;

  private static final int CONSTRAINED_END = 1 << 5;

  private Source source;

  private int sourceIndex;

  @SuppressWarnings("unused")
  private int sourceFirst;

  private int sourceLast;

  private int sourceMark;

  private int[] text;

  private int textIndex;

  private int state;

  private int regularEnd;

  private int textCursor;

  Pass2() {
    text = new int[128];
  }

  public final void execute(Source source, int first, int last) {
    Check.state(
      state == EOF,

      """
      Concurrent pass (2) is not supported.

      It seems a previous AsciiDoc document pass (2):

      - is currently running; or
      - finished abruptly (most likely due to a bug in this component, sorry...).
      """
    );

    textIndex = 0;

    this.source = source;

    sourceIndex = sourceFirst = first;

    sourceLast = last;

    state = START;

    execute0();

    textCursor = 0;
  }

  final boolean hasText() {
    return textCursor < textIndex;
  }

  final int nextText() {
    return text[textCursor++];
  }

  final int[] toText() {
    return Arrays.copyOf(text, textIndex);
  }

  private void addText(int t0) {
    text = IntArrays.copyIfNecessary(text, textIndex);

    text[textIndex++] = t0;
  }

  private void addText(int t0, int t1) {
    text = IntArrays.copyIfNecessary(text, textIndex + 1);

    text[textIndex++] = t0;
    text[textIndex++] = t1;
  }

  private void execute0() {
    while (hasToken()) {
      var token = nextToken();

      state = switch (token) {
        case Token.BLOB -> executeBlob(nextToken(), nextToken());

        case Token.EOF -> state;

        case Token.LF -> executeLf();

        case Token.BOLD_START -> executeBoldStart(nextToken());

        case Token.BOLD_END -> executeBoldEnd(nextToken());

        case Token.ITALIC_START -> executeItalicStart(nextToken());

        case Token.ITALIC_END -> executeItalicEnd(nextToken());

        case Token.MONO_START -> executeMonoStart(nextToken());

        case Token.MONO_END -> executeMonoEnd(nextToken());

        default -> uoe(token);
      };
    }

    state = executeEof();
  }

  private int executeBlob(int start, int end) {
    return switch (state) {
      case START, CONSTRAINED_END -> {
        addText(Text.REGULAR, start);

        regularEnd = end;

        yield REGULAR;
      }

      case MONOSPACE | START -> {
        addText(Text.REGULAR, start);

        regularEnd = end;

        yield MONOSPACE;
      }

      case BOLD | START -> {
        addText(Text.REGULAR, start);

        regularEnd = end;

        yield BOLD;
      }

      case ITALIC | START -> {
        addText(Text.REGULAR, start);

        regularEnd = end;

        yield ITALIC;
      }

      case REGULAR -> {
        regularEnd = end;

        yield state;
      }

      default -> uoe();
    };
  }

  private int executeBoldEnd(int index) {
    return switch (state) {
      case BOLD -> toConstrainedEnd(Text.BOLD_END, index);

      default -> uoe();
    };
  }

  private int executeBoldStart(int index) {
    return executeConstrainedStart(Text.BOLD_START, Token.BOLD_END, BOLD);
  }

  private int executeConstrainedStart(int startCode, int endToken, int nextState) {
    return switch (state) {
      case START -> {
        if (searchToken(endToken)) {
          addText(startCode);

          yield nextState | START;
        } else {
          yield REGULAR;
        }
      }

      case REGULAR -> {
        if (searchToken(endToken)) {
          addText(regularEnd);
          addText(startCode);

          yield nextState | START;
        } else {
          yield REGULAR;
        }
      }

      default -> uoe();
    };
  }

  private int executeEof() {
    return switch (state) {
      case START, CONSTRAINED_END -> EOF;

      case REGULAR -> {
        addText(regularEnd);

        yield EOF;
      }

      default -> uoe();
    };
  }

  private int executeItalicEnd(int index) {
    return switch (state) {
      case ITALIC -> toConstrainedEnd(Text.ITALIC_END, index);

      default -> uoe();
    };
  }

  private int executeItalicStart(int index) {
    return executeConstrainedStart(Text.ITALIC_START, Token.ITALIC_END, ITALIC);
  }

  private int executeLf() {
    return switch (state) {
      case START -> state;

      case CONSTRAINED_END -> {
        addText(Text.REGULAR, sourceMark);

        regularEnd = sourceMark;

        yield REGULAR;
      }

      case REGULAR -> {
        regularEnd++;

        yield state;
      }

      default -> uoe();
    };
  }

  private int executeMonoEnd(int index) {
    return switch (state) {
      case MONOSPACE -> toConstrainedEnd(Text.MONOSPACE_END, index);

      default -> uoe();
    };
  }

  private int executeMonoStart(int index) {
    return executeConstrainedStart(Text.MONOSPACE_START, Token.MONO_END, MONOSPACE);
  }

  private boolean hasToken() {
    return sourceIndex < sourceLast;
  }

  private int nextToken() {
    return source.token(sourceIndex++);
  }

  private boolean searchToken(int token) {
    sourceMark = sourceIndex;

    var found = false;

    while (hasToken()) {
      var current = nextToken();

      if (current < 0 && current == token) {
        found = true;

        break;
      }
    }

    sourceIndex = sourceMark;

    return found;
  }

  private int toConstrainedEnd(int text, int index) {
    addText(regularEnd);
    addText(text);

    sourceMark = index + 1;

    return CONSTRAINED_END;
  }

  private int uoe() {
    var s = Integer.toBinaryString(state);

    throw new UnsupportedOperationException("Implement me :: state=" + s);
  }

  private int uoe(int t) {
    throw new UnsupportedOperationException("Implement me :: state=" + state + "; token=" + t);
  }

}