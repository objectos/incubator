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
package br.com.objectos.http;

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.object.Checks;

public final class Response {

  private final Body body;

  private final ImmutableList<ResponseHeader> headers;

  private final String reasonPhrase;

  private final Status status;

  private final Version version;

  Response(Body body,
           ImmutableList<ResponseHeader> headers,
           String reasonPhrase,
           Status status,
           Version version) {
    this.body = body;
    this.headers = headers;
    this.reasonPhrase = reasonPhrase;
    this.status = status;
    this.version = version;
  }

  public final void acceptResponseVisitor(ResponseVisitor visitor) {
    Checks.checkNotNull(visitor, "visitor == null");

    visitor.visitResponseStatusLine(version, status, reasonPhrase);

    for (int i = 0; i < headers.size(); i++) {
      ResponseHeader h;
      h = headers.get(i);

      h.acceptResponseVisitor(visitor);
    }

    body.acceptResponseVisitor(visitor);
  }

}
