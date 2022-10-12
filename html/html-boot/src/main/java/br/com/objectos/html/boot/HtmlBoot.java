/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.boot;

import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.code.java.declaration.AnnotationCode;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.html.boot.spec.Spec;
import br.com.objectos.html.boot.spec.SpecDsl;
import br.com.objectos.html.boot.spec.Step;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.function.Consumer;

public class HtmlBoot {

  private class JavaFileWriter implements Consumer<JavaFile> {

    private final Path srcDir;

    JavaFileWriter(Path srcDir) {
      this.srcDir = srcDir;
    }

    @Override
    public final void accept(JavaFile file) {
      generateJavaFile(file);
    }

    private void generateJavaFile(JavaFile file) {
      try {
        file.writeTo(srcDir);
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }

  }

  @SuppressWarnings("exports")
  public static final AnnotationCode GENERATED = annotation(
    br.com.objectos.code.annotations.Generated.class,
    l(HtmlBoot.class.getCanonicalName())
  );

  private final Spec spec;

  public HtmlBoot() {
    this(HtmlSpec.INSTANCE);
  }

  HtmlBoot(Spec spec) {
    this.spec = spec;
  }

  public static void main(String[] args) throws IOException {
    HtmlBoot boot = new HtmlBoot();

    boot.execute(args[0], args[1]);
  }

  private void execute(String moduleName, String srcDir) throws IOException {
    SpecDsl dsl = new SpecDsl();
    spec.acceptSpecDsl(dsl);

    var directory = Path.of(srcDir);

    JavaFileWriter writer;
    writer = new JavaFileWriter(directory);

    Step step;
    switch (moduleName) {
      case "html":
        step = new ModuleHtml(writer);
        break;
      case "spi":
        step = new ModuleHtmlSpi(writer);
        break;
      default:
        throw new IllegalArgumentException("Unknown module: " + moduleName);
    }
    dsl.execute(step);
  }

}