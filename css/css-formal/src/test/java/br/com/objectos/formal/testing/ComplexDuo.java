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
package br.com.objectos.formal.testing;

import java.util.Objects;
import objectos.lang.ToString;

public class ComplexDuo implements IsComplex, ToString.Formattable {

  private final Object first;
  private final Object second;

  public ComplexDuo(Object first, Object second) {
    this.first = first;
    this.second = second;
  }

  public static ComplexDuo withOne(Object one) {
    return new ComplexDuo(one, null);
  }

  public static ComplexDuo withTwo(Object first, Object second) {
    return new ComplexDuo(first, second);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof ComplexDuo)) {
      return false;
    }
    ComplexDuo that = (ComplexDuo) obj;
    return Objects.equals(first, that.first)
        && Objects.equals(second, that.second);
  }

  @Override
  public final void formatToString(StringBuilder toString, int level) {
    ToString.format(
      toString, level, this,
      "first", first,
      "second", second
    );
  }

  @Override
  public final int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public final String toString() {
    return ToString.of(this);
  }

}