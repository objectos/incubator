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
package br.com.objectos.css.io;

import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.css.sheet.CompiledStyleSheet;
import java.io.IOException;
import objectos.lang.Checks;

public class PrettyCssWriter extends CssWriter {

  private final Indentation indentation;

  PrettyCssWriter(Appendable out, Indentation indentation) {
    super(out);
    this.indentation = indentation;
  }

  public static PrettyCssWriter of(Appendable out) {
    Checks.checkNotNull(out, "out == null");

    Indentation indentation = Indentation.standard();

    return new PrettyCssWriter(out, indentation);
  }

  public static PrettyCssWriter ofString() {
    return of(new StringBuilder());
  }

  public static String toString(CompiledStyleSheet sheet) {
    try {
      PrettyCssWriter w;
      w = ofString();

      sheet.acceptCompiledStyleSheetVisitor(w);

      return w.toString();
    } catch (IOException e) {
      throw new AssertionError("StringBuilder should not have thrown IOException", e);
    }
  }

  public static String toString(CssWritable element) {
    try {
      PrettyCssWriter w = ofString();
      element.acceptCssWriter(w);
      return w.toString();
    } catch (IOException e) {
      throw new AssertionError("IOException should not have occured with a StringBuilder", e);
    }
  }

  @Override
  public final void acceptCssWriterVisitor(CssWriterVisitor visitor) throws IOException {
    visitor.visitPrettyCssWriter(this);
  }

  public final void indent() {
    indentation.increment();
  }

  public final void unindent() {
    indentation.decrement();
  }

  @Override
  public final void visitAfterLastDeclaration() throws IOException {
    write(';');
  }

  @Override
  public final void visitBeforeNextDeclaration() throws IOException {
    visitAfterLastDeclaration();
    writeNewLine();
  }

  @Override
  public final void visitBeforeNextStatement() throws IOException {
    writeNewLine();
    writeNewLine();
  }

  @Override
  public final void visitBlockEnd() throws IOException {
    writeNewLine();
    unindent();
    writeIndentation();
    write('}');
  }

  @Override
  public final void visitBlockStart() throws IOException {
    write(' ');
    write('{');
    writeNewLine();
    indent();
  }

  @Override
  public final void visitCombinator(Combinator combinator) throws IOException {
    combinator.visitPrettyCssWriter(this);
  }

  @Override
  public final void visitDeclarationStart(StandardPropertyName name) throws IOException {
    writeIndentation();
    write(name.getName());
    write(':');
    write(' ');
  }

  @Override
  public final void visitEmptyBlock() throws IOException {
    write(' ');
    write('{');
    write('}');
  }

  @Override
  public final void visitMultiDeclarationSeparator() throws IOException {
    write(',');
    write(' ');
  }

  @Override
  public final void visitRuleStart() throws IOException {
    writeIndentation();
  }

  @Override
  public final void writeComma() throws IOException {
    write(',');
    write(' ');
  }

  public final void writeIndentation() throws IOException {
    indentation.write(this);
  }

  public final void writeNewLine() throws IOException {
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