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

final class NonVoidElementValueStep extends ThisTemplate {

  @Override
  protected final void definition() {
    _package(spi_type);

    autoImports();

    _interface(
      annotation(t(Generated.class), s(HtmlBoot.class.getCanonicalName())),
      _public(), id("NonVoidElementValue"),
      include(this::extendsClause)
    );
  }

  private void extendsClause() {
    for (var element : spec.elements()) {
      if (element.hasEndTag()) {
        _extends(t(spi_type, element.valueSimpleName()));
      }
    }
  }

}
