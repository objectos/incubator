/*
 * Copyright (C) 2016-2022 Objectos Software LTDA.
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
package br.com.objectos.css.maven.plugin.framework;

import static br.com.objectos.code.java.Java._new;
import static br.com.objectos.code.java.Java.invoke;
import static br.com.objectos.code.java.Java.javaFile;

import br.com.objectos.code.java.declaration.ClassCode;
import br.com.objectos.code.java.declaration.MethodCode;
import br.com.objectos.code.java.declaration.Modifiers;
import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.css.config.framework.ConfigurationDsl.FrameworkGroup;
import objectos.util.GrowableList;

class FrameworkClass {

  private final ClassCode.Builder c;
  private final MethodCode.Builder def;
  private final GrowableList<MethodCode> groupMethods = new GrowableList<>();

  private final PackageName packageName;

  FrameworkClass(PackageName packageName) {
    this.packageName = packageName;

    c = ClassCode.builder();
    c.addAnnotation(FrameworkMojo.GENERATED);
    c.addModifier(Modifiers.ABSTRACT);
    c.simpleName("AbstractFramework");
    c.superclass(FrameworkTypes._AbstractStyleSheet);

    def = MethodCode.builder();
    def.addAnnotation(Override.class);
    def.addModifier(Modifiers.PROTECTED);
    def.name("definition");
  }

  public final void addGroup(FrameworkGroup group, Iterable<PropertyClass> properties) {
    String groupName = group.name().toLowerCase();
    def.addStatement(invoke(groupName));

    MethodCode.Builder groupMethod = MethodCode.builder();
    groupMethod.addModifier(Modifiers.PROTECTED);
    groupMethod.name(groupName);

    for (PropertyClass property : properties) {
      groupMethod.addStatement(invoke("install", _new(property.className)));
    }

    groupMethods.add(groupMethod.build());
  }

  public final void execute(ConfigurationAdapter adapter) {
    adapter.writeJavaFile(
        javaFile(
            packageName,
            classCode()
        )
    );
  }

  private ClassCode classCode() {
    c.addMethod(def.build());
    c.addMethods(groupMethods);
    return c.build();
  }

}
