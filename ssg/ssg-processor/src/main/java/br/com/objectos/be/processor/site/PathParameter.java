/*
 * Copyright (C) 2011-2022 Objectos Software LTDA.
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
package br.com.objectos.be.processor.site;

import static br.com.objectos.code.java.Java.id;
import static br.com.objectos.code.java.Java.invoke;

import br.com.objectos.be.processor.Ids;
import br.com.objectos.be.resource.AbstractPath;
import br.com.objectos.code.java.JavaNames;
import br.com.objectos.code.java.expression.Arguments;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.model.element.ProcessingConstructor;
import br.com.objectos.code.model.element.ProcessingParameter;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.type.PDeclaredType;
import br.com.objectos.code.processing.type.PTypeMirror;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;

class PathParameter {

  final Identifier identifier;

  PathParameter(Identifier identifier) {
    this.identifier = identifier;
  }

  public static ImmutableList<PathParameter> fromDirectory(ProcessingType directory) {
    ImmutableList<ProcessingConstructor> constructors;
    constructors = directory.getDeclaredConstructors();

    ProcessingConstructor constructor;
    constructor = constructors.get(0);

    return fromDirectoryConstructor(constructor);
  }

  public static PathParameter named(String name) {
    return new PathParameter(id(name));
  }

  private static ImmutableList<PathParameter> fromDirectoryConstructor(
      ProcessingConstructor constructor) {
    MutableList<PathParameter> result;
    result = MutableList.create();

    ImmutableList<ProcessingParameter> parameters;
    parameters = constructor.getParameters();

    for (int i = 0; i < parameters.size(); i++) {
      ProcessingParameter parameter;
      parameter = parameters.get(i);

      PathParameter path;
      path = fromParameter(parameter);

      result.add(path);
    }

    return result.toImmutableList();
  }

  private static PathParameter fromParameter(ProcessingParameter parameter) {
    PTypeMirror type;
    type = parameter.getType();

    if (!type.isInstanceOf(AbstractPath.class)) {
      throw new UnsupportedOperationException("Implement me");
    }

    PDeclaredType parameterClass;
    parameterClass = type.toDeclaredType();

    ProcessingType parameterType;
    parameterType = parameterClass.toProcessingType();

    String simpleName;
    simpleName = parameterType.getSimpleName();

    String prefix;
    prefix = simpleName.substring(0, simpleName.length() - 4);

    String methodName;
    methodName = JavaNames.toValidMethodName(prefix);

    Identifier identifier;
    identifier = id(methodName);

    return new PathParameter(identifier);
  }

  public final void acceptDirectoryArgumentsBuilder(Arguments.Builder arguments) {
    arguments.addArgument(generateHelperExpression());
    arguments.addNewLine();
  }

  @Override
  public final boolean equals(Object obj) {
    PathParameter that = (PathParameter) obj;
    return identifier.equals(that.identifier);
  }

  public final MethodInvocation generateHelperExpression() {
    return invoke(Ids.helper, identifier.name(), Ids.from);
  }

}
