/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.smtp.command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;
import objectos.lang.Check;
import objectos.util.IntArrays;

public class CommandParser {

  private final CommandParserAdapter adapter;

  private final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);

  private final char[] charArray;

  private final CharBuffer charBuffer = CharBuffer.allocate(512);

  private int charIndex;

  private final CharsetDecoder charsetDecoder;

  private int[] codes = new int[64];
  private int codesEOL;
  private int codesIndex;
  private int codesLength;

  private State state;
  private int stateIndex;
  private int stateSize;

  private int stringOrPathLength;
  private int stringOrPathStart;

  public CommandParser(CommandParserAdapter adapter, CharsetDecoder charsetDecoder) {
    this.adapter = Check.notNull(adapter, "adapter == null");

    this.charsetDecoder = Check.notNull(charsetDecoder, "charsetDecoder == null");

    charArray = charBuffer.array();
  }

  public final boolean hasNext() {
    return codesIndex < codesEOL;
  }

  public final boolean readCommand(ReadableByteChannel client) throws IOException {
    int count;
    count = 0;

    int total;
    total = 0;

    byteBuffer.clear();

    for (;;) {
      count = client.read(byteBuffer);

      if (count == 0) {
        break;
      }

      else if (count < 0) {
        break;
      }

      total += count;
    }

    if (total == 0) {
      return false;
    }

    adapter.onChannelRead(total, byteBuffer.capacity());

    byteBuffer.flip();

    charsetDecoder.decode(byteBuffer, charBuffer, false);

    parse();

    return true;
  }

  public final void startSession() {
    byteBuffer.clear();

    charBuffer.clear();

    charsetDecoder.reset();

    charIndex = 0;

    codesLength = 0;
  }

  final int[] consume() {
    int[] result;
    result = Arrays.copyOfRange(codes, codesIndex, codesEOL);

    codesIndex = codesEOL;

    return result;
  }

  final String getString(int start, int length) {
    return new String(charArray, start, length);
  }

  final boolean hasNextChar() {
    return stateIndex < stateSize;
  }

  final void incStringOrPathLength() {
    stringOrPathLength++;
  }

  final boolean matches(char[] upper, char[] lower) {
    Check.argument(upper.length == lower.length, "lengths must be equal");

    boolean result;
    result = true;

    for (int i = 0; i < upper.length; i++) {
      if (hasNextChar()) {
        char c;
        c = nextChar();

        char upperChar;
        upperChar = upper[i];

        if (c == upperChar) {
          continue;
        }

        char lowerChar;
        lowerChar = lower[i];

        if (c == lowerChar) {
          continue;
        }
      }

      result = false;
      break;
    }

    return result;
  }

  final char nextChar() {
    return charArray[stateIndex++];
  }

  final char peekChar() {
    return charArray[stateIndex];
  }

  final void put(int value) {
    codes = IntArrays.growIfNecessary(codes, codesLength);

    codes[codesLength++] = value;
  }

  final void putCommand(Command command) {
    charIndex = stateIndex;
    put(ByteCodes.COMMAND);
    put(command.getCode());
  }

  final void putEOL() {
    charIndex = stateIndex;
    put(ByteCodes.EOL);
    codesEOL = codesLength;
  }

  final void putPath() {
    putStringOrPath(ByteCodes.PATH);
  }

  final void putString() {
    putStringOrPath(ByteCodes.STRING);
  }

  final void startStringOrPath() {
    stringOrPathStart = stateIndex;
    stringOrPathLength = 0;
  }

  final State toCommandSyntaxError() {
    // return TO_EOL;
    throw new UnsupportedOperationException("Implement me");
  }

  private void parse() {
    state = State.START;
    stateIndex = charIndex;
    stateSize = charBuffer.position();

    while (state != State.END) {
      state = state.execute(this);
    }
  }

  private void putStringOrPath(int code) {
    charIndex = stateIndex;
    put(code);
    put(stringOrPathStart);
    put(stringOrPathLength);
  }

  private enum State {

    ARG {
      @Override
      final State execute(CommandParser o) {
        while (o.hasNextChar()) {
          switch (o.peekChar()) {
            case ' ':
              o.nextChar();
              break;
            case '<':
              o.nextChar();
              return PATH;
            case '\r':
              o.nextChar();
              return CR_ARG;
            case '\n':
              throw new UnsupportedOperationException("Implement me");
            default:
              return STRING;
          }
        }

        throw new UnsupportedOperationException("Implement me");
      }
    },

    CR_ARG {
      @Override
      final State execute(CommandParser o) {
        if (!o.hasNextChar()) {
          throw new UnsupportedOperationException("Implement me");
        }

        switch (o.nextChar()) {
          case '\n':
            o.putEOL();
            return START;
          default:
            throw new UnsupportedOperationException("Implement me");
        }
      }
    },

    CR_STRING {
      @Override
      final State execute(CommandParser o) {
        if (!o.hasNextChar()) {
          throw new UnsupportedOperationException("Implement me");
        }

        switch (o.nextChar()) {
          case '\n':
            o.putString();
            o.putEOL();
            return START;
          default:
            throw new UnsupportedOperationException("Implement me");
        }
      }
    },

    END,

    PATH {
      @Override
      final State execute(CommandParser o) {
        o.startStringOrPath();

        while (o.hasNextChar()) {
          switch (o.nextChar()) {
            default:
              o.incStringOrPathLength();
              continue;
            case '>':
              o.putPath();
              return ARG;
            case '\r':
              throw new UnsupportedOperationException("Implement me");
            case '\n':
              throw new UnsupportedOperationException("Implement me");
          }
        }

        throw new UnsupportedOperationException("Implement me");
      }
    },

    START {
      @Override
      final State execute(CommandParser o) {
        if (!o.hasNextChar()) {
          return END;
        }

        switch (o.peekChar()) {
          case 'D':
          case 'd':
            return execute(o, Command.DATA);
          case 'E':
          case 'e':
            return execute(o, Command.EHLO);
          case 'M':
          case 'm':
            return execute(o, Command.MAIL);
          case 'Q':
          case 'q':
            return execute(o, Command.QUIT);
          case 'R':
          case 'r':
            return execute(o, Command.RCPT);
          default:
            throw new UnsupportedOperationException("Implement me");
        }
      }

      private State execute(CommandParser o, Command candidate) {
        boolean matches;
        matches = candidate.matches(o);

        if (!matches) {
          return o.toCommandSyntaxError();
        }

        o.putCommand(candidate);

        return ARG;
      }
    },

    STRING {
      @Override
      final State execute(CommandParser o) {
        o.startStringOrPath();

        while (o.hasNextChar()) {
          switch (o.nextChar()) {
            default:
              o.incStringOrPathLength();
              continue;
            case ' ':
              o.putString();
              return ARG;
            case '\r':
              return CR_STRING;
            case '\n':
              throw new UnsupportedOperationException("Implement me");
          }
        }

        throw new UnsupportedOperationException("Implement me");
      }
    },

    TO_EOL;

    State execute(CommandParser o) {
      throw new UnsupportedOperationException("Implement me @ " + name());
    }

  }

}
