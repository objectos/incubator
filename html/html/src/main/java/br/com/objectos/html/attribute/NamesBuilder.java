/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.attribute;

import objectos.util.UnmodifiableMap;
import objectos.util.MutableMap;

class NamesBuilder {

  private final MutableMap<String, StandardAttributeName> map = new MutableMap<>();

  public final UnmodifiableMap<String, StandardAttributeName> build() {
    return map.toUnmodifiableMap();
  }

  public final NamesBuilder put(String name, StandardAttributeName value) {
    map.put(name, value);
    return this;
  }

}
