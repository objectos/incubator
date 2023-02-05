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

import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkAtMediaSet;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkNamedValue;
import br.com.objectos.css.type.Value;

class NamedAtMediaSet extends AbstractFrameworkObject implements FrameworkNamedValue {

  private final String name;
  private final FrameworkAtMediaSet set;

  NamedAtMediaSet(String name, FrameworkAtMediaSet set) {
    this.name = name;
    this.set = set;
  }

  @Override
  final void acceptPropertyBuilder(Property.Builder builder) {
    Adapter adapter = new Adapter(builder);
    set.acceptFrameworkObjectVisitor(adapter);
  }

  class Adapter {

    private final Property.Builder builder;
    private String name;

    Adapter(Property.Builder builder) {
      this.builder = builder;
    }

    public final void addValue(Value value) {
      NamedValueSingle single;
      single = new NamedValueSingle(getName(), value);

      builder.addNamedValue(single);
    }

    public final void setName(String name) {
      this.name = name;
    }

    private String getName() {
      return NamedAtMediaSet.this.name + '-' + name;
    }

  }

}
