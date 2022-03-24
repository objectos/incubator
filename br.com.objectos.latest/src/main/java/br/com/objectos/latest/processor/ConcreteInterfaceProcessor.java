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
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * Process {@link Concrete} annotated interfaces.
 */
public final class ConcreteInterfaceProcessor extends AbstractConcreteProcessor {

  @Override
  final List<? extends TypeMirror> getSuperTypes(TypeElement candidate) {
    if (!isInterface(candidate)) {
      return EMPTY_SUPER;
    }

    Concrete.Bridge bridge;
    bridge = candidate.getAnnotation(Concrete.Bridge.class);

    if (bridge == null) {
      return EMPTY_SUPER;
    }

    return candidate.getInterfaces();
  }

  @Override
  final boolean isCorrectType(Element element) {
    return isInterface(element);
  }

  @Override
  final void writeBody(LatestWriter w, String simpleName, TypeElement latestType) {
    // noop
  }

  @Override
  final void writeClassOrInterface(LatestWriter w) {
    w.write("interface");
  }

}
