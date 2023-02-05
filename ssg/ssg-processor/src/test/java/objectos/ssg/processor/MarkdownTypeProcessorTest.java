/*
 * Copyright (C) 2011-2023 Objectos Software LTDA.
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
package objectos.ssg.processor;

import static br.com.objectos.tools.Tools.compilationUnit;
import static br.com.objectos.tools.Tools.javac;
import static br.com.objectos.tools.Tools.processor;
import static org.testng.Assert.assertEquals;

import br.com.objectos.tools.Compilation;
import org.testng.annotations.Test;

public class MarkdownTypeProcessorTest {

  @Test
  public void test() {
    Compilation compilation = javac(
      processor(new MarkdownTypeProcessor()),
      compilationUnit(
        "package pkg01;",
        "/**",
        "# Introduction",
        "",
        "Ipsum lorem",
        "*/",
        "@objectos.ssg.Markdown",
        "public class Processor01 {}"
      )
    );

    compilation.assertWasSuccessful();

    assertHasLines(
      compilation.getResource("pkg01/Processor01.md").contents(),
      "# Introduction",
      "",
      "Ipsum lorem"
    );
  }

  private void assertHasLines(String contents, String... expected) {
    String[] parts;
    parts = contents.split("\n");

    assertEquals(parts, expected);
  }

}