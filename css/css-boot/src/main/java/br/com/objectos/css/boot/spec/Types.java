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
package br.com.objectos.css.boot.spec;

import br.com.objectos.code.java.Java;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.expression.NewClass;
import br.com.objectos.code.java.type.NamedClass;
import objectos.util.ImmutableMap;
import objectos.util.MutableMap;

public class Types {

  public static final NamedClass _Css;

  public static final NamedClass _ImmutableMap;

  public static final NamedClass _MutableMap;

  public static final PackageName _PACKAGE;

  public static final NamedClass _String;

  private static final NewClass NEW_GROWABLE_MAP;

  static {
    _PACKAGE = PackageName.named("br.com.objectos.css");

    _Css = _PACKAGE.nestedClass("Css");

    _MutableMap = NamedClass.of(MutableMap.class);

    _ImmutableMap = NamedClass.of(ImmutableMap.class);

    _String = NamedClass.of(String.class);

    NEW_GROWABLE_MAP = Java._new(_MutableMap, Java.hint());
  }

  private Types() {}

  public static NewClass _newMutableMap() {
    return NEW_GROWABLE_MAP;
  }

  public static PackageName cssPackage() {
    return _PACKAGE;
  }

}
