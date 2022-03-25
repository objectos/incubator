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

import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.t;

import br.com.objectos.be.meta.MetaBeImage;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.java.expression.production.ClassInstanceCreationExpression;
import br.com.objectos.code.java.statement.Statement;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingAnnotationValue;
import br.com.objectos.code.model.element.ProcessingEnumConstant;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.http.media.ImageType;

final class ProcessedResourceImage extends ProcessedResource {

  final ImageDimensions dimensions;
  final ImageType imageType;

  ProcessedResourceImage(Builder builder) {
    super(builder);
    imageType = builder.imageType();
    dimensions = builder.dimensions();
  }

  public static ProcessedResourceImage build(ProcessingType type) {
    return new ProcessingTypeBuilder(type).build();
  }

  @Override
  public final void acceptDirectoryGenerator(DirectoryGenerator generator) {
    generator.addProcessedResourceImage(this);
  }

  @Override
  public final void acceptPathGenerator(PathGenerator generator) {
    generator.addImageInstance(this);
  }

  public final Statement generateDirectoryConfigureStatement() {
    return invoke(
        "addResource",
        t(ImageType.class).id(imageType.name()),
        l(resourceName)
    );
  }

  @Override
  final ClassInstanceCreationExpression creationExpression(
      NamedClass implClassName, MethodInvocation getMethodInvocation) {
    return dimensions.creationExpression(implClassName, getMethodInvocation);
  }

  abstract static class Builder extends AbstractBuilder<ProcessedResourceImage> {

    Builder() {}

    public final ProcessedResourceImage build() {
      return new ProcessedResourceImage(this);
    }

    abstract ImageDimensions dimensions();

    abstract ImageType imageType();

  }

  private static class ProcessingTypeBuilder extends Builder {

    private final ProcessingAnnotation metaBeImage;

    private final ProcessingType processingType;

    ProcessingTypeBuilder(ProcessingType processingType) {
      this.processingType = processingType;

      metaBeImage = processingType.getDirectlyPresentAnnotation(MetaBeImage.class);
    }

    @Override
    final NamedClass className() {
      return processingType.getName();
    }

    @Override
    final ImageDimensions dimensions() {
      return ImageDimensions.fromProcessingType(processingType);
    }

    @Override
    final ImageType imageType() {
      ProcessingAnnotationValue enumValue;
      enumValue = metaBeImage.getDeclaredValue("imageType");

      ProcessingEnumConstant declaredValue;
      declaredValue = enumValue.getEnumConstant();

      return declaredValue.valueOf(ImageType.class);
    }

    @Override
    final String resourceName() {
      ProcessingAnnotationValue stringValue;
      stringValue = metaBeImage.getDeclaredValue("resourceName");

      return stringValue.getString();
    }

  }

}