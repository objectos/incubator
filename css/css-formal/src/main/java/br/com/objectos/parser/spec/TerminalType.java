/*
 * Copyright (C) 2017-2022 Objectos Software LTDA.
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
package br.com.objectos.parser.spec;

import java.util.Objects;

class TerminalType extends Terminal {

  private final Class<?> type;

  TerminalType(Class<?> type) {
    this.type = Objects.requireNonNull(type);
  }

  @Override
  public final int hashCode() {
    return type.hashCode();
  }

  @Override
  public final boolean matches(Object token) {
    return type.isInstance(token);
  }

  @Override
  public final String toString() {
    return type.getSimpleName();
  }

  @Override
  final boolean equalsImpl(AbstractSymbol o) {
    TerminalType that = (TerminalType) o;
    return type.equals(that.type);
  }

}