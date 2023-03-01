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
package objectos.docs.code;

import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;

public class Tutorial06 extends JavaTemplate {
  private static final ClassTypeName SUBJECT
      = ClassTypeName.of("com.example.annotations", "Subject");

  public static void main(String[] args) {
    System.out.println(new Tutorial06());
  }

  @Override
  protected final void definition() {
    packageDeclaration("com.example");

    autoImports();

    classDeclaration(
      annotation(
        SUBJECT,
        annotationValue(s("Objectos Code"))
      ),
      PUBLIC, FINAL, name("Tutorial"),

      field(
        PRIVATE, FINAL, INT, name("value")
      ),

      constructor(
        PUBLIC,
        parameter(INT, name("value")),

        p(THIS, n("value"), IS, n("value"))
      )
    );
  }
}