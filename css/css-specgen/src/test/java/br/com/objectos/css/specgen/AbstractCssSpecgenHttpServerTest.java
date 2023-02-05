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
package br.com.objectos.css.specgen;

import br.com.objectos.core.io.Resource;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.http.path.Location;
import br.com.objectos.http.server.AbstractHttpModule;
import br.com.objectos.http.server.HttpServer;
import br.com.objectos.http.server.SimpleDirectoryAction;
import br.com.objectos.http.server.jetty.JettyHttpServerBuilder;
import br.com.objectos.http.testing.HttpTesting;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class AbstractCssSpecgenHttpServerTest extends AbstractHttpModule {

  protected static HttpServer httpServer;
  protected static HttpTesting httpTesting;

  @BeforeSuite
  public void setUp() {
    int port = 9986;
    httpServer = new JettyHttpServerBuilder()
        .port(port)
        .buildImmutable(this);
    httpTesting = HttpTesting.at(port);
    httpServer.start();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    if (httpServer != null) {
      httpServer.stop();
    }
  }

  @Override
  protected final void configure() {
    try {
      Resource resource = Resource.getResource("MARKER");

      RegularFile file = LocalFs.getRegularFile(resource);

      Directory directory = file.getParent();

      route(
          Location.builder().catchAll().build(),
          get(new SimpleDirectoryAction(directory))
      );
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}