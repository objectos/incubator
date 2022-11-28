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
import objectos.code.ClassName;

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
        _abstract(), tparam("N", t(attr_StandardAttributeName)),
        tvar("N"), id("addStandardAttribute"),
        param(tvar("N"), id("name"))
      ),

      method(
        _abstract(), tparam("N", t(attr_StandardAttributeName)),
        tvar("N"), id("addStandardAttribute"),
        param(tvar("N"), id("name")),
        param(t(jlang_String), id("value"))
      ),

      method(
        _abstract(), t(element_ElementName), id("addStandardElement"),
        param(t(element_StandardElementName), id("name")),
        param(t(jlang_String), id("text"))
      ),

      method(
        _abstract(), t(element_ElementName), id("addStandardElement"),
        param(t(element_StandardElementName), id("name")),
        param(t(t(spi_type_Value), dim()), id("values"))
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
    var type = ClassName.of(attr_StandardAttributeName, attribute.classSimpleName);

    method(
      _public(), _final(), t(type), id(name),

      include(this::attrMethodsParam),

      _return(
        invoke("addStandardAttribute", include(this::attrMethodsInvokeArgs))
      )
    );
  }

  private void attrMethodsInvokeArgs() {
    n(attr_StandardAttributeName, attribute.constantName);

    AttributeKind kind = attribute.kind();

    if (kind.isString()) {
      n("value");
    }
  }

  private void attrMethodsParam() {
    AttributeKind kind = attribute.kind();

    if (kind.isString()) {
      param(t(jlang_String), id("value"));
    }
  }

  private void elementMethods() {
    for (var element : spec.elements()) {
      elementMethods(element);
    }
  }

  private void elementMethods(ElementSpec element) {
    var valueType = ClassName.of(spi_type, element.valueSimpleName());

    method(
      _public(), _final(), t(element_ElementName), id(element.methodName()),
      param(t(valueType), ellipsis(), id("values")),
      _return(
        invoke(
          "addStandardElement",
          n(element_StandardElementName, element.constantName), n("values")
        )
      )
    );

    var template = spec.template();

    if (template.shouldIncludeText(element)) {
      method(
        _public(), _final(), t(element_ElementName), id(element.methodName()),
        param(t(jlang_String), id("text")),
        _return(
          invoke(
            "addStandardElement",
            n(element_StandardElementName, element.constantName), n("text")
          )
        )
      );
    }
  }

}
