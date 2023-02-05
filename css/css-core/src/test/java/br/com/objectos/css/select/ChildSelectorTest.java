/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.css.select;

import static br.com.objectos.css.select.Combinator.CHILD;
import static br.com.objectos.css.select.FakeSelectable.named;
import static br.com.objectos.css.select.SelectorAssertion.assertThat;
import static br.com.objectos.css.select.SelectorFactory.sel;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class ChildSelectorTest extends AbstractCssSelectTest {

  @Test
  public void matches() {
    assertThat(sel(ul, CHILD, li))
        .matches(named("li").withParent(named("ul")))
        .doesNotMatch(named("li").withParent(named("a").withParent(named("ul"))))
        .doesNotMatch(named("a").withParent(named("ul")));
  }

  @Test
  public void toStringTest() {
    assertEquals(sel(ul, cn("bar"), CHILD, li).toString(), "ul.bar > li");
  }

}