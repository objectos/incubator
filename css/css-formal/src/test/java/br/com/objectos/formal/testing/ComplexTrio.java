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

public class ComplexTrio implements IsComplex, ToString.Formattable {

  private final Object first;
  private final Object second;
  private final Object third;

  public ComplexTrio(Object first, Object second, Object third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof ComplexTrio)) {
      return false;
    }
    ComplexTrio that = (ComplexTrio) obj;
    return Objects.equals(first, that.first)
        && Objects.equals(second, that.second)
        && Objects.equals(third, that.third);
  }

  @Override
  public final void formatToString(StringBuilder toString, int level) {
    ToString.format(
      toString, level, this,
      "first", first,
      "second", second,
      "third", third
    );
  }

  @Override
  public final int hashCode() {
    return Objects.hash(first, second, third);
  }

  @Override
  public final String toString() {
    return ToString.of(this);
  }

}