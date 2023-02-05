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

import br.com.objectos.code.java.io.JavaFile;
import br.com.objectos.html.boot.attribute.StandardAttributeNameStep;
import br.com.objectos.html.boot.element.StandardElementNameStep;
import br.com.objectos.html.boot.spec.AttributeSpec;
import br.com.objectos.html.boot.spec.ElementSpec;
import br.com.objectos.html.boot.spec.Step;
import br.com.objectos.html.boot.spec.TemplateSpec;
import br.com.objectos.html.boot.spec.TextSpec;
import br.com.objectos.html.boot.tmpl.GeneratedAbstractTemplateStep;
import java.util.function.Consumer;

class ModuleHtml implements Step {

  private final GeneratedAbstractTemplateStep generatedAbstractTemplateStep;
  private final StandardAttributeNameStep standardAttributeNameStep;
  private final StandardElementNameStep standardElementNameStep;

  ModuleHtml(Consumer<JavaFile> javaFileWriter) {
    generatedAbstractTemplateStep = new GeneratedAbstractTemplateStep(javaFileWriter);
    standardAttributeNameStep = new StandardAttributeNameStep(javaFileWriter);
    standardElementNameStep = new StandardElementNameStep(javaFileWriter);
  }

  @Override
  public final void attributeSpec(AttributeSpec attributeSpec) {
    generatedAbstractTemplateStep.attributeSpec(attributeSpec);
    standardAttributeNameStep.attributeSpec(attributeSpec);
  }

  @Override
  public final void elementSpec(ElementSpec elementSpec) {
    generatedAbstractTemplateStep.elementSpec(elementSpec);
    standardElementNameStep.elementSpec(elementSpec);
  }

  @Override
  public final void execute() {
    generatedAbstractTemplateStep.execute();
    standardAttributeNameStep.execute();
    standardElementNameStep.execute();
  }

  @Override
  public final void templateSpec(TemplateSpec template) {
    generatedAbstractTemplateStep.templateSpec(template);
  }

  @Override
  public final void textSpec(TextSpec text) {}

}