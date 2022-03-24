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
import br.com.objectos.http.processor.common.ConfigureMethodSpec;
import br.com.objectos.http.processor.common.RoutePartException;
import br.com.objectos.http.processor.common.RoutePath;
import br.com.objectos.http.server.HttpMethodAnnotation;
import br.com.objectos.http.server.Method;
import br.com.objectos.http.server.Request;
import br.com.objectos.http.server.Response;
import br.com.objectos.way.code.model.element.AnnotationValueQuery;
import br.com.objectos.way.code.model.element.ExecutableElementQuery;
import br.com.objectos.way.code.model.element.TypeElementQuery;
import br.com.objectos.way.util.MoreCollectors;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.lang.model.element.Modifier;

class ActionMethod {

  private final ExecutableElementQuery methodElement;

  private final RoutePath path;
  private final String name;
  private final Method method;
  private final ActionKind kind;
  private final List<ActionParameter> parameterList;

  private final String generatedName;

  private ActionMethod(ExecutableElementQuery methodElement,
                       RoutePath path,
                       String name,
                       Method method,
                       ActionKind kind,
                       List<ActionParameter> parameterList) {
    this.methodElement = methodElement;
    this.path = path;
    this.name = name;
    this.method = method;
    this.kind = kind;
    this.parameterList = parameterList;

    generatedName = "___" + name + "___";
  }

  public static List<ActionMethod> listOf(TypeElementQuery typeElement) {
    return ExecutableElementQuery.distinctMethodStream(typeElement)
        .map(ActionMethod::of)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(MoreCollectors.toImmutableList());
  }

  private static Optional<ActionMethod> of(ExecutableElementQuery method) {
    return method.annotationMirrorList().stream()
        .map(ann -> ann.annotationMirror(HttpMethodAnnotation.class))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findFirst()
        .flatMap(ann -> ann.elementValue("value"))
        .map(v -> v.valueAsEnum(Method.class))
        .flatMap(m -> of0(method, m));
  }

  private static Optional<ActionMethod> of0(ExecutableElementQuery exe, Method method) {
    return exe.annotationMirror(method.annotationType())
        .flatMap(ann -> ann.elementValue("value"))
        .map(AnnotationValueQuery::valueAsString)
        .flatMap(path -> of1(exe, method, path));
  }

  private static Optional<ActionMethod> of1(ExecutableElementQuery exe, Method method, String path) {
    try {
      List<ActionParameter> parameterList = ActionParameter.listOf(exe);
      RoutePath routePath = RoutePath.parse(path, parameterList);
      ActionMethod value = new ActionMethod(
          exe,
          routePath,
          exe.name(),
          method,
          ActionKind.of(exe),
          parameterList);
      return Optional.of(value);
    } catch (RoutePartException e) {
      exe.compilationError(e.getMessage());
      return Optional.empty();
    }
  }

  public void acceptConfigureBody(RoutePath routePath, ConfigureMethodSpec body) {
    RoutePath sum = routePath.add(path);
    body.route(sum);
    sum.acceptConfigureBody(body);
    body.method(method, "this", generatedName);
  }

  public MethodSpec actionMethodSpec() {
    return MethodSpec.methodBuilder(generatedName)
        .addModifiers(Modifier.PRIVATE)
        .returns(Response.class)
        .addExceptions(methodElement.subject()
            .getThrownTypes()
            .stream()
            .map(TypeName::get)
            .collect(Collectors.toList()))
        .addParameter(Request.class, "request")
        .addCode(actionMethodBody())
        .build();
  }

  boolean hasName(String methodName) {
    return name.equals(methodName);
  }

  List<ActionParameter> parameterList() {
    return parameterList;
  }

  private CodeBlock actionMethodBody() {
    CodeBlock.Builder body = CodeBlock.builder();
    StringBuilder params = new StringBuilder();

    Iterator<ActionParameter> iterator = parameterList.iterator();
    if (iterator.hasNext()) {
      ActionParameter param = iterator.next();
      params.append(param.name());
      param.argumentCodeBlock(body);

      while (iterator.hasNext()) {
        param = iterator.next();
        params.append(", ").append(param.name());
        param.argumentCodeBlock(body);
      }
    }

    return kind.returnStatement(body, name, params.toString()).build();
  }

}