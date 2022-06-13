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
package br.com.objectos.css.boot.keyword;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.boot.AbstractCssBootTest;
import br.com.objectos.css.boot.spec.CssSpec;
import br.com.objectos.css.boot.spec.Source;
import objectos.util.ImmutableMap;
import org.testng.annotations.Test;

public class KeywordClassStepTest extends AbstractCssBootTest {

  @Test(description = ""
      + "keyword class must implement the PropertyValue interface "
      + "if part of that value set")
  public void execute() {
    ImmutableMap<String, JavaFile> javaFiles;
    javaFiles = execute(
        KeywordClassStep::new,
        new CssSpec() {
          @Override
          protected final void definition() {
            KeywordName auto = keyword("auto");
            keyword("double");

            property(
                "top",
                formal("", Source.MANUAL_ENTRY),
                sig(t("TopValue", auto), "value")
            );
          }
        }
    );
    assertEquals(javaFiles.size(), 2);

    testLines(
        javaFiles.get("AutoKeyword"),
        "package br.com.objectos.css.keyword;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.css.type.TopValue;",
        "",
        "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
        "public final class AutoKeyword extends StandardKeyword implements TopValue {",
        "",
        "  static final AutoKeyword INSTANCE = new AutoKeyword();",
        "",
        "  private AutoKeyword() {",
        "    super(0, \"auto\", \"auto\");",
        "  }",
        "",
        "}"
    );
    testLines(
        javaFiles.get("DoubleKeyword"),
        "package br.com.objectos.css.keyword;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
        "public final class DoubleKeyword extends StandardKeyword {",
        "",
        "  static final DoubleKeyword INSTANCE = new DoubleKeyword();",
        "",
        "  private DoubleKeyword() {",
        "    super(1, \"doubleKw\", \"double\");",
        "  }",
        "",
        "}"
    );
  }

}
