/*
 * Copyright (C) 2016-2021 Objectos Software LTDA.
 *
 * This file is part of the ObjectosHttp project.
 *
 * ObjectosHttp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * ObjectosHttp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ObjectosHttp.  If not, see <https://www.gnu.org/licenses/>.
 */
package br.com.objectos.http.processor;

import br.com.objectos.http.server.Module;
import br.com.objectos.metainf.annotations.Services;
import br.com.objectos.way.code.annotation.processing.AbstractAnnotationProcessor;
import br.com.objectos.way.code.model.element.TypeElementQuery;
import br.com.objectos.way.code.writer.AnnotationSpecs;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import javax.annotation.processing.Processor;
import javax.lang.model.element.TypeElement;

@Services(Processor.class)
public class ModuleProcessor extends AbstractAnnotationProcessor {

  static final AnnotationSpec GENERATED = AnnotationSpecs.generated(ModuleProcessor.class);

  @Override
  protected void configure() {
    process(Module.class).withTypeElementProcessor(this::process);
  }

  private void process(TypeElement aTypeElement) {
    TypeElementQuery typeElement = new TypeElementQuery(processingEnv, aTypeElement);
    JavaFile file = ModuleType.of(typeElement).generate();
    write(file);
  }

}