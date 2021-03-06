/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.boot.element;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.html.boot.AbstractHtmlBootTest;
import br.com.objectos.html.boot.spec.AbstractSpec;
import java.util.Map;
import org.testng.annotations.Test;

public class StandardElementNameStepTest extends AbstractHtmlBootTest {

  @Test(description = "it should generate an enum constant for each element defined")
  public void execute() {
    Map<String, JavaFile> javaFiles;
    javaFiles = execute(
        StandardElementNameStep::new,
        new AbstractSpec() {
          @Override
          protected final void definition() {
            element("div");
            element("meta").noEndTag();
          }
        }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
        javaFiles.get("StandardElementName"),
        "package br.com.objectos.html.element;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.html.spi.tmpl.Marker;",
        "import br.com.objectos.html.spi.tmpl.Renderer;",
        "",
        "@Generated(\"br.com.objectos.html.boot.HtmlBoot\")",
        "public enum StandardElementName implements ElementName {",
        "",
        "  DIV(ElementKind.NORMAL, \"div\"),",
        "",
        "  META(ElementKind.VOID, \"meta\");",
        "",
        "  private static final StandardElementName[] ARRAY = StandardElementName.values();",
        "",
        "  private final ElementKind kind;",
        "",
        "  private final String name;",
        "",
        "  private StandardElementName(ElementKind kind, String name) {",
        "    this.kind = kind;",
        "    this.name = name;",
        "  }",
        "",
        "  public static StandardElementName getByCode(int code) {",
        "    return ARRAY[code];",
        "  }",
        "",
        "  public static int size() {",
        "    return ARRAY.length;",
        "  }",
        "",
        "  @Override",
        "  public final int getCode() {",
        "    return ordinal();",
        "  }",
        "",
        "  @Override",
        "  public final ElementKind getKind() {",
        "    return kind;",
        "  }",
        "",
        "  @Override",
        "  public final String getName() {",
        "    return name;",
        "  }",
        "",
        "  @Override",
        "  public final void mark(Marker marker) {",
        "    marker.markElement();",
        "  }",
        "",
        "  @Override",
        "  public final void render(Renderer renderer) {}",
        "",
        "}"
    );
  }

}
