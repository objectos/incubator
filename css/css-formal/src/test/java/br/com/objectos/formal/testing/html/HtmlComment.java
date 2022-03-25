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
package br.com.objectos.formal.testing.html;

import br.com.objectos.core.object.ToString;
import br.com.objectos.formal.testing.Token;

public class HtmlComment implements Token {

  private final String value;

  public HtmlComment(HtmlSymbol open, String value, HtmlSymbol close) {
    this.value = value;
  }

  public HtmlComment(String value) {
    this.value = value;
  }

  public HtmlComment(String s0, String value, String s2) {
    this.value = value;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof HtmlComment)) {
      return false;
    }
    HtmlComment that = (HtmlComment) obj;
    return value.equals(that.value);
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public final String toString() {
    return ToString.toString(this, "", value);
  }

}