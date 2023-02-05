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
package br.com.objectos.css.boot.spec;

import br.com.objectos.code.java.declaration.PackageName;
import br.com.objectos.code.java.declaration.TypeCode;
import br.com.objectos.css.boot.function.FunctionName;
import br.com.objectos.css.boot.keyword.KeywordName;
import br.com.objectos.css.boot.property.Property;
import br.com.objectos.css.boot.sheet.FunctionOrProperty;
import br.com.objectos.css.boot.sheet.MethodSignature;
import br.com.objectos.css.boot.type.ColorName;
import br.com.objectos.css.boot.type.PrimitiveType;
import br.com.objectos.css.boot.type.ValueType;

public abstract class Step {

  private final StepAdapter adapter;

  protected Step(StepAdapter adapter) {
    this.adapter = adapter;
  }

  public void addAngleUnit(String unit) {}

  public void addColorName(ColorName colorName) {}

  public void addElementName(String elementName) {}

  public void addFunction(FunctionName function) {}

  public void addKeyword(KeywordName keyword) {}

  public void addLengthUnit(String unit) {}

  public void addMethodSignature(FunctionOrProperty property, MethodSignature signature) {}

  public void addPrimitiveType(PrimitiveType type) {}

  public void addProperty(Property property) {}

  public void addPseudoClass(String name) {}

  public void addPseudoElement(String name) {}

  public void addValueType(ValueType type) {}

  public void execute() {}

  protected final void writeJavaFile(PackageName packageName, TypeCode code) {
    adapter.writeJavaFile(packageName, code);
  }

}
