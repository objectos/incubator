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
package br.com.objectos.html.boot.spi.type;

import static org.testng.Assert.assertEquals;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.html.boot.AbstractHtmlBootTest;
import br.com.objectos.html.boot.spec.AbstractSpec;
import java.util.Map;
import org.testng.annotations.Test;

public class AnyElementValueStepTest extends AbstractHtmlBootTest {

  @Test(description = "it should generate an interface for each distinct element defined")
  public void execute() {
    Map<String, JavaFile> javaFiles = execute(
        AnyElementValueStep::new,
        new AbstractSpec() {
          @Override
          protected final void definition() {
            rootElement()
                .attribute("lang")
                .attributeEnd();

            element("meta")
                .attribute("name")
                .noEndTag();

            element("title")
                .attribute("name");
          }
        }
    );

    assertEquals(javaFiles.size(), 1);

    testLines(
        javaFiles.get("AnyElementValue"),
        "package br.com.objectos.html.spi.type;",
        "",
        "import br.com.objectos.code.annotations.Generated;",
        "",
        "@Generated(\"br.com.objectos.html.boot.HtmlBoot\")",
        "public interface AnyElementValue extends MetaValue, TitleValue {}"
    );
  }

}
