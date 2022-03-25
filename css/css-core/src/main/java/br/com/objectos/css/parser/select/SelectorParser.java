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

import br.com.objectos.css.parser.IsNonTerminal;
import br.com.objectos.css.parser.IsTerminal;
import br.com.objectos.css.select.Selector;
import br.com.objectos.lexer.Analyzer;
import br.com.objectos.lexer.UncheckedAnalyzer;
import br.com.objectos.parser.Parser;
import br.com.objectos.parser.impl.rd.RecursiveDescentParser;

public class SelectorParser {

  public static Selector parse(String text) {
    Analyzer<IsTerminal> analyzer = SelectorLexer.get().analyze(text);
    UncheckedAnalyzer<IsTerminal> iterator = new UncheckedAnalyzer<>(analyzer);
    return get().parse(Goal.class, iterator).get();
  }

  static Parser<IsNonTerminal, IsTerminal> get() {
    return ParserHolder.PARSER;
  }

  private static class ParserHolder {
    static Parser<IsNonTerminal, IsTerminal> PARSER = initParser();
  }

  private static Parser<IsNonTerminal, IsTerminal> initParser() {
    SelectorParserGrammar grammar = new SelectorParserGrammar();
    return new RecursiveDescentParser<>(grammar);
  }

}