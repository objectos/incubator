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

import br.com.objectos.parser.grammar.Constructor1;
import java.util.Iterator;

class Factory1<E, A1> implements Factory {

  private final Constructor1<E, A1> constructor;

  public Factory1(Constructor1<E, A1> constructor) {
    this.constructor = constructor;
  }

  @SuppressWarnings("unchecked")
  @Override
  public final Object get(Iterator<?> iterator) {
    A1 v1 = (A1) iterator.next();
    return constructor.apply(v1);
  }

}