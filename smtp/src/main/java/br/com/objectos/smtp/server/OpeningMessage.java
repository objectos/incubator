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
import java.nio.charset.Charset;

final class OpeningMessage {

  private final ByteBuffer message;

  private OpeningMessage(ByteBuffer message) {
    this.message = message;
  }

  public static OpeningMessage create(String hostName, Charset charset) {
    StringBuilder sb;
    sb = new StringBuilder();

    sb.append("220");
    sb.append(' ');
    sb.append(hostName);
    sb.append(' ');
    sb.append("SMTP Server");
    sb.append(' ');
    sb.append("ObjectosSmtp");
    sb.append('\r');
    sb.append('\n');

    CharBuffer charBuffer;
    charBuffer = CharBuffer.wrap(sb);

    ByteBuffer message;
    message = charset.encode(charBuffer);

    return new OpeningMessage(message);
  }

  final void execute(WritableByteChannel channel) throws IOException {
    ByteBuffer buffer;
    buffer = message.duplicate();

    channel.write(buffer);
  }

}
