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

import br.com.objectos.formal.testing.IsComplex;
import br.com.objectos.formal.testing.IsPrimary;
import br.com.objectos.parser.grammar.ParserGrammar;
import br.com.objectos.parser.testing.grammar.Grammar00;
import br.com.objectos.parser.testing.grammar.Grammar04;
import java.util.IdentityHashMap;
import java.util.Map;

public abstract class AbstractParserSpecTest {

  private static final ParserGrammar<?, ?>[] GRAMMARS = {
      new Grammar00(),
      null,
      null,
      new Grammar04()
  };

  private static final Map<ParserGrammar<?, ?>, Spec> SPECS = new IdentityHashMap<>();

  static {
    for (ParserGrammar<?, ?> grammar : GRAMMARS) {
      if (grammar != null) {
        SPECS.put(grammar, ParserKind.TOP_DOWN.specOf(grammar));
      }
    }
  }

  @SuppressWarnings("unchecked")
  final ParserGrammar<IsComplex, IsPrimary> grammar(int index) {
    return (ParserGrammar<IsComplex, IsPrimary>) GRAMMARS[index];
  }

  final Stage01 ofRecursiveDescentParser(ParserGrammar<IsComplex, IsPrimary> grammar) {
    Stage01Builder stage01 = new Stage01Builder();
    SpecBuilder<IsComplex, IsPrimary> builder = ParserKind.TOP_DOWN.specBuilder(stage01);
    grammar.acceptParserGrammarDsl(builder);
    return stage01.build();
  }

  final Production production(ParserGrammar<IsComplex, IsPrimary> grammar, Class<?> symbol) {
    Spec spec = SPECS.get(grammar);
    NonTerminal nonTerminal = NonTerminal.get(symbol);
    return spec.productionQuery(nonTerminal)
        .findFirst()
        .get();
  }

  final Stage02 toStage02(Stage01 stage01) {
    return stage01.toStage02();
  }

}