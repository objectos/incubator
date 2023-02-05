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

import static br.com.objectos.code.java.Java.a;
import static br.com.objectos.code.java.Java.expressionName;
import static br.com.objectos.code.java.Java.id;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.declaration.VarArgs;
import br.com.objectos.code.java.expression.ExpressionName;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.type.NamedClass;

public class TypeNames {

  public static final NamedClass _AngleType;

  public static final NamedClass _AngleUnit;

  public static final NamedClass _Color;

  public static final NamedClass _ColorMapBuilder;

  public static final NamedClass _ColorName;

  public static final NamedClass _ColorType;

  public static final NamedClass _GeneratedColor;

  public static final NamedClass _Length;

  public static final NamedClass _LengthType;

  public static final NamedClass _LengthUnit;

  public static final NamedClass _LengthValue;

  public static final NamedClass _PercentageValue;

  public static final NamedClass _Value;

  public static final VarArgs _Value___;

  public static final NamedClass _Zero;

  static final PackageName PACKAGE;

  static {
    PACKAGE = PackageName.named("br.com.objectos.css.type");

    _AngleType = className("AngleType");

    _AngleUnit = className("AngleUnit");

    _Color = className("Color");

    _ColorMapBuilder = className("ColorMapBuilder");

    _ColorName = className("ColorName");

    _ColorType = className("ColorType");

    _GeneratedColor = className("GeneratedColor");

    _Length = className("Length");

    _LengthType = className("LengthType");

    _LengthUnit = className("LengthUnit");

    _LengthValue = className("LengthValue");

    _PercentageValue = className("PercentageValue");

    _Value = className("Value");

    _Value___ = VarArgs.of(a(_Value));

    _Zero = className("Zero");
  }

  private TypeNames() {}

  public static ExpressionName angleUnitName(String unit) {
    String enumSimpleName;
    enumSimpleName = unit.toUpperCase();

    Identifier enumName;
    enumName = id(enumSimpleName);

    return expressionName(_AngleUnit, enumName);
  }

  public static NamedClass className(String simpleName) {
    return PACKAGE.nestedClass(simpleName);
  }

  public static ExpressionName lengthUnitName(String unit) {
    String enumSimpleName;
    enumSimpleName = unit.toUpperCase();

    Identifier enumName;
    enumName = id(enumSimpleName);

    return expressionName(_LengthUnit, enumName);
  }

}
