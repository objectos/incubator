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

import br.com.objectos.http.path.Argument;
import br.com.objectos.http.path.RouteParser;

class IntRequestedPart extends RequestedPart {

  public IntRequestedPart(char[] chars, int start, int length) {
    super(chars, start, length);
  }

  @Override
  public final boolean matchesCatchAllSlug(RouteParser parser) {
    String value = catchAllValue();
    return parser.add(Argument.stringArg(value));
  }

  @Override
  public final boolean matchesFixedSlug(RouteParser parser, String value) {
    return value().equals(value);
  }

  @Override
  public final boolean matchesIntParamSlug(RouteParser parser) {
    int value = Integer.parseInt(value(), 10);
    return parser.add(Argument.intArg(value));
  }

  @Override
  public final boolean matchesStringParamSlug(RouteParser parser) {
    return parser.add(Argument.stringArg(value()));
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append("int{")
        .append(value())
        .append('}')
        .toString();
  }

}