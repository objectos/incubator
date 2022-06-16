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
import objectos.util.UnmodifiableMap;
import org.testng.annotations.Test;

public class LengthUnitStepTest extends AbstractCssBootTest {

  @Test(description = ""
      + "it should generate an enum constant for each unit.")
  public void execute() {
    UnmodifiableMap<String, JavaFile> javaFiles;
    javaFiles = execute(
        LengthUnitStep::new,
        new CssSpec() {
          @Override
          protected final void definition() {
            lengthUnits("px", "em");
          }
        }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
        javaFiles.get("LengthUnit"),
        "package br.com.objectos.css.type;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import java.util.Locale;",
        "",
        "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
        "public enum LengthUnit {",
        "",
        "  EM,",
        "",
        "  PX;",
        "",
        "  private static final LengthUnit[] ARRAY = LengthUnit.values();",
        "",
        "  private final String name;",
        "",
        "  private LengthUnit() {",
        "    this.name = name().toLowerCase(Locale.US);",
        "  }",
        "",
        "  public static LengthUnit getByCode(int code) {",
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
