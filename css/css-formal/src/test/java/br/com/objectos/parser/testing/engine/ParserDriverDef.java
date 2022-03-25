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
package br.com.objectos.parser.testing.engine;

import br.com.objectos.formal.testing.IsComplex;
import br.com.objectos.formal.testing.IsPrimary;
import br.com.objectos.parser.grammar.ParserGrammar;

abstract class ParserDriverDef {

  final ParserDriverDef givenParserFromGrammar(ParserGrammar<IsComplex, IsPrimary> grammar) {
    givenParserFromGrammarImpl(grammar);
    return this;
  }

  final ParserDriverDef whenParse(Class<? extends IsComplex> targetType, IsPrimary... tokens) {
    whenParseImpl(targetType, tokens);
    return this;
  }

  final ParserDriverDef thenResult(IsComplex expected) {
    thenResultImpl(expected);
    return this;
  }

  ParserDriverDef it() {
    return this;
  }

  abstract void givenParserFromGrammarImpl(ParserGrammar<IsComplex, IsPrimary> grammar);

  abstract void whenParseImpl(Class<? extends IsComplex> targetType, IsPrimary... tokens);

  abstract void thenResultImpl(IsComplex expected);

}