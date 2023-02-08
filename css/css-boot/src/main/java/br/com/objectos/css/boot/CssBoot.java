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
package br.com.objectos.css.boot;

import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.code.java.declaration.AnnotationCode;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.css.boot.spec.CssSpecDsl;
import br.com.objectos.css.boot.spec.StepAdapter;
import br.com.objectos.fs.Directory;
import br.com.objectos.fs.LocalFs;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import objectos.code.JavaTemplate;

public class CssBoot extends StepAdapter {

  public static final AnnotationCode GENERATED = annotation(
    Generated.class,
    l(CssBoot.class.getCanonicalName())
  );

  private final Path srcDirectory;

  CssBoot(Path srcDirectory) {
    this.srcDirectory = srcDirectory;
  }

  public static void main(String[] args) throws IOException {
    String srcDirectoryPath;
    srcDirectoryPath = args[0];

    Path resolved;
    resolved = Path.of(srcDirectoryPath);

    Files.createDirectories(resolved);

    CssBoot boot = new CssBoot(resolved);

    boot.execute();
  }

  @Override
  public final void write(JavaTemplate template) {
    throw new UnsupportedOperationException("Implement me");
  }

  @Override
  public final void writeJavaFile(JavaFile javaFile) {
    try {
      File file = srcDirectory.toFile();
      Directory dir = LocalFs.getDirectory(file);
      javaFile.writeTo(dir);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private void execute() {
    CssStep step = new CssStep(this);
    CssSpecDsl dsl = new CssSpecDsl(step);

    CssModule module = new CssModule();
    module.acceptCssSpecDsl(dsl);

    dsl.execute();
  }

}
