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
package br.com.objectos.be.processor;

import static br.com.objectos.tools.Tools.compilationUnit;
import static br.com.objectos.tools.Tools.javac;
import static br.com.objectos.tools.Tools.processor;
import static org.testng.Assert.assertTrue;

import br.com.objectos.tools.Compilation;
import br.com.objectos.tools.GeneratedJavaFile;
import org.testng.annotations.Test;

public class BeTest {

  @Test
  public void testCase01() {
    Compilation compilation;
    compilation = javac(
        processor(new BeProcessor()),

        compilationUnit(
            "package testing;",
            "",
            "import br.com.objectos.be.annotations.Be;",
            "import br.com.objectos.html.tmpl.AbstractTemplate;",
            "",
            "@Be",
            "abstract class TestCase01 extends AbstractTemplate {",
            "  @Override protected final void definition() {}",
            "}"
        )
    );

    assertTrue(compilation.wasSuccessful());
    assertTrue(compilation.containsJavaFile("testing.TestCase01Html"));
  }

  @Test
  public void testCase03() {
    Compilation compilation;
    compilation = javac(
        processor(new BeDirectoryProcessor()),
        processor(new BeProcessor()),

        compilationUnit(
            "@br.com.objectos.be.annotations.BeDirectory({",
            "    TestCase03Html.class",
            "})",
            "package testing;"
        ),

        compilationUnit(
            "package testing;",
            "",
            "import br.com.objectos.be.annotations.Be;",
            "import br.com.objectos.be.annotations.Markdown;",
            "import br.com.objectos.html.tmpl.AbstractTemplate;",
            "",
            "@Be",
            "abstract class TestCase03 extends AbstractTemplate {",
            "  @Override",
            "  protected final void definition() {",
            "    html(",
            "      body(",
            "        f(this::body0)",
            "      )",
            "    );",
            "  }",
            "",
            "  /**",
            "",
            "   # Heading 1",
            "",
            "   First paragraph",
            "",
            "   ## Heading 2",
            "",
            "   This is the second",
            "   paragraph",
            "",
            "   Click [here](https://example.com)",
            "   for something",
            "   */",
            "  @Markdown",
            "  abstract void body0();",
            "}"
        )
    );

    compilation.assertWasSuccessful();

    assertTrue(compilation.containsJavaFile("testing.TestCase03Html"));

    assertTrue(compilation.containsJavaFile("testing.TestCase03Html"));

    GeneratedJavaFile markdown;
    markdown = compilation.getJavaFile("testing.MarkdownTestCase03");

    Util.assertHasLines(
        markdown.contents(),

        "package testing;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeProcessor\")",
        "class MarkdownTestCase03 extends TestCase03 {",
        "",
        "  @Override",
        "  final void body0() {",
        "    h1(",
        "        t(\"Heading 1\")",
        "    );",
        "    p(",
        "        t(\"First paragraph\")",
        "    );",
        "    h2(",
        "        t(\"Heading 2\")",
        "    );",
        "    p(",
        "        t(\"This is the second\"), t(\"\\n\"),",
        "        t(\"paragraph\")",
        "    );",
        "    p(",
        "        t(\"Click \"),",
        "        a(",
        "            href(\"https://example.com\"),",
        "            t(\"here\")",
        "        ), t(\"\\n\"),",
        "        t(\"for something\")",
        "    );",
        "  }",
        "",
        "}"
    );

    GeneratedJavaFile directory;
    directory = compilation.getJavaFile("testing.TestingDirectory");

    Util.assertHasLines(
        directory.contents(),

        "package testing;",
        "",
        "import br.com.objectos.be.site.AbstractDirectory;",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.be.processor.BeDirectoryProcessor\")",
        "public class TestingDirectory extends AbstractDirectory {",
        "",
        "  public TestingDirectory() {}",
        "",
        "  @Override",
        "  protected final void configure() {",
        "    addTemplate(new MarkdownTestCase03Impl());",
        "  }",
        "",
        "  private class MarkdownTestCase03Impl extends MarkdownTestCase03 {}",
        "",
        "}"
    );
  }

  @Test
  public void testCase04() {
    Compilation compilation;
    compilation = javac(
        processor(new MarkdownMethodProcessor()),

        compilationUnit(
            "package testing;",
            "",
            "import br.com.objectos.be.annotations.Markdown;",
            "import br.com.objectos.html.tmpl.AbstractTemplate;",
            "",
            "final class TestCase04 extends AbstractTemplate {",
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
        "import br.com.objectos.html.tmpl.AbstractFragment;",
        "",
        "@Generated(\"br.com.objectos.be.processor.MarkdownMethodProcessor\")",
        "class TestCase04_article0 extends AbstractFragment {",
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
