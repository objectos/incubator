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

import br.com.objectos.formal.testing.IsComplex;
import br.com.objectos.formal.testing.IsPrimary;
import br.com.objectos.parser.grammar.ParserGrammar;

abstract class SpecDriverDef {

  final SpecDriverDef givenRecursiveDescentParser() {
    givenRecursiveDescentParserImpl();
    return this;
  }

  final SpecDriverDef whenGrammar(ParserGrammar<IsComplex, IsPrimary> grammar) {
    whenGrammarImpl(grammar);
    return this;
  }

  final SpecDriverDef thenProductionCount(int expected) {
    thenProductionCountImpl(expected);
    return this;
  }

  final SpecDriverDef thenToString(String... expected) {
    thenToStringImpl(expected);
    return this;
  }

  SpecDriverDef it() {
    return this;
  }

  abstract void givenRecursiveDescentParserImpl();

  abstract void whenGrammarImpl(ParserGrammar<IsComplex, IsPrimary> grammar);

  abstract void thenProductionCountImpl(int expected);

  abstract void thenToStringImpl(String... expected);

}