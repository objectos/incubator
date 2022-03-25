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
package br.com.objectos.lexer.grammar;

import java.util.Objects;

public abstract class AbstractLexerGrammar<T, B> implements LexerGrammar<T, B> {

  private LexerGrammarDsl<T, B> dsl;

  protected AbstractLexerGrammar() {
  }

  @Override
  public final synchronized void acceptLexerGrammarDsl(LexerGrammarDsl<T, B> dsl) {
    if (this.dsl != null) {
      throw new IllegalStateException("Only one Dsl instance per time.");
    }

    this.dsl = Objects.requireNonNull(dsl);
    try {
      definition();
    } finally {
      this.dsl = null;
    }
  }

  protected abstract void definition();

  protected final <E extends B> LexerGrammarDslDefineRuleType<E, T, B> defineBrick(Class<E> brickType) {
    return dsl().defineBrick(brickType);
  }

  protected final LexerGrammarDslDefineRuleValue<T, B> defineBrick(B brickValue) {
    return dsl().defineBrick(brickValue);
  }

  protected final <E extends T> LexerGrammarDslDefineRuleType<E, T, B> defineToken(Class<E> tokenType) {
    return dsl().defineToken(tokenType);
  }

  protected final LexerGrammarDslDefineRuleValue<T, B> defineToken(T tokenValue) {
    return dsl().defineToken(tokenValue);
  }

  private LexerGrammarDsl<T, B> dsl() {
    return dsl;
  }

}