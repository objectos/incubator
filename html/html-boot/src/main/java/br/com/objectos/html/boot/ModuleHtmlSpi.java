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
package br.com.objectos.html.boot;

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.html.boot.spec.AttributeSpec;
import br.com.objectos.html.boot.spec.ElementSpec;
import br.com.objectos.html.boot.spec.Step;
import br.com.objectos.html.boot.spec.TemplateSpec;
import br.com.objectos.html.boot.spec.TextSpec;
import br.com.objectos.html.boot.spi.type.AnyElementValueStep;
import br.com.objectos.html.boot.spi.type.ElementValueIfaceStep;
import br.com.objectos.html.boot.spi.type.NonVoidElementValueStep;
import java.util.function.Consumer;

class ModuleHtmlSpi implements Step {

  private final AnyElementValueStep anyElementValueStep;
  private final ElementValueIfaceStep elementValueIfaceStep;
  private final NonVoidElementValueStep nonVoidElementValueStep;

  ModuleHtmlSpi(Consumer<JavaFile> javaFileWriter) {
    anyElementValueStep = new AnyElementValueStep(javaFileWriter);
    elementValueIfaceStep = new ElementValueIfaceStep(javaFileWriter);
    nonVoidElementValueStep = new NonVoidElementValueStep(javaFileWriter);
  }

  @Override
  public final void attributeSpec(AttributeSpec attributeSpec) {}

  @Override
  public final void elementSpec(ElementSpec elementSpec) {
    anyElementValueStep.elementSpec(elementSpec);
    elementValueIfaceStep.elementSpec(elementSpec);
    nonVoidElementValueStep.elementSpec(elementSpec);
  }

  @Override
  public final void execute() {
    anyElementValueStep.execute();
    nonVoidElementValueStep.execute();
  }

  @Override
  public final void templateSpec(TemplateSpec template) {}

  @Override
  public final void textSpec(TextSpec text) {}

}
