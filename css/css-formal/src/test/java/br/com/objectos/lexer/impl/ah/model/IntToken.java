/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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
package br.com.objectos.lexer.impl.ah.model;

import objectos.lang.ToString;

public class IntToken implements IsToken, ToString.Formattable {

  private final String value;

  public IntToken(String value) {
    this.value = value;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof IntToken)) {
      return false;
    }
    IntToken that = (IntToken) obj;
    return value.equals(that.value);
  }

  @Override
  public final void formatToString(StringBuilder toString, int level) {
    ToString.format(
      toString, level, this,
      "", value
    );
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public final String toString() {
    return ToString.of(this);
  }

}