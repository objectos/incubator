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
package br.com.objectos.be.processor.resources;

import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._interface;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.javaFile;
import static br.com.objectos.code.java.Java.l;
import static br.com.objectos.code.java.Java.t;
import static br.com.objectos.code.java.Java.value;

import br.com.objectos.be.processor.BeResourcesProcessor;
import br.com.objectos.be.processor.TypeNames;
import br.com.objectos.be.resource.ImageResource;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.io.JavaFileConsumer;
import br.com.objectos.http.media.ImageType;
import java.io.IOException;

final class ProcessedResourceImage extends ProcessedResource {

  final ImageDimensions dimensions;
  final ImageType imageType;

  ProcessedResourceImage(Builder builder) {
    super(builder);
    imageType = builder.imageType();
    dimensions = builder.dimensions();
  }

  @Override
  public final void acceptJavaFileConsumer(JavaFileConsumer round) throws IOException {
    round.acceptJavaFile(generateInterface());
  }

  public final JavaFile generateInterface() {
    return javaFile(
        className.getPackage(),
        _interface(
            BeResourcesProcessor.GENERATED,
            annotation(
                TypeNames.MetaBeImage,
                value("imageType", TypeNames.ImageType.id(imageType.name())),
                value("resourceName", l(resourceName))
            ),
            dimensions.generateMetaAnnotationIfPossible(),
            _public(), className,
            _extends(t(ImageResource.class))
        )
    );
  }

  abstract static class Builder extends AbstractBuilder<ProcessedResourceImage> {

    Builder() {}

    public final ProcessedResourceImage build() {
      return new ProcessedResourceImage(this);
    }

    abstract ImageDimensions dimensions();

    abstract ImageType imageType();

  }

}