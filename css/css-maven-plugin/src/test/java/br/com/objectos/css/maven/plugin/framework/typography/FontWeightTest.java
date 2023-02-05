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
package br.com.objectos.css.maven.plugin.framework.typography;

import static org.testng.Assert.assertEquals;

import br.com.objectos.css.config.framework.AbstractConfiguration;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import br.com.objectos.css.maven.plugin.framework.AbstractCssMavenPluginFrameworkTest;
import org.testng.annotations.Test;

public class FontWeightTest extends AbstractCssMavenPluginFrameworkTest {

  @Test
  public void execute() {
    var javaFiles = executeProperty(
      new AbstractConfiguration() {
        @Override
        protected final void configure() {
          packageName("br.com.objectos.css.framework");

          property(
            FrameworkGroup.TYPOGRAPHY,
            simpleName("FontWeight"),
            methods("fontWeight"),
            valueSet(
              v("normal", 400)
            )
          );
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
      javaFiles.get("FontWeight"),
      "package br.com.objectos.css.framework.typography;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import br.com.objectos.css.Css;",
      "import br.com.objectos.css.select.ClassSelector;",
      "import br.com.objectos.css.sheet.AbstractStyleSheet;",
      "",
      "@Generated(\"br.com.objectos.css.maven.plugin.framework.FrameworkMojo\")",
      "public final class FontWeight extends AbstractStyleSheet {",
      "",
      "  public static final ClassSelector normal = Css.randomDot(5);",
      "",
      "  @Override",
      "  protected final void definition() {",
      "    definition0();",
      "  }",
      "",
      "  private void definition0() {",
      "    style(",
      "        normal,",
      "        fontWeight(400)",
      "    );",
      "  }",
      "",
      "}"
    );
  }

}
