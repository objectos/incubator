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

import br.com.objectos.be.annotations.BeResources;
import br.com.objectos.code.java.io.JavaFileConsumer;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingAnnotationValue;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.core.list.ImmutableList;
import java.io.IOException;

public class BeResourcesAnnotatedPackage {

  private final ProcessingPackage annotatedPackage;
  private final ProcessingAnnotation annotation;

  private final JavaFileConsumer javaFileConsumer;

  BeResourcesAnnotatedPackage(JavaFileConsumer javaFileConsumer,
                              ProcessingPackage annotatedPackage) {
    this.javaFileConsumer = javaFileConsumer;
    this.annotatedPackage = annotatedPackage;

    annotation = annotatedPackage.getDirectlyPresentAnnotation(BeResources.class);
  }

  public static void process(
      JavaFileConsumer javaFileConsumer,
      ProcessingPackage annotatedPackage) {
    BeResourcesAnnotatedPackage resources;
    resources = new BeResourcesAnnotatedPackage(javaFileConsumer, annotatedPackage);

    resources.process();
  }

  private void process() {
    ProcessingAnnotationValue value;
    value = annotation.getDeclaredValue("value");

    ImmutableList<String> resourceNames;
    resourceNames = value.getStringArray();

    for (int i = 0; i < resourceNames.size(); i++) {
      String resourceName;
      resourceName = resourceNames.get(i);

      process0(resourceName);
    }
  }

  private void process0(String name) {
    try {
      process1(name);
    } catch (IOException e) {
      String message;
      message = String.format("Could not process resource '%s'", name);

      annotatedPackage.compilationError(message);
    }
  }

  private void process1(String name) throws IOException {
    ResourceName resourceName;
    resourceName = new ResourceName(annotatedPackage, name);

    ProcessedResource processedResource;
    processedResource = resourceName.process();

    processedResource.acceptJavaFileConsumer(javaFileConsumer);
  }

}
