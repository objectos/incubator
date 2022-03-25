/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.lexer.impl.ah;

import br.com.objectos.lexer.lang.Constructor2;
import java.util.Iterator;
import java.util.Objects;

class CreateWith2<E, A1, A2> extends CreateWith {

  private final Constructor2<E, A1, A2> constructor;

  CreateWith2(Constructor2<E, A1, A2> constructor) {
    this.constructor = Objects.requireNonNull(constructor);
  }

  @Override
  final int arity() {
    return 2;
  }

  @SuppressWarnings("unchecked")
  @Override
  final Object get(Iterator<?> iterator) {
    A1 v1 = (A1) iterator.next();
    A2 v2 = (A2) iterator.next();
    return constructor.apply(v1, v2);
  }

}
