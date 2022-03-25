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

import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.model.element.ProcessingResource;
import br.com.objectos.http.media.ApplicationType;
import br.com.objectos.http.media.FontType;
import br.com.objectos.http.media.ImageType;
import br.com.objectos.http.media.MediaType;
import br.com.objectos.http.media.MediaTypeVisitor;
import br.com.objectos.http.media.MediaTypes;
import br.com.objectos.http.media.TextType;
import br.com.objectos.http.media.TopLevel;
import java.io.IOException;
import javax.tools.StandardLocation;

class ResourceName implements MediaTypeVisitor<ProcessedResource, ProcessingResource> {

  final ProcessingPackage annotatedPackage;

  final String resourceName;

  private final PackageName packageName;

  ResourceName(ProcessingPackage annotatedPackage, String resourceName) {
    this.annotatedPackage = annotatedPackage;

    this.resourceName = resourceName;

    packageName = annotatedPackage.toNamedPackage();
  }

  public final ProcessedResource process() throws IOException {
    ProcessingResource resource;
    resource = getResource(StandardLocation.SOURCE_PATH, StandardLocation.CLASS_OUTPUT);

    String name;
    name = resource.getName();

    MediaType mediaType;
    mediaType = MediaTypes.ofFileName(name);

    return mediaType.acceptMediaTypeVisitor(this, resource);
  }

  @Override
  public final ProcessedResource visitApplicationType(ApplicationType type, ProcessingResource p) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final ProcessedResource visitFontType(FontType fontType, ProcessingResource p) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final ProcessedResource visitImageType(ImageType type, ProcessingResource p) {
    return new ProcessedResourceImage.Builder() {
      @Override
      final NamedClass className() {
        return ResourceName.this.className(type.type());
      }

      @Override
      final ImageDimensions dimensions() {
        return ImageDimensions.ofResource(p, type);
      }

      @Override
      final ImageType imageType() {
        return type;
      }

      @Override
      final String resourceName() {
        return resourceName;
      }
    }.build();
  }

  @Override
  public final ProcessedResource visitTextType(TextType type, ProcessingResource p) {
    throw new UnsupportedOperationException("Implement me");
  }

  final NamedClass className(TopLevel type) {
    return packageName.nestedClass(simpleName(type));
  }

  private void addTopLevel(StringBuilder sb, TopLevel type) {
    sb.append(type.simpleName());
    sb.append(' ');
  }

  private ProcessingResource getResource(
      StandardLocation primary, StandardLocation secondary)
      throws IOException {
    try {
      return annotatedPackage.getResource(primary, resourceName);
    } catch (IllegalArgumentException e) {
      return annotatedPackage.getResource(secondary, resourceName);
    }
  }

  private String simpleName(TopLevel type) {
    StringBuilder sb = new StringBuilder();

    char[] fileName = resourceName.toCharArray();
    char c = fileName[0];
    if (Character.isDigit(c)) {
      addTopLevel(sb, type);
      sb.append(c);
    } else if (!Character.isJavaIdentifierStart(c)) {
      addTopLevel(sb, type);
    } else {
      sb.append(c);
    }

    for (int i = 1; i < fileName.length; i++) {
      c = fileName[i];
      if (Character.isJavaIdentifierPart(c)) {
        sb.append(c);
      } else {
        sb.append(' ');
      }
    }

    return JavaNames.toValidClassName(sb.toString());
  }

}
