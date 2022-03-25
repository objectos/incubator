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
package br.com.objectos.lexer.impl.ah;

import static org.testng.Assert.assertEquals;

import br.com.objectos.lexer.Analyzer;
import br.com.objectos.lexer.Lexer;
import br.com.objectos.lexer.UndefinedTokenException;
import br.com.objectos.lexer.grammar.LexerGrammar;
import br.com.objectos.lexer.impl.ah.model.IsBrick;
import br.com.objectos.lexer.impl.ah.model.IsToken;
import org.testng.Assert;

class LexerDriver extends LexerDriverDef {

  private Lexer<IsToken> lexer;
  private Analyzer<IsToken> analyzer;

  @Override
  void givenLexerImpl(LexerGrammar<IsToken, IsBrick> grammar) {
    lexer = AdHocLexer.get(grammar);
  }

  @Override
  void whenInputImpl(String input) {
    analyzer = lexer.analyze(input);
  }

  @Override
  void thenHasNextImpl(boolean expected) {
    try {
      assertEquals(analyzer.hasNext(), expected);
    } catch (UndefinedTokenException e) {
      Assert.fail("uh-oh", e);
    }
  }

  @Override
  void thenNextImpl(IsToken expected) {
    try {
      assertEquals(analyzer.next(), expected);
    } catch (UndefinedTokenException e) {
      Assert.fail("uh-oh", e);
    }
  }

}