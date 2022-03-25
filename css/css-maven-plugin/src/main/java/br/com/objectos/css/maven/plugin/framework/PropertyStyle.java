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

import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._protected;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.init;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.nl;

import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.expression.ArgumentsElement;
import br.com.objectos.code.java.expression.ExpressionName;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;

class PropertyStyle {

  private final ImmutableList<String> methodNames;
  private final Prefix prefix;
  private final NamedValue value;

  PropertyStyle(NamedValue value, Prefix prefix, ImmutableList<String> methodNames) {
    this.value = value;
    this.prefix = prefix;
    this.methodNames = methodNames;
  }

  public final FieldCode generateField() {
    return field(
        _public(), _static(), _final(), FrameworkTypes._ClassSelector,
        init(
            value.getFieldName(),
            FrameworkTypes._Css.invoke("dot", value.getClassName(prefix))
        )
    );
  }

  public final FieldCode generateField(PropertyClass pclass) {
    return field(
        _protected(), _static(), _final(), FrameworkTypes._ClassSelector,
        init(
            value.getFieldName(prefix),
            pclass.className.id(value.getFieldName())
        )
    );
  }

  public final FieldCode generateInterfaceField(NamedAtMedia query) {
    return field(
        FrameworkTypes._ClassSelector,
        init(
            value.getFieldName(),
            FrameworkTypes._Css.invoke("dot", value.getClassName(query, prefix))
        )
    );
  }

  public final FieldCode generateInterfaceField(
      NamedAtMedia query, NamedClass propertyClassName) {
    return field(
        FrameworkTypes._ClassSelector,
        init(
            value.getFieldName(prefix),
            propertyClassName.id(query.simpleName).id(value.getFieldName())
        )
    );
  }

  public final MethodInvocation generateMethodInvocation() {
    return generateMethodInvocation0(value.getFieldName());
  }

  public final MethodInvocation generateMethodInvocation(Identifier simpleName) {
    return generateMethodInvocation0(simpleName.id(value.getFieldName()));
  }

  private MethodInvocation generateMethodInvocation0(ExpressionName name) {
    MutableList<ArgumentsElement> args;
    args = MutableList.create();

    args.add(nl());
    args.add(name);
    args.add(nl());

    for (String methodName : methodNames) {
      MethodInvocation propertyMethod;
      propertyMethod = value.invokePropertyMethod(methodName);

      args.add(propertyMethod);

      args.add(nl());
    }

    return invoke("style", args);
  }

}
