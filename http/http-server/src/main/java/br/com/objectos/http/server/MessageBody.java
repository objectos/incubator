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
import java.util.Objects;

public abstract class MessageBody implements SocketWritable {

  private static final MessageBody EMPTY = new MessageBody() {
    @Override
    Object value() {
      return null;
    }
  };

  private MessageBody() {}

  public static MessageBody empty() {
    return EMPTY;
  }

  public static MessageBody ofByteSource(InputStreamSource source) {
    return new ByteSourceMessageBody(source);
  }

  public static MessageBody ofString(String value) {
    return new StringMessageBody(value);
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof MessageBody)) {
      return false;
    }
    MessageBody that = (MessageBody) obj;
    return Objects.equals(getClass(), that.getClass())
        && Objects.equals(value(), that.value());
  }

  @Override
  public final int hashCode() {
    return value().hashCode();
  }

  @Override
  public void writeTo(SocketWriter writer) throws IOException {}

  abstract Object value();

  private static class ByteSourceMessageBody extends MessageBody {

    private final InputStreamSource source;

    public ByteSourceMessageBody(InputStreamSource source) {
      this.source = source;
    }

    @Override
    public void writeTo(SocketWriter writer) throws IOException {
      writer.newLine();
      writer.newLine();
      writer.write(source);
    }

    @Override
    Object value() {
      return source;
    }

  }

  private static class StringMessageBody extends MessageBody {

    private final String value;

    public StringMessageBody(String value) {
      this.value = value;
    }

    @Override
    public void writeTo(SocketWriter writer) throws IOException {
      writer.newLine();
      writer.newLine();
      writer.writeString(value);
    }

    @Override
    Object value() {
      return value;
    }

  }

}