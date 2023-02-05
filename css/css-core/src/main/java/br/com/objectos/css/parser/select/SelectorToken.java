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

import br.com.objectos.css.parser.IsTerminal;

enum SelectorToken implements IsTerminal {

  HASH("#"),

  DOT("."),

  ATTR_OPEN("["),

  ATTR_CLOSE("]"),

  STAR("*");

  private final String value;

  private SelectorToken(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

}