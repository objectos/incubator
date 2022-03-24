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

import br.com.objectos.http.server.HttpModuleDsl;
import br.com.objectos.http.server.Method;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import javax.lang.model.element.Modifier;

public class ConfigureMethodSpec {

  private final MethodSpec.Builder method = MethodSpec.methodBuilder("configure")
      .addAnnotation(Override.class)
      .addModifiers(Modifier.PUBLIC)
      .addParameter(HttpModuleDsl.class, "configuration");

  private final CodeBlock.Builder body = CodeBlock.builder();

  public ConfigureMethodSpec() {
  }

  public ConfigureMethodSpec addStatement(String format, Object... args) {
    method.addStatement(format, args);
    return this;
  }

  public ConfigureMethodSpec beginConfiguration() {
    body.add("configuration\n");
    return this;
  }

  public MethodSpec build() {
    return method
        .addCode(body.add(";\n").build())
        .build();
  }

  public void catchAllPart() {
    body.add("    .catchAllPart()\n");
  }

  public void fixedPart(String value) {
    body.add("    .fixedPart($S)\n", value);
  }

  public void intPart() {
    body.add("    .intPart()\n");
  }

  public void method(Method m, String reference, String methodName) {
    body.add("    .$L($L::$L)", m.configurationMethodName(), reference, methodName);
  }

  public void nextLine() {
    body.add("\n");
  }

  public void onGet(String format, Object... args) {
    body
        .add("    .$L(", Method.GET.configurationMethodName())
        .add(format, args)
        .add(")");
  }

  public void onPost(String format, Object... args) {
    body
        .add("    .$L(", Method.POST.configurationMethodName())
        .add(format, args)
        .add(")");
  }

  public void stringPart() {
    body.add("    .stringPart()\n");
  }

  public void route(Object path) {
    body.add("    .route($S)\n", path);
  }

  public void runtime(String fieldName) {
    body.addStatement("$L = configuration.runtime()", fieldName);
  }

}