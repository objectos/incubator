/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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

import objectos.util.GrowableList;
import objectos.util.GrowableSet;
import org.testng.annotations.Test;

public class BaseCollectionJoinExample {

  @Test
  public void all() {
    var abc = new GrowableList<String>();

    abc.add("A");
    abc.add("B");
    abc.add("C");

    assertEquals(abc.join(), "ABC");
    assertEquals(abc.join(", "), "A, B, C");
    assertEquals(abc.join(", ", "[", "]"), "[A, B, C]");
  }

  @Test
  public void join1() {
    var selectors = new GrowableList<String>();

    selectors.add("#foo");
    selectors.add(".bar");
    selectors.add(":hover");

    assertEquals(
      selectors.join(),
      "#foo.bar:hover"
    );
  }

  @Test
  public void join2() {
    var modifiers = new GrowableList<String>();

    modifiers.add("private");
    modifiers.add("static");
    modifiers.add("final");

    assertEquals(
      modifiers.join(" "),
      "private static final"
    );
  }

  @Test
  public void join3() {
    var typeArguments = new GrowableList<String>();

    typeArguments.add("Integer");
    typeArguments.add("String");

    assertEquals(
      typeArguments.join(", ", "Map<", ">"),
      "Map<Integer, String>"
    );
  }

  @Test
  public void join4() {
    var modifiers = new GrowableSet<String>();

    modifiers.add("private");
    modifiers.add("static");
    modifiers.add("final");

    assertEquals(
      modifiers.join(" "),
      "private final static"
    );
  }

}