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

import br.com.objectos.http.media.TextType;

public class HtmlResponse implements Response {

  private final String text;

  public HtmlResponse(String text) {
    this.text = text;
  }

  @Override
  public void writeTo(ResponseWriter writer) throws HttpException {
    writer.sayOk()
        .contentLength(text.length())
        .contentType(TextType.HTML)
        .messageBody(text)
        .write();
  }

}