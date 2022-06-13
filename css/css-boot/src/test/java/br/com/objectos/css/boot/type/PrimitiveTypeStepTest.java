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
import br.com.objectos.css.boot.spec.CssSpec;
import br.com.objectos.css.boot.spec.Source;
import objectos.util.ImmutableMap;
import org.testng.annotations.Test;

public class PrimitiveTypeStepTest extends AbstractCssBootTest {

  @Test
  public void execute() {
    ImmutableMap<String, JavaFile> javaFiles = execute(
        PrimitiveTypeStep::new,
        new CssSpec() {
          @Override
          protected final void definition() {
            PrimitiveType length = primitive(Primitive.LENGTH);
            PrimitiveType percentage = primitive(Primitive.PERCENTAGE);

            property(
                "bottom",
                formal("", Source.MANUAL_ENTRY),
                sig(t("BottomValue", length, percentage), "value")
            );
          }
        }
    );
    assertEquals(javaFiles.size(), 2);
    testLines(
        javaFiles.get("LengthType"),
        "package br.com.objectos.css.type;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
        "public interface LengthType extends BottomValue, Value {}"
    );
    testLines(
        javaFiles.get("PercentageType"),
        "package br.com.objectos.css.type;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.css.boot.CssBoot\")",
        "public interface PercentageType extends BottomValue, Value {}"
    );
  }

}
