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
import objectos.code.ParameterizedTypeName;
import objectos.code.tmpl.IncludeTarget;

final class StandardAttributeNameStep extends ThisTemplate {

  private static final ParameterizedTypeName MAP = ParameterizedTypeName.of(
    UNMODIFIABLE_MAP, STRING, STD_ATTR_NAME
  );

  private int counter;

  private AttributeSpec currentAttribute;

  @Override
  protected final void definition() {
    packageDeclaration(attr);

    autoImports();

    classDeclaration(this::standardAttributeName);
  }

  private void standardAttributeName() {
    modifiers(PUBLIC, ABSTRACT);
    name(STD_ATTR_NAME);
    implementsClause(ATTRIBUTE_NAME, VALUE);

    for (var attribute : spec.attributes()) {
      field(
        PUBLIC, STATIC, FINAL, attribute.className, name(attribute.constantName),
        NEW, attribute.className
      );
    }

    field((IncludeTarget) this::standardAttributeNameArray);

    field(
      PRIVATE, STATIC, FINAL, MAP, name("MAP"), v("mapInit")
    );

    field(
      PRIVATE, FINAL, INT, name("code")
    );

    field(
      PRIVATE, FINAL, ATTRIBUTE_KIND, name("kind")
    );

    field(
      PRIVATE, FINAL, STRING, name("name")
    );

    constructor(
      parameter(INT, "code"),
      parameter(ATTRIBUTE_KIND, "kind"),
      parameter(STRING, "name"),

      p(THIS, n("code"), IS, n("code")),
      p(THIS, n("kind"), IS, n("kind")),
      p(THIS, n("name"), IS, n("name"))
    );

    method(
      PUBLIC, STATIC, STD_ATTR_NAME, name("getByCode"),
      parameter(INT, "code"),
      p(RETURN, n("ARRAY"), dim(n("code")))
    );

    method(
      PUBLIC, STATIC, STD_ATTR_NAME, name("getByName"),
      parameter(STRING, "name"),
      p(RETURN, n("MAP"), v("get"), arg(n("name")))
    );

    method(
      PUBLIC, STATIC, INT, name("size"),
      p(RETURN, n("ARRAY"), n("length"))
    );

    method(this::standardAttributeNameMapInit);

    method(
      annotation(OVERRIDE),
      PUBLIC, FINAL, INT, name("getCode"),
      p(RETURN, n("code"))
    );

    method(
      annotation(OVERRIDE),
      PUBLIC, FINAL, ATTRIBUTE_KIND, name("getKind"),
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
      p(n("marker"), v("markAttribute"))
    );

    method(
      annotation(OVERRIDE),
      PUBLIC, FINAL, VOID, name("render"),
      parameter(RENDERER, "renderer")
    );

    for (var attribute : spec.attributes()) {
      currentAttribute = attribute;

      classDeclaration(this::standardAttributeNameType);
    }
  }

  private void standardAttributeNameArray() {
    modifiers(PRIVATE, STATIC, FINAL);

    var arrayType = ArrayTypeName.of(STD_ATTR_NAME);

    consume(arrayType);

    name("ARRAY");

    arrayInitializer();

    consume(NL);

    var attributes = spec.attributes();

    for (var attribute : attributes) {
      value(n(attribute.constantName()));

      consume(NL);
    }
  }

  private void standardAttributeNameMapInit() {
    modifiers(PRIVATE, STATIC);
    returnType(MAP);
    name("mapInit");
    p(VAR, name("builder"), NEW, NAMES_BUILDER);
    for (var attribute : spec.attributes()) {
      p(n("builder"), v("put"), arg(s(attribute.name())), arg(n(attribute.constantName)));
    }
    p(RETURN, n("builder"), v("build"));
  }

  private void standardAttributeNameType() {
    modifiers(PUBLIC, STATIC);

    name(currentAttribute.className);

    extendsClause(STD_ATTR_NAME);

    if (currentAttribute.global()) {
      implementsClause(GLB_ATTR_NAME);
    } else {
      for (var ifaceName : currentAttribute.interfaces()) {
        implementsClause(ifaceName);
      }
    }

    var kind = currentAttribute.kind();

    constructor(
      PRIVATE,
      p(
        SUPER,
        arg(i(counter++)),
        arg(ATTRIBUTE_KIND, n(kind.name())),
        arg(s(currentAttribute.name()))
      )
    );
  }

}
