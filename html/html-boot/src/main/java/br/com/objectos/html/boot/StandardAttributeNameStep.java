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

import objectos.util.UnmodifiableMap;

final class StandardAttributeNameStep extends ThisTemplate {

  @Override
  protected final void definition() {
    // @formatter:off
    _package(attr);

    autoImports();

    _public(); _abstract(); _class("StandardAttributeName"); _implements();
    t(attr, "AttributeName"); t(spi_type, "Value"); body(
      include(this::constants),

      _private(), _static(), _final(), t(t(attr, "StandardAttributeName"), dim()),
      id("ARRAY"), ainit(include(this::constantNames)),

      _private(), _static(), _final(),
      t(t(UnmodifiableMap.class), t(String.class), t(attr, "StandardAttributeName")),
      id("MAP"), invoke("mapInit"),

      _private(), _final(), _int(), id("code"),

      _private(), _final(), t(attr, "AttributeKind"), id("kind"),

      _private(), _final(), t(String.class), id("name"),

      constructor(
        _int(), id("code"),
        t(attr, "AttributeKind"), id("kind"),
        t(String.class), id("name")
      ), block(
        _this(), n("code"), gets(), n("code"),
        _this(), n("kind"), gets(), n("kind"),
        _this(), n("name"), gets(), n("name")
      ),

      _public(), _static(), t(attr, "StandardAttributeName"),
      method("getByCode", _int(), id("code")), block(
        _return(), n("ARRAY"),dim(n("code"))
      ),

      _public(), _static(), t(attr, "StandardAttributeName"),
      method("getByName", t(String.class), id("name")), block(
        _return(), n("MAP"), invoke("get", n("name"))
      ),

      _public(), _static(), _int(), method("size"), block(
        _return(), n("ARRAY"), n("length")
      ),

      _private(), _static(),
      t(t(UnmodifiableMap.class), t(String.class), t(attr, "StandardAttributeName")),
      method("mapInit"), block(
        _var(), id("builder"), _new(t(attr, "NamesBuilder")), end(),
        include(this::mapInit),
        _return(), n("builder"), invoke("build")
      ),

      at(t(Override.class)),
      _public(), _final(), _int(),  method("getCode"), block(
        _return(), n("code")
      ),

      at(t(Override.class)),
      _public(), _final(), t(attr, "AttributeKind"),  method("getKind"), block(
        _return(), n("kind")
      ),

      at(t(Override.class)),
      _public(), _final(), t(String.class), method("getName"), block(
        _return(), n("name")
      ),

      at(t(Override.class)),
      _public(), _final(), _void(), method("mark", t(spi_tmpl, "Marker"), id("marker")), block(
        n("marker"), invoke("markAttribute")
      ),

      at(t(Override.class)),
      _public(), _final(), _void(),
      method("render", t(spi_tmpl, "Renderer"), id("renderer")), block(),

      include(this::nameTypes)
    );
    // @formatter:on
  }

  private void constantNames() {
    nl();

    var attributes = spec.attributes();

    for (var attribute : attributes) {
      code(n(attribute.constantName()), end(), nl());
    }
  }

  private void constants() {
    for (var attribute : spec.attributes()) {
      var simpleName = attribute.classSimpleName;

      // @formatter:off
      _public(); _static(); _final(); t(attr, "StandardAttributeName", simpleName);
      id(attribute.constantName); _new(t(attr, "StandardAttributeName", simpleName));
      // @formatter:off
    }
  }

  private void mapInit() {
    for (var attribute : spec.attributes()) {
      // @formatter:off
      n("builder"); invoke("put", s(attribute.name()), end(), n(attribute.constantName)); end();
      // @formatter:on
    }
  }

  private void nameTypes() {
    int counter = 0;

    for (var attribute : spec.attributes()) {
      var kind = attribute.kind();

      // @formatter:off
      _public(); _static(); _class(attribute.classSimpleName);
      _extends(); t(attr, "StandardAttributeName");
      _implements(); nameTypesImplements(attribute); body(
        _private(), constructor(), block(
          _super(
            i(counter++),
            t(attr, "AttributeKind"), n(kind.name()),
            s(attribute.name())
          )
        )
      );
      // @formatter:on
    }
  }

  private void nameTypesImplements(AttributeSpec attribute) {
    if (attribute.global()) {
      t(attr, "GlobalAttributeName");
    } else {
      var names = attribute.interfaceSet();

      for (var sn : names) {
        t(spi_type, sn);
      }
    }
  }

}
