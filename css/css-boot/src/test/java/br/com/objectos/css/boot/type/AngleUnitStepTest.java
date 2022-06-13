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
package br.com.objectos.css.boot.type;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.boot.AbstractCssBootTest;
import br.com.objectos.css.boot.spec.CssSpec;
import objectos.util.ImmutableMap;
import org.testng.annotations.Test;

public class AngleUnitStepTest extends AbstractCssBootTest {

  @Test(description = ""
      + "it should generate an enum constant for each unit.")
  public void execute() {
    ImmutableMap<String, JavaFile> javaFiles;
    javaFiles = execute(
        AngleUnitStep::new,
        new CssSpec() {
          @Override
          protected final void definition() {
            angleUnits("deg", "turn");
          }
        }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
        javaFiles.get("AngleUnit"),
        "package br.com.objectos.css.type;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import java.util.Locale;",
        "",
        "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
        "public enum AngleUnit {",
        "",
        "  DEG,",
        "",
        "  TURN;",
        "",
        "  private static final AngleUnit[] ARRAY = AngleUnit.values();",
        "",
        "  private final String name;",
        "",
        "  private AngleUnit() {",
        "    this.name = name().toLowerCase(Locale.US);",
        "  }",
        "",
        "  public static AngleUnit getByCode(int code) {",
        "    return ARRAY[code];",
        "  }",
        "",
        "  public static int size() {",
        "    return ARRAY.length;",
        "  }",
        "",
        "  public final int getCode() {",
        "    return ordinal();",
        "  }",
        "",
        "  public final String getName() {",
        "    return name;",
        "  }",
        "",
        "}"
    );
  }

}
