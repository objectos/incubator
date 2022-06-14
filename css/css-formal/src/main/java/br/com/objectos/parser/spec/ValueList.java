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
import java.util.List;
import objectos.util.MutableList;

class ValueList {

  final MutableList<Object> list = new MutableList<>();

  ValueList() {}

  public void add(Object value) {
    list.add(value);
  }

  public final List<Object> toImmutableList() {
    return list.toImmutableList();
  }

  final Iterator<Object> build() {
    return list.iterator();
  }

  final Iterator<Object> iterator() {
    return list.iterator();
  }

  final int size() {
    return list.size();
  }

}