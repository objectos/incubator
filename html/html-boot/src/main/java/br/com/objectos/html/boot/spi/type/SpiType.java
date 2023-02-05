/*
 * Copyright (C) 2015-2023 Objectos Software LTDA.
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
package br.com.objectos.html.boot.spi.type;

import static br.com.objectos.code.java.Java.a;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;

public class SpiType {

  static final PackageName PACKAGE = PackageName.named("br.com.objectos.html.spi.type");

  static final NamedClass AnyElementValue = className("AnyElementValue");
  static final NamedClass NonVoidElementValue = className("NonVoidElementValue");

  public static final NamedClass Value = className("Value");
  public static final NamedArray ValueArray = a(Value);

  private SpiType() {}

  public static NamedClass className(String simpleName) {
    return PACKAGE.nestedClass(simpleName);
  }

}
