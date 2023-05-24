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

import br.com.objectos.code.annotations.Generated;
import br.com.objectos.css.boot.CssBoot;
import java.util.Locale;
import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;
import objectos.util.GrowableMap;
import objectos.util.UnmodifiableMap;

abstract class ThisTemplate extends JavaTemplate implements Step {

  static final ClassTypeName GROWABLE_MAP = ClassTypeName.of(GrowableMap.class);

  static final ClassTypeName IAE = ClassTypeName.of(IllegalArgumentException.class);

  static final ClassTypeName LOCALE = ClassTypeName.of(Locale.class);

  static final ClassTypeName STRING = ClassTypeName.of(String.class);

  static final ClassTypeName UNMODIFIABLE_MAP = ClassTypeName.of(UnmodifiableMap.class);

  static final String css = "br.com.objectos.css";

  static final String function = "br.com.objectos.css.function";

  static final String keyword = "br.com.objectos.css.keyword";

  static final String property = "br.com.objectos.css.property";

  static final String select = "br.com.objectos.css.select";

  static final String sheet = "br.com.objectos.css.sheet";

  static final String type = "br.com.objectos.css.type";

  private final StepAdapter adapter;

  ThisTemplate(StepAdapter adapter) {
    this.adapter = adapter;
  }

  @Override
  public void addAngleUnit(String unit) {}

  @Override
  public void addColorName(ColorName colorName) {}

  @Override
  public void addElementName(String elementName) {}

  @Override
  public void addFunction(FunctionName function) {}

  @Override
  public void addKeyword(KeywordName keyword) {}

  @Override
  public void addLengthUnit(String unit) {}

  @Override
  public void addMethodSignature(FunctionOrProperty property, MethodSignature signature) {}

  @Override
  public void addPrimitiveType(PrimitiveType type) {}

  @Override
  public void addProperty(Property property) {}

  @Override
  public void addPseudoClass(String name) {}

  @Override
  public void addPseudoElement(String name) {}

  @Override
  public void addValueType(ValueType type) {}

  @Override
  public void execute() {}

  @Override
  protected void definition() {}

  final void writeSelf() {
    adapter.write(this);
  }

  final void generatedAnnotation() {
    at(t(Generated.class), s(CssBoot.class.getCanonicalName()));
  }

}