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
package br.com.objectos.parser.testing.engine;

import static org.testng.Assert.assertEquals;

import br.com.objectos.formal.testing.IsComplex;
import br.com.objectos.formal.testing.IsPrimary;
import br.com.objectos.parser.Parser;
import br.com.objectos.parser.grammar.ParserGrammar;
import java.util.Arrays;
import java.util.Iterator;

abstract class ParserDriver extends ParserDriverDef {

  private Parser<IsComplex, IsPrimary> parser;

  private IsComplex result;

  protected abstract Parser<IsComplex, IsPrimary> parser(ParserGrammar<IsComplex, IsPrimary> grammar);

  @Override
  void givenParserFromGrammarImpl(ParserGrammar<IsComplex, IsPrimary> grammar) {
    parser = parser(grammar);
  }

  @Override
  void whenParseImpl(Class<? extends IsComplex> targetType, IsPrimary... tokens) {
    Iterator<IsPrimary> source = Arrays.asList(tokens).iterator();
    result = parser.parse(targetType, source);
  }

  @Override
  void thenResultImpl(IsComplex expected) {
    assertEquals(result, expected);
  }

}