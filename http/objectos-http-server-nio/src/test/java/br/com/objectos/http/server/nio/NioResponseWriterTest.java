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

import br.com.objectos.http.media.TextType;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.StringSocketWriter;
import org.testng.annotations.Test;

@SuppressWarnings("resource")
public class NioResponseWriterTest {

  @Test
  public void ok() throws HttpException {
    StringSocketWriter writer = new StringSocketWriter();
    new NioResponseWriter(() -> writer)
        .sayOk()
        .write();
    assertEquals(writer.toString(), "HTTP/1.1 200 OK");
  }

  @Test
  public void ok_headers() throws HttpException {
    StringSocketWriter writer = new StringSocketWriter();
    new NioResponseWriter(() -> writer)
        .sayOk()
        .contentType(TextType.HTML)
        .write();
    assertEquals(writer.toString(), ""
        + "HTTP/1.1 200 OK\r\n"
        + "Content-type: text/html");
  }

  @Test
  public void ok_headers_body() throws HttpException {
    StringSocketWriter writer = new StringSocketWriter();
    new NioResponseWriter(() -> writer)
        .sayOk()
        .contentType(TextType.PLAIN)
        .messageBody("hello\nworld!!!")
        .write();
    assertEquals(writer.toString(), ""
        + "HTTP/1.1 200 OK\r\n"
        + "Content-type: text/plain\r\n"
        + "\r\n"
        + "hello\n"
        + "world!!!");
  }

}