/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.http.server;

import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.media.TextType;
import objectos.lang.Check;

public class TextResponse implements Response {

  private final StatusCode code = StatusCode.OK;
  private final MediaType contentType = TextType.PLAIN;
  private final String text;

  public TextResponse(String text) {
    this.text = Check.notNull(text, "text == null");
  }

  @Override
  public void writeTo(ResponseWriter writer) throws HttpException {
    writer.say(code).contentType(contentType).messageBody(text).write();
  }

}