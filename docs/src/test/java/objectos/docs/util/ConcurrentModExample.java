/*
 * Copyright (C) 2022 Objectos Software LTDA.
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
package objectos.docs.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import objectos.util.GrowableList;
import org.testng.annotations.Test;

public class ConcurrentModExample {

  @Test(expectedExceptions = ConcurrentModificationException.class)
  public void arrayList() {
    var list = new ArrayList<String>();

    list.add("A");
    list.add("B");

    var it = list.iterator();

    list.add("C");

    assertTrue(it.hasNext());
    assertEquals(it.next(), "A");
  }

  @Test
  public void growableList() {
    var list = new GrowableList<String>();

    list.add("A");
    list.add("B");

    var it = list.iterator();

    list.add("C");

    assertTrue(it.hasNext());
    assertEquals(it.next(), "A");
  }

}