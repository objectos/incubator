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
package br.com.objectos.css.boot.property;

import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._interface;
import static br.com.objectos.code.java.Java._protected;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.expression.ExpressionName;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.Literal;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.css.boot.sheet.FunctionOrProperty;
import br.com.objectos.css.boot.sheet.GeneratedStyleSheetStep;
import br.com.objectos.css.boot.sheet.SheetNames;
import java.util.Locale;
import objectos.lang.ToString;

public class Property implements Comparable<Property>, FunctionOrProperty {

  private NamedClass declarationName;

  private Identifier enumName;

  private NamedClass hashDeclarationName;

  private final PropertyKind kind;

  private Identifier methodName;

  private final String name;

  private Literal nameLiteral;

  Property(PropertyKind kind, String name) {
    this.kind = kind;
    this.name = name;
  }

  Property(PropertyKind kind, String name, Identifier methodName) {
    this(kind, name);
    this.methodName = methodName;
  }

  public final void acceptGeneratedStyleSheetStep(GeneratedStyleSheetStep step) {
    switch (kind) {
      case STANDARD:
        acceptGeneratedStyleSheetStep0Standard(step);
        return;
      case HASH:
        acceptGeneratedStyleSheetStep0Hash(step);
        return;
      default:
        throw new AssertionError("Unexpected kind: " + kind);
    }
  }

  @Override
  public final String addMethodName() {
    return "addDeclaration";
  }

  @Override
  public final int compareTo(Property o) {
    return name.compareTo(o.name);
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Property)) {
      return false;
    }
    Property that = (Property) obj;
    return name.equals(that.name);
  }

  public final Identifier getEnumName() {
    if (enumName == null) {
      enumName = getEnumName0();
    }

    return enumName;
  }

  @Override
  public final Identifier getMethodName() {
    if (methodName == null) {
      methodName = getMethodName0();
    }

    return methodName;
  }

  @Override
  public final NamedClass getMultiDeclarationName() {
    if (hashDeclarationName == null) {
      hashDeclarationName = getMultiDeclarationName0();
    }

    return hashDeclarationName;
  }

  public final String getName() {
    return name;
  }

  public final Literal getNameLiteral() {
    if (nameLiteral == null) {
      nameLiteral = l(name);
    }

    return nameLiteral;
  }

  @Override
  public final NamedClass getSingleDeclarationName() {
    if (declarationName == null) {
      declarationName = getSingleDeclarationName0();
    }

    return declarationName;
  }

  @Override
  public final int hashCode() {
    return name.hashCode();
  }

  @Override
  public final ExpressionName standardPropertyName() {
    return PropertyNames.StandardPropertyName.id(getEnumName());
  }

  @Override
  public final String toString() {
    return ToString.toString(this, "", name);
  }

  private void acceptGeneratedStyleSheetStep0Hash(GeneratedStyleSheetStep step) {
    step.addType(
        _interface(
            _protected(), getMultiDeclarationName(),
            _extends(SheetNames._Declaration)
        )
    );

    step.addType(
        _interface(
            _protected(), getSingleDeclarationName(),
            _extends(SheetNames._MultiDeclarationElement)
        )
    );

    step.addAnyDeclarationExtends(
        getMultiDeclarationName()
    );
    step.addAnyDeclarationExtends(
        getSingleDeclarationName()
    );
  }

  private void acceptGeneratedStyleSheetStep0Standard(GeneratedStyleSheetStep step) {
    step.addType(
        _interface(
            _protected(), getSingleDeclarationName(),
            _extends(SheetNames._Declaration)
        )
    );

    step.addAnyDeclarationExtends(
        getSingleDeclarationName()
    );
  }

  private Identifier getEnumName0() {
    String uppercase;
    uppercase = name.replace('-', '_').toUpperCase(Locale.US);

    String enumSimpleName;
    enumSimpleName = JavaNames.toIdentifier(uppercase);

    return id(enumSimpleName);
  }

  private Identifier getMethodName0() {
    String javaName;
    javaName = JavaNames.toValidMethodName(name);

    return id(javaName);
  }

  private NamedClass getMultiDeclarationName0() {
    String simpleName;
    simpleName = JavaNames.toValidClassName(name + "MultiDeclaration");

    return SheetNames._GeneratedStyleSheet.nestedClass(simpleName);
  }

  private NamedClass getSingleDeclarationName0() {
    String singleSuffix;
    singleSuffix = kind.getSingleSuffix();

    String simpleName;
    simpleName = JavaNames.toValidClassName(name + singleSuffix);

    return SheetNames._GeneratedStyleSheet.nestedClass(simpleName);
  }

}
