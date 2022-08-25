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
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.type.NamedClass;
import objectos.util.UnmodifiableList;

class PropertyClass {

  static abstract class Builder {

    public final PropertyClass build() {
      return new PropertyClass(this);
    }

    abstract NamedClass className();

    abstract UnmodifiableList<PropertyAtMedia> queries();

    abstract UnmodifiableList<PropertyState> states();

    abstract UnmodifiableList<PropertyStyle> styles();

  }

  final NamedClass className;

  final UnmodifiableList<PropertyAtMedia> queries;

  final UnmodifiableList<PropertyState> states;

  final UnmodifiableList<PropertyStyle> styles;

  PropertyClass(Builder builder) {
    className = builder.className();

    states = builder.states();

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
    var c = ClassCode.builder();

    c.addAnnotation(FrameworkMojo.GENERATED);

    c.addModifier(Modifiers.PUBLIC, Modifiers.FINAL);

    c.simpleName(className);

    c.superclass(FrameworkTypes._AbstractStyleSheet);

    var definitionMethod = MethodCode.builder();

    definitionMethod.addAnnotation(Override.class);

    definitionMethod.addModifier(Modifiers.PROTECTED, Modifiers.FINAL);

    definitionMethod.name("definition");

    if (!styles.isEmpty()) {
      doStyles(c, definitionMethod);
    }

    for (var state : states) {
      c.addType(state.generateIface());

      state.acceptDefinitionMethod(definitionMethod);
    }

    for (var query : queries) {
      c.addType(query.generateIface());

      var stmt = query.generateMediaMethodInvocation();

      definitionMethod.addStatement(stmt);
    }

    c.addMethod(definitionMethod.build());

    return c.build();
  }

  private void doPropertyStyle(
      ClassCode.Builder c, MethodCode.Builder definitionMethod, PropertyStyle style) {
    var styleField = style.generateField();

    c.addField(styleField);

    var styleMethodInvocation = style.generateMethodInvocation();

    definitionMethod.addStatement(styleMethodInvocation);
  }

  private void doStyles(ClassCode.Builder c, MethodCode.Builder definitionMethod) {
    var first = styles.get(0);

    doPropertyStyle(c, definitionMethod, first);

    for (int i = 1; i < styles.size(); i++) {
      var next = styles.get(i);

      // TODO new line
      // TODO new line

      doPropertyStyle(c, definitionMethod, next);
    }
  }

}
