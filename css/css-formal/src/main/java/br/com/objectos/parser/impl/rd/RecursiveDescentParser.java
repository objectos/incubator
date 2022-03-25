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
package br.com.objectos.parser.impl.rd;

import br.com.objectos.parser.Parser;
import br.com.objectos.parser.grammar.ParserGrammar;
import br.com.objectos.parser.spec.ParserKind;
import br.com.objectos.parser.spec.Spec;
import br.com.objectos.parser.spec.SpecBuilder;
import java.util.Iterator;
import java.util.Objects;

public class RecursiveDescentParser<R, T> implements Parser<R, T> {

  private final Spec spec;

  public RecursiveDescentParser(ParserGrammar<R, T> grammar) {
    spec = toSpec(grammar);
  }

  static <R, T> Spec toSpec(ParserGrammar<R, T> grammar) {
    Objects.requireNonNull(grammar);
    SpecBuilder<R, T> builder = ParserKind.TOP_DOWN.specBuilder();
    grammar.acceptParserGrammarDsl(builder);
    return builder.build();
  }

  @Override
  public final <E extends R> E parse(Class<E> goalType, Iterator<T> iterator) {
    Processor processor = new Processor(spec, iterator);
    return processor.parse(goalType);
  }

}
