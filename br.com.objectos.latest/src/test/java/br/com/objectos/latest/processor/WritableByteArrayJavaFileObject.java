/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.latest.processor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import javax.tools.SimpleJavaFileObject;

class WritableByteArrayJavaFileObject extends SimpleJavaFileObject {

  private static final byte[] EMPTY = new byte[] {};

  private byte[] value = EMPTY;

  protected WritableByteArrayJavaFileObject(String qualifiedName, Kind kind) {
    super(URI.create(new StringBuilder()
        .append("writable:///")
        .append(qualifiedName.replace('.', '/'))
        .append(kind.extension)
        .toString()), kind);
  }

  @Override
  public InputStream openInputStream() throws IOException {
    return new ByteArrayInputStream(value);
  }

  @Override
  public OutputStream openOutputStream() throws IOException {
    return new ByteArrayOutputStream() {
      @Override
      public void close() throws IOException {
        super.close();
        value = toByteArray();
      }
    };
  }

  @Override
  public String toString() {
    return new String(value);
  }

}