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

import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkAtMediaDeclaration;
import br.com.objectos.css.maven.plugin.framework.PropertyAtMedia.Invocation;
import br.com.objectos.css.property.StandardPropertyName;
import br.com.objectos.css.type.LengthUnit;
import br.com.objectos.css.type.Value;

final class AtMediaDeclaration
    extends AbstractFrameworkObject implements FrameworkAtMediaDeclaration {

  private final StandardPropertyName propertyName;
  private final Value value;

  AtMediaDeclaration(StandardPropertyName propertyName, Value value) {
    this.propertyName = propertyName;
    this.value = value;
  }

  @Override
  final void acceptNamedAtMediaSetAdapter(NamedAtMediaSet.Adapter adapter) {
    adapter.addValue(value);
  }

  @Override
  final void acceptPropertyAtMediaInvocation(Invocation invocation) {
    MethodInvocation declarationMethodInvocation;
    declarationMethodInvocation = generateMethodInvocation();

    invocation.addArgument(declarationMethodInvocation);
  }

  private MethodInvocation generateMethodInvocation() {
    return invoke(getMethodName(), getValue());
  }

  private String getMethodName() {
    return propertyName.getJavaName();
  }

  private Argument getValue() {
    return ThisValueVisitor.INSTANCE.accept(value);
  }

  private static class ThisValueVisitor extends SimpleValueVisitor<Argument> {

    static final ThisValueVisitor INSTANCE = new ThisValueVisitor();

    @Override
    public final void createLength(LengthUnit unit, double value) {
      set(invoke(unit.getName(), l(value)));
    }

    @Override
    public final void createLength(LengthUnit unit, int value) {
      set(invoke(unit.getName(), l(value)));
    }

  }

}
