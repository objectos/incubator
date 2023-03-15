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
import static org.testng.Assert.assertTrue;

import br.com.objectos.tools.Compilation;
import br.com.objectos.tools.GeneratedJavaFile;
import org.testng.annotations.Test;

public class BeTest {

  @Test
  public void testCase04() {
    Compilation compilation;
    compilation = javac(
      processor(new MarkdownMethodProcessor()),

      compilationUnit(
        "package testing;",
        "",
        "import objectos.html.HtmlTemplate;",
        "import objectos.ssg.Markdown;",
        "",
        "final class TestCase04 extends HtmlTemplate {",
        "  @Override",
        "  protected final void definition() {",
        "    html(",
        "      body()",
        "    );",
        "  }",
        "",
        "  /**",
        "",
        "   # Heading 1",
        "",
        "   First paragraph",
        "   */",
        "  @Markdown",
        "  final void article0() {}",
        "}"
      )
    );

    compilation.assertWasSuccessful();

    assertTrue(compilation.containsJavaFile("testing.TestCase04_article0"));

    GeneratedJavaFile markdown;
    markdown = compilation.getJavaFile("testing.TestCase04_article0");

    Util.assertHasLines(
      markdown.contents(),

      "package testing;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import objectos.html.HtmlTemplate;",
      "",
      "@Generated(\"objectos.ssg.processor.MarkdownMethodProcessor\")",
      "class TestCase04_article0 extends HtmlTemplate {",
      "",
      "  @Override",
      "  protected void definition() {",
      "    h1(",
      "        t(\"Heading 1\")",
      "    );",
      "    p(",
      "        t(\"First paragraph\")",
      "    );",
      "  }",
      "",
      "}"
    );
  }

}
