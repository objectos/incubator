/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.http.server.jetty;

import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.server.Header;
import br.com.objectos.http.server.ResponseWriter;
import br.com.objectos.http.server.ResponseWriter.MessageBodyDsl;
import br.com.objectos.http.server.StatusCode;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;

class JettyResponseWriter
    implements
    ResponseWriter,
    ResponseWriter.MessageBodyDsl {

  private final Request baseRequest;
  private final HttpServletResponse response;

  JettyResponseWriter(Request baseRequest, HttpServletResponse response) {
    this.baseRequest = baseRequest;
    this.response = response;
  }

  @Override
  public ResponseWriter contentLength(long size) {
    response.setContentLengthLong(size);
    return this;
  }

  @Override
  public ResponseWriter contentType(MediaType mediaType) {
    response.setContentType(mediaType.qualifiedName());
    return this;
  }

  @Override
  public ResponseWriter header(Header header) {
    response.setHeader(header.name(), header.valueToString());
    return this;
  }

  @Override
  public MessageBodyDsl messageBody(InputStreamSource source) {
    try (InputStream in = source.openInputStream();
        ServletOutputStream out = response.getOutputStream()) {
      byte[] buf = new byte[0x1000];
      while (true) {
        int r = in.read(buf);
        if (r == -1) {
          break;
        }
        out.write(buf, 0, r);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return this;
  }

  @Override
  public MessageBodyDsl messageBody(String body) {
    try (Writer w = response.getWriter()) {
      w.write(body);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return this;
  }

  @Override
  public ResponseWriter say(StatusCode code) {
    response.setStatus(code.intValue());
    return this;
  }

  @Override
  public ResponseWriter sayNotFound() {
    return say(StatusCode.NOT_FOUND);
  }

  @Override
  public ResponseWriter sayOk() {
    return say(StatusCode.OK);
  }

  @Override
  public void write() {
    baseRequest.setHandled(true);
  }

}
