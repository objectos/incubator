/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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

import br.com.objectos.lexer.lang.Constructor1;
import br.com.objectos.lexer.lang.Constructor2;
import br.com.objectos.lexer.lang.Constructor3;
import java.util.Iterator;

abstract class CreateWith {

  static <E, A1> CreateWith of(Constructor1<E, A1> constructor) {
    return new CreateWith1<>(constructor);
  }

  static <E, A1, A2> CreateWith of(Constructor2<E, A1, A2> constructor) {
    return new CreateWith2<>(constructor);
  }

  static <E, A1, A2, A3> CreateWith of(Constructor3<E, A1, A2, A3> constructor) {
    return new CreateWith3<>(constructor);
  }

  public final Object get(ValueList list) {
    return get(list.iterator());
  }
  
  abstract int arity();

  abstract Object get(Iterator<?> iterator);

}