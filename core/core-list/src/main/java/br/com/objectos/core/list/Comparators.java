/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package br.com.objectos.core.list;

import java.util.Comparator;

final class Comparators {

  private Comparators() {}

  @SuppressWarnings("unchecked")
  public static <T> Comparator<T> naturalOrder() {
    return (Comparator<T>) NaturalOrder.INSTANCE;
  }

  private static class NaturalOrder implements Comparator<Comparable<Object>> {

    static final NaturalOrder INSTANCE = new NaturalOrder();

    @Override
    public final int compare(Comparable<Object> o1, Comparable<Object> o2) {
      return o1.compareTo(o2);
    }

  }

}
