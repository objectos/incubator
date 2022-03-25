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
package br.com.objectos.formal.testing.css;

import br.com.objectos.formal.testing.IsBrick;
import br.com.objectos.formal.testing.Token;

public class AttributeValue implements Token {

  private final AttributeValueBrick value;

  public AttributeValue(IsBrick value) {
    this.value = (AttributeValueBrick) value;
  }

  public static AttributeValue unquoted(String string) {
    return new AttributeValue(new Unquoted(string));
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof AttributeValue)) {
      return false;
    }
    AttributeValue that = (AttributeValue) obj;
    return value.equals(that.value);
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  public final String text() {
    return value.text();
  }

  @Override
  public final String toString() {
    StringBuilder s = new StringBuilder();
    value.toString(s);
    return s.toString();
  }

  static class DoubleQuoted extends AbstractString {
    DoubleQuoted(String value) {
      super(value);
    }

    DoubleQuoted(AttributeQuote open, String value, AttributeQuote close) {
      super(open, value, close);
    }

    @Override
    char quote() {
      return '"';
    }
  }

  static class SingleQuoted extends AbstractString {
    SingleQuoted(String value) {
      super(value);
    }

    SingleQuoted(AttributeQuote open, String value, AttributeQuote close) {
      super(open, value, close);
    }

    @Override
    char quote() {
      return '\'';
    }
  }

  static class Unquoted implements AttributeValueBrick {
    private final String value;

    Unquoted(String value) {
      this.value = value;
    }

    @Override
    public final boolean equals(Object obj) {
      if (!(obj instanceof Unquoted)) {
        return false;
      }
      Unquoted that = (Unquoted) obj;
      return value.equals(that.value);
    }

    @Override
    public final int hashCode() {
      return value.hashCode();
    }

    @Override
    public final String text() {
      return value;
    }

    @Override
    public final void toString(StringBuilder s) {
      s.append(value);
    }
  }

  private static abstract class AbstractString implements AttributeValueBrick {

    private final String value;

    AbstractString(String value) {
      this.value = value;
    }

    AbstractString(AttributeQuote open, String value, AttributeQuote close) {
      this.value = value;
    }

    @Override
    public final boolean equals(Object obj) {
      if (!(obj instanceof AbstractString)) {
        return false;
      }
      AbstractString that = (AbstractString) obj;
      return quote() == that.quote()
          && value.equals(that.value);
    }

    @Override
    public final int hashCode() {
      return value.hashCode();
    }

    @Override
    public final String text() {
      return value;
    }

    @Override
    public final void toString(StringBuilder s) {
      s.append(quote()).append(value).append(quote());
    }

    abstract char quote();

  }

}