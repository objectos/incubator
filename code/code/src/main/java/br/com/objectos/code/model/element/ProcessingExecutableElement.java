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
package br.com.objectos.code.model.element;

import br.com.objectos.code.java.declaration.AccessLevel;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.type.NamedTypeParameter;
import br.com.objectos.code.processing.type.PTypeMirror;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

abstract class ProcessingExecutableElement
    extends ProcessingElement<ExecutableElement>
    implements CanGenerateCompilationError {

  private ImmutableList<ProcessingParameter> parameters;

  private ImmutableList<PTypeMirror> thrownTypes;

  private ImmutableList<NamedTypeParameter> typeParameters;

  ProcessingExecutableElement(ProcessingEnvironment processingEnv,
                              ExecutableElement element) {
    super(processingEnv, element);
  }

  public final AccessLevel getAccessLevel() {
    return getAccessLevelImpl();
  }

  public final String getDocComment() {
    return getDocComment0();
  }

  public final ImmutableList<ProcessingParameter> getParameters() {
    if (parameters == null) {
      parameters = getParameters0();
    }

    return parameters;
  }

  public final ImmutableList<PTypeMirror> getThrownTypes() {
    if (thrownTypes == null) {
      thrownTypes = getThrownTypes0();
    }

    return thrownTypes;
  }

  public final ImmutableList<NamedTypeParameter> getTypeParameters() {
    if (typeParameters == null) {
      typeParameters = getTypeParameters0();
    }

    return typeParameters;
  }

  public final boolean hasAccessLevel(AccessLevel level) {
    return hasAccessLevelImpl(level);
  }

  public final boolean isPrivate() {
    return hasModifier(Modifiers._private());
  }

  public final boolean isProtected() {
    return hasModifier(Modifiers._protected());
  }

  public final boolean isPublic() {
    return hasModifier(Modifiers._public());
  }

  private ImmutableList<ProcessingParameter> getParameters0() {
    MutableList<ProcessingParameter> result;
    result = MutableList.create();

    List<? extends VariableElement> parameterElements;
    parameterElements = element.getParameters();

    for (int i = 0; i < parameterElements.size(); i++) {
      VariableElement parameterElement;
      parameterElement = parameterElements.get(i);

      ProcessingParameter parameter;
      parameter = ProcessingParameter.adapt(processingEnv, parameterElement);

      result.add(parameter);
    }

    return result.toImmutableList();
  }

  private ImmutableList<PTypeMirror> getThrownTypes0() {
    MutableList<PTypeMirror> result;
    result = MutableList.create();

    List<? extends TypeMirror> types;
    types = element.getThrownTypes();

    for (int i = 0; i < types.size(); i++) {
      TypeMirror type;
      type = types.get(i);

      PTypeMirror thrown;
      thrown = PTypeMirror.adapt(processingEnv, type);

      result.add(thrown);
    }

    return result.toImmutableList();
  }

  private ImmutableList<NamedTypeParameter> getTypeParameters0() {
    List<? extends TypeParameterElement> elements;
    elements = element.getTypeParameters();

    return toNamedTypeParameter(elements);
  }

}