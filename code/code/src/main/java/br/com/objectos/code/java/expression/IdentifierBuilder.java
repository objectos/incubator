/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.java.expression;

import objectos.lang.Checks;

public class IdentifierBuilder {

  private final StringBuilder sb = new StringBuilder();

  public final IdentifierBuilder append(String string) {
    Checks.checkNotNull(string, "string == null");
    sb.append(string);
    return this;
  }

  public final Identifier build() {
    return IdentifierImpl.id0(sb.toString());
  }

  public final IdentifierBuilder repeat(char c, int times) {
    if (!Character.isJavaIdentifierPart(c)) {
      throw new IllegalArgumentException(
          c + " is not a valid identifier character."
      );
    }

    for (int i = 0; i < times; i++) {
      sb.append(c);
    }

    return this;
  }

}
