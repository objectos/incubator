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
package br.com.objectos.code.testing;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.core.list.ImmutableList;
import objectos.lang.Checks;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class TestingEnvironmentTest implements IHookable, TestingEnvironment.Bootstrap {

  private Runner runner;

  private TestingEnvironment testingEnv;

  @Override
  public final String getModuleName() {
    return "br.com.objectos.code.testing";
  }

  @Test
  public void getProcessingPackage() {
    ProcessingPackage ex;
    ex = testingEnv.getProcessingPackage("br.com.objectos.code.testing");

    boolean result;
    result = false;

    NamedClass expected;
    expected = NamedClass.of(MarkerA.class);

    ImmutableList<ProcessingAnnotation> annotations;
    annotations = ex.getDirectlyPresentAnnotations();

    for (int i = 0; i < annotations.size(); i++) {
      ProcessingAnnotation annotation;
      annotation = annotations.get(i);

      NamedClass className;
      className = annotation.className();

      if (className.equals(expected)) {
        result = true;

        break;
      }
    }

    assertTrue(result);
  }

  @Test
  public void getProcessingType() {
    ProcessingType subject;
    subject = testingEnv.getProcessingType(Subject.class);

    assertEquals(
        subject.getBinaryName(),
        "br.com.objectos.code.testing.TestingEnvironmentTest$Subject"
    );
  }

  @SuppressWarnings("exports")
  @Override
  public final void run(IHookCallBack callBack, ITestResult testResult) {
    runner = new Runner(callBack, testResult);

    TestingEnvironment.bootstrap(this);
  }

  @Override
  public final void setTestingEnvironment(TestingEnvironment round) {
    testingEnv = Checks.checkNotNull(round, "round == null");
    runner.runTestMethod();
  }

  abstract class Subject {}

  private class Runner {

    final IHookCallBack callBack;

    final ITestResult testResult;

    Runner(IHookCallBack callBack, ITestResult testResult) {
      this.callBack = callBack;
      this.testResult = testResult;
    }

    final void runTestMethod() {
      callBack.runTestMethod(testResult);
    }

  }

}
