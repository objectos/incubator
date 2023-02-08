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

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.css.boot.function.FunctionName;
import br.com.objectos.css.boot.keyword.KeywordName;
import br.com.objectos.css.boot.property.ParameterType;
import br.com.objectos.css.boot.property.Property;
import br.com.objectos.css.boot.property.PropertyKind;
import br.com.objectos.css.boot.sheet.FunctionOrProperty;
import br.com.objectos.css.boot.sheet.MethodSignature;
import br.com.objectos.css.boot.sheet.MethodSignature.Abstract1;
import br.com.objectos.css.boot.sheet.MethodSignature.SigHash;
import br.com.objectos.css.boot.sheet.MethodSignature.SigZero;
import br.com.objectos.css.boot.sheet.MethodSignature.Signature1;
import br.com.objectos.css.boot.sheet.MethodSignature.Signature2;
import br.com.objectos.css.boot.sheet.MethodSignature.Signature3;
import br.com.objectos.css.boot.sheet.MethodSignature.Signature4;
import br.com.objectos.css.boot.sheet.MethodSignature.Signature5;
import br.com.objectos.css.boot.sheet.MethodSignature.Signature6;
import br.com.objectos.css.boot.type.ColorName;
import br.com.objectos.css.boot.type.JavaType;
import br.com.objectos.css.boot.type.PrimitiveType;
import br.com.objectos.css.boot.type.ValueType;
import java.util.List;
import objectos.util.GrowableList;

final class GeneratedStyleSheetStep extends ThisTemplate {

  private final class ThisAbstract1 extends ThisSignature {
    private final Abstract1 signature;

    public ThisAbstract1(FunctionOrProperty property, Abstract1 sig) {
      super(property);
      signature = sig;
    }

    @Override
    final void execute() {
      _protected();
      _abstract();
      t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName());
      method(
        property.methodName(),
        t(signature.type), id(signature.name.name())
      );
    }
  }

  private final class ThisSigHash extends ThisSignature {
    public ThisSigHash(FunctionOrProperty property, SigHash signature) {
      super(property);
    }

    @Override
    final void execute() {
      _protected();
      _final();
      t(sheet, "GeneratedStyleSheet", property.multiDeclarationSimpleName());
      method(
        property.methodName(),
        t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName()), ellipsis(),
        id("declarations")
      );
      block(
        _return(), invoke(
          property.addMethodName(),
          funcOrProp(), n(property.enumName()), end(),
          n("declarations")
        )
      );
    }
  }

  private abstract sealed class ThisSignature {

    final FunctionOrProperty property;

    public ThisSignature(FunctionOrProperty property) { this.property = property; }

    void execute() {}

    final objectos.code.JavaTemplate.ArgsPart funcOrProp() {
      if (property instanceof FunctionName) {
        return t(ThisTemplate.function, "StandardFunctionName");
      } else {
        return t(ThisTemplate.property, "StandardPropertyName");
      }
    }

    final objectos.code.JavaTemplate.ArgsPart funcOrPropSingle() {
      if (property instanceof FunctionName) {
        return t(ThisTemplate.function, property.singleDeclarationSimpleName());
      } else {
        return t(sheet, "GeneratedStyleSheet", property.singleDeclarationSimpleName());
      }
    }
  }

  private final class ThisSignature1 extends ThisSignature {
    private final Signature1 signature;

    public ThisSignature1(FunctionOrProperty property, Signature1 signature) {
      super(property);
      this.signature = signature;
    }

    @Override
    final void execute() {
      _protected();
      _final();
      funcOrPropSingle();
      method(
        property.methodName(),
        t(signature.type), id(signature.name.name())
      );
      block(
        _return(), invoke(
          property.addMethodName(),
          funcOrProp(), n(property.enumName()), end(),
          n(signature.name.name()), end()
        )
      );
    }
  }

  private final class ThisSignature2 extends ThisSignature {
    private final Signature2 signature;

    public ThisSignature2(FunctionOrProperty property, Signature2 signature) {
      super(property);
      this.signature = signature;
    }

    @Override
    final void execute() {
      _protected();
      _final();
      funcOrPropSingle();
      method(
        property.methodName(),
        t(signature.type0), id(signature.name0.name()),
        t(signature.type1), id(signature.name1.name())
      );
      block(
        _return(), invoke(
          property.addMethodName(),
          funcOrProp(), n(property.enumName()), end(),
          n(signature.name0.name()), end(),
          n(signature.name1.name()), end()
        )
      );
    }
  }

  private final class ThisSignature3 extends ThisSignature {
    private final Signature3 signature;

    public ThisSignature3(FunctionOrProperty property, Signature3 signature) {
      super(property);
      this.signature = signature;
    }

    @Override
    final void execute() {
      _protected();
      _final();
      funcOrPropSingle();
      method(
        property.methodName(),
        t(signature.type0), id(signature.name0.name()),
        t(signature.type1), id(signature.name1.name()),
        t(signature.type2), id(signature.name2.name())
      );
      block(
        _return(), invoke(
          property.addMethodName(),
          funcOrProp(), n(property.enumName()), end(),
          n(signature.name0.name()), end(),
          n(signature.name1.name()), end(),
          n(signature.name2.name()), end()
        )
      );
    }
  }

  private final class ThisSignature4 extends ThisSignature {
    private final Signature4 signature;

    public ThisSignature4(FunctionOrProperty property, Signature4 signature) {
      super(property);
      this.signature = signature;
    }

    @Override
    final void execute() {
      _protected();
      _final();
      funcOrPropSingle();
      method(
        property.methodName(),
        t(signature.type0), id(signature.name0.name()),
        t(signature.type1), id(signature.name1.name()),
        t(signature.type2), id(signature.name2.name()),
        t(signature.type3), id(signature.name3.name())
      );
      block(
        _return(), invoke(
          property.addMethodName(),
          funcOrProp(), n(property.enumName()), end(),
          n(signature.name0.name()), end(),
          n(signature.name1.name()), end(),
          n(signature.name2.name()), end(),
          n(signature.name3.name()), end()
        )
      );
    }
  }

  private final class ThisSignature5 extends ThisSignature {
    private final Signature5 signature;

    public ThisSignature5(FunctionOrProperty property, Signature5 signature) {
      super(property);
      this.signature = signature;
    }

    @Override
    final void execute() {
      _protected();
      _final();
      funcOrPropSingle();
      method(
        property.methodName(),
        t(signature.type0), id(signature.name0.name()),
        t(signature.type1), id(signature.name1.name()),
        t(signature.type2), id(signature.name2.name()),
        t(signature.type3), id(signature.name3.name()),
        t(signature.type4), id(signature.name4.name())
      );
      block(
        _return(), invoke(
          property.addMethodName(),
          funcOrProp(), n(property.enumName()), end(),
          n(signature.name0.name()), end(),
          n(signature.name1.name()), end(),
          n(signature.name2.name()), end(),
          n(signature.name3.name()), end(),
          n(signature.name4.name()), end()
        )
      );
    }
  }

  private final class ThisSignature6 extends ThisSignature {
    private final Signature6 signature;

    public ThisSignature6(FunctionOrProperty property, Signature6 signature) {
      super(property);
      this.signature = signature;
    }

    @Override
    final void execute() {
      _protected();
      _final();
      funcOrPropSingle();
      method(
        property.methodName(),
        t(signature.type0), id(signature.name0.name()),
        t(signature.type1), id(signature.name1.name()),
        t(signature.type2), id(signature.name2.name()),
        t(signature.type3), id(signature.name3.name()),
        t(signature.type4), id(signature.name4.name()),
        t(signature.type5), id(signature.name5.name())
      );
      block(
        _return(), invoke(
          property.addMethodName(),
          funcOrProp(), n(property.enumName()), end(),
          n(signature.name0.name()), end(),
          n(signature.name1.name()), end(),
          n(signature.name2.name()), end(),
          n(signature.name3.name()), end(),
          n(signature.name4.name()), end(),
          n(signature.name5.name()), end()
        )
      );
    }
  }

  private final class ThisSigZero extends ThisSignature {
    public ThisSigZero(FunctionOrProperty property, SigZero signature) {
      super(property);
    }

    @Override
    final void execute() {
      _protected();
      _final();
      funcOrPropSingle();
      method(
        property.methodName(),
        t(type, "Zero"), id("zero")
      );
      block(
        _return(), invoke(
          property.addMethodName(),
          funcOrProp(), n(property.enumName()), end(),
          i(0)
        )
      );
    }

  }

  private final List<String> angleUnitList = new GrowableList<>();

  private final List<String> anyDeclarationList = new GrowableList<>();

  private final List<String> anyFunctionList = new GrowableList<>();

  private final List<String> colorList = new GrowableList<>();

  private final List<String> elementList = new GrowableList<>();

  private final List<KeywordName> keywordList = new GrowableList<>();

  private final List<String> lengthUnitList = new GrowableList<>();

  private final List<Property> propertyList = new GrowableList<>();

  private final List<String> pseudoClassList = new GrowableList<>();

  private final List<String> pseudoElementList = new GrowableList<>();

  private final List<ThisSignature> signatureList = new GrowableList<>();

  GeneratedStyleSheetStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addAngleUnit(String unit) {
    angleUnitList.add(unit);
  }

  @Override
  public final void addColorName(ColorName colorName) {
    br.com.objectos.code.java.expression.Identifier id = colorName.identifier;

    colorList.add(id.name());
  }

  @Override
  public final void addElementName(String elementName) {
    elementList.add(elementName);
  }

  @Override
  public final void addFunction(FunctionName function) {
    anyFunctionList.add(function.singleDeclarationSimpleName());
  }

  @Override
  public final void addKeyword(KeywordName keyword) {
    keywordList.add(keyword);
  }

  @Override
  public final void addLengthUnit(String unit) {
    lengthUnitList.add(unit);
  }

  @Override
  public final void addProperty(Property property) {
    propertyList.add(property);

    var multiName = property.multiDeclarationSimpleName();

    var singleName = property.singleDeclarationSimpleName();

    switch (property.kind) {
      case HASH -> {
        anyDeclarationList.add(multiName);

        anyDeclarationList.add(singleName);
      }

      case STANDARD -> {
        anyDeclarationList.add(singleName);
      }
    }
  }

  @Override
  public final void addMethodSignature(FunctionOrProperty property, MethodSignature signature) {
    ThisSignature result;

    if (signature instanceof Abstract1 sig) {
      result = new ThisAbstract1(property, sig);
    } else if (signature instanceof SigHash sig) {
      result = new ThisSigHash(property, sig);
    } else if (signature instanceof Signature1 sig) {
      result = new ThisSignature1(property, sig);
    } else if (signature instanceof Signature2 sig) {
      result = new ThisSignature2(property, sig);
    } else if (signature instanceof Signature3 sig) {
      result = new ThisSignature3(property, sig);
    } else if (signature instanceof Signature4 sig) {
      result = new ThisSignature4(property, sig);
    } else if (signature instanceof Signature5 sig) {
      result = new ThisSignature5(property, sig);
    } else if (signature instanceof Signature6 sig) {
      result = new ThisSignature6(property, sig);
    } else if (signature instanceof SigZero sig) {
      result = new ThisSigZero(property, sig);
    } else {
      throw new UnsupportedOperationException("Implement me :: " + signature.getClass());
    }

    signatureList.add(result);
  }

  @Override
  public final void addPseudoClass(String name) {
    var fieldName = toFieldName(name);

    pseudoClassList.add(fieldName);
  }

  @Override
  public final void addPseudoElement(String name) {
    var fieldName = toFieldName(name);

    pseudoElementList.add(fieldName);
  }

  @Override
  public final void execute() {
    writeSelf();
  }

  @Override
  protected final void definition() {
    _package(sheet);

    autoImports();

    generatedAnnotation();
    _abstract();
    _class("GeneratedStyleSheet");
    body(
      include(this::fields),

      constructor(), block(),

      include(this::angleMethods),

      include(this::lengthMethods),

      include(this::propertyMethods),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyDeclaration"),
      method(
        "addDeclaration",
        t(property, "StandardPropertyName"), id("name"),
        _int(), id("value")
      ),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyDeclaration"),
      method(
        "addDeclaration",
        t(property, "StandardPropertyName"), id("name"),
        _double(), id("value")
      ),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyDeclaration"),
      method(
        "addDeclaration",
        t(property, "StandardPropertyName"), id("name"),
        t(sheet, "MultiDeclarationElement"), ellipsis(), id("elements")
      ),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyDeclaration"),
      method(
        "addDeclaration",
        t(property, "StandardPropertyName"), id("name"),
        t(String.class), id("value")
      ),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyDeclaration"),
      method(
        "addDeclaration",
        t(property, "StandardPropertyName"), id("name"),
        t(type, "Value"), id("v1")
      ),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyDeclaration"),
      method(
        "addDeclaration",
        t(property, "StandardPropertyName"), id("name"),
        t(type, "Value"), id("v1"),
        t(type, "Value"), id("v2")
      ),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyDeclaration"),
      method(
        "addDeclaration",
        t(property, "StandardPropertyName"), id("name"),
        t(type, "Value"), id("v1"),
        t(type, "Value"), id("v2"),
        t(type, "Value"), id("v3")
      ),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyDeclaration"),
      method(
        "addDeclaration",
        t(property, "StandardPropertyName"), id("name"),
        t(type, "Value"), id("v1"),
        t(type, "Value"), id("v2"),
        t(type, "Value"), id("v3"),
        t(type, "Value"), id("v4")
      ),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyDeclaration"),
      method(
        "addDeclaration",
        t(property, "StandardPropertyName"), id("name"),
        t(type, "Value"), id("v1"),
        t(type, "Value"), id("v2"),
        t(type, "Value"), id("v3"),
        t(type, "Value"), id("v4"),
        t(type, "Value"), id("v5")
      ),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyDeclaration"),
      method(
        "addDeclaration",
        t(property, "StandardPropertyName"), id("name"),
        t(type, "Value"), id("v1"),
        t(type, "Value"), id("v2"),
        t(type, "Value"), id("v3"),
        t(type, "Value"), id("v4"),
        t(type, "Value"), id("v5"),
        t(type, "Value"), id("v6")
      ),

      _abstract(),
      t(sheet, "GeneratedStyleSheet", "AnyFunction"),
      method(
        "addFunction",
        t(function, "StandardFunctionName"), id("name"),
        t(type, "Value"), id("v1")
      ),

      _abstract(),
      t(type, "AngleType"),
      method(
        "getAngle",
        t(type, "AngleUnit"), id("unit"),
        _double(), id("value")
      ),

      _abstract(),
      t(type, "AngleType"),
      method(
        "getAngle",
        t(type, "AngleUnit"), id("unit"),
        _int(), id("value")
      ),

      _abstract(),
      t(type, "LengthType"),
      method(
        "getLength",
        t(type, "LengthUnit"), id("unit"),
        _double(), id("value")
      ),

      _abstract(),
      t(type, "LengthType"),
      method(
        "getLength",
        t(type, "LengthUnit"), id("unit"),
        _int(), id("value")
      ),

      include(this::propertyInterfaces)
    );
  }

  private void propertyInterfaces() {
    for (var property : propertyList) {
      PropertyKind kind = property.kind;

      switch (kind) {
        case HASH -> {
          _protected();
          _interface(property.multiDeclarationSimpleName());
          _extends();
          t(sheet, "Declaration");
          body();

          _protected();
          _interface(property.singleDeclarationSimpleName());
          _extends();
          t(sheet, "MultiDeclarationElement");
          body();
        }

        case STANDARD -> {
          _protected();
          _interface(property.singleDeclarationSimpleName());
          _extends();
          t(sheet, "Declaration");
          body();
        }
      }
    }

    _interface("AnyDeclaration");
    _extends();
    for (var simplName : anyDeclarationList) {
      t(sheet, "GeneratedStyleSheet", simplName);
    }
    body();

    _interface("AnyFunction");
    _extends();
    for (var simplName : anyFunctionList) {
      t(function, simplName);
    }
    body();
  }

  private void angleMethods() {
    for (var unit : angleUnitList) {
      var enumSimpleName = unit.toUpperCase();

      _protected();
      _final();
      t(type, "AngleType");
      method(unit, _double(), id("value"));
      block(
        _return(), invoke("getAngle", t(type, "AngleUnit"), n(enumSimpleName), end(), n("value"))
      );

      _protected();
      _final();
      t(type, "AngleType");
      method(unit, _int(), id("value"));
      block(
        _return(), invoke("getAngle", t(type, "AngleUnit"), n(enumSimpleName), end(), n("value"))
      );
    }
  }

  private void fields() {
    for (var elementName : elementList) {
      _protected();
      _static();
      _final();
      t(select, "TypeSelector");
      id(elementName);
      t(css, "Css");
      n(elementName);
    }

    for (var name : pseudoClassList) {
      _protected();
      _static();
      _final();
      t(select, "PseudoClassSelector");
      id(name);
      t(css, "Css");
      n(name);
    }

    for (var name : pseudoElementList) {
      _protected();
      _static();
      _final();
      t(select, "PseudoElementSelector");
      id(name);
      t(css, "Css");
      n(name);
    }

    for (var name : colorList) {
      _protected();
      _static();
      _final();
      t(type, "Color");
      id(name);
      t(type, "Color");
      n(name);
    }

    for (var kw : keywordList) {
      br.com.objectos.code.java.expression.Identifier fieldName = kw.fieldName;

      var id = fieldName.name();

      _protected();
      _static();
      _final();
      t(keyword, kw.simpleName);
      id(id);
      t(keyword, "Keywords");
      n(id);
    }
  }

  private void lengthMethods() {
    for (var unit : lengthUnitList) {
      var enumSimpleName = unit.toUpperCase();

      _protected();
      _final();
      t(type, "LengthType");
      method(unit, _double(), id("value"));
      block(
        _return(), invoke("getLength", t(type, "LengthUnit"), n(enumSimpleName), end(), n("value"))
      );

      _protected();
      _final();
      t(type, "LengthType");
      method(unit, _int(), id("value"));
      block(
        _return(), invoke("getLength", t(type, "LengthUnit"), n(enumSimpleName), end(), n("value"))
      );
    }
  }

  private void propertyMethods() {
    for (var signature : signatureList) {
      signature.execute();
    }
  }

  private objectos.code.JavaTemplate.ParameterElement t(ParameterType type) {
    if (type instanceof JavaType t) {
      return switch (t) {
        case DOUBLE -> _double();
        case INT -> _int();
        case STRING -> t(String.class);
      };
    } else if (type instanceof KeywordName name) {
      return t(keyword, name.simpleName);
    } else if (type instanceof PrimitiveType t) {
      var kind = t.kind;
      return t(ThisTemplate.type, kind.typeSimpleName());
    } else if (type instanceof ValueType t) {
      return t(ThisTemplate.type, t.simpleName);
    } else {
      throw new UnsupportedOperationException("Implement me :: " + type.getClass());
    }
  }

  private String toFieldName(String simpleName) {
    var fieldName = simpleName.replace('-', '_').toUpperCase();

    return JavaNames.toIdentifier(fieldName);
  }

}