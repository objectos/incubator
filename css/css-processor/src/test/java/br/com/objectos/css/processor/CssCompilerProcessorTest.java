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
package br.com.objectos.css.processor;

import static br.com.objectos.tools.Tools.compilationUnit;
import static br.com.objectos.tools.Tools.javac;
import static br.com.objectos.tools.Tools.processor;

import br.com.objectos.code.testing.annotation.processing.ResourcesFiler;
import br.com.objectos.tools.Compilation;
import org.testng.annotations.Test;

public class CssCompilerProcessorTest {

  @Test
  public void test() {
    Compilation compilation;
    compilation = javac(
      processor(new CssCompilerProcessor(ResourcesFiler::getInstance)),
      compilationUnit(
        "@br.com.objectos.css.CssCompiler({",
        "    \"Sheet\"",
        "})",
        "package code.css.compiler;"
      )
    );

    compilation.assertWasSuccessful();

    Util.assertHasLines(
      compilation.getJavaFile("code.css.compiler.Sheet").toString(),

      "package code.css.compiler;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import br.com.objectos.css.sheet.AbstractStyleSheet;",
      "",
      "@Generated(\"br.com.objectos.css.processor.CssCompilerProcessor\")",
      "public class Sheet extends AbstractStyleSheet {",
      "",
      "  @Override",
      "  protected final void definition() {",
      "    style(",
      "        html,",
      "",
      "        margin(zero())",
      "    );",
      "  }",
      "",
      "}"
    );
  }

}
