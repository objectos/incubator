/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package objectos.ssg.stage;

import br.com.objectos.core.io.InputStreamSource;
import br.com.objectos.core.io.Read;
import br.com.objectos.http.server.Code500InternalServerErrorException;
import br.com.objectos.http.server.HttpAction;
import br.com.objectos.http.server.HttpException;
import br.com.objectos.http.server.Request;
import br.com.objectos.http.server.Response;
import br.com.objectos.http.server.ResponseWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

final class SiteResourceHttpAction implements HttpAction, Response {

  private final SiteResource resource;

  SiteResourceHttpAction(SiteResource resource) {
    this.resource = resource;
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof SiteResourceHttpAction)) {
      return false;
    }

    SiteResourceHttpAction that = (SiteResourceHttpAction) obj;

    return resource.equals(that.resource);
  }

  @Override
  public final Response execute(Request request) throws HttpException {
    return this;
  }

  @Override
  public final int hashCode() {
    return resource.hashCode();
  }

  @Override
  public final void writeTo(ResponseWriter writer) throws HttpException {
    try {
      byte[] bytes;
      bytes = Read.byteArray(resource);

      writer.sayOk();

      writer.contentLength(bytes.length);

      writer.contentType(resource.mediaType());

      writer.messageBody(new ByteArrayByteSource(bytes));

      writer.write();
    } catch (IOException e) {
      throw new Code500InternalServerErrorException(e);
    }
  }

  private static class ByteArrayByteSource implements InputStreamSource {
    private final byte[] bytes;

    ByteArrayByteSource(byte[] bytes) {
      this.bytes = bytes;
    }

    @Override
    public final InputStream openInputStream() throws IOException {
      return new ByteArrayInputStream(bytes);
    }
  }

}
