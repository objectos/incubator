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

import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._static;
import static br.com.objectos.code.java.Java.field;
import static br.com.objectos.code.java.Java.init;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.nl;

import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.expression.ArgumentsElement;
import br.com.objectos.code.java.expression.ExpressionName;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.MethodInvocation;
import objectos.util.GrowableList;
import objectos.util.UnmodifiableList;

class PropertyStyle {

  private final UnmodifiableList<String> methodNames;

  private final NamedValue value;

  PropertyStyle(NamedValue value, UnmodifiableList<String> methodNames) {
    this.value = value;
    this.methodNames = methodNames;
  }

  public final FieldCode generateField() {
    return field(
      _public(), _static(), _final(), FrameworkTypes._ClassSelector,
      init(
        value.getFieldName(),
        FrameworkTypes._Css.invoke("randomDot", l(5))
      )
    );
  }

  public final FieldCode generateInterfaceField() {
    return field(
      FrameworkTypes._ClassSelector,
      init(
        value.getFieldName(),
        FrameworkTypes._Css.invoke("randomDot", l(5))
      )
    );
  }

  public final MethodInvocation generateMethodInvocation() {
    return generateMethodInvocation0(value.getFieldName(), null);
  }

  public final MethodInvocation generateMethodInvocation(Identifier simpleName) {
    var fieldName = value.getFieldName();

    var id = simpleName.id(fieldName);

    return generateMethodInvocation0(id, null);
  }

  public final MethodInvocation generateMethodInvocation(
      Identifier simpleName, Identifier argumentName) {
    var fieldName = value.getFieldName();

    var id = simpleName.id(fieldName);

    return generateMethodInvocation0(id, argumentName);
  }

  private MethodInvocation generateMethodInvocation0(ExpressionName arg1, ExpressionName arg2) {
    var args = new GrowableList<ArgumentsElement>();

    args.add(nl());
    args.add(arg1);

    if (arg2 != null) {
      args.add(arg2);
    }

    args.add(nl());

    for (var methodName : methodNames) {
      var propertyMethod = value.invokePropertyMethod(methodName);

      args.add(propertyMethod);

      args.add(nl());
    }

    return invoke("style", args);
  }

}
