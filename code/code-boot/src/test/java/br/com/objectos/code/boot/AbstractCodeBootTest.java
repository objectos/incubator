/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.boot;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.annotations.Ignore;
import br.com.objectos.code.java.expression.ArrayInitializer;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.model.element.ProcessingField;
import br.com.objectos.code.model.element.ProcessingMethod;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.testng.AbstractCodeTestNgTest;
import java.util.NoSuchElementException;
import objectos.util.ImmutableList;

public abstract class AbstractCodeBootTest extends AbstractCodeTestNgTest {

  @Override
  public final String getModuleName() {
    return "br.com.objectos.code.boot";
  }

  protected final ProcessingField field(Class<?> type, String name) {
    ProcessingType processing;
    processing = query(type);

    ImmutableList<ProcessingField> fields;
    fields = processing.getDeclaredOrInheritedFields();

    for (int i = 0; i < fields.size(); i++) {
      ProcessingField field;
      field = fields.get(i);

      if (field.hasName(name)) {
        return field;
      }
    }

    throw new NoSuchElementException(name);
  }

  protected final ProcessingMethod method(Class<?> type, String name) {
    ProcessingType processing;
    processing = query(type);

    ImmutableList<ProcessingMethod> methods;
    methods = processing.getDeclaredMethods();

    for (int i = 0; i < methods.size(); i++) {
      ProcessingMethod method;
      method = methods.get(i);

      if (method.hasName(name)) {
        return method;
      }
    }

    throw new NoSuchElementException(name);
  }

  protected final ProcessingType query(Class<?> type) {
    return testingEnv.getProcessingType(type);
  }

  protected final void test(Object el, String... lines) {
    testToString(el, lines);
  }

  protected final void testToString(Object o, String... lines) {
    ImmutableList<String> list;
    list = ImmutableList.copyOf(lines);

    String expected;
    expected = list.join("\n");

    assertEquals(o.toString(), expected);
  }

  @SuppressWarnings("exports")
  public static class Expressions {
    public static ArrayInitializer a() {
      throw new UnsupportedOperationException();
    }

    @Ignore
    public static Identifier annotatedIgnoreMe() {
      throw new UnsupportedOperationException();
    }

    public static Identifier id(String name) {
      throw new UnsupportedOperationException();
    }

    final Identifier ignoreMe() {
      throw new UnsupportedOperationException();
    }
  }

  public static class Fields {
    public static final String FIELD_TEST = "I am valid";

    static final int notPublic = 0;

    public String notFinal = "not final";
  }

}
