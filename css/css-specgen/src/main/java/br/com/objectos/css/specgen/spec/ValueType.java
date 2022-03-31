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
package br.com.objectos.css.specgen.spec;

import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.java.expression.Literal;
import br.com.objectos.core.object.Checks;
import br.com.objectos.core.object.ToString;
import br.com.objectos.core.object.ToStringObject;
import java.util.Objects;

public class ValueType implements ToStringObject {

  private final String formal;
  private final String name;

  public ValueType(String name, String formal) {
    this.name = Checks.checkNotNull(name, "name == null");
    this.formal = Checks.checkNotNull(formal, "formal == null");
  }

  public final void acceptKeywordSetBuilder(KeywordSet.Builder builder) {
    builder.parse(name, formal);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof ValueType)) {
      return false;
    }
    ValueType that = (ValueType) obj;
    return name.equals(that.name)
        && formal.equals(that.formal);
  }

  public final Literal formalLiteral() {
    return l(name + " = " + formal);
  }

  @Override
  public final void formatToString(StringBuilder sb, int depth) {
    ToString.formatToString(
        sb, depth, this,
        "name", name,
        "formal", formal
    );
  }

  @Override
  public final int hashCode() {
    return Objects.hash(name, formal);
  }

  @Override
  public final String toString() {
    return ToString.toString(this);
  }

  public final String write() {
    return name + ':' + formal;
  }

}