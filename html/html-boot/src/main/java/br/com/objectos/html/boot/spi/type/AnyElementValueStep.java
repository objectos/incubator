/*
 * Copyright (C) 2015-2022 Objectos Software LTDA.
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
package br.com.objectos.html.boot.spi.type;

import static br.com.objectos.code.java.Java._extends;
import static br.com.objectos.code.java.Java._interface;
import static br.com.objectos.code.java.Java._public;

import br.com.objectos.code.java.declaration.InterfaceCode;
import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.core.list.MutableList;
import br.com.objectos.html.boot.HtmlBoot;
import br.com.objectos.html.boot.spec.AbstractJavaFileStep;
import br.com.objectos.html.boot.spec.ElementSpec;
import java.util.function.Consumer;

public class AnyElementValueStep extends AbstractJavaFileStep {

  private final MutableList<NamedClass> interfaces = MutableList.create();

  public AnyElementValueStep(Consumer<JavaFile> javaFileWriter) {
    super(javaFileWriter);
  }

  @Override
  public final void elementSpec(ElementSpec elementSpec) {
    interfaces.add(elementSpec.classNameValue());
  }

  @Override
  public final void execute() {
    generateJavaFile(SpiType.PACKAGE, ifaceCode());
  }

  private InterfaceCode ifaceCode() {
    return _interface(
        HtmlBoot.GENERATED,
        _public(), SpiType.AnyElementValue, _extends(interfaces)
    );
  }

}
