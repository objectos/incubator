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
package br.com.objectos.css.boot.property;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.boot.AbstractCssBootTest;
import br.com.objectos.css.boot.keyword.KeywordName;
import br.com.objectos.css.boot.spec.CssSpec;
import br.com.objectos.css.boot.spec.Source;
import objectos.util.UnmodifiableMap;
import org.testng.annotations.Test;

public class StandardPropertyNameStepTest extends AbstractCssBootTest {

  @Test(description = "it should generate an enum constant for each property defined")
  public void execute() {
    UnmodifiableMap<String, JavaFile> javaFiles;
    javaFiles = execute(
      StandardPropertyNameStep::new,
      new CssSpec() {
        @Override
        protected final void definition() {
          KeywordName auto = keyword("auto");
          KeywordName none = keyword("none");

          property(
            "clear",
            formal("", Source.MANUAL_ENTRY),
            sig(t("ClearValue", none), "value")
          );

          property(
            "top",
            formal("", Source.MANUAL_ENTRY),
            sig(t("TopValue", auto), "value")
          );
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
      javaFiles.get("StandardPropertyName"),
      "package br.com.objectos.css.property;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import objectos.util.UnmodifiableMap;",
      "import objectos.util.MutableMap;",
      "",
      "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
      "public enum StandardPropertyName implements PropertyName {",
      "",
      "  CLEAR(\"clear\", \"clear\"),",
      "",
      "  TOP(\"top\", \"top\");",
      "",
      "  private static final StandardPropertyName[] ARRAY = StandardPropertyName.values();",
      "",
      "  private static final UnmodifiableMap<String, StandardPropertyName> MAP = buildMap();",
      "",
      "  private final String javaName;",
      "",
      "  private final String name;",
      "",
      "  private StandardPropertyName(String javaName, String name) {",
      "    this.javaName = javaName;",
      "    this.name = name;",
      "  }",
      "",
      "  public static StandardPropertyName getByCode(int code) {",
      "    return ARRAY[code];",
      "  }",
      "",
      "  public static StandardPropertyName getByName(String name) {",
      "    return MAP.get(name);",
      "  }",
      "",
      "  private static UnmodifiableMap<String, StandardPropertyName> buildMap() {",
      "    MutableMap<String, StandardPropertyName> m = new MutableMap<>();",
      "    m.put(\"clear\", CLEAR);",
      "    m.put(\"top\", TOP);",
      "    return m.toUnmodifiableMap();",
      "  }",
      "",
      "  public static int size() {",
      "    return ARRAY.length;",
      "  }",
      "",
      "  @Override",
      "  public final int getCode() {",
      "    return ordinal();",
      "  }",
      "",
      "  public final String getJavaName() {",
      "    return javaName;",
      "  }",
      "",
      "  @Override",
      "  public final String getName() {",
      "    return name;",
      "  }",
      "",
      "}"
    );
  }

}
