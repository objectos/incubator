/*
 * Copyright (C) 2022-2023 Objectos Software LTDA.
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
package objectos.docs.code.constructor;

import java.util.List;
import objectos.code.ArrayTypeName;
import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;
import objectos.code.ParameterizedTypeName;
import objectos.code.TypeVariableName;

public class ConstructorParams02 extends JavaTemplate {
  static final ClassTypeName STRING
      = ClassTypeName.of(String.class);

  static final ParameterizedTypeName LIST
      = ParameterizedTypeName.of(
        ClassTypeName.of(List.class),
        STRING
      );

  static final TypeVariableName E
      = TypeVariableName.of("E");

  static final ArrayTypeName OBJECT_ARRAY
      = ArrayTypeName.of(Object[].class);

  public static void main(String[] args) {
    System.out.println(new ConstructorParams02());
  }

  @Override
  protected final void definition() {
    autoImports();

    classDeclaration(
      name("TypeAndName"),

      constructor(
        parameter(INT, name("primitive")),
        parameter(STRING, name("classOrInterface"))
      ),

      constructor(
        parameter(LIST, name("parameterized"))
      ),

      constructor(
        parameter(E, name("typeVariable")),
        parameter(OBJECT_ARRAY, name("array"))
      )
    );
  }
}