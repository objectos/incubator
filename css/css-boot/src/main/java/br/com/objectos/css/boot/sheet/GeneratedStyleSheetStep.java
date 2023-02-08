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
import static br.com.objectos.code.java.Java._double;
import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._int;
import static br.com.objectos.code.java.Java._protected;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java.constructor;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.init;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.param;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.InterfaceCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.declaration.TypeCode;
import br.com.objectos.code.java.declaration.VarArgs;
import br.com.objectos.code.java.expression.ExpressionName;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedPrimitive;
import br.com.objectos.css.boot.CssBoot;
import br.com.objectos.css.boot.function.FunctionName;
import br.com.objectos.css.boot.function.FunctionNames;
import br.com.objectos.css.boot.keyword.KeywordName;
import br.com.objectos.css.boot.keyword.KeywordNames;
import br.com.objectos.css.boot.property.Property;
import br.com.objectos.css.boot.property.PropertyNames;
import br.com.objectos.css.boot.select.PseudoClassSelectorsGen;
import br.com.objectos.css.boot.select.PseudoElementSelectorsGen;
import br.com.objectos.css.boot.select.TypeSelectorsGen;
import br.com.objectos.css.boot.spec.Ids;
import br.com.objectos.css.boot.spec.AbstractStep;
import br.com.objectos.css.boot.spec.StepAdapter;
import br.com.objectos.css.boot.spec.Types;
import br.com.objectos.css.boot.type.ColorName;
import br.com.objectos.css.boot.type.TypeNames;

public class GeneratedStyleSheetStep extends AbstractStep {

  private final InterfaceCode.Builder anyDeclaration;

  private final InterfaceCode.Builder anyFunction;

  private final ClassCode.Builder builder;

  public GeneratedStyleSheetStep(StepAdapter adapter) {
    super(adapter);

    builder = ClassCode.builder();
    builder.addAnnotation(CssBoot.GENERATED);
    builder.addModifier(Modifiers._abstract());
    builder.simpleName(SheetNames._GeneratedStyleSheet);
    builder.addConstructor(constructor());

    anyDeclaration = InterfaceCode.builder();
    anyDeclaration.simpleName(SheetNames._AnyDeclaration);

    anyFunction = InterfaceCode.builder();
    anyFunction.simpleName(SheetNames._AnyFunction);
  }

  @Override
  public final void addAngleUnit(String unit) {
    ExpressionName unitName;
    unitName = TypeNames.angleUnitName(unit);

    Identifier methodName;
    methodName = id(unit);

    builder.addMethod(
        method(
            _protected(), _final(), TypeNames._AngleType, methodName,
            param(_double(), Ids.value),
            _return(invoke(Ids.getAngle.name(), unitName, Ids.value))
        )
    );

    builder.addMethod(
        method(
            _protected(), _final(), TypeNames._AngleType, methodName,
            param(_int(), Ids.value),
            _return(invoke(Ids.getAngle.name(), unitName, Ids.value))
        )
    );
  }

  public final void addAnyDeclarationExtends(NamedClass iface) {
    anyDeclaration.addExtends(iface);
  }

  @Override
  public final void addColorName(ColorName colorName) {
    builder.addField(
        field(
            _protected(), _static(), _final(), TypeNames._Color,
            init(colorName.identifier, TypeNames._Color.id(colorName.identifier))
        )
    );
  }

  @Override
  public final void addElementName(String elementName) {
    builder.addField(TypeSelectorsGen.styleSheetField(elementName));
  }

  @Override
  public final void addFunction(FunctionName function) {
    anyFunction.addExtends(function.getSingleDeclarationName());
  }

  @Override
  public final void addKeyword(KeywordName keyword) {
    builder.addField(
        field(
            _protected(), _static(), _final(), keyword.className,
            init(keyword.fieldName, KeywordNames._Keywords.id(keyword.fieldName))
        )
    );
  }

  @Override
  public final void addLengthUnit(String unit) {
    ExpressionName unitName;
    unitName = TypeNames.lengthUnitName(unit);

    Identifier methodName;
    methodName = id(unit);

    builder.addMethod(
        method(
            _protected(), _final(), TypeNames._LengthType, methodName,
            param(_double(), Ids.value),
            _return(invoke(Ids.getLength.name(), unitName, Ids.value))
        )
    );

    builder.addMethod(
        method(
            _protected(), _final(), TypeNames._LengthType, methodName,
            param(_int(), Ids.value),
            _return(invoke(Ids.getLength.name(), unitName, Ids.value))
        )
    );
  }

  @Override
  public final void addMethodSignature(FunctionOrProperty property, MethodSignature signature) {
    builder.addMethod(
        signature.writeMethodCode(property)
    );
  }

  @Override
  public final void addProperty(Property property) {
    property.acceptGeneratedStyleSheetStep(this);
  }

  @Override
  public final void addPseudoClass(String name) {
    builder.addField(PseudoClassSelectorsGen.styleSheetField(name));
  }

  @Override
  public final void addPseudoElement(String name) {
    builder.addField(PseudoElementSelectorsGen.styleSheetField(name));
  }

  public final void addType(TypeCode type) {
    builder.addType(type);
  }

  @Override
  public final void execute() {
    writeJavaFile(
        SheetNames.__PACKAGE,
        classCode()
    );
  }

  private ClassCode classCode() {
    NamedClass singleDeclaration = SheetNames._MultiDeclarationElement;

    NamedArray singleDeclarationArray = singleDeclaration.toNamedArray();

    VarArgs singleDeclarationVarArgs = VarArgs.of(singleDeclarationArray);

    return builder
        .addMethod(
            method(
                _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
                param(PropertyNames.StandardPropertyName, Ids.name),
                param(NamedPrimitive.INT, Ids.value)
            )
        )
        .addMethod(
            method(
                _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
                param(PropertyNames.StandardPropertyName, Ids.name),
                param(NamedPrimitive.DOUBLE, Ids.value)
            )
        )
        .addMethod(
            method(
                _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
                param(PropertyNames.StandardPropertyName, Ids.name),
                param(singleDeclarationVarArgs, Ids.elements)
            )
        )
        .addMethod(
            method(
                _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
                param(PropertyNames.StandardPropertyName, Ids.name),
                param(Types._String, Ids.value)
            )
        )
        .addMethod(
            method(
                _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
                param(PropertyNames.StandardPropertyName, Ids.name),
                param(TypeNames._Value, Ids.v1)
            )
        )
        .addMethod(
            method(
                _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
                param(PropertyNames.StandardPropertyName, Ids.name),
                param(TypeNames._Value, Ids.v1),
                param(TypeNames._Value, Ids.v2)
            )
        )
        .addMethod(
            method(
                _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
                param(PropertyNames.StandardPropertyName, Ids.name),
                param(TypeNames._Value, Ids.v1),
                param(TypeNames._Value, Ids.v2),
                param(TypeNames._Value, Ids.v3)
            )
        )
        .addMethod(
            method(
                _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
                param(PropertyNames.StandardPropertyName, Ids.name),
                param(TypeNames._Value, Ids.v1),
                param(TypeNames._Value, Ids.v2),
                param(TypeNames._Value, Ids.v3),
                param(TypeNames._Value, Ids.v4)
            )
        )
        .addMethod(
            method(
                _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
                param(PropertyNames.StandardPropertyName, Ids.name),
                param(TypeNames._Value, Ids.v1),
                param(TypeNames._Value, Ids.v2),
                param(TypeNames._Value, Ids.v3),
                param(TypeNames._Value, Ids.v4),
                param(TypeNames._Value, Ids.v5)
            )
        )
        .addMethod(
            method(
                _abstract(), SheetNames._AnyDeclaration, Ids.addDeclaration,
                param(PropertyNames.StandardPropertyName, Ids.name),
                param(TypeNames._Value, Ids.v1),
                param(TypeNames._Value, Ids.v2),
                param(TypeNames._Value, Ids.v3),
                param(TypeNames._Value, Ids.v4),
                param(TypeNames._Value, Ids.v5),
                param(TypeNames._Value, Ids.v6)
            )
        )
        .addMethod(
            method(
                _abstract(), SheetNames._AnyFunction, Ids.addFunction,
                param(FunctionNames.StandardFunctionName, Ids.name),
                param(TypeNames._Value, Ids.v1)
            )
        )
        .addMethod(
            method(
                _abstract(), TypeNames._AngleType, Ids.getAngle,
                param(TypeNames._AngleUnit, Ids.unit),
                param(_double(), Ids.value)
            )
        )
        .addMethod(
            method(
                _abstract(), TypeNames._AngleType, Ids.getAngle,
                param(TypeNames._AngleUnit, Ids.unit),
                param(_int(), Ids.value)
            )
        )
        .addMethod(
            method(
                _abstract(), TypeNames._LengthType, Ids.getLength,
                param(TypeNames._LengthUnit, Ids.unit),
                param(_double(), Ids.value)
            )
        )
        .addMethod(
            method(
                _abstract(), TypeNames._LengthType, Ids.getLength,
                param(TypeNames._LengthUnit, Ids.unit),
                param(_int(), Ids.value)
            )
        )
        .addType(anyDeclaration.build())
        .addType(anyFunction.build())
        .build();
  }

}