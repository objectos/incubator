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
package br.com.objectos.html.boot.spec;

import static br.com.objectos.code.java.Java.javaFile;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.declaration.TypeCode;
import br.com.objectos.code.java.io.JavaFile;
import java.util.function.Consumer;

public abstract class AbstractJavaFileStep extends AbstractStep {

  private final Consumer<JavaFile> javaFileWriter;

  protected AbstractJavaFileStep(Consumer<JavaFile> javaFileWriter) {
    this.javaFileWriter = javaFileWriter;
  }

  protected final void generateJavaFile(PackageName packageName, TypeCode typeCode) {
    javaFileWriter.accept(
        javaFile(packageName, typeCode)
    );
  }

}