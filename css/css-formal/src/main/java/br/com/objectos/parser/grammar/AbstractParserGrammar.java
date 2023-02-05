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
package br.com.objectos.parser.grammar;

import java.util.Objects;

public abstract class AbstractParserGrammar<R, T> implements ParserGrammar<R, T> {

  private ParserGrammarDsl<R, T> dsl;

  protected AbstractParserGrammar() {
  }

  @Override
  public final synchronized void acceptParserGrammarDsl(ParserGrammarDsl<R, T> dsl) {
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

  protected <E extends R> ParserGrammarDslDefine<E, R, T> define(Class<E> ruleType) {
    return dsl().define(ruleType);
  }

  protected ParserGrammarDsl<R, T> dsl() {
    return dsl;
  }

}