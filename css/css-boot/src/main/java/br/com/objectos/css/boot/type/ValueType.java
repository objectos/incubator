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

import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._interface;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java.a;

import br.com.objectos.code.java.declaration.InterfaceCode;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedType;
import br.com.objectos.css.boot.CssBoot;
import br.com.objectos.css.boot.property.ParameterType;
import java.util.Set;
import java.util.TreeSet;

public class ValueType implements ParameterType, Value {

  public final NamedClass className;

  public final String simpleName;

  private final Set<NamedClass> interfaces = new TreeSet<>();

  private final Set<String> interfaceNames = new TreeSet<>();

  private ValueType(String simpleName) {
    this.className = TypeNames.className(simpleName);

    this.simpleName = simpleName;

    interfaces.add(TypeNames._Value);

    interfaceNames.add("Value");
  }

  public static ValueType of(String simpleName) {
    return new ValueType(simpleName);
  }

  @Override
  public void acceptValueType(ValueType type) {
    interfaces.add(type.className);

    interfaceNames.add(type.simpleName);
  }

  public final Iterable<String> interfaceNames() {
    return interfaceNames;
  }

  @Override
  public final NamedArray toNamedArray() {
    return a(className);
  }

  @Override
  public final NamedType toNamedType() {
    return className;
  }

  final InterfaceCode generate() {
    return _interface(
      CssBoot.GENERATED,
      _public(), className, _extends(interfaces)
    );
  }

}
