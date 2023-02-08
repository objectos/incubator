/*
 * Copyright (C) 2016-2023 Objectos Software LTDA.
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
package br.com.objectos.css.boot.sheet;

import br.com.objectos.code.java.expression.ExpressionName;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.type.NamedClass;

public interface FunctionOrProperty {

  String addMethodName();

  String enumName();

  Identifier getMethodName();

  NamedClass getMultiDeclarationName();

  NamedClass getSingleDeclarationName();

  default String methodName() {
    return getMethodName().name();
  }

  default String multiDeclarationSimpleName() {
    return getMultiDeclarationName().getSimpleName();
  }

  default String singleDeclarationSimpleName() {
    return getSingleDeclarationName().getSimpleName();
  }

  ExpressionName standardPropertyName();

}