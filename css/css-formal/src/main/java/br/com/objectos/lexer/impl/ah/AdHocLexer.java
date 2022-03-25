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

import br.com.objectos.lexer.Analyzer;
import br.com.objectos.lexer.Lexer;
import br.com.objectos.lexer.grammar.LexerGrammar;
import java.io.IOException;
import java.io.Reader;

public class AdHocLexer<T> implements Lexer<T> {

  private final Spec spec;

  private AdHocLexer(Spec spec) {
    this.spec = spec;
  }

  public static <T, B> Lexer<T> get(LexerGrammar<T, B> grammar) {
    Spec spec = new SpecCompiler<>(grammar).compile();
    return new AdHocLexer<>(spec);
  }

  @Override
  public final Analyzer<T> analyze(Reader reader, int size) throws IOException {
    return analyze(toString(reader, size));
  }

  @Override
  public final Analyzer<T> analyze(String input) {
    Source source = new StringSource(input);
    return analyze0(source);
  }

  @Override
  public final String toString() {
    return spec.toString();
  }

  private Analyzer<T> analyze0(Source source) {
    return new AdHocAnalyzer<>(spec, source);
  }

  private String toString(Reader reader, int size) throws IOException {
    StringBuilder out = new StringBuilder();
    char[] buffer = new char[size];
    int read = 0;
    while ((read = reader.read(buffer)) != -1) {
      out.append(buffer, 0, read);
    }
    return out.toString();
  }

}