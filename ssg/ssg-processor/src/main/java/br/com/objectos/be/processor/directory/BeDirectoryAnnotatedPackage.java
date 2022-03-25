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

import br.com.objectos.be.annotations.BeDirectory;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.io.JavaFileConsumer;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.code.model.element.ProcessingAnnotationValue;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.model.element.ProcessingPackageReprocessor;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.ProcessingRound;
import br.com.objectos.code.processing.type.ErrorTypeException;
import br.com.objectos.code.processing.type.PDeclaredType;
import br.com.objectos.code.processing.type.PTypeMirror;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;
import java.io.IOException;

public final class BeDirectoryAnnotatedPackage implements JavaFileConsumer {

  private final ProcessingPackage annotatedPackage;
  private final ProcessingAnnotation annotation;

  private final DirectoryGenerator directoryGenerator;

  private final JavaFileConsumer javaFileConsumer;
  private final MutableList<JavaFile> javaFiles = MutableList.create();

  private final ProcessingPackageReprocessor packageReprocessor;
  private final PathGenerator pathGenerator;

  BeDirectoryAnnotatedPackage(JavaFileConsumer javaFileConsumer,
                              ProcessingPackageReprocessor packageReprocessor,
                              ProcessingPackage annotatedPackage) {
    this.javaFileConsumer = javaFileConsumer;
    this.packageReprocessor = packageReprocessor;
    this.annotatedPackage = annotatedPackage;
    this.annotation = annotatedPackage.getDirectlyPresentAnnotation(BeDirectory.class);

    br.com.objectos.code.java.declaration.PackageName packageName;
    packageName = annotatedPackage.toNamedPackage();

    directoryGenerator = DirectoryGenerator.build(packageName);
    pathGenerator = PathGenerator.build(packageName);
  }

  public static void process(
      ProcessingRound round, ProcessingPackage annotatedPackage)
      throws IOException {
    BeDirectoryAnnotatedPackage be = new BeDirectoryAnnotatedPackage(
        round,
        round,
        annotatedPackage
    );
    be.process();
  }

  static void process(
      JavaFileConsumer javaFileConsumer,
      ProcessingPackageReprocessor packageReprocessor,
      ProcessingPackage annotatedPackage)
      throws IOException {
    BeDirectoryAnnotatedPackage be = new BeDirectoryAnnotatedPackage(
        javaFileConsumer,
        packageReprocessor,
        annotatedPackage
    );
    be.process();
  }

  @Override
  public final void acceptJavaFile(JavaFile javaFile) throws IOException {
    javaFiles.addWithNullMessage(javaFile, "javaFile == null");
  }

  private void process() throws IOException {
    try {
      processTypes();

      directoryGenerator.acceptJavaFileConsumer(javaFileConsumer);

      pathGenerator.acceptJavaFileConsumer(javaFileConsumer);

      writeJavaFiles();
    } catch (ErrorTypeException e) {
      packageReprocessor.reprocessPackage(annotatedPackage);
    }
  }

  private void processTypes() throws ErrorTypeException {
    ProcessingAnnotationValue types;
    types = annotation.getDeclaredOrDefaultValue("value");

    ImmutableList<PTypeMirror> typeValues;
    typeValues = types.getTypeArray();

    for (int i = 0; i < typeValues.size(); i++) {
      PTypeMirror typeValue;
      typeValue = typeValues.get(i);

      processTypes0(typeValue);
    }
  }

  private void processTypes0(PTypeMirror typeValue) throws ErrorTypeException {
    typeValue.throwErrorTypeExceptionIfPossible();

    PDeclaredType maybeProcessingType;
    maybeProcessingType = typeValue.toDeclaredType();

    ProcessingType processingType;
    processingType = maybeProcessingType.toProcessingType();

    ResourceType resourceType;
    resourceType = new ResourceType(processingType);

    ProcessedResource processedResource;
    processedResource = resourceType.process();

    processedResource.acceptDirectoryGenerator(directoryGenerator);

    processedResource.acceptPathGenerator(pathGenerator);
  }

  private void writeJavaFiles() throws IOException {
    for (int i = 0; i < javaFiles.size(); i++) {
      JavaFile javaFile;
      javaFile = javaFiles.get(i);

      javaFileConsumer.acceptJavaFile(javaFile);
    }
  }

}
