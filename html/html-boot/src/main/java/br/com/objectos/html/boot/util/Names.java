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
package br.com.objectos.html.boot.util;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedClass;

public class Names {

  public static final PackageName _PKG_NAME = PackageName.named("br.com.objectos.html");

  public static final NamedClass AbstractStep = className("AbstractStep");
  public static final NamedClass Attribute = className("Attribute");
  public static final NamedClass BooleanAttribute = className("BooleanAttribute");
  public static final NamedClass Element = className("Element");
  public static final NamedClass ElementDsl = className("ElementDsl");
  public static final NamedClass Renderable = className("Renderable");
  public static final NamedClass String = NamedClass.of(String.class);

  public static final NamedClass StringAttribute = className("StringAttribute");

  private Names() {}

  public static NamedClass className(String simpleName) {
    return _PKG_NAME.nestedClass(simpleName);
  }

}