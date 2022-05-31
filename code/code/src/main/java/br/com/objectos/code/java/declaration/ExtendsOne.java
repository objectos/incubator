/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.java.declaration;

import br.com.objectos.code.java.type.NamedClassOrParameterized;
import objectos.lang.Check;

public final class ExtendsOne
    implements
    ClassCodeElement,
    InterfaceCodeElement {

  private final NamedClassOrParameterized type;

  private ExtendsOne(NamedClassOrParameterized type) {
    this.type = type;
  }

  public static ExtendsOne _extends(NamedClassOrParameterized type) {
    Check.notNull(type, "type == null");
    return new ExtendsOne(type);
  }

  @Override
  public final void acceptClassCodeBuilder(ClassCode.Builder builder) {
    builder.superclass(type);
  }

  @Override
  public final void acceptInterfaceCodeBuilder(InterfaceCode.Builder builder) {
    builder.addExtends(type);
  }

}
