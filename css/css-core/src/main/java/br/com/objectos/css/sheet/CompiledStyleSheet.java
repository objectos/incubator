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

import br.com.objectos.css.io.CssWriter;
import java.util.Arrays;

public class CompiledStyleSheet {

  private final int[] codes;
  private final char[] chars;
  private final double[] doubles;

  CompiledStyleSheet(int[] codes, char[] chars, double[] doubles) {
    this.codes = codes;
    this.chars = chars;
    this.doubles = doubles;
  }

  public final <E extends Exception> void acceptCompiledStyleSheetVisitor(
      CompiledStyleSheetVisitor<E> visitor) throws E {
    Interpreter<E> interpreter = new Interpreter<>(this, visitor);
    interpreter.execute();
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof CompiledStyleSheet)) {
      return false;
    }
    CompiledStyleSheet that = (CompiledStyleSheet) obj;
    return Arrays.equals(codes, that.codes)
        && Arrays.equals(chars, that.chars)
        && Arrays.equals(doubles, that.doubles);
  }

  @Override
  public final int hashCode() {
    return Arrays.hashCode(
      new int[] {
          Arrays.hashCode(codes),
          Arrays.hashCode(chars),
          Arrays.hashCode(doubles)
      }
    );
  }

  @Override
  public final String toString() {
    return CssWriter.toString(this);
  }

  final int getCode(int index) {
    return codes[index];
  }

  final double getDouble(int index) {
    return doubles[index];
  }

  final String getString(int index, int length) {
    return new String(chars, index, length);
  }

}
