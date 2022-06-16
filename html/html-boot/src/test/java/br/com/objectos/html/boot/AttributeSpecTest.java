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
package br.com.objectos.html.boot;

import static org.testng.Assert.assertEquals;

import br.com.objectos.html.boot.attribute.AttributeNames;
import br.com.objectos.html.boot.spec.AttributeKind;
import br.com.objectos.html.boot.spec.AttributeSpec;
import br.com.objectos.html.boot.spec.SpecDsl;
import br.com.objectos.html.boot.spi.type.SpiType;
import objectos.util.UnmodifiableList;
import objectos.util.UnmodifiableSet;
import org.testng.annotations.Test;

public class AttributeSpecTest extends AbstractHtmlBootTest {

  @Test
  public void classAttribute() {
    specDsl()
        .rootElement()
        .attribute("class").as("_class")
        .attributeEnd();

    AttributeSpec res = attributeSpec("class");

    assertEquals(res.name(), "class");
    assertEquals(res.kindSet(), UnmodifiableSet.of(AttributeKind.STRING));
    assertEquals(res.interfaceSet(), UnmodifiableSet.of(AttributeNames.GlobalAttributeName));
    assertEquals(res.methodNameStream(), UnmodifiableList.of("_class"));
  }

  @Test
  public void hiddenAttribute() {
    specDsl()
        .rootElement()
        .attribute("hidden")
        .booleanType();

    AttributeSpec res = attributeSpec("hidden");

    assertEquals(res.name(), "hidden");
    assertEquals(res.kindSet(), UnmodifiableSet.of(AttributeKind.BOOLEAN));
    assertEquals(res.interfaceSet(), UnmodifiableSet.of(AttributeNames.GlobalAttributeName));
    assertEquals(res.methodNameStream(), UnmodifiableList.of("hidden"));
  }

  @Test
  public void hrefAttribute() {
    SpecDsl dsl = specDsl();

    dsl.element("a")
        .attribute("href")
        .attributeEnd();

    dsl.element("link")
        .attribute("href")
        .attributeEnd();

    AttributeSpec res = attributeSpec("href");

    assertEquals(res.name(), "href");
    assertEquals(res.kindSet(), UnmodifiableSet.of(AttributeKind.STRING));
    assertEquals(
      res.interfaceSet(),
      UnmodifiableSet.of(
        SpiType.className("AValue"),
        SpiType.className("LinkValue")
      )
    );
  }

  @Test
  public void idAttribute() {
    specDsl()
        .rootElement()
        .attribute("id")
        .attributeEnd();

    AttributeSpec res = attributeSpec("id");

    assertEquals(res.name(), "id");
    assertEquals(res.kindSet(), UnmodifiableSet.of(AttributeKind.STRING));
    assertEquals(res.interfaceSet(), UnmodifiableSet.of(AttributeNames.GlobalAttributeName));
    assertEquals(res.methodNameStream(), UnmodifiableList.of("id"));
  }

}