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
package objectos.docs.code.class_;

import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;

public class ClassDeclarationImplements07 extends JavaTemplate {
  static final ClassTypeName IFACE_A
      = ClassTypeName.of("com.example", "InterfaceA");
  static final ClassTypeName IFACE_B
      = ClassTypeName.of("com.example", "InterfaceB");
  static final ClassTypeName IFACE_C
      = ClassTypeName.of("com.example", "InterfaceC");
  static final ClassTypeName IFACE_D
      = ClassTypeName.of("com.example", "InterfaceD");

  public static void main(String[] args) {
    System.out.println(new ClassDeclarationImplements07());
  }

  @Override
  protected final void definition() {
    autoImports();

    classDeclaration(
      name("Implements"),
      implementsClause(NL, IFACE_A, NL),
      implementsClause(IFACE_B, IFACE_C),
      implementsClause(NL, IFACE_D)
    );
  }
}