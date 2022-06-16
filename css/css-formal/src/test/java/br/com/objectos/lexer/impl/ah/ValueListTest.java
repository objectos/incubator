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
package br.com.objectos.lexer.impl.ah;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import objectos.util.UnmodifiableList;
import org.testng.annotations.Test;

public class ValueListTest {

  @Test
  public void simple() {
    ValueList list = new ValueList();
    list.add("A");
    list.add("B");
    list.add("C");

    assertEquals(list.size(), 3);
    Iterator<Object> iterator = list.iterator();
    UnmodifiableList<Object> res = UnmodifiableList.copyOf(iterator);
    assertEquals(res.size(), 3);
    assertEquals(res.get(0), "A");
    assertEquals(res.get(1), "B");
    assertEquals(res.get(2), "C");

    list.set("ABC");

    assertEquals(list.get(), "ABC");
  }

}