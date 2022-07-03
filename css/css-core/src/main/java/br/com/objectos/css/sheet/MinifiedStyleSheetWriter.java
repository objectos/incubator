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
import br.com.objectos.css.type.ColorHex;
import java.io.IOException;

final class MinifiedStyleSheetWriter extends StyleSheetWriter {

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
    write(combinator.symbol);
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