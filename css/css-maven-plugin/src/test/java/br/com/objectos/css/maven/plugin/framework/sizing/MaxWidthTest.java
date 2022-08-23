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
package br.com.objectos.css.maven.plugin.framework.sizing;

import static br.com.objectos.css.property.StandardPropertyName.MIN_WIDTH;
import static br.com.objectos.css.sheet.MediaType.SCREEN;
import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.config.framework.AbstractConfiguration;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkAtMediaSet;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import br.com.objectos.css.keyword.Keywords;
import br.com.objectos.css.maven.plugin.framework.AbstractCssMavenPluginFrameworkTest;
import objectos.util.UnmodifiableMap;
import org.testng.annotations.Test;

public class MaxWidthTest extends AbstractCssMavenPluginFrameworkTest {

  @Test
  public void execute() {
    UnmodifiableMap<String, JavaFile> javaFiles = executeProperty(
      new AbstractConfiguration() {
        @Override
        protected final void configure() {
          packageName("br.com.objectos.css.framework");

          FrameworkAtMediaSet responsive = mediaSet(
            media("sm", SCREEN, declaration(MIN_WIDTH, px(640))),
            media("xl", SCREEN, declaration(MIN_WIDTH, px(1280)))
          );

          property(
            FrameworkGroup.SIZING,
            simpleName("MaxWidth"),
            methods("maxWidth"),
            valueSet(
              v("none", Keywords.none),
              v("full", pct(100)),
              v("screen", responsive)
            )
          );
        }
      }
    );
    assertEquals(javaFiles.size(), 1);
    testLines(
      javaFiles.get("MaxWidth"),
      "package br.com.objectos.css.framework.sizing;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import br.com.objectos.css.Css;",
      "import br.com.objectos.css.keyword.Keywords;",
      "import br.com.objectos.css.select.ClassSelector;",
      "import br.com.objectos.css.sheet.AbstractStyleSheet;",
      "",
      "@Generated(\"br.com.objectos.css.maven.plugin.framework.FrameworkMojo\")",
      "public final class MaxWidth extends AbstractStyleSheet {",
      "",
      "  public static final ClassSelector none = Css.randomDot(5);",
      "",
      "  public static final ClassSelector full = Css.randomDot(5);",
      "",
      "  public static final ClassSelector screenSm = Css.randomDot(5);",
      "",
      "  public static final ClassSelector screenXl = Css.randomDot(5);",
      "",
      "  @Override",
      "  protected final void definition() {",
      "    style(",
      "        none,",
      "        maxWidth(Keywords.none)",
      "    );",
      "    style(",
      "        full,",
      "        maxWidth(pct(100))",
      "    );",
      "    style(",
      "        screenSm,",
      "        maxWidth(px(640))",
      "    );",
      "    style(",
      "        screenXl,",
      "        maxWidth(px(1280))",
      "    );",
      "  }",
      "",
      "}"
    );
  }

}
