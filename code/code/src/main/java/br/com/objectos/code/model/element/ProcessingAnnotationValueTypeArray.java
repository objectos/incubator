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

import br.com.objectos.code.processing.type.PTypeMirror;
import java.util.List;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import objectos.util.ImmutableList;
import objectos.util.MutableList;

class ProcessingAnnotationValueTypeArray extends ProcessingAnnotationValue {

  private final ImmutableList<PTypeMirror> value;

  ProcessingAnnotationValueTypeArray(ProcessingAnnotation annotation,
                                     ExecutableElement element,
                                     AnnotationValue annotationValue,
                                     ImmutableList<PTypeMirror> value) {
    super(annotation, element, annotationValue);
    this.value = value;
  }

  static ProcessingAnnotationValueTypeArray build(
      ProcessingAnnotation annotation,
      ExecutableElement element,
      AnnotationValue annotationValue,
      List<? extends AnnotationValue> values) {
    return new ProcessingAnnotationValueTypeArray(
        annotation,
        element,
        annotationValue,
        toClassArray(annotation, values)
    );
  }

  private static ImmutableList<PTypeMirror> toClassArray(
      ProcessingAnnotation annotation, List<? extends AnnotationValue> array) {
    MutableList<PTypeMirror> result;
    result = new MutableList<>();

    for (int i = 0; i < array.size(); i++) {
      AnnotationValue annotationValue;
      annotationValue = array.get(i);

      PTypeMirror type;
      type = annotation.toModelTypeIfPossible(annotationValue);

      result.add(type);
    }

    return result.toImmutableList();
  }

  @Override
  public final ImmutableList<PTypeMirror> getTypeArray() {
    return value;
  }

}
