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
package objectos.docs.code.field;

import java.util.List;
import objectos.code.ArrayTypeName;
import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;

public class FieldInit06 extends JavaTemplate {
  static final ClassTypeName FOO
      = ClassTypeName.of("com.example", "Foo");

  static final ArrayTypeName FOO_ARRAY
      = ArrayTypeName.of(FOO);

  public static void main(String[] args) {
    System.out.println(new FieldInit06());
  }

  @Override
  protected final void definition() {
    autoImports();

    classDeclaration(
      name("Include"),

      field(
        FOO_ARRAY, name("a"), include(this::init)
      )
    );
  }

  private void init() {
    var values = List.of("A", "B", "C", "D");

    arrayInitializer();

    code(NL);

    for (var value : values) {
      value(FOO, n(value));

      code(NL);
    }
  }
}