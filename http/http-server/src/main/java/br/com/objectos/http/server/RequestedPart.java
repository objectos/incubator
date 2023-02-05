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

import br.com.objectos.http.path.RouteParser;
import java.util.Objects;

public abstract class RequestedPart {

  private final char[] chars;
  private final int start;
  private final int length;

  public RequestedPart(char[] chars, int start, int length) {
    this.chars = chars;
    this.start = start;
    this.length = length;
  }

  @Override
  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof RequestedPart)) {
      return false;
    }
    RequestedPart that = (RequestedPart) obj;
    return Objects.equals(getClass(), that.getClass())
        && Objects.equals(value(), that.value());
  }

  @Override
  public final int hashCode() {
    return value().hashCode();
  }

  String catchAllValue() {
    return new String(chars, start, chars.length - start);
  }

  public boolean matchesCatchAllSlug(RouteParser parser) {
    return false;
  }

  public boolean matchesFixedSlug(RouteParser parser, String value) {
    return false;
  }

  public boolean matchesIntParamSlug(RouteParser parser) {
    return false;
  }

  public boolean matchesStringParamSlug(RouteParser parser) {
    return false;
  }

  String value() {
    return new String(chars, start, length);
  }

}