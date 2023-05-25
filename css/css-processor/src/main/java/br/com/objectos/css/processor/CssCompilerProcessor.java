/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.css.processor;

import br.com.objectos.code.annotations.Services;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingPackage;
import br.com.objectos.code.processing.AbstractProcessingRoundProcessor;
import br.com.objectos.code.processing.ProcessingRound;
import br.com.objectos.css.CssCompiler;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import objectos.util.UnmodifiableSet;

@Services(Processor.class)
public class CssCompilerProcessor extends AbstractProcessingRoundProcessor {

  private Supplier<Filer> filerSupplier;

  private final ToJavaFile toJavaFile = ToJavaFile.generatedBy(getClass());

  public CssCompilerProcessor() {}

  CssCompilerProcessor(Supplier<Filer> filerSupplier) {
    this.filerSupplier = filerSupplier;
  }

  @Override
  public final Set<String> getSupportedAnnotationTypes() {
    return supportedAnnotationTypes(CssCompiler.class);
  }

  @Override
  public synchronized final void init(ProcessingEnvironment processingEnv) {
    if (filerSupplier == null) {
      filerSupplier = processingEnv::getFiler;
    }
    super.init(processingEnv);
  }

  @Override
  protected final boolean process(ProcessingRound round) {
    UnmodifiableSet<ProcessingPackage> packages = round.getAnnotatedPackages();
    for (ProcessingPackage pkg : packages) {
      process0(round, pkg);
    }
    return round.claimTheseAnnotations();
  }

  private Filer getFiler() {
    return filerSupplier.get();
  }

  private void process0(ProcessingRound round, ProcessingPackage pkg) {
    PackageName packageName = pkg.toNamedPackage();

    Iterable<String> simpleNameList = pkg
        .getDirectlyPresentAnnotation(CssCompiler.class)
        .getDeclaredOrDefaultValue("value")
        .getStringArray();

    for (String simpleName : simpleNameList) {
      process1(round, packageName, simpleName);
    }
  }

  private void process1(ProcessingRound round, PackageName packageName, String simpleName) {
    try {
      JavaFile javaFile = process2(packageName, simpleName);
      round.writeJavaFile(javaFile);
    } catch (IOException e) {
      round.printMessageError(e);
    }
  }

  private JavaFile process2(PackageName packageName, String simpleName) throws IOException {
    ThisCssFile cssFile = new ThisCssFile(packageName, simpleName);
    return toJavaFile.generate(cssFile);
  }

  private class ThisCssFile extends ToJavaFile.CssFile {

    private final PackageName packageName;
    private final String simpleName;

    ThisCssFile(PackageName packageName, String simpleName) {
      this.packageName = packageName;
      this.simpleName = simpleName;
    }

    @Override
    protected final NamedClass className() {
      return packageName.nestedClass(simpleName);
    }

    @Override
    protected final InputStream openStream() throws IOException {
      Filer filer = getFiler();

      FileObject resource = filer.getResource(
        StandardLocation.SOURCE_PATH,
        packageName.toString(),
        simpleName + ".css");

      return resource.openInputStream();
    }

  }

}