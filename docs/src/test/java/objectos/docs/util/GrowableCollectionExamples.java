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

import java.util.List;
import objectos.util.GrowableList;
import objectos.util.GrowableMap;
import org.testng.annotations.Test;

public class GrowableCollectionExamples {

  @Test(
      expectedExceptions = NullPointerException.class,
      expectedExceptionsMessageRegExp = "e == null")
  public void add() {
    var list = new GrowableList<String>();

    list.add(null);
  }

  @Test
  public void toStringTest() {
    var cities = new GrowableList<City>();

    cities.add(new City("São Paulo"));
    cities.add(new City("Piracicaba"));
    cities.add(new City("São José dos Campos"));

    System.out.println(cities);
  }

  @Test
  public void toStringTest_map() {
    var states = new GrowableMap<State, List<City>>();

    var cities = new GrowableList<City>();

    cities.add(new City("São Paulo"));
    cities.add(new City("Piracicaba"));
    cities.add(new City("São José dos Campos"));

    states.put(new State("SP"), cities.toUnmodifiableList());

    cities.clear();

    cities.add(new City("Belém"));
    cities.add(new City("Santarém"));
    cities.add(new City("Tomé-Açu"));

    states.put(new State("PA"), cities.toUnmodifiableList());

    System.out.println(states);
  }

}