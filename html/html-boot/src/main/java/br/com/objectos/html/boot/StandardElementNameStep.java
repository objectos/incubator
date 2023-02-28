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

import objectos.code.ArrayTypeName;

final class StandardElementNameStep extends ThisTemplate {

  @Override
  protected final void definition() {
    packageDeclaration(elem);

    autoImports();

    enumDeclaration(this::standardElementName);
  }

  private void standardElementName() {
    modifiers(PUBLIC);
    name(STD_ELEMENT_NAME);
    implementsClause(ELEMENT_NAME);

    for (var element : spec.elements()) {
      enumConstant(
        element.constantName,
        arg(ELEMENT_KIND, n(element.hasEndTag() ? "NORMAL" : "VOID")),
        arg(s(element.name()))
      );
    }

    var arrayType = ArrayTypeName.of(STD_ELEMENT_NAME);

    field(
      PRIVATE, STATIC, FINAL, arrayType, name("ARRAY"),
      STD_ELEMENT_NAME, v("values")
    );

    field(
      PRIVATE, FINAL, ELEMENT_KIND, name("kind")
    );

    field(
      PRIVATE, FINAL, STRING, name("name")
    );

    constructor(
      PRIVATE,
      parameter(ELEMENT_KIND, "kind"),
      parameter(STRING, "name"),

      p(THIS, n("kind"), IS, n("kind")),
      p(THIS, n("name"), IS, n("name"))
    );

    method(
      PUBLIC, STATIC, STD_ELEMENT_NAME, name("getByCode"),
      parameter(INT, "code"),
      p(RETURN, n("ARRAY"), dim(n("code")))
    );

    method(
      PUBLIC, STATIC, INT, name("size"),
      p(RETURN, n("ARRAY"), n("length"))
    );

    method(
      annotation(OVERRIDE),
      PUBLIC, FINAL, INT, name("getCode"),
      p(RETURN, v("ordinal"))
    );

    method(
      annotation(OVERRIDE),
      PUBLIC, FINAL, ELEMENT_KIND, name("getKind"),
      p(RETURN, n("kind"))
    );

    method(
      annotation(OVERRIDE),
      PUBLIC, FINAL, STRING, name("getName"),
      p(RETURN, n("name"))
    );

    method(
      annotation(OVERRIDE),
      PUBLIC, FINAL, VOID, name("mark"),
      parameter(MARKER, "marker"),
      p(n("marker"), v("markElement"))
    );

    method(
      annotation(OVERRIDE),
      PUBLIC, FINAL, VOID, name("render"),
      parameter(RENDERER, "renderer")
    );
  }

}
