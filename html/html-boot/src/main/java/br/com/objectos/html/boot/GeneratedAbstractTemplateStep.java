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

final class GeneratedAbstractTemplateStep extends ThisTemplate {

  private AttributeSpec attribute;

  @Override
  protected final void definition() {
    // @formatter:off
    _package("br.com.objectos.html.tmpl");

    autoImports();

    _abstract(); _class("GeneratedAbstractTemplate"); body(
      include(this::elementMethods),

      include(this::attrMethods),

      _abstract(), tparam("N", t(attr, "StandardAttributeName")), tvar("N"),
      method(
        "addStandardAttribute",
        tvar("N"), id("name")
      ),

      _abstract(), tparam("N", t(attr, "StandardAttributeName")), tvar("N"),
      method(
        "addStandardAttribute",
        tvar("N"), id("name"),
        t(String.class), id("value")
      ),

      _abstract(), t(elem, "ElementName"),
      method(
        "addStandardElement",
        t(elem, "StandardElementName"), id("name"),
        t(String.class), id("text")
      ),

      _abstract(), t(elem, "ElementName"),
      method(
        "addStandardElement",
        t(elem, "StandardElementName"), id("name"),
        t(t(spi_type, "Value"), dim()), id("values")
      )
    );
    // @formatter:on
  }

  private void attrMethods() {
    var template = spec.template();

    for (var attribute : spec.attributes()) {
      for (String name : attribute.methodNames()) {
        this.attribute = attribute;

        if (template.shouldIncludeAttribute(name)) {
          attrMethods(name);
        }
      }
    }
  }

  private void attrMethods(String name) {
    // @formatter:off
    _public(); _final(); t(attr, "StandardAttributeName", attribute.classSimpleName);
    method(name, include(this::attrMethodsParam)); block(
      _return(), invoke("addStandardAttribute", include(this::attrMethodsInvokeArgs))
    );
    // @formatter:on
  }

  private void attrMethodsInvokeArgs() {
    code(t(attr, "StandardAttributeName"), n(attribute.constantName), end());

    AttributeKind kind = attribute.kind();

    if (kind.isString()) {
      n("value");
    }
  }

  private void attrMethodsParam() {
    AttributeKind kind = attribute.kind();

    if (kind.isString()) {
      code(t(String.class), id("value"));
    }
  }

  private void elementMethods() {
    for (var element : spec.elements()) {
      elementMethods(element);
    }
  }

  private void elementMethods(ElementSpec el) {
    // @formatter:off
    _public(); _final(); t(elem, "ElementName");
    method(el.methodName(), t(spi_type, el.valueSimpleName()), ellipsis(), id("values"));
    block(
      _return(),
      invoke(
        "addStandardElement",
        t(elem, "StandardElementName"), n(el.constantName), end(), n("values")
      )
    );
    // @formatter:on

    var template = spec.template();

    if (template.shouldIncludeText(el)) {
      // @formatter:off
      _public(); _final(); t(elem, "ElementName");
      method(el.methodName(), t(String.class), id("text")); block(
        _return(),
        invoke(
          "addStandardElement",
          t(elem, "StandardElementName"), n(el.constantName), end(), n("text")
        )
      );
      // @formatter:on
    }
  }

}
