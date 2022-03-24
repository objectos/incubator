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

import br.com.objectos.core.object.Checks;
import br.com.objectos.http.media.MediaType;
import java.io.IOException;
import java.util.Objects;

public abstract class Header implements SocketWritable {

  private final String name;

  private Header(String name) {
    this.name = name;
  }

  public static Header accessControlAllowOrigin(String origin) {
    Checks.checkNotNull(origin, "origin == null");
    return new StringHeader("Access-Control-Allow-Origin", origin);
  }

  public static Header contentLength(long size) {
    return new LongHeader("Content-length", size);
  }

  public static Header contentType(MediaType type) {
    return new StringHeader("Content-type", type.qualifiedName());
  }

  public static Header location(String location) {
    Checks.checkNotNull(location, "location == null");
    return new StringHeader("Location", location);
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Header)) {
      return false;
    }
    Header that = (Header) obj;
    return Objects.equals(getClass(), that.getClass())
        && Objects.equals(name, that.name)
        && Objects.equals(value(), that.value());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(name, value());
  }

  public String name() {
    return name;
  }

  public final String valueToString() {
    try {
      StringSocketWriter writer = new StringSocketWriter();
      writeValue(writer);
      return writer.toString();
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  @Override
  public final String toString() {
    try {
      StringSocketWriter writer = new StringSocketWriter();
      writeTo(writer);
      return writer.toString();
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  @Override
  public final void writeTo(SocketWriter writer) throws IOException {
    writer.writeString(name);
    writer.writeString(": ");
    writeValue(writer);
  }

  abstract Object value();

  abstract void writeValue(SocketWriter writer) throws IOException;

  private static class LongHeader extends Header {

    private final long value;

    public LongHeader(String name, long value) {
      super(name);
      this.value = value;
    }

    @Override
    Object value() {
      return value;
    }

    @Override
    void writeValue(SocketWriter writer) throws IOException {
      writer.writeString(Long.toString(value));
    }

  }

  private static class StringHeader extends Header {

    private final String value;

    StringHeader(String name, String value) {
      super(name);
      this.value = value;
    }

    @Override
    Object value() {
      return value;
    }

    @Override
    void writeValue(SocketWriter writer) throws IOException {
      writer.writeString(value);
    }

  }

}