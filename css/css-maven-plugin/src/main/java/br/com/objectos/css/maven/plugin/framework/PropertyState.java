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

import br.com.objectos.code.java.Java;
import br.com.objectos.code.java.declaration.InterfaceCode;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkPropertyState;
import objectos.util.UnmodifiableList;

final class PropertyState {

  private final Identifier argumentName;

  private final Identifier interfaceName;

  private final UnmodifiableList<PropertyStyle> styles;

  PropertyState(FrameworkPropertyState value, UnmodifiableList<PropertyStyle> styles) {
    var name = value.name();

    argumentName = Java.id(name);

    interfaceName = Java.id(value.interfaceName());

    this.styles = styles;
  }

  public final void acceptDefinitionMethod(MethodCode.Builder method) {
    for (int i = 0, size = styles.size(); i < size; i++) {
      var style = styles.get(i);

      var styleMethodInvocation = style.generateMethodInvocation(interfaceName, argumentName);

      method.addStatement(styleMethodInvocation);
    }
  }

  public final InterfaceCode generateIface() {
    var b = InterfaceCode.builder();

    b.addModifier(Modifiers.PUBLIC);

    b.simpleName(interfaceName.name());

    for (int i = 0, size = styles.size(); i < size; i++) {
      var style = styles.get(i);

      var field = style.generateInterfaceField();

      b.addField(field);
    }

    return b.build();
  }

}