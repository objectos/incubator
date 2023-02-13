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
import br.com.objectos.css.boot.spec.CssStep;
import br.com.objectos.css.boot.spec.StepAdapter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import objectos.code.JavaSink;
import objectos.code.JavaTemplate;

public class CssBoot extends StepAdapter {

  public static final AnnotationCode GENERATED = annotation(
    Generated.class,
    l(CssBoot.class.getCanonicalName())
  );

  private final JavaSink sink;

  CssBoot(JavaSink sink) {
    this.sink = sink;
  }

  public static void main(String[] args) throws IOException {
    var srcDirectoryPath = args[0];

    var resolved = Path.of(srcDirectoryPath);

    Files.createDirectories(resolved);

    var sink = JavaSink.ofDirectory(
      resolved,
      JavaSink.overwriteExisting()
    );

    var boot = new CssBoot(sink);

    boot.execute();
  }

  @Override
  public final void write(JavaTemplate template) {
    try {
      sink.write(template);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public final void writeJavaFile(JavaFile javaFile) {
    throw new UnsupportedOperationException();
  }

  private void execute() {
    var step = new CssStep(this);

    var dsl = new CssSpecDsl(step);

    var module = new CssModule();

    module.acceptCssSpecDsl(dsl);

    dsl.execute();
  }

}
