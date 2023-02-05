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

import static br.com.objectos.parser.spec.Factory.factoryOf;
import static org.testng.Assert.assertEquals;

import br.com.objectos.formal.testing.ComplexSingle;
import br.com.objectos.formal.testing.HasLetters;
import br.com.objectos.formal.testing.Letter;
import java.util.List;
import org.testng.annotations.Test;

public class ProductionBuilderTest {

  @Test
  public void grammar0_ComplexSingle() {
    NonTerminal symbol = NonTerminal.get(ComplexSingle.class);

    List<Production> res = new ProductionBuilder(ParserKind.TOP_DOWN, symbol)
        .addRuleType(HasLetters.class)
        .andCreateWith(ComplexSingle::new);

    assertEquals(res.size(), 1);
    assertEquals(res.get(0),
      factoryOf(ComplexSingle::new).production(symbol, NonTerminal.get(HasLetters.class)));
  }

  @Test
  public void grammar0_HasLetters() {
    NonTerminal symbol = NonTerminal.get(HasLetters.class);

    List<Production> res = new ProductionBuilder(ParserKind.TOP_DOWN, symbol)
        .addTokenValue(Letter.A)
        .andCreateWith(HasLetters::hasOne);

    assertEquals(res.size(), 1);
    assertEquals(res.get(0),
      factoryOf(HasLetters::hasOne).production(symbol, Terminal.get(Letter.A)));
  }

  @Test
  public void grammar1_HasLetters() {
    NonTerminal symbol = NonTerminal.get(HasLetters.class);

    var res = new ProductionBuilder(ParserKind.TOP_DOWN, symbol)
        .addTokenType(Letter.class).oneOrMore()
        .andCreateWith(HasLetters::ofIterable);

    assertEquals(res.size(), 3);
    assertEquals(res.get(0).toString(), "Letter+ -> Letter Letter+");
    assertEquals(res.get(1).toString(), "Letter+ -> Letter");
    assertEquals(res.get(2).toString(), "<HasLetters> -> Letter+");
  }

}