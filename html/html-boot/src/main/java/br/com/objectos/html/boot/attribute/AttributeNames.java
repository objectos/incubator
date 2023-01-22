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
package br.com.objectos.html.boot.attribute;

import static br.com.objectos.code.java.Java.t;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedArray;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedParameterized;
import objectos.util.UnmodifiableMap;

public class AttributeNames {

  public static final PackageName _PACKAGE = PackageName.named("br.com.objectos.html.attribute");

  public static final NamedClass AttributeKind = className("AttributeKind");

  public static final NamedClass AttributeName = className("AttributeName");

  public static final NamedClass GlobalAttributeName = className("GlobalAttributeName");

  public static final NamedClass NamesBuilder = className("NamesBuilder");

  public static final NamedClass StandardAttributeName = className("StandardAttributeName");

  public static final NamedArray StandardAttributeNameArray
      = StandardAttributeName.toNamedArray();

  static final NamedParameterized namesMapTypeName = t(
      t(UnmodifiableMap.class),
      t(String.class),
      StandardAttributeName
  );

  private AttributeNames() {}

  public static NamedClass className(String simpleName) {
    return _PACKAGE.nestedClass(simpleName);
  }

}
