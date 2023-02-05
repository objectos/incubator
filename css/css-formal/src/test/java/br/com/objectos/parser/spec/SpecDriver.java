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

import static org.testng.Assert.assertEquals;

import br.com.objectos.formal.testing.IsComplex;
import br.com.objectos.formal.testing.IsPrimary;
import br.com.objectos.parser.grammar.ParserGrammar;
import java.util.Arrays;

class SpecDriver extends SpecDriverDef {

  private SpecBuilder<IsComplex, IsPrimary> builder;
  private Spec spec;

  @Override
  void givenRecursiveDescentParserImpl() {
    builder = ParserKind.TOP_DOWN.specBuilder();
  }

  @Override
  void whenGrammarImpl(ParserGrammar<IsComplex, IsPrimary> grammar) {
    grammar.acceptParserGrammarDsl(builder);
    spec = builder.build();
  }

  @Override
  void thenProductionCountImpl(int expected) {
    assertEquals(spec.productionListSize(), expected);
  }

  @Override
  void thenToStringImpl(String... expected) {
    assertEquals(spec.productionListToString(), Arrays.asList(expected));
  }

}