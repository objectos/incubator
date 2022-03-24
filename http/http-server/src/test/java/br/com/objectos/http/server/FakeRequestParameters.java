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
package br.com.objectos.http.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class FakeRequestParameters implements RequestParameters {

  private static final FakeRequestParameters EMPTY = new FakeRequestParameters(new HashMap<>(0));

  private final Map<String, String> map;

  FakeRequestParameters(Map<String, String> map) {
    this.map = map;
  }

  public static FakeRequestParameters empty() {
    return EMPTY;
  }

  @Override
  public String get(String key) {
    return map.get(key);
  }

  @Override
  public int hashCode() {
    return map.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    FakeRequestParameters that = (FakeRequestParameters) obj;
    return Objects.equals(map, that.map);
  }

}