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
package br.com.objectos.parser.spec;

import br.com.objectos.parser.grammar.Constructor7;
import java.util.Iterator;

class Factory7<E, A1, A2, A3, A4, A5, A6, A7> implements Factory {

  private final Constructor7<E, A1, A2, A3, A4, A5, A6, A7> constructor;

  public Factory7(Constructor7<E, A1, A2, A3, A4, A5, A6, A7> constructor) {
    this.constructor = constructor;
  }

  @SuppressWarnings("unchecked")
  @Override
  public final Object get(Iterator<?> iterator) {
    A1 v1 = (A1) iterator.next();
    A2 v2 = (A2) iterator.next();
    A3 v3 = (A3) iterator.next();
    A4 v4 = (A4) iterator.next();
    A5 v5 = (A5) iterator.next();
    A6 v6 = (A6) iterator.next();
    A7 v7 = (A7) iterator.next();
    return constructor.apply(v1, v2, v3, v4, v5, v6, v7);
  }

}