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

import br.com.objectos.formal.testing.Token;

public class HtmlDtd implements Token {

  private final String value;

  public HtmlDtd(String value) {
    this.value = value;
  }

  public HtmlDtd(String s0, String value, String s1) {
    this.value = value;
  }

  public HtmlDtd(HtmlSymbol open, String value, String close) {
    this.value = value;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof HtmlDtd)) {
      return false;
    }
    HtmlDtd that = (HtmlDtd) obj;
    return value.equals(that.value);
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return value;
  }

}