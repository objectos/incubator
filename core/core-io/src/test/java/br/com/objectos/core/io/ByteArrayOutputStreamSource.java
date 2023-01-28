/*
 * Copyright (C) 2021-2023 Objectos Software LTDA.
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
package br.com.objectos.core.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class ByteArrayOutputStreamSource implements OutputStreamSource {

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @Override
  public final OutputStream openOutputStream() throws IOException {
    outputStream.reset();

    return outputStream;
  }

  public final byte[] toByteArray() {
    return outputStream.toByteArray();
  }

}