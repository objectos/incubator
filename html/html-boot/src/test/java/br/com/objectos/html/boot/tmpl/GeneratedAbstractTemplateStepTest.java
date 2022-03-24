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
package br.com.objectos.html.boot.tmpl;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.html.boot.AbstractHtmlBootTest;
import br.com.objectos.html.boot.spec.AbstractSpec;
import java.util.Map;
import org.testng.annotations.Test;

public class GeneratedAbstractTemplateStepTest extends AbstractHtmlBootTest {

  @Test(description = ""
      + "it should generate methods for each element."
      + "for each element, it should generate attribute overloads.")
  public void execute() {
    Map<String, JavaFile> javaFiles = execute(
        GeneratedAbstractTemplateStep::new,
        new AbstractSpec() {
          @Override
          protected final void definition() {
            template()
                .maxLevel(1)
                .maxArity(1)
                .skipText("form");

            element("div");
            element("form");
            element("meta").noEndTag();
          }
        }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
        javaFiles.get("GeneratedAbstractTemplate"),
        "package br.com.objectos.html.tmpl;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.html.attribute.StandardAttributeName;",
        "import br.com.objectos.html.element.ElementName;",
        "import br.com.objectos.html.element.StandardElementName;",
        "import br.com.objectos.html.spi.type.DivValue;",
        "import br.com.objectos.html.spi.type.FormValue;",
        "import br.com.objectos.html.spi.type.MetaValue;",
        "import br.com.objectos.html.spi.type.Value;",
        "",
        "@Generated(\"br.com.objectos.html.boot.HtmlBoot\")",
        "abstract class GeneratedAbstractTemplate {",
        "",
        "  public final ElementName div(DivValue... values) {",
        "    return addStandardElement(StandardElementName.DIV, values);",
        "  }",
        "",
        "  public final ElementName div(String text) {",
        "    return addStandardElement(StandardElementName.DIV, text);",
        "  }",
        "",
        "  public final ElementName form(FormValue... values) {",
        "    return addStandardElement(StandardElementName.FORM, values);",
        "  }",
        "",
        "  public final ElementName meta(MetaValue... values) {",
        "    return addStandardElement(StandardElementName.META, values);",
        "  }",
        "",
        "  abstract <N extends StandardAttributeName> N addStandardAttribute(N name);",
        "",
        "  abstract <N extends StandardAttributeName> N addStandardAttribute(N name, String value);",
        "",
        "  abstract ElementName addStandardElement(StandardElementName name, String text);",
        "",
        "  abstract ElementName addStandardElement(StandardElementName name, Value[] values);",
        "",
        "}"
    );
  }

  @Test(description = ""
      + "it should generate methods for each attribute.")
  public void executeAttributes() {
    Map<String, JavaFile> javaFiles = execute(
        GeneratedAbstractTemplateStep::new,
        new AbstractSpec() {
          @Override
          protected final void definition() {
            template()
                .maxLevel(1)
                .maxArity(1)
                .skipAttribute("title");

            rootElement()
                .attribute("s")
                .attribute("b").booleanType()
                .attribute("t").as("title")
                .attributeEnd();
          }
        }
    );

    assertEquals(javaFiles.size(), 1);
    testLines(
        javaFiles.get("GeneratedAbstractTemplate"),
        "package br.com.objectos.html.tmpl;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "import br.com.objectos.html.attribute.StandardAttributeName;",
        "import br.com.objectos.html.attribute.StandardAttributeName.B;",
        "import br.com.objectos.html.attribute.StandardAttributeName.S;",
        "import br.com.objectos.html.element.ElementName;",
        "import br.com.objectos.html.element.StandardElementName;",
        "import br.com.objectos.html.spi.type.Value;",
        "",
        "@Generated(\"br.com.objectos.html.boot.HtmlBoot\")",
        "abstract class GeneratedAbstractTemplate {",
        "",
        "  public final B b() {",
        "    return addStandardAttribute(StandardAttributeName.B);",
        "  }",
        "",
        "  public final S s(String value) {",
        "    return addStandardAttribute(StandardAttributeName.S, value);",
        "  }",
        "",
        "  abstract <N extends StandardAttributeName> N addStandardAttribute(N name);",
        "",
        "  abstract <N extends StandardAttributeName> N addStandardAttribute(N name, String value);",
        "",
        "  abstract ElementName addStandardElement(StandardElementName name, String text);",
        "",
        "  abstract ElementName addStandardElement(StandardElementName name, Value[] values);",
        "",
        "}"
    );
  }

}
