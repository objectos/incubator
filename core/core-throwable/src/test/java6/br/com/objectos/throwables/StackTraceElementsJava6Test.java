/*
 * Copyright (C) 2021-2022 Objectos Software LTDA.
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
package br.com.objectos.throwables;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import br.com.objectos.core.throwable.StackTraceElements;
import org.testng.annotations.Test;

public class StackTraceElementsJava6Test {

  private final StackTraceElement element = new StackTraceElement(
      "test.Foo", "a", "Foo.java", 123);

  @Test
  public void createStackTraceElement() {
    String classLoaderName;
    classLoaderName = "loader";

    String moduleName;
    moduleName = "some-module";

    String moduleVersion;
    moduleVersion = "some-version";

    String declaringClass;
    declaringClass = "o7.foo.Bar";

    String methodName;
    methodName = "baz";

    String fileName;
    fileName = "Bar.java";

    int lineNumber;
    lineNumber = 123;

    StackTraceElement result;
    result = StackTraceElements.create(
        classLoaderName,
        moduleName,
        moduleVersion,
        declaringClass,
        methodName,
        fileName,
        lineNumber
    );

    assertEquals(result.getClassName(), declaringClass);

    assertEquals(result.getMethodName(), methodName);

    assertEquals(result.getFileName(), fileName);

    assertEquals(result.getLineNumber(), lineNumber);
  }

  @Test
  public void getClassLoaderName() {
    assertNull(StackTraceElements.getClassLoaderName(element));
  }

  @Test
  public void getModuleName() {
    assertNull(StackTraceElements.getModuleName(element));
  }

  @Test
  public void getModuleVersion() {
    assertNull(StackTraceElements.getModuleVersion(element));
  }

}