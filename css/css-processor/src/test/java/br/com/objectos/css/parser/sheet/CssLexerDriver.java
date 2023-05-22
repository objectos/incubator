/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.css.parser.sheet;

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.parser.IsTerminal;
import br.com.objectos.lexer.Analyzer;
import br.com.objectos.lexer.UncheckedAnalyzer;
import objectos.util.UnmodifiableList;

class CssLexerDriver extends CssLexerDriverDef {

  private String css;
  private UnmodifiableList<IsTerminal> tokenList;

  @Override
  void givenCssImpl(String css) {
    this.css = css;
  }

  @Override
  CssLexerDriverDef it() {
    css = "";
    return super.it();
  }

  @Override
  void thenTokenListImpl(IsTerminal[] expected) {
    assertEquals(tokenList, UnmodifiableList.copyOf(expected));
  }

  @Override
  void whenAnalyzeImpl() {
    Analyzer<IsTerminal> analyzer = CssLexer.get().analyze(css);
    tokenList = UnmodifiableList.copyOf(new UncheckedAnalyzer<>(analyzer));
  }

}