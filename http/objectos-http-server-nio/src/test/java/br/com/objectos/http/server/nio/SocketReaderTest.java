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

import java.io.IOException;
import org.testng.annotations.Test;

public class SocketReaderTest extends AbstractTest {

  @Test
  public void readString() throws IOException {
    SocketReader reader = socketReaderOf("http/method-get-hello-world.txt", 1024);
    assertEquals(reader.readString(), "GET");
    assertEquals(reader.readString(), "/hello-world.txt");
    assertEquals(reader.readString(), "HTTP/1.1");
    assertEquals(reader.readString(), "User-Agent:");
  }

}