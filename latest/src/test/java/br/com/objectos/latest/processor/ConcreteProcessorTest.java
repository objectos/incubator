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

public class ConcreteProcessorTest extends AbstractMultiReleaseProcessorTest {

  @Test
  public void testCase0() throws IOException {
    process(
        new ConcreteClassProcessor(),
        new StringJavaFileObject(
            "objectos.testing.DirectoryJava6",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete.Bridge "
                + "abstract class DirectoryJava6 extends AbstractDirectory {"
                + ""
                + "  @Concrete.Constructor"
                + "  DirectoryJava6(int foo) { super(foo); }"
                + "}"
        ),
        new StringJavaFileObject(
            "objectos.testing.DirectoryJava7",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete.Bridge "
                + "abstract class DirectoryJava7 extends AbstractDirectory {"
                + "  DirectoryJava7() {}"
                + ""
                + "  @Concrete.Constructor"
                + "  DirectoryJava7(int foo) { super(foo); }"
                + "}"
        ),
        new StringJavaFileObject(
            "objectos.testing.DirectoryJava8",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "// not a Bridge\n"
                + "abstract class DirectoryJava8 extends AbstractDirectory {"
                + "  @Concrete.Constructor"
                + "  DirectoryJava8(int foo) { super(foo); }"
                + "}"
        ),
        new StringJavaFileObject(
            "objectos.testing.AbstractDirectory",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete(modifiers = \"public final\", simpleName = \"Directory\")"
                + "abstract class AbstractDirectory {"
                + "  AbstractDirectory() {}"
                + ""
                + "  AbstractDirectory(int test) {}"
                + ""
                + "  void param(Directory dir) {}"
                + "}"
        )
    );

    JavaFileObject factory;
    factory = getGeneratedJavaFile("objectos.testing.Directory");

    assertNotNull(factory);

    testToString(
        factory,
        "package objectos.testing;",
        "",
        "import br.com.objectos.latest.Generated;",
        "",
        "@Generated(\"br.com.objectos.latest.processor.ConcreteClassProcessor\")",
        "public final class Directory extends DirectoryJava7 {",
        "",
        "  Directory(int foo) {",
        "    super(foo);",
        "  }",
        "",
        "}"
    );
  }

  @Test
  public void testCase1() {
    process(
        new ConcreteInterfaceProcessor(),
        new StringJavaFileObject(
            "objectos.testing.FunctionJava6",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete.Bridge "
                + "interface FunctionJava6<R, T> extends AbstractFunction<R, T> {}"
        ),
        new StringJavaFileObject(
            "objectos.testing.FunctionJava8",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete.Bridge(annotations = {\"@java.lang.SuppressWarnings({})\"})"
                + "interface FunctionJava8<R, T> extends AbstractFunction<R, T> {}"
        ),
        new StringJavaFileObject(
            "objectos.testing.AbstractFunction",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete(modifiers = \"public\", simpleName = \"Function\")"
                + "interface AbstractFunction<R, T> {"
                + "  R apply(T t);"
                + "}"
        )
    );

    JavaFileObject result;
    result = getGeneratedJavaFile("objectos.testing.Function");

    assertNotNull(result);

    testToString(
        result,
        "package objectos.testing;",
        "",
        "import br.com.objectos.latest.Generated;",
        "",
        "@Generated(\"br.com.objectos.latest.processor.ConcreteInterfaceProcessor\")",
        "@java.lang.SuppressWarnings({})",
        "public interface Function<R, T> extends FunctionJava8<R, T> {}"
    );
  }

  @Test
  public void testCase2() {
    process(
        new ConcreteClassProcessor(),
        new StringJavaFileObject(
            "objectos.testing.Foo6",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete.Bridge "
                + "class Foo6 extends AbstractFoo {}"
        ),
        new StringJavaFileObject(
            "objectos.testing.Foo11",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete.Bridge "
                + "class Foo11 extends Foo6 {}"
        ),
        new StringJavaFileObject(
            "objectos.testing.Foo17",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete.Bridge "
                + "class Foo17 extends Foo11 {}"
        ),
        new StringJavaFileObject(
            "objectos.testing.AbstractFoo",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete(modifiers = \"public\", simpleName = \"Foo\")"
                + "abstract class AbstractFoo {}"
        )
    );

    JavaFileObject result;
    result = getGeneratedJavaFile("objectos.testing.Foo");

    assertNotNull(result);

    testToString(
        result,
        "package objectos.testing;",
        "",
        "import br.com.objectos.latest.Generated;",
        "",
        "@Generated(\"br.com.objectos.latest.processor.ConcreteClassProcessor\")",
        "public class Foo extends Foo17 {}"
    );
  }

  @Test(description = ""
      + "it should incorporate the Javadoc comment of the @Concrete annotation declaration")
  public void testCase3() {
    process(
        new ConcreteClassProcessor(),
        new StringJavaFileObject(
            "objectos.testing.JavadocJava6",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete.Bridge "
                + "abstract class JavadocJava6 extends AbstractJavadoc {"
                + "  /**\n"
                + "   * Java 6 constructor\n"
                + "   */\n"
                + "  @Concrete.Constructor"
                + "  public JavadocJava6() {}"
                + "}"
        ),
        new StringJavaFileObject(
            "objectos.testing.JavadocJava7",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;"
                + "@Concrete.Bridge "
                + "abstract class JavadocJava7 extends AbstractJavadoc {"
                + "  /**\n"
                + "   * Java 7 constructor\n"
                + "   */\n"
                + "  @Concrete.Constructor"
                + "  public JavadocJava7() {}"
                + "}"
        ),
        new StringJavaFileObject(
            "objectos.testing.AbstractJavadoc",
            "package objectos.testing;"
                + "import br.com.objectos.latest.Concrete;\n"
                + "/**\n"
                + " * This is a Javadoc test.\n"
                + " * <p>\n"
                + " * Paragraph\n"
                + " * @since 0.1\n"
                + " */\n"
                + "@Concrete(modifiers = \"public final\", simpleName = \"Javadoc\")"
                + "abstract class AbstractJavadoc {"
                + "}"
        )
    );

    JavaFileObject factory;
    factory = getGeneratedJavaFile("objectos.testing.Javadoc");

    assertNotNull(factory);

    testToString(
        factory,
        "package objectos.testing;",
        "",
        "import br.com.objectos.latest.Generated;",
        "",
        "/**",
        " * This is a Javadoc test.",
        " * <p>",
        " * Paragraph",
        " * @since 0.1",
        " */",
        "@Generated(\"br.com.objectos.latest.processor.ConcreteClassProcessor\")",
        "public final class Javadoc extends JavadocJava7 {",
        "",
        "  /**",
        "   * Java 7 constructor",
        "   */",
        "  public Javadoc() {",
        "    super();",
        "  }",
        "",
        "}"
    );
  }

}
