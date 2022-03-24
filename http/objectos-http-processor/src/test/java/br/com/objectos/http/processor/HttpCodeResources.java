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
import br.com.objectos.way.code.annotation.processing.AbstractAnnotationProcessor;
import br.com.objectos.way.code.model.element.TypeElementQuery;
import br.com.objectos.way.code.testing.CompilationScript;
import java.util.HashMap;
import java.util.Map;
import javax.lang.model.element.TypeElement;

class HttpCodeResources extends AbstractAnnotationProcessor {

  private static final HttpCodeResources INSTANCE = new HttpCodeResources();

  static {
    new CompilationScript()
        .addProcessor(INSTANCE)
        .addCompilationUnit("/code/http/DirectoryModule.java")
        .addCompilationUnit("/code/http/MirrorModule.java")
        .addCompilationUnit("/code/http/ResponseModule.java")
        .compile();
  }

  private final Map<String, ModuleTypeArtifact> moduleMap = new HashMap<>();

  private HttpCodeResources() {
  }

  public static ModuleTypeArtifact moduleType(String typeName) {
    return INSTANCE.moduleMap.get(typeName);
  }

  @Override
  protected void configure() {
    process(Module.class).withTypeElementProcessor(this::moduleType0);
  }

  private void moduleType0(TypeElement aTypeElement) {
    TypeElementQuery typeElement = new TypeElementQuery(processingEnv, aTypeElement);
    ModuleType moduleType = ModuleType.of(typeElement);
    moduleMap.put(typeElement.simpleName(), new ModuleTypeArtifact(moduleType));
  }

}