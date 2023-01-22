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
package br.com.objectos.css.specgen;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.specgen.mdn.Mdn;
import java.io.IOException;
import objectos.util.UnmodifiableMap;
import org.testng.annotations.Test;

public class PropertyModuleStepTest extends AbstractCssSpecgenTest {

  @Test
  public void clear() throws IOException {
    UnmodifiableMap<String, JavaFile> javaFiles = execute(
        PropertyModuleStep::new,
        new AbstractSpecgen(Mdn.load()) {
          @Override
          protected final void definition() {
            p("clear");
          }
        }
    );
    assertEquals(javaFiles.size(), 1);
    testLines(
        javaFiles.get("ClearPropertyModule"),
        "package br.com.objectos.css.boot;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.css.boot.keyword.KeywordName;",
        "import br.com.objectos.css.boot.spec.Source;",
        "",
        "@Generated(\"br.com.objectos.css.specgen.SpecgenBoot\")",
        "final class ClearPropertyModule extends AbstractPropertyModule {",
        "",
        "  @Override",
        "  final void propertyDefinition() {",
        "    KeywordName both = keyword(\"both\");",
        "    KeywordName inlineEnd = keyword(\"inline-end\");",
        "    KeywordName inlineStart = keyword(\"inline-start\");",
        "    KeywordName left = keyword(\"left\");",
        "    KeywordName none = keyword(\"none\");",
        "    KeywordName right = keyword(\"right\");",
        "    property(",
        "        \"clear\",",
        "",
        "        formal(",
        "            Source.MDN,",
        "            \"none | left | right | both | inline-start | inline-end\"",
        "        ),",
        "",
        "        globalSig",
        "    );",
        "  }",
        "",
        "}"
    );
  }

  @Test
  public void margin() throws IOException {
    UnmodifiableMap<String, JavaFile> javaFiles = execute(
        PropertyModuleStep::new,
        new AbstractSpecgen(Mdn.load()) {
          @Override
          protected final void definition() {
            p("margin", "margin-top", "margin-right", "margin-bottom", "margin-left");
          }
        }
    );
    assertEquals(javaFiles.size(), 1);
    testLines(
        javaFiles.get("MarginPropertyModule"),
        "package br.com.objectos.css.boot;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.css.boot.keyword.KeywordName;",
        "import br.com.objectos.css.boot.spec.Source;",
        "",
        "@Generated(\"br.com.objectos.css.specgen.SpecgenBoot\")",
        "final class MarginPropertyModule extends AbstractPropertyModule {",
        "",
        "  @Override",
        "  final void propertyDefinition() {",
        "    KeywordName auto = keyword(\"auto\");",
        "    property(",
        "        \"margin\",",
        "",
        "        formal(",
        "            Source.MDN,",
        "            \"[ <length> | <percentage> | auto ]{1,4}\"",
        "        ),",
        "",
        "        globalSig",
        "    );",
        "    property(",
        "        names(\"margin-top\", \"margin-right\", \"margin-bottom\", \"margin-left\"),",
        "",
        "        formal(",
        "            Source.MDN,",
        "            \"<length> | <percentage> | auto\"",
        "        ),",
        "",
        "        globalSig",
        "    );",
        "  }",
        "",
        "}"
    );
  }

}
