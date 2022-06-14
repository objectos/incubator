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

import static br.com.objectos.code.java.Java.invoke;

import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.css.type.Value;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

class NamedArguments extends NamedValue {

  private final ImmutableList<Value> values;

  NamedArguments(String name, ImmutableList<Value> values) {
    super(name);
    this.values = values;
  }

  @Override
  public final MethodInvocation invokePropertyMethod(String methodName) {
    MutableList<Argument> arguments;
    arguments = new MutableList<>();

    for (Value v : values) {
      Argument argument = toArgument(v);

      arguments.add(argument);
    }

    return invoke(methodName, arguments);
  }

  @Override
  final boolean equalsImpl(NamedValue obj) {
    NamedArguments that = (NamedArguments) obj;

    return values.equals(that.values);
  }

  @Override
  final Object hashValue() {
    return values;
  }

}
