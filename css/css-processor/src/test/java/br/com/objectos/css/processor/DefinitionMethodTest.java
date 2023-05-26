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
package br.com.objectos.css.processor;

import br.com.objectos.code.java.declaration.MethodCode;
import objectos.css.sheet.AbstractStyleSheet;
import objectos.css.sheet.StyleSheet;
import org.testng.annotations.Test;

public class DefinitionMethodTest {

  @Test
  public void rgb() {
    test(
      new AbstractStyleSheet() {
        @Override
        protected void definition() {
          style(
            cn("rgb"),

            color(rgb(0, 1, 2)),
            color(rgb(0, 100.1, 255)),
            color(rgb(0, 127, 255, 0.5)),
            color(rgb(0.1, 0.2, 0.3, 0.4)),
            color(rgba(0, 127, 255, 0.5)),
            color(rgba(0.1, 0.2, 0.3, 0.4))
          );
        }
      },
      "@java.lang.Override",
      "protected final void definition() {",
      "  style(",
      "      cn(\"rgb\"),",
      "",
      "      color(rgb(0, 1, 2)),",
      "      color(rgb(0.0, 100.1, 255.0)),",
      "      color(rgb(0, 127, 255, 0.5)),",
      "      color(rgb(0.1, 0.2, 0.3, 0.4)),",
      "      color(rgba(0, 127, 255, 0.5)),",
      "      color(rgba(0.1, 0.2, 0.3, 0.4))",
      "  );",
      "}"
    );
  }

  @Test
  public void selector() {
    test(
      new AbstractStyleSheet() {
        @Override
        protected void definition() {
          style(html);

          style(id("a"), cn("dot"));
        }
      },
      "@java.lang.Override",
      "protected final void definition() {",
      "  style(",
      "      html",
      "  );",
      "  style(",
      "      id(\"a\"), cn(\"dot\")",
      "  );",
      "}"
    );
  }

  @Test
  public void zeroValue() {
    test(
      new AbstractStyleSheet() {
        @Override
        protected void definition() {
          style(
            body,

            margin(zero())
          );
        }
      },
      "@java.lang.Override",
      "protected final void definition() {",
      "  style(",
      "      body,",
      "",
      "      margin(zero())",
      "  );",
      "}"
    );
  }

  private void test(StyleSheet sheet, String... expected) {
    MethodCode method = DefinitionMethod.of(sheet);

    Util.assertHasLines(method.toString(), expected);
  }

}