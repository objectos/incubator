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

import static br.com.objectos.code.java.Java.javaFile;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.FieldCode;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.java.type.NamedClass;
import objectos.util.UnmodifiableList;

class PropertyClass {

  final NamedClass className;
  final UnmodifiableList<PropertyAtMedia> queries;
  final UnmodifiableList<PropertyStyle> styles;

  PropertyClass(Builder builder) {
    className = builder.className();
    styles = builder.styles();
    queries = builder.queries();
  }

  public final void execute(ConfigurationAdapter adapter) {
    adapter.writeJavaFile(
        javaFile(
            className.getPackage(),
            classCode()
        )
    );
  }

  private ClassCode classCode() {
    ClassCode.Builder c;
    c = ClassCode.builder();

    c.addAnnotation(FrameworkMojo.GENERATED);

    c.addModifier(Modifiers.PUBLIC, Modifiers.FINAL);

    c.simpleName(className);

    c.superclass(FrameworkTypes._AbstractStyleSheet);

    MethodCode.Builder definitionMethod;
    definitionMethod = MethodCode.builder();

    definitionMethod.addAnnotation(Override.class);

    definitionMethod.addModifier(Modifiers.PROTECTED, Modifiers.FINAL);

    definitionMethod.name("definition");

    if (!styles.isEmpty()) {
      doStyles(c, definitionMethod);
    }

    for (PropertyAtMedia iface : queries) {
      c.addType(iface.generateIface());

      MethodInvocation mediaMethodInvocation;
      mediaMethodInvocation = iface.generateMediaMethodInvocation();

      definitionMethod.addStatement(mediaMethodInvocation);
    }

    c.addMethod(definitionMethod.build());

    return c.build();
  }

  private void doPropertyStyle(
      ClassCode.Builder c, MethodCode.Builder definitionMethod, PropertyStyle style) {
    FieldCode styleField;
    styleField = style.generateField();

    c.addField(styleField);

    MethodInvocation styleMethodInvocation;
    styleMethodInvocation = style.generateMethodInvocation();

    definitionMethod.addStatement(styleMethodInvocation);
  }

  private void doStyles(ClassCode.Builder c, MethodCode.Builder definitionMethod) {
    PropertyStyle first;
    first = styles.get(0);

    doPropertyStyle(c, definitionMethod, first);

    for (int i = 1; i < styles.size(); i++) {
      PropertyStyle next;
      next = styles.get(i);

      // TODO new line
      // TODO new line

      doPropertyStyle(c, definitionMethod, next);
    }
  }

  static abstract class Builder {

    public final PropertyClass build() {
      return new PropertyClass(this);
    }

    abstract NamedClass className();

    abstract UnmodifiableList<PropertyAtMedia> queries();

    abstract UnmodifiableList<PropertyStyle> styles();

  }

}
