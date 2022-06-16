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
package br.com.objectos.css.boot.type;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.boot.AbstractCssBootTest;
import br.com.objectos.css.boot.keyword.KeywordName;
import br.com.objectos.css.boot.spec.CssSpec;
import br.com.objectos.css.boot.spec.Source;
import objectos.util.UnmodifiableMap;
import org.testng.annotations.Test;

public class ValueTypeIfaceStepTest extends AbstractCssBootTest {

  @Test(description = "it should generate a Value interface for each ValueType defined")
  public void execute() {
    UnmodifiableMap<String, JavaFile> javaFiles = execute(
        ValueTypeIfaceStep::new,
        new CssSpec() {
          @Override
          protected final void definition() {
            KeywordName auto = keyword("auto");
            KeywordName none = keyword("none");

            property(
                "clear",
                formal("", Source.MANUAL_ENTRY),
                sig(t("ClearValue", none), "value")
            );

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
        javaFiles.get("ClearValue"),
        "package br.com.objectos.css.type;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
        "public interface ClearValue extends Value {}"
    );
    testLines(
        javaFiles.get("TopValue"),
        "package br.com.objectos.css.type;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
        "public interface TopValue extends Value {}"
    );
  }

}
