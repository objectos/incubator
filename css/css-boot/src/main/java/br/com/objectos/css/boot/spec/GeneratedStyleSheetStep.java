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
package br.com.objectos.css.boot.spec;

final class GeneratedStyleSheetStep extends ThisTemplate {

  CssSpecDsl spec;

  GeneratedStyleSheetStep(StepAdapter adapter) { super(adapter); }

  //  private final InterfaceCode.Builder anyDeclaration;
  //
  //  private final InterfaceCode.Builder anyFunction;
  //
  //  private final ClassCode.Builder builder;
  //
  //  public GeneratedStyleSheetStep(StepAdapter adapter) {
  //    super(adapter);
  //
  //    builder = ClassCode.builder();
  //    builder.addAnnotation(CssBoot.GENERATED);
  //    builder.addModifier(Modifiers._abstract());
  //    builder.simpleName(SheetNames._GeneratedStyleSheet);
  //    builder.addConstructor(constructor());
  //
  //    anyDeclaration = InterfaceCode.builder();
  //    anyDeclaration.simpleName(SheetNames._AnyDeclaration);
  //
  //    anyFunction = InterfaceCode.builder();
  //    anyFunction.simpleName(SheetNames._AnyFunction);
  //  }
  //
  //  public final void addAnyDeclarationExtends(NamedClass iface) {
  //    anyDeclaration.addExtends(iface);
  //  }
  //
  //  @Override
  //  public final void addFunction(FunctionName function) {
  //    anyFunction.addExtends(function.getSingleDeclarationName());
  //  }
  //
  //  @Override
  //  public final void addProperty(Property property) {
  //    property.acceptGeneratedStyleSheetStep(this);
  //  }
  //
  //  public final void addType(TypeCode type) {
  //    builder.addType(type);
  //  }

  @Override
  protected final void definition() {
    _package(sheet);

    autoImports();

    generatedAnnotation();
    _abstract();
    _class("GeneratedStyleSheet");
    body(
      //      include(this::fields),
    //
    //      constructor(),
    //
    //      include(this::angleMethods),
    //
    //      include(this::lengthMethods),
    //
    //      include(this::propertyMethods)
    );
  }

  //  private void angleMethods() {
  //    for (var unit : spec.angleUnits) {
  //      var enumSimpleName = unit.toUpperCase();
  //
  //      _protected();
  //      _final();
  //      t(type, "AngleType");
  //      method(unit, _double(), id("value"));
  //      block(
  //        _return(), invoke("getAngle", t(type, "AngleUnit"), n(enumSimpleName), end(), n("value"))
  //      );
  //
  //      _protected();
  //      _final();
  //      t(type, "AngleType");
  //      method(unit, _int(), id("value"));
  //      block(
  //        _return(), invoke("getAngle", t(type, "AngleUnit"), n(enumSimpleName), end(), n("value"))
  //      );
  //    }
  //  }

  //  private ClassCode classCode() {
  //    NamedClass singleDeclaration = SheetNames._MultiDeclarationElement;
  //
  //    NamedArray singleDeclarationArray = singleDeclaration.toNamedArray();
  //
  //    VarArgs singleDeclarationVarArgs = VarArgs.of(singleDeclarationArray);
  //
  //    return builder
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
  //            param(PropertyNames.StandardPropertyName, Ids.name),
  //            param(NamedPrimitive.INT, Ids.value)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
  //            param(PropertyNames.StandardPropertyName, Ids.name),
  //            param(NamedPrimitive.DOUBLE, Ids.value)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
  //            param(PropertyNames.StandardPropertyName, Ids.name),
  //            param(singleDeclarationVarArgs, Ids.elements)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
  //            param(PropertyNames.StandardPropertyName, Ids.name),
  //            param(Types._String, Ids.value)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
  //            param(PropertyNames.StandardPropertyName, Ids.name),
  //            param(TypeNames._Value, Ids.v1)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
  //            param(PropertyNames.StandardPropertyName, Ids.name),
  //            param(TypeNames._Value, Ids.v1),
  //            param(TypeNames._Value, Ids.v2)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
  //            param(PropertyNames.StandardPropertyName, Ids.name),
  //            param(TypeNames._Value, Ids.v1),
  //            param(TypeNames._Value, Ids.v2),
  //            param(TypeNames._Value, Ids.v3)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
  //            param(PropertyNames.StandardPropertyName, Ids.name),
  //            param(TypeNames._Value, Ids.v1),
  //            param(TypeNames._Value, Ids.v2),
  //            param(TypeNames._Value, Ids.v3),
  //            param(TypeNames._Value, Ids.v4)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
  //            param(PropertyNames.StandardPropertyName, Ids.name),
  //            param(TypeNames._Value, Ids.v1),
  //            param(TypeNames._Value, Ids.v2),
  //            param(TypeNames._Value, Ids.v3),
  //            param(TypeNames._Value, Ids.v4),
  //            param(TypeNames._Value, Ids.v5)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
  //            param(PropertyNames.StandardPropertyName, Ids.name),
  //            param(TypeNames._Value, Ids.v1),
  //            param(TypeNames._Value, Ids.v2),
  //            param(TypeNames._Value, Ids.v3),
  //            param(TypeNames._Value, Ids.v4),
  //            param(TypeNames._Value, Ids.v5),
  //            param(TypeNames._Value, Ids.v6)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), SheetNames._AnyFunction, Ids.addFunction,
  //            param(FunctionNames.StandardFunctionName, Ids.name),
  //            param(TypeNames._Value, Ids.v1)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), TypeNames._AngleType, Ids.getAngle,
  //            param(TypeNames._AngleUnit, Ids.unit),
  //            param(_double(), Ids.value)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), TypeNames._AngleType, Ids.getAngle,
  //            param(TypeNames._AngleUnit, Ids.unit),
  //            param(_int(), Ids.value)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), TypeNames._LengthType, Ids.getLength,
  //            param(TypeNames._LengthUnit, Ids.unit),
  //            param(_double(), Ids.value)
  //          )
  //        )
  //        .addMethod(
  //          method(
  //            _abstract(), TypeNames._LengthType, Ids.getLength,
  //            param(TypeNames._LengthUnit, Ids.unit),
  //            param(_int(), Ids.value)
  //          )
  //        )
  //        .addType(anyDeclaration.build())
  //        .addType(anyFunction.build())
  //        .build();
  //  }

  //  private void fields() {
  //    for (var elementName : spec.elementNames) {
  //      _protected();
  //      _static();
  //      _final();
  //      t(select, "TypeSelector");
  //      id(elementName);
  //      t(css, "Css");
  //      n(elementName);
  //    }
  //
  //    for (var pseudoClass : spec.pseudoClasses) {
  //      var name = toFieldName(pseudoClass);
  //
  //      _protected();
  //      _static();
  //      _final();
  //      t(select, "PseudoClassSelector");
  //      id(name);
  //      t(css, "Css");
  //      n(name);
  //    }
  //
  //    for (var pseudoElement : spec.pseudoElements) {
  //      var name = toFieldName(pseudoElement);
  //
  //      _protected();
  //      _static();
  //      _final();
  //      t(select, "PseudoElementSelector");
  //      id(name);
  //      t(css, "Css");
  //      n(name);
  //    }
  //
  //    for (var color : spec.colors) {
  //      var name = color.identifier.name();
  //
  //      _protected();
  //      _static();
  //      _final();
  //      t(type, "Color");
  //      id(name);
  //      t(css, "Css");
  //      n(name);
  //    }
  //
  //    for (var keywordName : spec.keywords.values()) {
  //      var id = keywordName.fieldName.name();
  //
  //      _protected();
  //      _static();
  //      _final();
  //      t(keyword, keywordName.simpleName);
  //      id(id);
  //      t(keyword, "Keywords");
  //      n(id);
  //    }
  //  }
  //
  //  private void lengthMethods() {
  //    for (var unit : spec.lengthUnits) {
  //      var enumSimpleName = unit.toUpperCase();
  //
  //      _protected();
  //      _final();
  //      t(type, "LengthType");
  //      method(unit, _double(), id("value"));
  //      block(
  //        _return(), invoke("getLength", t(type, "LengthUnit"), n(enumSimpleName), end(), n("value"))
  //      );
  //
  //      _protected();
  //      _final();
  //      t(type, "LengthType");
  //      method(unit, _int(), id("value"));
  //      block(
  //        _return(), invoke("getLength", t(type, "LengthUnit"), n(enumSimpleName), end(), n("value"))
  //      );
  //    }
  //  }
  //
  //  private void propertyMethods() {
  //    for (var property : spec.propertyMap.values()) {
  //      for (var signature : property.signatures) {
  //        if (signature instanceof Abstract1 sig) {
  //          propertyMethodsAbstract1(property, sig);
  //        } else if (signature instanceof SigHash sig) {
  //          propertyMethodsSigHash(property, sig);
  //        } else if (signature instanceof Signature1 sig) {
  //          propertyMethodsSignature1(property, sig);
  //        } else if (signature instanceof Signature2 sig) {
  //          propertyMethodsSignature2(property, sig);
  //        } else if (signature instanceof Signature3 sig) {
  //          propertyMethodsSignature3(property, sig);
  //        } else if (signature instanceof Signature4 sig) {
  //          propertyMethodsSignature4(property, sig);
  //        } else if (signature instanceof Signature5 sig) {
  //          propertyMethodsSignature5(property, sig);
  //        } else if (signature instanceof Signature6 sig) {
  //          propertyMethodsSignature6(property, sig);
  //        } else if (signature instanceof SigZero sig) {
  //          propertyMethodsSigZero(property, sig);
  //        } else {
  //          throw new UnsupportedOperationException("Implement me :: " + signature.getClass());
  //        }
  //      }
  //    }
  //  }
  //
  //  private void propertyMethodsSigZero(Property property, SigZero sig) {
  //    _protected();
  //    _final();
  //    t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName());
  //    method(
  //      property.methodName(),
  //      t(type, "Zero"), id("zero")
  //    );
  //    block(
  //      _return(), invoke(
  //        property.addMethodName(),
  //        t(ThisTemplate.property, "StandardPropertyName"), n(property.enumName()), end(),
  //        i(0)
  //      )
  //    );
  //  }
  //
  //  private void propertyMethodsAbstract1(Property property, Abstract1 sig) {
  //    _protected();
  //    _abstract();
  //    t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName());
  //    method(
  //      property.methodName(),
  //      t(sig.type), id(sig.name.name())
  //    );
  //  }
  //
  //  private void propertyMethodsSigHash(Property property, SigHash sig) {
  //    _protected();
  //    _final();
  //    t(sheet, "GeneratedStyleSheet", property.multiDeclarationSimpleName());
  //    method(
  //      property.methodName(),
  //      t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName()), ellipsis(),
  //      id("declarations")
  //    );
  //    block(
  //      _return(), invoke(
  //        property.addMethodName(),
  //        t(ThisTemplate.property, "StandardPropertyName"), n(property.enumName()), end(),
  //        n("declarations")
  //      )
  //    );
  //  }
  //
  //  private void propertyMethodsSignature1(Property property, Signature1 sig) {
  //    _protected();
  //    _final();
  //    t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName());
  //    method(
  //      property.methodName(),
  //      t(sig.type), id(sig.name.name())
  //    );
  //    block(
  //      _return(), invoke(
  //        property.addMethodName(),
  //        t(ThisTemplate.property, "StandardPropertyName"), n(property.enumName()), end(),
  //        n(sig.name.name()), end()
  //      )
  //    );
  //  }
  //
  //  private void propertyMethodsSignature2(Property property, Signature2 sig) {
  //    _protected();
  //    _final();
  //    t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName());
  //    method(
  //      property.methodName(),
  //      t(sig.type0), id(sig.name0.name()),
  //      t(sig.type1), id(sig.name1.name())
  //    );
  //    block(
  //      _return(), invoke(
  //        property.addMethodName(),
  //        t(ThisTemplate.property, "StandardPropertyName"), n(property.enumName()), end(),
  //        n(sig.name0.name()), end(),
  //        n(sig.name1.name()), end()
  //      )
  //    );
  //  }
  //
  //  private void propertyMethodsSignature3(Property property, Signature3 sig) {
  //    _protected();
  //    _final();
  //    t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName());
  //    method(
  //      property.methodName(),
  //      t(sig.type0), id(sig.name0.name()),
  //      t(sig.type1), id(sig.name1.name()),
  //      t(sig.type2), id(sig.name2.name())
  //    );
  //    block(
  //      _return(), invoke(
  //        property.addMethodName(),
  //        t(ThisTemplate.property, "StandardPropertyName"), n(property.enumName()), end(),
  //        n(sig.name0.name()), end(),
  //        n(sig.name1.name()), end(),
  //        n(sig.name2.name()), end()
  //      )
  //    );
  //  }
  //
  //  private void propertyMethodsSignature4(Property property, Signature4 sig) {
  //    _protected();
  //    _final();
  //    t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName());
  //    method(
  //      property.methodName(),
  //      t(sig.type0), id(sig.name0.name()),
  //      t(sig.type1), id(sig.name1.name()),
  //      t(sig.type2), id(sig.name2.name()),
  //      t(sig.type3), id(sig.name3.name())
  //    );
  //    block(
  //      _return(), invoke(
  //        property.addMethodName(),
  //        t(ThisTemplate.property, "StandardPropertyName"), n(property.enumName()), end(),
  //        n(sig.name0.name()), end(),
  //        n(sig.name1.name()), end(),
  //        n(sig.name2.name()), end(),
  //        n(sig.name3.name()), end()
  //      )
  //    );
  //  }
  //
  //  private void propertyMethodsSignature5(Property property, Signature5 sig) {
  //    _protected();
  //    _final();
  //    t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName());
  //    method(
  //      property.methodName(),
  //      t(sig.type0), id(sig.name0.name()),
  //      t(sig.type1), id(sig.name1.name()),
  //      t(sig.type2), id(sig.name2.name()),
  //      t(sig.type3), id(sig.name3.name()),
  //      t(sig.type4), id(sig.name4.name())
  //    );
  //    block(
  //      _return(), invoke(
  //        property.addMethodName(),
  //        t(ThisTemplate.property, "StandardPropertyName"), n(property.enumName()), end(),
  //        n(sig.name0.name()), end(),
  //        n(sig.name1.name()), end(),
  //        n(sig.name2.name()), end(),
  //        n(sig.name3.name()), end(),
  //        n(sig.name4.name()), end()
  //      )
  //    );
  //  }
  //
  //  private void propertyMethodsSignature6(Property property, Signature6 sig) {
  //    _protected();
  //    _final();
  //    t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName());
  //    method(
  //      property.methodName(),
  //      t(sig.type0), id(sig.name0.name()),
  //      t(sig.type1), id(sig.name1.name()),
  //      t(sig.type2), id(sig.name2.name()),
  //      t(sig.type3), id(sig.name3.name()),
  //      t(sig.type4), id(sig.name4.name()),
  //      t(sig.type5), id(sig.name5.name())
  //    );
  //    block(
  //      _return(), invoke(
  //        property.addMethodName(),
  //        t(ThisTemplate.property, "StandardPropertyName"), n(property.enumName()), end(),
  //        n(sig.name0.name()), end(),
  //        n(sig.name1.name()), end(),
  //        n(sig.name2.name()), end(),
  //        n(sig.name3.name()), end(),
  //        n(sig.name4.name()), end(),
  //        n(sig.name5.name()), end()
  //      )
  //    );
  //  }

  //  private ParameterElement t(ParameterType type) {
  //    if (type instanceof JavaType t) {
  //      return switch (t) {
  //        case DOUBLE -> _double();
  //        case INT -> _int();
  //        case STRING -> t(String.class);
  //      };
  //    } else if (type instanceof KeywordName name) {
  //      return t(keyword, name.simpleName);
  //    } else if (type instanceof PrimitiveType t) {
  //      var kind = t.kind;
  //      return t(ThisTemplate.type, kind.typeSimpleName());
  //    } else if (type instanceof ValueType t) {
  //      return t(ThisTemplate.type, t.simpleName);
  //    } else {
  //      throw new UnsupportedOperationException("Implement me :: " + type.getClass());
  //    }
  //  }

  //  private String toFieldName(String simpleName) {
  //    var fieldName = simpleName.replace('-', '_').toUpperCase();
  //
  //    return JavaNames.toIdentifier(fieldName);
  //  }

}