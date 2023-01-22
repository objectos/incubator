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

import static br.com.objectos.code.java.Java.a;
import static br.com.objectos.code.java.Java.t;

import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedPrimitive;
import br.com.objectos.code.java.type.NamedType;
import br.com.objectos.css.boot.property.ParameterType;

public enum JavaType implements ParameterType {

  INT(NamedPrimitive.INT),

  DOUBLE(NamedPrimitive.DOUBLE),

  STRING(t(String.class));

  private final NamedArray arrayTypeName;
  private final NamedType typeName;

  private JavaType(NamedClass typeName) {
    this.arrayTypeName = a(typeName);
    this.typeName = typeName;
  }

  private JavaType(NamedPrimitive typeName) {
    this.arrayTypeName = a(typeName);
    this.typeName = typeName;
  }

  @Override
  public final NamedArray toNamedArray() {
    return arrayTypeName;
  }

  @Override
  public final NamedType toNamedType() {
    return typeName;
  }

}
