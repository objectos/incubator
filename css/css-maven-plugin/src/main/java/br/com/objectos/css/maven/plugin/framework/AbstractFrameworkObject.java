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
package br.com.objectos.css.maven.plugin.framework;

import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkObject;
import br.com.objectos.css.maven.plugin.framework.PropertyAtMedia.Invocation;

abstract class AbstractFrameworkObject implements FrameworkObject {

  @Override
  public final void acceptFrameworkObjectVisitor(Object visitor) {
    if (visitor instanceof NamedAtMediaSet.Adapter) {
      NamedAtMediaSet.Adapter adapter;
      adapter = (NamedAtMediaSet.Adapter) visitor;

      acceptNamedAtMediaSetAdapter(adapter);
    }

    else if (visitor instanceof NamedMulti.Invocation) {
      NamedMulti.Invocation invocation;
      invocation = (NamedMulti.Invocation) visitor;

      acceptNamedMultiInvocation(invocation);
    }

    else if (visitor instanceof Property.Builder) {
      Property.Builder builder;
      builder = (Property.Builder) visitor;

      acceptPropertyBuilder(builder);
    }

    else if (visitor instanceof PropertyAtMedia.Invocation) {
      PropertyAtMedia.Invocation invocation;
      invocation = (PropertyAtMedia.Invocation) visitor;

      acceptPropertyAtMediaInvocation(invocation);
    }
  }

  void acceptNamedAtMediaSetAdapter(NamedAtMediaSet.Adapter adapter) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  void acceptNamedMultiInvocation(NamedMulti.Invocation invocation) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  void acceptPropertyAtMediaInvocation(Invocation invocation) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

  void acceptPropertyBuilder(Property.Builder builder) {
    throw new UnsupportedOperationException("Implement me @ " + getClass().getSimpleName());
  }

}
