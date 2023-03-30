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

import objectos.code.ClassTypeName;
import objectos.code.JavaTemplate;

public class MethodBody01 extends JavaTemplate {
  static final ClassTypeName OBJECT
      = ClassTypeName.of(Object.class);
  static final ClassTypeName IAE
      = ClassTypeName.of(IllegalArgumentException.class);

  public static void main(String[] args) {
    System.out.println(new MethodBody01());
  }

  @Override
  protected final void definition() {
    autoImports();

    classDeclaration(
      name("MethodBodyExample"),

      field(OBJECT, name("value")),

      method(
        PUBLIC, VOID, name("set"),
        parameter(OBJECT, name("value")),

        p(IF, condition(n("value"), EQ, NULL), block(
          p(THROW, NEW, IAE, argument(s("value is null")))
        )),

        p(THIS, n("value"), IS, n("value"))
      ),

      method(
        PUBLIC, OBJECT, name("get"),

        p(RETURN, n("value"))
      )
    );
  }
}
