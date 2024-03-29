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

import objectos.css.Css;
import objectos.css.select.AttributeSelector;
import objectos.css.select.AttributeValueOperator;
import objectos.css.select.AttributeValueSelector;
import objectos.css.select.ClassSelector;
import objectos.css.select.IdSelector;
import objectos.css.select.SelectorFactory;
import objectos.css.select.TypeSelector;
import objectos.css.select.TypeSelectors;
import objectos.lang.ToString;

class IdentToken implements HasStringValue, ToString.Formattable {

  private final String value;

  IdentToken(String first, String rest) {
    this(first + nullToEmpty(rest));
  }

  private IdentToken(String value) {
    this.value = value;
  }

  // @VisibleForTesting
  static IdentToken of(String value) {
    return new IdentToken(value);
  }

  private static String nullToEmpty(String s) {
    if (s == null) {
      return "";
    }

    return s;
  }

  public final AttributeSelector asAttributeSelector() {
    return Css.attr(value);
  }

  public final AttributeValueSelector asAttributeValueSelector(
      AttributeValueOperator operator, HasStringValue theValue) {
    return SelectorFactory.attr(value, operator, theValue.stringValue());
  }

  public final ClassSelector asClassSelector() {
    return Css.cn(value);
  }

  public final IdSelector asIdSelector() {
    return Css.id(value);
  }

  public final TypeSelector asTypeSelector() {
    return TypeSelectors.getByName(value);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof IdentToken)) {
      return false;
    }
    IdentToken that = (IdentToken) obj;
    return value.equals(that.value);
  }

  @Override
  public final void formatToString(StringBuilder toString, int level) {
    ToString.format(
      toString, level, this,
      "", value
    );
  }

  @Override
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public final String stringValue() {
    return value;
  }

  @Override
  public final String toString() {
    return ToString.of(this);
  }

}