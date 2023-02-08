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
package br.com.objectos.css.boot.spec;

import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

final class LengthUnitStep extends ThisTemplate {

  private final Set<String> simpleNames = new TreeSet<>();

  LengthUnitStep(StepAdapter adapter) {
    super(adapter);
  }

  @Override
  public final void addLengthUnit(String unit) {
    var simpleName = unit.toUpperCase();

    simpleNames.add(simpleName);
  }

  @Override
  public final void execute() {
    writeSelf();
  }

  @Override
  protected final void definition() {
    _package(type);

    autoImports();

    generatedAnnotation();
    _public();
    _enum("LengthUnit");
    body(
      include(this::constants),

      _private(), _static(), _final(), t(t(type, "LengthUnit"), dim()), id("ARRAY"),
      t(type, "LengthUnit"), invoke("values"),

      _private(), _final(), t(String.class), id("name"),

      _private(), constructor(), block(
        _this(), n("name"), gets(), invoke("name"), invoke("toLowerCase", t(Locale.class), n("US"))
      ),

      _public(), _static(), t(type, "LengthUnit"), method("getByCode", _int(), id("code")), block(
        _return(), n("ARRAY"), dim(n("code"))
      ),

      _public(), _static(), _int(), method("size"), block(
        _return(), n("ARRAY"), n("length")
      ),

      _public(), _final(), _int(), method("getCode"), block(
        _return(), invoke("ordinal")
      ),

      _public(), _final(), t(String.class), method("getName"), block(
        _return(), n("name")
      )
    );
  }

  private void constants() {
    for (var simpleName : simpleNames) {
      enumConstant(simpleName);
    }
  }

}
