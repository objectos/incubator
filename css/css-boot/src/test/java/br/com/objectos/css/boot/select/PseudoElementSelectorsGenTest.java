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
import br.com.objectos.core.map.ImmutableMap;
import br.com.objectos.css.boot.AbstractCssBootTest;
import br.com.objectos.css.boot.spec.CssSpec;
import org.testng.annotations.Test;

public class PseudoElementSelectorsGenTest extends AbstractCssBootTest {

  @Test
  public void generateJavaFile() {
    ImmutableMap<String, JavaFile> javaFiles;
    javaFiles = execute(
        PseudoElementSelectorsGen::new,
        new CssSpec() {
          @Override
          protected final void definition() {
            pseudoElements(
                "after",
                "-moz-focus-inner"
            );
          }
        }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
        javaFiles.get("PseudoElementSelectors"),
        "package br.com.objectos.css.select;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.code.annotations.Ignore;",
        "import br.com.objectos.core.map.ImmutableMap;",
        "import br.com.objectos.core.map.MutableMap;",
        "",
        "@Generated(\"br.com.objectos.css.boot.select.PseudoElementSelectorsGen\")",
        "public final class PseudoElementSelectors {",
        "",
        "  public static final PseudoElementSelector AFTER = new PseudoElementSelector(0, \"after\");",
        "",
        "  public static final PseudoElementSelector _MOZ_FOCUS_INNER = new PseudoElementSelector(1, \"-moz-focus-inner\");",
        "",
        "  private static final PseudoElementSelector[] ARRAY = new PseudoElementSelector[] {AFTER, _MOZ_FOCUS_INNER};",
        "",
        "  private static final ImmutableMap<String, PseudoElementSelector> MAP = buildMap();",
        "",
        "  private PseudoElementSelectors() {}",
        "",
        "  @Ignore",
        "  public static PseudoElementSelector getByCode(int code) {",
        "    return ARRAY[code];",
        "  }",
        "",
        "  @Ignore",
        "  public static PseudoElementSelector getByName(String name) {",
        "    return MAP.get(name);",
        "  }",
        "",
        "  private static ImmutableMap<String, PseudoElementSelector> buildMap() {",
        "    MutableMap<String, PseudoElementSelector> m = MutableMap.create();",
        "    m.put(\"after\", AFTER);",
        "    m.put(\"-moz-focus-inner\", _MOZ_FOCUS_INNER);",
        "    return m.toImmutableMap();",
        "  }",
        "",
        "}"
    );
  }

}