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
package br.com.objectos.parser.spec;

import java.util.Iterator;
import objectos.util.GrowableList;

public enum CollectionKind {

  LIST {
    @Override
    final Object get(Iterator<?> iterator) {
      GrowableList<Object> builder = new GrowableList<>();
      while (iterator.hasNext()) {
        Object value = iterator.next();
        if (value instanceof Iterable<?>) {
          @SuppressWarnings("unchecked")
          Iterable<Object> cast = (Iterable<Object>) value;
          builder.addAllIterable(cast);
        } else {
          builder.add(value);
        }
      }
      return builder;
    }

    @Override
    final ValueList valueList() {
      return new IterableValueList();
    }
  };

  abstract Object get(Iterator<?> iterator);

  abstract ValueList valueList();

}