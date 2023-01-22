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
package br.com.objectos.css.specgen.spec;

import static br.com.objectos.code.java.Java.javaFile;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.declaration.TypeCode;
import objectos.util.UnmodifiableList;

public abstract class Step {

  private final StepAdapter adapter;

  protected Step(StepAdapter adapter) {
    this.adapter = adapter;
  }

  public void addProperty(Property property) {

  }

  public void addProperty(Property property, UnmodifiableList<Property> group) {

  }

  public void execute() {}

  protected final void writeJavaFile(PackageName packageName, TypeCode code) {
    adapter.writeJavaFile(
      javaFile(packageName, code)
    );
  }

}
