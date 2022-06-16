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
package br.com.objectos.css.boot.keyword;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.boot.AbstractCssBootTest;
import br.com.objectos.css.boot.spec.CssSpec;
import objectos.util.UnmodifiableMap;
import org.testng.annotations.Test;

public class KeywordsClassStepTest extends AbstractCssBootTest {

  @Test(description = "Keywords class must declared each keyword defined (sorted alpha)")
  public void execute() {
    UnmodifiableMap<String, JavaFile> javaFiles;
    javaFiles = execute(
      KeywordsClassStep::new,
      new CssSpec() {
        @Override
        protected final void definition() {
          elementName("a");
          elementName("div");

          keyword("none");
          keyword("auto");
          keyword("double");
          keyword("div");
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
      javaFiles.get("Keywords"),
      "package br.com.objectos.css.keyword;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import objectos.util.MutableMap;",
      "import objectos.util.UnmodifiableMap;",
      "",
      "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
      "public final class Keywords {",
      "",
      "  public static final AutoKeyword auto = AutoKeyword.INSTANCE;",
      "",
      "  public static final DivKeyword divKw = DivKeyword.INSTANCE;",
      "",
      "  public static final DoubleKeyword doubleKw = DoubleKeyword.INSTANCE;",
      "",
      "  public static final NoneKeyword none = NoneKeyword.INSTANCE;",
      "",
      "  private static final StandardKeyword[] ARRAY = new StandardKeyword[] {auto, divKw, doubleKw, none};",
      "",
      "  private static final UnmodifiableMap<String, StandardKeyword> MAP = buildMap();",
      "",
      "  private Keywords() {}",
      "",
      "  public static StandardKeyword getByCode(int code) {",
      "    return ARRAY[code];",
      "  }",
      "",
      "  public static StandardKeyword getByName(String name) {",
      "    StandardKeyword k = MAP.get(name);",
      "    if (k == null) {",
      "      throw new IllegalArgumentException(name);",
      "    }",
      "    return k;",
      "  }",
      "",
      "  public static boolean isKeyword(String name) {",
      "    return MAP.containsKey(name);",
      "  }",
      "",
      "  private static UnmodifiableMap<String, StandardKeyword> buildMap() {",
      "    MutableMap<String, StandardKeyword> m = new MutableMap<>();",
      "    m.put(\"auto\", auto);",
      "    m.put(\"div\", divKw);",
      "    m.put(\"double\", doubleKw);",
      "    m.put(\"none\", none);",
      "    return m.toUnmodifiableMap();",
      "  }",
      "",
      "}"
    );
  }

}
