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
package br.com.objectos.css.sheet;

import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.Combinator;
import java.io.IOException;

final class PrettyStyleSheetWriter extends StyleSheetWriter {
  private static class Indentation {
    private final char c;
    private final int n;

    private int level = 0;

    private Indentation(char c, int n) {
      this.c = c;
      this.n = n;
    }

    public static Indentation standard() {
      return new Indentation(' ', 2);
    }

    public void decrement() {
      level--;
    }

    public void increment() {
      level++;
    }

    public void write(StyleSheetWriter writer) throws IOException {
      int count = level * n;
      for (int i = 0; i < count; i++) {
        writer.write(c);
      }
    }
  }

  private final Indentation indentation = Indentation.standard();

  public final void indent() {
    indentation.increment();
  }

  public final void unindent() {
    indentation.decrement();
  }

  @Override
  public void visitAfterLastDeclaration() throws IOException {
    write(';');
  }

  @Override
  public void visitBeforeNextDeclaration() throws IOException {
    visitAfterLastDeclaration();
    writeNewLine();
  }

  @Override
  public void visitBeforeNextStatement() throws IOException {
    writeNewLine();
    writeNewLine();
  }

  @Override
  public void visitBlockEnd() throws IOException {
    writeNewLine();
    unindent();
    writeIndentation();
    write('}');
  }

  @Override
  public void visitBlockStart() throws IOException {
    write(' ');
    write('{');
    writeNewLine();
    indent();
  }

  @Override
  public void visitCombinator(Combinator combinator) throws IOException {
    switch (combinator) {
      default -> {
        write(' ');
        write(combinator.symbol);
        write(' ');
      }
      case DESCENDANT -> write(' ');
      case LIST -> {
        write(',');
        write(' ');
      }
    }
  }

  @Override
  public void visitDeclarationStart(StandardPropertyName name) throws IOException {
    writeIndentation();
    write(name.getName());
    write(':');
    write(' ');
  }

  @Override
  public void visitEmptyBlock() throws IOException {
    write(' ');
    write('{');
    write('}');
  }

  @Override
  public void visitMultiDeclarationSeparator() throws IOException {
    write(',');
    write(' ');
  }

  @Override
  public void visitRuleStart() throws IOException {
    writeIndentation();
  }

  @Override
  public void writeComma() throws IOException {
    write(',');
    write(' ');
  }

  public void writeIndentation() throws IOException {
    indentation.write(this);
  }

  public void writeNewLine() throws IOException {
    write(System.lineSeparator());
  }

  @Override
  final void quoteIfNecessary(String value) throws IOException {
    quote(value);
  }

  @Override
  final void writeFirstValuePrefix() throws IOException {
    write(' ');
  }

  @Override
  final void writeValueColorHex(String value) throws IOException {
    write(value);
  }
}