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

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingType;
import org.testng.annotations.Test;

public class CodeTestngTest extends AbstractCodeTestNgTest {

  @Override
  public final String getModuleName() {
    return "br.com.objectos.code.testng";
  }

  @Test
  public void getProcessingType() {
    ProcessingType subject;
    subject = testingEnv.getProcessingType(Subject.class);

    NamedClass name;
    name = subject.getName();

    assertEquals(name.getCanonicalName(), Subject.class.getCanonicalName());
  }

  static class Subject {
    int a;

    void variableElement(String name) {}
  }

}