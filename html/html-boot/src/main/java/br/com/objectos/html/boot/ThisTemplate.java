/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.boot;

import br.com.objectos.html.boot.spec.SpecDsl;
import objectos.code.ClassName;
import objectos.code.JavaTemplate;
import objectos.code.PackageName;

abstract class ThisTemplate extends JavaTemplate {

  static final PackageName spi_type;

  static final ClassName attr_StandardAttributeName;

  static final ClassName element_ElementName;

  static final ClassName element_StandardElementName;

  static final ClassName jlang_String = ClassName.of(String.class);

  static final ClassName spi_type_Value;

  static {
    PackageName attrPkg = PackageName.of("br.com.objectos.html.attribute");

    attr_StandardAttributeName = ClassName.of(attrPkg, "StandardAttributeName");

    PackageName elementPkg = PackageName.of("br.com.objectos.html.element");

    element_ElementName = ClassName.of(elementPkg, "ElementName");

    element_StandardElementName = ClassName.of(elementPkg, "StandardElementName");

    spi_type = PackageName.of("br.com.objectos.html.spi.type");

    spi_type_Value = ClassName.of(spi_type, "Value");
  }

  SpecDsl spec;

}