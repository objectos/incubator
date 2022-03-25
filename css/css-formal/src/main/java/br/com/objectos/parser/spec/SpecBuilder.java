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
package br.com.objectos.parser.spec;

import br.com.objectos.parser.grammar.Constructor1;
import br.com.objectos.parser.grammar.Constructor2;
import br.com.objectos.parser.grammar.Constructor3;
import br.com.objectos.parser.grammar.Constructor4;
import br.com.objectos.parser.grammar.Constructor5;
import br.com.objectos.parser.grammar.Constructor6;
import br.com.objectos.parser.grammar.Constructor7;
import br.com.objectos.parser.grammar.ParserGrammarDsl;
import br.com.objectos.parser.grammar.ParserGrammarDslDefine;
import java.util.List;
import java.util.Objects;

public class SpecBuilder<R, T> implements ParserGrammarDsl<R, T> {

  private final ParserKind parserKind;
  private final SpecBuilderDelegate delegate;

  private ProductionBuilder builder;

  @Deprecated
  SpecBuilder(ParserKind parserKind) {
    this(parserKind, SpecBuilderDelegate.noop());
  }

  SpecBuilder(ParserKind parserKind, SpecBuilderDelegate delegate) {
    this.parserKind = parserKind;
    this.delegate = delegate;
  }

  @Override
  public final <E extends R> ParserGrammarDslDefine<E, R, T> define(Class<E> ruleType) {
    builder = new ProductionBuilder(parserKind, ruleType);
    return new SpecBuilderDefine<>(this);
  }

  public final Spec build() {
    return toStage01().toStage02().toSpec();
  }

  final <E extends R> void addRuleType(Class<E> ruleType) {
    builder.addRuleType(ruleType);
  }

  final <E extends T> void addTokenType(Class<E> tokenType) {
    builder.addTokenType(tokenType);
  }

  final <E extends T> void addTokenValue(E token) {
    builder.addTokenValue(token);
  }

  final <E, A1> SpecBuilder<R, T> andCreateWith(Constructor1<E, A1> constructor) {
    Objects.requireNonNull(constructor);
    return andCreateWith0(builder.andCreateWith(constructor));
  }

  final <E, A1, A2> SpecBuilder<R, T> andCreateWith(Constructor2<E, A1, A2> constructor) {
    Objects.requireNonNull(constructor);
    return andCreateWith0(builder.andCreateWith(constructor));
  }

  final <E, A1, A2, A3> SpecBuilder<R, T> andCreateWith(Constructor3<E, A1, A2, A3> constructor) {
    Objects.requireNonNull(constructor);
    return andCreateWith0(builder.andCreateWith(constructor));
  }

  final <E, A1, A2, A3, A4> SpecBuilder<R, T> andCreateWith(Constructor4<E, A1, A2, A3, A4> constructor) {
    Objects.requireNonNull(constructor);
    return andCreateWith0(builder.andCreateWith(constructor));
  }

  final <E, A1, A2, A3, A4, A5> SpecBuilder<R, T> andCreateWith(Constructor5<E, A1, A2, A3, A4, A5> constructor) {
    Objects.requireNonNull(constructor);
    return andCreateWith0(builder.andCreateWith(constructor));
  }

  final <E, A1, A2, A3, A4, A5, A6> SpecBuilder<R, T> andCreateWith(
      Constructor6<E, A1, A2, A3, A4, A5, A6> constructor) {
    Objects.requireNonNull(constructor);
    return andCreateWith0(builder.andCreateWith(constructor));
  }

  final <E, A1, A2, A3, A4, A5, A6, A7> SpecBuilder<R, T> andCreateWith(
      Constructor7<E, A1, A2, A3, A4, A5, A6, A7> constructor) {
    Objects.requireNonNull(constructor);
    return andCreateWith0(builder.andCreateWith(constructor));
  }

  final void oneOrMore() {
    builder.oneOrMore();
  }

  final void optional() {
    builder.optional();
  }

  final void zeroOrMore() {
    builder.zeroOrMore();
  }

  private SpecBuilder<R, T> andCreateWith0(List<Production> list) {
    for (Production production : list) {
      delegate.addProduction(production);
    }
    return this;
  }

  private Stage01 toStage01() {
    return ((Stage01Builder) delegate).build();
  }

}