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

import br.com.objectos.be.meta.ResourceKind;
import br.com.objectos.be.processor.TypeNames;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingAnnotationValue;
import br.com.objectos.code.model.element.ProcessingEnumConstant;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.core.list.ImmutableList;

final class ResourceKindEnum {

  private ResourceKindEnum() {}
  
  public static ResourceKind getFromGeneratedType(ProcessingType type) {
    ProcessingAnnotation metaAnnotation;
    metaAnnotation = findMetaBeResourceAnnotation(type);

    ProcessingAnnotationValue value;
    value = metaAnnotation.getDeclaredValue("value");

    ProcessingEnumConstant declaredValue;
    declaredValue = value.getEnumConstant();

    return declaredValue.valueOf(ResourceKind.class);
  }

  private static ProcessingAnnotation findMetaBeResourceAnnotation(ProcessingType type) {
    ImmutableList<ProcessingAnnotation> annotations;
    annotations = type.getDirectlyPresentAnnotations();

    for (int i = 0; i < annotations.size(); i++) {
      ProcessingAnnotation annotation;
      annotation = annotations.get(i);

      ProcessingAnnotation candidate;
      candidate = findMetaBeResourceAnnotation0(annotation);

      if (candidate != null) {
        return candidate;
      }
    }

    throw new AssertionError("Did not find MetaBeResource annotation");
  }

  private static ProcessingAnnotation findMetaBeResourceAnnotation0(ProcessingAnnotation meta) {
    ImmutableList<ProcessingAnnotation> annotations;
    annotations = meta.getDirectlyPresentAnnotations();

    for (int i = 0; i < annotations.size(); i++) {
      ProcessingAnnotation annotation;
      annotation = annotations.get(i);

      if (annotation.hasQualifiedName(TypeNames.MetaBeResource.getCanonicalName())) {
        return annotation;
      }
    }

    return null;
  }

}
