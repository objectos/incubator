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

import static br.com.objectos.code.java.Java.id;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkAtMedia;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkAtMediaElement;
import br.com.objectos.css.maven.plugin.framework.PropertyAtMedia.Invocation;
import objectos.util.ImmutableList;

class NamedAtMedia extends AbstractFrameworkObject implements FrameworkAtMedia {

  final ImmutableList<FrameworkAtMediaElement> elements;

  final String name;

  final Identifier simpleName;

  NamedAtMedia(String name, ImmutableList<FrameworkAtMediaElement> elements) {
    this.name = name;
    this.elements = elements;

    simpleName = id(JavaNames.toIdentifier(name));
  }

  public final String toClassName(String className) {
    return name + '-' + className;
  }

  @Override
  final void acceptNamedAtMediaSetAdapter(NamedAtMediaSet.Adapter adapter) {
    adapter.setName(name);
    for (FrameworkAtMediaElement element : elements) {
      element.acceptFrameworkObjectVisitor(adapter);
    }
  }

  @Override
  final void acceptPropertyAtMediaInvocation(Invocation invocation) {
    for (FrameworkAtMediaElement element : elements) {
      element.acceptFrameworkObjectVisitor(invocation);
    }
  }

  @Override
  final void acceptPropertyBuilder(Property.Builder builder) {
    builder.addNamedQuery(this);
  }

}
