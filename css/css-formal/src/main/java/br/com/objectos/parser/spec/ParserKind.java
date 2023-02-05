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
package br.com.objectos.parser.spec;

import br.com.objectos.parser.grammar.ParserGrammar;
import objectos.util.GrowableList;

public enum ParserKind {

  TOP_DOWN {
    @Override
    final void acceptRepetition(Repetition repetition, GrowableList<Production> list) {
      repetition.forTopDown(list);
    }
  };

  public final <R, T> SpecBuilder<R, T> specBuilder() {
    return specBuilder(new Stage01Builder());
  }

  public final <R, T> Spec specOf(ParserGrammar<R, T> grammar) {
    SpecBuilder<R, T> builder = specBuilder();
    grammar.acceptParserGrammarDsl(builder);
    return builder.build();
  }

  final <R, T> SpecBuilder<R, T> specBuilder(SpecBuilderDelegate delegate) {
    return new SpecBuilder<>(this, delegate);
  }

  abstract void acceptRepetition(Repetition repetition, GrowableList<Production> list);

}