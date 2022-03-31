/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.select;

import br.com.objectos.core.list.MutableList;

public class SelectorListBuilder {

  private final MutableList<Selector> list = MutableList.create();

  public SelectorListBuilder() {}

  public final void add(Selector selector) {
    list.add(selector);
  }

  public final void addAll(Iterable<Selector> iterable) {
    list.addAllIterable(iterable);
  }

  public final SelectorList build() {
    return new SelectorList(list.toImmutableList());
  }

}