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

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedClass;

public class SheetNames {

  public static final PackageName __PACKAGE = PackageName.named("br.com.objectos.css.sheet");

  public static final NamedClass _Declaration = className("Declaration");

  public static final NamedClass _GeneratedStyleSheet = className("GeneratedStyleSheet");

  public static final NamedClass _MultiDeclarationElement = className("MultiDeclarationElement");

  public static final NamedClass _StyleSheetDsl = className("StyleSheetDsl");

  static final NamedClass _AnyDeclaration = _GeneratedStyleSheet.nestedClass("AnyDeclaration");

  static final NamedClass _AnyFunction = _GeneratedStyleSheet.nestedClass("AnyFunction");

  private SheetNames() {}

  public static NamedClass className(String simpleName) {
    return __PACKAGE.nestedClass(simpleName);
  }

}
