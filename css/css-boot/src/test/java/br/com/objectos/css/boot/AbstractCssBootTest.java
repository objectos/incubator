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
package br.com.objectos.css.boot;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.boot.spec.CssSpec;
import br.com.objectos.css.boot.spec.CssSpecDsl;
import br.com.objectos.css.boot.spec.Step;
import br.com.objectos.css.boot.spec.StepAdapter;
import java.util.Arrays;
import java.util.List;
import objectos.util.UnmodifiableMap;
import objectos.util.MutableList;
import objectos.util.MutableMap;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractCssBootTest {

  protected MutableList<JavaFile> srcJavaFiles;

  protected AbstractCssBootTest() {}

  @BeforeMethod
  public void setUp() {
    srcJavaFiles = new MutableList<>();
  }

  protected final UnmodifiableMap<String, JavaFile> execute(StepFactory factory, CssSpec spec) {
    ThisStepAdapter stepAdapter = new ThisStepAdapter();
    Step step = factory.get(stepAdapter);

    CssSpecDsl dsl = new CssSpecDsl(step);
    spec.acceptCssSpecDsl(dsl);
    dsl.execute();

    return stepAdapter.toMap();
  }

  protected final void testLines(Object o, String... expected) {
    assertHasLines(o.toString(), expected);
  }

  List<String> list(String... strs) {
    return Arrays.asList(strs);
  }

  private void assertHasLines(String string, String[] expected) {
    String[] split = string.split("\n");

    assertEquals(split, expected);
  }

  @FunctionalInterface
  protected interface StepFactory {

    Step get(StepAdapter adapter);

  }

  private static class ThisStepAdapter extends StepAdapter {

    private final MutableMap<String, JavaFile> javaFiles = new MutableMap<>();

    public UnmodifiableMap<String, JavaFile> toMap() {
      return javaFiles.toUnmodifiableMap();
    }

    @Override
    public void writeJavaFile(JavaFile javaFile) {
      javaFiles.put(javaFile.simpleName(), javaFile);
    }

  }

}