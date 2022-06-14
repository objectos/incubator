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
package br.com.objectos.css.keyword;

import objectos.util.ImmutableMap;
import objectos.util.MutableMap;

class ImmutableMapBuilder {

  private final MutableMap<String, StandardKeyword> map = new MutableMap<>();

  public final ImmutableMap<String, StandardKeyword> build() {
    return map.toImmutableMap();
  }

  final ImmutableMapBuilder put(String name, StandardKeyword value) {
    map.put(name, value);
    return this;
  }

}
