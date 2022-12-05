/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.boot.spec;

import br.com.objectos.css.boot.type.ValueType;

final class ValueTypeIfaceStep extends ThisTemplate {

  ValueType valueType;

  @Override
  protected final void definition() {
    _package(type);

    autoImports();

    _interface(
      generatedAnnotation(),
      _public(), id(valueType.simpleName), include(this::interfaces)
    );
  }

  private void interfaces() {
    for (var name : valueType.interfaceNames()) {
      _extends(t(type, name));
    }
  }

}