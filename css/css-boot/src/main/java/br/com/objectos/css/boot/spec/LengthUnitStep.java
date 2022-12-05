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

import java.util.Locale;

final class LengthUnitStep extends ThisTemplate {

  CssSpecDsl spec;

  @Override
  protected final void definition() {
    _package(type);

    autoImports();

    _enum(
      generatedAnnotation(),
      _public(), id("LengthUnit"),

      include(this::constants),

      field(
        _private(), _static(), _final(),
        t(t(type, "LengthUnit"), dim()), id("ARRAY"),
        invoke(t(type, "LengthUnit"), "values")
      ),

      field(
        _private(), _final(), t(String.class), id("name")
      ),

      constructor(
        _private(),
        assign(
          n(_this(), "name"),

          chain(
            invoke("name"),
            invoke("toLowerCase", n(t(Locale.class), "US"))
          )
        )
      ),

      method(
        _public(), _static(), t(type, "LengthUnit"), id("getByCode"),
        param(_int(), id("code")),
        _return(aget(n("ARRAY"), n("code")))
      ),

      method(
        _public(), _static(), _int(), id("size"),
        _return(n("ARRAY", "length"))
      ),

      method(
        _public(), _final(), _int(), id("getCode"),
        _return(invoke("ordinal"))
      ),

      method(
        _public(), _final(), t(String.class), id("getName"),
        _return(n("name"))
      )
    );
  }

  private void constants() {
    for (var unit : spec.lengthUnits()) {
      var simpleName = unit.toUpperCase();

      enumConstant(id(simpleName));
    }
  }

}
