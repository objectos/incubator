/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.http.server.nio;

import br.com.objectos.io.Directory;
import br.com.objectos.io.Io;
import br.com.objectos.io.RegularFile;
import br.com.objectos.io.Resource;
import br.com.objectos.http.path.Location;
import br.com.objectos.http.server.AbstractHttpModule;
import br.com.objectos.http.server.HttpServer;
import br.com.objectos.http.server.SimpleDirectoryAction;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class AbstractProxyTest {

  private HttpServer mirror8080;
  private HttpServer mirror8081;

  @BeforeClass
  public void httpStart() {
    mirror8080 = new NioHttpServerBuilder()
        .port(8080)
        .buildImmutable(new AbstractHttpModule() {
          @Override
          protected final void configure() {
            route(
                Location.builder().fixed("proxy").catchAll().build(),
                get(new SimpleDirectoryAction(parentOf("mirror8080/MARKER")))
            );
          }
        });
    mirror8081 = new NioHttpServerBuilder()
        .port(8081)
        .buildImmutable(new AbstractHttpModule() {
          @Override
          protected final void configure() {
            route(
                Location.builder().fixed("proxy").catchAll().build(),
                get(new SimpleDirectoryAction(parentOf("mirror8081/MARKER")))
            );
          }
        });
    mirror8080.start();
    mirror8081.start();
  }

  @AfterClass(alwaysRun = true)
  public void httpStop() {
    if (mirror8080 != null) {
      mirror8080.stop();
    }
    if (mirror8081 != null) {
      mirror8081.stop();
    }
  }

  private Directory parentOf(String name) {
    try {
      Resource resource;
      resource = Resource.getResource(name);

      RegularFile file;
      file = Io.getRegularFile(resource);

      return file.getDirectory();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}