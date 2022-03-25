/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.processor.directory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.be.processor.AbstractBeProcessorTest;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import org.testng.annotations.Test;

public class BeAnnotatedTypeMethodTest extends AbstractBeProcessorTest {

  @Test
  public void iter01() {
    String indexCanonicalName;
    indexCanonicalName = "br.com.objectos.be.processor.testing.iter01.Index01";

    ProcessingType indexProcessingType;
    indexProcessingType = testingEnv.getProcessingType(indexCanonicalName);

    ImmutableList<BeAnnotatedTypeMethod> indexMethods;
    indexMethods = BeAnnotatedTypeMethod.fromBeAnnotatedProcessingType(indexProcessingType);

    ImmutableList<String> indexMethodNames;
    indexMethodNames = toMethodName(indexMethods);

    assertEquals(indexMethodNames.size(), 1);
    assertTrue(indexMethodNames.contains("styles"));
  }

  private ImmutableList<String> toMethodName(ImmutableList<BeAnnotatedTypeMethod> methods) {
    MutableList<String> result;
    result = MutableList.create();

    for (int i = 0; i < methods.size(); i++) {
      BeAnnotatedTypeMethod method;
      method = methods.get(i);

      String name;
      name = method.methodName();

      result.add(name);
    }

    return result.toImmutableList();
  }

}