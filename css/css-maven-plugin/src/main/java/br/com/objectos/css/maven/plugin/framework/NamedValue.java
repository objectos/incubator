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

import static br.com.objectos.code.java.Java.id;

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.MethodInvocation;
import java.util.Objects;
import javax.lang.model.SourceVersion;
import objectos.css.config.framework.ConfigurationDsl.FrameworkNamedValue;
import objectos.css.type.Value;

abstract class NamedValue extends AbstractFrameworkObject implements FrameworkNamedValue {

  private final String name;

  NamedValue(String name) {
    this.name = name;
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof NamedValue)) {
      return false;
    }
    NamedValue that = (NamedValue) obj;
    return getClass().equals(that.getClass())
        && name.equals(that.name)
        && equalsImpl(that);
  }

  public final Identifier getFieldName() {
    char firstChar = name.charAt(0);
    return Character.isDigit(firstChar)
        ? id(JavaNames.toValidMethodName('v' + name))
        : id(toJavaName());
  }

  @Override
  public final int hashCode() {
    return Objects.hash(name, hashValue());
  }

  public abstract MethodInvocation invokePropertyMethod(String methodName);

  @Override
  final void acceptPropertyBuilder(Property.Builder builder) {
    builder.addNamedValue(this);
  }

  abstract boolean equalsImpl(NamedValue obj);

  abstract Object hashValue();

  final Argument toArgument(Value value) {
    return ArgumentValueVisitor.INSTANCE.accept(value);
  }

  private String toJavaName() {
    return SourceVersion.isKeyword(name)
        ? name + "Value"
        : JavaNames.toValidMethodName(name);
  }

}
