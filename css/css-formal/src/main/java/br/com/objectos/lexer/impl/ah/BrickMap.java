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
package br.com.objectos.lexer.impl.ah;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

class BrickMap {

  private final Map<Object, Link> valueMap = new IdentityHashMap<>();
  private final Map<Class<?>, Link> typeMap = new HashMap<>();

  BrickMap() {
  }

  public final Link getByValue(Object value) {
    Link link = valueMap.get(value);

    if (link != null) {
      return decorate(link);
    }

    Class<?> type = value.getClass();
    return getByType(type);
  }

  public final Link getByType(Class<?> type) {
    Link link = typeMap.get(type);

    if (link != null) {
      return decorate(link);
    }

    return null;
  }

  public final void putType(Class<?> type, Link link) {
    Link toPut = link;

    if (typeMap.containsKey(type)) {
      Link existing = typeMap.get(type);
      toPut = existing.merge(link);
    }

    typeMap.put(type, toPut);
  }

  public final void putValue(Object value, Link link) {
    if (valueMap.containsKey(value)) {
      throw new UnsupportedOperationException("Implement me");
    }
    valueMap.put(value, link);
  }

  private Link decorate(Link link) {
    return new BrickStartLink(link);
  }

}