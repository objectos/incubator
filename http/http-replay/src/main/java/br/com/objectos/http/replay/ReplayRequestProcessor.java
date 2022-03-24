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
package br.com.objectos.http.replay;

import br.com.objectos.http.Body;
import br.com.objectos.http.Body.Ignored;
import br.com.objectos.http.Body.Text;
import br.com.objectos.http.Http;
import br.com.objectos.http.Method;
import br.com.objectos.http.RequestHeader;
import br.com.objectos.http.SimpleRequestVisitor;
import br.com.objectos.http.Version;
import java.nio.CharBuffer;
import java.util.NoSuchElementException;

public class ReplayRequestProcessor extends SimpleRequestVisitor {

  private CharBuffer charBuffer;

  private boolean overflow;

  private Body resultBody;

  protected ReplayRequestProcessor() {}

  public final Body getBody() {
    if (resultBody == null) {
      throw new NoSuchElementException();
    }

    return resultBody;
  }

  @Override
  public final void visitRequestBody(Ignored ignored) {
    defaultRequestBodyAction(ignored);
  }

  @Override
  public final void visitRequestBody(Text text) {
    defaultRequestBodyAction(text);
  }

  @Override
  public void visitRequestLine(Method method, String target, Version version) {
    buffer(method.name());

    buffer(' ');

    buffer(target);

    buffer(' ');

    buffer(version.signature);

    buffer(Http.CRLF);
  }

  protected final void buffer(char c) {
    if (overflow) {
      overflow(c);

      return;
    }

    if (!charBuffer.hasRemaining()) {
      overflow(c);

      return;
    }

    charBuffer.put(c);
  }

  protected final void buffer(String value) {
    if (overflow) {
      overflow(value);

      return;
    }

    int length;
    length = value.length();

    if (length > charBuffer.remaining()) {
      overflow(value);

      return;
    }

    charBuffer.put(value);
  }

  protected void clear() {}

  @Override
  protected final void defaultRequestBodyAction(Body body) {
    buffer(Http.CRLF);

    resultBody = body;
  }

  @Override
  protected final void defaultRequestHeaderAction(RequestHeader header) {
    buffer(header.toString());

    buffer(Http.CRLF);
  }

  final void reset(CharBuffer charBuffer) {
    this.charBuffer = charBuffer.clear();

    overflow = false;

    resultBody = null;

    clear();
  }

  private void overflow(char c) {
    throw new UnsupportedOperationException("Implement me");
  }

  private void overflow(String value) {
    throw new UnsupportedOperationException("Implement me");
  }

}