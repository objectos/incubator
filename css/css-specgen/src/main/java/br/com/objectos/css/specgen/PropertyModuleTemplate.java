/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.css.specgen;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.code.java.JavaNames;
import java.util.List;
import java.util.Set;
import javax.lang.model.SourceVersion;
import objectos.code.JavaTemplate;

class PropertyModuleTemplate extends JavaTemplate {

  private static final String BOOT = "br.com.objectos.css.boot";
  private static final String SPEC = "br.com.objectos.css.boot.spec";

  private final String globalSig = "globalSig";

  private List<Property> group;

  private Property property;

  @Override
  protected final void definition() {
    // @formatter:off
    _package(BOOT);

    autoImports();

    at(t(Generated.class), s(SpecgenBoot.class.getCanonicalName()));
    _final(); _class(simpleName()); _extends(); t(BOOT, "AbstractPropertyModule"); body(
      at(t(Override.class)),
      _final(), _void(), method("propertyDefinition"), block(
        include(this::def0keywords),

        include(this::def1MainProperty),

        include(this::def2GroupProperties)
      )
    );
    // @formatter:on
  }

  final void set(Property property, List<Property> group) {
    this.property = property;

    this.group = group;
  }

  private void def0keywords() {
    for (var keyword : keywords()) {
      var id = JavaNames.toValidMethodName(keyword);

      if (SourceVersion.isKeyword(id)) {
        id = id + "Kw";
      }

      // @formatter:off
      _var(); id(id); invoke("keyword", s(keyword)); end();
      // @formatter:on
    }
  }

  private void def1MainProperty() {
    invoke(
      "property", nl(),

      s(property.name()), end(), nl(),

      nl(),

      invoke(
        "formal",
        include(() -> formalArgs(property))
      ), end(), nl(),

      nl(),

      n(globalSig), nl()
    );

    end();
  }

  private void def2GroupProperties() {
    if (group.isEmpty()) {
      return;
    }

    var first = group.get(0);

    invoke(
      "property", nl(),

      invoke("names", include(this::namesArgs)), end(), nl(),

      nl(),

      invoke(
        "formal",
        include(() -> formalArgs(first))
      ), end(), nl(),

      nl(),

      n(globalSig), nl()
    );

    end();
  }

  private void formalArgs(Property property) {
    nl();

    // @formatter:off
    t(SPEC, "Source"); n("MDN");
    // @formatter:on
    nl();

    s(property.formal());
    nl();

    var valueTypes = property.valueTypes();

    if (!valueTypes.isEmpty()) {
      nl();

      var valueType = valueTypes.get(0);

      s(valueType.join());

      nl();

      for (int i = 1, size = valueTypes.size(); i < size; i++) {
        valueType = valueTypes.get(0);

        s(valueType.join());

        nl();
      }
    }
  }

  private Set<String> keywords() {
    var builder = KeywordSet.builder();

    property.acceptKeywordSetBuilder(builder);

    for (var groupProperty : group) {
      groupProperty.acceptKeywordSetBuilder(builder);
    }

    var keywordSet = builder.build();

    return keywordSet.values;
  }

  private void namesArgs() {
    for (int i = 0, size = group.size(); i < size; i++) {
      var prop = group.get(i);

      s(prop.name());
    }
  }

  private String simpleName() {
    return JavaNames.toValidClassName(
      property.name() + "PropertyModule"
    );
  }

}