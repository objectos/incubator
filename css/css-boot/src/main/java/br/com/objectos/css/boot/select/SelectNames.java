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
package br.com.objectos.css.boot.select;

import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._protected;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.init;
import static br.com.objectos.code.java.Java.t;

import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedParameterized;
import br.com.objectos.css.boot.spec.Types;

class SelectNames {

  static final PackageName PACKAGE = PackageName.named("br.com.objectos.css.select");

  public static final NamedClass PseudoClassSelector = cn("PseudoClassSelector");
  public static final NamedClass PseudoClassSelectors = cn("PseudoClassSelectors");
  public static final NamedClass PseudoElementSelector = cn("PseudoElementSelector");
  public static final NamedClass PseudoElementSelectors = cn("PseudoElementSelectors");

  public static final NamedClass TypeSelector = cn("TypeSelector");
  public static final NamedClass TypeSelectors = cn("TypeSelectors");

  static final NamedArray TypeSelectorArray = TypeSelector.toNamedArray();
  static final NamedParameterized TypeSelectorGrowableMap = t(
      Types._MutableMap,
      Types._String,
      TypeSelector
  );
  static final NamedParameterized TypeSelectorUnmodifiableMap = t(
      Types._UnmodifiableMap,
      Types._String,
      TypeSelector
  );

  static final String CORE_PKG_NAME = "br.com.objectos.css.select";

  static final String StaticAttributeProvider = CORE_PKG_NAME + ".StaticAttributeProvider";
  static final String StaticFieldProvider = CORE_PKG_NAME + ".StaticFieldProvider";
  static final String StaticMethodProvider = CORE_PKG_NAME + ".StaticMethodProvider";
  static final String StaticType = CORE_PKG_NAME + ".StaticType";

  static final String SelectorDslSpec = CORE_PKG_NAME + ".SelectorDslSpec";
  static final String SelectorImplSpec = CORE_PKG_NAME + ".SelectorImplSpec";

  private SelectNames() {}

  static FieldCode styleSheetField(NamedClass type, String name) {
    Identifier identifier = id(name);
    return field(
        _protected(), _static(), _final(), type,
        init(identifier, Types._Css.id(identifier))
    );
  }

  private static NamedClass cn(String simpleName) {
    return PACKAGE.nestedClass(simpleName);
  }

}