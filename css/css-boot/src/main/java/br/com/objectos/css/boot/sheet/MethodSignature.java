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
package br.com.objectos.css.boot.sheet;

import static br.com.objectos.code.java.Java._abstract;
import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._protected;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.param;

import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.VarArgs;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.css.boot.property.ParameterType;
import br.com.objectos.css.boot.spec.Ids;
import br.com.objectos.css.boot.type.TypeNames;
import objectos.lang.Check;

public abstract sealed class MethodSignature implements Comparable<MethodSignature> {

  public static final class Abstract1 extends MethodSignature {

    public final Identifier name;
    public final ParameterType type;

    public Abstract1(ParameterType type, String name) {
      this.type = type;
      this.name = id(name);
    }

    @Override
    public final int arity() {
      return 1;
    }

    @Override
    public final MethodCode writeMethodCode(FunctionOrProperty property) {
      return method(
        _protected(), _abstract(), property.getSingleDeclarationName(),
        property.getMethodName(),
        param(type.toNamedType(), name)
      );
    }

  }

  public static final class SigHash extends MethodSignature {

    static final SigHash INSTANCE = new SigHash();

    private SigHash() {}

    @Override
    public final int arity() {
      return Integer.MAX_VALUE;
    }

    @Override
    public final MethodCode writeMethodCode(FunctionOrProperty property) {
      NamedClass declarationInterfaceName;
      declarationInterfaceName = property.getSingleDeclarationName();

      NamedArray declarationArray;
      declarationArray = declarationInterfaceName.toNamedArray();

      VarArgs varArgs;
      varArgs = VarArgs.of(declarationArray);

      return method(
        _protected(), _final(), property.getMultiDeclarationName(), property.getMethodName(),

        param(varArgs, Ids.declarations),
        _return(
          invoke(property.addMethodName(), property.standardPropertyName(), Ids.declarations)
        )
      );
    }

  }

  public static final class Signature1 extends MethodSignature {

    public final Identifier name;
    public final ParameterType type;

    Signature1(ParameterType type, String name) {
      this.type = type;
      this.name = id(name);
    }

    @Override
    public final int arity() {
      return 1;
    }

    @Override
    public final MethodCode writeMethodCode(FunctionOrProperty property) {
      return method(
        _protected(), _final(), property.getSingleDeclarationName(), property.getMethodName(),
        param(type.toNamedType(), name),
        _return(
          invoke(property.addMethodName(), property.standardPropertyName(), name)
        )
      );
    }

  }

  public static final class Signature2 extends MethodSignature {

    public final Identifier name0;
    public final Identifier name1;
    public final ParameterType type0;
    public final ParameterType type1;

    Signature2(ParameterType type0,
               String name0,
               ParameterType type1,
               String name1) {
      this.type0 = type0;
      this.name0 = id(name0);
      this.type1 = type1;
      this.name1 = id(name1);
    }

    @Override
    public final int arity() {
      return 2;
    }

    @Override
    public final MethodCode writeMethodCode(FunctionOrProperty property) {
      return method(
        _protected(), _final(), property.getSingleDeclarationName(), property.getMethodName(),
        param(type0.toNamedType(), name0),
        param(type1.toNamedType(), name1),
        _return(
          invoke(property.addMethodName(), property.standardPropertyName(), name0, name1)
        )
      );
    }

  }

  public static final class Signature3 extends MethodSignature {

    public final Identifier name0;
    public final Identifier name1;
    public final Identifier name2;
    public final ParameterType type0;
    public final ParameterType type1;
    public final ParameterType type2;

    Signature3(ParameterType type0,
               String name0,
               ParameterType type1,
               String name1,
               ParameterType type2,
               String name2) {
      this.type0 = type0;
      this.name0 = id(name0);
      this.type1 = type1;
      this.name1 = id(name1);
      this.type2 = type2;
      this.name2 = id(name2);
    }

    @Override
    public final int arity() {
      return 3;
    }

    @Override
    public final MethodCode writeMethodCode(FunctionOrProperty property) {
      return method(
        _protected(), _final(), property.getSingleDeclarationName(), property.getMethodName(),
        param(type0.toNamedType(), name0),
        param(type1.toNamedType(), name1),
        param(type2.toNamedType(), name2),
        _return(
          invoke(property.addMethodName(), property.standardPropertyName(), name0, name1, name2)
        )
      );
    }

  }

  public static final class Signature4 extends MethodSignature {

    public final Identifier name0;
    public final Identifier name1;
    public final Identifier name2;
    public final Identifier name3;
    public final ParameterType type0;
    public final ParameterType type1;
    public final ParameterType type2;
    public final ParameterType type3;

    Signature4(ParameterType type0,
               String name0,
               ParameterType type1,
               String name1,
               ParameterType type2,
               String name2,
               ParameterType type3,
               String name3) {
      this.type0 = type0;
      this.name0 = id(name0);
      this.type1 = type1;
      this.name1 = id(name1);
      this.type2 = type2;
      this.name2 = id(name2);
      this.type3 = type3;
      this.name3 = id(name3);
    }

    @Override
    public final int arity() {
      return 4;
    }

    @Override
    public final MethodCode writeMethodCode(FunctionOrProperty property) {
      return method(
        _protected(), _final(), property.getSingleDeclarationName(), property.getMethodName(),
        param(type0.toNamedType(), name0),
        param(type1.toNamedType(), name1),
        param(type2.toNamedType(), name2),
        param(type3.toNamedType(), name3),
        _return(
          invoke(property.addMethodName(), property.standardPropertyName(), name0, name1, name2,
            name3)
        )
      );
    }

  }

  public static final class Signature5 extends MethodSignature {

    public final Identifier name0;
    public final Identifier name1;
    public final Identifier name2;
    public final Identifier name3;
    public final Identifier name4;
    public final ParameterType type0;
    public final ParameterType type1;
    public final ParameterType type2;
    public final ParameterType type3;
    public final ParameterType type4;

    Signature5(ParameterType type0,
               String name0,
               ParameterType type1,
               String name1,
               ParameterType type2,
               String name2,
               ParameterType type3,
               String name3,
               ParameterType type4,
               String name4) {
      this.type0 = type0;
      this.name0 = id(name0);
      this.type1 = type1;
      this.name1 = id(name1);
      this.type2 = type2;
      this.name2 = id(name2);
      this.type3 = type3;
      this.name3 = id(name3);
      this.type4 = type4;
      this.name4 = id(name4);
    }

    @Override
    public final int arity() {
      return 5;
    }

    @Override
    public final MethodCode writeMethodCode(FunctionOrProperty property) {
      return method(
        _protected(), _final(), property.getSingleDeclarationName(), property.getMethodName(),
        param(type0.toNamedType(), name0),
        param(type1.toNamedType(), name1),
        param(type2.toNamedType(), name2),
        param(type3.toNamedType(), name3),
        param(type4.toNamedType(), name4),
        _return(
          invoke(
            property.addMethodName(), property.standardPropertyName(),
            name0, name1, name2, name3, name4
          )
        )
      );
    }

  }

  public static final class Signature6 extends MethodSignature {

    public final Identifier name0;
    public final Identifier name1;
    public final Identifier name2;
    public final Identifier name3;
    public final Identifier name4;
    public final Identifier name5;
    public final ParameterType type0;
    public final ParameterType type1;
    public final ParameterType type2;
    public final ParameterType type3;
    public final ParameterType type4;
    public final ParameterType type5;

    Signature6(ParameterType type0,
               String name0,
               ParameterType type1,
               String name1,
               ParameterType type2,
               String name2,
               ParameterType type3,
               String name3,
               ParameterType type4,
               String name4,
               ParameterType type5,
               String name5) {
      this.type0 = type0;
      this.name0 = id(name0);
      this.type1 = type1;
      this.name1 = id(name1);
      this.type2 = type2;
      this.name2 = id(name2);
      this.type3 = type3;
      this.name3 = id(name3);
      this.type4 = type4;
      this.name4 = id(name4);
      this.type5 = type5;
      this.name5 = id(name5);
    }

    @Override
    public final int arity() {
      return 6;
    }

    @Override
    public final MethodCode writeMethodCode(FunctionOrProperty property) {
      return method(
        _protected(), _final(), property.getSingleDeclarationName(), property.getMethodName(),
        param(type0.toNamedType(), name0),
        param(type1.toNamedType(), name1),
        param(type2.toNamedType(), name2),
        param(type3.toNamedType(), name3),
        param(type4.toNamedType(), name4),
        param(type5.toNamedType(), name5),
        _return(
          invoke(
            property.addMethodName(), property.standardPropertyName(),
            name0, name1, name2, name3, name4, name5
          )
        )
      );
    }

  }

  public static final class SigZero extends MethodSignature {

    static final SigZero INSTANCE = new SigZero();

    private SigZero() {}

    @Override
    public final int arity() {
      return 1;
    }

    @Override
    public final MethodCode writeMethodCode(FunctionOrProperty property) {
      return method(
        _protected(), _final(), property.getSingleDeclarationName(), property.getMethodName(),
        param(TypeNames._Zero, Ids.zero),
        _return(
          invoke(property.addMethodName(), property.standardPropertyName(), l(0))
        )
      );
    }

  }

  MethodSignature() {}

  public static MethodSignature abstractOf(ParameterType type, String name) {
    Check.notNull(type, "type == null");
    Check.notNull(name, "name == null");
    return new Abstract1(type, name);
  }

  public static MethodSignature of(
      ParameterType type, String name) {
    Check.notNull(type, "type == null");
    Check.notNull(name, "name == null");
    return new Signature1(type, name);
  }

  public static MethodSignature of(
      ParameterType type0, String name0,
      ParameterType type1, String name1) {
    Check.notNull(type0, "type0 == null");
    Check.notNull(name0, "name0 == null");
    Check.notNull(type1, "type1 == null");
    Check.notNull(name1, "name1 == null");
    return new Signature2(type0, name0, type1, name1);
  }

  public static MethodSignature of(
      ParameterType type0, String name0,
      ParameterType type1, String name1,
      ParameterType type2, String name2) {
    Check.notNull(type0, "type0 == null");
    Check.notNull(name0, "name0 == null");
    Check.notNull(type1, "type1 == null");
    Check.notNull(name1, "name1 == null");
    Check.notNull(type2, "type2 == null");
    Check.notNull(name2, "name2 == null");
    return new Signature3(type0, name0, type1, name1, type2, name2);
  }

  public static MethodSignature of(
      ParameterType type0, String name0,
      ParameterType type1, String name1,
      ParameterType type2, String name2,
      ParameterType type3, String name3) {
    Check.notNull(type0, "type0 == null");
    Check.notNull(name0, "name0 == null");
    Check.notNull(type1, "type1 == null");
    Check.notNull(name1, "name1 == null");
    Check.notNull(type2, "type2 == null");
    Check.notNull(name2, "name2 == null");
    Check.notNull(type3, "type3 == null");
    Check.notNull(name3, "name3 == null");
    return new Signature4(type0, name0, type1, name1, type2, name2, type3, name3);
  }

  public static MethodSignature of(
      ParameterType type0, String name0,
      ParameterType type1, String name1,
      ParameterType type2, String name2,
      ParameterType type3, String name3,
      ParameterType type4, String name4) {
    Check.notNull(type0, "type0 == null");
    Check.notNull(name0, "name0 == null");
    Check.notNull(type1, "type1 == null");
    Check.notNull(name1, "name1 == null");
    Check.notNull(type2, "type2 == null");
    Check.notNull(name2, "name2 == null");
    Check.notNull(type3, "type3 == null");
    Check.notNull(name3, "name3 == null");
    Check.notNull(type4, "type4 == null");
    Check.notNull(name4, "name4 == null");
    return new Signature5(type0, name0, type1, name1, type2, name2, type3, name3, type4, name4);
  }

  public static MethodSignature of(
      ParameterType type0, String name0,
      ParameterType type1, String name1,
      ParameterType type2, String name2,
      ParameterType type3, String name3,
      ParameterType type4, String name4,
      ParameterType type5, String name5) {
    Check.notNull(type0, "type0 == null");
    Check.notNull(name0, "name0 == null");
    Check.notNull(type1, "type1 == null");
    Check.notNull(name1, "name1 == null");
    Check.notNull(type2, "type2 == null");
    Check.notNull(name2, "name2 == null");
    Check.notNull(type3, "type3 == null");
    Check.notNull(name3, "name3 == null");
    Check.notNull(type4, "type4 == null");
    Check.notNull(name4, "name4 == null");
    Check.notNull(type5, "type5 == null");
    Check.notNull(name5, "name5 == null");
    return new Signature6(
      type0, name0, type1, name1, type2, name2, type3, name3, type4, name4, type5, name5
    );
  }

  public static MethodSignature sigHash() {
    return SigHash.INSTANCE;
  }

  public static MethodSignature sigZero() {
    return SigZero.INSTANCE;
  }

  public abstract int arity();

  @Override
  public final int compareTo(MethodSignature o) {
    return Integer.compare(arity(), o.arity());
  }

  public abstract MethodCode writeMethodCode(FunctionOrProperty property);

}
