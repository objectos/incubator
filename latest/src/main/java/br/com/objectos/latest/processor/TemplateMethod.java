/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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

import java.util.Iterator;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeMirror;

final class TemplateMethod {

  private static final String IDENTATION = "  ";

  private final ExecutableElement element;

  private TemplateMethod(ExecutableElement element) {
    this.element = element;
  }

  public static void createAll(TypeElement typeElement, List<TemplateMethod> methods) {
    List<? extends Element> enclosedElements;
    enclosedElements = typeElement.getEnclosedElements();

    for (int i = 0, size = enclosedElements.size(); i < size; i++) {
      Element candidate;
      candidate = enclosedElements.get(i);

      ElementKind kind;
      kind = candidate.getKind();

      if (kind != ElementKind.METHOD) {
        continue;
      }

      ExecutableElement methodElement;
      methodElement = (ExecutableElement) candidate;

      TemplateMethod method;
      method = new TemplateMethod(methodElement);

      methods.add(method);
    }
  }

  public final void write(LatestWriter w) {
    w.writeJavadoc(IDENTATION, element);

    w.write(IDENTATION);

    TypeMirror returnType;
    returnType = element.getReturnType();

    w.write(returnType.toString());

    w.write(' ');

    Name methodName;
    methodName = element.getSimpleName();

    w.write(methodName.toString());

    w.write('(');

    List<? extends VariableElement> parameters;
    parameters = element.getParameters();

    Iterator<? extends VariableElement> params;
    params = parameters.iterator();

    if (params.hasNext()) {
      VariableElement param;
      param = params.next();

      writeParam(w, param, params.hasNext());

      while (params.hasNext()) {
        w.write(", ");

        param = params.next();

        writeParam(w, param, params.hasNext());
      }
    }

    w.write(')');

    List<? extends TypeMirror> thrownTypes = element.getThrownTypes();

    Iterator<? extends TypeMirror> thrownIter = thrownTypes.iterator();

    if (thrownIter.hasNext()) {
      w.write(" throws ");

      TypeMirror thrown;
      thrown = thrownIter.next();

      w.write(thrown.toString());

      while (thrownIter.hasNext()) {
        w.write(", ");

        thrown = thrownIter.next();

        w.write(thrown.toString());
      }
    }

    w.write(';');
  }

  private void writeParam(
      LatestWriter w, VariableElement param, boolean hasNext) {
    boolean varArgs;
    varArgs = element.isVarArgs();

    TypeMirror type;
    type = param.asType();

    if (varArgs && !hasNext) {
      ArrayType arrayType;
      arrayType = (ArrayType) type;

      TypeMirror componentType;
      componentType = arrayType.getComponentType();

      w.write(componentType.toString());

      w.write("...");
    } else {
      w.write(type.toString());
    }

    w.write(' ');

    Name name;
    name = param.getSimpleName();

    w.write(name.toString());
  }

}