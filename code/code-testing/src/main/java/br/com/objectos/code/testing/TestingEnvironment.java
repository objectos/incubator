/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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
package br.com.objectos.code.testing;

import static br.com.objectos.tools.Tools.compilationUnit;
import static br.com.objectos.tools.Tools.javac;
import static br.com.objectos.tools.Tools.patchModuleWithTestClasses;
import static br.com.objectos.tools.Tools.processor;

import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.model.element.ProcessingType;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import objectos.lang.Check;

public final class TestingEnvironment {

  private final Elements elements;

  private final ProcessingEnvironment processingEnv;

  private TestingEnvironment(ProcessingEnvironment processingEnv) {
    this.processingEnv = processingEnv;

    this.elements = processingEnv.getElementUtils();
  }

  public static void bootstrap(Bootstrap bootstrap) {
    Check.notNull(bootstrap, "bootstrap == null");

    BootstrapProcessor bootstrapProcessor;
    bootstrapProcessor = new BootstrapProcessor(bootstrap);

    javac(
        processor(bootstrapProcessor),

        patchModuleWithTestClasses(bootstrap.getModuleName()),

        compilationUnit("class Boot {}")
    );
  }

  public final ProcessingPackage getProcessingPackage(Class<?> type) {
    Check.notNull(type, "type == null");

    Package typePackage;
    typePackage = type.getPackage();

    return getProcessingPackage(typePackage.getName());
  }

  public final ProcessingPackage getProcessingPackage(String qualifiedName) {
    Check.notNull(qualifiedName, "qualifiedName == null");

    PackageElement packageElement;
    packageElement = elements.getPackageElement(qualifiedName);

    return ProcessingPackage.adapt(processingEnv, packageElement);
  }

  public final ProcessingType getProcessingType(Class<?> type) {
    Check.notNull(type, "type == null");

    return getProcessingType(type.getCanonicalName());
  }

  public final ProcessingType getProcessingType(String qualifiedName) {
    Check.notNull(qualifiedName, "qualifiedName == null");

    TypeElement typeElement;
    typeElement = elements.getTypeElement(qualifiedName);

    return ProcessingType.adapt(processingEnv, typeElement);
  }

  public interface Bootstrap {

    String getModuleName();

    void setTestingEnvironment(TestingEnvironment testingEnv);

  }

  @SupportedAnnotationTypes("*")
  private static class BootstrapProcessor extends AbstractProcessor {

    private final Bootstrap boot;

    BootstrapProcessor(Bootstrap boot) {
      this.boot = boot;
    }

    @Override
    public final SourceVersion getSupportedSourceVersion() {
      return SourceVersion.latestSupported();
    }

    @Override
    public final boolean process(
        Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
      if (roundEnv.processingOver()) {
        TestingEnvironment environment;
        environment = new TestingEnvironment(processingEnv);

        boot.setTestingEnvironment(environment);
      }

      return false;
    }

  }

}
