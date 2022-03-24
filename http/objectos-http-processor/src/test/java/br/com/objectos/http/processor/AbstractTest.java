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

import br.com.objectos.http.processor.common.ActionParameter;
import br.com.objectos.way.code.testing.JavaPoetAssert;
import br.com.objectos.way.code.testing.JavaPoetAssert.AtAssert;
import java.util.Arrays;
import java.util.List;

abstract class AbstractTest {

  static final AtAssert ASSERT = JavaPoetAssert.at("code/http-output");

  @SuppressWarnings("unchecked")
  <T> List<T> list(T... els) {
    return Arrays.asList(els);
  }

  ActionMethod actionMethod(String typeName, String methodName) {
    return moduleType(typeName)
        .actionMethodList()
        .stream()
        .filter(m -> m.hasName(methodName))
        .findFirst()
        .get();
  }

  ActionParameter actionParameter(String typeName, String methodName, String paramName) {
    return actionMethod(typeName, methodName)
        .parameterList()
        .stream()
        .filter(p -> p.hasName(paramName))
        .findFirst()
        .get();
  }

  ModuleTypeArtifact moduleType(String typeName) {
    return HttpCodeResources.moduleType(typeName);
  }

}