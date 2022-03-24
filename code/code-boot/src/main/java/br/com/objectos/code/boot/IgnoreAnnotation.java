/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.boot;

import br.com.objectos.code.annotations.Ignore;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.AnnotatedElementOrType;
import br.com.objectos.code.model.element.ProcessingAnnotation;
import br.com.objectos.core.list.ImmutableList;

class IgnoreAnnotation {

  private static final NamedClass IGNORE = NamedClass.of(Ignore.class);

  private IgnoreAnnotation() {}

  public static boolean isAnnotatedWith(AnnotatedElementOrType element) {
    ImmutableList<ProcessingAnnotation> annotations;
    annotations = element.getDirectlyPresentAnnotations();

    for (int i = 0; i < annotations.size(); i++) {
      ProcessingAnnotation annotation;
      annotation = annotations.get(i);

      NamedClass name;
      name = annotation.className();

      if (name.equals(IGNORE)) {
        return true;
      }
    }

    return false;
  }

}
