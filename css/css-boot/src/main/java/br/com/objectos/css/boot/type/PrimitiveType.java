/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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

import br.com.objectos.css.boot.property.ParameterType;
import java.util.Set;
import java.util.TreeSet;
import objectos.lang.Check;

public class PrimitiveType implements ParameterType, Value {

  private final Set<String> interfaceNames = new TreeSet<>();

  public final Primitive kind;

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
    interfaceNames.add(type.simpleName);
  }

  public final Set<String> interfaceNames() {
    return interfaceNames;
  }

  public final String typeSimpleName() {
    return kind.typeSimpleName();
  }

}
