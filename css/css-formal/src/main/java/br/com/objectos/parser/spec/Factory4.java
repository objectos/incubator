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

import br.com.objectos.parser.grammar.Constructor4;
import java.util.Iterator;

class Factory4<E, A1, A2, A3, A4> implements Factory {

  private final Constructor4<E, A1, A2, A3, A4> constructor;

  public Factory4(Constructor4<E, A1, A2, A3, A4> constructor) {
    this.constructor = constructor;
  }

  @SuppressWarnings("unchecked")
  @Override
  public final Object get(Iterator<?> iterator) {
    A1 v1 = (A1) iterator.next();
    A2 v2 = (A2) iterator.next();
    A3 v3 = (A3) iterator.next();
    A4 v4 = (A4) iterator.next();
    return constructor.apply(v1, v2, v3, v4);
  }

}