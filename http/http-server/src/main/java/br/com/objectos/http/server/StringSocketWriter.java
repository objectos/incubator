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

import br.com.objectos.core.io.InputStreamSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringSocketWriter implements SocketWriter {

  private final StringBuilder buffer = new StringBuilder();

  @Override
  public void close() throws IOException {}

  @Override
  public void flush() throws IOException {}

  @Override
  public void newLine() {
    buffer.append('\r').append('\n');
  }

  @Override
  public String toString() {
    return buffer.toString();
  }

  @Override
  public void write(InputStreamSource source) throws IOException {
    try (BufferedReader reader
        = new BufferedReader(new InputStreamReader(source.openInputStream()))) {
      int read;
      while ((read = reader.read()) != -1) {
        char asChar = (char) read;
        buffer.append(asChar);
      }
    }
  }

  @Override
  public void writeString(String text) {
    buffer.append(text);
  }

}