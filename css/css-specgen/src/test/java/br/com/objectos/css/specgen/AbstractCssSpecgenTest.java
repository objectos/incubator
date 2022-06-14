/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.specgen;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.specgen.spec.Step;
import br.com.objectos.css.specgen.spec.StepAdapter;
import objectos.util.ImmutableMap;
import objectos.util.MutableMap;
import org.testng.Assert;

public abstract class AbstractCssSpecgenTest {

  protected final ImmutableMap<String, JavaFile> execute(
      StepFactory factory, AbstractSpecgen specgen) {
    ThisStepAdapter adapter = new ThisStepAdapter();
    Step step = factory.get(adapter);
    specgen.execute(step);
    return adapter.toMap();
  }

  protected final void testLines(Object o, String... expected) {
    assertHasLines(o.toString(), expected);
  }

  private void assertHasLines(String string, String[] expected) {
    String[] parts = string.split("\n");

    Assert.assertEquals(parts, expected);
  }

  @FunctionalInterface
  protected interface StepFactory {

    Step get(StepAdapter adapter);

  }

  private static class ThisStepAdapter extends StepAdapter {

    private final MutableMap<String, JavaFile> javaFiles = new MutableMap<>();

    @Override
    public final void writeJavaFile(JavaFile javaFile) {
      javaFiles.put(javaFile.simpleName(), javaFile);
    }

    final ImmutableMap<String, JavaFile> toMap() {
      return javaFiles.toImmutableMap();
    }

  }

}
