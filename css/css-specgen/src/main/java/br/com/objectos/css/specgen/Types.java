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
package br.com.objectos.css.specgen;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedClass;

public final class Types {

  static final PackageName BOOT = PackageName.named("br.com.objectos.css.boot");
  static final PackageName BOOT_KEYWORD = BOOT.nestedPackage("keyword");
  static final PackageName BOOT_SPEC = BOOT.nestedPackage("spec");

  static final NamedClass AbstractPropertyModule = BOOT.nestedClass("AbstractPropertyModule");
  public static final NamedClass KeywordName = BOOT_KEYWORD.nestedClass("KeywordName");
  static final NamedClass Source = BOOT_SPEC.nestedClass("Source");

  private Types() {}

}
