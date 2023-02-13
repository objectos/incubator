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
package br.com.objectos.html.boot;

final class StandardElementNameStep extends ThisTemplate {

  @Override
  protected final void definition() {
    // @formatter:off
    _package(elem);

    autoImports();

    _public(); _enum("StandardElementName"); _implements(); t(elem, "ElementName"); body(
      include(this::enumConstants),

      _private(), _static(), _final(), t(t(elem, "StandardElementName"), dim()),
      id("ARRAY"), t(elem, "StandardElementName"), invoke("values"),

      _private(), _final(), t(elem, "ElementKind"), id("kind"),

      _private(), _final(), t(String.class), id("name"),

      _private(), constructor(
        t(elem, "ElementKind"), id("kind"),
        t(String.class), id("name")
      ), block(
        _this(), n("kind"), gets(), n("kind"),
        _this(), n("name"), gets(), n("name")
      ),

      _public(), _static(), t(elem, "StandardElementName"),
      method("getByCode", _int(), id("code")), block(
        _return(), n("ARRAY"), dim(n("code"))
      ),

      _public(), _static(), _int(), method("size"), block(
        _return(), n("ARRAY"), n("length")
      ),

      at(t(Override.class)),
      _public(), _final(), _int(), method("getCode"), block(
        _return(), invoke("ordinal")
      ),

      at(t(Override.class)),
      _public(), _final(), t(elem, "ElementKind"), method("getKind"), block(
        _return(), n("kind")
      ),

      at(t(Override.class)),
      _public(), _final(), t(String.class), method("getName"), block(
        _return(), n("name")
      ),

      at(t(Override.class)),
      _public(), _final(), _void(),
      method("mark", t(spi_tmpl, "Marker"), id("marker")), block(
        n("marker"), invoke("markElement")
      ),

      at(t(Override.class)),
      _public(), _final(), _void(),
      method("render", t(spi_tmpl, "Renderer"), id("renderer")), block()
    );
  }

  private void enumConstants() {
    for (var element : spec.elements()) {
      enumConstant(
        element.constantName,
        t(elem, "ElementKind"), n(element.hasEndTag() ? "NORMAL" : "VOID"), end(),
        s(element.name())
      );
    }
  }

}
