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
package br.com.objectos.formal.testing.css;

import br.com.objectos.formal.testing.Token;

public class AttributeName implements Token {

  private final String name;

  public AttributeName(String name) {
    this.name = name;
  }

  public AttributeSelector asAttributeSelector(AttributeOperator operator, String value) {
    return operator.selector(name, value);
  }

  public AttributeSelector asAttributeSelector(AttributeSuffix suffix) {
    if (suffix != null) {
      return suffix.asAttributeSelector(name);
    } else {
      return AttributeOperator.ATTRIBUTE.selector(name, "");
    }
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof AttributeName)) {
      return false;
    }
    AttributeName that = (AttributeName) obj;
    return name.equals(that.name);
  }

  @Override
  public final int hashCode() {
    return name.hashCode();
  }

  @Override
  public final String toString() {
    return "AttributeName(" + name + ")";
  }

}