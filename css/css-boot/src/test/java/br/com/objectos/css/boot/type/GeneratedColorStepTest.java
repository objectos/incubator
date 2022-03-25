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
import br.com.objectos.core.map.ImmutableMap;
import br.com.objectos.css.boot.AbstractCssBootTest;
import br.com.objectos.css.boot.spec.CssSpec;
import org.testng.annotations.Test;

public class GeneratedColorStepTest extends AbstractCssBootTest {

  @Test
  public void execute() {
    ImmutableMap<String, JavaFile> javaFiles;
    javaFiles = execute(
        GeneratedColorStep::new,
        new CssSpec() {
          @Override
          protected final void definition() {
            namedColors("transparent", "ButtonText");
          }
        }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
        javaFiles.get("GeneratedColor"),
        "package br.com.objectos.css.type;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.core.map.ImmutableMap;",
        "import br.com.objectos.core.map.MutableMap;",
        "",
        "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
        "abstract class GeneratedColor {",
        "",
        "  public static final ColorName ButtonText = new ColorName(0, \"ButtonText\");",
        "",
        "  public static final ColorName transparent = new ColorName(1, \"transparent\");",
        "",
        "  private static final ColorName[] ARRAY = new ColorName[] {ButtonText, transparent};",
        "",
        "  private static final ImmutableMap<String, ColorName> MAP = buildMap();",
        "",
        "  public static ColorName getByCode(int code) {",
        "    return ARRAY[code];",
        "  }",
        "",
        "  public static ColorName getByName(String name) {",
        "    ColorName c = MAP.get(name);",
        "    if (c == null) {",
        "      throw new IllegalArgumentException(name);",
        "    }",
        "    return c;",
        "  }",
        "",
        "  public static boolean isColor(String name) {",
        "    return MAP.containsKey(name);",
        "  }",
        "",
        "  private static ImmutableMap<String, ColorName> buildMap() {",
        "    MutableMap<String, ColorName> m = MutableMap.create();",
        "    m.put(\"ButtonText\", ButtonText);",
        "    m.put(\"transparent\", transparent);",
        "    return m.toImmutableMap();",
        "  }",
        "",
        "}"
    );
  }

}