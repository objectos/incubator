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

import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworMultiElement;
import br.com.objectos.css.maven.plugin.framework.NamedMulti.Invocation;
import br.com.objectos.css.type.Value;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

class MultiElement extends AbstractFrameworkObject implements FrameworMultiElement {

  private final ImmutableList<Value> values;

  MultiElement(ImmutableList<Value> values) {
    this.values = values;
  }

  @Override
  final void acceptNamedMultiInvocation(Invocation invocation) {
    invocation.addNewLine();

    MutableList<Argument> arguments;
    arguments = MutableList.create();

    for (int i = 0; i < values.size(); i++) {
      Value value;
      value = values.get(i);

      Argument argument;
      argument = ArgumentValueVisitor.INSTANCE.accept(value);

      arguments.add(argument);
    }

    invocation.addElement(arguments);
  }

}