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
package objectos.ssg.stage;

import static org.testng.Assert.assertEquals;

import br.com.objectos.http.server.ImmutableHttpServer;
import br.com.objectos.http.server.jetty.JettyHttpServerBuilder;
import br.com.objectos.http.testing.HttpTesting;
import br.com.objectos.www.objectos.ObjectosSite;
import java.io.IOException;
import org.testng.annotations.Test;

public class DevelopmentStageTest {

  @Test
  public void objectos() throws IOException {
    DevelopmentStage stage;
    stage = new DevelopmentStage();

    stage.addSite(new ObjectosSite());

    stage.render();

    int port = 7777;

    ImmutableHttpServer server;
    server = new JettyHttpServerBuilder()
        .port(port)
        .buildImmutable(stage);

    HttpTesting testing;
    testing = HttpTesting.at(port);

    try {
      server.start();

      assertEquals(
        testing.readResponse("/css/styles.css"),
        String.join("",
          "body{",
          "font-family:MyCustomFont",
          "}",
          ".container",
          "{width:100%}"
        )
      );

      assertEquals(
        testing.readResponse("/index.html"),
        String.join("",
          "<html>",
          "<head>",
          "<link rel=\"stylesheet\" href=\"/css/styles.css\">",
          "</head>",
          "<body>",
          "<nav>",
          "<ul>",
          "</ul>",
          "</nav>",
          "<ul><li><a href=\"/index.html\"></a></li></ul>",
          "</body>",
          "</html>"
        )
      );

      assertEquals(
        testing.readResponse("/foo.txt"),
        "hello foo!"
      );

      assertEquals(
        testing.readResponse("/v1/bar.html"),
        "<html><body>bar!</body></html>"
      );
    } finally {
      server.stop();
    }
  }

}
