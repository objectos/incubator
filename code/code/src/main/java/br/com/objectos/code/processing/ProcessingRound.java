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
package br.com.objectos.code.processing;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.io.JavaFileConsumer;
import br.com.objectos.code.model.element.ProcessingMethod;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.model.element.ProcessingPackageReprocessor;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.model.element.ProcessingTypeReprocessor;
import br.com.objectos.core.set.ImmutableSet;
import br.com.objectos.core.set.MutableSet;
import br.com.objectos.core.throwable.Throwables;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic.Kind;
import objectos.lang.Checks;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public class ProcessingRound
    implements
    JavaFileConsumer,
    ProcessingPackageReprocessor,
    ProcessingTypeReprocessor {

  private static final Set<ElementKind> TYPES = EnumSet.of(
      ElementKind.CLASS,
      ElementKind.ENUM,
      ElementKind.INTERFACE,
      ElementKind.ANNOTATION_TYPE
  );

  private final Set<? extends TypeElement> annotations;
  private final ProcessingEnvironment processingEnv;
  private final Reprocessor reprocessor;

  private final RoundEnvironment roundEnv;

  private ProcessingRound(ProcessingEnvironment processingEnv,
                          Set<? extends TypeElement> annotations,
                          RoundEnvironment roundEnv,
                          Reprocessor reprocessor) {
    this.processingEnv = processingEnv;
    this.annotations = annotations;
    this.roundEnv = roundEnv;
    this.reprocessor = reprocessor;
  }

  public static ProcessingRound adapt(
      ProcessingEnvironment processingEnv,
      Set<? extends TypeElement> annotations,
      RoundEnvironment roundEnv,
      Reprocessor reprocessor) {
    Checks.checkNotNull(processingEnv, "processingEnv == null");
    Checks.checkNotNull(annotations, "annotations == null");
    Checks.checkNotNull(roundEnv, "roundEnv == null");
    Checks.checkNotNull(reprocessor, "reprocessor == null");
    return new ProcessingRound(processingEnv, annotations, roundEnv, reprocessor);
  }

  @Override
  public final void acceptJavaFile(JavaFile javaFile) throws IOException {
    writeJavaFile(javaFile);
  }

  public final boolean allowOtherProcessors() {
    return false;
  }

  public final boolean claimTheseAnnotations() {
    return true;
  }

  public final ImmutableSet<ProcessingMethod> getAnnotatedMethods() {
    MutableSet<ProcessingMethod> result;
    result = MutableSet.create();

    for (TypeElement annotation : annotations) {
      getAnnotatedMethods0(result, annotation);
    }

    return result.toImmutableSet();
  }

  public final ImmutableSet<ProcessingPackage> getAnnotatedPackages() {
    MutableSet<ProcessingPackage> result;
    result = MutableSet.create();

    addReprocessorPackagesIfNecessary(result);

    for (TypeElement annotation : annotations) {
      getAnnotatedPackages0(result, annotation);
    }

    return result.toImmutableSet();
  }

  public final ImmutableSet<ProcessingType> getAnnotatedTypes() {
    MutableSet<ProcessingType> result = MutableSet.create();

    addReprocessorTypesIfNecessary(result);

    for (TypeElement annotation : annotations) {
      getAnnotatedTypes0(result, annotation);
    }

    return result.toImmutableSet();
  }

  public final boolean isOver() {
    return roundEnv.processingOver();
  }

  public final void printMessageError(Exception e) {
    Checks.checkNotNull(e, "e == null");

    String stackTrace = Throwables.printStackTraceToString(e);

    printMessageError("Processor threw an exception: " + stackTrace);
  }

  public final void printMessageError(String message) {
    Checks.checkNotNull(message, "message == null");
    printMessage(Kind.ERROR, message);
  }

  @Override
  public final void reprocessPackage(ProcessingPackage pkg) {
    Checks.checkNotNull(pkg, "pkg == null");
    pkg.acceptReprocessor(reprocessor);
  }

  @Override
  public final void reprocessType(ProcessingType type) {
    Checks.checkNotNull(type, "type == null");
    type.acceptReprocessor(reprocessor);
  }

  public final void writeArtifact(Artifact artifact) {
    Checks.checkNotNull(artifact, "artifact == null");
    artifact.execute(processingEnv);
  }

  public final void writeJavaFile(JavaFile javaFile) throws IOException {
    Checks.checkNotNull(javaFile, "javaFile == null");
    Filer filer = processingEnv.getFiler();
    javaFile.writeTo(filer);
  }

  public final void writeResource(String resourceName, String contents) throws IOException {
    Checks.checkNotNull(resourceName, "resourceName == null");
    Checks.checkNotNull(contents, "contents == null");
    Filer filer = processingEnv.getFiler();
    FileObject file = filer.createResource(StandardLocation.CLASS_OUTPUT, "", resourceName);
    OutputStream out = file.openOutputStream();
    try {
      out.write(contents.getBytes());
    } finally {
      out.close();
    }
  }

  private void addProcessingMethod(Set<ProcessingMethod> result, ExecutableElement element) {
    ProcessingMethod method;
    method = ProcessingMethod.adapt(processingEnv, element);

    result.add(method);
  }

  private void addProcessingPackage(Set<ProcessingPackage> result, PackageElement element) {
    ProcessingPackage pkg;
    pkg = ProcessingPackage.adapt(processingEnv, element);

    result.add(pkg);
  }

  private void addProcessingType(Set<ProcessingType> result, TypeElement element) {
    ProcessingType type;
    type = ProcessingType.adapt(processingEnv, element);

    result.add(type);
  }

  private void addReprocessorPackagesIfNecessary(Set<ProcessingPackage> result) {
    ImmutableSet<Name> packageNames;
    packageNames = reprocessor.getPackages();

    if (packageNames.isEmpty()) {
      return;
    }

    Elements elements;
    elements = processingEnv.getElementUtils();

    for (Name packageName : packageNames) {
      PackageElement element;
      element = elements.getPackageElement(packageName.toString());

      addProcessingPackage(result, element);
    }
  }

  private void addReprocessorTypesIfNecessary(Set<ProcessingType> result) {
    ImmutableSet<Name> typeNames;
    typeNames = reprocessor.getTypes();

    if (typeNames.isEmpty()) {
      return;
    }

    Elements elements;
    elements = processingEnv.getElementUtils();

    for (Name typeName : typeNames) {
      TypeElement element;
      element = elements.getTypeElement(typeName.toString());

      addProcessingType(result, element);
    }
  }

  private void getAnnotatedMethods0(Set<ProcessingMethod> result, TypeElement annotation) {
    Set<? extends Element> elements;
    elements = roundEnv.getElementsAnnotatedWith(annotation);

    for (Element e : elements) {
      if (ElementKind.METHOD == e.getKind()) {
        ExecutableElement element;
        element = ExecutableElement.class.cast(e);

        addProcessingMethod(result, element);
      }
    }
  }

  private void getAnnotatedPackages0(Set<ProcessingPackage> result, TypeElement annotation) {
    Set<? extends Element> elements;
    elements = roundEnv.getElementsAnnotatedWith(annotation);

    for (Element e : elements) {
      if (ElementKind.PACKAGE == e.getKind()) {
        PackageElement element;
        element = PackageElement.class.cast(e);

        addProcessingPackage(result, element);
      }
    }
  }

  private void getAnnotatedTypes0(Set<ProcessingType> result, TypeElement annotation) {
    Set<? extends Element> elements;
    elements = roundEnv.getElementsAnnotatedWith(annotation);

    for (Element e : elements) {
      if (TYPES.contains(e.getKind())) {
        TypeElement element;
        element = TypeElement.class.cast(e);

        addProcessingType(result, element);
      }
    }
  }

  private void printMessage(Kind kind, String message) {
    Messager messager = processingEnv.getMessager();
    messager.printMessage(kind, message);
  }

}
