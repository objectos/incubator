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
package br.com.objectos.css.boot.type;

import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java.a;

import br.com.objectos.code.java.declaration.ExtendsMany;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedType;
import br.com.objectos.css.boot.property.ParameterType;
import java.util.Set;
import java.util.TreeSet;
import objectos.lang.Check;

public class PrimitiveType implements ParameterType, Value {

  private final Set<NamedClass> interfaces = new TreeSet<>();

  private final Set<String> interfaceNames = new TreeSet<>();

  private final Primitive kind;

  PrimitiveType(Primitive kind) {
    this.kind = kind;

    interfaceNames.add("Value");
  }

  public static PrimitiveType of(Primitive kind) {
    Check.notNull(kind, "kind == null");

    return new PrimitiveType(kind);
  }

  @Override
  public final void acceptValueType(ValueType type) {
    interfaces.add(type.className);

    interfaceNames.add(type.simpleName);
  }

  public final ExtendsMany extendsClause() {
    interfaces.add(TypeNames._Value);

    return _extends(interfaces);
  }

  public final Set<String> interfaceNames() {
    return interfaceNames;
  }

  @Override
  public final NamedArray toNamedArray() {
    return a(kind.typeClassName());
  }

  @Override
  public final NamedType toNamedType() {
    return kind.typeClassName();
  }

  public final NamedClass typeClassName() {
    return kind.typeClassName();
  }

  public final String typeSimpleName() {
    return kind.typeSimpleName();
  }

}
