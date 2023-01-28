/*
 * Copyright (C) 2020-2023 Objectos Software LTDA.
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
package br.com.objectos.latest.processor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardLocation;

class TestingJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {

  private final Map<String, JavaFileObject> generatedJavaFiles
      = new HashMap<String, JavaFileObject>();

  TestingJavaFileManager(JavaCompiler compiler) {
    super(compiler.getStandardFileManager(null, Locale.getDefault(), Charset.defaultCharset()));
  }

  public final JavaFileObject getGeneratedJavaFile(String className) {
    return generatedJavaFiles.get(className);
  }

  @Override
  public final JavaFileObject getJavaFileForOutput(
      Location location, String className, Kind kind, FileObject sibling)
      throws IOException {
    if (StandardLocation.SOURCE_OUTPUT.equals(location)) {
      JavaFileObject file = generatedJavaFiles.get(className);

      if (file == null) {
        file = new WritableStringJavaFileObject(className, kind);

        generatedJavaFiles.put(className, file);
      }

      return file;
    }

    else if (StandardLocation.CLASS_OUTPUT.equals(location)) {
      return new WritableByteArrayJavaFileObject(className, kind);
    }

    else {
      return super.getJavaFileForOutput(location, className, kind, sibling);
    }
  }

  @Override
  public final boolean isSameFile(FileObject a, FileObject b) {
    return a.toUri().equals(b.toUri());
  }

}