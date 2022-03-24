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

import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import java.util.List;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

class ProcessingAnnotationValueEnumConstantArray extends ProcessingAnnotationValue {

  private final ImmutableList<ProcessingEnumConstant> value;

  ProcessingAnnotationValueEnumConstantArray(ProcessingAnnotation annotation,
                                             ExecutableElement element,
                                             AnnotationValue annotationValue,
                                             ImmutableList<ProcessingEnumConstant> value) {
    super(annotation, element, annotationValue);
    this.value = value;
  }

  static ProcessingAnnotationValueEnumConstantArray build(
      ProcessingAnnotation annotation,
      ExecutableElement element,
      AnnotationValue annotationValue,
      List<? extends AnnotationValue> values) {
    return new ProcessingAnnotationValueEnumConstantArray(
        annotation,
        element,
        annotationValue,
        toEnumConstant(annotation, values)
    );
  }

  private static ImmutableList<ProcessingEnumConstant> toEnumConstant(
      ProcessingAnnotation annotation, List<? extends AnnotationValue> array) {
    MutableList<ProcessingEnumConstant> result;
    result = MutableList.create();

    for (int i = 0; i < array.size(); i++) {
      AnnotationValue annotationValue;
      annotationValue = array.get(i);

      VariableElement element;
      element = (VariableElement) annotationValue.getValue();

      ProcessingEnumConstant value;
      value = annotation.toEnumConstant(element);

      result.add(value);
    }

    return result.toImmutableList();
  }

  @Override
  public final ImmutableList<ProcessingEnumConstant> getEnumConstantArray() {
    return value;
  }

}
