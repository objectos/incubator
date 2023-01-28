/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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
package br.com.objectos.code.testing;

import static br.com.objectos.code.java.declaration.InterfaceCode._interface;
import static br.com.objectos.code.java.declaration.PackageName._package;
import static br.com.objectos.code.java.expression.Expressions.id;
import static br.com.objectos.code.java.io.JavaFile.javaFile;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import br.com.objectos.code.java.declaration.PackageName;
import java.util.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InMemoryJavaFileConsumerTest {

  private static final PackageName TESTING_A = _package("testing.a");

  private static final PackageName TESTING_B = _package("testing.b");

  private InMemoryJavaFileConsumer round;

  @BeforeClass
  public void _beforeClass() {
    round = new InMemoryJavaFileConsumer();
    round.acceptJavaFile(
        javaFile(
            TESTING_A,
            _interface(id("IfaceA"))
        )
    );
    round.acceptJavaFile(
        javaFile(
            TESTING_B,
            _interface(id("IfaceB"))
        )
    );
  }

  @Test
  public void contains() {
    assertTrue(round.contains(TESTING_A.nestedClass("IfaceA")));
    assertTrue(round.contains(TESTING_B.nestedClass("IfaceB")));
    assertFalse(round.contains(TESTING_A.nestedClass("Foo")));
    assertFalse(round.contains(TESTING_B.nestedClass("Foo")));
  }

  @Test
  public void get() {
    assertHasLines(
        round.get(TESTING_A.nestedClass("IfaceA")).toString(),
        "package testing.a;",
        "",
        "interface IfaceA {}"
    );

    try {
      round.get("x");

      Assert.fail();
    } catch (NoSuchElementException expected) {
      assertEquals(expected.getMessage(), "x");
    }
  }

  @Test
  public void size() {
    assertEquals(round.size(), 2);
  }

  private void assertHasLines(String string, String... expected) {
    String[] parts;
    parts = string.split("\n");

    assertEquals(parts, expected);
  }

}
