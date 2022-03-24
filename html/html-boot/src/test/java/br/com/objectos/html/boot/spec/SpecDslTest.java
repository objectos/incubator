/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.boot.spec;

import static org.testng.Assert.assertEquals;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.html.boot.AbstractHtmlBootTest;
import org.testng.annotations.Test;

public class SpecDslTest extends AbstractHtmlBootTest {

  @Test
  public void category_createCategoryAndAddElements() {
    SpecDsl dsl = specDsl();
    CategorySpec flow = dsl.category("flow");
    assertEquals(flow.name(), "flow");

    dsl.element("div").category(flow);
    dsl.element("form").category(flow);

    assertEquals(flow.name(), "flow");

    MutableList<String> names;
    names = MutableList.create();

    for (Child child : flow.childStream()) {
      names.add(child.name());
    }

    assertEquals(names, ImmutableList.of("div", "form"));
  }

  @Test
  public void element_addCategoryAsContentModel() {
    SpecDsl dsl = specDsl();
    CategorySpec flow = dsl.category("flow");
    ElementSpec div = dsl.element("div")
        .category(flow)
        .contentModel(flow);
    ElementSpec form = dsl.element("form")
        .category(flow);

    dsl.prepare();

    assertEquals(toName(div.parentStream()), ImmutableList.of("div"));
    assertEquals(toName(form.parentStream()), ImmutableList.of("div"));
  }

  @Test
  public void element_formCanHaveFlowContentExceptForForms() {
    SpecDsl dsl = specDsl();
    CategorySpec flow = dsl.category("flow");
    ElementSpec div = dsl.element("div")
        .category(flow)
        .contentModel(flow);

    ElementSpec form = dsl.element("form");
    form.category(flow)
        .contentModel(flow)
        .except(form);

    dsl.prepare();

    assertEquals(toName(div.parentStream()), ImmutableList.of("div", "form"));
    assertEquals(toName(form.parentStream()), ImmutableList.of("div"));
  }

  @Test
  public void text_withCategory() {
    SpecDsl dsl = specDsl();
    CategorySpec flow = dsl.category("flow");
    ElementSpec div = dsl.element("div")
        .category(flow)
        .contentModel(flow);

    TextSpec text = dsl.text()
        .category(flow);

    dsl.prepare();

    assertEquals(toName(div.parentStream()), ImmutableList.of("div"));
    assertEquals(toName(text.parentStream()), ImmutableList.of("div"));
  }

  private Iterable<String> toName(Iterable<ElementSpec> specs) {
    MutableList<String> result;
    result = MutableList.create();

    for (ElementSpec spec : specs) {
      result.add(spec.name());
    }

    return result;
  }

}