/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.lexer.impl.ah;

import java.util.Arrays;

class CharArrayState implements State {

  private final char[] charArray;
  private final State[] stateArray;
  private final State failState;

  CharArrayState(char[] charArray, State[] stateArray, State failState) {
    this.charArray = charArray;
    this.stateArray = stateArray;
    this.failState = failState == null ? new UndefinedTokenState(this) : failState;
  }

  @Override
  public final boolean hasNext() {
    return true;
  }

  @Override
  public final State next(StateSubject subject) {
    LexemeSubject lexeme = subject.lexemeSubject();
    
    if (!lexeme.hasNext()) {
      return failState;
    }
    
    char nextChar = lexeme.peekChar();
    int index = Arrays.binarySearch(charArray, nextChar);

    if (index < 0) {
      return failState;
    }

    lexeme.consumeChar(nextChar);
    return stateArray[index];
  }

  public final int size() {
    return charArray.length;
  }

  @Override
  public final String toString() {
    return State.toString(this);
  }

  @Override
  public final StateWriter toString(StateWriter sw) {
    String prefix = sw.removePrefix();
    int length = charArray.length;

    for (int i = 0; i < length; i++) {
      char c = charArray[i];
      State next = stateArray[i];
      sw = next.toString(sw.write(prefix).begin().writeQuote(c)).writeNewLine();
    }

    if (!(failState instanceof UndefinedTokenState)) {
      return failState.toString(sw.write(prefix));
    } else {
      return sw.deleteLastChar();
    }
  }

}