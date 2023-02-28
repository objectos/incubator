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
package br.com.objectos.html.boot;

import java.io.IOException;
import objectos.code.ClassTypeName;
import objectos.code.JavaSink;
import objectos.code.JavaTemplate;

abstract class ThisTemplate extends JavaTemplate {

  static final String attr = "br.com.objectos.html.attribute";

  static final String elem = "br.com.objectos.html.element";

  static final String spi_tmpl = "br.com.objectos.html.spi.tmpl";

  static final String spi_type = "br.com.objectos.html.spi.type";

  static final ClassTypeName ANY_ELEMENT_VALUE = ClassTypeName.of(spi_type, "AnyElementValue");

  SpecDsl spec;

  public void write(JavaSink sink, SpecDsl spec) throws IOException {
    this.spec = spec;

    sink.write(this);
  }

}