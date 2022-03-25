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
package br.com.objectos.be.processor.resources;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import br.com.objectos.be.processor.AbstractBeProcessorTest;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.testing.InMemoryJavaFileConsumer;
import org.testng.annotations.Test;

public class BeResourcesAnnotatedPackageTest extends AbstractBeProcessorTest {

  @Test
  public void testingImg() {
    PackageName packageName;
    packageName = PackageName.named("br.com.objectos.be.processor.testing.img");

    InMemoryJavaFileConsumer result;
    result = process(packageName);

    assertEquals(result.size(), 1);

    assertTrue(
        result.contains(
            packageName.nestedClass("Image5x2Jpg")
        )
    );
  }

  private InMemoryJavaFileConsumer process(PackageName packageName) {
    InMemoryJavaFileConsumer javaFileConsumer;
    javaFileConsumer = new InMemoryJavaFileConsumer();

    ProcessingPackage pkg;
    pkg = testingEnv.getProcessingPackage(packageName.getCanonicalName());

    BeResourcesAnnotatedPackage.process(javaFileConsumer, pkg);

    return javaFileConsumer;
  }

}
