/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.specgen;

import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.code.java.declaration.AnnotationCode;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.core.io.Read;
import br.com.objectos.css.specgen.mdn.Mdn;
import br.com.objectos.css.specgen.spec.Spec;
import br.com.objectos.css.specgen.spec.StepAdapter;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import br.com.objectos.fs.RegularFile;
import br.com.objectos.fs.ResolvedPath;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;

public class SpecgenBoot extends StepAdapter {

  static final AnnotationCode GENERATED = annotation(
      Generated.class,
      l(SpecgenBoot.class.getCanonicalName())
  );

  private final Directory srcDirectory;

  private SpecgenBoot(Directory srcDirectory) {
    this.srcDirectory = srcDirectory;
  }

  public static void main(String[] args) throws IOException {
    String srcDirectoryPath;
    srcDirectoryPath = args[0];

    ResolvedPath resolved;
    resolved = LocalFs.resolve(srcDirectoryPath);

    Directory srcDirectory;
    srcDirectory = resolved.toDirectoryCreateIfNotFound();

    SpecgenBoot boot = new SpecgenBoot(
        srcDirectory
    );

    boot.execute();
  }

  @Override
  public final void writeJavaFile(JavaFile javaFile) {
    try {
      if (shouldWrite(javaFile)) {
        javaFile.writeTo(srcDirectory);
      }
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private void execute() throws IOException {
    Spec spec = Mdn.load();
    Specgen specgen = new Specgen(spec);
    specgen.execute(new PropertyModuleStep(this));
  }

  private boolean shouldWrite(JavaFile javaFile) throws IOException {
    NamedClass className = javaFile.className();

    RegularFile sourceFile = className.createSourceFile(srcDirectory);

    String s = Read.string(sourceFile, Charset.defaultCharset());

    return !s.contains("@DoNotOverwrite");
  }

}
