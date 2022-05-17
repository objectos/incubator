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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import objectos.lang.ToString;

public class ProductionQuery
    implements Iterable<Production>, Iterator<ProductionQuery> {

  private static final ProductionQuery EMPTY = new ProductionQuery(Collections.emptyList());

  private final int index;

  private final List<Production> list;

  public ProductionQuery(List<Production> list) {
    this(list, 0);
  }

  ProductionQuery(List<Production> list, int index) {
    this.list = list;
    this.index = index;
  }

  static ProductionQuery empty() {
    return EMPTY;
  }

  public final Optional<Production> findFirst() {
    return list.stream().findFirst();
  }

  public final Production get() {
    return list.get(index);
  }

  public final Production get(int index) {
    return list.get(index);
  }

  public final boolean has(int index) {
    return index < list.size();
  }

  @Override
  public final boolean hasNext() {
    return index + 1 < list.size();
  }

  @Override
  public final Iterator<Production> iterator() {
    return list.subList(index, list.size()).iterator();
  }

  @Override
  public final ProductionQuery next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return new ProductionQuery(list, index + 1);
  }

  @Override
  public final String toString() {
    return ToString.toString(
        this,
        "index", index,
        "list", list
    );
  }

}