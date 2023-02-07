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
package br.com.objectos.css.boot.spec;

import br.com.objectos.css.boot.type.PrimitiveType;

final class PrimitiveTypeStep extends ThisTemplate {

  PrimitiveType primitiveType;

  @Override
  protected final void definition() {
    _package(type);

    autoImports();

    generatedAnnotation();
    _public();
    _interface(primitiveType.typeSimpleName());
    _extends();
    superInterfaces();
    body();
  }

  private void superInterfaces() {
    for (var name : primitiveType.interfaceNames()) {
      t(type, name);
    }
  }

}