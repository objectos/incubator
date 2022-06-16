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
package br.com.objectos.http.processor.common;

import br.com.objectos.http.processor.common.ActionParameterConstructor.IndexGenerator;
import br.com.objectos.way.code.model.element.ExecutableElementQuery;
import br.com.objectos.way.util.UnmodifiableMap;
import br.com.objectos.way.util.MoreCollectors;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;
import java.util.List;
import java.util.Map;

public abstract class ActionParameter {

  private static final Map<String, ActionParameterFactory> FACTORY_MAP = UnmodifiableMap
      .<String, ActionParameterFactory>builder()
      .put("int", ActionParameterInt::new)
      .put("java.lang.String", ActionParameterString::new)
      .build();

  private static final ActionParameterFactory UNSUPPORTED = ActionParameterUnsupported::new;

  private final int index;
  private final String name;

  ActionParameter(ActionParameterConstructor constructor) {
    index = constructor.index();
    name = constructor.name();
  }

  public static List<ActionParameter> listOf(ExecutableElementQuery method) {
    IndexGenerator index = ActionParameterConstructor.newIndexGenerator();
    return method.parameterStream()
        .map(param -> ActionParameterConstructor.ofParameter(index, param))
        .map(ActionParameter::of)
        .collect(MoreCollectors.toUnmodifiableList());
  }

  public static ActionParameter of(ActionParameterConstructor constructor) {
    String qname = constructor.qualifiedName();

    ActionParameterFactory factory = FACTORY_MAP.get(qname);
    if (factory == null) {
      constructor.compilationError("Unsupported type");
      factory = UNSUPPORTED;
    }

    return factory.create(constructor);
  }

  public abstract void acceptConfigureBody(ConfigureMethodSpec method);

  public void argumentCodeBlock(CodeBlock.Builder body) {
    body.addStatement("$T $L = request.$L($L)", typeName(), name, getMethod(), index);
  }

  public boolean hasName(String paramName) {
    return name.equals(paramName);
  }

  public String name() {
    return name;
  }

  abstract void accept(RoutePathVisitor visitor);

  abstract String getMethod();

  abstract TypeName typeName();

}