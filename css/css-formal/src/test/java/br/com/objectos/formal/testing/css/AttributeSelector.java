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

import br.com.objectos.formal.testing.Node;
import java.util.Objects;

public class AttributeSelector implements Node {

  private final String attribute;
  private final String value;
  private final AttributeOperator operator;

  public AttributeSelector(String attribute, String value, AttributeOperator operator) {
    this.attribute = attribute;
    this.value = value;
    this.operator = operator;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof AttributeSelector)) {
      return false;
    }
    AttributeSelector that = (AttributeSelector) obj;
    return attribute.equals(that.attribute)
        && value.equals(that.value)
        && operator.equals(that.operator);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(attribute, value, operator);
  }

  @Override
  public final String toString() {
    return "[" + attribute + operator + value + "]";
  }

}