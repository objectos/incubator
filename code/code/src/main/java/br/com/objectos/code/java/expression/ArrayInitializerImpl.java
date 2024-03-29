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
package br.com.objectos.code.java.expression;

import br.com.objectos.code.java.element.AbstractImmutableCodeElement;
import br.com.objectos.code.java.element.CodeElement;
import br.com.objectos.code.java.statement.VariableInitializer;

final class ArrayInitializerImpl extends AbstractImmutableCodeElement implements ArrayInitializer {

  static final ArrayInitializer EMPTY = new ArrayInitializerImpl(
      openBrace(), closeBrace()
  );

  private ArrayInitializerImpl(CodeElement... elements) {
    super(elements);
  }
  
  static ArrayInitializer a0(VariableInitializer... elements) {
    return new ArrayInitializerImpl(
        openBrace(), commaSeparated(elements), closeBrace()
    );
  }

  static ArrayInitializer a0(Iterable<? extends VariableInitializer> elements) {
    return new ArrayInitializerImpl(
        openBrace(), commaSeparated(elements), closeBrace()
    );
  }

}
