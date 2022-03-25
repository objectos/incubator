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
package br.com.objectos.css.parser.select;

import br.com.objectos.core.object.ToString;
import br.com.objectos.core.string.Strings;
import br.com.objectos.css.Css;
import br.com.objectos.css.select.AttributeSelector;
import br.com.objectos.css.select.AttributeValueOperator;
import br.com.objectos.css.select.AttributeValueSelector;
import br.com.objectos.css.select.ClassSelector;
import br.com.objectos.css.select.IdSelector;
import br.com.objectos.css.select.SelectorFactory;
import br.com.objectos.css.select.TypeSelector;
import br.com.objectos.css.select.TypeSelectors;

class IdentToken implements HasStringValue {

  private final String value;

  IdentToken(String first, String rest) {
    this(first + Strings.nullToEmpty(rest));
  }

  private IdentToken(String value) {
    this.value = value;
  }

  // @VisibleForTesting
  static IdentToken of(String value) {
    return new IdentToken(value);
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
  public final int hashCode() {
    return value.hashCode();
  }

  @Override
  public final String stringValue() {
    return value;
  }

  @Override
  public final String toString() {
    return ToString.toString(this, "", value);
  }

}