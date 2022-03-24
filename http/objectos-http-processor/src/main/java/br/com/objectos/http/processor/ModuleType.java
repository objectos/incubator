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
import br.com.objectos.http.processor.common.RoutePath;
import br.com.objectos.http.server.HttpModule;
import br.com.objectos.http.server.Module;
import br.com.objectos.way.code.model.element.AnnotationValueQuery;
import br.com.objectos.way.code.model.element.ExecutableElementQuery;
import br.com.objectos.way.code.model.element.TypeElementQuery;
import br.com.objectos.way.code.writer.ConstructorWriter;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.lang.model.element.Modifier;

class ModuleType {

  private final TypeElementQuery typeElement;
  private final RoutePath path;
  private final ClassName className;
  private final ClassName superClassName;
  private final List<ActionMethod> actionMethodList;
  private final RuntimeMethod runtimeMethod;

  private ModuleType(TypeElementQuery typeElement,
                     RoutePath path,
                     ClassName className,
                     ClassName superClassName,
                     List<ActionMethod> actionMethodList,
                     RuntimeMethod runtimeMethod) {
    this.typeElement = typeElement;
    this.path = path;
    this.className = className;
    this.superClassName = superClassName;
    this.actionMethodList = actionMethodList;
    this.runtimeMethod = runtimeMethod;
  }

  public static ModuleType of(TypeElementQuery typeElement) {
    String path = typeElement.annotationMirror(Module.class)
        .flatMap(ann -> ann.elementValue("value"))
        .map(AnnotationValueQuery::valueAsString)
        .orElse("");
    RoutePath modulePath = RoutePath.parse(path);
    ClassName className = ClassName.get(typeElement.subject());
    return new ModuleType(
        typeElement,
        modulePath,
        className.peerClass("Http" + typeElement.simpleName()),
        className,
        ActionMethod.listOf(typeElement),
        RuntimeMethod.of(typeElement));
  }

  public JavaFile generate() {
    return JavaFile.builder(typeElement.packageName(), typeSpec())
        .skipJavaLangImports(true)
        .build();
  }

  List<ActionMethod> actionMethodList() {
    return actionMethodList;
  }

  MethodSpec configureMethodSpec() {
    ConfigureMethodSpec body = new ConfigureMethodSpec();

    runtimeMethod.acceptConfigureBody(body);

    Iterator<ActionMethod> methods = actionMethodList.iterator();
    if (methods.hasNext()) {
      ActionMethod method = methods.next();
      method.acceptConfigureBody(path, body);
      while (methods.hasNext()) {
        body.nextLine();
        method = methods.next();
        method.acceptConfigureBody(path, body);
      }
    }

    return body.build();
  }

  List<MethodSpec> constructorList() {
    return typeElement.declaredExecutableElementsStream()
        .filter(ExecutableElementQuery::isConstructor)
        .map(c -> new ConstructorWriter(c.subject())
            .accessModifier(c.accessModifier())
            .addParameterList()
            .addStandardSuperStatement()
            .write())
        .collect(Collectors.toList());
  }

  RuntimeMethod runtimeMethod() {
    return runtimeMethod;
  }

  TypeSpec typeSpec() {
    TypeSpec.Builder type = TypeSpec.classBuilder(className)
        .addAnnotation(ModuleProcessor.GENERATED)
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .superclass(superClassName)
        .addSuperinterface(HttpModule.class)
        .addMethods(constructorList())
        .addMethod(configureMethodSpec());

    runtimeMethod.acceptModuleType(type);

    return type
        .addMethods(actionMethodList.stream()
            .map(ActionMethod::actionMethodSpec)
            .collect(Collectors.toList()))
        .build();
  }

}