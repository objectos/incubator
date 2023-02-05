/*
 * Copyright (C) 2015-2023 Objectos Software LTDA.
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
package br.com.objectos.html.boot.spi.tmpl;

import static br.com.objectos.code.java.Java._final;
import static br.com.objectos.code.java.Java._package;
import static br.com.objectos.code.java.Java._public;
import static br.com.objectos.code.java.Java._void;
import static br.com.objectos.code.java.Java.annotation;
import static br.com.objectos.code.java.Java.empty;
import static br.com.objectos.code.java.Java.method;
import static br.com.objectos.code.java.Java.param;

import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.html.boot.util.Ids;

public class SpiTmpl {

  static final PackageName PACKAGE = _package("br.com.objectos.html.spi.tmpl");

  private static final NamedClass Marker = className("Marker");
  private static final NamedClass Renderer = className("Renderer");

  private SpiTmpl() {}

  public static MethodCode markMethod(String methodName) {
    return method(
        annotation(Override.class),
        _public(), _final(), _void(), Ids.mark,
        param(Marker, Ids.marker),
        Ids.marker.invoke(methodName)
    );
  }

  public static MethodCode renderMethod() {
    return method(
        annotation(Override.class),
        _public(), _final(), _void(), Ids.render,
        param(Renderer, Ids.renderer),
        empty()
    );
  }

  private static NamedClass className(String simpleName) {
    return PACKAGE.nestedClass(simpleName);
  }

}
