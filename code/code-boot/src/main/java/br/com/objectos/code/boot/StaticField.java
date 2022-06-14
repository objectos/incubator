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

import static br.com.objectos.code.java.declaration.FieldCode.field;
import static br.com.objectos.code.java.declaration.FieldCode.init;
import static br.com.objectos.code.java.declaration.Modifiers._final;
import static br.com.objectos.code.java.declaration.Modifiers._public;
import static br.com.objectos.code.java.declaration.Modifiers._static;

import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedType;
import br.com.objectos.code.model.element.ProcessingField;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.type.PTypeMirror;
import objectos.lang.HashCode;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

final class StaticField {

  private final NamedClass enclosingClassName;

  private final Identifier name;

  private final NamedType typeName;

  private StaticField(Builder builder) {
    enclosingClassName = builder.enclosingClassName();
    typeName = builder.typeName();
    name = builder.name();
  }

  public static ImmutableList<StaticField> listOf(ProcessingType type) {
    MutableList<StaticField> result;
    result = new MutableList<>();

    ImmutableList<ProcessingField> fields;
    fields = type.getDeclaredOrInheritedFields();

    for (int i = 0; i < fields.size(); i++) {
      ProcessingField field;
      field = fields.get(i);

      if (!StaticField.valid(field)) {
        continue;
      }

      StaticField staticField;
      staticField = StaticField.ofUnchecked(field);

      result.add(staticField);
    }

    return result.toImmutableList();
  }

  public static StaticField ofUnchecked(ProcessingField element) {
    return new FieldElementQueryBuilder(element).build();
  }

  private static boolean valid(ProcessingField field) {
    if (!field.hasModifier(Modifiers.PUBLIC)) {
      return false;
    }

    if (!field.hasModifier(Modifiers.STATIC)) {
      return false;
    }

    if (!field.hasModifier(Modifiers.FINAL)) {
      return false;
    }

    if (IgnoreAnnotation.isAnnotatedWith(field)) {
      return false;
    }

    return true;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof StaticField)) {
      return false;
    }
    StaticField that = (StaticField) obj;
    return enclosingClassName.equals(that.enclosingClassName)
        && typeName.equals(that.typeName)
        && name.equals(that.name);
  }

  public final FieldCode generate() {
    return field(
      _public(), _static(), _final(), typeName,
      init(name, enclosingClassName.id(name))
    );
  }

  @Override
  public final int hashCode() {
    return HashCode.of(enclosingClassName, typeName, name);
  }

  static abstract class Builder {
    public final StaticField build() {
      return new StaticField(this);
    }

    abstract NamedClass enclosingClassName();

    abstract Identifier name();

    abstract NamedType typeName();
  }

  private static class FieldElementQueryBuilder extends Builder {

    private final ProcessingField element;

    FieldElementQueryBuilder(ProcessingField element) {
      this.element = element;
    }

    @Override
    final NamedClass enclosingClassName() {
      ProcessingType declaringType;
      declaringType = element.getDeclaringType();

      return declaringType.getName();
    }

    @Override
    final Identifier name() {
      return element.toIdentifier();
    }

    @Override
    final NamedType typeName() {
      PTypeMirror type;
      type = element.getType();

      return type.getName();
    }

  }

}
