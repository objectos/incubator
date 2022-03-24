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

import br.com.objectos.http.processor.common.ConfigureMethodSpec;
import br.com.objectos.http.server.HttpRuntime;
import br.com.objectos.way.code.common.AccessModifier;
import br.com.objectos.way.code.model.element.ExecutableElementQuery;
import br.com.objectos.way.code.model.element.TypeElementQuery;
import br.com.objectos.way.code.model.type.TypeMirrorQuery;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import javax.lang.model.element.Modifier;

abstract class RuntimeMethod {

  private static final RuntimeMethod EMPTY = new Empty();

  private RuntimeMethod() {
  }

  public static RuntimeMethod of(TypeElementQuery typeElement) {
    return ExecutableElementQuery.distinctMethodStream(typeElement)
        .filter(m -> !m.hasAccessModifier(AccessModifier.PRIVATE))
        .filter(m -> m.hasModifier(Modifier.ABSTRACT))
        .filter(m -> m.returnType().isSubType(HttpRuntime.class))
        .filter(m -> m.hasParameterListSize(0))
        .findFirst()
        .map(RuntimeMethod::of)
        .orElse(EMPTY);
  }

  private static RuntimeMethod of(ExecutableElementQuery method) {
    return new Present(method);
  }

  public abstract void acceptConfigureBody(ConfigureMethodSpec method);

  public abstract void acceptModuleType(TypeSpec.Builder type);

  static class Empty extends RuntimeMethod {

    @Override
    public void acceptConfigureBody(ConfigureMethodSpec method) {
      method.beginConfiguration();
    }

    @Override
    public void acceptModuleType(TypeSpec.Builder type) {
      // noop
    }

  }

  static class Present extends RuntimeMethod {

    private final ExecutableElementQuery methodElement;

    public Present(ExecutableElementQuery methodElement) {
      this.methodElement = methodElement;
    }

    @Override
    public void acceptConfigureBody(ConfigureMethodSpec method) {
      method.runtime(methodElement.fieldName());
      method.beginConfiguration();
    }

    @Override
    public void acceptModuleType(TypeSpec.Builder type) {
      TypeMirrorQuery returnType = methodElement.returnType();
      type.addField(FieldSpec.builder(TypeName.get(returnType.subject()), methodElement.fieldName())
          .addModifiers(Modifier.PRIVATE)
          .build())
          .addMethod(MethodSpec.overriding(methodElement.subject())
              .addStatement("return $L", methodElement.fieldName())
              .build());
    }

  }

}