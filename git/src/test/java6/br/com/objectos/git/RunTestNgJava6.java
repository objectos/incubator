/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.git;

import java.util.ArrayList;
import java.util.List;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * Manually runs a TestNG test. Required as the Eclipse TestNG plugin requires a
 * Java7+ jvm to run.
 *
 * @since 3
 */
public class RunTestNgJava6 {

  public static void main(String[] args) {
    // runMethod(GitTest.class, "testCase08");
  }

  static void doFailures(TestListenerAdapter listener) {
    List<ITestResult> failedTests;
    failedTests = listener.getFailedTests();

    for (ITestResult result : failedTests) {
      Throwable t;
      t = result.getThrowable();

      if (t != null) {
        t.printStackTrace();
      }
    }
  }

  static void runClasses(Class<?>... tests) {
    TestNG testng;
    testng = new TestNG();

    TestListenerAdapter listener;
    listener = new TestListenerAdapter();

    testng.addListener(listener);

    testng.setTestClasses(tests);

    testng.run();

    doFailures(listener);
  }

  static void runMethod(Class<?> testClass, String methodName) {
    TestNG testng;
    testng = new TestNG();

    TestListenerAdapter listener;
    listener = new TestListenerAdapter();

    testng.addListener(listener);

    XmlSuite suite;
    suite = new XmlSuite();

    suite.setName("TmpSuite");

    XmlTest test;
    test = new XmlTest(suite);

    test.setName("TmpTest");

    List<XmlClass> classes;
    classes = new ArrayList<XmlClass>();

    XmlClass xmlClass;
    xmlClass = new XmlClass(testClass.getCanonicalName());

    List<XmlInclude> includes;
    includes = new ArrayList<XmlInclude>();

    XmlInclude include;
    include = new XmlInclude(methodName);

    includes.add(include);

    xmlClass.setIncludedMethods(includes);

    classes.add(xmlClass);

    test.setXmlClasses(classes);

    List<XmlSuite> suites;
    suites = new ArrayList<XmlSuite>();

    suites.add(suite);

    testng.setXmlSuites(suites);

    testng.run();

    doFailures(listener);
  }

}