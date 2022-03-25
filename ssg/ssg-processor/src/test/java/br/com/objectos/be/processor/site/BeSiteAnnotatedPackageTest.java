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
package br.com.objectos.be.processor.site;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.be.processor.AbstractBeProcessorTest;
import br.com.objectos.be.processor.testing.iter02.Iter02Directory;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.processing.type.ErrorTypeException;
import br.com.objectos.code.testing.InMemoryJavaFileConsumer;
import java.io.IOException;
import org.testng.annotations.Test;

public class BeSiteAnnotatedPackageTest extends AbstractBeProcessorTest {

  @Test
  public void _iter02() throws ErrorTypeException, IOException {
    ProcessingPackage iter02;
    iter02 = testingEnv.getProcessingPackage(Iter02Directory.class);

    InMemoryJavaFileConsumer result;
    result = process(iter02);

    assertEquals(result.size(), 1);

    br.com.objectos.code.java.declaration.PackageName iter02Name;
    iter02Name = iter02.toNamedPackage();

    assertTrue(
        result.contains(
            iter02Name.nestedClass("Iter02Site")
        )
    );
  }

  private InMemoryJavaFileConsumer process(
      ProcessingPackage annotatedPackage)
      throws ErrorTypeException, IOException {
    InMemoryJavaFileConsumer javaFileConsumer;
    javaFileConsumer = new InMemoryJavaFileConsumer();

    BeSiteAnnotatedPackage.process(javaFileConsumer, annotatedPackage);

    return javaFileConsumer;
  }

}
