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
package objectos.docs.ui;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;

public class PageSwitcherTest {

  private final Map<Class<?>, Object> map = new HashMap<>();

  @Test
  public void backPage() {
    Page0 p0 = new Page0();
    Page1 p1 = new Page1();
    Page2 p2 = new Page2();

    map.put(Page0.class, p0);
    map.put(Page1.class, p1);
    map.put(Page2.class, p2);

    Locator locator;
    locator = this::locator;

    PageSwitcher sw;
    sw = new PageSwitcher(locator);

    sw.load(p0);

    assertNull(sw.backPage(p0));
    assertSame(sw.backPage(p1), p0);
    assertSame(sw.backPage(p2), p1);
  }

  @SuppressWarnings("unchecked")
  private <T> T locator(Class<? extends T> key) {
    return (T) map.get(key);
  }

}