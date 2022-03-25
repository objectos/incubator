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
package br.com.objectos.css.boot.function;

import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._interface;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.declaration.InterfaceCode;
import br.com.objectos.code.java.expression.ExpressionName;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.Literal;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.core.set.Sets;
import br.com.objectos.css.boot.sheet.FunctionOrProperty;
import br.com.objectos.css.boot.type.Value;
import br.com.objectos.css.boot.type.ValueType;
import java.util.Locale;
import java.util.Set;

public final class FunctionName implements FunctionOrProperty, Value {

  private final Set<NamedClass> interfaces = Sets.newTreeSet();

  private final String name;

  FunctionName(String name) {
    this.name = name;
  }

  public static FunctionName of(String name) {
    return new FunctionName(name);
  }

  @Override
  public final void acceptValueType(ValueType type) {
    NamedClass className;
    className = type.className;

    interfaces.add(className);
  }

  @Override
  public final String addMethodName() {
    return "addFunction";
  }

  public final Identifier getEnumName() {
    String uppercase;
    uppercase = name.replace('-', '_').toUpperCase(Locale.US);

    String enumSimpleName;
    enumSimpleName = JavaNames.toIdentifier(uppercase);

    return id(enumSimpleName);
  }

  @Override
  public final Identifier getMethodName() {
    String javaName;
    javaName = JavaNames.toValidMethodName(name);

    return id(javaName);
  }

  @Override
  public NamedClass getMultiDeclarationName() {
    throw new UnsupportedOperationException("Implement me");
  }

  public final String getName() {
    return name;
  }

  public final Literal getNameLiteral() {
    return l(name);
  }

  @Override
  public final NamedClass getSingleDeclarationName() {
    String simpleName;
    simpleName = JavaNames.toValidClassName(name + "Function");

    return FunctionNames.className(simpleName);
  }

  @Override
  public final ExpressionName standardPropertyName() {
    return FunctionNames.StandardFunctionName.id(getEnumName());
  }

  final InterfaceCode generate() {
    return _interface(
        _public(), getSingleDeclarationName(),
        _extends(interfaces)
    );
  }

}
