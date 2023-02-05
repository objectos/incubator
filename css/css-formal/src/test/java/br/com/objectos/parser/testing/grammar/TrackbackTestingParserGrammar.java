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
package br.com.objectos.parser.testing.grammar;

import br.com.objectos.formal.testing.IsComplex;
import br.com.objectos.formal.testing.IsPrimary;
import br.com.objectos.parser.grammar.AbstractParserGrammar;

public class TrackbackTestingParserGrammar extends AbstractParserGrammar<IsComplex, IsPrimary> {

  private static final TrackbackTestingParserGrammar INSTANCE = new TrackbackTestingParserGrammar();

  private TrackbackTestingParserGrammar() {
  }

  public static TrackbackTestingParserGrammar get() {
    return INSTANCE;
  }

  @Override
  protected final void definition() {
    define(Goal.class)
        .addRule(TrackbackTestingRule.class)
        .andCreateWith(Goal::new);

    define(TrackbackKeyword.class)
        .addToken(TrackbackKeyword.class)
        .andCreateWith(this::keyword);

    define(TrackbackValueType.class)
        .addToken(TrackbackValueType.class)
        .andCreateWith(this::valueType);

    define(Or.class)
        .addRule(OrCombinable.class)
        .addToken(TrackbackSymbol.OR)
        .addRule(OrCombinable.class)
        .andCreateWith(this::or);

    define(Optional.class)
        .addRule(OptionalCombinable.class)
        .addToken(TrackbackSymbol.OPTIONAL)
        .andCreateWith(this::optional);

    define(And.class)
        .addRule(AndCombinable.class)
        .addRule(AndCombinable.class)
        .andCreateWith(And::new);
  }

  private TrackbackKeyword keyword(TrackbackKeyword self) {
    return self;
  }

  private TrackbackValueType valueType(TrackbackValueType self) {
    return self;
  }

  private Optional optional(OptionalCombinable value, TrackbackSymbol optional) {
    return new Optional(value);
  }

  private Or or(OrCombinable first, TrackbackSymbol or, OrCombinable second) {
    return new Or(first, second);
  }

}