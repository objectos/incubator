/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.boot;

import static br.com.objectos.tools.Tools.compilationUnit;
import static br.com.objectos.tools.Tools.javac;
import static br.com.objectos.tools.Tools.patchModuleWithTestClasses;
import static br.com.objectos.tools.Tools.processor;
import static org.testng.Assert.assertEquals;

import br.com.objectos.tools.Compilation;
import org.testng.annotations.Test;

public class StaticFactoryAggregateGeneratorTest extends AbstractCodeBootTest {

  @Test
  public void testCase00() {
    Compilation compilation = javac(
        processor(new StaticFactoryAggregateGenerator()),
        patchModuleWithTestClasses("br.com.objectos.code.boot"),
        compilationUnit(
            "@br.com.objectos.code.annotations.GenerateStaticFactoryAggregate(",
            "  simpleName = \"CodeJava\",",
            "  factories = {",
            "      br.com.objectos.code.boot.AbstractCodeBootTest.Expressions.class,",
            "      br.com.objectos.code.boot.AbstractCodeBootTest.Fields.class,",
            "      br.com.objectos.code.boot.TypeNames.class",
            "",
            "  }",
            ")",
            "package br.com.objectos.code.boot.testing;"
        )
    );

    compilation.assertWasSuccessful();

    assertHasLines(
        compilation.getJavaFile("br.com.objectos.code.boot.testing.CodeJava").contents(),
        "package br.com.objectos.code.boot.testing;",
        "",
        "import br.com.objectos.code.boot.AbstractCodeBootTest.Expressions;",
        "import br.com.objectos.code.boot.AbstractCodeBootTest.Fields;",
        "import br.com.objectos.code.boot.TypeNames;",
        "import br.com.objectos.code.java.expression.ArrayInitializer;",
        "import br.com.objectos.code.java.expression.Identifier;",
        "import br.com.objectos.code.java.type.NamedClass;",
        "",
        "public final class CodeJava {",
        "",
        "  public static final String FIELD_TEST = Fields.FIELD_TEST;",
        "",
        "  private CodeJava() {}",
        "",
        "  public static ArrayInitializer a() {",
        "    return Expressions.a();",
        "  }",
        "",
        "  public static Identifier id(String name) {",
        "    return Expressions.id(name);",
        "  }",
        "",
        "  public static NamedClass cn(Class<?> type) {",
        "    return TypeNames.cn(type);",
        "  }",
        "",
        "}"
    );
  }

  @Test
  public void testCase01() {
    Compilation compilation = javac(
        processor(new StaticFactoryAggregateGenerator()),
        patchModuleWithTestClasses("br.com.objectos.code.boot"),
        compilationUnit(
            "@br.com.objectos.code.annotations.GenerateStaticFactoryAggregate(",
            "  simpleName = \"TestCase01\",",
            "  factories = {",
            "      br.com.objectos.code.boot.TestCase01Factory.class",
            "  }",
            ")",
            "package br.com.objectos.code.boot.testing;"
        )
    );

    compilation.assertWasSuccessful();

    assertHasLines(
        compilation.getJavaFile("br.com.objectos.code.boot.testing.TestCase01").contents(),
        "package br.com.objectos.code.boot.testing;",
        "",
        "import br.com.objectos.code.boot.TestCase01Factory;",
        "import java.io.IOException;",
        "",
        "public final class TestCase01 {",
        "",
        "  private TestCase01() {}",
        "",
        "  public static void m1() throws IOException {",
        "    TestCase01Factory.m1();",
        "  }",
        "",
        "}"
    );

  }

  private void assertHasLines(String contents, String... expected) {
    String[] parts;
    parts = contents.split("\n");

    assertEquals(parts, expected);
  }

}
