/*
 * Copyright (C) 2014-2022 Objectos Software LTDA.
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
package br.com.objectos.code.boot;

import static br.com.objectos.code.java.statement.ReturnStatement._return;

import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.MethodCode.Builder;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.declaration.ParameterCode;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.expression.MethodInvocation;
import br.com.objectos.code.java.statement.Statement;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedType;
import br.com.objectos.code.java.type.NamedTypeVariable;
import br.com.objectos.code.java.type.SimpleNamedTypeVisitor;
import br.com.objectos.code.model.element.ProcessingMethod;
import br.com.objectos.code.model.element.ProcessingParameter;
import br.com.objectos.code.model.element.ProcessingType;
import br.com.objectos.code.processing.type.PTypeMirror;
import br.com.objectos.core.list.ImmutableList;
import br.com.objectos.core.list.MutableList;

final class StaticMethod {

  private final ProcessingMethod method;

  private final ProcessingType type;

  private StaticMethod(ProcessingType type, ProcessingMethod method) {
    this.type = type;
    this.method = method;
  }

  static ImmutableList<StaticMethod> listOf(ProcessingType type) {
    MutableList<StaticMethod> result;
    result = MutableList.create();

    ImmutableList<ProcessingMethod> methods;
    methods = type.getDeclaredOrInheritedMethods();

    for (int i = 0; i < methods.size(); i++) {
      ProcessingMethod method;
      method = methods.get(i);

      if (!StaticMethod.test(method)) {
        continue;
      }

      StaticMethod staticMethod;
      staticMethod = StaticMethod.ofUnchecked(type, method);

      result.add(staticMethod);
    }

    return result.toImmutableList();
  }

  static StaticMethod ofUnchecked(ProcessingType type, ProcessingMethod method) {
    return new StaticMethod(type, method);
  }

  private static boolean test(ProcessingMethod method) {
    if (!method.isPublic()) {
      return false;
    }

    if (!method.isStatic()) {
      return false;
    }

    if (IgnoreAnnotation.isAnnotatedWith(method)) {
      return false;
    }

    return true;
  }

  public final MethodCode generate() {
    MethodCode.Builder b;
    b = MethodCode.builder();

    b.addModifier(Modifiers.PUBLIC, Modifiers.STATIC);

    PTypeMirror returnType;
    returnType = method.getReturnType();

    NamedType returnTypeName;
    returnTypeName = returnType.getName();

    b.returnType(returnTypeName);

    String methodName;
    methodName = method.getName();

    b.name(methodName);

    MutableList<Identifier> names;
    names = MutableList.create();

    ImmutableList<ProcessingParameter> parameters;
    parameters = method.getParameters();

    for (int i = 0; i < parameters.size(); i++) {
      ProcessingParameter parameter;
      parameter = parameters.get(i);

      ParameterCode code;
      code = parameter.toParameterCode();

      b.addParameter(code);

      names.add(parameter.toIdentifier());
    }

    ImmutableList<PTypeMirror> thrownTypes;
    thrownTypes = method.getThrownTypes();

    for (int i = 0; i < thrownTypes.size(); i++) {
      PTypeMirror thrown;
      thrown = thrownTypes.get(i);

      NamedType name;
      name = thrown.getName();

      name.acceptTypeNameVisitor(Thrown.INSTANCE, b);
    }

    NamedClass enclosingClassName;
    enclosingClassName = type.getName();

    MethodInvocation invocation;
    invocation = enclosingClassName.invoke(
        methodName,
        names
    );

    Statement statement;

    if (returnType.isNoType()) {
      statement = invocation;
    } else {
      statement = _return(invocation);
    }

    b.addStatement(statement);

    return b.build();
  }

  private static class Thrown extends SimpleNamedTypeVisitor<Void, MethodCode.Builder> {

    static final Thrown INSTANCE = new Thrown();

    @Override
    public final Void visitNamedClass(NamedClass t, MethodCode.Builder p) {
      p.addThrownType(t);

      return null;
    }

    @Override
    public final Void visitNamedTypeVariable(NamedTypeVariable t, MethodCode.Builder p) {
      p.addThrownType(t);

      return null;
    }

    @Override
    protected final Void defaultAction(NamedType t, Builder p) {
      throw new UnsupportedOperationException();
    }

  }

}
