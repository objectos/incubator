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
package br.com.objectos.css.maven.plugin.framework.sizing;

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.maven.plugin.framework.AbstractCssMavenPluginFrameworkTest;
import objectos.css.config.framework.AbstractConfiguration;
import objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import objectos.css.config.framework.ConfigurationDsl.FrameworkNamedValueSet;
import objectos.css.type.Zero;
import org.testng.annotations.Test;

public class HeightTest extends AbstractCssMavenPluginFrameworkTest {

  @Test
  public void execute() {
    var javaFiles = executeProperty(
      new AbstractConfiguration() {
        @Override
        protected final void configure() {
          packageName("br.com.objectos.css.framework");

          FrameworkNamedValueSet spacing = valueSet(
            v("0", Zero.INSTANCE),
            v("2", rem(0.5)),
            v("8", rem(2))
          );

          property(
            FrameworkGroup.SIZING,
            simpleName("Height"),
            methods("height"),
            valueSet(
              spacing,
              v("full", pct(100))
            )
          );
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
      javaFiles.get("Height"),

      "package br.com.objectos.css.framework.sizing;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import objectos.css.Css;",
      "import objectos.css.select.ClassSelector;",
      "import objectos.css.sheet.AbstractStyleSheet;",
      "",
      "@Generated(\"br.com.objectos.css.maven.plugin.framework.FrameworkMojo\")",
      "public final class Height extends AbstractStyleSheet {",
      "",
      "  public static final ClassSelector v0 = Css.randomDot(5);",
      "",
      "  public static final ClassSelector v2 = Css.randomDot(5);",
      "",
      "  public static final ClassSelector v8 = Css.randomDot(5);",
      "",
      "  public static final ClassSelector full = Css.randomDot(5);",
      "",
      "  @Override",
      "  protected final void definition() {",
      "    definition0();",
      "  }",
      "",
      "  private void definition0() {",
      "    style(",
      "        v0,",
      "        height(zero())",
      "    );",
      "    style(",
      "        v2,",
      "        height(rem(0.5))",
      "    );",
      "    style(",
      "        v8,",
      "        height(rem(2))",
      "    );",
      "    style(",
      "        full,",
      "        height(pct(100))",
      "    );",
      "  }",
      "",
      "}"
    );
  }

}
