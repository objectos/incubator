/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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
package br.com.objectos.latest.processor;

import br.com.objectos.latest.Concrete;
import br.com.objectos.latest.Concrete.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

class ConcreteConstructor {

  private final ExecutableElement executable;

  ConcreteConstructor(ExecutableElement executable) {
    this.executable = executable;
  }

  public static List<ConcreteConstructor> listOf(
      ProcessingEnvironment processingEnv, TypeElement concrete) {
    List<ConcreteConstructor> result;
    result = new ArrayList<ConcreteConstructor>();

    List<? extends Element> enclosedElements;
    enclosedElements = concrete.getEnclosedElements();

    for (int i = 0; i < enclosedElements.size(); i++) {
      Element element;
      element = enclosedElements.get(i);

      ElementKind kind;
      kind = element.getKind();

      if (kind != ElementKind.CONSTRUCTOR) {
        continue;
      }

      Constructor constructorAnnotation;
      constructorAnnotation = element.getAnnotation(Concrete.Constructor.class);

      if (constructorAnnotation == null) {
        continue;
      }

      ExecutableElement executable;
      executable = ExecutableElement.class.cast(element);

      ConcreteConstructor constructor;
      constructor = new ConcreteConstructor(executable);

      result.add(constructor);
    }

    return result;
  }

  @Override
  public final String toString() {
    return "Constructor";
  }

  final void write(LatestWriter w, String simpleName) {
    w.newLine();

    w.newLine();

    w.writeJavadoc("  ", executable);

    w.write("  ");

    Set<Modifier> modifiers;
    modifiers = executable.getModifiers();

    for (Modifier modifier : modifiers) {
      w.write(modifier.toString());
    }

    if (!modifiers.isEmpty()) {
      w.write(' ');
    }

    w.write(simpleName);

    w.write('(');

    StringBuilder superInvocation;
    superInvocation = new StringBuilder();

    superInvocation.append("super(");

    List<? extends VariableElement> parameters;
    parameters = executable.getParameters();

    if (!parameters.isEmpty()) {
      VariableElement first;
      first = parameters.get(0);

      writeParameter(w, superInvocation, first);
    }

    for (int i = 1; i < parameters.size(); i++) {
      VariableElement next;
      next = parameters.get(i);

      w.write(", ");

      superInvocation.append(", ");

      writeParameter(w, superInvocation, next);
    }

    w.write(')');

    superInvocation.append(");");

    w.write(" {");
    w.newLine();

    w.write("    ");
    w.write(superInvocation.toString());
    w.newLine();

    w.write("  }");
  }

  private void writeParameter(
      LatestWriter w, StringBuilder superInvocation, VariableElement parameter) {
    TypeMirror type;
    type = parameter.asType();

    w.write(type.toString());

    w.write(' ');

    Name simpleName;
    simpleName = parameter.getSimpleName();

    String simpleNameValue;
    simpleNameValue = simpleName.toString();

    w.write(simpleNameValue);

    superInvocation.append(simpleNameValue);
  }

}
