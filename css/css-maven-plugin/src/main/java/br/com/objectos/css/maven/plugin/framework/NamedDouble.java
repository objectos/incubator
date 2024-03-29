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

import br.com.objectos.code.java.expression.MethodInvocation;

class NamedDouble extends NamedValue {

  private final double value;

  NamedDouble(String name, double value) {
    super(name);
    this.value = value;
  }

  @Override
  public final MethodInvocation invokePropertyMethod(String methodName) {
    return invoke(methodName, l(value));
  }

  @Override
  final boolean equalsImpl(NamedValue obj) {
    NamedDouble that = (NamedDouble) obj;
    return value == that.value;
  }

  @Override
  final Object hashValue() {
    return Double.valueOf(value);
  }
  
}
