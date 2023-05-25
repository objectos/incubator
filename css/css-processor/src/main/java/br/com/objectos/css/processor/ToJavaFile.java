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

import static br.com.objectos.code.java.Java._class;
import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java.javaFile;
import static br.com.objectos.code.java.Java.t;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.ExtendsOne;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.css.parser.sheet.CssParser;
import br.com.objectos.css.sheet.AbstractStyleSheet;
import br.com.objectos.css.sheet.StyleSheet;
import java.io.IOException;
import java.io.InputStream;
import objectos.lang.Check;

public class ToJavaFile {

  private ToJavaFile() {}

  public static ToJavaFile generatedBy(Class<?> generator) {
    Check.notNull(generator, "generator == null");
    return new ToJavaFile();
  }

  public final JavaFile generate(CssFile file) throws IOException {
    return file.generate();
  }

  public static abstract class CssFile {

    private static final ExtendsOne EXTENDS = _extends(t(AbstractStyleSheet.class));

    protected CssFile() {}

    protected abstract NamedClass className();

    protected abstract InputStream openStream() throws IOException;

    final JavaFile generate() throws IOException {
      return javaFile(
        className().getPackage(),
        generateClassCode()
      );
    }

    private ClassCode generateClassCode() throws IOException {
      return _class(
        _public(), className(), EXTENDS,
        DefinitionMethod.of(parseStyleSheet())
      );
    }

    private StyleSheet parseStyleSheet() throws IOException {
      return CssParser.parse(openStream());
    }

  }

}
