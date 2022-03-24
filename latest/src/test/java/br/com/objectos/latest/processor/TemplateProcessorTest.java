/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.latest.processor;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import javax.tools.JavaFileObject;
import org.testng.annotations.Test;

public class TemplateProcessorTest extends AbstractMultiReleaseProcessorTest {

  @Test
  public void testCase00() throws IOException {
    process(
        new TemplateProcessor(),

        javaFileObject(
            "objectos.testing.TestJavaAny",

            "package objectos.testing;",
            "import br.com.objectos.latest.Template;",
            "/**",
            " * A TemplateProcessor test.",
            " */",
            "@Template(simpleName = \"Test\", extendsClause = \"java.util.Collection<E>, java.util.RandomAccess\")",
            "interface TestJavaAny<E> {",
            "  /**",
            "   * Method A",
            "   */",
            "  void a();",
            "}"
        ),

        javaFileObject(
            "objectos.testing.TestJava6",

            "package objectos.testing;",
            "import br.com.objectos.latest.Template;",
            "@Template.Bridge ",
            "interface TestJava6<E> extends TestJavaAny<E> {}"
        ),

        javaFileObject(
            "objectos.testing.TestJava8",

            "package objectos.testing;",
            "import br.com.objectos.latest.Template;",
            "@Template.Bridge ",
            "interface TestJava8<E> extends TestJavaAny<E> {",
            "  /**",
            "   * Method B",
            "   */",
            "  void b();",
            "",
            "  boolean c(java.util.Collection<? extends E> c);",
            "",
            "  boolean d(Object first, Object... more);",
            "",
            "  E e() throws java.lang.IllegalStateException;",
            "}"
        )
    );

    JavaFileObject factory;
    factory = getGeneratedJavaFile("objectos.testing.Test");

    assertNotNull(factory);

    testToString(
        factory,
        "package objectos.testing;",
        "",
        "import br.com.objectos.latest.Generated;",
        "",
        "/**",
        " * A TemplateProcessor test.",
        " */",
        "@Generated(\"br.com.objectos.latest.processor.TemplateProcessor\")",
        "public interface Test<E> extends java.util.Collection<E>, java.util.RandomAccess {",
        "",
        "  /**",
        "   * Method A",
        "   */",
        "  void a();",
        "",
        "  /**",
        "   * Method B",
        "   */",
        "  void b();",
        "",
        "  boolean c(java.util.Collection<? extends E> c);",
        "",
        "  boolean d(java.lang.Object first, java.lang.Object... more);",
        "",
        "  E e() throws java.lang.IllegalStateException;",
        "",
        "}"
    );
  }

}