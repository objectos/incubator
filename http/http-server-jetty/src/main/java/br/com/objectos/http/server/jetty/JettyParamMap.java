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
package br.com.objectos.http.server.jetty;

import br.com.objectos.http.server.RequestParameters;
import java.util.NoSuchElementException;
import objectos.util.UnmodifiableMap;

class JettyParamMap implements RequestParameters {

  private final UnmodifiableMap<String, String> map;

  JettyParamMap(UnmodifiableMap<String, String> map) {
    this.map = map;
  }

  @Override
  public final String get(String key) {
    checkKey(key);
    return map.get(key);
  }

  @Override
  public final boolean getBoolean(String key) {
    return map.containsKey(key);
  }

  private void checkKey(String key) {
    if (!map.containsKey(key)) {
      throw new NoSuchElementException(key);
    }
  }

}