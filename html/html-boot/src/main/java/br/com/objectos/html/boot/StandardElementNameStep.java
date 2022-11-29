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

final class StandardElementNameStep extends ThisTemplate {

  @Override
  protected final void definition() {
    _package(elementPkg.name);

    autoImports();

    _enum(
      generatedAnnotation(),
      _public(), id("StandardElementName"), _implements(element_ElementName),

      include(this::enumConstants),

      field(
        _private(), _static(), _final(),
        t(t(element_StandardElementName), dim()),
        id("ARRAY"), invoke(t(element_StandardElementName), "values")
      ),

      field(_private(), _final(), t(element_ElementKind), id("kind")),
      field(_private(), _final(), t(jlang_String), id("name")),

      constructor(
        _private(),
        param(t(element_ElementKind), id("kind")),
        param(t(jlang_String), id("name")),
        assign(n(_this(), "kind"), n("kind")),
        assign(n(_this(), "name"), n("name"))
      ),

      method(
        _public(), _static(), t(element_StandardElementName), id("getByCode"),
        param(_int(), id("code")),
        _return(aget(n("ARRAY"), n("code")))
      ),

      method(
        _public(), _static(), _int(), id("size"),
        _return(n("ARRAY", "length"))
      ),

      method(
        annotation(t(Override.class)),
        _public(), _final(), _int(), id("getCode"),
        _return(invoke("ordinal"))
      ),

      method(
        annotation(t(Override.class)),
        _public(), _final(), t(element_ElementKind), id("getKind"),
        _return(n("kind"))
      ),

      method(
        annotation(t(Override.class)),
        _public(), _final(), t(jlang_String), id("getName"),
        _return(n("name"))
      ),

      method(
        annotation(t(Override.class)),
        _public(), _final(), _void(), id("mark"),
        param(t(spi_tmpl_Marker), id("marker")),
        invoke(n("marker"), "markElement")
      ),

      method(
        annotation(t(Override.class)),
        _public(), _final(), _void(), id("render"),
        param(t(spi_tmpl_Renderer), id("renderer"))
      )
    );
  }

  private void enumConstants() {
    for (var element : spec.elements()) {
      enumConstant(
        id(element.constantName),
        n(element_ElementKind, element.hasEndTag() ? "NORMAL" : "VOID"),
        s(element.name())
      );
    }
  }

}
