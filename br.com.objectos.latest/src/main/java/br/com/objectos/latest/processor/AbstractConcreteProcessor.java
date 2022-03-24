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

import br.com.objectos.latest.Concrete;
import br.com.objectos.latest.Concrete.Bridge;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

abstract class AbstractConcreteProcessor extends AbstractLatestProcessor {

  @Override
  final Class<? extends Annotation> getAnnotationType() {
    return Concrete.class;
  }

  abstract boolean isCorrectType(Element element);

  @Override
  final void processElement(Element element) {
    if (!isCorrectType(element)) {
      notifyUser(element);

      return;
    }

    TypeElement concreteType;
    concreteType = asTypeElement(element);

    Concrete concrete;
    concrete = concreteType.getAnnotation(Concrete.class);

    String modifiers;
    modifiers = concrete.modifiers();

    String simpleName;
    simpleName = concrete.simpleName();

    LatestEntry latestEntry;
    latestEntry = findLatestEntry(concreteType);

    TypeElement latestType;
    latestType = latestEntry.element;

    List<String> annotations;
    annotations = Collections.emptyList();

    Bridge bridge;
    bridge = latestType.getAnnotation(Bridge.class);

    if (bridge != null) {
      String[] value;
      value = bridge.annotations();

      annotations = Arrays.asList(value);
    }

    // writing
    LatestWriter w;
    w = new LatestWriter(processingEnv);

    w.writePackageName(concreteType);

    w.writeImports();

    w.writeJavadoc("", latestType, concreteType);

    w.writeGenerated(getClass());

    for (int i = 0, size = annotations.size(); i < size; i++) {
      String annotation;
      annotation = annotations.get(i);

      w.write(annotation);

      w.newLine();
    }

    w.write(modifiers);

    if (!modifiers.isEmpty()) {
      w.write(' ');
    }

    writeClassOrInterface(w);

    w.write(' ');

    w.write(simpleName);

    w.writeTypeParameters(concreteType);

    w.write(" extends ");

    w.write(latestEntry.name);

    w.writeTypeArguments(concreteType);

    w.write(" {");

    writeBody(w, simpleName, latestType);

    w.write("}");

    w.flush(concreteType, simpleName);
  }

  abstract void writeBody(LatestWriter w, String simpleName, TypeElement latestType);

  abstract void writeClassOrInterface(LatestWriter w);

}