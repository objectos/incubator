/*
 * Copyright (C) 2014-2023 Objectos Software LTDA.
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
package br.com.objectos.code.processing.type;

import br.com.objectos.latest.Concrete;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.type.TypeMirror;

@Concrete.Bridge
abstract class PTypeMirrorJava8 extends PTypeMirrorJavaAny {

  @Concrete.Constructor
  PTypeMirrorJava8(ProcessingEnvironment processingEnv) {
    super(processingEnv);
  }

  @Override
  protected final List<? extends AnnotationMirror> getAnnotationMirrors() {
    TypeMirror type;
    type = getType();

    return type.getAnnotationMirrors();
  }

}
