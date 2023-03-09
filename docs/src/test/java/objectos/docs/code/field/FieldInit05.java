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

import objectos.code.ArrayTypeName;
import objectos.code.JavaTemplate;

public class FieldInit05 extends JavaTemplate {
  static final ArrayTypeName STRING_ARRAY
      = ArrayTypeName.of(String[].class);

  public static void main(String[] args) {
    System.out.println(new FieldInit05());
  }

  @Override
  protected final void definition() {
    autoImports();

    classDeclaration(
      name("ArrayInit"),

      field(
        STRING_ARRAY, name("a"), arrayInitializer(),
        value(s("A")), value(s("B")), value(s("C"))
      )
    );
  }
}