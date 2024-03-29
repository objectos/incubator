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
package br.com.objectos.css.parser.select;

import objectos.css.parser.IsTerminal;
import objectos.css.select.PseudoClassSelector;
import objectos.css.select.PseudoClassSelectors;

class PseudoClassToken implements IsTerminal {

  private final String value;

  PseudoClassToken(String start, String value) {
    this.value = value;
  }

  public static PseudoClassToken of(String string) {
    return new PseudoClassToken(null, string);
  }

  public final PseudoClassSelector asPseudoClassSelector() {
    return PseudoClassSelectors.getByName(value);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof PseudoClassToken)) {
      return false;
    }
    PseudoClassToken that = (PseudoClassToken) obj;
    return value.equals(that.value);
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public final String toString() {
    return ":" + value;
  }

}