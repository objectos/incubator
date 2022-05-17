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
package br.com.objectos.core.throwable;

import br.com.objectos.latest.Concrete.Bridge;
import br.com.objectos.latest.Concrete.Constructor;
import objectos.lang.Checks;

@Bridge
class StackTraceElementsJava6 extends AbstractStackTraceElements {

  @Constructor
  StackTraceElementsJava6() {}

  /**
   * Creates a new {@code StackTraceElement} instance in a Java multi-release
   * manner. While it accepts module-related values, they are ignored in this
   * build of this class.
   *
   * @param classLoaderName
   *        ignored as it is only available for Java 9 or later runtimes
   * @param moduleName
   *        ignored as it is only available for Java 9 or later runtimes
   * @param moduleVersion
   *        ignored as it is only available for Java 9 or later runtimes
   * @param declaringClass
   *        please consult the 'See Also' link below.
   * @param methodName
   *        please consult the 'See Also' link below.
   * @param fileName
   *        please consult the 'See Also' link below.
   * @param lineNumber
   *        please consult the 'See Also' link below.
   *
   * @return a newly constructed stack trace element
   *
   * @see StackTraceElement#StackTraceElement(String, String, String, int)
   */
  public static StackTraceElement create(
      String classLoaderName,
      String moduleName, String moduleVersion,
      String declaringClass, String methodName,
      String fileName, int lineNumber) {
    return new StackTraceElement(declaringClass, methodName, fileName, lineNumber);
  }

  /**
   * Returns {@code null} as the operation
   * {@code StackTraceElement.getClassLoaderName()} is only available for Java
   * 9 or later runtimes.
   *
   * @param element
   *        a stack trace element
   *
   * @return {@code null} as the class loader name is only avaible on later Java
   *         releases
   */
  public static String getClassLoaderName(StackTraceElement element) {
    Checks.checkNotNull(element, "element == null");

    return null;
  }

  /**
   * Returns {@code null} as the operation
   * {@code StackTraceElement.getModuleName()} is only available for Java
   * 9 or later runtimes.
   *
   * @param element
   *        a stack trace element
   *
   * @return {@code null} as the module name is only avaible on later Java
   *         releases
   */
  public static String getModuleName(StackTraceElement element) {
    Checks.checkNotNull(element, "element == null");

    return null;
  }

  /**
   * Returns {@code null} as the operation
   * {@code StackTraceElement.getModuleVersion()} is only available for Java
   * 9 or later runtimes.
   *
   * @param element
   *        a stack trace element
   *
   * @return {@code null} as the module version is only avaible on later Java
   *         releases
   */
  public static String getModuleVersion(StackTraceElement element) {
    Checks.checkNotNull(element, "element == null");

    return null;
  }

}