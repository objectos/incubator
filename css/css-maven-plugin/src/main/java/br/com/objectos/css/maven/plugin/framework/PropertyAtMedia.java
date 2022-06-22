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

import static br.com.objectos.code.java.Java.expressionName;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.nl;

import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.declaration.InterfaceCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.expression.Argument;
import br.com.objectos.code.java.expression.ArgumentsElement;
import br.com.objectos.code.java.expression.ExpressionName;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.MediaType;
import objectos.util.UnmodifiableList;
import objectos.util.GrowableList;

class PropertyAtMedia {

  private final NamedAtMedia media;
  private final UnmodifiableList<PropertyStyle> styles;

  PropertyAtMedia(NamedAtMedia query, UnmodifiableList<PropertyStyle> styles) {
    this.media = query;
    this.styles = styles;
  }

  public final InterfaceCode generateIface() {
    InterfaceCode.Builder b;
    b = InterfaceCode.builder();

    b.addModifier(Modifiers.PUBLIC);

    b.simpleName(media.simpleName.name());

    for (int i = 0; i < styles.size(); i++) {
      PropertyStyle style;
      style = styles.get(i);

      FieldCode field;
      field = style.generateInterfaceField(media);

      b.addField(field);
    }

    return b.build();
  }

  public final MethodInvocation generateMediaMethodInvocation() {
    Invocation invocation = new Invocation();

    invocation.addNewLine();

    media.acceptFrameworkObjectVisitor(invocation);

    for (int i = 0; i < styles.size(); i++) {
      PropertyStyle style;
      style = styles.get(i);

      MethodInvocation styleMethodInvocation;
      styleMethodInvocation = style.generateMethodInvocation(media.simpleName);

      invocation.addNewLine();

      invocation.addNewLine();

      invocation.addArgument(styleMethodInvocation);
    }

    invocation.addNewLine();

    return invocation.build();
  }

  public class Invocation implements MediaType.Visitor {

    private final GrowableList<ArgumentsElement> arguments = new GrowableList<>();

    public final void addArgument(Argument argument) {
      arguments.add(argument);
    }

    public final void addNewLine() {
      arguments.add(nl());
    }

    @Override
    public final void visitMediaType(MediaType type) {
      ExpressionName fieldName;
      fieldName = expressionName(AbstractStyleSheet.class, type.getName());

      arguments.add(fieldName);
    }

    private MethodInvocation build() {
      return invoke("media", arguments);
    }

  }

}
