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
package br.com.objectos.css.maven.plugin.framework;

import objectos.css.config.framework.ConfigurationDsl.FrameworkNamedValue;
import objectos.css.config.framework.ConfigurationDsl.FrameworkNamedValueSet;
import objectos.util.UnmodifiableList;

class NamedValueSet extends AbstractFrameworkObject implements FrameworkNamedValueSet {

  private final UnmodifiableList<FrameworkNamedValue> set;

  NamedValueSet(UnmodifiableList<FrameworkNamedValue> set) {
    this.set = set;
  }

  @Override
  final void acceptPropertyBuilder(Property.Builder builder) {
    for (FrameworkNamedValue value : set) {
      value.acceptFrameworkObjectVisitor(builder);
    }
  }

}
