/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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

public class PseudoClassSelectorsGenTest extends AbstractCssBootTest {

  @Test
  public void generateJavaFile() {
    UnmodifiableMap<String, JavaFile> javaFiles;
    javaFiles = execute(
      PseudoClassSelectorsGen::new,
      new CssSpec() {
        @Override
        protected final void definition() {
          pseudoClasses(
            "checked",
            "-moz-focusring"
          );
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
      javaFiles.get("PseudoClassSelectors"),
      "package br.com.objectos.css.select;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import br.com.objectos.code.annotations.Ignore;",
      "import objectos.util.GrowableMap;",
      "import objectos.util.UnmodifiableMap;",
      "",
      "@Generated(\"br.com.objectos.css.boot.select.PseudoClassSelectorsGen\")",
      "public final class PseudoClassSelectors {",
      "",
      "  public static final PseudoClassSelector CHECKED = new PseudoClassSelector(0, \"checked\");",
      "",
      "  public static final PseudoClassSelector _MOZ_FOCUSRING = new PseudoClassSelector(1, \"-moz-focusring\");",
      "",
      "  private static final PseudoClassSelector[] ARRAY = new PseudoClassSelector[] {CHECKED, _MOZ_FOCUSRING};",
      "",
      "  private static final UnmodifiableMap<String, PseudoClassSelector> MAP = buildMap();",
      "",
      "  private PseudoClassSelectors() {}",
      "",
      "  @Ignore",
      "  public static PseudoClassSelector getByCode(int code) {",
      "    return ARRAY[code];",
      "  }",
      "",
      "  @Ignore",
      "  public static PseudoClassSelector getByName(String name) {",
      "    return MAP.get(name);",
      "  }",
      "",
      "  private static UnmodifiableMap<String, PseudoClassSelector> buildMap() {",
      "    GrowableMap<String, PseudoClassSelector> m = new GrowableMap<>();",
      "    m.put(\"checked\", CHECKED);",
      "    m.put(\"-moz-focusring\", _MOZ_FOCUSRING);",
      "    return m.toUnmodifiableMap();",
      "  }",
      "",
      "}"
    );
  }

}
