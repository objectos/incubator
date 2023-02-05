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
import br.com.objectos.parser.testing.grammar.Grammar02;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

public class SpecBuilderTest {

  @Test
  public void grammar2_RD() {
    List<Production> it = ofRecursiveDescentParser(new Grammar02());
    List<String> res = it.stream().map(Object::toString).collect(Collectors.toList());
    assertEquals(res.size(), 4);
    assertEquals(res.get(0), "Letter+ -> Letter Letter+");
    assertEquals(res.get(1), "Letter+ -> Letter");
    assertEquals(res.get(2), "<HasLetters> -> Letter+");
    assertEquals(res.get(3), "<ComplexSingle> -> <HasSomething>");
  }

  private List<Production> ofRecursiveDescentParser(ParserGrammar<IsComplex, IsPrimary> grammar) {
    List<Production> it = new ArrayList<>();
    SpecBuilder<IsComplex, IsPrimary> builder = ParserKind.TOP_DOWN.specBuilder(it::add);
    grammar.acceptParserGrammarDsl(builder);
    return it;
  }

}