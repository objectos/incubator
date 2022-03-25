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
package br.com.objectos.parser.testing.grammar;

import br.com.objectos.formal.testing.ComplexSingle;
import br.com.objectos.formal.testing.HasLetters;
import br.com.objectos.formal.testing.IsComplex;
import br.com.objectos.formal.testing.IsPrimary;
import br.com.objectos.formal.testing.Letter;
import br.com.objectos.parser.grammar.AbstractParserGrammar;

public class Grammar07 extends AbstractParserGrammar<IsComplex, IsPrimary> {

  @Override
  protected void definition() {
    // <HasLetters> -> Letter*
    define(HasLetters.class)
        .addToken(Letter.class).zeroOrMore()
        .andCreateWith(HasLetters::ofIterable);

    // <ComplexSingle> -> <HasLetters>
    define(ComplexSingle.class)
        .addRule(HasLetters.class)
        .andCreateWith(ComplexSingle::new);
  }

}