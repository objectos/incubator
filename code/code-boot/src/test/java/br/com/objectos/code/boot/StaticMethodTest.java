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

import br.com.objectos.code.model.element.ProcessingMethod;
import br.com.objectos.code.model.element.ProcessingType;
import objectos.util.UnmodifiableList;
import org.testng.annotations.Test;

public class StaticMethodTest extends AbstractCodeBootTest {

  @Test
  public void testCase00() {
    StaticMethod method;
    method = getStaticMethod(Expressions.class, "id");

    test(
      method.generate(),
      "public static br.com.objectos.code.java.expression.Identifier id(java.lang.String name) {",
      "  return br.com.objectos.code.boot.AbstractCodeBootTest.Expressions.id(name);",
      "}"
    );
  }

  @Test
  public void testCase01() {
    ProcessingType subject;
    subject = query(TestCase01Factory.class);

    UnmodifiableList<StaticMethod> result;
    result = StaticMethod.listOf(subject);

    assertEquals(result.size(), 1);

    StaticMethod m1;
    m1 = result.get(0);

    test(
      m1.generate(),
      "public static void m1() throws java.io.IOException {",
      "  br.com.objectos.code.boot.TestCase01Factory.m1();",
      "}"
    );
  }

  private StaticMethod getStaticMethod(Class<?> type, String methodName) {
    ProcessingType processingType;
    processingType = query(type);

    ProcessingMethod method;
    method = method(type, methodName);

    return StaticMethod.ofUnchecked(processingType, method);
  }

}
