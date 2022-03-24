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

import br.com.objectos.http.server.NoopSocketWriter;
import br.com.objectos.http.server.SocketWriter;
import java.io.IOException;

class NoopSocket extends Socket {

  private static final NoopSocket INSTANCE = new NoopSocket();

  private NoopSocket() {
  }

  public static NoopSocket instance() {
    return INSTANCE;
  }

  @Override
  public void close() throws IOException {
    // noop
  }

  @Override
  public SocketRequest parse() {
    return NoopRequest.instance();
  }

  @Override
  public SocketReader openReader() {
    throw new UnsupportedOperationException("This method must not be called.");
  }

  @Override
  public SocketWriter openWriter() {
    return NoopSocketWriter.instance();
  }

}