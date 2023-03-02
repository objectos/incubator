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
package objectos.docs.code.declaration;

import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;

public class ClassDeclarationImplements04 extends JavaTemplate {
  static final ClassTypeName A
      = ClassTypeName.of("com.example", "A");
  static final ClassTypeName B
      = ClassTypeName.of("com.example", "B");

  public static void main(String[] args) {
    System.out.println(new ClassDeclarationImplements04());
  }

  @Override
  protected final void definition() {
    autoImports();

    classDeclaration(
      name("Implements"),
      implementsClause(A),
      implementsClause(A),
      implementsClause(B, B)
    );
  }
}