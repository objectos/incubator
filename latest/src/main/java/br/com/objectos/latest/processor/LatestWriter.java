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

import br.com.objectos.latest.Generated;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

final class LatestWriter {

  private static final String BIG_R = "\r\n|\r|\n";

  private static final String[] EMPTY_JAVADOC = new String[0];

  private static final String NL = System.getProperty("line.separator");

  private final StringBuilder out = new StringBuilder();

  private final ProcessingEnvironment processingEnv;

  LatestWriter(ProcessingEnvironment processingEnv) {
    this.processingEnv = processingEnv;
  }

  public final void flush(TypeElement element, String simpleName) {
    try {
      flush0(element, simpleName);
    } catch (IOException e) {
      Messager messager;
      messager = processingEnv.getMessager();

      messager.printMessage(Kind.ERROR, e.getMessage());
    }
  }

  public final void newLine() {
    out.append(NL);
  }

  public final void write(char c) {
    out.append(c);
  }

  public final void write(String string) {
    out.append(string);
  }

  public final void writeGenerated(Class<?> generator) {
    out.append("@Generated(\"");

    out.append(generator.getCanonicalName());

    out.append("\")");

    out.append(NL);
  }

  public final void writeImports() {
    out.append("import ");

    out.append(Generated.class.getCanonicalName());

    out.append(';');

    out.append(NL);

    out.append(NL);
  }

  public final void writeJavadoc(
      String indentation, Element element) {
    String[] javadoc;
    javadoc = getJavadoc0(processingEnv, element);

    writeJavadoc0(indentation, javadoc);
  }

  public final void writeJavadoc(
      String indentation, Element first, Element second) {
    String[] javadoc;
    javadoc = getJavadoc0(processingEnv, first);

    if (javadoc == EMPTY_JAVADOC) {
      javadoc = getJavadoc0(processingEnv, second);
    }

    writeJavadoc0(indentation, javadoc);
  }

  public final void writePackageName(TypeElement element) {
    PackageElement packageElement;
    packageElement = Util.getPackageElement(element);

    Name qualifiedName;
    qualifiedName = packageElement.getQualifiedName();

    String packageName;
    packageName = qualifiedName.toString();

    out.append("package ");

    out.append(packageName);

    out.append(';');

    out.append(NL);

    out.append(NL);
  }

  public final void writeTypeArguments(TypeElement element) {
    List<? extends TypeParameterElement> parameters;
    parameters = element.getTypeParameters();

    if (parameters.isEmpty()) {
      return;
    }

    out.append('<');

    TypeParameterElement first;
    first = parameters.get(0);

    writeTypeArgument(first);

    for (int i = 1; i < parameters.size(); i++) {
      TypeParameterElement next;
      next = parameters.get(i);

      out.append(", ");

      writeTypeArgument(next);
    }

    out.append('>');
  }

  public final boolean writeTypeParameters(TypeElement element) {
    List<? extends TypeParameterElement> parameters;
    parameters = element.getTypeParameters();

    if (parameters.isEmpty()) {
      return false;
    }

    out.append('<');

    TypeParameterElement first;
    first = parameters.get(0);

    out.append(first.toString());

    for (int i = 1; i < parameters.size(); i++) {
      TypeParameterElement next;
      next = parameters.get(i);

      out.append(", ");

      out.append(next.toString());
    }

    out.append('>');

    return true;
  }

  private void flush0(TypeElement element, String simpleName) throws IOException {
    Filer filer;
    filer = processingEnv.getFiler();

    String canonicalName;
    canonicalName = Util.getCanonicalName(element, simpleName);

    JavaFileObject file;
    file = filer.createSourceFile(canonicalName);

    BufferedWriter writer = null;

    try {
      Writer fileWriter;
      fileWriter = file.openWriter();

      writer = new BufferedWriter(fileWriter);

      writer.write(out.toString());
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
  }

  private String[] getJavadoc0(ProcessingEnvironment processingEnv, Element element) {
    String[] javadoc;
    javadoc = EMPTY_JAVADOC;

    Elements elements;
    elements = processingEnv.getElementUtils();

    String comment;
    comment = elements.getDocComment(element);

    if (comment != null && !comment.isEmpty()) {
      javadoc = comment.split(BIG_R);
    }

    return javadoc;
  }

  private void writeJavadoc0(String indentation, String[] javadoc) {
    if (javadoc == EMPTY_JAVADOC) {
      return;
    }

    out.append(indentation);

    out.append("/**");

    for (String s : javadoc) {
      out.append(NL);

      out.append(indentation);

      out.append(" *");

      out.append(s);
    }

    out.append(NL);

    out.append(indentation);

    out.append(" */");

    out.append(NL);
  }

  private void writeTypeArgument(TypeParameterElement e) {
    Name simpleName;
    simpleName = e.getSimpleName();

    out.append(simpleName.toString());
  }

}