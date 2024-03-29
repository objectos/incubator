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

import br.com.objectos.core.io.InputStreamSource;
import java.io.IOException;

public class NoopSocketWriter implements SocketWriter {

  private static final NoopSocketWriter INSTANCE = new NoopSocketWriter();

  private NoopSocketWriter() {}

  public static NoopSocketWriter instance() {
    return INSTANCE;
  }

  @Override
  public void close() {}

  @Override
  public void flush() throws IOException {}

  @Override
  public void newLine() throws IOException {}

  @Override
  public void write(InputStreamSource source) throws IOException {}

  @Override
  public void writeString(String text) throws IOException {}

}