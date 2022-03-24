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

import br.com.objectos.http.server.Code400BadRequestException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Protocol {

  HTTP_1_0("HTTP/1.0"),

  HTTP_1_1("HTTP/1.1"),

  HTTP_2("HTTP/2");

  private static final Map<String, Protocol> MAP = Stream.of(Protocol.values())
      .collect(Collectors.toMap(proto -> proto.value, Function.identity()));

  private final String value;

  private Protocol(String value) {
    this.value = value;
  }

  public static Protocol read(SocketReader reader) throws Code400BadRequestException {
    String name = reader.readString();

    if (!MAP.containsKey(name)) {
      throw Code400BadRequestException.invalidProtocol(name);
    }

    return MAP.get(name);
  }

}