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
import static br.com.objectos.code.java.Java.nl;

import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.code.java.expression.ArgumentsElement;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworMultiElement;
import objectos.util.UnmodifiableList;
import objectos.util.MutableList;

class NamedMulti extends NamedValue {

  private final UnmodifiableList<FrameworMultiElement> elements;

  NamedMulti(String name, UnmodifiableList<FrameworMultiElement> elements) {
    super(name);
    this.elements = elements;
  }

  @Override
  public final MethodInvocation invokePropertyMethod(String methodName) {
    Invocation invocation = new Invocation(methodName);

    for (int i = 0; i < elements.size(); i++) {
      FrameworMultiElement element;
      element = elements.get(i);

      element.acceptFrameworkObjectVisitor(invocation);
    }

    return invocation.build();
  }

  @Override
  final boolean equalsImpl(NamedValue obj) {
    NamedMulti that = (NamedMulti) obj;
    return elements.equals(that.elements);
  }

  @Override
  final Object hashValue() {
    return elements;
  }

  public class Invocation {

    private final MutableList<ArgumentsElement> arguments = new MutableList<>();

    private final String methodName;

    Invocation(String methodName) {
      this.methodName = methodName;
    }

    public final void addElement(MutableList<Argument> element) {
      MethodInvocation invocation;
      invocation = invoke(methodName, element);

      arguments.add(invocation);
    }

    public final void addNewLine() {
      arguments.add(nl());
    }

    private MethodInvocation build() {
      addNewLine();

      return invoke(methodName, arguments);
    }

  }

}
