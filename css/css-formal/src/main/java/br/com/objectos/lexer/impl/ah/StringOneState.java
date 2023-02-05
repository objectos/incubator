/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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

import br.com.objectos.lexer.charexp.CharExpression;
import java.util.Optional;

class StringOneState extends AbstractStringState implements NonGreedyBound {

  StringOneState(String value, State state) {
    super(value, state);
  }

  @Override
  public final State next(StateSubject subject) {
    LexemeSubject lexeme = subject.lexemeSubject();
    if (lexeme.matchesString(value)) {
      lexeme.consumeString(value);
      return state;
    } else {
      return new UndefinedTokenState(this);
    }
  }

  @Override
  public final State nextZeroOrMoreNonGreedy(StateSubject subject, CharExpression expression, int count) {
    LexemeSubject lexeme = subject.lexemeSubject();
    Optional<String> maybe = lexeme.peekString(value);
    if (maybe.isPresent()) {
      String matched = maybe.get();
      lexeme.consumeString(matched);
      for (int i = 0; i < count; i++) {
        lexeme.commit();
      }
      lexeme.consumeString(value);
      return state;
    } else {
      return new UndefinedTokenState(this);
    }
  }

}