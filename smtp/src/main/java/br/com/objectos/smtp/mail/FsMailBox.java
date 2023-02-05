/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.smtp.mail;

import br.com.objectos.smtp.MailBox;
import br.com.objectos.smtp.MailObject;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import objectos.util.ByteArrays;

final class FsMailBox implements MailBox {

  private static final long RECORD_SIZE = 16L;

  private final FileChannel messages;
  private final FsMailStore store;

  FsMailBox(FsMailStore store, FileChannel messages) {
    this.store = store;
    this.messages = messages;
  }

  @Override
  public final void close() throws IOException {
    messages.close();
  }

  @Override
  public final MailObject get(int index) throws IOException {
    if (index < 0) {
      throw new IndexOutOfBoundsException("index has to be >= 0");
    }

    long size;
    size = size();

    if (index >= size) {
      throw new IndexOutOfBoundsException("index has to be < " + size);
    }

    byte[] uuidBytes;
    uuidBytes = getUuidBytes(index, 1);

    String hex;
    hex = ByteArrays.toHexString(uuidBytes);

    return store.getMailObject(hex);
  }

  @Override
  public final long size() {
    try {
      long fileSize;
      fileSize = messages.size();

      long recordQty;
      recordQty = fileSize / RECORD_SIZE;

      return recordQty;
    } catch (IOException e) {
      return 0;
    }
  }

  private byte[] getUuidBytes(int index, int count) throws IOException {
    long offset;
    offset = (index) * RECORD_SIZE;

    messages.position(offset);

    int capacity;
    capacity = (int) (count * RECORD_SIZE);

    ByteBuffer byteBuffer;
    byteBuffer = ByteBuffer.allocate(capacity);

    messages.read(byteBuffer);

    return byteBuffer.array();
  }

}
