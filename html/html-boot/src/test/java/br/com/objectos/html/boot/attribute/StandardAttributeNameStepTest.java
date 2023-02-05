/*
 * Copyright (C) 2015-2023 Objectos Software LTDA.
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
package br.com.objectos.html.boot.attribute;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.html.boot.AbstractHtmlBootTest;
import br.com.objectos.html.boot.spec.AbstractSpec;
import java.util.Map;
import org.testng.annotations.Test;

public class StandardAttributeNameStepTest extends AbstractHtmlBootTest {

  @Test(description = "it should generate an class for each distinct attribute defined")
  public void execute() {
    Map<String, JavaFile> javaFiles;
    javaFiles = execute(
      StandardAttributeNameStep::new,
      new AbstractSpec() {
        @Override
        protected final void definition() {
          rootElement()
              .attribute("hidden").booleanType()
              .attributeEnd();

          element("meta")
              .attribute("charset")
              .noEndTag();
        }
      }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
      javaFiles.get("StandardAttributeName"),
      "package br.com.objectos.html.attribute;",
      "",
      "import br.com.objectos.code.annotations.Generated;",
      "import br.com.objectos.html.spi.tmpl.Marker;",
      "import br.com.objectos.html.spi.tmpl.Renderer;",
      "import br.com.objectos.html.spi.type.MetaValue;",
      "import br.com.objectos.html.spi.type.Value;",
      "import objectos.util.UnmodifiableMap;",
      "",
      "@Generated(\"br.com.objectos.html.boot.HtmlBoot\")",
      "public abstract class StandardAttributeName implements AttributeName, Value {",
      "",
      "  public static final Charset CHARSET = new Charset();",
      "",
      "  public static final Hidden HIDDEN = new Hidden();",
      "",
      "  private static final StandardAttributeName[] ARRAY = new StandardAttributeName[] {CHARSET, HIDDEN};",
      "",
      "  private static final UnmodifiableMap<String, StandardAttributeName> MAP = new NamesBuilder().put(\"charset\", CHARSET).put(\"hidden\", HIDDEN).build();",
      "",
      "  private final int code;",
      "",
      "  private final AttributeKind kind;",
      "",
      "  private final String name;",
      "",
      "  StandardAttributeName(int code, AttributeKind kind, String name) {",
      "    this.code = code;",
      "    this.kind = kind;",
      "    this.name = name;",
      "  }",
      "",
      "  public static StandardAttributeName getByCode(int code) {",
      "    return ARRAY[code];",
      "  }",
      "",
      "  public static StandardAttributeName getByName(String name) {",
      "    return MAP.get(name);",
      "  }",
      "",
      "  public static int size() {",
      "    return ARRAY.length;",
      "  }",
      "",
      "  @Override",
      "  public final int getCode() {",
      "    return code;",
      "  }",
      "",
      "  @Override",
      "  public final AttributeKind getKind() {",
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
      "    marker.markAttribute();",
      "  }",
      "",
      "  @Override",
      "  public final void render(Renderer renderer) {}",
      "",
      "  public static class Charset extends StandardAttributeName implements MetaValue {",
      "",
      "    private Charset() {",
      "      super(0, AttributeKind.STRING, \"charset\");",
      "    }",
      "",
      "  }",
      "",
      "  public static class Hidden extends StandardAttributeName implements GlobalAttributeName {",
      "",
      "    private Hidden() {",
      "      super(1, AttributeKind.BOOLEAN, \"hidden\");",
      "    }",
      "",
      "  }",
      "",
      "}"
    );
  }

}
