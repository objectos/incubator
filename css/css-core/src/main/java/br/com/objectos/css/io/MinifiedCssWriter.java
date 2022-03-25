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

import br.com.objectos.core.object.Checks;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.select.Combinator;
import br.com.objectos.css.sheet.CompiledStyleSheet;
import br.com.objectos.css.type.ColorHex;
import java.io.IOException;

public class MinifiedCssWriter extends CssWriter {

  MinifiedCssWriter(Appendable out) {
    super(out);
  }

  public static MinifiedCssWriter of(Appendable out) {
    Checks.checkNotNull(out, "out == null");

    return new MinifiedCssWriter(out);
  }

  public static MinifiedCssWriter ofString() {
    return of(new StringBuilder());
  }

  public static String toString(CompiledStyleSheet sheet) {
    try {
      MinifiedCssWriter w;
      w = ofString();

      sheet.acceptCompiledStyleSheetVisitor(w);

      return w.toString();
    } catch (IOException e) {
      throw new AssertionError("StringBuilder should not have thrown IOException", e);
    }
  }

  public static String toString(CssWritable element) {
    try {
      MinifiedCssWriter w = ofString();
      element.acceptCssWriter(w);
      return w.toString();
    } catch (IOException e) {
      throw new AssertionError("IOException should not have occured with a StringBuilder", e);
    }
  }

  @Override
  public final void acceptCssWriterVisitor(CssWriterVisitor visitor) throws IOException {
    visitor.visitMinifiedCssWriter(this);
  }

  @Override
  public final void visitAfterLastDeclaration() throws IOException {
    // noop
  }

  @Override
  public final void visitBeforeNextDeclaration() throws IOException {
    write(';');
  }

  @Override
  public final void visitBeforeNextStatement() throws IOException {
    // noop
  }

  @Override
  public final void visitBlockEnd() throws IOException {
    write('}');
  }

  @Override
  public final void visitBlockStart() throws IOException {
    write('{');
  }

  @Override
  public final void visitCombinator(Combinator combinator) throws IOException {
    combinator.visitMinifiedCssWriter(this);
  }

  @Override
  public final void visitDeclarationStart(StandardPropertyName name) throws IOException {
    write(name.getName());
    write(':');
  }

  @Override
  public final void visitEmptyBlock() throws IOException {
    visitBlockStart();
    visitBlockEnd();
  }

  @Override
  public final void visitMultiDeclarationSeparator() throws IOException {
    write(',');
  }

  @Override
  public final void visitRuleStart() throws IOException {
    // noop
  }

  @Override
  public final void writeComma() throws IOException {
    write(',');
  }

  @Override
  final void quoteIfNecessary(String value) throws IOException {
    boolean shouldQuote = false;

    char[] array;
    array = value.toCharArray();

    for (int i = 0; i < array.length; i++) {
      char c;
      c = array[i];

      if (Character.isLetterOrDigit(c)) {
        continue;
      }

      shouldQuote = true;

      break;
    }

    if (shouldQuote) {
      quote(value);
    } else {
      write(value);
    }
  }

  @Override
  final void writeDoubleImpl(double value) throws IOException {
    String string = Double.toString(value);

    if (string.startsWith("0.")) {
      string = string.substring(1);
    }

    write(string);
  }

  @Override
  final void writeFirstValuePrefix() throws IOException {
    // noop
  }

  @Override
  final void writeValueColorHex(String value) throws IOException {
    ColorHex color;
    color = ColorHex.of(value);

    write(color.getMinifiedHexString());
  }

}