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

import br.com.objectos.be.processor.AbstractBeProcessorTest;
import br.com.objectos.be.processor.testing.iter01.Index01Html;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.core.list.ImmutableList;
import org.testng.annotations.Test;

public class BeAnnotatedTypeTest extends AbstractBeProcessorTest {

  @Test
  public void iter01() {
    ProcessingType indexHtml;
    indexHtml = testingEnv.getProcessingType(Index01Html.class);

    BeAnnotatedType type;
    type = BeAnnotatedType.fromGeneratedBeResource(indexHtml);

    assertEquals(
        type.className,
        PackageName.of(Index01Html.class).nestedClass("Index01")
    );

    ImmutableList<BeAnnotatedTypeMethod> methods;
    methods = type.methods;

    assertEquals(methods.size(), 1);

    BeAnnotatedTypeMethod method0;
    method0 = methods.get(0);

    assertEquals(method0.methodName(), "styles");
  }

}
