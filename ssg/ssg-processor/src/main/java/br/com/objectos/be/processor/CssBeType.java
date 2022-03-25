/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.processor;

import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._interface;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.l;

import br.com.objectos.be.meta.MetaBeCss;
import br.com.objectos.code.java.Java;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.model.element.ProcessingType;

final class CssBeType extends BeType {

  private final NamedClass className;

  CssBeType(NamedClass className) {
    this.className = className;
  }

  public static BeType of0(ProcessingType type) {
    return new CssBeType(
        type.getName()
    );
  }

  @Override
  public final boolean generatesIntermediateType() {
    return false;
  }

  @Override
  public final JavaFile intermediateTypeGen() {
    throw new UnsupportedOperationException();
  }

  @Override
  public final JavaFile resourceIfaceGen() {
    return Java.javaFile(
        className.getPackage(),

        _interface(
            BeProcessor.GENERATED,
            annotation(MetaBeCss.class, l(className)),
            _public(), className.withSuffix("Css"), _extends(TypeNames.CssResource)
        )
    );
  }

}