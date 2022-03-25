/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.parser.select;

import br.com.objectos.css.parser.IsTerminal;
import br.com.objectos.lexer.Lexer;
import br.com.objectos.lexer.impl.ah.AdHocLexer;

class SelectorLexer {

  private SelectorLexer() {}

  public static Lexer<IsTerminal> get() {
    return LexerHolder.LEXER;
  }

  private static class LexerHolder {
    static Lexer<IsTerminal> LEXER = initLexer();
  }

  private static Lexer<IsTerminal> initLexer() {
    SelectorLexerGrammar grammar = new SelectorLexerGrammar();
    return AdHocLexer.get(grammar);
  }

}