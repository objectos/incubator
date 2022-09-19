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

import org.testng.annotations.Test;

public class OrderBuilderTest {

  @Test(
      expectedExceptions = NullPointerException.class,
      expectedExceptionsMessageRegExp = "item == null")
  public void add() {
    var builder = new OrderBuilder();

    builder.add(null);
  }

  @Test(
      expectedExceptions = NullPointerException.class,
      expectedExceptionsMessageRegExp = "items\\[2\\] == null")
  public void addAll() {
    var builder = new OrderBuilder();

    builder.addAll(
      new Item("A"),
      new Item("B"),
      null,
      new Item("D")
    );
  }

}