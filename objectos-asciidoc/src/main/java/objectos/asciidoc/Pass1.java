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

  private static final int HEADING = 1 << 3;

  private static final int METADATA = 1 << 4;

  private int[] proto;

  private int protoIndex;

  private int state;

  private Source source;

  private int tokenIndex;

  private int tokenStart;

  Pass1() {
    proto = new int[512];
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

    protoIndex = 0;

    state = START;

    execute0();
  }

  final int[] toCode() {
    return Arrays.copyOf(proto, protoIndex);
  }

  private void addProto(int p0) {
    proto = IntArrays.copyIfNecessary(proto, protoIndex);

    proto[protoIndex++] = p0;
  }

  private void addProto(int p0, int p1) {
    proto = IntArrays.copyIfNecessary(proto, protoIndex + 1);

    proto[protoIndex++] = p0;
    proto[protoIndex++] = p1;
  }

  private void addProto(int p0, int p1, int p2, int p3) {
    proto = IntArrays.copyIfNecessary(proto, protoIndex + 3);

    proto[protoIndex++] = p0;
    proto[protoIndex++] = p1;
    proto[protoIndex++] = p2;
    proto[protoIndex++] = p3;
  }

  private void execute0() {
    while (hasToken()) {
      tokenIndex = source.tokenCursor();

      var token = nextToken();

      state = switch (token) {
        case Token.EOF -> parseEof();

        case Token.LINE_START -> parseLineStart();

        case Token.LINE_END -> parseLineEnd();

        case Token.HEADING -> parseHeading(nextToken(), nextToken(), nextToken());

        case Token.WORD -> parseWord(nextToken(), nextToken());

        case Token.SP -> parseSpace();

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

  private int parseEof() {
    return switch (state) {
      case DOCUMENT | METADATA -> {
        addProto(Proto.DOCUMENT_END);

        yield EOF;
      }
      default -> uoe();
    };
  }

  private int parseHeading(int level, int start, int end) {
    return switch (state) {
      case DOCUMENT | HEADING -> {
        addProto(Proto.HEADING_START, level);

        yield DOCUMENT | HEADING | START;
      }
      default -> uoe();
    };
  }

  private int parseLineEnd() {
    return switch (state) {
      case DOCUMENT | HEADING | NEXT -> {
        addProto(
          Proto.TOKENS, tokenStart, tokenIndex,
          Proto.HEADING_END
        );

        yield DOCUMENT | METADATA;
      }
      default -> uoe();
    };
  }

  private int parseLineStart() {
    return switch (state) {
      case START -> {
        addProto(Proto.DOCUMENT_START);

        yield DOCUMENT | HEADING;
      }
      default -> uoe();
    };
  }

  private int parseSpace() {
    return switch (state) {
      case DOCUMENT | HEADING | NEXT -> state;
      default -> uoe();
    };
  }

  private int parseWord(int start, int end) {
    return switch (state) {
      case DOCUMENT | HEADING | START -> {
        tokenStart = tokenIndex;

        yield DOCUMENT | HEADING | NEXT;
      }
      case DOCUMENT | HEADING | NEXT -> state;
      default -> uoe();
    };
  }

  private int uoe() {
    var s = Integer.toBinaryString(state);

    throw new UnsupportedOperationException("Implement me :: state=" + s);
  }

}