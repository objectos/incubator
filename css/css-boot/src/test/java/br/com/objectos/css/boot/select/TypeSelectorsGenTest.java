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
package br.com.objectos.css.boot.select;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.boot.AbstractCssBootTest;
import br.com.objectos.css.boot.spec.CssSpec;
import objectos.util.UnmodifiableMap;
import org.testng.annotations.Test;

public class TypeSelectorsGenTest extends AbstractCssBootTest {

  @Test
  public void generateJavaFile() {
    UnmodifiableMap<String, JavaFile> javaFiles;
    javaFiles = execute(
      TypeSelectorsGen::new,
      new CssSpec() {
        @Override
        protected final void definition() {
          elementName("a");
          elementName("div");
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
      javaFiles.get("TypeSelectors"),
      "package br.com.objectos.css.select;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import br.com.objectos.code.annotations.Ignore;",
      "import objectos.util.GrowableMap;",
      "import objectos.util.UnmodifiableMap;",
      "",
      "@Generated(\"br.com.objectos.css.boot.select.TypeSelectorsGen\")",
      "public final class TypeSelectors {",
      "",
      "  public static final TypeSelector a = new TypeSelector(0, \"a\");",
      "",
      "  public static final TypeSelector div = new TypeSelector(1, \"div\");",
      "",
      "  private static final TypeSelector[] ARRAY = new TypeSelector[] {a, div};",
      "",
      "  private static final UnmodifiableMap<String, TypeSelector> MAP = buildMap();",
      "",
      "  private TypeSelectors() {}",
      "",
      "  @Ignore",
      "  public static TypeSelector getByCode(int code) {",
      "    return ARRAY[code];",
      "  }",
      "",
      "  @Ignore",
      "  public static TypeSelector getByName(String name) {",
      "    return MAP.get(name);",
      "  }",
      "",
      "  private static UnmodifiableMap<String, TypeSelector> buildMap() {",
      "    GrowableMap<String, TypeSelector> m = new GrowableMap<>();",
      "    m.put(\"a\", a);",
      "    m.put(\"div\", div);",
      "    return m.toUnmodifiableMap();",
      "  }",
      "",
      "}"
    );
  }

}
