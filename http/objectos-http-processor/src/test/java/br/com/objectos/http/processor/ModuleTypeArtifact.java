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

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.util.List;

class ModuleTypeArtifact {

  private final List<MethodSpec> constructorList;
  private final List<ActionMethod> actionMethodList;
  private final MethodSpec configureMethodSpec;
  private final RuntimeMethod runtimeMethod;
  private final TypeSpec typeSpec;

  public ModuleTypeArtifact(ModuleType moduleType) {
    constructorList = moduleType.constructorList();
    actionMethodList = moduleType.actionMethodList();
    configureMethodSpec = moduleType.configureMethodSpec();
    runtimeMethod = moduleType.runtimeMethod();
    typeSpec = moduleType.typeSpec();
  }

  public List<MethodSpec> constructorList() {
    return constructorList;
  }

  public List<ActionMethod> actionMethodList() {
    return actionMethodList;
  }

  public MethodSpec configureMethodSpec() {
    return configureMethodSpec;
  }

  public TypeSpec typeSpec() {
    return typeSpec;
  }

  public RuntimeMethod runtimeMethod() {
    return runtimeMethod;
  }

}