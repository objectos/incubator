/*
 * Copyright (C) 2020-2022 Objectos Software LTDA.
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

import br.com.objectos.latest.Singleton;
import br.com.objectos.latest.Singleton.Field;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Process {@link Singleton} annotated classes.
 */
public final class SingletonProcessor extends AbstractLatestProcessor {

  @Override
  final Class<? extends Annotation> getAnnotationType() {
    return Singleton.class;
  }

  @Override
  final List<? extends TypeMirror> getSuperTypes(TypeElement candidate) {
    if (!isClass(candidate)) {
      return EMPTY_SUPER;
    }

    TypeMirror superclass;
    superclass = candidate.getSuperclass();

    return Collections.singletonList(superclass);
  }

  @Override
  final void processElement(Element element) {
    if (!isClass(element)) {
      notifyUser(element);

      return;
    }

    TypeElement singletonType;
    singletonType = asTypeElement(element);

    LatestEntry latestEntry;
    latestEntry = findLatestEntry(singletonType);

    TypeElement latestType;
    latestType = latestEntry.element;

    VariableElement fieldElement;
    fieldElement = null;

    List<? extends Element> enclosedElements;
    enclosedElements = latestType.getEnclosedElements();

    for (int i = 0; i < enclosedElements.size(); i++) {
      Element maybeField;
      maybeField = enclosedElements.get(i);

      if (maybeField.getKind() != ElementKind.FIELD) {
        continue;
      }

      Field annotation;
      annotation = maybeField.getAnnotation(Singleton.Field.class);

      if (annotation == null) {
        continue;
      }

      fieldElement = VariableElement.class.cast(maybeField);
    }

    String fieldName;
    fieldName = "INSTANCE";

    TypeMirror fieldType;
    fieldType = null;

    if (fieldElement != null) {
      Name simpleName;
      simpleName = fieldElement.getSimpleName();

      fieldName = simpleName.toString();

      fieldType = fieldElement.asType();
    }

    List<String> names;
    names = getNames(singletonType);

    String simpleName;
    simpleName = getNameWithSuffix(names, "Singleton");

    // writing

    LatestWriter w;
    w = new LatestWriter(processingEnv);

    w.writePackageName(singletonType);

    w.writeImports();

    w.writeGenerated(SingletonProcessor.class);

    w.write("final class ");

    w.write(simpleName);

    w.write(" {");

    w.newLine();

    w.write("  static final ");

    if (fieldType != null) {
      w.write(fieldType.toString());
    } else {
      writeQualifiedNameWithoutPackage(w, names);
    }

    w.write(" INSTANCE = ");

    w.write(latestEntry.name);

    w.write('.');

    w.write(fieldName);

    w.write(';');

    w.newLine();

    w.newLine();

    w.write("  private ");

    w.write(simpleName);

    w.write("() {}");

    w.newLine();

    w.write("}");

    w.flush(singletonType, simpleName);
  }

  private List<String> getNames(TypeElement element) {
    List<String> list;
    list = new ArrayList<String>();

    Element outer;
    outer = element;

    while (outer.getKind() != ElementKind.PACKAGE) {
      Name simpleName;
      simpleName = outer.getSimpleName();

      list.add(simpleName.toString());

      outer = outer.getEnclosingElement();
    }

    Collections.reverse(list);

    return list;
  }

  private String getNameWithSuffix(List<String> names, String suffix) {
    StringBuilder result;
    result = new StringBuilder();

    for (String name : names) {
      result.append(name);
    }

    result.append(suffix);

    return result.toString();
  }

  private void writeQualifiedNameWithoutPackage(LatestWriter w, List<String> names) {
    String firstName;
    firstName = names.get(0);

    w.write(firstName);

    for (int i = 1; i < names.size(); i++) {
      w.write('.');

      String nextName;
      nextName = names.get(i);

      w.write(nextName);
    }
  }

}
