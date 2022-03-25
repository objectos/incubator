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
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.model.element.ProcessingPackageReprocessor;
import br.com.objectos.code.testing.InMemoryJavaFileConsumer;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.testng.annotations.Test;

public class BeDirectoryAnnotatedPackageTest extends AbstractBeProcessorTest
    implements
    ProcessingPackageReprocessor {

  @Test
  public void iter00() {
    PackageName packageName;
    packageName = PackageName.named("br.com.objectos.be.processor.testing.iter00");

    InMemoryJavaFileConsumer result;
    result = process(packageName);

    assertEquals(result.size(), 2);

    assertTrue(
        result.contains(
            packageName.nestedClass("Iter00Directory")
        )
    );
    assertTrue(
        result.contains(
            packageName.nestedClass("Iter00Path")
        )
    );
  }

  // ProcessingPackageReprocessor

  @Override
  public final void reprocessPackage(ProcessingPackage pkg) {
    // noop
  }

  private InMemoryJavaFileConsumer process(PackageName packageName) {
    try {
      InMemoryJavaFileConsumer javaFileConsumer;
      javaFileConsumer = new InMemoryJavaFileConsumer();

      ProcessingPackage pkg;
      pkg = testingEnv.getProcessingPackage(packageName.getCanonicalName());

      BeDirectoryAnnotatedPackage.process(javaFileConsumer, this, pkg);

      return javaFileConsumer;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}