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
package br.com.objectos.css.boot.keyword;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedClass;

public class KeywordNames {

  static final PackageName PACKAGE = PackageName.named("br.com.objectos.css.keyword");

  static final NamedClass _ImmutableMapBuilder = className("ImmutableMapBuilder");
  static final NamedClass _StandardKeyword = className("StandardKeyword");
  static final NamedClass _UnknownKeyword = className("UnknownKeyword");
  public static final NamedClass _Keywords = className("Keywords");

  private KeywordNames() {}

  public static NamedClass className(String simpleName) {
    return PACKAGE.nestedClass(simpleName);
  }

}
