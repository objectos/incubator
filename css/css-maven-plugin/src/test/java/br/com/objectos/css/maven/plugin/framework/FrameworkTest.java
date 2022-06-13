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
package br.com.objectos.css.maven.plugin.framework;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.config.framework.AbstractConfiguration;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkNamedValueSet;
import br.com.objectos.css.type.Color;
import br.com.objectos.css.type.Zero;
import objectos.util.ImmutableMap;
import org.testng.annotations.Test;

public class FrameworkTest extends AbstractCssMavenPluginFrameworkTest {

  @Test
  public void execute() {
    ImmutableMap<String, JavaFile> javaFiles = executeFramework(
        new AbstractConfiguration() {
          @Override
          protected final void configure() {
            packageName("br.com.objectos.css.framework");

            FrameworkNamedValueSet colors = valueSet(
                v("transparent", Color.transparent),
                v("black", rgb(0x0)),
                v("white", rgb(0xffffff)),
                v("blue-400", rgb(0x63b3ed))
            );

            property(
                FrameworkGroup.BACKGROUND,
                simpleName("BackgroundColor"),
                prefix("bg"),
                methods("backgroundColor"),
                colors
            );
            
            FrameworkNamedValueSet spacing = valueSet(
                v("0", Zero.INSTANCE),
                v("2", rem(0.5)),
                v("8", rem(2))
            );

            property(
                FrameworkGroup.SIZING,
                simpleName("Height"),
                prefix("h"),
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
        javaFiles.get("AbstractFramework"),
        "package br.com.objectos.css.framework;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.css.framework.background.BackgroundColor;",
        "import br.com.objectos.css.framework.sizing.Height;",        
        "import br.com.objectos.css.sheet.AbstractStyleSheet;",
        "",
        "@Generated(\"br.com.objectos.css.maven.plugin.framework.FrameworkMojo\")",
        "abstract class AbstractFramework extends AbstractStyleSheet {",
        "",
        "  @Override",
        "  protected void definition() {",
        "    background();",
        "    sizing();",
        "  }",
        "",
        "  protected void background() {",
        "    install(new BackgroundColor());",
        "  }",
        "",
        "  protected void sizing() {",
        "    install(new Height());",
        "  }",
        "",
        "}"
    );
  }
  
}
