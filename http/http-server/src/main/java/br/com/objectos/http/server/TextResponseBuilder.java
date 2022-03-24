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
package br.com.objectos.http.server;

public class TextResponseBuilder {

  private final StringBuilder text;

  public TextResponseBuilder() {
    text = new StringBuilder();
  }

  public TextResponse build() {
    return new TextResponse(text.toString());
  }

  public TextResponseBuilder writeLine(String line) {
    text.append(line).append(System.lineSeparator());
    return this;
  }

  public TextResponseBuilder writeLine(String format, Object... args) {
    return writeLine(String.format(format, args));
  }

}