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

import br.com.objectos.latest.Concrete;
import java.util.Collections;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * Process {@link Concrete} annotated classes.
 */
public final class ConcreteClassProcessor extends AbstractConcreteProcessor {

  @Override
  final List<? extends TypeMirror> getSuperTypes(TypeElement candidate) {
    if (!isClass(candidate)) {
      return EMPTY_SUPER;
    }

    Concrete.Bridge bridge;
    bridge = candidate.getAnnotation(Concrete.Bridge.class);

    if (bridge == null) {
      return EMPTY_SUPER;
    }

    TypeMirror superclass;
    superclass = candidate.getSuperclass();

    return Collections.singletonList(superclass);
  }

  @Override
  final boolean isCorrectType(Element element) {
    return isClass(element);
  }

  @Override
  final void writeBody(LatestWriter w, String simpleName, TypeElement latest) {
    List<ConcreteConstructor> constructors;
    constructors = ConcreteConstructor.listOf(processingEnv, latest);

    for (int i = 0, size = constructors.size(); i < size; i++) {
      ConcreteConstructor constructor;
      constructor = constructors.get(i);

      constructor.write(w, simpleName);
    }

    if (!constructors.isEmpty()) {
      w.newLine();

      w.newLine();
    }
  }

  @Override
  final void writeClassOrInterface(LatestWriter w) {
    w.write("class");
  }

}
