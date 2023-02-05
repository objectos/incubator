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
package br.com.objectos.smtp.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.CharsetEncoder;

final class ResponseBuffer {

  private final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);
  private final CharBuffer charBuffer = CharBuffer.allocate(512);

  private final CharsetEncoder charsetEncoder;

  public ResponseBuffer(CharsetEncoder charsetEncoder) {
    this.charsetEncoder = charsetEncoder;
  }

  public final void addLine(String string) {
    charBuffer.clear();

    charBuffer.put(string);
    charBuffer.put('\r');
    charBuffer.put('\n');

    charBuffer.flip();

    charsetEncoder.encode(charBuffer, byteBuffer, false);
  }

  public final void reset() {
    byteBuffer.clear();

    charBuffer.clear();

    charsetEncoder.reset();
  }

  public final boolean write(WritableByteChannel client) throws IOException {
    charBuffer.clear();
    charBuffer.limit(0);

    charsetEncoder.encode(charBuffer, byteBuffer, true);

    charsetEncoder.flush(byteBuffer);

    byteBuffer.flip();

    int total;
    total = 0;

    while (byteBuffer.hasRemaining()) {
      int thisCount;
      thisCount = client.write(byteBuffer);

      total += thisCount;
    }

    return total > 0;
  }

}
