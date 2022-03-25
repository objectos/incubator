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
package br.com.objectos.css.parser.sheet;

import br.com.objectos.css.parser.IsNonTerminal;
import br.com.objectos.css.parser.IsTerminal;
import br.com.objectos.css.sheet.StyleSheet;
import br.com.objectos.lexer.Analyzer;
import br.com.objectos.lexer.UncheckedAnalyzer;
import br.com.objectos.parser.Parser;
import br.com.objectos.parser.impl.rd.RecursiveDescentParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public class CssParser {

  private static final Parser<IsNonTerminal, IsTerminal> INSTANCE = new RecursiveDescentParser<>(
      CssParserGrammar.INSTANCE);

  private CssParser() {}

  public static StyleSheet parse(InputStream in) {
    try (Analyzer<IsTerminal> analyzer = CssLexer.get().analyze(in)) {
      return parse0(analyzer);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public static StyleSheet parse(String css) {
    try (Analyzer<IsTerminal> analyzer = CssLexer.get().analyze(css)) {
      return parse0(analyzer);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private static StyleSheet parse0(Analyzer<IsTerminal> analyzer) {
    UncheckedAnalyzer<IsTerminal> source = new UncheckedAnalyzer<>(analyzer);
    return INSTANCE.parse(StyleSheet.class, source);
  }

}