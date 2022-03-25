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

import static br.com.objectos.code.java.Java._new;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.be.processor.TypeNames;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.java.expression.production.ClassInstanceCreationExpression;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingAnnotationValue;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.core.list.ImmutableList;

abstract class ImageDimensions {

  ImageDimensions() {}

  public static ImageDimensions dim(int width, int height) {
    return new Standard(width, height);
  }

  public static ImageDimensions fromProcessingType(ProcessingType processingType) {
    ImageDimensions dimensions;
    dimensions = None.INSTANCE;

    ImmutableList<ProcessingAnnotation> annotations;
    annotations = processingType.getDirectlyPresentAnnotations();

    for (int i = 0; i < annotations.size(); i++) {
      ProcessingAnnotation ann;
      ann = annotations.get(i);

      NamedClass className;
      className = ann.className();

      if (className.equals(TypeNames.MetaBeImageDimensions)) {
        dimensions = getStandard(ann);

        break;
      }
    }

    return dimensions;
  }

  public static ImageDimensions none() {
    return None.INSTANCE;
  }

  private static ImageDimensions getStandard(ProcessingAnnotation annotation) {
    ProcessingAnnotationValue widthValue;
    widthValue = annotation.getDeclaredValue("width");

    int width = widthValue.getInt();

    ProcessingAnnotationValue heightValue;
    heightValue = annotation.getDeclaredValue("height");

    int height = heightValue.getInt();

    return new Standard(width, height);
  }

  public abstract ClassInstanceCreationExpression creationExpression(
      NamedClass implClassName, MethodInvocation getMethodInvocation);

  private static class None extends ImageDimensions {

    static final None INSTANCE = new None();

    @Override
    public final ClassInstanceCreationExpression creationExpression(
        NamedClass implClassName, MethodInvocation getMethodInvocation) {
      return _new(implClassName, getMethodInvocation);
    }

  }

  private static class Standard extends ImageDimensions {

    private final int height;
    private final int width;

    Standard(int width, int height) {
      this.width = width;
      this.height = height;
    }

    @Override
    public final ClassInstanceCreationExpression creationExpression(
        NamedClass implClassName, MethodInvocation getMethodInvocation) {
      return _new(implClassName, getMethodInvocation, l(width), l(height));
    }

  }

}
