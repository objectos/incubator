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
package br.com.objectos.code.testng;

import br.com.objectos.code.testing.TestingEnvironment;
import objectos.lang.Check;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public abstract class AbstractCodeTestNgTest implements IHookable, TestingEnvironment.Bootstrap {

  protected TestingEnvironment testingEnv;

  private Runner runner;

  @Override
  public final void run(IHookCallBack callBack, ITestResult testResult) {
    runner = new Runner(callBack, testResult);

    TestingEnvironment.bootstrap(this);
  }

  @Override
  public final void setTestingEnvironment(TestingEnvironment testingEnv) {
    this.testingEnv = Check.notNull(testingEnv, "testingEnv");

    runner.runTestMethod();
  }

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