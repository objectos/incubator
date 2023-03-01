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
package objectos.docs.code.template;

import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;

// objectos-code/java-template.adoc
public class AutoImportsConflict extends JavaTemplate {
  private static final ClassTypeName LIST_A = ClassTypeName.of(java.awt.List.class);

  private static final ClassTypeName LIST_B = ClassTypeName.of(java.util.List.class);

  private static final ClassTypeName LIST_C = ClassTypeName.of("com.example", "List");

  public static void main(String[] args) {
    System.out.println(new AutoImportsConflict());
  }

  @Override
  protected final void definition() {
    packageDeclaration("com.example");

    autoImports();

    classDeclaration(
      PUBLIC, name("AutoImports"),

      field(LIST_A, name("a")),

      field(LIST_B, name("b")),

      field(LIST_C, name("c"))
    );
  }
}