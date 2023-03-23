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
package objectos.docs.code.method;

import java.util.List;
import objectos.code.ArrayTypeName;
import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;
import objectos.code.ParameterizedTypeName;

public class MethodParams05 extends JavaTemplate {
  static final ClassTypeName STRING
      = ClassTypeName.of(String.class);

  static final ParameterizedTypeName LIST
      = ParameterizedTypeName.of(
        ClassTypeName.of(List.class),
        STRING
      );

  static final ArrayTypeName OBJECT_ARRAY
      = ArrayTypeName.of(Object[].class);

  public static void main(String[] args) {
    System.out.println(new MethodParams05());
  }

  @Override
  protected final void definition() {
    autoImports();

    classDeclaration(
      name("Varargs"),

      method(
        ABSTRACT, name("ex01"),
        parameter(INT, ELLIPSIS, name("values"))
      ),

      method(
        ABSTRACT, name("ex02"),
        parameter(LIST, ELLIPSIS, name("lists"))
      ),

      method(
        ABSTRACT, name("ex03"),
        parameter(OBJECT_ARRAY, ELLIPSIS, name("arrays"))
      )
    );
  }
}