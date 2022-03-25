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

public class AttributeSuffix implements Node {

  private final AttributeOperator operator;
  private final AttributeValue value;

  public AttributeSuffix(AttributeOperator operator, AttributeValue value) {
    this.operator = operator;
    this.value = value;
  }

  public AttributeSelector asAttributeSelector(String name) {
    return operator.selector(name, value.text());
  }

}