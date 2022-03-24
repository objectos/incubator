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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import br.com.objectos.http.path.Location;
import br.com.objectos.http.server.AbstractHttpModule;
import br.com.objectos.http.server.HttpServer;
import br.com.objectos.io.Directory;
import br.com.objectos.io.FsObject;
import br.com.objectos.io.Io;
import br.com.objectos.io.Url;
import br.com.objectos.testing.Testing;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CachingProxyTest extends AbstractProxyTest {

  private HttpServer http;
  private Directory workingDir;

  @Test
  public void first() throws IOException {
    FsObject first = workingDir.resolve("first.txt");

    assertFalse(first.exists());
    Url firstUrl = Url.of("http://localhost:7000/first.txt");

    assertEquals(Io.readStringUtf8(firstUrl), "First mirror only!");

    first = workingDir.resolve("first.txt");

    assertTrue(first.exists());
  }

  @BeforeClass
  public void proxyStart() throws IOException {
    workingDir = Testing.createTempDirectory();
    CachingProxy proxy = new CachingProxyBuilder(workingDir)
        .addMirror("http://localhost:8080/proxy/")
        .addMirror("http://localhost:8081/proxy/")
        .build();
    http = new NioHttpServerBuilder()
        .port(7000)
        .buildImmutable(new AbstractHttpModule() {
          @Override
          protected final void configure() {
            route(
                Location.builder().catchAll().build(),
                get(req -> {
                  String path = req.getString(0);
                  return proxy.get(path);
                })
            );
          }
        });
    http.start();
  }

  @AfterClass(alwaysRun = true)
  public void proxyStop() {
    http.stop();
  }

  @Test
  public void second() throws IOException {
    Url url = Url.of("http://localhost:7000/second.txt");
    assertEquals(Io.readStringUtf8(url), "Second mirror only!");
  }

}