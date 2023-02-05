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
package br.com.objectos.css.maven.plugin.framework.background;

import static br.com.objectos.css.property.StandardPropertyName.MIN_WIDTH;
import static br.com.objectos.css.sheet.MediaType.SCREEN;
import static org.testng.Assert.assertEquals;

import br.com.objectos.css.config.framework.AbstractConfiguration;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkAtMediaSet;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkNamedValueSet;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkPropertyState;
import br.com.objectos.css.maven.plugin.framework.AbstractCssMavenPluginFrameworkTest;
import br.com.objectos.css.type.Color;
import org.testng.annotations.Test;

public class BackgroundColorTest extends AbstractCssMavenPluginFrameworkTest {

  @Test
  public void execute() {
    var javaFiles = executeProperty(
      new AbstractConfiguration() {
        @Override
        protected final void configure() {
          packageName("br.com.objectos.css.framework");

          FrameworkAtMediaSet responsive = mediaSet(
            media("md", SCREEN, declaration(MIN_WIDTH, px(768)))
          );

          FrameworkNamedValueSet colors = valueSet(
            v("transparent", Color.transparent),
            v("black", rgb(0x0)),
            v("white", rgb(0xffffff)),
            v("blue-400", rgb(0x63b3ed))
          );

          property(
            FrameworkGroup.BACKGROUND,
            simpleName("BackgroundColor"),
            methods("backgroundColor"),
            colors,
            responsive,
            FrameworkPropertyState.HOVER
          );
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
      javaFiles.get("BackgroundColor"),

      "package br.com.objectos.css.framework.background;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import br.com.objectos.css.Css;",
      "import br.com.objectos.css.select.ClassSelector;",
      "import br.com.objectos.css.sheet.AbstractStyleSheet;",
      "import br.com.objectos.css.type.Color;",
      "",
      "@Generated(\"br.com.objectos.css.maven.plugin.framework.FrameworkMojo\")",
      "public final class BackgroundColor extends AbstractStyleSheet {",
      "",
      "  public static final ClassSelector transparent = Css.randomDot(5);",
      "",
      "  public static final ClassSelector black = Css.randomDot(5);",
      "",
      "  public static final ClassSelector white = Css.randomDot(5);",
      "",
      "  public static final ClassSelector blue400 = Css.randomDot(5);",
      "",
      "  @Override",
      "  protected final void definition() {",
      "    definition0();",
      "    definition1();",
      "    definition2();",
      "  }",
      "",
      "  private void definition0() {",
      "    style(",
      "        transparent,",
      "        backgroundColor(Color.transparent)",
      "    );",
      "    style(",
      "        black,",
      "        backgroundColor(hex(\"#000000\"))",
      "    );",
      "    style(",
      "        white,",
      "        backgroundColor(hex(\"#ffffff\"))",
      "    );",
      "    style(",
      "        blue400,",
      "        backgroundColor(hex(\"#63b3ed\"))",
      "    );",
      "  }",
      "",
      "  private void definition1() {",
      "    style(",
      "        hover.transparent, HOVER,",
      "        backgroundColor(Color.transparent)",
      "    );",
      "    style(",
      "        hover.black, HOVER,",
      "        backgroundColor(hex(\"#000000\"))",
      "    );",
      "    style(",
      "        hover.white, HOVER,",
      "        backgroundColor(hex(\"#ffffff\"))",
      "    );",
      "    style(",
      "        hover.blue400, HOVER,",
      "        backgroundColor(hex(\"#63b3ed\"))",
      "    );",
      "  }",
      "",
      "  private void definition2() {",
      "    media(",
      "        AbstractStyleSheet.screen, minWidth(px(768)),",
      "",
      "        style(",
      "            md.transparent,",
      "            backgroundColor(Color.transparent)",
      "        ),",
      "",
      "        style(",
      "            md.black,",
      "            backgroundColor(hex(\"#000000\"))",
      "        ),",
      "",
      "        style(",
      "            md.white,",
      "            backgroundColor(hex(\"#ffffff\"))",
      "        ),",
      "",
      "        style(",
      "            md.blue400,",
      "            backgroundColor(hex(\"#63b3ed\"))",
      "        )",
      "    );",
      "  }",
      "",
      "  public interface hover {",
      "",
      "    ClassSelector transparent = Css.randomDot(5);",
      "",
      "    ClassSelector black = Css.randomDot(5);",
      "",
      "    ClassSelector white = Css.randomDot(5);",
      "",
      "    ClassSelector blue400 = Css.randomDot(5);",
      "",
      "  }",
      "",
      "  public interface md {",
      "",
      "    ClassSelector transparent = Css.randomDot(5);",
      "",
      "    ClassSelector black = Css.randomDot(5);",
      "",
      "    ClassSelector white = Css.randomDot(5);",
      "",
      "    ClassSelector blue400 = Css.randomDot(5);",
      "",
      "  }",
      "",
      "}"
    );
  }

}
