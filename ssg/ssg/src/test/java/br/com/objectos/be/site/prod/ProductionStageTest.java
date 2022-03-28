/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.site.prod;

import static org.testng.Assert.assertEquals;

import br.com.objectos.core.io.Charsets;
import br.com.objectos.core.io.Read;
import br.com.objectos.fs.Directory;
import br.com.objectos.www.objectos.ObjectosSite;
import java.io.IOException;
import org.testng.annotations.Test;

public class ProductionStageTest extends AbstractSiteProdTest {

  @Test
  public void www() throws IOException {
    Directory target;
    target = newTempDir();

    ProductionStage stage;
    stage = new ProductionStage("", target);

    stage.addSite(new ObjectosSite());

    assertEquals(
      Read.string(target.getRegularFile("index.html"), Charsets.utf8()),
      String.join("",
        "<html>",
        "<head>",
        "<link rel=\"stylesheet\" href=\"/css/styles.css\">",
        "</head>",
        "<body>",
        "<nav>",
        "<ul>",
        "<li><a href=\"/index.html\">Home</a></li>",
        "<li><a href=\"/blog/index.html\">Blog</a></li>",
        "</ul>",
        "</nav>",
        "<ul><li><a href=\"/index.html\"></a></li></ul>",
        "</body>",
        "</html>"
      )
    );

    Directory css;
    css = target.getDirectory("css");

    assertHasLines(
      Read.string(css.getRegularFile("styles.css"), Charsets.utf8()),
      String.join("",
        "body{",
        "font-family:MyCustomFont",
        "}",
        ".container",
        "{width:100%}"
      )
    );

    Directory v1;
    v1 = target.getDirectory("v1");

    assertEquals(
      Read.string(v1.getRegularFile("bar.html"), Charsets.utf8()),
      "<html><body>bar!</body></html>"
    );
  }

  private void assertHasLines(String string, String... expected) {
    String[] split;
    split = string.split("\n");

    assertEquals(split, expected);
  }

}
