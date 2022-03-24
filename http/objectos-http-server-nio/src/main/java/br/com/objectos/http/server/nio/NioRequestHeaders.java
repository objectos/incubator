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

import br.com.objectos.http.server.RequestHeaders;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

class NioRequestHeaders implements RequestHeaders {

  private final Map<String, NioHeader> map;

  private NioRequestHeaders(Map<String, NioHeader> map) {
    this.map = map;
  }

  public static NioRequestHeaders parse(SocketReader reader) {
    Map<String, NioHeader> headerMap = new LinkedHashMap<>();
    String line = reader.readLine(); // skip HTTP
    line = reader.readLine();
    while (!line.isEmpty()) {
      NioHeader header = NioHeader.parse(line);
      header.addTo(headerMap);
      line = reader.readLine();
    }
    return new NioRequestHeaders(headerMap);
  }

  @Override
  public boolean contains(String header) {
    return map.containsKey(header);
  }

  @Override
  public String get(String header) {
    if (!contains(header)) {
      throw new NoSuchElementException(header);
    }
    return map.get(header).value();
  }

}