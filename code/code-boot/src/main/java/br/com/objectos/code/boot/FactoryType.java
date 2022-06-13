/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.boot;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.model.element.ProcessingType;
import objectos.lang.ToString;
import objectos.util.ImmutableList;

final class FactoryType implements ToString.Formattable {

  private final ImmutableList<StaticField> staticFields;

  private final ImmutableList<StaticMethod> staticMethods;

  private FactoryType(Builder builder) {
    staticFields = builder.staticFields();
    staticMethods = builder.staticMethods();
  }

  static FactoryType of(ProcessingType query) {
    return new TypeElementQueryBuilder(query).build();
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof FactoryType)) {
      return false;
    }
    FactoryType that = (FactoryType) obj;
    return staticFields.equals(that.staticFields)
        && staticMethods.equals(that.staticMethods);
  }

  @Override
  public final void formatToString(StringBuilder toString, int level) {
    ToString.format(
      toString, level, this,
      "staticMethods", staticMethods
    );
  }

  public final void generateFields(ClassCode.Builder b) {
    for (int i = 0; i < staticFields.size(); i++) {
      StaticField staticField;
      staticField = staticFields.get(i);

      FieldCode code;
      code = staticField.generate();

      b.addField(code);
    }
  }

  public final void generateMethods(ClassCode.Builder b) {
    for (int i = 0; i < staticMethods.size(); i++) {
      StaticMethod staticMethod;
      staticMethod = staticMethods.get(i);

      MethodCode code;
      code = staticMethod.generate();

      b.addMethod(code);
    }
  }

  @Override
  public final int hashCode() {
    return staticMethods.hashCode();
  }

  @Override
  public final String toString() {
    return ToString.of(this);
  }

  static abstract class Builder {
    public final FactoryType build() {
      return new FactoryType(this);
    }

    abstract ImmutableList<StaticField> staticFields();

    abstract ImmutableList<StaticMethod> staticMethods();
  }

  private static class TypeElementQueryBuilder extends Builder {
    private final ProcessingType query;

    TypeElementQueryBuilder(ProcessingType query) {
      this.query = query;
    }

    @Override
    final ImmutableList<StaticField> staticFields() {
      return StaticField.listOf(query);
    }

    @Override
    final ImmutableList<StaticMethod> staticMethods() {
      return StaticMethod.listOf(query);
    }
  }

}
