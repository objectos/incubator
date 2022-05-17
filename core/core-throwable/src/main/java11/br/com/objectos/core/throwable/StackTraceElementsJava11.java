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
class StackTraceElementsJava11 extends AbstractStackTraceElements {

  @Constructor
  StackTraceElementsJava11() {}

  /**
   * Creates a new {@code StackTraceElement} instance in a Java multi-release
   * manner. Invokes
   * {@link StackTraceElement#StackTraceElement(String, String, String, String, String, String, int)}.
   *
   * @param classLoaderName
   *        please consult the 'See Also' link below.
   * @param moduleName
   *        please consult the 'See Also' link below.
   * @param moduleVersion
   *        please consult the 'See Also' link below.
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
   * @see StackTraceElement#StackTraceElement(String, String, String, String,
   *      String, String, int)
   */
  public static StackTraceElement create(
      String classLoaderName,
      String moduleName, String moduleVersion,
      String declaringClass, String methodName,
      String fileName, int lineNumber) {
    return new StackTraceElement(
        classLoaderName,
        moduleName, moduleVersion,
        declaringClass, methodName,
        fileName, lineNumber
    );
  }

  /**
   * Returns the result of evaluating {@code element.getClassLoaderName()}.
   *
   * @param element
   *        a stack trace element
   *
   * @return the result of evaluating {@code element.getClassLoaderName()}
   *
   * @see StackTraceElement#getClassLoaderName()
   */
  public static String getClassLoaderName(StackTraceElement element) {
    Checks.checkNotNull(element, "element == null");

    return element.getClassLoaderName();
  }

  /**
   * Returns the result of evaluating {@code element.getModuleName()}.
   *
   * @param element
   *        a stack trace element
   *
   * @return the result of evaluating {@code element.getModuleName()}
   *
   * @see StackTraceElement#getModuleName()
   */
  public static String getModuleName(StackTraceElement element) {
    Checks.checkNotNull(element, "element == null");

    return element.getModuleName();
  }

  /**
   * Returns the result of evaluating {@code element.getModuleVersion()}.
   *
   * @param element
   *        a stack trace element
   *
   * @return the result of evaluating {@code element.getModuleVersion()}
   *
   * @see StackTraceElement#getModuleVersion()
   */
  public static String getModuleVersion(StackTraceElement element) {
    Checks.checkNotNull(element, "element == null");

    return element.getModuleVersion();
  }

}