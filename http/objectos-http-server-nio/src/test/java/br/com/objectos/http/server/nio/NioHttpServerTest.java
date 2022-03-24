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

import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.MutableHttpServer;
import br.com.objectos.http.testing.AbstractHttpServerTest;
import java.io.IOException;
import org.testng.annotations.Test;

public class NioHttpServerTest extends AbstractHttpServerTest {

  @Test(enabled = false)
  @Override
  public void requestUrl() throws IOException {
    super.requestUrl();
  }

  @Override
  protected int port() {
    return 8081;
  }

  @Override
  protected final MutableHttpServer newServerWith(HttpModule module) {
    return new NioHttpServerBuilder().port(port()).buildMutable(module);
  }

}