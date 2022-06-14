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

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.html.boot.element.ElementNames;
import br.com.objectos.html.boot.spec.AttributeSpec;
import br.com.objectos.html.boot.spec.Spec;
import br.com.objectos.html.boot.spec.SpecDsl;
import br.com.objectos.html.boot.spec.Step;
import br.com.objectos.html.boot.spec.StepFactory;
import java.util.HashMap;
import java.util.Map;
import objectos.lang.Check;
import objectos.util.MutableList;

public abstract class AbstractHtmlBootTest {

  SpecDsl specDsl;

  protected final Map<String, JavaFile> execute(StepFactory factory, Spec spec) {
    SpecDsl dsl = new SpecDsl();

    dsl.with(spec);

    MutableList<JavaFile> javaFiles = new MutableList<>();

    Step step = factory.get(javaFiles::add);

    dsl.execute(step);

    Map<String, JavaFile> map;
    map = new HashMap<>();

    for (int i = 0; i < javaFiles.size(); i++) {
      JavaFile file = javaFiles.get(i);

      String simpleName = file.simpleName();

      map.put(simpleName, file);
    }

    return map;
  }

  protected final SpecDsl specDsl() {
    return specDsl = new SpecDsl();
  }

  protected final void testLines(Object o, String... expected) {
    Check.notNull(o, "o == null");

    assertHasLines(o.toString(), expected);
  }

  final AttributeSpec attributeSpec(String name) {
    return specDsl.attributeSpec(name);
  }

  final NamedClass elementClassName(String name) {
    return ElementNames.className(name);
  }

  private void assertHasLines(String string, String[] expected) {
    String[] parts = string.split("\n");

    assertEquals(parts, expected);
  }

}