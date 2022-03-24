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

import static br.com.objectos.code.java.expression.Expressions.id;
import static br.com.objectos.code.java.type.NamedTypes.t;

import br.com.objectos.code.boot.AbstractCodeBootTest.Fields;
import br.com.objectos.code.java.expression.Identifier;
import br.com.objectos.code.java.type.NamedClass;
import br.com.objectos.code.java.type.NamedType;

class StaticFieldFake extends StaticField.Builder {

  public static final StaticField FIELD_TEST = new StaticFieldFake()
      .enclosingClass(Fields.class)
      .typeName(t(String.class))
      .name("FIELD_TEST")
      .build();

  private NamedClass enclosingClassName;
  private NamedType typeName;
  private Identifier name;

  @Override
  final NamedClass enclosingClassName() {
    return enclosingClassName;
  }

  @Override
  final Identifier name() {
    return name;
  }

  @Override
  final NamedType typeName() {
    return typeName;
  }

  private StaticFieldFake enclosingClass(Class<?> type) {
    enclosingClassName = t(type);
    return this;
  }

  private StaticFieldFake name(String string) {
    name = id(string);
    return this;
  }

  private StaticFieldFake typeName(NamedClass className) {
    typeName = className;
    return this;
  }

}
