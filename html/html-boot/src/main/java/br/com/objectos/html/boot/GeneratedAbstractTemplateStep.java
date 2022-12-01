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

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.html.boot.spec.AttributeKind;
import br.com.objectos.html.boot.spec.AttributeSpec;
import br.com.objectos.html.boot.spec.ElementSpec;

final class GeneratedAbstractTemplateStep extends ThisTemplate {

  private AttributeSpec attribute;

  @Override
  protected final void definition() {
    _package("br.com.objectos.html.tmpl");

    autoImports();

    _class(
      annotation(t(Generated.class), s(HtmlBoot.class.getCanonicalName())),
      _abstract(), id("GeneratedAbstractTemplate"),

      include(this::elementMethods),

      include(this::attrMethods),

      method(
        _abstract(), tparam("N", t(attr, "StandardAttributeName")),
        tvar("N"), id("addStandardAttribute"),
        param(tvar("N"), id("name"))
      ),

      method(
        _abstract(), tparam("N", t(attr, "StandardAttributeName")),
        tvar("N"), id("addStandardAttribute"),
        param(tvar("N"), id("name")),
        param(t(String.class), id("value"))
      ),

      method(
        _abstract(), t(elem, "ElementName"), id("addStandardElement"),
        param(t(elem, "StandardElementName"), id("name")),
        param(t(String.class), id("text"))
      ),

      method(
        _abstract(), t(elem, "ElementName"), id("addStandardElement"),
        param(t(elem, "StandardElementName"), id("name")),
        param(t(t(spi_type, "Value"), dim()), id("values"))
      )
    );
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
    method(
      _public(), _final(), t(t(attr, "StandardAttributeName"), attribute.classSimpleName), id(name),

      include(this::attrMethodsParam),

      _return(
        invoke("addStandardAttribute", include(this::attrMethodsInvokeArgs))
      )
    );
  }

  private void attrMethodsInvokeArgs() {
    n(t(attr, "StandardAttributeName"), attribute.constantName);

    AttributeKind kind = attribute.kind();

    if (kind.isString()) {
      n("value");
    }
  }

  private void attrMethodsParam() {
    AttributeKind kind = attribute.kind();

    if (kind.isString()) {
      param(t(String.class), id("value"));
    }
  }

  private void elementMethods() {
    for (var element : spec.elements()) {
      elementMethods(element);
    }
  }

  private void elementMethods(ElementSpec el) {
    method(
      _public(), _final(), t(elem, "ElementName"), id(el.methodName()),
      param(t(spi_type, el.valueSimpleName()), ellipsis(), id("values")),
      _return(
        invoke(
          "addStandardElement",
          n(t(elem, "StandardElementName"), el.constantName), n("values")
        )
      )
    );

    var template = spec.template();

    if (template.shouldIncludeText(el)) {
      method(
        _public(), _final(), t(elem, "ElementName"), id(el.methodName()),
        param(t(String.class), id("text")),
        _return(
          invoke(
            "addStandardElement",
            n(t(elem, "StandardElementName"), el.constantName), n("text")
          )
        )
      );
    }
  }

}
