/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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
package br.com.objectos.code.java.declaration;

import br.com.objectos.code.java.element.CodeElement;
import br.com.objectos.code.java.io.CodeWriter;

class SimpleNameCodeElement implements CodeElement {

  private final String value;

  SimpleNameCodeElement(String value) {
    this.value = value;
  }

  @Override
  public final CodeWriter acceptCodeWriter(CodeWriter w) {
    w.pushSimpleName(value);
    w.writePreIndentation();
    w.write(value);
    return w;
  }

}
