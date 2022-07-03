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

import br.com.objectos.css.function.StandardFunctionName;
import br.com.objectos.css.keyword.StandardKeyword;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.SimpleSelector;
import br.com.objectos.css.select.UniversalSelector;
import br.com.objectos.css.type.AngleUnit;
import br.com.objectos.css.type.ColorName;
import br.com.objectos.css.type.LengthUnit;
import java.io.IOException;
import objectos.lang.Check;

public abstract class StyleSheetWriter extends StyleSheetEngine<IOException> {

  private enum NoOpAppendable implements Appendable {
    INSTANCE;

    @Override
    public final Appendable append(char c) throws IOException {
      return this;
    }

    @Override
    public final Appendable append(CharSequence csq) throws IOException {
      return this;
    }

    @Override
    public final Appendable append(CharSequence csq, int start, int end) throws IOException {
      return this;
    }
  }

  Appendable out = NoOpAppendable.INSTANCE;

  StyleSheetWriter() {}

  public static StyleSheetWriter ofMinified() {
    return new MinifiedStyleSheetWriter();
  }

  public static StyleSheetWriter ofPretty() {
    return new PrettyStyleSheetWriter();
  }

  public static String toMinifiedString(StyleSheet sheet) {
    try {
      var writer = StyleSheetWriter.ofMinified();

      var out = new StringBuilder();

      writer.writeTo(sheet, out);

      return out.toString();
    } catch (IOException e) {
      throw new AssertionError("StringBuilder does not throw IOException", e);
    }
  }

  @Override
  public void visitAngle(AngleUnit unit, double value) throws IOException {
    writeDouble(value);
    write(unit.getName());
  }

  @Override
  public void visitAngle(AngleUnit unit, int value) throws IOException {
    writeInt(value);
    write(unit.getName());
  }

  @Override
  public void visitAttributeSelector(String attributeName) throws IOException {
    write('[');
    write(attributeName);
    write(']');
  }

  @Override
  public void visitAttributeValueSelector(
      String attributeName, AttributeValueOperator operator, String value)
      throws IOException {
    write('[');
    write(attributeName);
    write(operator.getSymbol());
    quoteIfNecessary(value);
    write(']');
  }

  @Override
  public void visitBeforeNextValue() throws IOException {
    write(' ');
  }

  @Override
  public void visitClassSelector(String className) throws IOException {
    write('.');
    write(className);
  }

  @Override
  public void visitColor(ColorName value) throws IOException {
    write(value.getName());
  }

  @Override
  public void visitColor(String value) throws IOException {
    writeValueColorHex(value);
  }

  @Override
  public void visitDouble(double value) throws IOException {
    writeDoubleImpl(value);
  }

  @Override
  public void visitFunctionEnd() throws IOException {
    write(')');
  }

  @Override
  public void visitFunctionStart(StandardFunctionName name) throws IOException {
    write(name.getName());
    write('(');
  }

  @Override
  public void visitIdSelector(String id) throws IOException {
    write('#');
    write(id);
  }

  @Override
  public void visitInt(int value) throws IOException {
    write(Integer.toString(value));
  }

  @Override
  public void visitKeyword(StandardKeyword value) throws IOException {
    write(value.getName());
  }

  @Override
  public void visitKeyword(String keyword) throws IOException {
    write(keyword);
  }

  @Override
  public void visitLength(LengthUnit unit, double value) throws IOException {
    writeDouble(value);
    write(unit.getName());
  }

  @Override
  public void visitLength(LengthUnit unit, int value) throws IOException {
    writeInt(value);
    write(unit.getName());
  }

  @Override
  public void visitLogicalExpressionEnd() throws IOException {
    write(')');
  }

  @Override
  public void visitLogicalExpressionStart(LogicalOperator operator) throws IOException {
    write(' ');
    write(operator.getName());
    write(' ');
    write('(');
  }

  @Override
  public void visitMediaStart() throws IOException {
    write("@media");
  }

  @Override
  public void visitMediaType(MediaType type) throws IOException {
    write(' ');
    write(type.getName());
  }

  @Override
  public void visitPercentage(double value) throws IOException {
    writeDouble(value);
    write('%');
  }

  @Override
  public void visitPercentage(int value) throws IOException {
    writeInt(value);
    write('%');
  }

  @Override
  public void visitRgb(double r, double g, double b) throws IOException {
    writeRgbStart();
    writeDouble(r);
    writeComma();
    writeDouble(g);
    writeComma();
    writeDouble(b);
    write(')');
  }

  @Override
  public void visitRgb(double r, double g, double b, double alpha) throws IOException {
    writeRgbStart();
    writeDouble(r);
    writeComma();
    writeDouble(g);
    writeComma();
    writeDouble(b);
    writeComma();
    writeDouble(alpha);
    write(')');
  }

  @Override
  public void visitRgb(int r, int g, int b) throws IOException {
    writeRgbStart();
    writeInt(r);
    writeComma();
    writeInt(g);
    writeComma();
    writeInt(b);
    write(')');
  }

  @Override
  public void visitRgb(int r, int g, int b, double alpha) throws IOException {
    writeRgbStart();
    writeInt(r);
    writeComma();
    writeInt(g);
    writeComma();
    writeInt(b);
    writeComma();
    writeDouble(alpha);
    write(')');
  }

  @Override
  public void visitRgba(double r, double g, double b, double alpha) throws IOException {
    writeRgbaStart();
    writeDouble(r);
    writeComma();
    writeDouble(g);
    writeComma();
    writeDouble(b);
    writeComma();
    writeDouble(alpha);
    write(')');
  }

  @Override
  public void visitRgba(int r, int g, int b, double alpha) throws IOException {
    writeRgbaStart();
    writeInt(r);
    writeComma();
    writeInt(g);
    writeComma();
    writeInt(b);
    writeComma();
    writeDouble(alpha);
    write(')');
  }

  @Override
  public void visitSimpleSelector(SimpleSelector selector) throws IOException {
    write(selector.toString());
  }

  @Override
  public void visitString(String value) throws IOException {
    quoteIfNecessary(value);
  }

  @Override
  public void visitUniversalSelector(UniversalSelector selector) throws IOException {
    write('*');
  }

  @Override
  public void visitUri(String value) throws IOException {
    write("url(");
    quoteIfNecessary(value);
    write(')');
  }

  public void write(char c) throws IOException {
    out.append(c);
  }

  public void write(String s) throws IOException {
    out.append(s);
  }

  public abstract void writeComma() throws IOException;

  public final void writeTo(StyleSheet sheet, Appendable out) throws IOException {
    Check.notNull(sheet, "sheet == null");
    this.out = Check.notNull(out, "out == null");

    reset();

    sheet.eval(this);

    compile();

    execute();
  }

  public void writeUrl(String src) throws IOException {
    write("url(");
    write('"');
    write(src);
    write('"');
    write(')');
  }

  final void quote(String value) throws IOException {
    write('"');
    write(value);
    write('"');
  }

  abstract void quoteIfNecessary(String value) throws IOException;

  void writeDoubleImpl(double value) throws IOException {
    write(Double.toString(value));
  }

  abstract void writeFirstValuePrefix() throws IOException;

  final void writeInt(int value) throws IOException {
    write(Integer.toString(value));
  }

  abstract void writeValueColorHex(String value) throws IOException;

  private boolean isIntWithinTolerance(double value) {
    int whole;
    whole = (int) value;

    double decimal;
    decimal = value - whole;

    return isZeroWithinTolerance(decimal);
  }

  private boolean isZeroWithinTolerance(double value) {
    if (value == 0) {
      return true;
    }

    double abs;
    abs = Math.abs(value);

    return abs < 1e-5;
  }

  private void writeDouble(double value) throws IOException {
    if (isZeroWithinTolerance(value)) {
      write('0');
    }

    else if (isIntWithinTolerance(value)) {
      writeInt((int) value);
    }

    else {
      writeDoubleImpl(value);
    }
  }

  private void writeRgbaStart() throws IOException {
    write("rgba(");
  }

  private void writeRgbStart() throws IOException {
    write("rgb(");
  }

}