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
package br.com.objectos.core.map;

import java.util.Map;
import org.testng.annotations.Test;

public class OrderedGrowableMapTest extends MutableMapTest {

  @Test(enabled = false)
  public void _enableEclipseCodeMining() {}

  @Override
  final <K, V> void assertContents(Map<Thing, String> result, Map<Thing, String> expected) {
    assertOrderedMap(result, expected);
  }

  @Override
  final <K, V> Map<K, V> createExpectedMap() {
    return Maps.newLinkedHashMap();
  }

  @Override
  final <K, V> MutableMap<K, V> createGrowableMap() {
    return MutableOrderedMap.create();
  }

}