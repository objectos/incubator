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

import br.com.objectos.html.boot.spec.AttributeSpec;
import objectos.code.JavaModel.ClassDeclarationElement;
import objectos.code.JavaModel.ClassType;
import objectos.code.JavaModel.ExpressionName;
import objectos.util.UnmodifiableMap;

final class StandardAttributeNameStep extends ThisTemplate {

  @Override
  protected final void definition() {
    _package(attr);

    autoImports();

    _class(
      generatedAnnotation(),
      _public(), _abstract(), id("StandardAttributeName"),
      _implements(t(attr, "AttributeName"), t(spi_type, "Value")),

      include(this::constants),

      field(
        _private(), _static(), _final(), t(t(attr, "StandardAttributeName"), dim()),
        id("ARRAY"), ainit(constantNames())
      ),

      field(
        _private(), _static(), _final(),
        t(t(UnmodifiableMap.class), t(String.class), t(attr, "StandardAttributeName")),
        id("MAP"), invoke("mapInit")
      ),

      field(_private(), _final(), _int(), id("code")),

      field(_private(), _final(), t(attr, "AttributeKind"), id("kind")),

      field(_private(), _final(), t(String.class), id("name")),

      constructor(
        param(_int(), id("code")),
        param(t(attr, "AttributeKind"), id("kind")),
        param(t(String.class), id("name")),
        assign(n(_this(), "code"), n("code")),
        assign(n(_this(), "kind"), n("kind")),
        assign(n(_this(), "name"), n("name"))
      ),

      method(
        _public(), _static(), t(attr, "StandardAttributeName"), id("getByCode"),
        param(_int(), id("code")),
        _return(aget(n("ARRAY"), n("code")))
      ),

      method(
        _public(), _static(), t(attr, "StandardAttributeName"), id("getByName"),
        param(t(String.class), id("name")),
        _return(invoke(n("MAP"), "get", n("name")))
      ),

      method(
        _public(), _static(), _int(), id("size"),
        _return(n("ARRAY", "length"))
      ),

      method(
        _private(), _static(),
        t(t(UnmodifiableMap.class), t(String.class), t(attr, "StandardAttributeName")),
        id("mapInit"),
        var("builder", _new(t(attr, "NamesBuilder"))),
        include(this::mapInit),
        _return(invoke(n("builder"), "build"))
      ),

      method(
        annotation(t(Override.class)),
        _public(), _final(), _int(), id("getCode"),
        _return(n("code"))
      ),

      method(
        annotation(t(Override.class)),
        _public(), _final(), t(attr, "AttributeKind"), id("getKind"),
        _return(n("kind"))
      ),

      method(
        annotation(t(Override.class)),
        _public(), _final(), t(String.class), id("getName"),
        _return(n("name"))
      ),

      method(
        annotation(t(Override.class)),
        _public(), _final(), _void(), id("mark"),
        param(t(spi_tmpl, "Marker"), id("marker")),
        invoke(n("marker"), "markAttribute")
      ),

      method(
        annotation(t(Override.class)),
        _public(), _final(), _void(), id("render"),
        param(t(spi_tmpl, "Renderer"), id("renderer"))
      ),

      include(this::nameTypes)
    );
  }

  private ExpressionName[] constantNames() {
    return spec.attributes().stream()
        .map(AttributeSpec::constantName)
        .map(this::n)
        .toArray(ExpressionName[]::new);
  }

  private void constants() {
    for (var attribute : spec.attributes()) {
      var simpleName = attribute.classSimpleName;

      field(
        _public(), _static(), _final(), t(attr, "StandardAttributeName", simpleName),
        id(attribute.constantName), _new(t(attr, "StandardAttributeName", simpleName))
      );
    }
  }

  private void mapInit() {
    for (var attribute : spec.attributes()) {
      invoke(n("builder"), "put", s(attribute.name()), n(attribute.constantName));
    }
  }

  private void nameTypes() {
    int counter = 0;

    for (var attribute : spec.attributes()) {
      var kind = attribute.kind();

      _class(
        _public(), _static(), id(attribute.classSimpleName),
        _extends(t(attr, "StandardAttributeName")),
        nameTypesImplements(attribute),
        constructor(
          _private(),
          _super(
            i(counter++),
            n(t(attr, "AttributeKind"), kind.name()),
            s(attribute.name())
          )
        )
      );
    }
  }

  private ClassDeclarationElement nameTypesImplements(AttributeSpec attribute) {
    if (attribute.global()) {
      return _implements(t(attr, "GlobalAttributeName"));
    } else {
      return _implements(
        attribute.interfaceSet()
            .stream()
            .map(sn -> t(spi_type, sn))
            .toArray(ClassType[]::new)
      );
    }
  }

}
