/*
 * Copyright (C) 2017-2023 Objectos Software LTDA.
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
import java.util.Objects;

public abstract class HtmlCharRef implements Token {

  private final String value;
  private final HtmlSymbol semi;

  HtmlCharRef(String value, HtmlSymbol semi) {
    this.value = value;
    this.semi = semi;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof HtmlCharRef)) {
      return false;
    }
    HtmlCharRef that = (HtmlCharRef) obj;
    return getClass().equals(that.getClass())
        && value.equals(that.value)
        && Objects.equals(semi, that.semi);
  }

  @Override
  public final int hashCode() {
    return super.hashCode();
  }

  @Override
  public final String toString() {
    StringBuilder s = new StringBuilder().append('&');
    toStringValue(s, value);
    if (semi != null) {
      s.append(';');
    }
    return s.toString();
  }

  abstract void toStringValue(StringBuilder s, String value);

  public static class Hex extends HtmlCharRef {
    private final HtmlSymbol open;

    public Hex(HtmlSymbol open, String value, HtmlSymbol semi) {
      super(value, semi);
      this.open = open;
    }

    @Override
    void toStringValue(StringBuilder s, String value) {
      open.toString(s);
      s.append(value);
    }
  }

  public static class Named extends HtmlCharRef {
    public Named(String value, HtmlSymbol semi) {
      super(value, semi);
    }

    public Named(HtmlSymbol open, String value, HtmlSymbol semi) {
      super(value, semi);
    }

    @Override
    void toStringValue(StringBuilder s, String value) {
      s.append(value);
    }
  }

}