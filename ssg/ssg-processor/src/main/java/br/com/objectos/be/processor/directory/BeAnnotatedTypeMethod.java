/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.processor.directory;

import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._return;
import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.overriding;

import br.com.objectos.be.processor.TypeNames;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingMethod;
import br.com.objectos.code.model.element.ProcessingParameter;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.type.PDeclaredType;
import br.com.objectos.code.processing.type.PTypeMirror;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;

class BeAnnotatedTypeMethod {

  private final ProcessingMethod processingMethod;
  private final NamedClass returnClassName;

  BeAnnotatedTypeMethod(ProcessingMethod processingMethod, NamedClass returnClassName) {
    this.processingMethod = processingMethod;
    this.returnClassName = returnClassName;
  }

  static ImmutableList<BeAnnotatedTypeMethod> fromBeAnnotatedProcessingType(ProcessingType type) {
    MutableList<BeAnnotatedTypeMethod> result;
    result = MutableList.create();

    ImmutableList<ProcessingMethod> methods;
    methods = type.getDeclaredOrInheritedMethods();

    for (int i = 0; i < methods.size(); i++) {
      ProcessingMethod method;
      method = methods.get(i);

      fromBeAnnotatedProcessingType0(result, method);
    }

    return result.toImmutableList();
  }

  private static void fromBeAnnotatedProcessingType0(
      MutableList<BeAnnotatedTypeMethod> result, ProcessingMethod method) {
    if (!method.isAbstract()) {
      return;
    }

    ImmutableList<ProcessingParameter> parameters;
    parameters = method.getParameters();

    if (!parameters.isEmpty()) {
      return;
    }

    PTypeMirror returnType;
    returnType = method.getReturnType();

    if (!returnType.isDeclaredType()) {
      return;
    }

    PDeclaredType returnClassOrParameterized;
    returnClassOrParameterized = returnType.toDeclaredType();

    ProcessingType returnProcessingType;
    returnProcessingType = returnClassOrParameterized.toProcessingType();

    if (!isReturnTypeOfBeType(returnProcessingType)) {
      return;
    }

    NamedClass returnClassName;
    returnClassName = returnProcessingType.getName();

    BeAnnotatedTypeMethod beAnnotatedTypeMethod;
    beAnnotatedTypeMethod = new BeAnnotatedTypeMethod(method, returnClassName);

    result.add(beAnnotatedTypeMethod);
  }

  private static boolean isDirectlyAnnotatedWithMetaBeResource(ProcessingAnnotation annotation) {
    ImmutableList<ProcessingAnnotation> annotations;
    annotations = annotation.getDirectlyPresentAnnotations();

    for (int i = 0; i < annotations.size(); i++) {
      ProcessingAnnotation ann = annotations.get(i);

      NamedClass className = ann.className();

      if (className.equals(TypeNames.MetaBeResource)) {
        return true;
      }
    }

    return false;
  }

  private static boolean isReturnTypeOfBeType(ProcessingType processingType) {
    ImmutableList<ProcessingAnnotation> annotations;
    annotations = processingType.getDirectlyPresentAnnotations();

    for (int i = 0; i < annotations.size(); i++) {
      ProcessingAnnotation candidate = annotations.get(i);

      if (isDirectlyAnnotatedWithMetaBeResource(candidate)) {
        return true;
      }
    }

    return false;
  }

  public final MethodCode generateDirectoryNestedImplMethod(DirectoryFacade facade) {
    return method(
        annotation(Override.class),
        overriding(processingMethod), _final(),
        _return(facade.getDirectoryMethodInvocation(returnClassName))
    );
  }

  final String methodName() {
    return processingMethod.getName();
  }

}
